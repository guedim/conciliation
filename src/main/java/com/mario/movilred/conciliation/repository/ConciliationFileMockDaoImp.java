package com.mario.movilred.conciliation.repository;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.model.ConciliationFile;

@ConditionalOnProperty(name="cassandra.is.enabled", havingValue="false")
@Component
public class ConciliationFileMockDaoImp implements ConciliationFileDao {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ConciliationFileMockDaoImp.class.getName());

  @Override
  public UUID save(String fileContent) {
    ConciliationFile conciliationFile = new ConciliationFile(fileContent);
    LOGGER.log(Level.INFO, "MOCK DAO - retrive file: {0}", conciliationFile);
    return null;
  }
}
