package com.mario.movilred.conciliation.ftp;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FtpUploadRouteBuilder extends AbstractFtpRouteBuilder {
  
  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(FtpUploadRouteBuilder.class.getName());

  @Value("${uploadfolder:upload}")
  private String uploadFolder;

  @Value("${prefixUploadFileName:movilred}")
  private String prefixUploadFileName;
  
  @Value("${cron.movilred.uploadfile}")
  private String cronUploadFile;
  
  /** The ftp url and parameters */
  private String ftpUrl;
  
  /** The cron expression */
  private String cronExpression;
  
  @PostConstruct
  public void postConstruct() {
    StringBuilder ftpBuilder = new StringBuilder(protocol + "://")
        .append(server + ":" + port + "/" + uploadFolder).append("?username=" + user)
        .append("&password=" + password).append("&fileName=" + prefixUploadFileName + ".txt")
        .append("&stepwise=" + stepwise).append("&stepwise=" + useList);
    ftpUrl = ftpBuilder.toString() + fileExceptionConfiguration;
    LOGGER.info("In postConstruct() - The ftp upload url:" + ftpUrl);
    
    cronExpression = cronConfiguration + cronUploadFile;
    LOGGER.info("In postConstruct() - The upload cron expression: " + cronExpression);
  }


  @Override
  public void configure() throws Exception {

    // lets shutdown faster in case of in-flight messages stack up
    getContext().getShutdownStrategy().setTimeout(10);

    // to handle any IOException being thrown
    onException(Exception.class)
        .handled(true)
        .log("Exception occurred due: ${exception.message}")
        .transform().simple("Error ${exception.message}")
        .process(exceptionHandlerProcessor); 
    
    
    
    
    from("quartz2://myGroup/myTimerName?cron=" + cronUploadFile)//.to("activemq:Totally.Rocks");
      .log("corrio el cron :)");
    
    
    //from("file://D:\\test.txt").to(ftpUrl + cronExpression).log("Uploaded file ${file:name} complete.");
  }
 
}
