package com.haitiong.timestampbatch;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class TimestampDynamicBatchProperties {

    private String jobId;

    public String getJobId(){
        return this.jobId;
    }

    public void setJobId(String jobId){
        this.jobId = jobId;
    }
}
