package com.haitiong.timestampbatch;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TimestampBatchConfiguration {

    @Bean
    public BatchConfigurer configurer(@Qualifier("dataSource-batch") DataSource dataSource){
        return new DefaultBatchConfigurer(dataSource) {
            @Override
            protected JobRepository createJobRepository() throws Exception {
                JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
                factory.setDataSource(dataSource);
                factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
                factory.setTransactionManager(getTransactionManager());
                factory.afterPropertiesSet();
                return factory.getObject();
            }
        };
    }
}
