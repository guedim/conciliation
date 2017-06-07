package com.mario.movilred.conciliation.processor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerProcessor implements Processor {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ExceptionHandlerProcessor.class.getName());

  @Value("${email.to.notify.error}")
  private String emailToNofifyError;
  
  @Autowired
  private JavaMailSender emailSender;


  @Override
  public void process(Exchange exchange) throws Exception {

    StringBuilder errorMessage = new StringBuilder("Error processing conciliation file. ")
        .append(" Endpoint:" + exchange.getFromEndpoint()).append(" Exchage:" + exchange)
        .append(" Body:" + exchange.getIn().getBody());

    LOGGER.log(Level.SEVERE, errorMessage.toString());
    
    sendEmail(errorMessage.toString());
  }


  private void sendEmail(String errorMessage) throws Exception {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setTo(emailToNofifyError);
    helper.setText(errorMessage);
    helper.setSubject("Error in Movilred Conciliation Process");
    emailSender.send(message);
  }
}