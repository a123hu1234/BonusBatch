package com.huateng.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

//@RestController
//@Controller
public class DemoController {
    
        @Autowired
        JobLauncher jobLauncher;

        @Autowired
        @Qualifier("importJob")
        Job importJob;
        public JobParameters   jobParameters;
        
      //  @RequestMapping("/read")
        public String imp(String fileName) throws Exception{
            
            String path = fileName+".csv";
            jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .addString("input.file.name", path)
                    .toJobParameters();
            jobLauncher.run(importJob,jobParameters);
            return "ok";
        }

}
