package com.spring.mongodb.ims.imsdesktopapplication.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.mongodb.ims.imsdesktopapplication.model.Address;
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;

@Repository
public interface AddressRepository extends MongoRepository<Address, String>{
	
	List<Address> findAllByEmployee(Employee employeeDetails);
	Address findByAddressId(String addressId);

}
