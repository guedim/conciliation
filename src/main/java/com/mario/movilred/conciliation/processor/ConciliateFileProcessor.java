package com.mario.movilred.conciliation.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.model.ConciliationFile;

@Component
public class ConciliateFileProcessor implements Processor{

  @SuppressWarnings("unchecked")
  @Override
  public void process(Exchange exchange) throws Exception {
    List<ConciliationFile> records = exchange.getIn().getBody(List.class);
    System.err.println("IN processor: " + records);
    //TODO: lógica para conciliar el archivo de Movilred
  }
}