//import java.util.EmptyStackException;
/*
 * Netta Paulson
 * Project 3: Stacks 
 * This project creates a generic stack, an infix calculator, and a bridges stack.
 * MyStack.java 
 * This class creates generic stack that can add, remove, look at top element, check if empty, clear, and create a string of elements.
 * 3/14/19
 * CMSC 256, 003
 */

public class MyStack<E> implements StackInterface<E> {
	/*
	 * Instance variables for MyStack
	 */
	private Node top;
	private int numElements;

	/*
	 * This method pushes/adds the given element onto the top of the stack
	 * 
	 * @param E newEntry the new element that will be added
	 */
	@Override
	public void push(E newEntry) {

		if (isEmpty()) {
			Node newNode = new Node(newEntry);
			top = newNode;
		} else {
			Node newNode = new Node(newEntry, top);
			newNode.setNextNode(top);
			top = newNode;

		}
		numElements++;
	}

	/*
	 * This method pops/removes the element at the top of the stack if the stack is
	 * not empty
	 * 
	 * @return E tempValue the element that is pop/removed
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			E tempValue = top.getData();
			top = top.next;
			numElements--;
			return tempValue;
		}
	}

	/*
	 * This method shows the element at the top of the stack if the stack is not
	 * empty
	 * 
	 * @return E top.getData() the element at the top of the stack
	 */
	@Override
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return top.getData();

	}

	/*
	 * This method checks if the stack is empty
	 * 
	 * @return boolean true or false depending on the state of the stack
	 */
	@Override
	public boolean isEmpty() {
		if ((numElements == 0) || (top == null)) {
			return true;
		}
		return false;
	}

	/*
	 * This method clears the stack
	 */
	@Override
	public void clear() {
		top = null;
		numElements = 0;
	}

	/*
	 * This method creates a string of the elements in the stack
	 * 
	 * @return String aString final string with stack elements and | separators
	 * added
	 */
	public String toString() {
		String aString = "TOP:";
		Node current = top;
		for (int i = 0; i < numElements; i++) {
			aString += current.getData();
			if (i + 1 != numElements) {
				aString += "|";
			}
			current = current.getNextNode();
		}
		return aString;
	}

	/*
	 * This is the nested Node class within MyStack
	 */
	class Node {
		/*
		 * Instance variables for Node
		 */
		private E data;
		private Node next;

		/*
		 * Parameterized constructor for Node with only one entry
		 */
		private Node(E newEntry) {
			data = newEntry;
			next = null;
		}

		/*
		 * Parameterized constructor for Node with reference to next node
		 */
		private Node(E dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		/*
		 * This method gets data
		 * 
		 * @returns E data
		 */
		private E getData() {
			return data;
		}

		/*
		 * This method sets data
		 * 
		 * @param E newData
		 */
		private void setData(E newData) {
			data = newData;
		}

		/*
		 * This method gets the next node in the stack
		 * 
		 * @returns Node next
		 */
		private Node getNextNode() {
			return next;
		}

		/*
		 * This method sets the next node in the stack
		 * 
		 * @param Node nextNode
		 */
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
}
