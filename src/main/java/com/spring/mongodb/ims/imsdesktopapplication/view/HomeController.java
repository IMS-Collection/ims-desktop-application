package com.spring.mongodb.ims.imsdesktopapplication.view;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@Component
public class HomeController extends BaseController {
	
	@FXML
	private Label homePage;
	
	public void homePageClicked() {
		System.out.println("Home page clicked");
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
