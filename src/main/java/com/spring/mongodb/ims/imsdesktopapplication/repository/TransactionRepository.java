package com.spring.mongodb.ims.imsdesktopapplication.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.mongodb.ims.imsdesktopapplication.model.Customer;
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;
import com.spring.mongodb.ims.imsdesktopapplication.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
	
	List<Transaction> findAllByBuyer(Customer buyer);
	Transaction findByTransactionId(String transactionId);
	List<Transaction> findAllBySeller(Employee employee);

}
