package com.skrymer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ContextConfiguration(locations = {"classpath*:test-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class StockJobTest {
  @Autowired
  private JobLauncherTestUtils jobLauncherUtils;

  @Test
  public void testJob() throws Exception {
    JobExecution jobExecution = jobLauncherUtils.launchJob();

    Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
  }
}
