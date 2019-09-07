import java.io.IOException;
import bridges.base.DLelement;
import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

/**
 * @author < Netta Paulson >
 *  Computer Science Department
 *  College of Engineering
 *  Virginia Commonwealth University
 *  Project 4: BridgesDoublyLinkedList
 *  This project creates a generic doubly linked list that can complete all the methods described in MyListInteface.
 *  4/9/19
 *  CMSC 256, 003
 */

public class BridgesDoublyLinkedList<E> implements MyListInterface<E> {

	/*
	 * Instance variables
	 */
	private DLelement<E> last;
	private DLelement<E> first;
	private int numElements;

	/*
	 * Main method that adds 20 elements and tests every method except clear.
	 */
	public static void main(String[] args) {
		BridgesDoublyLinkedList<String> colors = new BridgesDoublyLinkedList<String>();
		colors.add("Red");
		colors.add("Orange");
		colors.add("Yellow");
		colors.add("Green");
		colors.add("Blue");
		colors.add("Indigo");
		colors.add("Violet");
		colors.add("Pink");
		colors.add("Black");
		colors.add("White");
		colors.add("Purple");
		colors.add("Magenta");
		colors.add("Silver");
		colors.add("Turquiose");
		colors.add("Teal");
		colors.add("Brown");
		colors.add("Navy");
		colors.add("Gray");
		colors.add("Peach");
		colors.add("Gold");
		colors.add(8, "Cream");
		colors.remove(15);
		colors.remove("Silver");
		colors.replace(5, "Seagreen");
		colors.getEntry(18);
		colors.toArray();
		colors.contains("Purple");
		colors.getLength();
		colors.isEmpty();
		colors.indexOf("Gold");
		colors.lastIndexOf("Pink");
		colors.getNodeAt(12);
		colors.visualize();
	}
	
	/*
	 * Default constructor
	 */
	public void BridgesDoublyLinkedList() {
		first = null;
		last = null;
		numElements = 0;
	}

	/*
	 * This method adds an element into the list
	 * 
	 * @param E element		the given element
	 */
	@Override
	public void add(E element) {
		DLelement<E> temp = new DLelement<E>(null, element);
		if (first == null) {
			first = temp;
			last = first;
		} else {
			last.setNext(temp);
			temp.setPrev(last);
			last = temp;
		}
		numElements++;
	}
	
