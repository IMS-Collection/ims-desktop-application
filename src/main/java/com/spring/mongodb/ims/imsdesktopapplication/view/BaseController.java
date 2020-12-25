package com.spring.mongodb.ims.imsdesktopapplication.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.mongodb.ims.imsdesktopapplication.StageListener;

import javafx.stage.Stage;

@Component
public abstract class BaseController {
	
	@Autowired
	StageListener stageListener;
	
	protected String error = "";
	
	public abstract void homePage();
	public abstract void productPage();
	public abstract void accountPage();
	
	public void showHomePage() {
		Stage window = stageListener.getWindow();
		window.setScene(stageListener.getHomeScene());
		window.setTitle("Home Page");
		window.show();
	}
	
	public void showProductPage() {
		Stage window = stageListener.getWindow();
		window.setScene(stageListener.getProductScene());
		window.setTitle("Home Page");
		window.show();
	}
	
	public void showAccountPage() {
		Stage window = stageListener.getWindow();
		window.setScene(stageListener.getAccountScene());
		window.setTitle("Home Page");
		window.show();
	}

}
