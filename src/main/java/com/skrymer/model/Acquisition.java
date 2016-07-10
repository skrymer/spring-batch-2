package com.skrymer.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A stock acquisition
 */
public class Acquisition {
  private String stockSymbol;
  private Integer acquired;

  public Acquisition(){}

  public String getStockSymbol() {
    return stockSymbol;
  }

  public void setStockSymbol(String stockSymbol) {
    this.stockSymbol = stockSymbol;
  }


  public Integer getAcquired() {
    return acquired;
  }

  public void setAcquired(Integer acquired) {
    this.acquired = acquired;
  }

  public static RowMapper<Acquisition> getRowMapper(){
    return new RowMapper<Acquisition>() {
      public Acquisition mapRow(ResultSet resultSet, int i) throws SQLException {
        Acquisition acquisition = new Acquisition();
        acquisition.setStockSymbol(resultSet.getString("stockSymbol"));
        acquisition.setAcquired(resultSet.getInt("acquired"));
        return acquisition;
      }
    };
  }
}
