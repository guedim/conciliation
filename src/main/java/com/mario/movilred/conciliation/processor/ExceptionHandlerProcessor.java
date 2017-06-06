package com.mario.movilred.conciliation.processor;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerProcessor implements Processor {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ExceptionHandlerProcessor.class.getName());

  @Autowired
  private JavaMailSender emailSender;


  @Override
  public void process(Exchange exchange) throws Exception {

    StringBuilder message = new StringBuilder("Error processing conciliation file. ")
        .append(" Endpoint:" + exchange.getFromEndpoint()).append(" Exchage:" + exchange)
        .append(" Body:" + exchange.getIn().getBody());

    LOGGER.log(Level.SEVERE, message.toString());
    
    sendEmail();
    
    

    Properties mailServerProperties;
    Session getMailSession;
    MimeMessage generateMailMessage;
    
    // Step1
    System.out.println("\n 1st ===> setup Mail Server Properties..");
    mailServerProperties = System.getProperties();
    mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
    mailServerProperties.put("mail.smtp.socketFactory.port", "465");
    mailServerProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    mailServerProperties.put("mail.smtp.auth", "true");
    mailServerProperties.put("mail.smtp.port", "465");
    mailServerProperties.put("mail.smtp.starttls.enable", "true");
    System.out.println("Mail Server Properties have been setup successfully..");

    // Step2
    System.out.println("\n\n 2nd ===> get Mail Session..");
    getMailSession = Session.getDefaultInstance(mailServerProperties, null);
    generateMailMessage = new MimeMessage(getMailSession);
    generateMailMessage.addRecipient(Message.RecipientType.TO,
        new InternetAddress("integrations@minka.io"));
    generateMailMessage.setSubject("Greetings from Crunchify..");
    String emailBody = "Test email by Crunchify.com JavaMail API example. "
        + "<br><br> Regards, <br>Crunchify Admin";
    generateMailMessage.setContent(emailBody, "text/html");
    System.out.println("Mail Session has been created successfully..");

    // Step3
    System.out.println("\n\n 3rd ===> Get Session and Send mail");
    Transport transport = getMailSession.getTransport("smtp");

    // Enter your correct gmail UserID and Password
    // if you have 2FA enabled then provide App Specific Password
    transport.connect("guedim@gmail.com", "i8gqj5wt");
    transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
    transport.close();
  }


  private void sendEmail() throws Exception {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setTo("integrations@minka.io");
    helper.setText("How are you?");
    helper.setSubject("Hi");
    emailSender.send(message);
  }



}
