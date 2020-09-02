package com.spring.mongodb.ims.imsdesktopapplication.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.CustomerDTO;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.TransactionDTO;

@Service
public interface CustomerService {
	
	List<TransactionDTO> getCustomerTransactions(String userName);
	
	void createCustomer(CustomerDTO customerDTO, String employeeId) throws InvalidInputException;
	
	void updateCustomer(CustomerDTO customerDTO, String employeeId, String userName) throws InvalidInputException;
	
	void deleteCustomer(String userName, String employeeId);
	
	List<CustomerDTO> getCustomers(int page, int limit);
	
	double getCustomerDebt(String userName, String employeeId) throws InvalidInputException;
	
	double getCustomerTransactionDebt(String customerId, Date date) throws InvalidInputException;
	
	List<CustomerDTO> getCustomers();
	
	CustomerDTO customerLogin(String userName, String employeeId) throws InvalidInputException;
	
	void customerLogout(String userName);
	
	CustomerDTO getCustomerTransactions(String userName, String employeeId) throws InvalidInputException;

}
