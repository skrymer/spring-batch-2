package com.skrymer;

import com.jayway.jsonpath.JsonPath;
import com.skrymer.model.Stock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.fluent.Request;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by skrymer on 7/07/16.
 */
public class StockProcessor implements ItemProcessor<String, Stock> {
  public static final Log LOG = LogFactory.getLog(StockProcessor.class);

  public Stock process(String symbol) throws Exception {
    LOG.info("Getting stock info for symbol: " + symbol);

    String response = Request.Get("http://finance.yahoo.com/webservice/v1/symbols/" + symbol + "/quote?format=json")
        .execute()
        .returnContent()
        .asString();

    LOG.info("Response received: " + response);
    Stock stock = JsonPath.parse(response).read("$.list.resources[0].resource.fields", Stock.class);
    LOG.info("Stock " + stock);

    return stock;
  }
}
