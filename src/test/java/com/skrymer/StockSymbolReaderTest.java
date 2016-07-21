package com.skrymer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by nielses on 21/07/16.
 */
@ContextConfiguration(locations = { "/test-context.xml" })
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    StepScopeTestExecutionListener.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public class StockSymbolReaderTest {
  @Autowired
  @Qualifier("stockSymbolReader")
  FlatFileItemReader itemReader;

  @Test
  public void testStockSymbolReader() throws Exception {
    itemReader.open(new ExecutionContext());

    Object obj;

    while((obj = itemReader.read()) != null){
      System.out.println(obj);
    }
  }
}
