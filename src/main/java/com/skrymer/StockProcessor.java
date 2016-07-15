package com.skrymer;

import com.skrymer.model.Stock;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

public class StockProcessor implements ItemProcessor<String, Stock> {
  public static final Log LOG = LogFactory.getLog(StockProcessor.class);

  public Stock process(String symbol) throws Exception {
    LOG.info("Getting stock info for symbol: " + symbol);
    Stock stock = new Stock("name-" + symbol, new Double(RandomUtils.nextDouble(0, 100)).toString(), symbol, "ts", "type", "utctime", "volume");
    LOG.info("Stock " + stock);

    return stock;
  }

//Using broken endpoint
//  HttpHost proxy = new HttpHost("192.168.58.129", 3128, "http");
//
//  String response = Request.Get("http://finance.yahoo.com/webservice/v1/symbols/" + symbol + "/quote?format=json")
//      .viaProxy(proxy)
//      .execute()
//      .returnContent()
//      .asString();
//
//  LOG.info("Response received: " + response);
//  Stock stock = JsonPath.parse(response).read("$.list.resources[0].resource.fields", Stock.class);
//  LOG.info("Stock " + stock);


//  process using yql
//HttpHost proxy = new HttpHost("192.168.58.129", 3128, "http");
//  String url = "http://query.yahooapis.com/v1/public/yql?";
//
//  String queryParam = URLEncodedUtils.format(Arrays.asList(
//      new BasicNameValuePair("q","select * from yahoo.finance.quotes where symbol in (\"" + symbol + "\")"),
//      new BasicNameValuePair("format", "json"),
//      new BasicNameValuePair("env", "store://datatables.org/alltableswithkeys")
//      ),
//      CharEncoding.UTF_8);
//
//  LOG.info("Getting stock info for symbol: " + symbol + " URL " + url + queryParam);
//  String response = Request.Get(url + queryParam)
//      .viaProxy(proxy)
//      .execute()
//      .returnContent()
//      .asString();
//
//  LOG.info("Response received: " + response);
//  Stock stock = JsonPath.parse(response).read("$.query.results[0].quote", Stock.class);
//  LOG.info("Stock " + stock);
//
//  return stock;

}
