package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputInterpreter {

	private final Map<String, RomanNumber> foreignValue2RomanMap;
	private final Map<String, Double> currencyMap;

	private static final String INVALID_ANSWER = "I have no idea what you are talking about.";
	private static final String NO_QUESTIONS = "No questions provided.";

	public InputInterpreter() {
		this.foreignValue2RomanMap = new HashMap<>();
		this.currencyMap = new HashMap<>();
	}

	public static String getInputFromFile(String path) throws IOException {
		StringBuilder sb = new StringBuilder();

		if (path == null) {
			throw new FileNotFoundException("Provided path is 'null'.");
		}

		File file = new File(path);
		try (BufferedReader is = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = is.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (IOException ex) {
			throw ex;
		}

		return sb.toString();
	}

	public List<String> interpretInputFromText(String text) {
		List<String> questions = new ArrayList<>();
		
		if(text == null) {
			return questions;
		}

		String[] splitText = text.split("\n");
		for (String line : splitText) {
			if (interpretInput(line)) {
				questions.add(line);
			}
		}

		return questions;
	}

	public List<String> interpretInputFromFile(String path) throws IOException {
		String inputFromFile = getInputFromFile(path);
		return interpretInputFromText(inputFromFile);
	}

	public String getQuestionsResult(List<String> questions) {
		StringBuilder sb = new StringBuilder();

		if (questions == null || questions.isEmpty()) {
			return NO_QUESTIONS;
		}

		for (String question : questions) {
			sb.append(getQuestionResult(question));
			sb.append("\n");
		}

		return sb.toString();
	}

	private String getQuestionResult(String question) {
		String questionResult = INVALID_ANSWER;
		String questionLower = question.toLowerCase();

		if (!(questionLower.startsWith("how much is") || questionLower.startsWith("how many credits is"))) {
			return questionResult;
		}

		String answerBeginning = question.substring(question.indexOf("is") + 3, question.indexOf("?") - 1);

		String[] calcValues = answerBeginning.split(" ");
		String romanNumber = "";
		double currencyWorth = 1;

		for (int i = 0; i < calcValues.length; i++) {
			String calcValue = calcValues[i];

			if (foreignValue2RomanMap.containsKey(calcValue)) {
				romanNumber += foreignValue2RomanMap.get(calcValue).name();
			} else if ((i == calcValues.length - 1) && currencyMap.containsKey(calcValue)) {
				currencyWorth = currencyMap.get(calcValue);
			} else {
				return questionResult;
			}
		}

		int conversionResult = RomanNumberConvertor.convertRomanToArabic(romanNumber);
		if (conversionResult < 0) {
			return questionResult;
		}

		int totalResult = (int) Math.ceil(conversionResult * currencyWorth);

		String answer = answerBeginning + " is " + totalResult
				+ (questionLower.contains("credits") ? " Credits." : ".");
		return answer;
	}

	private boolean interpretInput(String inputLine) {
		String[] splitLine = inputLine.split(" ");

		if (splitLine.length == 0) {
			return false;
		}

		int lastLinePart = splitLine.length - 1;
		if ("?".equals(splitLine[lastLinePart])) {
			return true;
		}

		if (splitLine.length == 3 && splitLine[1].toLowerCase().equals("is")) {
			RomanNumber romanNumber = null;
			try {
				romanNumber = RomanNumber.valueOf(splitLine[2].toUpperCase());
			} catch (Exception ex) {
				System.out.println("Invalid roman number: " + splitLine[2]);
				return false;
			}

			foreignValue2RomanMap.put(splitLine[0], romanNumber);
			return false;
		}

		if ("credits".equals(splitLine[lastLinePart].toLowerCase())) {

			String romanNumber = "";
			double credits = 0;
			String currency = "";

			for (String word : splitLine) {
				if ("is".equals(word.toLowerCase()) || "credits".equals(word.toLowerCase())) {
					continue;
				}

				if (foreignValue2RomanMap.get(word) != null) {
					romanNumber += foreignValue2RomanMap.get(word).name();
					continue;
				}

				try {
					credits = Double.parseDouble(word);
				} catch (NumberFormatException ex) {
					currency = word;
				}
			}

			if (currency.isEmpty()) {
				System.out.println("Invalid line: " + inputLine);
				return false;
			}

			int romanToArabic = RomanNumberConvertor.convertRomanToArabic(romanNumber);
			if (romanToArabic < 0) {
				System.out.println("Invalid line: " + inputLine);
				return false;
			} else if (romanToArabic == 0) {
				// avoid div0
				romanToArabic = 1;
			}

			double currencyWorth = credits / romanToArabic;
			currencyMap.put(currency, currencyWorth);

			return false;
		}

		System.out.println("Invalid line: " + inputLine);
		return false;
	}
}
