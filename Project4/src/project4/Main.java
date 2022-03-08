package project4;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Filename: Main.java
 * 
 * @author William Weir 
 * Date: 8 March, 2022 
 * Description: Main class 
 */
public class Main {

	static DirectedGraph graph = new DirectedGraph();

	// method to take input using JFileChooser
	public void readGraph() {

		// Open file reader window
		JFileChooser choice = new JFileChooser(new File("."));
		int option = choice.showOpenDialog(null);

		//check if user selected a valid file then proceed further
		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				// Take input from user input file using Scanner class from Java Utility
				Scanner input = new Scanner(choice.getSelectedFile());

				// Read the file line by line
				while (input.hasNextLine()) {
					String edgeString = input.nextLine();
					String[] edge = edgeString.split(" ");

					// Marks the first node of the graph
					// DFS starts from this node
					if (graph.startingNode == null)
						// this line will be executed only once
						graph.startingNode = graph.getVertex(edge[0]);

					// add edges to the Directed graph
					// First node of the Line ---------------> All other nodes
					for (int i = 1; i < edge.length; i++) {
						graph.addEdge(edge[0], edge[i]);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// main method
	public static void main(String[] args) {

		// Initializing Main Class
		new Main().readGraph();

		// Starting Depth First Search Utility to complete the DFS
		graph.depthFirstSearch();
		
		// Display Hierarchy after processing the vertices
		System.out.println(graph.hierarchy.toString());

		// Display Parenthesized List after processing the vertices
		System.out.println(graph.parenthesizedList.toString());

		// Display all the nodes that remained unreachable in the searching process
		graph.displayUnreachableClasses();
	}
}