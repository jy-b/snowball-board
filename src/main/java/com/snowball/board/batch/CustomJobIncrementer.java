package com.snowball.board.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class CustomJobIncrementer implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters parameters) {

        return new JobParametersBuilder(parameters)
                .addLong("customIncrement", System.currentTimeMillis())
                .toJobParameters();
    }
}
