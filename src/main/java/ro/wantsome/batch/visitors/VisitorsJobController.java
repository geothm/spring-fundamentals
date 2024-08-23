package ro.wantsome.batch.visitors;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.wantsome.databases.domain.batch.Visitors;
import ro.wantsome.databases.domain.batch.repository.VisitorsRepository;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorsJobController {

    @Autowired
    private JobLauncher jobLauncher;

    //We need to explicit tell what Job bean to use, because in our code right now we have 2, the importUserJob(the one with the names)
    // and importVisitorsJob and spring does not know which to use without us
    @Autowired
    @Qualifier("importVisitorsJob")
    private Job importVisitorsJob;

    @Autowired
    private VisitorsRepository visitorsRepository;


    //GET endpoint to trigger the job batch. We are returning as response the status of the JOB, e.g. FAILED, COMPLETED
    @GetMapping("/runJob")
    public BatchStatus run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(importVisitorsJob, jobParameters);
        while (jobExecution.isRunning()){
            System.out.println("..................");
        }
        return jobExecution.getStatus();
    }

    //GET method to retrieve all the visitors from batch_visitors_table after the csv was processed.
    @GetMapping("/findAll")
    public ResponseEntity<List<Visitors>> getAllVisitors() {
        return ResponseEntity.ok(visitorsRepository.findAll());
    }
}
