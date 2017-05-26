package com.mario.movilred.conciliation.repository;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mario.movilred.conciliation.model.ConciliationFile;

@Service
public class ConciliationFileDaoImp implements ConciliationFileDao {

  /** The logger class **/
  private final static Logger LOGGER = Logger.getLogger(ConciliationFileDaoImp.class.getName());


  @Autowired
  private ConciliationFileRepository repository;


  @Override
  public UUID save(String fileContent) {
    ConciliationFile conciliationFile = new ConciliationFile(fileContent);
    try {
      repository.save(conciliationFile);
      return conciliationFile.getId();
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "could not save the file with content:" + fileContent, e);
      return null;
    }
  }
}