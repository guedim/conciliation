package com.mario.movilred.conciliation.processor;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExceptionHandlerProcessor implements Processor  {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ExceptionHandlerProcessor.class.getName());

  @Override
  public void process(Exchange exchange) throws Exception {
    String body = exchange.getIn().toString();
    LOGGER.log(Level.SEVERE, "Error processing file:" + body);
  }

}
