package com.spring.mongodb.ims.imsdesktopapplication.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.mongodb.ims.imsdesktopapplication.model.Product;
import com.spring.mongodb.ims.imsdesktopapplication.model.ProductTransaction;
import com.spring.mongodb.ims.imsdesktopapplication.model.Transaction;

@Repository
public interface ProductTransactionRepository extends MongoRepository<ProductTransaction, String> {
	List<ProductTransaction> findAllByTransaction(Transaction transaction);
	List<ProductTransaction> findAllByProduct(Product product);
	ProductTransaction findBypTransactionId(String pTransactionId);
}
