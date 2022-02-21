package project3;

import java.util.EmptyStackException;
import java.util.Stack;


/**
 * Filename: BinaryTree.java
 * 
 * @author William Weir 
 * Date: Feb 21, 2022 
 * Description: Class that handles all BinaryTree logic
 */

public class BinaryTree {

	// class variables
	Node parent, child;

	// Constructor that takes a string and parses it 
	public BinaryTree(String input) throws InvalidTreeSyntax {
		
		// stack for storing our Nodes
		Stack<Node> nodeStack = new Stack<>();
		
		// remove the first and last parenthesis
		String[] inputArray = input.substring(1, input.length() - 1) 
				// split on the parenthesis and make an array of strings
				.split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
		
		// setting the root of the tree
		parent = new Node(inputArray[0]);
		
		// look inside the next set of parenthesis
		for (int i = 1; i < inputArray.length - 1; i++) {
			
			// Means there is another child. Child becomes parent if one exists
			if (inputArray[i].equals("(")) {
				nodeStack.push(parent);
				if (child != null) {
					parent = child;
				}
			
			// assign the current parent as the child of one on the stack
			} else if (inputArray[i].equals(")")) {
				try {
					child = parent;
					parent = nodeStack.pop();
				} catch (EmptyStackException emptyStack) {
					throw new InvalidTreeSyntax("Incorrect parenthesis!");
				}
			
			//if it gets here, it is a value to be assigned as child to a parent
			} else {
				child = new Node(inputArray[i]);
				if (parent != null) {
					parent.addChild(child);
				} // addChild with throw InvalidTreeSyntax
			}
			
		} // for every node, you should have 2 parenthesis
		if (this.recNodes(parent) * 3 != input.length())
			throw new InvalidTreeSyntax("Incorrect Syntax, check your parens!");
	}

	
	// method to check whether the tree is balanced or not
	// calls recursive method, which also calls the recursive height method.
	public boolean isBalanced() {
		return recIsBalanced(this.parent);
	}

	// recursive method to check if tree is balanced
	private boolean recIsBalanced(Node root) {
		//base case
		if (root == null) {
			return true;
		}
		//return true if the absolute difference is at most 1
		System.out.println(Math.abs(recHeight(root.left) - recHeight(root.right)));
		System.out.println(root);
		return (Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1)
				&& (recIsBalanced(root.left) && recIsBalanced(root.right)); // and calls recursively
	}

	
	// method to check whether the tree is full or not
	// calls the recursive method, which also calls the recursive height method
	public boolean isFull() {
		return recIsFull(this.parent, recHeight(this.parent), 0);
	}

	//the index of parent is 0
	private boolean recIsFull(Node root, int height, int index) {

		//if it is empty, it is full
		if (root == null) {
			return true;
		}

		//check to see if height is same among leaves
		if (root.left == null && root.right == null) {
			return (height == index + 1);
		}
		
		//one child empty
		if (root.left == null || root.right == null) {
			return false;
		}
		
		//recursive call to both children
		return recIsFull(root.left, height, index + 1) && recIsFull(root.right, height, index + 1);

	}

	
	// method to check whether the binary tree is proper or not
	// calls the recursive method
	public boolean isProper() {
		return recIsProper(this.parent);
	}

	// the recursive method
	private boolean recIsProper(Node root) {
		
		//base case
		if (root == null) {
			return true;
		}
		
		//returns true or false based on two or zero children
		return ((root.left != null || root.right == null) && (root.left == null || root.right != null))
				&& (recIsProper(root.left) && recIsProper(root.right)); // and calling recursively
	}


	// method to get the height of the binary tree
	// calls the recursive method
	public int height() {
		return recHeight(this.parent) - 1;
	}

	//subtracted one since root is 0
	private int recHeight(Node root) {

		//adds one to the greater of left and right, zero if null
		return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));
	}


	// method to find the amount of nodes in the tree
	// calls the recursive method 
	public int nodes() {
		return recNodes(this.parent);
	}

	// the recursive method
	private int recNodes(Node root) {

		//similar to recHeight, but adds one for both left and right. If null, zero
		return (root == null) ? 0 : 1 + recNodes(root.left) + recNodes(root.right);
	}
	

	// method to print the info of the nodes in order
	// calls the recursive method
	public String inOrder() {
		return recInOrder(this.parent);
	}

	private String recInOrder(Node root) {
		
		//Blank string for nulls
		return (root == null) ? "" : "(" + recInOrder(root.left) + root.info + recInOrder(root.right) + ")";
	}


	// simply calls the the root node toString
	@Override
	public String toString() {
		return parent.toString();
	}

	
	// Nested class for Nodes
	public static class Node {
		
		// class variables
		private String info;
		private Node left;
		private Node right;

		// constructor
		public Node(String info) {
			this.info = info;
		}

		// method to add a child of a Node
		private void addChild(Node child) throws InvalidTreeSyntax {
			
			//simple conditions for nodes, can have at most 2 children
			if (this.left == null) {
				this.setLeft(child);
			} else if (this.right == null) {
				this.setRight(child);
			} else {
				throw new InvalidTreeSyntax("Nodes can only have 2 children!");
			}
		}

		//setters for the nodes
		private void setLeft(Node newLeft) {
			left = newLeft;
		}

		private void setRight(Node newRight) {
			right = newRight;
		}

		@Override
		public String toString() {
			return toString(this);
		}

		// recursively printing out the nodes
		private static String toString(Node root) {
			return (root == null) ? "" : "(" + root.info + toString(root.left) + toString(root.right) + ")";
		}
	}
}