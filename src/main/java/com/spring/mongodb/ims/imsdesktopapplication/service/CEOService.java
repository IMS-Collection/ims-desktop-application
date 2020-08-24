package com.spring.mongodb.ims.imsdesktopapplication.service;

import org.springframework.stereotype.Service;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.CEODTO;

@Service
public interface CEOService {
	
	CEODTO createCEO(CEODTO CEODTO) throws InvalidInputException;
	
	CEODTO updateCEO(String id, CEODTO CEODto) throws InvalidInputException;
	
	void deleteCEO(String CEOId);

}
