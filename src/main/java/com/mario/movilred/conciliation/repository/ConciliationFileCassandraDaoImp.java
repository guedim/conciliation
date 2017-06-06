package com.mario.movilred.conciliation.repository;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.model.ConciliationFile;

@Component
public class ConciliationFileCassandraDaoImp implements ConciliationFileDao {

  /** The logger class **/
  private final static Logger LOGGER =
      Logger.getLogger(ConciliationFileCassandraDaoImp.class.getName());

  @Autowired(required = false)
  private CassandraOperations cassandraOperations;

  @Override
  public UUID save(String fileContent) {
    ConciliationFile conciliationFile = new ConciliationFile(fileContent);
    try {
      if (cassandraOperations != null) {
        cassandraOperations.insert(conciliationFile);
      } else {
        logFileContent(fileContent);
      }
      return conciliationFile.getId();
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "could not save the file with content:" + fileContent, e);
      return null;
    }
  }

  
  private void logFileContent(String fileContect) {
    LOGGER.warning("The cassandra datasource is not enabled. You have to change install Cassandra change the configuration options in the application properties file");
    LOGGER.warning("File contecto to process: " + fileContect);
  }
}
