package com.mario.movilred.conciliation.ftp;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.camel.model.dataformat.BindyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.mario.movilred.conciliation.model.ConciliationFileDetail;
import com.mario.movilred.conciliation.processor.ConciliateFileProcessor;
import com.mario.movilred.conciliation.processor.ConvertFileProcessor;

//@Component
public class FtpDownloadRouteBuilder extends AbstractFtpRouteBuilder {
  
  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(FtpDownloadRouteBuilder.class.getName());
    
  @Value("${movilred.ftp.donwloadfolder:download}")
  private String downloadFolder;

  @Value("${movilred.ftp.prefixDownloadFileName:movilred}")
  private String prefixDownloadFileName;

  @Value("${cron.movilred.downloadfile}")
  private String cronDownloadFile;

  /** The ftp url and parameters */
  private String ftpUrl;
  
  /** The cron expression */
  private String cronExpression;
  
  @Autowired
  private ConvertFileProcessor convertFileProcessor;
  
  @Autowired
  private ConciliateFileProcessor conciliateFileProcessor;
  
  @PostConstruct
  public void postConstruct() {
    StringBuilder ftpBuilder = new StringBuilder(protocol + "://")
        .append(server + ":" + port + "/" + downloadFolder).append("?username=" + user)
        .append("&password=" + password).append("&fileName=" + prefixDownloadFileName + ".txt")
        .append("&stepwise=" + stepwise).append("&stepwise=" + useList);
    ftpUrl = ftpBuilder.toString() + fileExceptionConfiguration;
    LOGGER.info("In postConstruct() - The ftp download url:" + ftpUrl);
    
    cronExpression = cronConfiguration + cronDownloadFile;
    LOGGER.info("In postConstruct() - The download cron expression: " + cronExpression);
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
    
    from(ftpUrl + cronExpression)
      .log("Start processing file ${file:name}.")
      .process(saveFileProcessor)
      .unmarshal().bindy(BindyType.Fixed, ConciliationFileDetail.class)
      .process(convertFileProcessor)
      .process(conciliateFileProcessor)
      .log("Downloaded file ${file:name} complete.");
  }
}
