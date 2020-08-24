package com.spring.mongodb.ims.imsdesktopapplication.shared;

import java.util.UUID;

public class GenerateId {
	
	public static String generateId() {
		
		return UUID.randomUUID().toString();
	}

}
