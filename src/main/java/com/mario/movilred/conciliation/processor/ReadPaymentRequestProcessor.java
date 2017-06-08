package com.mario.movilred.conciliation.processor;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.model.PaymentRequest;
import com.mario.movilred.conciliation.repository.PaymentRequestRepository;
import com.mario.movilred.conciliation.utils.DateUtils;

@Component
public class ReadPaymentRequestProcessor implements Processor {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ReadPaymentRequestProcessor.class.getName());

  @Autowired
  PaymentRequestRepository repository;
  
  @Override
  public void process(Exchange exchange) throws Exception {
    
    Date startDate = DateUtils.getStartOfDay(new Date());
    Date endDate = DateUtils.getEndOfDay(new Date());
    LOGGER.log(Level.INFO,  String.format("Processing payment request bewtween {0} and {1} ",  String.valueOf(startDate), String.valueOf(endDate)));
    
    List<PaymentRequest> prs = repository.findByDates(startDate, endDate);
    //TODO: Es necesario procesar los registros que se van a publicar en el ftp
    for (PaymentRequest paymentRequest : prs) {
      System.out.println(paymentRequest);
    }
    exchange.getOut().setBody(prs, List.class);
  }

}
