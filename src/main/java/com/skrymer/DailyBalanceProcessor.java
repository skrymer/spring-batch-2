package com.skrymer;

import com.skrymer.model.Acquisition;
import com.skrymer.model.Owner;
import com.skrymer.model.Stock;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by skrymer on 8/07/16.
 */
public class DailyBalanceProcessor implements ItemProcessor<Owner, Owner> {
  private JdbcTemplate jdbcTemplate;

  public DailyBalanceProcessor(DataSource dataSource){
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Owner process(Owner owner) throws Exception {
    List<Acquisition> acquisitions = jdbcTemplate.query("select * from  acquisition where owner_name = ?",  Acquisition.getRowMapper(), owner.getName());

    owner.setDailyBalance(calculateDailyBalance(acquisitions));

    Thread.sleep(1000);

    return owner;
  }

  private String calculateDailyBalance(List<Acquisition> acquisitions) {
    String stocksSql = "select * from stock where symbol " + inClause(acquisitions);
    List<Stock> stocks = jdbcTemplate.query(stocksSql, Stock.getRowMapper());

    BigDecimal dailyBalance = BigDecimal.ZERO;
    for(Acquisition acquisition : acquisitions){
      Stock stock = getStockWithSymbol(acquisition.getStockSymbol(), stocks);

      BigDecimal stockPrice = new BigDecimal(stock.getPrice());
      BigDecimal acquiredStocks = BigDecimal.valueOf(acquisition.getAcquired());
      dailyBalance = dailyBalance.add(acquiredStocks.multiply(stockPrice));
    }

    return dailyBalance.toPlainString();
  }

  private Stock getStockWithSymbol(String stockSymbol, List<Stock> stocks) {
    for(Stock stock : stocks){
      if(stock.getSymbol().equals(stockSymbol)){
        return stock;
      }
    }

    return Stock.EMPTY_STOCK;
  }

  private String inClause(List<Acquisition> acquisitions){
    StringBuilder inClause = new StringBuilder("in (");

    for(int i = 0; i < acquisitions.size(); i++){
      Acquisition acquisition = acquisitions.get(i);
      if(isLastElement(i, acquisitions)){
        inClause.append("'" + acquisition.getStockSymbol() + "'");
      }
      else {
        inClause.append("'" + acquisition.getStockSymbol() + "',");
      }
    }
    inClause.append(")");

    return inClause.toString();
  }

  private boolean isLastElement(int index, List<Acquisition> acquisitions) {
    return (index + 1 == acquisitions.size()) ? true : false;
  }
}
