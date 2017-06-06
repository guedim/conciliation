package com.mario.movilred.conciliation.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.mario.movilred.conciliation.model.ConciliationFile;

@NoRepositoryBean
public interface ConciliationFileRepository extends CrudRepository<ConciliationFile, UUID> {

	@Query("Select * from customer where creationDate=?0 ALLOW FILTERING")
	public ConciliationFile findByFirstName(String firstName);
}
