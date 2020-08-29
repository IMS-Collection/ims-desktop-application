package com.spring.mongodb.ims.imsdesktopapplication.service;

import org.springframework.stereotype.Service;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;

@Service
public interface ImsService {
	
	void login(String username, String password) throws InvalidInputException;
	void logout();

}
