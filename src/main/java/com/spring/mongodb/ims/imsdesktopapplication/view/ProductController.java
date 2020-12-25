package com.spring.mongodb.ims.imsdesktopapplication.view;

import org.springframework.stereotype.Component;

import com.spring.mongodb.ims.imsdesktopapplication.model.ProductKind;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.ProductDTO;

@Component
public class ProductController extends BaseController {
	
	public void testMethod() {
		ProductDTO prdt = new ProductDTO();
		prdt.setProductKind(ProductKind.BLOCK);
	}
	
	@Override
	public void homePage() {
		showHomePage();
		
	}

	@Override
	public void productPage() {
		showProductPage();
		
	}

	@Override
	public void accountPage() {
		showAccountPage();
		
	}

}
