package com.mario.movilred.conciliation.processor;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.model.ConciliationFileDetail;

@Component
public class ConvertFileProcessor implements Processor {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ConvertFileProcessor.class.getName());


  @SuppressWarnings("unchecked")
  @Override
  public void process(Exchange exchange) throws Exception {

    Message in = exchange.getIn();
    try {
      List<ConciliationFileDetail> records = (List<ConciliationFileDetail>) in.getBody();
      LOGGER.log(Level.INFO, "converted file with {0} records", records.size());
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Could not process conciliation file");
    }
  }
}
