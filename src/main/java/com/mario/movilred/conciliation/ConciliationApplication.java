package com.mario.movilred.conciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({
  "classpath:application.properties",
  "classpath:ftp.properties" })
//@EnableAutoConfiguration(exclude={CassandraDataAutoConfiguration.class})
public class ConciliationApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConciliationApplication.class, args);
  }
}
