package com.mario.movilred.conciliation.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord(crlf = "WINDOWS",  skipHeader = false, skipFooter = false,
    header = ConciliationFileHeader.class, footer = ConciliationFileFooter.class, length = 54)
public class ConciliationFileDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  @DataField(pos = 1, length = 2)
  private int orderNr;

  @DataField(pos = 3, length = 2)
  private String clientNr;

  @DataField(pos = 5, length = 7)
  private String firstName;

  @DataField(pos = 12, length = 1, align = "L")
  private String lastName;

  @DataField(pos = 13, length = 4)
  private String instrumentCode;

  @DataField(pos = 17, length = 10)
  private String instrumentNumber;

  @DataField(pos = 27, length = 3)
  private String orderType;

  @DataField(pos = 30, length = 5)
  private String instrumentType;

  @DataField(pos = 35, precision = 2, length = 7)
  private BigDecimal amount;

  @DataField(pos = 42, length = 3)
  private String currency;

  @DataField(pos = 45, length = 10, pattern = "dd-MM-yyyy")
  private Date orderDate;


  @Override
  public String toString() {
    return "ConciliationFileDetail [ orderNr="
        + orderNr + ", clientNr=" + clientNr + ", firstName=" + firstName + ", lastName=" + lastName
        + ", instrumentCode=" + instrumentCode + ", instrumentNumber=" + instrumentNumber
        + ", orderType=" + orderType + ", instrumentType=" + instrumentType + ", amount=" + amount
        + ", currency=" + currency + ", orderDate=" + orderDate + "]";
  }  
}