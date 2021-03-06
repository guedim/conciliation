package com.mario.movilred.conciliation.processor;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.repository.ConciliationFileRepository;

@Component
public class SaveFileProcessor implements Processor  {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(SaveFileProcessor.class.getName());
  
  @Autowired
  private ConciliationFileRepository repository;
  
  @Override
  public void process(Exchange exchange) throws Exception {
    String fileContent = exchange.getIn().getBody(String.class);
    LOGGER.info("processing conciliation file with content: " + fileContent);
    repository.save(fileContent);
  }
}