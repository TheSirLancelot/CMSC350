/**
 * 
 */
package com.Week1;

/**
 * @author William Weir
 *
 */
public class Compare {

	public static long f(int x) {
		//f(x)=350x^2+25x+3500
		long answer = 350*x*x+25*x+3500;
		return answer;
	}
	
	public static long g(int x) {
		//g(x)=5x^3
		long answer = 5*x*x*x;
		return answer;
	}
	
	
	public static void main(String[] args) {
		//starting value of x
		int startingValue = 10;
		
		while (f(startingValue)>g(startingValue)) {
			System.out.println(
					"x: " + startingValue + 
					" f(x): " + f(startingValue) + 
					" g(x): " + g(startingValue));
			startingValue += 10;
		}
		
		//printing result
		System.out.println("g(x) passes f(x) at: " + startingValue);
	}

}
