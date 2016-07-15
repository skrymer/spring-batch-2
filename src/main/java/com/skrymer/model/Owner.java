package com.skrymer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Stock owner
 */
public class Owner {
  private String name;
  private List<Acquisition> acquisitions;
  private String dailyBalance;

  public Owner(){}

  public List<Acquisition> getAcquisitions() {
    return acquisitions;
  }

  public void setAcquisitions(List<Acquisition> acquisitions) {
    this.acquisitions = acquisitions;
  }

  public String getDailyBalance() {
    return dailyBalance;
  }

  public void setDailyBalance(String dailyBalance) {
    this.dailyBalance = dailyBalance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static RowMapper<Owner> getMapper(){
    return new RowMapper<Owner>() {
      public Owner mapRow(ResultSet resultSet, int i) throws SQLException {
        Owner owner = new Owner();
        owner.setName(resultSet.getString("name"));
        owner.setDailyBalance(resultSet.getString("dailyBalance"));
        return owner;
      }
    };
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
