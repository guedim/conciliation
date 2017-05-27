package com.mario.movilred.conciliation.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
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
    
    /*
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("To", "guedim@gmail.com");
    map.put("From", "guedim@gmail.com");
    map.put("Subject", "Camel rocks");
    map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    String body = "Hello Claus.\nYes it does.\n\nRegards James.";
    ProducerTemplate template = exchange.getContext().createProducerTemplate();
    template.sendBodyAndHeaders("smtp://smtp@gmail.com:587?username=guedim@gmail.com&password=i8gqj5wt&mail.smtp.auth=true&mail.smtp.starttls.enable=true&To=guedim@gmail.com", body, map);
    */
    
    /*email.from=from@gmail.com
    email.to=to@gmail.com
    mail.uri=smtps://smtp.gmail.com:465?username=guedim@gmail.com&password=i8gqj5wt...&debugMode=true*/

  }
}
