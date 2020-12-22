package com.spring.mongodb.ims.imsdesktopapplication.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.service.ImsService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

@Component
public class LogInController extends BaseController {
	
	@Autowired
	ImsService imsService;
	
	@FXML
	private TextField loginUserName;
	
	@FXML
	private PasswordField loginPassword;
	
	@FXML
	private Button loginButton;
	
	public LogInController() {
		
	}
	
	public void login() {
		System.out.println("Login button clicked");
		//clear error
		error = "";
		String name = loginUserName.getText();
		String password = loginPassword.getText();
		
		boolean loggedIn = true;
		
		if (name != null && name.length() > 0) {
			if (password != null && password.length() > 0) {
				try {
					imsService.login(name, password);
					// TODO: implement
					//initializeProductForTransaction();
				} catch (InvalidInputException er) {
					loggedIn = false;
					error = er.getMessage();
				}
			} else {
				loggedIn = false;
				error = "You can't log in with empty password";
			}
		} else {
			loggedIn = false;
			error = "You can't log in with empty user name";
		}
		if(loggedIn) {
			System.out.println("Logged In!!");
			/**
			 * Launch the main page.
			 */
			try {
				error = "";
				// TODO: implement
//				layeredPane.removeAll();
//				layeredPane.add(imsPagePanel);
//				layeredPane.repaint();
//				layeredPane.revalidate();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		//update visuals
		refreshLoginData();
		
		if (!(error.length() > 0)) {
			//TOD: implement
			//refreshDashBoard();
		}
	}
	
	private void refreshLoginData() {
		//error
		if (error.length() > 0) {
			Alert a = new Alert(AlertType.ERROR, error);
			a.showAndWait();
		} else {

			//clear contents
			loginUserName.setText("");
			loginPassword.setText("");
		}
		
	}

}
