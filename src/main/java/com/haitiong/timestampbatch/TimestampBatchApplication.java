package com.haitiong.timestampbatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
@EnableConfigurationProperties(TimestampDynamicBatchProperties.class)
@ImportResource("classpath:batchJob.xml")
public class TimestampBatchApplication implements CommandLineRunner {

	private static final Log logger = LogFactory.getLog(TimestampBatchApplication.class);

	@Autowired
	public JobLauncher jobLauncher;

	@Autowired
	public TimestampDynamicBatchProperties properties;

	@Autowired
	private ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(TimestampBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("========Start launching Timestemp-Dynamic-Batch========");
		logger.info("Get Property: ");
		String dataDate = System.getProperty("dataDate", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
		String jobId = properties.getJobId();
		logger.info("dateDate: " + dataDate + "; jobId: " + jobId);

		JobParameters jobParameters = new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis()).toJobParameters();
		jobLauncher.run(ctx.getBean(jobId, Job.class), jobParameters);

		logger.info("========End launching Timestamp-Dynamic-Batch========");
	}
}
