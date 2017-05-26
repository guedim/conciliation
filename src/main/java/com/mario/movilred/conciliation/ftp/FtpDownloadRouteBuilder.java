package com.mario.movilred.conciliation.ftp;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.exception.ConciliationFileException;
import com.mario.movilred.conciliation.model.ConciliationFileDetail;
import com.mario.movilred.conciliation.processor.ConciliateFileProcessor;
import com.mario.movilred.conciliation.processor.ConvertFileProcessor;
import com.mario.movilred.conciliation.processor.ExceptionHandlerProcessor;
import com.mario.movilred.conciliation.processor.SaveFileProcessor;

@Component
public class FtpDownloadRouteBuilder extends RouteBuilder {
  
  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(FtpDownloadRouteBuilder.class.getName());
  

  @Value("${protocol}")
  private String protocol;

  @Value("${server}")
  private String server;

  @Value("${port}")
  private String port;

  @Value("${donwloadfolder:download}")
  private String downloadFolder;

  @Value("${uploadfolder:upload}")
  private String uploadFolder;

  @Value("${user}")
  private String user;

  @Value("${password}")
  private String password;

  @Value("${prefixUploadFileName:movilred}")
  private String prefixUploadFileName;

  @Value("${prefixDownloadFileName:movilred}")
  private String prefixDownloadFileName;

  @Value("${stepwise:false}")
  private String stepwise;

  @Value("${useList:false}")
  private String useList;
  
  @Value("${cron.movilred.downloadfile}")
  private String cronDownloadFile;

  /** The ftp url and parameters */
  private String ftpUrl;
  
  /** The cron expression */
  private String cronExpression;
  
  @Autowired
  private ConvertFileProcessor convertFileProcessor;
  
  @Autowired
  private SaveFileProcessor saveFileProcessor;
  
  @Autowired
  private ConciliateFileProcessor conciliateFileProcessor;
  
  @Autowired
  private ExceptionHandlerProcessor exceptionHandlerProcessor;

  @PostConstruct
  public void postConstruct() {
    StringBuilder ftpBuilder = new StringBuilder(protocol + "://")
        .append(server + ":" + port + "/" + uploadFolder).append("?username=" + user)
        .append("&password=" + password).append("&fileName=" + prefixDownloadFileName + ".txt")
        .append("&stepwise=" + stepwise).append("&stepwise=" + useList);
    ftpUrl = ftpBuilder.toString();
    LOGGER.info("In postConstruct() - The ftp download url:" + ftpUrl);
    
    cronExpression = "&scheduler=quartz2&scheduler.cron="+cronDownloadFile;
    LOGGER.info("In postConstruct() - The download cron expression: " + cronExpression);
  }


  @Override
  public void configure() throws Exception {

    // lets shutdown faster in case of in-flight messages stack up
    getContext().getShutdownStrategy().setTimeout(10);

    // to handle any IOException being thrown
    onException(ConciliationFileException.class)
        .handled(true)
        .log("Exception occurred due: ${exception.message}")
        .transform().simple("Error ${exception.message}")
        .process(exceptionHandlerProcessor);
    
    from(ftpUrl + cronExpression)
      .log("Start processing file ${file:name}.")
      .process(saveFileProcessor)
      .unmarshal().bindy(BindyType.Fixed, ConciliationFileDetail.class)
      .process(convertFileProcessor)
      .process(conciliateFileProcessor)
      .log("Downloaded file ${file:name} complete.");
  }
}
