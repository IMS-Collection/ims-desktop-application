package com.spring.mongodb.ims.imsdesktopapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.mongodb.ims.imsdesktopapplication.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	Product findByName(String name);
	//Product findByProductTransaction(ProductTransaction pTransaction);
	//long 

}
