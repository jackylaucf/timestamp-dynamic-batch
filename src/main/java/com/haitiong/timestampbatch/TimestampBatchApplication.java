package com.haitiong.timestampbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class TimestampBatchApplication extends CommandLineJobRunner {

	public static void main(String[] args) {
		SpringApplication.run(TimestampBatchApplication.class, args);
	}

}
