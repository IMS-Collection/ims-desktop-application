package com.spring.mongodb.ims.imsdesktopapplication.view;

import com.spring.mongodb.ims.imsdesktopapplication.model.ProductKind;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.ProductDTO;

public class ProductController extends BaseController {
	
	public void testMethod() {
		ProductDTO prdt = new ProductDTO();
		prdt.setProductKind(ProductKind.BLOCK);
	}

}
