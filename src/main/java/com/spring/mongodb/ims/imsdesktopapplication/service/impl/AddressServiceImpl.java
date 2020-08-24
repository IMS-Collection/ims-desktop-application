package com.spring.mongodb.ims.imsdesktopapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.mongodb.ims.imsdesktopapplication.model.Address;
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;
import com.spring.mongodb.ims.imsdesktopapplication.repository.AddressRepository;
import com.spring.mongodb.ims.imsdesktopapplication.repository.EmployeeRepository;
import com.spring.mongodb.ims.imsdesktopapplication.service.AddressService;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.AddressDTO;


@Component
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	

	@Override
	public List<AddressDTO> getAddresses(String userId) {
		// TODO Auto-generated method stub
		
		List<AddressDTO> returnValue = new ArrayList<>();
		
		Employee employee = employeeRepository.findByUserName(userId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		Iterable<Address> addresses = addressRepository.findAllByEmployee(employee);
		
		for (Address address : addresses) {
			returnValue.add(modelMapper.map(address, AddressDTO.class));
		}

		return returnValue;
		
	}

	@Override
	public AddressDTO getAddress(String addressId) {
		
		AddressDTO returnValue = null;
		
		Address address = addressRepository.findByAddressId(addressId);
		if (address != null) {
			ModelMapper modelMapper = new ModelMapper();
			
			returnValue = modelMapper.map(address, AddressDTO.class);
		}
		return returnValue;
	}

}
