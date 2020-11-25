package com.yptraining.findAnyBatch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class FindAnyBatchApplication {
	
	@Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    Job job;

	public static void main(String[] args) {
		SpringApplication.run(FindAnyBatchApplication.class, args);
	}
	
	/**
	 * This performs the scheduler operation
	 * Time given is 3min at the moment
	 * @return
	 * @throws Exception
	 */
	@Scheduled(cron = "0 */3 * * * ?")
    public BatchStatus perform2() throws Exception {
	 Map<String, JobParameter> maps = new HashMap<>();
    maps.put("time", new JobParameter(System.currentTimeMillis()));
    JobParameters parameters = new JobParameters(maps);
    JobExecution jobExecution = jobLauncher.run(job, parameters);
    System.out.println("JobExecution: " + jobExecution.getStatus());
    System.out.println("findAny Batch is Running...");
    return jobExecution.getStatus();
    }
	

}
