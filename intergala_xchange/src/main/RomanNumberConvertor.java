package main;

public class RomanNumberConvertor {

	public static int convertRomanToArabic(String romanInput) {
		if(romanInput == null || romanInput.isEmpty()) {
			System.out.println("Invalid input: " + romanInput);
			return -1;
		}
		
		String inputUpper = romanInput.toUpperCase();
		RomanNumber[] romanNumbers = RomanNumber.values();
		
		int result = 0;
		
		for (RomanNumber romanNumber : romanNumbers) {
			int occurances = 0;
			
			while(inputUpper.startsWith(romanNumber.name())) {
				occurances++;
				
				if(occurances > 1 & !romanNumber.isRepeatable() || occurances > 3) {
					System.out.println("Invalid input: " + romanInput + ", " + romanNumber.name()+ " is repeated "+ occurances +" times.");
					return -1;
				}
				
				result += romanNumber.getValue();
				inputUpper = inputUpper.substring(romanNumber.name().length());
			}
		}
		
		if(inputUpper.length() > 0) {
			System.out.println("Invalid input: " + romanInput + ", could not be fully parsed. Not parsed part: " + inputUpper);
			return -1;
		}
		
		return result;
	}
}
