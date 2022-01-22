/**
 * 
 */
package project1;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Filename: ExpressionParser.java
 * @author William Weir
 * Date: Jan 19, 2022
 * Description: 
 */
public class ExpressionParser {
	
	public static String evaluate(String equation, String direction) 
			throws SyntaxError {
		
		//Variable(s)
		Stack<String> workingStack = new Stack<String>();
		Stack<String> operandStack = new Stack<String>();
		StringTokenizer tokenizer;
		String output = "";
		boolean needsReversing = false;
		
		//Nothing was written in the expression field
		if(equation.isBlank()) {
			throw new SyntaxError("Expression Field is Blank");
		} else {
			//create a new string tokenizer with our supplied delimiters
			tokenizer = new StringTokenizer(
						equation,
						"+-*/ ", 
						true
					);
		}
		
		//check to see if we need to reverse the stack
		if(direction.equals("PostToPre")) {
			//Postfix to prefix does not need to use a reverse stack
			needsReversing = false;
		} else if(direction.equals("PreToPost")) {
			//Prefix to postfix requires a reverse stack
			needsReversing = true;
		}
		
		//set up our working stack. I went with two stacks for each 
		//conversion to cut down on code duplication
		workingStack = tokenizeToStack(tokenizer, needsReversing);

		//iterate through the working stack
		while(!workingStack.isEmpty()) {
			//set token equal to the token on the top of the stack
			String token = workingStack.peek();
			//check if the token is an operator
			if(isOperator(token.charAt(0))) {
				//pop two operands off the operand stack
				String operand1 = operandStack.peek();
				operandStack.pop();
				String operand2 = operandStack.peek();
				operandStack.pop();
				
				//concatenate the operands and operator 
				//and push them onto the main stack (depending on which 
				//button was pressed)
				if(direction.equals("PreToPost")) {
					operandStack.push(
							operand1 + 
							" " + 
							operand2 + 
							" " + 
							token);
				} else {
					operandStack.push(
					token + 
					" " + 
					operand2 + 
					" " +
					operand1
					);
				}
			//else it's an operand
			} else {
				operandStack.push(token);
			}
			//next token
			workingStack.pop();
		}
		
		//output operator stack as a string
		output = operandStack.peek();
		operandStack.pop();
		
		//check if stack is empty
		if(!operandStack.isEmpty()) {
			throw new SyntaxError("Stack not empty post-conversion");
		}
		
		return output;
	}
	
	/**
	 * @param x
	 */
	static boolean isOperator(char x) {
		switch(x) {
		case '+':
		case '-':
		case '/':
		case '*':
			return true;
		}
		return false;
	}
	
	/**
	 * @param tokenizer
	 * @param needsReversing
	 */
	static Stack<String> tokenizeToStack(
			StringTokenizer tokenizer, boolean needsReversing) {
		Stack<String> workingStack = new Stack<String>();		
		Stack<String> tempStack = new Stack<String>();		
		
		//check if we need to reverse it, if we don't we just parse the
		//string into a stack. If we do, we reverse it into a stack
		
		//we reverse by default then correct the order if we don't need it
		//iterate through the equation and reverse it onto the tempStack
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			//if the character is NOT a space
			if(!Character.isSpaceChar(token.charAt(0))) {
				//push it to the working stack
				tempStack.push(token);
			}
		}
		if(needsReversing) {
			//if we needed to reverse it, it's now reversed and on a stack
			return tempStack;
		} else {
			while(!tempStack.isEmpty()) {
				//iterate through the temp stack and push them to the working 
				//stack, essentially fixing the order
				workingStack.push(tempStack.peek());
				tempStack.pop();
			}
			return workingStack;
		}

	}

}