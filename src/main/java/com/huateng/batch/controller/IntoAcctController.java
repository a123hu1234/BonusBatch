package com.huateng.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IntoAcctController {

    
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("intoAcctJob")
    private Job intoAcctJob;
    private JobParameters   jobParameters;
    
  //  @RequestMapping("/read")
    public String imp(String fileName) throws Exception{
        
        String path = fileName+".csv";
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", path)
                .toJobParameters();
        jobLauncher.run(intoAcctJob,jobParameters);
        return "ok";
    }



}
