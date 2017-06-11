/**
 * 
 */
package com.mario.movilred.conciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.yaml"})
@EnableAutoConfiguration(exclude = {CassandraDataAutoConfiguration.class,
    DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
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