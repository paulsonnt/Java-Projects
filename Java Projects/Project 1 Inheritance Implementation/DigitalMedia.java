/*
 * Netta Paulson
 * Project 1: Inheritance Implementation 
 * This class accepts an input file, opens it, reads in the contents, and creates objects based on the information in the file. 
 * 1/29/19
 * CMSC 256, 003
 */

public class DigitalMedia {
	
	/*
	 * Instance variables for DigitalMedia
	 */
	private String name;
	private long size;
	
	public DigitalMedia() {
		name = "";
		size = 0;
	}

	/*
	 * Parameterized constructor for DigitalMedia
	 * @param a string for the file name
	 * @param a long for the file size
	 */
	public DigitalMedia(String fileName, long fileSize) {
		name = fileName;
		size = fileSize;
	}
	/*
	 * This method gets the file name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/*
	 * This method gets the file size
	 * @return size
	 */
	public long getSize() {
		return size;
	}
	/*
	 * This method sets fileName to name
	 * @param a string variable that represents name
	 */	
	public void setName(String fileName) {
		name = fileName;
	}
	 /*
	  * This method checks if file name is null
	  * @param a string that represents the file name
	  * @returns boolean value of true if the name is not null, else false
	  */
	private boolean isValidName(String fileName) {
		boolean check = false;
		if(fileName != null) {
			check = true;
		}
		return check;
	}
	/*
	 * This method sets fileSize to size
	 * @param a long variable that represents size
	 */
	public void setSize(long fileSize) {
		size = fileSize;
	}
	/*
	 * This method checks if two objects are equal
	 * 
	 * @param an Object variable is passed into the method
	 * 
	 * First it is checked whether the object is a reference to itself
	 * @return if true returns true
	 * 
	 * Then it is checked if the object is an not instance of Digital Media
	 * @return if true returns false
	 * 
	 * The object is cast as an object of type DigitalMedia
	 * Then it is checked if the name and size are equal
	 * @return if true returns true, else false
	 */
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		else if(!(obj instanceof DigitalMedia)) {
			return false;
		}
		
		DigitalMedia file = (DigitalMedia) obj;
		
		if((file.name.equals(name) == true) && (file.size == size)) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * This method creates a string with name, size, and their respective titles
	 * @return the created string
	 */
	public String toString() {
		String string = "Name: " + name + "\nSize: " + size + "\n";
		return string;
	}
}