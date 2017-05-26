package com.mario.movilred.conciliation.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.mario.movilred.conciliation.model.ConciliationFile;

public interface ConciliationFileRepository extends CassandraRepository<ConciliationFile> {

	@Query("Select * from customer where creationDate=?0 ALLOW FILTERING")
	public ConciliationFile findByFirstName(String firstName);
}
