package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntergalaXchange_Main {
	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "/src/resources/input.txt";
		InputInterpreter interpreter = new InputInterpreter();
		List<String> questions = new ArrayList<>();
		
		try {
			questions = interpreter.interpretInputFromFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String result = interpreter.getQuestionsResult(questions);
		System.out.println(result);
	}
}
