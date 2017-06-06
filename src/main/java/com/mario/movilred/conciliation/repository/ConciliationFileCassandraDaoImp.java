package com.mario.movilred.conciliation.repository;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.model.ConciliationFile;

@ConditionalOnProperty(name="cassandra.is.enabled", havingValue="true")
@Component
public class ConciliationFileCassandraDaoImp implements ConciliationFileDao {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ConciliationFileCassandraDaoImp.class.getName());

  //@Autowired
  //private ConciliationFileRepository repository;
  
  @Autowired(required=false)
  private CassandraOperations cassandraOperations;

  @Override
  public UUID save(String fileContent) {
    ConciliationFile conciliationFile = new ConciliationFile(fileContent);
    try {
      //repository.save(conciliationFile);
      cassandraOperations.insert(conciliationFile);
      
      return conciliationFile.getId();
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "could not save the file with content:" + fileContent, e);
      return null;
    }
  }
}