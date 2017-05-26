package com.mario.movilred.conciliation.repository;

import java.util.UUID;

public interface ConciliationFileDao {

  /**
   * 
   * @param fileContent
   * @return
   */
  public UUID save(String fileContent);
  
}
