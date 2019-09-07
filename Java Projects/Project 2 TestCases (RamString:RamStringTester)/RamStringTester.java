/*
 * Netta Paulson
 * Project 2: Test Cases (RamString/RamStringTester)"
 * This project alters or checks a string based on the method's specifications. Test cases were created to test for errors in the code. 
 * 2/21/19
 * CMSC 256, 003
 * This class tests the methods in RamString returns either true or false based on if the test was passed.
 */

public class RamStringTester {
	
	public static void main(String[] args) {
		printHeading();
		/*
		 * If the method call returns true, then all tests have been passed
		 */
		if(getEvenCharactersTest() == true) {
			System.out.println("Even character tests passed");
		}
		if(getOddCharacterTest() == true) {
			System.out.println("Odd character tests passed");
		}
		if(countNonDigitsTest() == true) {
			System.out.println("Nondigits tests passed");
		}
		if(ramifyStringTest() == true) {
			System.out.println("Ramify string tests passed");
		}
		if(isValidEmailTest() == true) {
			System.out.println("Email validity test's tests passed");
		}
		if(convertDigitsToRomanNumeralsInSubstringTest() == true) {
			System.out.println("Roman Numeral conversion tests passed");
		}	
	}
	
	public static boolean getEvenCharactersTest() {
		RamString test = new RamString();
		
		/*
		 * This test checks whether method getEvenCharacters
		 * when given an empty string returns an empty string
		 */
		test.setWackyString("");
		if(!(test.getEvenCharacters().equals(""))) {
			System.out.println("Error in even charcter test for empty string.");
			return false;
		}
		/*
		 * This test checks whether method getEvenCharacters
		 * when given a single, odd string returns an empty string
		 */
		test.setWackyString("A");
		if(!(test.getEvenCharacters().equals(""))) {
			System.out.println("Error in even character test for single, odd string.");
			return false;
		}
		/*
		 * This test checks whether method getEvenCharacters
		 * when given a single, even string returns the even character
		 */
		test.setWackyString("WE");
		if(!(test.getEvenCharacters().equals("E"))) {
			System.out.println("Error in even charcter test for single, even string.");
			return false;
		}
		/*
		 * This test checks whether method getEvenCharacters
		 * when given a two-word string returns the even letters
		 */
		test.setWackyString("Hello World");
		if(!(test.getEvenCharacters().equals("el ol"))) {
			System.out.print("Error in even charcter test for two word string.");
			return false;
		}
		
		return true;
	}
	public static boolean getOddCharacterTest() {
		RamString test = new RamString();
		
		/*
		 * This test checks whether method getOddCharacters
		 * when given an empty returns an empty string
		 */
		test.setWackyString("");
		if(!(test.getOddCharacters().equals(""))) {
			System.out.println("Error in odd charcter test for empty string.");
			return false;
		}
		/*
		 * This test checks whether method getOddCharacters
		 * when given a single, odd string returns the odd character
		 */
		test.setWackyString("A");
		if(!(test.getOddCharacters().equals("A"))) {
			System.out.println("Error in odd charcter test for single, odd string.");
			return false;
		}
		/*
		 * This test checks whether method getOddCharacters
		 * when given a single, even string returns the odd character
		 */
		test.setWackyString("WE");
		if(!(test.getOddCharacters().equals("W"))) {
			System.out.println("Error in odd charcter test for single, even string.");
			return false;
		}
		/*
		 * This test checks whether method getOddCharacters
		 * when given a two-word string returns the even letters
		 */
		test.setWackyString("Hello World");
		if(!(test.getOddCharacters().equals("HloWrd"))) {
			System.out.println("Error in odd charcter test for two word string.");
			return false;
		}
		
		return true;
	}
	public static boolean countNonDigitsTest() {
		RamString test = new RamString();
		
		/*
		 * This test checks whether method countNonDigits
		 * when given an empty string returns an empty string
		 */
		test.setWackyString("");
		if(!(test.countNonDigits() == 0)) {
			System.out.println("Error in non-digit count for empty string.");
			return false;
		}
		/*
		 * This test checks whether method countNonDigits
		 * when given a string with only characters returns the length of the string
		 */
		test.setWackyString("Hello World");
		if(!(test.countNonDigits() == 11)) {
			System.out.println("Error in non-digit count for only charcter string.");
			return false;
		}
		/*
		 * This test checks whether method countNonDigits
		 * when given a string with only integers returns 0
		 */
		test.setWackyString("282047302");
		if(!(test.countNonDigits() == 0)) {
			System.out.println("Error in non-digit count for only digit string.");
			return false;
		}
		/*
		 * This test checks whether method countNonDigits
		 * when given a complex string returns the number of non-digits
		 */
		test.setWackyString("ash& yell!");
		if(!(test.countNonDigits() == 10)) {
			System.out.println("Error in non-digit count for complex string.");
			return false;
		}
		/*
		 * This test checks whether method countNonDigits
		 * when given an alphanumeric string returns the number of non-digits
		 */
		test.setWackyString("a3b2c1");
		if(!(test.countNonDigits() == 3)) {
			System.out.println("Error in non-digit count for alphanumeric string.");
			return false;
		}
		/*
		 * This test checks whether method countNonDigits
		 * when given a complex, alphanumeric string returns the number of non-digits
		 */
		test.setWackyString("h&y32E40D%");
		if(!(test.countNonDigits() == 6)) {
			System.out.println("Error in non-digit count for complex, alphanumeric string.");
			return false;
		}
		
		return true;
	}
	public static boolean isValidEmailTest() {
		RamString test = new RamString();
		/*
		 * This test checks whether method isValidEmail
		 * when given a valid email returns true
		 */
		test.setWackyString("paulsonnt@vcu.edu");
		if(test.isValidEmail()!= true) {
			System.out.println("Error in a valid email.");
			return false;
		}
		/*
		 * This test checks whether method isValidEmail
		 * when given an email with no characters before the @ returns false
		 */
		test.setWackyString("@vcu.edu");
		if(test.isValidEmail()!= false) {
			System.out.println(test.getWackyString());
			System.out.println("Error in a invalid email beginning with @.");
			return false;
		}
		/*
		 * This test checks whether method isValidEmail
		 * when given an email with no characters after the @ returns false
		 */
		test.setWackyString("email@.edu");
		if(test.isValidEmail()!= false) {
			System.out.println(test.getWackyString());
			System.out.println("Error in a invalid email with no charcter(s) after @.");
			return false;
		}
		/*
		 * This test checks whether method isValidEmail
		 * when given an email with no characters after the . returns false
		 */
		test.setWackyString("email@gmail.");
		if(test.isValidEmail()!= false) {
			System.out.println(test.getWackyString());
			System.out.println("Error in a invalid email with no charcter(s) after period.");
			return false;
		}
		/*
		 * This test checks whether method isValidEmail
		 * when given an email with no @ symbol returns false
		 */
		test.setWackyString("emailvcu.edu");
		if(test.isValidEmail()!= false) {
			System.out.println(test.getWackyString());
			System.out.println("Error in a invalid email with no @.");
			return false;
		}
		/*
		 * This test checks whether method isValidEmail
		 * when given an email with no @ or . returns false
		 */
		test.setWackyString("emailgmailcom");
		if(test.isValidEmail()!= false) {
			System.out.println(test.getWackyString());
			System.out.println("Error in a invalid email with no @ or period.");
			return false;
		}
		
		return true;
	}
	public static boolean ramifyStringTest() {
		RamString test = new RamString();
		/*
		 * This test checks whether method ramifyString
		 * when given an empty string returns an empty string
		 */
		test.setWackyString("");
		test.ramifyString();
		if(!(test.getWackyString().equals(""))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for empty string.");
			return false;
		}
		/*
		 * This test checks whether method ramifyString
		 * when given 0 returns Rams
		 */
		test.setWackyString("0");
		test.ramifyString();
		if(!(test.getWackyString().equals("Rams"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for 0 within string.");
			return false;
		}
		/*
		 * This test checks whether method ramifyString
		 * when given 00 returns VCU
		 */
		test.setWackyString("00");
		test.ramifyString();
		if(!(test.getWackyString().equals("VCU"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for 00 within string.");
			return false;
		}
		/*
		 * This test checks whether method ramifyString
		 * when given a string and 0 returns string plus Rams
		 */
		test.setWackyString("Go 0");
		test.ramifyString();
		if(!(test.getWackyString().equals("Go Rams"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for 0 within string.");
			return false;
		}
		/*
		 * This test checks whether method ramifyString
		 * when given a string and 00 returns string plus VCU
		 */
		test.setWackyString("Yay 00");
		test.ramifyString();
		if(!(test.getWackyString().equals("Yay VCU"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for 00 within string.");
			return false;
		}
		/*
		 * This test checks whether method ramifyString
		 * when given a 0 and 00 returns string Rams VCU
		 */
		test.setWackyString("0 00");
		test.ramifyString();
		if(!(test.getWackyString().equals("Rams VCU"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for 0 and 00 within string.");
			return false;
		}
		/*
		 * This test checks whether method ramifyString
		 * when given 000 returns string VCURams
		 */
		test.setWackyString("000");
		test.ramifyString();
		if(!(test.getWackyString().equals("VCURams"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for 000 within string.");
			return false;
		}
		/*
		 * This test checks whether method ramifyString
		 * when given a string with no zeros returns the same string
		 */
		test.setWackyString("Black and Gold");
		test.ramifyString();
		if(!(test.getWackyString().equals("Black and Gold"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in ramify for string with only charcters.");
			return false;
		}
		return true;
	}

	public static boolean convertDigitsToRomanNumeralsInSubstringTest() {
		RamString test = new RamString();
		/*
		 * This test checks whether method convertDigitsToRomanNumeralsInSubstring
		 * when given a string with only integers, returns that string with converted integers
		 */
		test.setWackyString("123");
		test.convertDigitsToRomanNumeralsInSubstring(1,2);
		if(!(test.getWackyString().equals("III3"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in converting substring with only integers.");
			return false;
		}
		/*
		 * This test checks whether method convertDigitsToRomanNumeralsInSubstring
		 * when given a string with only characters, returns that same string
		 */
		test.setWackyString("roman Numerals");
		test.convertDigitsToRomanNumeralsInSubstring(5,8);
		if(!(test.getWackyString().equals("roman Numerals"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in converting substring with only characters.");
			return false;
		}
		/*
		 * This test checks whether method convertDigitsToRomanNumeralsInSubstring
		 * when given a alphanumeric string returns the string with
		 * same characters but converted integers
		 */
		test.setWackyString("f9359e23g55ww8");
		test.convertDigitsToRomanNumeralsInSubstring(3,10);
		if(!(test.getWackyString().equals("f9IIIVIXeIIIIIgV5ww8"))) {
			System.out.println(test.getWackyString());
			System.out.println("Error in converting substring with both integers and characters.");
			return false;
		}
		/*
		 * This test checks whether method convertDigitsToRomanNumeralsInSubstring suitably 
		 * throws an IllegalArgumentException if startPosition is greater than endPosition
		 */
		test.setWackyString("f9359e23g55ww8");
		try {
			test.convertDigitsToRomanNumeralsInSubstring(7,2);
			System.out.println("Error in throwing IllegalArgumentException.");
			return false;
		}
		catch(IllegalArgumentException i){
		}
		/*
		 * This test checks whether method convertDigitsToRomanNumeralsInSubstring
		 * when given a the same start and end positions, returns string with that position's value converted
		 */
		test.setWackyString("d74hto2");
		test.convertDigitsToRomanNumeralsInSubstring(3,3);
			if(!(test.getWackyString().equals("d7IVhto2"))) {
				System.out.println(test.getWackyString());
				System.out.println("Error in converting substring with same start and end position");
				return false;
			}
		/*
		 * This test checks whether method convertDigitsToRomanNumeralsInSubstring suitably 
		 * throws an MyIndexOutOfBoundsException if startPosition is a negative integer
		 */
		test.setWackyString("f9359e23g55ww8");
		try {
			test.convertDigitsToRomanNumeralsInSubstring(-2,2);
			System.out.println("Error in throwing MyIndexOutOfBoundsException.");
			return false;
		}
		catch(MyIndexOutOfBoundsException i){
		}
		/*
		 * This test checks whether method convertDigitsToRomanNumeralsInSubstring suitably 
		 * throws an MyIndexOutOfBoundsException if endPosition is greater than length of string
		 */
		test.setWackyString("acb2345");
		try {
			test.convertDigitsToRomanNumeralsInSubstring(5,10);
			System.out.println("Error in throwing MyIndexOutOfBoundsException.");
			return false;
		}
		catch(MyIndexOutOfBoundsException i){
		}
		
		return true;
	}
	
	/*
	 * This method prints the creator's name, project name, class, and semester
	 */
	public static void printHeading() {
		System.out.println("Netta Paulson");
		System.out.println("Project 2: Test Cases (RamString/RamStringTester)");
		System.out.println("CMSC 256, 003");
		System.out.println("Spring Semester 2019\n");

	}
}
