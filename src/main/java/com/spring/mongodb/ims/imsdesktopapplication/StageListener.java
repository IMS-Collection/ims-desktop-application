package com.spring.mongodb.ims.imsdesktopapplication;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.spring.mongodb.ims.imsdesktopapplication.JavaFXApplication.StageReadyEvent;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<JavaFXApplication.StageReadyEvent> {

    private final String applicationTitle;
    private final Resource loginFXML;
    private final Resource homePageFXML;
    private final Resource accountPageFXML;
    private final Resource productPageFXML;
    private final Resource transactionPageFXML;
    private final Resource registerPageFXML;
    private final ApplicationContext applicationContext;

    private Stage window;
    private Scene loginScene;
    private Scene registerScene; 
    private Scene homeScene; 
    private Scene productScene; 
    private Scene accountScene; 
    private Scene transactionScene;

    //private Label;

    public StageListener(
    		@Value("${spring.application.ui.title}") String applicationTitle,
    		@Value("classpath:/loginPage.fxml") Resource loginFXML, 
            @Value("classpath:/homePage.fxml") Resource homePageFXML, 
            @Value("classpath:/accountPage.fxml") Resource accountPageFXML, 
            @Value("classpath:/productPage.fxml") Resource productPageFXML, 
            @Value("classpath:/transactionPage.fxml") Resource transactionPageFXML, 
            @Value("classpath:/registerPage.fxml") Resource registerPageFXML, 
            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.loginFXML = loginFXML;
        this.homePageFXML = homePageFXML;
		this.accountPageFXML = accountPageFXML;
		this.productPageFXML = productPageFXML;
		this.transactionPageFXML = transactionPageFXML;
		this.registerPageFXML = registerPageFXML;
		this.applicationContext = applicationContext;
		
    }
    
    /**
     * Initializes the scene of the login page
     * @param fxml - the login fxml
     */
    private void setLoginScene(Resource fxml) {
    	Parent root = getRoot(fxml);
		this.loginScene = new Scene(root, 800, 600);
    }
    
    /**
     * Initializes the scene of the register page
     * @param fxml - the register fxml
     */
    private void setRegisterScene(Resource fxml) {
    	Parent root = getRoot(fxml);
		this.registerScene = new Scene(root, 800, 600);
    }
    
    /**
     * Initializes the scene of the home page
     * @param fxml - the home fxml
     */
    private void setHomeScene(Resource fxml) {
    	Parent root = getRoot(fxml);
		this.homeScene = new Scene(root, 800, 600);
    }
    
    /**
     * Initializes the scene of the product page
     * @param fxml - the product fxml
     */
    private void setProductScene(Resource fxml) {
    	Parent root = getRoot(fxml);
		this.productScene = new Scene(root, 800, 600);
    }
    
    /**
     * Initializes the scene of the account page
     * @param fxml - the account fxml
     */
    private void setAccountScene(Resource fxml) {
    	Parent root = getRoot(fxml);
		this.accountScene = new Scene(root, 800, 600);
    }
    
    /**
     * Initializes the scene of the transaction page
     * @param fxml - the transaction fxml
     */
    private void setTransactionScene(Resource fxml) {
    	Parent root = getRoot(fxml);
		this.transactionScene = new Scene(root, 800, 600);
    }
    
    /**
     * Gets the root of the fxml resource
     * @param fxml
     * @return
     */
    private Parent getRoot(Resource fxml) {
    	Parent root;
    	try {
    		URL url = fxml.getURL();
    		FXMLLoader fxmlLoader = new FXMLLoader(url);
    		fxmlLoader.setControllerFactory(applicationContext::getBean);
    		root = fxmlLoader.load();
    	} catch (IOException e) {
            throw new RuntimeException(e);
        }
    	
    	return root;
    }

    @Override
    public void onApplicationEvent(JavaFXApplication.StageReadyEvent stageReadyEvent) {
    	
    	setLoginScene(loginFXML);
    	setRegisterScene(registerPageFXML);
    	setHomeScene(homePageFXML);
    	setProductScene(productPageFXML);
    	setAccountScene(accountPageFXML);
    	setTransactionScene(transactionPageFXML);
    	
    	 window = stageReadyEvent.getStage();
    	 Scene scene = getLoginScene();
    	 window.setScene(scene);
         window.setTitle(this.applicationTitle);
         window.show();

    }

//    public ObservableList<Product> getProduct() {
//        ObservableList<Product> products = FXCollections.observableArrayList();
//
//        products.add(new Product("Laptop", 859, 20));
//        products.add(new Product("Bouncy", 145, 78));
//        products.add(new Product("Toilet", 435, 56));
//        products.add(new Product("The Notebook DVD", 567, 456));
//        products.add(new Product("Corn", 2345, 5));
//
//        return products;
//    }

    private void handleOption(CheckBox box1, CheckBox box2) {
        String message = "User oder: \n";
        if (box1.isSelected()) {
            message += "Bacon selected \n";
        }
        if (box2.isSelected()) {
            message += "Tuna selected";
        }

        System.out.println(message);
    }

    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

	public Stage getWindow() {
		return window;
	}

	public Scene getLoginScene() {
		return loginScene;
	}

	public Scene getRegisterScene() {
		return registerScene;
	}

	public Scene getHomeScene() {
		return homeScene;
	}

	public Scene getProductScene() {
		return productScene;
	}

	public Scene getAccountScene() {
		return accountScene;
	}

	public Scene getTransactionScene() {
		return transactionScene;
	}


//    private void closeProgram() {
//        boolean answer = ConfirmBox.display("Title", "Are sure you want to close the program?");
//        if (answer) {
//            window.close();
//        }
//
//    }
    
}
