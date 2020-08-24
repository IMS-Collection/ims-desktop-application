package com.spring.mongodb.ims.imsdesktopapplication.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.AddressDTO;


@Component
public interface AddressService {
	List<AddressDTO> getAddresses(String userId);
	AddressDTO getAddress(String addressId);

}
