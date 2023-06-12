package main;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IntergalaXchange extends Application {

    @Override
    public void start(Stage stage) {
    	try {
    		URL pathToResource = getClass().getResource("../view/IntergalaXchange.fxml");
    		
			Parent parent = FXMLLoader.load(pathToResource);
			stage.setTitle("IntergalaXchange");
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch();
    }
}