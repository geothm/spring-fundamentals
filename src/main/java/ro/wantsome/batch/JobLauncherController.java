package ro.wantsome.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobParametersNotFoundException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/batch")
public class JobLauncherController {


    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job importUserJob;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobExplorer jobExplorer;

    @GetMapping("/launch-job")
    public void launchJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("Starting our job!!");
        JobParameters parameters = new JobParametersBuilder()
                .addLong("run.id", new Date().getTime()).toJobParameters();

        jobLauncher.run(importUserJob, parameters);
    }

//    @Scheduled(cron = " */5 * * * * *")
    public void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, NoSuchJobException, JobParametersNotFoundException {
        List<JobInstance> lastInstances = jobExplorer.getJobInstances(importUserJob.getName(), 0, 1);

        if (lastInstances.isEmpty()) {
            jobLauncher.run(importUserJob, new JobParameters());
        } else {
           jobOperator.startNextInstance(importUserJob.getName()) ;
        }

//        JobExecution execution = jobLauncher.run(
//                importUserJob,
//                new JobParametersBuilder()
//                        .addLong("unique", System.nanoTime())
//                        .toJobParameters()
//        );
    }
}
