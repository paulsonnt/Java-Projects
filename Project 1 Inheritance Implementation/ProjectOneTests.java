import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectOneTests {
	
	@Test
	public void DigitalMediaInstanceVariablesTest() {
		DigitalMedia testDigitalMedia = new DigitalMedia(null,0);
		@SuppressWarnings("rawtypes")
		Class c = testDigitalMedia.getClass();
		try {
			c.getDeclaredField("name");
			c.getDeclaredField("size");

			assertEquals(
					"You must only have the instance variables specified in the UML Diagram. When looking at the number of instance variables we",
					2, c.getDeclaredFields().length);

			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("name").getModifiers()));
			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("size").getModifiers()));

			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("name").getModifiers()));
			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("size").getModifiers()));

			assertEquals("You must make your name instance variable of type String.", (String.class),
					c.getDeclaredField("name").getType());
			assertEquals("You must make your size instance variable of type long.", (long.class),
					c.getDeclaredField("size").getType());

		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage().toString() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}

	@Test
	public void DigitalMediaParameterizedConstructorTest() {
		DigitalMedia myDigitalMedia = new DigitalMedia("Douglas Engelbart",1968);
		@SuppressWarnings("rawtypes")
		Class c = myDigitalMedia.getClass();
		try {
			Field name = c.getDeclaredField("name");
			name.setAccessible(true);
			String nameValue = (String) name.get(myDigitalMedia);
			assertEquals("When checking the value of name we", "Douglas Engelbart", nameValue);
			
			Field size = c.getDeclaredField("size");
			size.setAccessible(true);
			long sizeValue = (long) size.get(myDigitalMedia);
			assertEquals("When checking the value of size we", 1968, sizeValue);

		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void DigitalMediaGetNameTest() {
		DigitalMedia customDigitalMedia = createDigitalMedia("James Gosling", 1990);
		assertEquals("When calling the DigitalMedia object's getName method, we", "James Gosling",
				customDigitalMedia.getName());
	}
	
	@Test
	public void DigitalMediaGetSizeTest() {
		DigitalMedia customDigitalMedia = createDigitalMedia("James Gosling", 1990);
		assertEquals("When calling the DigitalMedia object's getSize method, we", 1990,
				customDigitalMedia.getSize());
	}

	@Test
	public void DigitalMediaSetNameTest() {
		DigitalMedia customDigitalMedia = createDigitalMedia("James Gosling", 1990);
		customDigitalMedia.setName("Dennis Ritchie");
		@SuppressWarnings("rawtypes")
		Class c = customDigitalMedia.getClass();
		try {
			Field name = c.getDeclaredField("name");
			name.setAccessible(true);
			String nameValue = (String) name.get(customDigitalMedia);
			assertEquals("After calling DigitalMedia's setName method, for the value of name we", "Dennis Ritchie", nameValue);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void DigitalMediaSetSizeTest() {
		DigitalMedia customDigitalMedia = createDigitalMedia("James Gosling", 1990);
		customDigitalMedia.setSize(1972);
		@SuppressWarnings("rawtypes")
		Class c = customDigitalMedia.getClass();
		try {
			Field size = c.getDeclaredField("size");
			size.setAccessible(true);
			long sizeValue = (long) size.get(customDigitalMedia);
			assertEquals("After calling DigitalMedia's setSize method, for the value of size we", 1972, sizeValue);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void DigitalMediaEqualsTest() {
		DigitalMedia cdm1 = createDigitalMedia("James Gosling", 1990);
		DigitalMedia cdm2 = createDigitalMedia("Dennis Ritchie", 1972);
		DigitalMedia cdm3 = createDigitalMedia("James Gosling", 1990);
		DigitalMedia cdm4 = null;
		Object cdm5 = "Test";
		
		assertEquals("When comparing two logically equlivant DigitalMedia objects using .equals(), we", true,
				cdm1.equals(cdm3));
		assertEquals("When comparing two logically different DigitalMedia objects using .equals(), we", false,
				cdm1.equals(cdm2));
		assertEquals("When comparing a DigitalMedia object to itself using .equals(), we", true,
				cdm1.equals(cdm1));
		assertEquals("When comparing a DigitalMedia object to null using .equals(), we", false,
				cdm1.equals(cdm4));
		assertEquals("When comparing a DigitalMedia object to a non DigitalMedia obejct using .equals(), we", false,
				cdm1.equals(cdm5));
		
		assertEquals(".equals(), must be symmetric", true,
				cdm3.equals(cdm1));
		
	}
	
	@Test
	public void DigitalMediaToStringTest() {
		DigitalMedia customDigitalMedia = createDigitalMedia("Martin Richards",1967);
		String result = customDigitalMedia.toString();
		String expected = "Name: " + "Martin Richards" + "\nSize: " + 1967 + "\n";
		assertEquals(expected,result);
		
	}
	
	private DigitalMedia createDigitalMedia(String aName, long aSize) {
		DigitalMedia myDigitalMedia = new DigitalMedia(null,0);
		@SuppressWarnings("rawtypes")
		Class c = myDigitalMedia.getClass();
		try {
			Field name = c.getDeclaredField("name");
			name.setAccessible(true);
			name.set(myDigitalMedia, aName);
			
			Field size = c.getDeclaredField("size");
			size.setAccessible(true);
			size.set(myDigitalMedia, aSize);
		} catch (Exception e) {
			fail(e.toString());
		}
		return myDigitalMedia;
	}

	

	@Test
	public void SongSuperclass() {
		Song testSong = new Song(null,0);
		assertEquals("When Testing if the Song super class is DigitalMedia, we", true, (testSong instanceof DigitalMedia));
	}

	@Test
	public void SongInstanceVariablesTest() {
		Song testSong = new Song(null,0);
		@SuppressWarnings("rawtypes")
		Class c = testSong.getClass();
		try {
			c.getDeclaredField("artist");
			c.getDeclaredField("album");

			assertEquals(
					"You must only have the instance variables specified in the UML Diagram. When looking at the number of instance variables we",
					2, c.getDeclaredFields().length);

			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("artist").getModifiers()));
			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("album").getModifiers()));

			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("artist").getModifiers()));
			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("album").getModifiers()));

			assertEquals("You must make your artist instance variable of type String.", (String.class),
					c.getDeclaredField("artist").getType());
			assertEquals("You must make your album instance variable of type String.", (String.class),
					c.getDeclaredField("album").getType());

		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage().toString() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}

	@Test
	public void SongParameterizedTwoArgsConstructorTest() {
		Song mySong = new Song("Kansai",10);
		@SuppressWarnings("rawtypes")
		Class c = mySong.getClass();
		@SuppressWarnings("rawtypes")
		Class superC = c.getSuperclass();
		try {

			Field name = superC.getDeclaredField("name");
			name.setAccessible(true);
			String nameValue = (String) name.get(mySong);
			assertEquals("When checking the value of name we", "Kansai", nameValue);

			Field size = superC.getDeclaredField("size");
			size.setAccessible(true);
			long sizeValue = (long) size.get(mySong);
			assertEquals("When checking the value of size we", 10, sizeValue);

			Field artist = c.getDeclaredField("artist");
			artist.setAccessible(true);
			String artistValue = (String) artist.get(mySong);


			Field album = c.getDeclaredField("album");
			album.setAccessible(true);
			String albumValue = (String) album.get(mySong);
			assertEquals("When checking the value of album we", "", albumValue);

		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void SongParameterizedFourArgsConstructorTest() {
		Song mySong = new Song("Time",9,"Pink Floyd","Dark Side Of The Moon");
		@SuppressWarnings("rawtypes")
		Class c = mySong.getClass();
		@SuppressWarnings("rawtypes")
		Class superC = c.getSuperclass();
		try {

			Field name = superC.getDeclaredField("name");
			name.setAccessible(true);
			String nameValue = (String) name.get(mySong);
			assertEquals("When checking the value of name we", "Time", nameValue);

			Field size = superC.getDeclaredField("size");
			size.setAccessible(true);
			long sizeValue = (long) size.get(mySong);
			assertEquals("When checking the value of size we", 9, sizeValue);

			Field artist = c.getDeclaredField("artist");
			artist.setAccessible(true);
			String artistValue = (String) artist.get(mySong);
			assertEquals("When checking the value of artist we", "Pink Floyd", artistValue);

			Field album = c.getDeclaredField("album");
			album.setAccessible(true);
			String albumValue = (String) album.get(mySong);
			assertEquals("When checking the value of album we", "Dark Side Of The Moon", albumValue);

		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void SongGetArtistTest() {
		Song customSong = createSong("Time",9,"Pink Floyd","Dark Side Of The Moon");
		assertEquals("When calling the Song object's getArtist method, we", "Pink Floyd",
				customSong.getArtist());
	}
	
	@Test
	public void SongGetAlbumTest() {
		Song customSong = createSong("Time",9,"Pink Floyd","Dark Side Of The Moon");
		assertEquals("When calling the Song object's getAlbum method, we", "Dark Side Of The Moon",
				customSong.getAlbum());
	}

	@Test
	public void SongSetArtistTest() {
		Song customSong = createSong("Time",9,"Pink Floyd","Dark Side Of The Moon");
		customSong.setArtist("Arctic Monkeys");
		@SuppressWarnings("rawtypes")
		Class c = customSong.getClass();
		try {
			Field artist = c.getDeclaredField("artist");
			artist.setAccessible(true);
			String artistValue = (String) artist.get(customSong);
			assertEquals("After calling Song's setArtist method, for the value of artist we", "Arctic Monkeys", artistValue);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void SongSetAlbumTest() {
		Song customSong = createSong("Time",9,"Pink Floyd","Dark Side Of The Moon");
		customSong.setAlbum("Tranquility Base Hotel & Casino");
		@SuppressWarnings("rawtypes")
		Class c = customSong.getClass();
		try {
			Field album = c.getDeclaredField("album");
			album.setAccessible(true);
			String albumValue = (String) album.get(customSong);
			assertEquals("After calling Song's setAlbum method, for the value of album we", "Tranquility Base Hotel & Casino", albumValue);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void SongEqualsTest() {
		Song cdm1 = createSong("Time",9,"Pink Floyd","Dark Side Of The Moon");
		Song cdm2 = createSong("Four Out of Five",16,"Arctic Monkeys","Tranquility Base Hotel & Casino");
		Song cdm3 = createSong("Time",9,"Pink Floyd","Dark Side Of The Moon");
		Song cdm4 = null;
		Object cdm5 = "Test";
		
		assertEquals("When comparing two logically equlivant Song objects using .equals(), we", true,
				cdm1.equals(cdm3));
		assertEquals("When comparing two logically different Song objects using .equals(), we", false,
				cdm1.equals(cdm2));
		assertEquals("When comparing a Song object to itself using .equals(), we", true,
				cdm1.equals(cdm1));
		assertEquals("When comparing a Song object to null using .equals(), we", false,
				cdm1.equals(cdm4));
		assertEquals("When comparing a Song object to a non Song obejct using .equals(), we", false,
				cdm1.equals(cdm5));
		
		assertEquals(".equals(), must be symmetric", true,
				cdm3.equals(cdm1));
		
	}
	
	@Test
	public void SongToStringTest() {
		Song customSong = createSong("The Man Who Married A Robot / Love Theme",8,"The 1975","A Brief Inquiry Into Online Relationships");
		String result = customSong.toString();
		String expected = "Title: " + "The Man Who Married A Robot / Love Theme" + "\nArtist: " + "The 1975" + "\nAlbum: " + "A Brief Inquiry Into Online Relationships" + "\n";

		
	}
	
	private Song createSong(String aName, long aSize, String anArtist, String anAlbum) {
		Song mySong = new Song(null,0);
		@SuppressWarnings("rawtypes")
		Class c = mySong.getClass();
		@SuppressWarnings("rawtypes")
		Class superC = c.getSuperclass();
		try {
			Field name = superC.getDeclaredField("name");
			name.setAccessible(true);
			name.set(mySong, aName);
			
			Field size = superC.getDeclaredField("size");
			size.setAccessible(true);
			size.set(mySong, aSize);

			Field album = c.getDeclaredField("album");
			album.setAccessible(true);
			album.set(mySong, anAlbum);
			
			Field artist = c.getDeclaredField("artist");
			artist.setAccessible(true);
			artist.set(mySong, anArtist);

		} catch (Exception e) {
			fail(e.toString());
		}
		return mySong;
	}

	
	@Test
	public void ImageSuperclass() {
		Image testImage = new Image(null,0);
		assertEquals("When Testing if the Image super class is DigitalMedia, we", true, (testImage instanceof DigitalMedia));
	}

	@Test
	public void ImageInstanceVariablesTest() {
		Image testImage = new Image(null,0);
		@SuppressWarnings("rawtypes")
		Class c = testImage.getClass();
		try {
			c.getDeclaredField("width");
			c.getDeclaredField("height");

			assertEquals(
					"You must only have the instance variables specified in the UML Diagram. When looking at the number of instance variables we",
					2, c.getDeclaredFields().length);

			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("width").getModifiers()));
			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("height").getModifiers()));

			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("width").getModifiers()));
			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("height").getModifiers()));

			assertEquals("You must make your width instance variable of type int.", (int.class),
					c.getDeclaredField("width").getType());
			assertEquals("You must make your height instance variable of type int.", (int.class),
					c.getDeclaredField("height").getType());

		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage().toString() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}

	@Test
	public void ImageParameterizedTwoArgsConstructorTest() {
		Image myImage = new Image("xd6wmk6mz6201.png",68);
		@SuppressWarnings("rawtypes")
		Class c = myImage.getClass();
		@SuppressWarnings("rawtypes")
		Class superC = c.getSuperclass();
		try {

			Field name = superC.getDeclaredField("name");
			name.setAccessible(true);
			String nameValue = (String) name.get(myImage);
			assertEquals("When checking the value of name we", "xd6wmk6mz6201.png", nameValue);

			Field size = superC.getDeclaredField("size");
			size.setAccessible(true);
			long sizeValue = (long) size.get(myImage);
			assertEquals("When checking the value of size we", 68, sizeValue);

			Field width = c.getDeclaredField("width");
			width.setAccessible(true);
			int widthValue = (int) width.get(myImage);
			assertEquals("When checking the value of width we", 0, widthValue);

			Field height = c.getDeclaredField("height");
			height.setAccessible(true);
			int heightValue = (int) height.get(myImage);
			assertEquals("When checking the value of height we", 0, heightValue);

		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void ImageParameterizedFourArgsConstructorTest() {
		Image myImage = new Image("lisp.jpg",64,740,220);
		@SuppressWarnings("rawtypes")
		Class c = myImage.getClass();
		@SuppressWarnings("rawtypes")
		Class superC = c.getSuperclass();
		try {

			Field name = superC.getDeclaredField("name");
			name.setAccessible(true);
			String nameValue = (String) name.get(myImage);
			assertEquals("When checking the value of name we", "lisp.jpg", nameValue);

			Field size = superC.getDeclaredField("size");
			size.setAccessible(true);
			long sizeValue = (long) size.get(myImage);
			assertEquals("When checking the value of size we", 64, sizeValue);

			Field width = c.getDeclaredField("width");
			width.setAccessible(true);
			int widthValue = (int) width.get(myImage);
			assertEquals("When checking the value of width we", 740, widthValue);

			Field height = c.getDeclaredField("height");
			height.setAccessible(true);
			int heightValue = (int) height.get(myImage);
			assertEquals("When checking the value of height we", 220, heightValue);

		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void ImageGetWidthTest() {
		Image customImage = createImage("lisp.jpg",64,740,220);
		assertEquals("When calling the Image object's getWidth method, we", 740,
				customImage.getWidth());
	}
	
	@Test
	public void ImageGetHeightTest() {
		Image customImage = createImage("lisp.jpg",64,740,220);
		assertEquals("When calling the Image object's getHeight method, we", 220,
				customImage.getHeight());
	}

	@Test
	public void ImageSetWidthTest() {
		Image customImage = createImage("lisp.jpg",64,740,220);
		customImage.setWidth(640);
		@SuppressWarnings("rawtypes")
		Class c = customImage.getClass();
		try {
			Field width = c.getDeclaredField("width");
			width.setAccessible(true);
			int widthValue = (int) width.get(customImage);
			assertEquals("After calling Image's setWidth method, for the value of width we", 640, widthValue);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void ImageSetHeightTest() {
		Image customImage = createImage("lisp.jpg",64,740,220);
		customImage.setHeight(211);
		@SuppressWarnings("rawtypes")
		Class c = customImage.getClass();
		try {
			Field height = c.getDeclaredField("height");
			height.setAccessible(true);
			int heightValue = (int) height.get(customImage);
			assertEquals("After calling Image's setHeight method, for the value of height we", 211, heightValue);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void ImageEqualsTest() {
		Image cdm1 = createImage("lisp.jpg",64,740,220);
		Image cdm2 = createImage("lisp_cycles",41,640,211);
		Image cdm3 = createImage("lisp.jpg",64,740,220);
		Image cdm4 = null;
		Object cdm5 = "Test";
		
		assertEquals("When comparing two logically equlivant Image objects using .equals(), we", true,
				cdm1.equals(cdm3));
		assertEquals("When comparing two logically different Image objects using .equals(), we", false,
				cdm1.equals(cdm2));
		assertEquals("When comparing a Image object to itself using .equals(), we", true,
				cdm1.equals(cdm1));
		assertEquals("When comparing a Image object to null using .equals(), we", false,
				cdm1.equals(cdm4));
		assertEquals("When comparing a Image object to a non Image obejct using .equals(), we", false,
				cdm1.equals(cdm5));
		
		assertEquals(".equals(), must be symmetric", true,
				cdm3.equals(cdm1));
		
	}
	
	@Test
	public void ImageToStringTest() {
		Image customImage = createImage("A Brief Inquiry Into Online Relationships.jpg",8,1500,1501);
		String result = customImage.toString();
		String expected = "Title: " + "A Brief Inquiry Into Online Relationships.jpg" + "\nWidth: " + 1500 + "\nHeight: " + 1501 + "\n";
		assertEquals(expected,result);
		
	}
	
	private Image createImage(String aName, long aSize, int aWidth, int aHeight) {
		Image myImage = new Image(null,0);
		@SuppressWarnings("rawtypes")
		Class c = myImage.getClass();
		@SuppressWarnings("rawtypes")
		Class superC = c.getSuperclass();
		try {
			Field name = superC.getDeclaredField("name");
			name.setAccessible(true);
			name.set(myImage, aName);
			
			Field size = superC.getDeclaredField("size");
			size.setAccessible(true);
			size.set(myImage, aSize);

			Field height = c.getDeclaredField("height");
			height.setAccessible(true);
			height.set(myImage, aHeight);
			
			Field width = c.getDeclaredField("width");
			width.setAccessible(true);
			width.set(myImage, aWidth);

		} catch (Exception e) {
			fail(e.toString());
		}
		return myImage;
	}
}



