package com.spring.mongodb.ims.imsdesktopapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.mongodb.ims.imsdesktopapplication.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	Customer findByUserName(String userName);

}
