package com.skrymer.web.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class JobController {

  @Autowired
  JobLauncher jobLauncher;

  @Autowired
  JobRepository jobRepository;

  @Autowired
  JobOperator jobOperator;

  @Autowired
  JobRegistry jobRegistry;

  @Autowired
  JobExplorer jobExplorer;

  @Autowired
  @Qualifier("stockJob")
  Job job;



  @RequestMapping("/launch")
  public ResponseEntity<String> handle() throws Exception{
    JobParameter parameter = new JobParameter(System.currentTimeMillis());
    Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
    parameters.put("time", parameter);

    //Not async? - weird
    jobLauncher.run(job, new JobParameters(parameters));

    return new ResponseEntity<String>("ok", HttpStatus.OK);
  }

  @RequestMapping("/stop")
  public ResponseEntity<String> stop(){
    try {
      Set<Long> executions = jobOperator.getRunningExecutions(job.getName());
      jobOperator.stop(executions.iterator().next());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>("ok", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<String>("ok", HttpStatus.OK);
  }

  @RequestMapping("/restart")
  public ResponseEntity<String> restart(){
    try {
      Set<Long> executions = jobOperator.getRunningExecutions(job.getName());

      jobOperator.stop(executions.iterator().next());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>("ok", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<String>("ok", HttpStatus.OK);
  }


//  Endpoints for stop and restart

  //Endpoints for getting job info
}