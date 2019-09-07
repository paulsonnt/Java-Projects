/**
 * @author < Netta Paulson > Computer Science Department College of Engineering
 *         Virginia Commonwealth University Project 5: MorseCode This project
 *         creates a MorseCharacter object that implements the comparable
 *         interface. 4/30/19 CMSC 256, 003
 */

public class MorseCharacter implements Comparable<MorseCharacter> {

	/*
	 * Instance variable for MorseCharacter
	 */
	private String character;

	/*
	 * Default constructor for MorseCharacter
	 */
	public MorseCharacter() {
		character = "";
	}

	/*
	 * Parameterized constructor for MorseCharacter
	 */
	public MorseCharacter(String code) {
		character = code;
	}

	/*
	 * This method gets the string representation of the Morse character
	 * 
	 * @returns String character the Morse character
	 */
	public String getCode() {
		return character;
	}

	/*
	 * This method set the character to a given string
	 * 
	 * @param String code the given string to create a Morse character
	 */
	public void setCode(String code) {
		character = code;
	}

	/*
	 * This method converted character to a string
	 * 
	 * @returns the string representation of the Morse character
	 */
	public String toString() {
		if (character != null) {
			return character;
		}
		return "";
	}

	/*
	 * This method checks if two Morse characters are equivalent
	 * 
	 * @returns boolean true if the two characters are equal
	 */
	public boolean equals(Object entry) {
		if (entry != null) {
			if (character.compareTo(entry.toString()) == 0) {
				return true;
			}
		}
		return false;
	}

	/*
	 * This method converts the character into a hashcode
	 * 
	 * @returns int value the value after it has gone throught the hash function
	 */
	public int hashCode() {
		return character.hashCode();
	}

	/*
	 * This method compares two Morse characters to check which is larger
	 * 
	 * @param MorseCharacter entry the character to be compared with
	 * 
	 * @returns int child the value -1 if the first value is smaller, 0 if they are
	 * equal, or 1 if the first is smaller
	 */
	public int compareTo(MorseCharacter entry) {
		int value = 1;
		int check = this.toString().compareTo(entry.toString());
		if (check == -1) {
			if (this.toString().length() == entry.toString().length()) {
				value = 1;
			} else {
				value = check;
			}
		} else if (check == 1) {
			if (this.toString().length() == entry.toString().length()) {
				value = -1;
			} else {
				value = check;
			}
		} else {
			if (check == 0) {
				value = 0;
			}
		}
		return value;
	}
}
