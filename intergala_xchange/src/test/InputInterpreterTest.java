package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.InputInterpreter;

public class InputInterpreterTest {

	@Test
	public void test_InputTxt_expectSuccess() {
		String path = System.getProperty("user.dir") + "/src/resources/input.txt";

		InputInterpreter interpreter = new InputInterpreter();
		try {
			List<String> questions = interpreter.interpretInputFromFile(path);
			assertFalse(questions.isEmpty(), "Questions should not be empty.");
		} catch (IOException e) {
			fail("No exception should be thrown.");
		}
	}

	@Test
	public void test_InputATxt_expectFileNotFoundException() {
		String path = System.getProperty("user.dir") + "/src/resources/inputA.txt";

		InputInterpreter interpreter = new InputInterpreter();
		try {
			List<String> questions = interpreter.interpretInputFromFile(path);
			assertTrue(questions.isEmpty(), "No questions should be read, since fileNotFound is expected.");
		} catch (FileNotFoundException fnfEx) {
			// valid
		} catch (IOException ex) {
			fail("FileNotFound exeption should be thrown.");
		}
	}
	
	@Test
	public void test_InputNull_expectFileNotFoundException() {

		InputInterpreter interpreter = new InputInterpreter();
		try {
			List<String> questions = interpreter.interpretInputFromFile(null);
			assertTrue(questions.isEmpty(), "No questions should be read, since fileNotFound is expected.");
		} catch (FileNotFoundException fnfEx) {
			// valid
		} catch (IOException ex) {
			fail("FileNotFound exeption should be thrown.");
		}
	}
	
	@Test
	public void test_output_expectValidResult() {
		String invalidAnswer = "I have no idea what you are talking about.";
		String path = System.getProperty("user.dir") + "/src/resources/input.txt";

		InputInterpreter interpreter = new InputInterpreter();
		List<String> questions = new ArrayList<>();
		
		try {
			questions = interpreter.interpretInputFromFile(path);
			assertFalse(questions.isEmpty(), "Questions should not be empty.");
		} catch (IOException e) {
			fail("No exception should be thrown.");
		}
		
		String result = interpreter.getQuestionsResult(questions);
		assertNotNull(result, "Result should not be null.");
		
		String[] lines = result.split("\n");
		
		assertTrue(lines.length == 5, "Result should have 5 lines.");
		assertTrue(lines[0].contains("42"), "Result should contain '42'.");
		assertTrue(lines[1].contains("68"), "Result should contain '42'.");
		assertTrue(lines[2].contains("57800"), "Result should contain '57800'.");
		assertTrue(lines[3].contains("782"), "Result should contain '782'.");
		assertTrue(lines[4].equals(invalidAnswer), String.format("Result should be '%s'.", invalidAnswer));
	}
	
	@Test
	public void test_output_expectEmptyResult() {
		String noQuestions = "No questions provided.";
		
		InputInterpreter interpreter = new InputInterpreter();
		String result = interpreter.getQuestionsResult(new ArrayList<>());
		
		assertTrue(result.equals(noQuestions), String.format("Result should be '%s'.", noQuestions));
		
		result = interpreter.getQuestionsResult(null);
		assertTrue(result.equals(noQuestions), String.format("Result should be '%s'.", noQuestions));
	}
}
