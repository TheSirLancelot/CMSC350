/**
 * 
 */
package project2;

// Imports
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Filename: Polynomial.java
 * 
 * @author William Weir 
 * Date: Jan 31, 2022 
 * Description: Class that contains all Polynomial functionality
 */

public class Polynomial implements Iterable<Polynomial.Term>, Comparable<Polynomial> {

	// Class variables
	Comparator<Polynomial> compare;
	private Term head;

	// Polynomial constructor
	public Polynomial(String stringFromInputFile) {

		head = null; // explicitly stating for clarity
		Scanner termScanner = new Scanner(stringFromInputFile);
		
		// Read each line and parse the polynomial
		try {
			while (termScanner.hasNext()) {
				// add the next double and next integer combo
				addTerm(termScanner.nextDouble(), termScanner.nextInt());
			}
		} catch (Exception ex) {
			// catch an exception and throw a syntax error
			System.out.println(ex.getLocalizedMessage());
			termScanner.close();
			throw new InvalidPolynomialSyntax("Incorrect Syntax.");
		}
		
		// Close the scanner so you don't get a resource leak
		termScanner.close();
	}

	// Method to parse a new term and add it to the linked list
	public void addTerm(double coefficient, int exponent) {

		// exponent is less that 0
		if (exponent < 0) {
			throw new InvalidPolynomialSyntax("No negative exponents!");
		}
		
		// We set head to null originally, so if it's null, this is the first 
		Term current = head;
		
		// Setting up the term's next link
		if (current == null) { // then Polynomial is empty
			head = new Term(coefficient, exponent);
			head.next = null;
		} else { // find end by looping to null next link
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Term(coefficient, exponent);
		}
	}

	@Override
	public int compareTo(Polynomial otherPoly) {
		Term thisCurrent = this.head;
		Term otherCurrent = otherPoly.head;
		
		while (thisCurrent != null && otherCurrent != null) {
			// positive if this is larger, negative otherwise
			if (thisCurrent.getExponent() != otherCurrent.getExponent()) {
				return thisCurrent.getExponent() - otherCurrent.getExponent();
				// casting to an int truncates, so simple checking for larger
			} else if (thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
				if (otherCurrent.getCoefficient() > thisCurrent.getCoefficient()) {
					return -1;
				} else if (otherCurrent.getCoefficient() < thisCurrent.getCoefficient()) {
					return +1;
				}
			} // resetting the values outside of the loop
			thisCurrent = thisCurrent.getNext();
			otherCurrent = otherCurrent.getNext();
		} // if both are null, and at this point, they are equal, ret zero
		if (thisCurrent == null && otherCurrent == null) {
			return 0;
		} // this would be the case of one with more terms than other
		if (thisCurrent == null) {
			return -1;
		} else {
			return +1;
		}
	}

	@Override
	public String toString() {
		StringBuilder expressionBuilder = new StringBuilder();

		// first check head to avoid +1x^3 +3x^2
		if (head.coefficient > 0) {
			expressionBuilder.append(head.toString());
		} else {
			expressionBuilder.append(" - ").append(head.toString());
		} // then check the other nodes if they are not null
		for (Term tmp = head.next; tmp != null; tmp = tmp.next) {
			if (tmp.coefficient < 0) {
				expressionBuilder.append(" - ").append(tmp.toString());
			} else {
				expressionBuilder.append(" + ").append(tmp.toString());
			}
		}
		return expressionBuilder.toString();
	}

	public class Term {
		private double coefficient;
		private int exponent;
		private Term next;

		private Term(double coefficient, int exponent) {
			this.coefficient = coefficient;
			this.exponent = exponent;
			this.next = null; // explicitly setting to null
		}

		private int getExponent() {
			return this.exponent;
		}

		private double getCoefficient() {
			return this.coefficient;
		}

		private Term getNext() {
			return next;
		}

		@Override
		public String toString() {
			String termString = String.format("%.1f", Math.abs(coefficient));

			if (exponent == 0) { // no variable
				return termString;
			} else if (exponent == 1) { // do not display exponent
				return termString + "x";
			} else { // display exponent after variable
				return termString + "x^" + exponent;
			}
		}
	}

	@Override
	public Iterator<Term> iterator() {
		return new Iterator<Term>() {
			private Term current = getHead();

			@Override
			public boolean hasNext() {
				return current != null && current.getNext() != null;
			}

			@Override
			public Term next() {
				Term data = current;
				current = current.next;
				return data;
			}
		};
	}

	public Term getHead() {
		return head;
	}

}
