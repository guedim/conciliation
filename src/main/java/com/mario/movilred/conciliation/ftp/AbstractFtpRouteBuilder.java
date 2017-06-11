package com.mario.movilred.conciliation.ftp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.mario.movilred.conciliation.processor.ExceptionHandlerProcessor;
import com.mario.movilred.conciliation.processor.SaveFileProcessor;

public class AbstractFtpRouteBuilder extends RouteBuilder {

  protected String fileExceptionConfiguration = "&throwExceptionOnConnectFailed=true&consumer.bridgeErrorHandler=true";
  
  protected String cronConfiguration = "&scheduler=quartz2&scheduler.cron=";
  
  @Value("${movilred.ftp.protocol}")
  protected String protocol;

  @Value("${movilred.ftp.server}")
  protected String server;

  @Value("${movilred.ftp.port}")
  protected String port;

  @Value("${movilred.ftp.user}")
  protected String user;

  @Value("${movilred.ftp.password}")
  protected String password;

  @Value("${movilred.ftp.stepwise:false}")
  protected String stepwise;

  @Value("${movilred.ftp.useList:false}")
  protected String useList;
  
  @Autowired
  protected SaveFileProcessor saveFileProcessor;
  
  @Autowired
  protected ExceptionHandlerProcessor exceptionHandlerProcessor;


  @Override
  public void configure() throws Exception {
    // The children class implement this method
  }

}