//import java.util.EmptyStackException;

/*
 * Netta Paulson
 * Project 3: Stacks 
 * This project creates a generic stack, an infix calculator, and a bridges stack.
 * InfixExpressionEvaluator.java 
 * This class creates takes inputs of a string equation and an int array of variable values and solves the equation
 * and outputs the solution or error messages.
 * 3/14/19
 * CMSC 256, 003
 */
public class InfixExpressionEvaluator<E> extends MyStack<E> {

	/*
	 * This method takes a given string expression and calculates the solution based on precedence
	 * and can operate using variables
	 * 
	 * @param String infix			the given expression to solve
	 * @param int[] values 			the array of values that correspond to variables, if present in the expression
	 * @returns double result		the solution to the given expression
	 */
	public static double evaluateInfix(String infix, int[] values) {
		MyStack<String> operatorStack = new MyStack<String>();
		MyStack<Double> valueStack = new MyStack<Double>();
		String[] infixChars = infix.split(" ");
		infixChars = convertVaribles(infixChars, values);
		String topOperator = "";
		double operandTwo;
		double operandOne;
		double result = 0;
		for (int i = 0; i < infixChars.length; i++) {
			if (infixChars[i].equals("^")) {
				operatorStack.push(infixChars[i]);
			}

			else if ((infixChars[i].equals("+")) || (infixChars[i].equals("-")) || (infixChars[i].equals("*"))
					|| (infixChars[i].equals("/"))) {

				while ((!operatorStack.isEmpty())
						&& (getPrecedence(infixChars[i]) <= getPrecedence(operatorStack.peek()))) {
					topOperator = operatorStack.pop();
					operandTwo = valueStack.pop();
					operandOne = valueStack.pop();
					result = getResult(operandOne, operandTwo, topOperator);
					valueStack.push(result);
				}
				operatorStack.push(infixChars[i]);
			} else if (infixChars[i].equals("(")) {
				operatorStack.push(infixChars[i]);
			} else if (infixChars[i].equals(")")) {
				try {
					if (operatorStack.isEmpty()) {
						topOperator = operatorStack.pop();
					}
				} catch (EmptyStackException e) {
					throw new IllegalStateException();
				}
				topOperator = operatorStack.pop();
				while (!topOperator.equals("(")) {
					operandTwo = valueStack.pop();
					if (!valueStack.isEmpty()) {
						operandOne = valueStack.pop();
						result = getResult(operandOne, operandTwo, topOperator);
						valueStack.push(result);
					}
					try {
						if (operatorStack.isEmpty()) {
							topOperator = operatorStack.pop();
						}
					} catch (EmptyStackException e) {
						throw new IllegalStateException();
					}
					topOperator = operatorStack.pop();
				}
			} else {
				int value = Integer.parseInt(infixChars[i]);
				valueStack.push((double) value);
			}
		}
		while (!operatorStack.isEmpty()) {
			topOperator = operatorStack.pop();
			if (topOperator.equals("(")) {
				throw new IllegalStateException();
			}
			operandTwo = valueStack.pop();
			operandOne = valueStack.pop();
			result = getResult(operandOne, operandTwo, topOperator);
			valueStack.push(result);
		}
		return result;
	}

	/*
	 * This method assigns a numeric value to each operator according to its precedence
	 * 
	 * @param String operator		the operator that is being compared/checked
	 * 
	 * @returns int i				the index of the operator in the array that matches the given operator
	 */
	public static int getPrecedence(String operator) {
		String[] precedence = new String[] { "(", "-", "+", "*", "/", "^" };
		int i = 0;
		for (i = 0; i < precedence.length; i++) {
			if (precedence[i].equals(operator)) {
				return i;
			}
		}
		return i;
	}

	/*
	 * This method checks which operator is used and then solves the given expression accordingly
	 * 
	 * @param double operand1		the first value in the expression
	 * @param double operand2		the second value in the expression
	 * @param String operator 		the operator used in the expression	
	 * 
	 * @returns double result		the solution from solving the expression
	 */
	public static double getResult(double operand1, double operand2, String operator) {
		double result = 0;
		if (operator.equals("+")) {
			result = operand1 + operand2;
		}
		if (operator.equals("-")) {
			result = operand1 - operand2;
		}
		if (operator.equals("*")) {
			result = operand1 * operand2;
		}
		if (operator.equals("/")) {
			result = operand1 / operand2;
		}
		if (operator.equals("^")) {
			result = operand1;
			for (int i = 0; i < operand2 - 1; i++) {
				result = result * operand1;
			}
		}
		return result;
	}

	/*
	 * This method searches the given string and converts the variables to given integer values
	 * 
	 * @param String[] expressionArray		the given string put into a string array
	 * @param int[] valueArray				the given integer array with the integer conversion values
	 * 
	 * @return String[] expressionArray		the changed string array with the variables converted to integers
	 */
	public static String[] convertVaribles(String[] expressionArray, int[] valueArray) {
		String[] variablesLowCase = new String[] { "a", "b", "c", "d", "e", "f" };
		String[] variablesUppCase = new String[] { "A", "B", "C", "D", "E", "F" };
		int index = 0;
		for (int i = 0; i < expressionArray.length; i++) {
			for (int j = 0; j < 6; j++)
				if ((expressionArray[i].equals(variablesLowCase[j]))
						|| (expressionArray[i].equals(variablesUppCase[j]))) {
					index = j;
					expressionArray[i] = Integer.toString(valueArray[j]);
				}
		}
		return expressionArray;
	}
}
