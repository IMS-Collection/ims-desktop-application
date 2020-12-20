package com.spring.mongodb.ims.imsdesktopapplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

	private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer = new ApplicationContextInitializer<GenericApplicationContext>() {
            @Override
            public void initialize(GenericApplicationContext genericApplicationContext) {
                genericApplicationContext.registerBean(Application.class, () -> JavaFXApplication.this);
                genericApplicationContext.registerBean(Parameters.class, () -> getParameters());
                genericApplicationContext.registerBean(HostServices.class, () -> getHostServices());
            }
        };

        this.context = new SpringApplicationBuilder().sources(ImsDesktopApplication.class)
                .initializers(initializer)
                .build().run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.context.publishEvent(new StageReadyEvent(primaryStage));
        
        //window = stageReadyEvent.getStage();
//        Group root = new Group();
//        Text text =  new Text(50, 100, "Here's a Text String");
//        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
//        text.setFill(Color.LIGHTSALMON);
//        text.setStroke(Color.DARKBLUE);
//        text.setStrokeWidth(2);
//        text.setUnderline(true);
//
//        root.getChildren().add(text);
//        Scene scene = new Scene(root, 600, 600);
//        primaryStage.setScene(scene);
//        //primaryStage.setTitle(this.applicationTitle);
//        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

    public class StageReadyEvent extends ApplicationEvent {

        /**
		 * 
		 */
		private static final long serialVersionUID = -8708239203808551397L;

		public Stage getStage() {
            return Stage.class.cast(getSource());
        }

        public StageReadyEvent(Object source) {
            super(source);
        }
    }

}
