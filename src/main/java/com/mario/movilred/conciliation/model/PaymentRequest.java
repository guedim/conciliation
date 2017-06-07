package com.mario.movilred.conciliation.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@Entity
@FixedLengthRecord(crlf = "WINDOWS", skipHeader = true, skipFooter = true, length = 54)
public class PaymentRequest {

  @Id
  @DataField(pos = 1, length = 40, align="L")
  private String id;
  
  @Column(name = "creation_date", nullable = false)
  @DataField(pos = 41, length = 23, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
  private Date creationDate;
  
  @Column(name = "reference", length = 64, nullable = false)
  @DataField(pos = 80, length = 64)
  private String reference;
  
  @Column(name = "value", precision = 10, scale = 2, nullable = false)
  @DataField(pos = 160, length = 10, precision=2)
  private BigDecimal value;

  public String getId() {
    return id;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public String getReference() {
    return reference;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "PaymentRequest [id=" + id + ", creationDate=" + creationDate + ", reference="
        + reference + ", value=" + value + "]";
  }
}
