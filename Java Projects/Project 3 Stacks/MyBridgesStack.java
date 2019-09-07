//import java.io.IOException;
//import java.util.EmptyStackException;
//import bridges.connect.Bridges;
//import bridges.validation.RateLimitException;
//import bridges.base.SLelement;

/*
 * Netta Paulson
 * Project 3: Stacks 
 * This project creates a generic stack, an infix calculator, and a bridges stack.
 * MyBridgesStack.java 
 * This class creates a bridges stack that can add, remove, look at top element, check if empty, clear,
 * create a string of elements, and visualize the stack.
 * 3/14/19
 * CMSC 256, 003
 */
public class MyBridgesStack<E> {
	/*
	 * Instance variables for MyBridgesStack
	 */
	SLelement<E> top;
	int numElements = 0;
	
	/*
	 * This method pushes/adds the given entry onto the top of the stack
	 * 
	 * @param E newEntry 	the new entry that will be added
	 */
	public void push(E newEntry) {
		if (isEmpty()) {
			SLelement<E> element = new SLelement<E>(newEntry);
			top = element;
		} else {
			SLelement<E> element = new SLelement<E>(newEntry, top);
			element.setNext(top);
			top = element;
		}
		numElements++;
	}
	
	/*
	 * This method pops/removes the element at the top of the stack if the stack is not empty
	 * 
	 * @return E tempValue	 the element that is pop/removed
	 */
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			E tempValue = top.getValue();
			top = top.getNext();
			numElements--;
			return tempValue;
		}
	}

	/*
	 * This method shows the element at the top of the stack if the stack is not empty
	 * 
	 * @return E top.getData()	 the element at the top of the stack
	 */
	public E peek() {
		if ((isEmpty()) || (top == null)) {
			throw new EmptyStackException();
		}
		return top.getValue();

	}

	/*
	 * This method checks if the stack is empty
	 * 
	 * @return boolean true or false 	depending on the state of the stack
	 */
	public boolean isEmpty() {
		if ((numElements == 0) || (top == null)) {
			return true;
		}
		return false;
	}
	
	/*
	 * This method clears the stack
	 */
	public void clear() {
		top = null;
		numElements = 0;
	}
	
	/*
	 * This method creates a string of the elements in the stack
	 * 
	 * @return String aString final string with stack elements and | separators added
	 */
	public String toString() {
		String aString = "TOP:";
		SLelement current = top;
		for (int i = 0; i < numElements; i++) {
			aString += current.getValue();
			if (i + 1 != numElements) {
				aString += "|";
			}
			current = current.getNext();
		}
		return aString;
	}

	/*
	 * This method visualizes the stack by outputting a bridges link
	 */
	public void visualize() throws IOException, RateLimitException {
		Bridges bridges = new Bridges(1, "paulsonnt", "82339506885");
		bridges.setTitle("BRIDGES Stack");
		bridges.setDataStructure(top);
		bridges.visualize();
	}

	/*
	 * This is the main method
	 * 
	 * A new stack was created, strings were pushed onto the stack, the stack was visualized
	 * and strings were popped off the stack
	 */
	public static void main(String[] args) throws IOException, RateLimitException {
		MyBridgesStack<String> aStack = new MyBridgesStack<String>();
		aStack.push("Red");
		aStack.push("Yellow");
		aStack.push("Purple");
		aStack.push("Green");
		aStack.push("Blue");
		aStack.visualize();
		aStack.pop();
		aStack.pop();
		aStack.pop();
	}
}
