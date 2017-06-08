/**
 * 
 */
package com.mario.movilred.conciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties", "classpath:ftp.properties"})
@EnableAutoConfiguration(exclude = {CassandraDataAutoConfiguration.class
})
public class ConciliationApplication {

  /**
   * 
   * @param args
   */
  public static void main(final String[] args) {
    SpringApplication.run(ConciliationApplication.class, args);
  }
}
