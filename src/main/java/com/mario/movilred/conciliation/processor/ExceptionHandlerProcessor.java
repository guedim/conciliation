package com.mario.movilred.conciliation.processor;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerProcessor implements Processor  {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ExceptionHandlerProcessor.class.getName());

  @Override
  public void process(Exchange exchange) throws Exception {
    
    StringBuilder message = new StringBuilder("Error processing conciliation file. ")
          .append(" Endpoint:" + exchange.getFromEndpoint())
          .append(" Exchage:" + exchange)
          .append(" Body:" + exchange.getIn().getBody());
    
    LOGGER.log(Level.SEVERE, message.toString());
    //TODO: Enviar un correo electrónico
  }

}
