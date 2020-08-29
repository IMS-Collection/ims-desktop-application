package com.spring.mongodb.ims.imsdesktopapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mongodb.ims.imsdesktopapplication.ImsDesktopApplication;
import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;
import com.spring.mongodb.ims.imsdesktopapplication.service.ImsService;

@Service
public class ImsServiceImpl implements ImsService {
	
	@Autowired
	EmployeeServiceImpl employeeService;

	@Override
	public void login(String username, String password) throws InvalidInputException {
		
		Employee currentEmployee = null;
		if (ImsDesktopApplication.getCurrentEmployee() != null) {
			throw new InvalidInputException("An employee is currently loggedin!");
		}
		
		if (username.contains("@")) {
			currentEmployee = employeeService.getEmployeeByEmail(username, password);
		} else {
			currentEmployee = employeeService.getEmployeeByUserName(username, password);
		}
		ImsDesktopApplication.setCurrentEmployee(currentEmployee);
	}

	@Override
	public void logout() {
		ImsDesktopApplication.setCurrentEmployee(null);
		
	}

}
