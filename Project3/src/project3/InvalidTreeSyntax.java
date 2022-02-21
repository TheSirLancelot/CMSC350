package project3;

/**
 * Filename: InvalidTreeSyntax.java
 * 
 * @author William Weir 
 * Date: Feb 21, 2022 
 * Description: Custom exception for BinaryTree syntax
 */

@SuppressWarnings("serial")
public class InvalidTreeSyntax extends Exception {

	public InvalidTreeSyntax() {
		super();
	}

	public InvalidTreeSyntax(String message) {
		super(message);
	}
}