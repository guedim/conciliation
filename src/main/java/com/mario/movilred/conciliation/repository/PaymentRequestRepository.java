package com.mario.movilred.conciliation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mario.movilred.conciliation.model.PaymentRequest;

@Repository
public class PaymentRequestRepository {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(PaymentRequestRepository.class.getName());


  @Autowired(required = false)
  private JdbcTemplate jdbcTemplate;

  public List<PaymentRequest> findByDates(Date startDate, Date endDate) {

    List<PaymentRequest> result = null;
    if (jdbcTemplate != null) {

      result = jdbcTemplate.query(
          "SELECT id, creation_date, reference, value FROM payment_request WHERE creation_date >= ? and creation_date <= ?",
          new Object[] {startDate, endDate}, new PaymentRequestRowMapper());
      return result;

    } else {
      logFileContent();
      return result;
    }

  }

  private void logFileContent() {
    LOGGER.severe(
        "The postgres datasource is not enabled. You have to change the configuration options in the application properties file");
  }

}


class PaymentRequestRowMapper implements RowMapper<PaymentRequest> {
  @Override
  public PaymentRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
    PaymentRequest pr = new PaymentRequest();
    pr.setId(rs.getString("id"));
    pr.setCreationDate(rs.getDate("creation_date"));
    pr.setReference("reference");
    pr.setValue(rs.getBigDecimal("value"));
    return pr;
  }
}