	/*
	 * This method adds an element into the list as a specific location
	 * 
	 * @param int index		the given location
	 * @param E element		the given element
	 */
	@Override
	public void add(int index, E element) {
		DLelement<E> temp = new DLelement<E>(null, element);
		if (index < 0 || index > numElements) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			if (isEmpty()) {
				first = temp;
				last = first;
				numElements++;
			} else {
				first.setPrev(temp);
				temp.setNext(first);
				first = temp;
				numElements++;
			}
		} else if (index == numElements) {
			add(element);
		} else {
			DLelement<E> current = first;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			DLelement<E> previous = current.getPrev();
			previous.setNext(temp);
			temp.setPrev(previous);
			temp.setNext(current);
			current.setPrev(temp);
			numElements++;
		}
	}

	/*
	 * This method removes the element at a specific index
	 * 
	 * @param int index			the given location in the list
	 * 
	 * @return E removedEntry	the element that is removed
	 */
	@Override
	public E remove(int index) {
		E removedEntry = null;
		if (index < 0 || index >= numElements) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			removedEntry = getEntry(0);
			first = first.getNext();
			first.setPrev(null);
			numElements--;
			return removedEntry;
		} else if (index == numElements - 1) {
			removedEntry = getEntry(numElements - 1);
			last = last.getPrev();
			last.setNext(null);
			numElements--;
			return removedEntry;
		} else {
			DLelement<E> current = first;
			removedEntry = getEntry(index);
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			DLelement<E> previous = current.getPrev();
			DLelement<E> next = current.getNext();
			previous.setNext(current.getNext());
			next.setPrev(previous);
			numElements--;
			return removedEntry;
		}
	}
	/*
	 * This method removes the the specified element
	 * 
	 * @param E element			the given element in the list
	 * 
	 * @return boolean 			if the element was removed or not
	 */
	@Override
	public boolean remove(E element) {
		boolean found = false;
		int index = 0;
		DLelement<E> current = first;
		for (int i = 0; i < numElements; i++) {
			if (current.getValue().equals(element)) {
				index = i;
				found = true;
			}
			current = current.getNext();
		}
		if (found == true) {
			E removedValue = remove(index);
			return true;
		}
		return false;
	}

	/*
	 * This method clears the list and sets numElements to 0
	 */
	@Override
	public void clear() {
		first = null;
		last = null;
		numElements = 0;
	}

	/*
	 * This methods replaces a elements in the list at a given location
	 * 
	 * @param int givenPosition		the given location in the list
	 * @param E newEntry			the new element to be added into the list
	 * 
	 * @return E 			the element at the given position after it has been replaced
	 */
	@Override
	public E replace(int givenPosition, E newEntry) {
		DLelement<E> temp = new DLelement<E>(null, newEntry);
		if (givenPosition < 0 || givenPosition >= numElements) {
			throw new IndexOutOfBoundsException();
		}
		if (givenPosition == 0) {
			first = temp;
		} else if (givenPosition == numElements - 1) {
			DLelement<E> previous = last.getPrev();
			previous.setNext(temp);
			temp.setPrev(previous);
			last = temp;
		} else {
			DLelement<E> current = first;
			for (int i = 0; i < givenPosition; i++) {
				current = current.getNext();
			}
			DLelement<E> previous = current.getPrev();
			previous.setNext(temp);
			temp.setPrev(previous);
			DLelement<E> next = current.getNext();
			next.setPrev(temp);
			temp.setNext(next);
		}
		return getEntry(givenPosition);
	}

	/*
	 * This method retrieves the entry at a given location
	 * 
	 * @param int givenPosition		the location in the list
	 * 
	 * @return	E getEntry			the element at the given position
	 */
	@Override
	public E getEntry(int givenPosition) {
		DLelement<E> current = first;
		if ((givenPosition < 0) || (givenPosition >= numElements)) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < givenPosition; i++) {
			current = current.getNext();
		}
		return current.getValue();
	}

	/*
	 * This method gets the last element in the list
	 * 
	 * @return DLelement<E> last 		the variable that maintains the end of the list
	 */
	@Override
	public DLelement<E> getLast() {
		return last;
	}
	/*
	 * This method gets the first element in the list
	 * 
	 * @return DLelement<E> first 		the variable that maintains the beginning of the list
	 */
	@Override
	public DLelement<E> getFirst() {
		return first;
	}

	/*
	 * This method converts the list into an array
	 * 
	 * @return E[] newArray		the array filled with the list elements
	 */
	@Override
	public E[] toArray() {
		E[] newArray = (E[]) new Object[numElements];

		DLelement<E> current = first;
		for (int i = 0; i < numElements; i++) {
			if (i == 0) {
				newArray[i] = current.getValue();
			} else {
				current = current.getNext();
				newArray[i] = current.getValue();
			}
		}
		return newArray;
	}

	/*
	 * This method checks to see if the list contains the given entry
	 * 
	 * @param E anEntry		the given element
	 * 
	 * @return boolean		whether the given element was found or not
	 */
	@Override
	public boolean contains(E anEntry) {
		DLelement<E> current = first;
		for (int i = 0; i < numElements; i++) {
			if (current.getValue().equals(anEntry)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	/*
	 * This method gets the length of the list
	 * 
	 * @return int numElements		the variable that maintains the length of the list
	 */
	@Override
	public int getLength() {
		return numElements;
	}

	/*
	 * This method checks to see if the list is empty
	 * 
	 * @return boolean		whether the list is empty or not
	 */
	@Override
	public boolean isEmpty() {
		if (numElements == 0) {
			return true;
		}
		return false;
	}

	/*
	 * This method get the index of the first instance of a given element within the list
	 * The method returns -1 if the element is not found
	 * 
	 * @param E element 	the given element to search for
	 * 
	 * @return int index	the index of the element
	 */
	@Override
	public int indexOf(E element) {
		DLelement<E> current = first;
		int index = -1;
		for (int i = 0; i < numElements; i++) {
			if (current.getValue().equals(element)) {
				return index = i;
			}
			current = current.getNext();
		}
		return index;
	}
	/*
	 * This method get the index of the last instance of a given element within the list
	 * The method returns -1 if the element is not found
	 * 
	 * @param E element 	the given element to search for
	 * 
	 * @return int index	the index of the element
	 */
	@Override
	public int lastIndexOf(E element) {
		DLelement<E> current = last;
		int index = -1;
		for (int i = numElements - 1; i > 0; i--) {
			if (current.getValue().equals(element)) {
				return index = i;
			}
			current = current.getPrev();
		}
		return index;
	}

	/*
	 * This method retrieves that node at a given position
	 * 
	 * @param int givenPosition			the location within the list
	 * 
	 * @return	DLelement<E> current		the node at the given location
	 */
	@Override
	public DLelement<E> getNodeAt(int givenPosition) {
		DLelement<E> current = first;
		if ((givenPosition < 0) || (givenPosition >= numElements)) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < givenPosition; i++) {
			current = current.getNext();
		}
		return current;
	}

	/*
	 * This method visualized the list
	 */
	public void visualize() {
		Bridges bridges = new Bridges(1, "paulsonnt", "82339506885");
		bridges.setTitle("BRIDGES Doubly Linked List");
		bridges.setDataStructure(first);
		try {
			bridges.visualize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RateLimitException e) {
			e.printStackTrace();
		}
	}
}
