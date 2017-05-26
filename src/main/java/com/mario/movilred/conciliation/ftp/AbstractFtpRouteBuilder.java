package com.mario.movilred.conciliation.ftp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;

public class AbstractFtpRouteBuilder extends RouteBuilder {

  protected String fileExceptionConfiguration = "&throwExceptionOnConnectFailed=true&consumer.bridgeErrorHandler=true";
  
  protected String cronConfiguration = "&scheduler=quartz2&scheduler.cron=";
  
  @Value("${protocol}")
  protected String protocol;

  @Value("${server}")
  protected String server;

  @Value("${port}")
  protected String port;

  @Value("${user}")
  protected String user;

  @Value("${password}")
  protected String password;

  @Value("${stepwise:false}")
  protected String stepwise;

  @Value("${useList:false}")
  protected String useList;

  @Override
  public void configure() throws Exception {
    // The children class implement this method
  }

}
