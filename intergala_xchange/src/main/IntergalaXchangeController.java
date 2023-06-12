package main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class IntergalaXchangeController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String path = System.getProperty("user.dir") + "/src/resources/input.txt";
		
		String defaultText = "";
		try {
			defaultText = InputInterpreter.getInputFromFile(path);
		} catch (IOException e) {
			defaultText = String.format("Unable to read default text, error occured: %s.", e.getMessage());
		}
		
		inputArea.setText(defaultText);
	}
	
	@FXML
    protected TextArea inputArea;
    
    @FXML 
    protected TextArea outputArea;
    
    @FXML
    protected void onInterpret() {
    	InputInterpreter interpreter = new InputInterpreter();
    	List<String> questions = interpreter.interpretInputFromText(inputArea.getText());
    	
    	String outputResult = interpreter.getQuestionsResult(questions);
    	outputArea.setText(outputResult);
    }
    
    @FXML
    protected void onSelectFile() {
    	
    }
}
