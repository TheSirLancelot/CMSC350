package project2;

/**
 * Filename: InvalidPolynomialSyntax.java
 * 
 * @author William Weir 
 * Date: Jan 31, 2022 
 * Description: Custom exception for invalid polynomial syntax
 */

@SuppressWarnings("serial")
public class InvalidPolynomialSyntax extends RuntimeException {
	
	// using the super constructor 
	InvalidPolynomialSyntax(String msg){
		super(msg);
	}

}