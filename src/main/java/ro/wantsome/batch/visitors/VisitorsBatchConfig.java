package ro.wantsome.batch.visitors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import ro.wantsome.databases.domain.batch.repository.VisitorsRepository;
import ro.wantsome.databases.domain.batch.Visitors;

@Configuration
@EnableScheduling
public class VisitorsBatchConfig {

    @Autowired
    private VisitorsRepository visitorsRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    //with this method we are creating the job itself to export data from CSV and then process it and save it into our DB
    @Bean
    public Job importVisitorsJob() {
        return new JobBuilder("importVisitorsJob", jobRepository)
                .start(importVisitorsStep(jobRepository, transactionManager))
                .build();
    }

    //Generating the step that will be used by the job
    @Bean
    public Step importVisitorsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("importVisitorsStep", jobRepository)
                .<Visitors, Visitors>chunk(100, transactionManager)
                .reader(visitorReader())
                .processor(itemProcessor())
                .writer(visitorWriter())
                .build();
    }

    //Creating the instance of our processor, that based on our custom implementation from VisitorsItemProcessor class
    //will map strVisitDate to visitDate field
    @Bean
    public ItemProcessor<Visitors, Visitors> itemProcessor() {
        return new VisitorsItemProcessor();
    }


    //Our writer that will persist all the field in the database
    @Bean
    public ItemWriter<Visitors> visitorWriter() {
        return visitorsRepository::saveAll;
    }

    //Our reader which will read a file, named convert.csv(the file is placed in the resource folder)
    @Bean
    public FlatFileItemReader<Visitors> visitorReader(){
        FlatFileItemReader<Visitors> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("WANTSOME");
        flatFileItemReader.setLinesToSkip(1);//it will skip first line
        flatFileItemReader.setResource(new ClassPathResource("convert.csv"));
        flatFileItemReader.setLineMapper(linMappe());
        return flatFileItemReader;
    }

    //additional method that is telling the program how to parse the csv to get the data mapped to a Person object
    @Bean
    public LineMapper<Visitors> linMappe() {
        DefaultLineMapper<Visitors> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("id","firstName","lastName","emailAddress","phoneNumber","address","strVisitDate");
        lineTokenizer.setStrict(false); // Set strict property to false
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(Visitors.class);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}