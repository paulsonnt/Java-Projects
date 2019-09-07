/*
 * Netta Paulson
 * Project 1: Inheritance Implementation 
 * This class accepts an input file, opens it, reads in the contents, and creates objects based on the information in the file. 
 * 1/29/19
 * CMSC 256, 003
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MediaList {
	
	public MediaList() {
		
	}
	/*
	 * @param a string array
	 */
	public static void main(String[] args) {
		/*
		 * The method printHeading is called.
		 */
		printHeading();
		/*
		 * The displayOptions method is called
		 * A scanner variable is created
		 * The variable choice stores the user input
		 */
	    displayOptions();
	    Scanner input = new Scanner(System.in);
	    String choice = input.next();
		/*
		 * This while loop iterates as long as the user enters an invalid character
		 */
	    while(choice.charAt(0) != 'S' && choice.charAt(0) != 's' && choice.charAt(0) != 'I' && choice.charAt(0) != 'i' && choice.charAt(0) != 'Q' && choice.charAt(0) != 'q') {
	    	System.out.println("Please enter a valid character.");
	    	choice = input.next();
	     }
	    /*
	     * The while loop iterates as long as the valid character is not Q
	     */
	    while(choice.charAt(0) != 'Q' && choice.charAt(0) != 'q') {
	    	/*
	    	 * A scanner variable is created
	    	 */
	    	Scanner in;
	    	/*
	    	 * A try catch loop is implemented to catch FileNotFound exceptions
	    	 * @catch FileNotFoundException
	    	 */
	    	try {
	    		/*
		    	 * This is to get the input file name from the command line
		    	 * Else the promptForFileName method is called to complete the action through the console
		    	 */
	    		if(args.length != 0) {
	    			in = openFile(args[0]);
	    		}
	    		else {
	    			in = openFile(promptForFileName());
	    		}
	    		/*
		    	 * An ArrayList of type DigitalMedia is created called fileList
		    	 */
	    		ArrayList<DigitalMedia> fileList  = new ArrayList<DigitalMedia>();
	    		/*
		    	 * This while loop iterates while there is another line to read in the input file
		    	 */
	    		while(in.hasNextLine()) {
	    			/*
	    	    	 * The line is acquired and set to a string
	    	    	 * The line is split at each : and each part is put into the splitData array
	    	    	 */
	    			String nextLine = in.nextLine();
	    			String splitData[] = nextLine.split(":");
	    			/*
	    			 * If the first element in the array or the first value in the input data line is an S,
	    			 * Then create a new Song object with its specified arguments
	    			 * Add the object to the fileList array
	    			 * If the user's choice was to print Song objects, then the toString method prints out the specifications
	    			 */
	    			if(splitData[0].charAt(0) == 'S' || splitData[0].charAt(0) == 's') {
	    				Song songMedia = new Song(splitData[1].trim(), Long.parseLong(splitData[4].trim()), splitData[2].trim(), splitData[3].trim());
	    				fileList.add(songMedia); 
	    			if (choice.charAt(0) == 'S' || choice.charAt(0) == 's') {
	    				System.out.println(songMedia.toString());
	    			}
	    		}
	    			/*
	    			 * If the first element in the array or the first value in the input data line is an I,
	    			 * Then create a new Image object with its specified arguments
	    			 * Add the object to the fileList array
	    			 * If the user's choice was to print Image objects, then the toString method prints out the specifications
	    			 */
	    			if(splitData[0].charAt(0) == 'I' || splitData[0].charAt(0) == 'i') {
	    				Image imageMedia = new Image(splitData[1].trim(), Long.parseLong(splitData[4].trim()), Integer.parseInt(splitData[2].trim()), Integer.parseInt(splitData[3].trim()));
	    				fileList.add(imageMedia);
	    				if(choice.charAt(0) == 'I' || choice.charAt(0) == 'i') {
	    					System.out.println(imageMedia.toString());
	    				}
	    			}
	    		}
	    		/*
	    		 * The display options are given again and the user input is set to choice
	    		 */
	    		displayOptions();
	    	    input = new Scanner(System.in);
	    	    choice = input.next();
	    	    /*
	    	     * The new input value is checked again
	    	     */
	    	    while(choice.charAt(0) != 'S' && choice.charAt(0) != 's' && choice.charAt(0) != 'I' && choice.charAt(0) != 'i' && choice.charAt(0) != 'Q' && choice.charAt(0) != 'q') {
	    	    	System.out.println("Please enter a valid character.");
	    	    	choice = input.next();
	    	     }
	    	}
		catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		}
	   }
	    /*
	     * If the user's choice was Q, then print out Goodbye and the program ends
	     */
	    if(choice.charAt(0) == 'Q' || choice.charAt(0) == 'q')
		   System.out.println("Goodbye");
	   }
	/*
	 * This creates the method pringHeading.
	 * The project name, description, version date, and class are all output.
	 */
	public static void printHeading() {
		System.out.println("Project 1: Inheritance Implementation");
		System.out.println("This class accepts an input file, opens it, reads in the contents, and creates objects based on the information in the file.");
		System.out.println("1/29/19");
		System.out.println("CMSC 256, 003\n");
	}
	/*
     * This method gets the input file name from the user
     * @return the file name given
     */
	private static String promptForFileName() {
		String inputFileName = "";
      
	    Scanner input = new Scanner(System.in);
	    System.out.println("Enter an input file");
	    inputFileName = input.next();
	     
		return inputFileName;
	}
	/*
     * The method opens the a file
     * @param a string that represents the file name
     * A scanner is created using that file name
     * @return the scanner that is created
     */
	private static Scanner openFile(String fileName) throws FileNotFoundException{
		Scanner in = null;
		
		File inputFile = new File(fileName);
	    in = new Scanner(inputFile);
	    
		return in; 
	}
	/*
     * This method prints out the options the user has in this program
     */
	public static void displayOptions() {
		System.out.println("Press S to display a list of all the songs.");
		System.out.println("Press I to display a list of all the images.");
		System.out.println("Press Q to quit the program.");
	}
}