package com.haitiong.timestampbatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TimestampBatchTasklet implements Tasklet {

    private static final Log logger = LogFactory.getLog(TimestampBatchTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.info("Start running Tasklet...");
        StepContext stepContext = chunkContext.getStepContext();
        logger.info("job name: " + stepContext.getJobName() + "; step name: " + stepContext.getStepName());
        return RepeatStatus.FINISHED;
    }
}
