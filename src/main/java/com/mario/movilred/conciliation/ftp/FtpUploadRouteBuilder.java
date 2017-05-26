package com.mario.movilred.conciliation.ftp;

import org.springframework.beans.factory.annotation.Value;

public class FtpUploadRouteBuilder extends AbstractFtpRouteBuilder {

  @Value("${uploadfolder:upload}")
  private String uploadFolder;

  @Value("${prefixUploadFileName:movilred}")
  private String prefixUploadFileName;

 
}
