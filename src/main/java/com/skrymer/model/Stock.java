package com.skrymer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * "name" : "Microsoft Corporation",
 * "price" : "51.380001",
 * "symbol" : "MSFT",
 * "ts" : "1467835200",
 * "type" : "equity",
 * "utctime" : "2016-07-06T20:00:00+0000",
 * "volume" : "28167461"
 */
public class Stock {
  public static Stock EMPTY_STOCK = new Stock("", "","","","","","");
  private String name;
  private String price;
  private String symbol;
  private String ts;
  private String type;
  private String utctime;
  private String volume;

  public Stock(){}

  public Stock(String name, String price, String symbol, String ts, String type, String utctime, String volume){
    this.name = name;
    this.price = price;
    this.symbol = symbol;
    this.ts = ts;
    this.type = type;
    this.utctime = utctime;
    this.volume = volume;
  }

  public String getName() {
    return name;
  }

  public String getPrice() {
    return price;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getType() {
    return type;
  }

  public String getUtctime() {
    return utctime;
  }

  public String getVolume() {
    return volume;
  }

  public String getTs() {
    return ts;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUtctime(String utctime) {
    this.utctime = utctime;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public void setTs(String ts) {
    this.ts = ts;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static RowMapper<Stock> getRowMapper() {
    return new RowMapper<Stock>() {
      public Stock mapRow(ResultSet resultSet, int i) throws SQLException {
        Stock stock = new Stock();
        stock.setName(resultSet.getString("name"));
        stock.setPrice(resultSet.getString("price"));
        stock.setSymbol(resultSet.getString("symbol"));
        stock.setTs(resultSet.getString("ts"));
        stock.setType(resultSet.getString("type"));
        stock.setUtctime(resultSet.getString("utcTime"));
        stock.setVolume(resultSet.getString("volume"));
        return stock;
      }
    };
  }
}
