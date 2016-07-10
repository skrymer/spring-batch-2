package com.skrymer;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by skrymer on 7/07/16.
 */
public class CleanupTasklet implements Tasklet {
  private JdbcTemplate template;

  @Autowired
  public CleanupTasklet(DataSource dataSource){
      this.template = new JdbcTemplate(dataSource);
  }

  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    template.execute("delete from stock");

    return RepeatStatus.FINISHED;
  }
}
