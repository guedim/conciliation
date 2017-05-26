package com.mario.movilred.conciliation.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord(crlf = "WINDOWS", length = 11)
public class ConciliationFileHeader implements Serializable {

  private static final long serialVersionUID = 1L;

  @DataField(pos = 1, length = 1)
  private int recordType = 1;
   
  @DataField(pos = 2, length = 10, pattern = "dd-MM-yyyy")
  private Date recordDate;

  @Override
  public String toString() {
    return "ConciliationFileHeader [recordType=" + recordType + ", recordDate=" + recordDate + "]";
  }
  
  
  
}
