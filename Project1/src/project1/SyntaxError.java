/**
 * 
 */
package project1;

/**
 * Filename: SyntaxError.java
 * @author William Weir
 * Date: Jan 19, 2022
 * Description: Custom Exception that allows for specifying error message
 */

@SuppressWarnings("serial")
public class SyntaxError extends Exception {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SyntaxError(String message) {
		this.setMessage(message);
	}

}
