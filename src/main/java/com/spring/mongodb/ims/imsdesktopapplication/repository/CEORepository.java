package com.spring.mongodb.ims.imsdesktopapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.mongodb.ims.imsdesktopapplication.model.CEO;

@Repository
public interface CEORepository extends MongoRepository<CEO, String>{

}
