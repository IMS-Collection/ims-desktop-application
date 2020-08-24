package com.spring.mongodb.ims.imsdesktopapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.service.ImsService;

@Service
public class ImsServiceImpl implements ImsService {
	
	@Autowired
	EmployeeServiceImpl employeeService;

	@Override
	public void login(String username, String password) throws InvalidInputException {
		
		if (username.contains("@")) {
			employeeService.getEmployeeByEmail(username, password);
		} else {
			employeeService.getEmployeeByUserName(username, password);
		}
		
		
		
	}

	@Override
	public void logout(String userName) {
		// TODO Auto-generated method stub
		
	}

}
