package com.mario.movilred.conciliation.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

@Table("file")
public class ConciliationFile implements Serializable {

  private static final long serialVersionUID = 1L;

  @PrimaryKey("id")
  private UUID id;

  @Column("content")
  private String content;

  public ConciliationFile(String content) {
    super();
    this.id = UUIDs.timeBased();
    this.content = content;
  }

  public UUID getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "ConciliationFileCassandra [id=" + id + ", content=" + content + "]";
  }
}
