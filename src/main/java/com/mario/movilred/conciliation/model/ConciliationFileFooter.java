package com.mario.movilred.conciliation.model;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord(crlf = "WINDOWS", length = 10)
public class ConciliationFileFooter implements Serializable {

  private static final long serialVersionUID = 1L;

  @DataField(pos = 1, length = 1)
  private int recordType = 9;
   
  @DataField(pos = 2, length = 9, align = "R", paddingChar = ' ')
  private int numberOfRecordsInTheFile;
  
  
  @Override
  public String toString() {
    return "ConciliationFileFooter [recordType=" + recordType + ", numberOfRecordsInTheFile="
        + numberOfRecordsInTheFile + "]";
  }
}