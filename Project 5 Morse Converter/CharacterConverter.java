import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

/**
 * @author < Netta Paulson > Computer Science Department College of Engineering
 *         Virginia Commonwealth University Project 5: MorseCode This project
 *         creates the CharacterConverter Class than can convert a given English
 *         string to Morse code and vice versa. 4/30/19 CMSC 256, 003
 */
public class CharacterConverter extends MorseCharacter {

	/*
	 * CharacterConverter instance variables
	 */
	private BSTNode<MorseCharacter, String> searchTree;
	private HashMap<Integer, String> toEnglishHM = new HashMap<Integer, String>();
	private HashMap<Integer, MorseCharacter> toMorseHM = new HashMap<Integer, MorseCharacter>();

	/*
	 * This methods takes in a file and creates a search tree, a to Morse hashmap,
	 * and a to English hashmap based on the input
	 * 
	 * @param File aConversionFile the file that contains the morse characters and
	 * English equivalents
	 */
	public CharacterConverter(File aConversionFile) {
		Scanner fileScanner = null;
		File inputFile = aConversionFile;
		try {
			fileScanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file name.");
		}
		String englishChar = fileScanner.next();
		MorseCharacter morseCode = new MorseCharacter(fileScanner.next());
		searchTree = new BSTNode<MorseCharacter, String>(morseCode, englishChar);
		toMorseHM.put(englishChar.hashCode(), morseCode);
		toEnglishHM.put(morseCode.hashCode(), englishChar);
		while (fileScanner.hasNext()) {
			englishChar = fileScanner.next();
			morseCode = new MorseCharacter(fileScanner.next());
			searchTree.add(morseCode, englishChar);
			toMorseHM.put(englishChar.hashCode(), morseCode);
			toEnglishHM.put(morseCode.hashCode(), englishChar);
		}
		visualize();
	}

	/*
	 * This method converts a given English string to Morse code.
	 * 
	 * @param String anInput the given string to convert
	 * 
	 * @returns String converted the converted Morse code string
	 */
	public String convertFromEnglish(String anInput) {
		String converted = "";
		for (int i = 0; i < anInput.length(); i++) {
			if (toMorseHM.containsKey(anInput.substring(i, i + 1).hashCode())) {
				converted += toMorseHM.get(anInput.substring(i, i + 1).hashCode());
			} else {
				converted += anInput.charAt(i);
			}
			if (anInput.length() > i + 1) {
				converted += " ";
			}
		}
		return converted;

	}

	/*
	 * This method converts a given Morse code string to English.
	 * 
	 * @param String anInput the given string to convert
	 * 
	 * @returns String converted the converted English string
	 */
	public String convertToEnglish(String anInput) {
		String converted = "";
		String[] strSplit = anInput.split(" ");
		for (int i = 0; i < strSplit.length; i++) {
			if (toEnglishHM.containsKey(strSplit[i].hashCode())) {
				converted += toEnglishHM.get(strSplit[i].hashCode());
			} else {
				converted += strSplit[i];
			}
		}
		return converted;
	}

	/*
	 * This method traverses the tree inorder to return the values from least to
	 * greatest
	 * 
	 * @returns ArrayList<MorseCharacter> an ArrayList of the characters in
	 * ascending order
	 */
	public ArrayList<MorseCharacter> getSymbolAlphabet() {
		return searchTree.Inorder();
	}

	/*
	 * This method visualizes the search tree through Bridges
	 */
	public void visualize() {
		Bridges bridges = new Bridges(0, "paulsonnt", "82339506885");

		bridges.setTitle("Project 5");
		bridges.setDataStructure(searchTree);

		try {
			bridges.visualize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RateLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
