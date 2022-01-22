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
		Stack<String> reversalStack = new Stack<String>();
		Stack<String> operandStack = new Stack<String>();
		StringTokenizer tokenizer;
		String output = "";
		
		//Nothing was written in the expression field
		if(equation.isBlank()) {
			throw new SyntaxError("Expression Field is Blank");
		} else {
			//Length of equation that was submitted
			//equationLength = equation.length();
			tokenizer = new StringTokenizer(
						equation,
						"+-*/ ", 
						true
					);
		}
		
		//Postfix to Prefix 
		if(direction.equals("PostToPre")) {
			//parse the equation
			while(tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				if(!Character.isSpaceChar(token.charAt(0))) {
					if(isOperator(token.charAt(0))) {
						System.out.println("is operator");
						//pop two operands off the operand stack
						String operand1 = operandStack.peek();
						operandStack.pop();
						String operand2 = operandStack.peek();
						operandStack.pop();
						
						//concatenate the operands and operator 
						//and push them onto the main stack
						operandStack.push(
								token + 
								" " + 
								operand2 + 
								" " +
								operand1
								);
					//else it's an operand
					} else {
						System.out.println("is operand");
						operandStack.push(token);
					}
				}
			}
			
			//output operator stack as a string
			output = operandStack.peek();
			operandStack.pop();
			
			//check if stack is empty
			if(!operandStack.isEmpty()) {
				throw new SyntaxError("Stack not empty post-conversion");
			}
			
			return output;
			
			
		//Prefix to Postifx
			//* 2 + 2 - + 12 9 2
		} else if (direction.equals("PreToPost")) {
			//iterate through the equation and reverse it onto the reversalStack
			while(tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				//if the character is NOT a space
				if(!Character.isSpaceChar(token.charAt(0))) {
					//push it to the reversal stack
					System.out.println("pushing token onto the stack:" + token);
 					reversalStack.push(token);
				}
			}
			
			while(!reversalStack.isEmpty()) {
				String token = reversalStack.peek();
				System.out.println("operating on token:" + token);
				//check if the token is an operator
				if(isOperator(token.charAt(0))) {
					System.out.println("is operator");
					//pop two operands off the operand stack
					String operand1 = operandStack.peek();
					operandStack.pop();
					String operand2 = operandStack.peek();
					operandStack.pop();
					
					//concatenate the operands and operator 
					//and push them onto the main stack
					operandStack.push(
							operand1 + 
							" " + 
							operand2 + 
							" " + 
							token);
					reversalStack.pop();
					
				//else it's an operand
				} else {
					System.out.println("is operand");
					operandStack.push(token);
					reversalStack.pop();
				}
			}
			
			//output operator stack as a string
			output = operandStack.peek();
			operandStack.pop();
			
			//check if stack is empty
			if(!operandStack.isEmpty()) {
				throw new SyntaxError("Stack not empty post-conversion");
			}

			return output;
			
			
		//There was an error and neither postfix or prefix was requested
		} else {
			throw new SyntaxError("Neither post nor pre");
		}
	}
	
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

}