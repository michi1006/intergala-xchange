package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.RomanNumberConvertor;

public class RomanNumberTest {
	
	@Test
	public void testRomanNumber_inputMMXXIII_expect2023() {
		String input = "MMXXIII";
		
		int output = RomanNumberConvertor.convertRomanToArabic(input);
		assertEquals(output, 2023, output + " should equal 2018.");
	}
	
	@Test
	public void testRomanNumber_inputMXL_expect1040() {
		String input = "MXL";
		
		int output = RomanNumberConvertor.convertRomanToArabic(input);
		assertEquals(output, 1040, output + " should equal 1040.");
	}
	
	@Test
	public void testRomanNumber_inputMCMXLIV_expect1944() {
		String input = "MCMXLIV";
		
		int output = RomanNumberConvertor.convertRomanToArabic(input);
		assertEquals(output, 1944, output + " should equal 1944.");
	}
	
	@Test
	public void testRomanNumber_inputXXXX_expectInvalid() {
		String input = "XXXX";
		
		int output = RomanNumberConvertor.convertRomanToArabic(input);
		assertEquals(output, -1, output + " should be invalid (-1).");
	}
	
	@Test
	public void testRomanNumber_inputMMIM_expectInvalid() {
		String input = "MMIM";
		
		int output = RomanNumberConvertor.convertRomanToArabic(input);
		assertEquals(output, -1, output + " should be invalid (-1).");
	}
	
	@Test
	public void testRomanNumber_inputDDIX_expectInvalid() {
		String input = "DDIX";
		
		int output = RomanNumberConvertor.convertRomanToArabic(input);
		assertEquals(output, -1, output + " should be invalid (-1).");
	}
}
