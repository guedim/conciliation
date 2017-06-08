package com.mario.movilred.conciliation.repository;

import java.util.Properties;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

@ConditionalOnProperty(name = "postgres.is.enabled", havingValue = "true")
@Configuration
@EnableJpaRepositories(basePackages = "com.mario.movilred.conciliation",
    entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class PostgresConfig {

  @Autowired
  private Environment environment;


  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public HikariDataSource dataSource() {
    DataSourceProperties dataSourceProperties = dataSourceProperties();
    HikariDataSource dataSource =
        (HikariDataSource) DataSourceBuilder.create(dataSourceProperties.getClassLoader())
            .driverClassName(dataSourceProperties.getDriverClassName())
            .url(dataSourceProperties.getUrl())
            .username(dataSourceProperties.getUsername())
            .password(dataSourceProperties.getPassword())
            .type(HikariDataSource.class).build();
    dataSource.setMaximumPoolSize(Integer.valueOf(environment.getRequiredProperty("spring.datasource.maximumPoolSize")));
    dataSource.setPoolName(environment.getRequiredProperty("spring.datasource.poolName"));
    dataSource.setIdleTimeout(Long.valueOf(environment.getRequiredProperty("spring.datasource.idleTimeout")));
    dataSource.setConnectionTimeout(Long.valueOf(environment.getRequiredProperty("spring.datasource.connectionTimeout")));
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
    LocalContainerEntityManagerFactoryBean factoryBean =
        new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(dataSource());
    factoryBean.setPackagesToScan(new String[] {"com.mario.movilred.conciliation.model"});
    factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
    factoryBean.setJpaProperties(jpaProperties());
    return factoryBean;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    return hibernateJpaVendorAdapter;
  }

  private Properties jpaProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("spring.jpa.format_sql"));
    properties.put("hibernate.generate-ddl", environment.getRequiredProperty("spring.jpa.generate-ddl"));
    properties.put("hibernate.default_schema", environment.getRequiredProperty("spring.jpa.default_schema"));
    return properties;
  }
}
