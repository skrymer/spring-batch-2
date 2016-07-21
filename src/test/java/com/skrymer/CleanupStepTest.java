package com.skrymer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath*:test-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class CleanupStepTest {
  @Autowired
  CleanupTasklet cleanupTasklet;

  @Autowired
  private JobLauncherTestUtils jobLauncherUtils;

  @Test
  public void testStep(){
    assertEquals(BatchStatus.COMPLETED, jobLauncherUtils.launchStep("cleanup").getStatus());
  }

  @Test
  public void testCleanupStocks(){
    System.out.println("");
//    cleanupTasklet.execute()
  }

}
