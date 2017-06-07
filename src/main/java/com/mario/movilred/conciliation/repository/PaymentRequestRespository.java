package com.mario.movilred.conciliation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.movilred.conciliation.model.PaymentRequest;

public interface PaymentRequestRespository extends CrudRepository<PaymentRequest, String> {


  @Query("select pr from PaymentRequest pr where pr.creationDate>=:startDate and pr.creationDate<=:endDate")
  List<PaymentRequest> findByDatesReturnStream(@Param("startDate") Date startDate,
      @Param("endDate") Date endDate);


}
