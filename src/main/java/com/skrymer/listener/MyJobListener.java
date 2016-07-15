package com.skrymer.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by nielses on 14/07/16.
 */
public class MyJobListener implements JobExecutionListener {
  private static final Log LOG = LogFactory.getLog(MyJobListener.class);
  private ThreadPoolTaskExecutor executor;

  public MyJobListener(ThreadPoolTaskExecutor threadPoolTaskExecutor){
    this.executor = threadPoolTaskExecutor;
  }

  @Override
  public void beforeJob(final JobExecution jobExecution) {
    executor.execute(new Runnable(){
      @Override
      public void run() {
        LOG.info(jobExecution.getJobInstance().getJobName().toUpperCase() + " has started at: " + jobExecution.getStartTime() );
      }
    });
  }

  @Override
  public void afterJob(final JobExecution jobExecution) {
    executor.execute(new Runnable(){
      @Override
      public void run() {
        String jobName = jobExecution.getJobInstance().getJobName().toUpperCase();

        if(BatchStatus.STOPPED.equals(jobExecution.getStatus())){
          LOG.error(jobName + " has failed at: " + jobExecution.getEndTime() + " with status : " + jobExecution.getStatus() );
        }
        else {
          LOG.info(jobName + " has ended at: " + jobExecution.getEndTime() + " with status : " + jobExecution.getStatus() );
        }
      }
    });
  }
}
