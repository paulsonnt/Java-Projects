import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import bridges.base.DLelement;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BridgesDoublyLinkedListTest {

	BridgesDoublyLinkedList<String> list;
			
	public void populateList(String[] elements) {
		list = createList(elements);
	}
		
	@Test
	@GradedTest(name="Test add to an empty list", max_score=2.5)
	public void testAddT0() {
		String[] givenElements = {};
		String[] expectedElements = {"Barb"};
		
		populateList(givenElements);
		list.add("Barb");
		validateListState(list, expectedElements);
	}
	
	@Test 
	@GradedTest(name="Test add to a non-empty list", max_score=2.5)
	public void testAddT2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven","Barb"};
		
		populateList(givenElements);
		list.add("Barb");
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Add to index zero of an empty list", max_score=0.83)
	public void testAddIntT1a() {
		String[] givenElements = {};
		String[] expectedElements = {"Barb"};
		
		populateList(givenElements);
		list.add(0, "Barb");
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Add to index zero of a non-empty list", max_score=0.83)
	public void testAddIntT2a() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Barb","Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		list.add(0, "Barb");    
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Add to index 2 of a non-empty list", max_score=0.83)
	public void testAddIntT3a() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Barb","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		list.add(2, "Barb");
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Add to end of a non-empty list", max_score=0.83)
	public void testAddIntT4() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven","Barb"};
		
		populateList(givenElements);
		list.add(5, "Barb");
		validateListState(list,expectedElements);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Add an element to an invalid index of an empty list", max_score=0.83) 
	public void testAddIntT5() {
		String[] givenElements = {};
		
		populateList(givenElements);
		list.add(2, "Barb");
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}

	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Add an element to an invalid index of a non-empty list", max_score=0.83) 
	public void testAddIntT6() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		list.add(10, "Barb");
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}
	
	@Test 
	@GradedTest(name="Remove from front of a non-empty list", max_score=1)
	public void testRemove1() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When removing the element at index 0 and checking the return value of remove, we","Will", list.remove(0));
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Remove from end of a non-empty list", max_score=1)
	public void testRemove2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike"};
		
		populateList(givenElements);
		assertEquals("When removing the element at index 4 and checking the return value of remove, we","Eleven",list.remove(4));
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Remove from the middle", max_score=1)
	public void testRemove3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When removing the element at index 4 and checking the return value of remove, we","Lucas",list.remove(2));
		validateListState(list,expectedElements);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Remove invalid (too small) index", max_score=1) 
	public void testRemove4() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		list.remove(-1);
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}

	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Remove invalid (too large) index", max_score=1) 
	public void testRemove5() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
	
		populateList(givenElements);
		list.remove(8);
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}

	@Test 
	@GradedTest(name="Test remove element return value - true", max_score=2.5)
	public void testRemoveElement1() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Eleven"};
		
		populateList(givenElements);
		assertTrue("When calling remove with a value contained in the list, for the return value of remove we",list.remove("Mike"));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test remove element return value - false", max_score=2.5)
	public void testRemoveElement2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertFalse("When calling remove with a value not contained in the list, for the return value of remove we",list.remove("Joyce"));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test is empty after call to clear()", max_score=5)
	public void testClear() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {};
		
		populateList(givenElements);
		list.clear();
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test Replace for last node", max_score=1.25)
	public void testReplace1() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven","Barb"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven","Nothing"};
		
		populateList(givenElements);
		list.replace(5, "Nothing");
		validateListState(list,expectedElements);
	}

	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test replace with a position greater than list length", max_score=1.25) 
	public void testReplace2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven","Barb"};
		
		populateList(givenElements);
		list.replace(6, "Nothing");
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test replace with a position less than list length", max_score=1.25) 
	public void testReplace3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven","Barb"};
		
		populateList(givenElements);
		list.replace(-1, "Nothing");
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}

	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test replace in an empty list", max_score=1.25) 
	public void testReplace4() {
		String[] givenElements = {};
		
		populateList(givenElements);
		list.replace(1, "Nothing");
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}

	@Test 
	@GradedTest(name="Test getEntry when index is first item", max_score=1)
	public void testGetEntry1() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When getting the value at index 0 we","Will",list.getEntry(0));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test getEntry when index is last item", max_score=1)
	public void testGetEntry2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When getting the value at index 4 we","Eleven",list.getEntry(4));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test getEntry when index is 2", max_score=1)
	public void testGetEntry3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When getting the value at index 2 we","Lucas",list.getEntry(2));
		validateListState(list,expectedElements);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test getEntry when index is too large", max_score=1) 
	public void testGetEntry4() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		list.getEntry(5);
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}

	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test getEntry when index is too small", max_score=1) 
	public void testGetEntry5() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		list.getEntry(-1);
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}
	
	@Test 
	@GradedTest(name="Test toArray when list is empty", max_score=2.5)
	public void testToArray1() {
		String[] givenElements = {};
		String[] expectedElements = {};
		
		populateList(givenElements);
		assertArrayEquals("When checking the value of toArray we",expectedElements,list.toArray());
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test toArray when list is populated", max_score=2.5)
	public void testToArray2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertArrayEquals("When checking the value of toArray we",expectedElements,list.toArray());
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Test contains on an empty list", max_score=1.67)
	public void testContains1() {
		String[] givenElements = {};
		String[] expectedElements = {};
		
		populateList(givenElements);
		assertFalse(list.contains("Eleven"));
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Test contains on a populated list", max_score=1.67)
	public void testContains2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertTrue(list.contains("Eleven"));
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Test contains on item not in list", max_score=1.67)
	public void testContains3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertFalse(list.contains("Barb"));
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Test getLength on an empty list", max_score=1.67)
	public void testGetLength1() {
		String[] givenElements = {};
		String[] expectedElements = {};
		
		populateList(givenElements);
		assertEquals(0, list.getLength());
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test getLength on a list with one element", max_score=1.67)
	public void testGetLength2() {
		String[] givenElements = {"Barb"};
		String[] expectedElements = {"Barb"};
		
		populateList(givenElements);
		assertEquals(1, list.getLength());
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test getLength on a populated list", max_score=1.67)
	public void testGetLength3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals(5, list.getLength());
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test isEmpty on empty list", max_score=1.67)
	public void testIsEmpty1() {
		String[] givenElements = {};
		String[] expectedElements = {};
		
		populateList(givenElements);
		assertTrue(list.isEmpty());
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test isEmpty on list with a single element", max_score=1.67)
	public void testIsEmpty2() {
		String[] givenElements = {"Barb"};
		String[] expectedElements = {"Barb"};
		
		populateList(givenElements);
		assertFalse(list.isEmpty());
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test isEmpty on a list with multiple elements", max_score=1.67)
	public void testIsEmpty3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertFalse(list.isEmpty());
		validateListState(list,expectedElements);
	}
	
	@Test 
	@GradedTest(name="Test indexOf first element in a populated list", max_score=2.5)
	public void testIndexOf1() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When checking the index of Will in the list we",0, list.indexOf("Will"));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test indexOf element not in a populated list", max_score=2.5)
	public void testIndexOf2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When checking the index of Joyce (not in list) we",-1, list.indexOf("Joyce"));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test lastIndexOf an element that is in the list twice", max_score=1.67)
	public void testLastIndexOf1() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven","Will","Dustin"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven","Will","Dustin"};
		
		populateList(givenElements);
		assertEquals("When checking the index of Will we",5, list.lastIndexOf("Will"));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test lastIndexOf an element that is in the list twice", max_score=1.67)
	public void testLastIndexOf2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven","Will","Dustin"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven","Will","Dustin"};
		
		populateList(givenElements);
		assertEquals("When checking the index of Dustin we",6, list.lastIndexOf("Dustin"));
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test lastIndexOf an element that is not in the list", max_score=1.67)
	public void testLastIndexOf3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven","Will","Dustin"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven","Will","Dustin"};
		
		populateList(givenElements);
		assertEquals("When checking the index of Joyce (not in list) we",-1, list.lastIndexOf("Joyce"));
		validateListState(list,expectedElements);
	}

	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test getNodeAt in an empty list", max_score=0.83) 
	public void testGetNodeAt0() {
		String[] givenElements = {};
		
		populateList(givenElements);
		
		list.getNodeAt(1);
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test getNodeAt with an invalid position of -1", max_score=0.83) 
	public void testGetNodeAt1() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		
		list.getNodeAt(-1);
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}

	@Test (expected=IndexOutOfBoundsException.class)
	@GradedTest(name="Test getNodeAt with an invalid position > list size", max_score=0.83) 
	public void testGetNodeAt2() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		
		list.getNodeAt(7);
		fail("Expected an IndexOutOfBoundsException to be thrown");
	}
	
	@Test 
	@GradedTest(name="Test getNodeAt for last element in a populated list", max_score=0.83) 
	public void testGetNodeAt3() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When getting the node at index 4 we",list.getNodeAt(4).getValue(), "Eleven");
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test getNodeAt for first element in a populated list", max_score=0.83) 
	public void testGetNodeAt4() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When getting the node at index 0 we",list.getNodeAt(0).getValue(), "Will");
		validateListState(list,expectedElements);
	}

	@Test 
	@GradedTest(name="Test getNodeAt for middle element in a populated list", max_score=0.83) 
	public void testGetNodeAt5() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("When getting the node at index 2 we",list.getNodeAt(2).getValue(), "Lucas");
		validateListState(list,expectedElements);
	}
	
	@Test
	@GradedTest(name="Test visualize method", max_score=5) 
	public void visualizeTest() {
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));

		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		list.visualize();
		validateListState(list,expectedElements);

		final String standardOutput = myOut.toString();
		assertTrue(standardOutput.contains("http://bridges-cs.herokuapp.com/"));
	}
	
	@Test
	@GradedTest(name="Test instance variables", max_score=2.5) 
	public void InstanceVariablesTest() {
		BridgesDoublyLinkedList<Object> testList = new BridgesDoublyLinkedList<Object>();
		@SuppressWarnings("rawtypes")
		Class c = testList.getClass();
		try {
			c.getDeclaredField("first");
			c.getDeclaredField("last");
			c.getDeclaredField("numElements");

			assertEquals(
					"You must only have the instance variables specified. When looking at the number of instance variables we",
					3, c.getDeclaredFields().length);

			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("first").getModifiers()));
			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("last").getModifiers()));
			assertEquals("You must make your instance variables private.", true,
					Modifier.isPrivate(c.getDeclaredField("numElements").getModifiers()));

			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("first").getModifiers()));
			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("last").getModifiers()));
			assertEquals("Your instance variables must NOT be static.", false,
					Modifier.isStatic(c.getDeclaredField("numElements").getModifiers()));

			assertEquals("You must make your first instance variable of type DLelement.", (DLelement.class),
					c.getDeclaredField("first").getType());
			assertEquals("You must make your last instance variable of type DLelement.", (DLelement.class),
					c.getDeclaredField("last").getType());
			assertEquals("You must make your numElements instance variable of type int.", (int.class),
					c.getDeclaredField("numElements").getType());

		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage().toString() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}

	@Test
	@GradedTest(name="Test constructor", max_score=2.5) 
	public void DefaultConstructorTest() {
		BridgesDoublyLinkedList<Object> myList = new BridgesDoublyLinkedList<Object>();
		@SuppressWarnings("rawtypes")
		Class c = myList.getClass();
		try {
			Field first = c.getDeclaredField("first");
			first.setAccessible(true);
			@SuppressWarnings("unchecked")
			DLelement<Object> firstValue = (DLelement<Object>) first.get(myList);
			assertNull("When checking the value of first we", firstValue);
			
			Field last = c.getDeclaredField("last");
			last.setAccessible(true);
			@SuppressWarnings("unchecked")
			DLelement<Object> lastValue = (DLelement<Object>) last.get(myList);
			assertNull("When checking the value of last we", lastValue);
			
			Field numElements = c.getDeclaredField("numElements");
			numElements.setAccessible(true);
			int numElementsValue = (int) numElements.get(myList);
			assertEquals("When checking the value of numElements we", 0, numElementsValue);

		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	@GradedTest(name="Test add to an empty list", max_score=5)
	public void testGetFirst() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("Will",list.getFirst().getValue());
		validateListState(list, expectedElements);
	}
	
	@Test
	@GradedTest(name="Test add to an empty list", max_score=5)
	public void testGetLast() {
		String[] givenElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		String[] expectedElements = {"Will","Dustin","Lucas","Mike","Eleven"};
		
		populateList(givenElements);
		assertEquals("Eleven",list.getLast().getValue());
		validateListState(list, expectedElements);
	}
	
	private <T> BridgesDoublyLinkedList<T> createList(T[] elements) {
		//Create nodes and link them
		DLelement<T> head = null;
		DLelement<T> currentNode = null;
		if(elements.length > 0) {
			head = new DLelement<T>(elements[0].toString(),elements[0]);
			currentNode = head;
			for(int i = 1; i < elements.length; i++) {
				DLelement<T> newNode = new DLelement<T>(elements[i].toString(),elements[i]);
				currentNode.setNext(newNode);
				newNode.setPrev(currentNode);
				currentNode = newNode;
			}
		}
		//Create a BridgesDoublyLinkedList object, bind the created DLelement list to it, return it
		BridgesDoublyLinkedList<T> myList = new BridgesDoublyLinkedList<T>();
		@SuppressWarnings("rawtypes")
		Class c = myList.getClass();
		
		try {
			Field first = c.getDeclaredField("first");
			first.setAccessible(true);
			first.set(myList, head);

			Field last = c.getDeclaredField("last");
			last.setAccessible(true);
			last.set(myList, currentNode);
			
			Field numElements = c.getDeclaredField("numElements");
			numElements.setAccessible(true);
			numElements.set(myList, elements.length);

		} catch (Exception e) {
			fail(e.toString());
		}
		return myList;
	}

	@SuppressWarnings("unchecked")
	private <T> void validateListState(BridgesDoublyLinkedList<T> aList, T[] expectedElements) {
		//If we expect an empty list, we need to take some extra steps in our test for robustness 
		//First, we're gonna check if we expect an empty list and store a boolean value indicating that information
		boolean emptyList = (expectedElements.length == 0) ? true : false;
		//Second, we're gonna set our empty expected elements array to an array with a single element, and that element is null
			//We do this because the expected value of first and last are null in this case 
		if(emptyList) {expectedElements = (T[]) new Object[1]; expectedElements[0] = null; }
		//Third, were gonna add a ternary operator to the assertEqual calls for each instance variable 
			//If emptyList is true, we know to check for what we would expect when the list is empty. 
			//These ternary operators are necessary to avoid null pointer exceptions 
		@SuppressWarnings("rawtypes")
		Class c = aList.getClass();
		try {
			Field first = c.getDeclaredField("first");
			first.setAccessible(true);
			DLelement<T> firstNode = (DLelement<T>) first.get(aList);
			assertEquals("When checking the value of first we", expectedElements[0] ,(emptyList) ? null : firstNode.getValue());
			
			Field last = c.getDeclaredField("last");
			last.setAccessible(true);
			DLelement<T> lastNode = (DLelement<T>) last.get(aList);
			assertEquals("When checking the value of last we", expectedElements[expectedElements.length-1] ,(emptyList) ? null : lastNode.getValue());
			
			Field numElements = c.getDeclaredField("numElements");
			numElements.setAccessible(true);
			int numElementsValue = (int) numElements.get(aList);
			assertEquals("When checking the value of numElements we", (emptyList) ? 0 : expectedElements.length, numElementsValue);

			//Fourth, if the instance variables are valid and we're dealing with an empty list, there's nothing left to validate
				//(can't check an empty list for correctness)
				//So if we have an empty list we return at this point 
			if(emptyList) {
				return;
			}
			
			//Check that the links for first to last are valid and that the values in those nodes are correct 
			DLelement<T> currentNode = firstNode;
			int i = 0;
			while(currentNode != null) {
				assertEquals("(while traversing from head to tail) At index " + i + "we", expectedElements[i], currentNode.getValue());
				currentNode = currentNode.getNext();
				i++;
			}
			//If there are less nodes then i will be less than expectedElements.length and throw an assert
			assertEquals("When checking the number of nodes traversed (from head to tail) we", expectedElements.length, i);
			
			//Check that the links from last to first are valid
			currentNode = lastNode;
			int j = expectedElements.length; 
			while(currentNode != null) {
				j--;
				assertEquals("(while traversing from tail to head) At index " + j + "we", expectedElements[j], currentNode.getValue());
				currentNode = currentNode.getPrev();
			}
			//If there are less nodes then j will be less than expectedElements.length and throw an assert
			assertEquals("When checking the number of nodes traversed (from tail to head) we", expectedElements.length, (expectedElements.length-j));
			
			//If there are more nodes than expected elements catch exception
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			fail("When checking the number of nodes in the list we expected " + expectedElements.length + " but was less");
		}
		catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail(e.toString());
		} 
	}


}
