package main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class IntergalaXchangeController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String path = System.getProperty("user.dir") + "/src/resources/input.txt";
		setInputTextForFile(new File(path));
	}

	@FXML
	private TextArea inputArea;

	@FXML
	private TextArea outputArea;
	
	@FXML
	private TextArea feedbackArea;

	@FXML
	private void onInterpret() {
		InputInterpreter interpreter = new InputInterpreter();
		List<String> questions = interpreter.interpretInputFromText(inputArea.getText());

		String outputResult = interpreter.getQuestionsResult(questions);
		outputArea.setText(outputResult);
		
		String feedback = interpreter.getConversionFeedback();
		feedbackArea.setText(feedback);
	}

	@FXML
	private void onSelectFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File inputFile = fileChooser.showOpenDialog(inputArea.getScene().getWindow());

		if (inputFile != null) {
			setInputTextForFile(inputFile);
		}
	}

	private void setInputTextForFile(File file) {
		String inputText = "";
		try {
			inputText = InputInterpreter.getInputFromFile(file);
		} catch (IOException e) {
			inputText = String.format("Unable to read default text, error occured: %s.", e.getMessage());
		}

		inputArea.setText(inputText);
	}
}
