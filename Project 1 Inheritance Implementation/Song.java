/*
 * Netta Paulson
 * Project 1: Inheritance Implementation 
 * This class accepts an input file, opens it, reads in the contents, and creates objects based on the information in the file. 
 * 1/29/19
 * CMSC 256, 003
 */

public class Song extends DigitalMedia {
	
	/*
	 * Instance variables for Song
	 */
	private String artist;
	private String album;

	/*
	 * Parameterized constructor for Song with two arguments
	 * 
	 * @param a string for the file name
	 * @param a long for the file size
	 * 
	 * call DigitalMedia methods to set name and size
	 * set artist and album to empty strings
	 */
	public Song(String fileName, long fileLength) {
		super(fileName,fileLength);
		artist = "";
		album = "";
	}
	/*
	 * Parameterized constructor for Song with two arguments
	 * 
	 * @param a string for the file name
	 * @param a long for the file size
	 * @param a string for artist name
	 * @param a string for the album name
	 */
	public Song(String fileName, long fileLength, String songArtist, String songAlbum) {
		super(fileName, fileLength);
		artist = songArtist;
		album = songAlbum;		
	}
	/*
	 * This method gets the song title
	 * The extension from the file name is removed to leave just the song title
	 * @return title
	 */
	public String getTitle() {
		String title = getName().substring(0,getName().length() - 4);
		return title;
	}
	/*
	 * This method gets the song's artist
	 * @return artist
	 */
	public String getArtist() {
		return artist;
	}
	/*
	 * This method gets the song's album
	 * @return album
	 */
	public String getAlbum() {
		return album;
	}
	/*
	 * This method sets songTitle to the DigitalMedia file name
	 * @param a string variable that represents the song title
	 */	
	public void setTitle(String songTitle) {
		setName(songTitle);
	}
	/*
	 * This method sets songArtist to artist
	 * @param a string variable that represents the song artist
	 */
	public void setArtist(String songArtist) {
		artist = songArtist;
	}
	/*
	 * This method sets songAlbum to album
	 * @param a string variable that represents the song album
	 */
	public void setAlbum(String songAlbum) {
		album = songAlbum;
	}
	/*
	 * This method checks if two objects are equal
	 * 
	 * @param an Object variable is passed into the method
	 * 
	 * First it is checked whether the object is a reference to itself
	 * @return if true returns true
	 * 
	 * Then it is checked if the object is an not instance of Song
	 * @return if true returns false
	 * 
	 * The object is cast as an object of type Song
	 * Then it is checked if the name, size, artist, and album are equal
	 * @return if true returns true, else false
	 */
	public boolean equals(Object songObj) {
		if(this == songObj) {
			return true;
		}
		else if(!(songObj instanceof Song)) {
			return false;
		}
		
		Song songFile = (Song) songObj;
		
		if((songFile.getTitle().equals(getTitle()) == true) && (songFile.getSize() == getSize()) && (songFile.artist.equals(artist) == true) && (songFile.album.equals(album) == true)) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * This method creates a string with name, size, artist, album, and their respective titles
	 * @return the created string
	 */
	public String toString() {
		String string = "Title: " + getTitle() + "\nArtist: " + artist + "\nAlbum: " + album + "\n";
		return string;
	}
}
