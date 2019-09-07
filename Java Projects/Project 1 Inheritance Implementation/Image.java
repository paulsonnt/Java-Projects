/*
 * Netta Paulson
 * Project 1: Inheritance Implementation 
 * This class accepts an input file, opens it, reads in the contents, and creates objects based on the information in the file. 
 * 1/29/19
 * CMSC 256, 003
 */

public class Image extends DigitalMedia {
	
	/*
	 * Instance variables for Image
	 */
	private int width;
	private int height;
	
	/*
	 * Parameterized constructor for Image with two arguments
	 * @param a string for the file name
	 * @param a long for the file size
	 * 
	 * call DigitalMedia methods to set name and size
	 */
	public Image(String name, long size) {
		super(name,size);
		width = 0;
		height = 0;
	}
	/*
	 * Parameterized constructor for Image with four arguments
	 * @param a string for the file name
	 * @param a long for the file size
	 * @param an int for the image width
	 * @param an int for the image height
	 * 
	 * call DigitalMedia methods to set name and size
	 */
	public Image(String name, long size, int imgWidth, int imgHeight) {
		super(name, size);
		width = imgWidth;
		height = imgHeight;
	}
	/*
	 * This method gets the image's width
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	/*
	 * This method gets the image's height
	 * @return width
	 */
	public int getHeight() {
		return height;
	}
	/*
	 * This method sets imgWidth to width
	 * @param a string variable that represents the image width
	 */
	public void setWidth(int imgWidth) {
		width = imgWidth;
	}
	/*
	 * This method sets imgHeight to height
	 * @param a string variable that represents the image height
	 */
	public void setHeight(int imgHeight) {
		height = imgHeight;
	}
	/*
	 * This method checks if two objects are equal
	 * 
	 * @param an Object variable is passed into the method
	 * 
	 * First it is checked whether the object is a reference to itself
	 * @return if true returns true
	 * 
	 * Then it is checked if the object is an not instance of Image
	 * @return if true returns false
	 * 
	 * The object is cast as an object of type Image
	 * Then it is checked if the name, size, width, and height are equal
	 * @return if true returns true, else false
	 */
	public boolean equals(Object imgObj) {
		if( this == imgObj) {
			return true;
		}
		else if(!(imgObj instanceof Image)) {
			return false;
		}
		
		Image imgFile = (Image) imgObj;
		
		if((imgFile.getName().equals(getName())) && (imgFile.getSize() == getSize()) && (imgFile.width == width) && (imgFile.height == height)){
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * This method creates a string with name, width, height, and their respective titles
	 * @return the created string
	 */
	public String toString() {
		String string = "Title: " + getName() + "\nWidth: " + width + "\nHeight: " + height + "\n";
		return string;
	}
}