package com.spring.mongodb.ims.imsdesktopapplication.view;

import org.springframework.stereotype.Component;

@Component
public class AccountController extends BaseController {

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
