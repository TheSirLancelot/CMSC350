package project4;

import java.util.*;

/**
 * Filename: Graph.java
 * 
 * @author William Weir 
 * Date: 8 March, 2022 
 * Description: Class that handles base graph functionality
 */
public class Graph<V> {
	// Starting node of the graph
	public V startingNode = null;

	// Map all the vertex name (String) to a corresponding Vertex
	Map<String, V> vertices = new HashMap<>();

	// Adjacency representation of the graph
	Map<V, ArrayList<V>> adjacencyList = new HashMap<>();

	// Track if a node/vertex is visited in the searching process
	Set<V> visited = new HashSet<>();

	// Representation utility
	ParenthesizedList parenthesizedList = new ParenthesizedList();
	Hierarchy hierarchy = new Hierarchy();

	// keep track if the graph contains a cycle
	boolean cycle;

	Set<V> discovered = new HashSet<>();
	
	// method to initialize the DFS with all other related attributes
	public void depthFirstSearch() {

		// Marking cycle flag as false
		cycle = false;
		// Starting DFS from the first node of the input data
		dfs(startingNode);
	}

	// method to search in the adjacency list in Depth-First-Order
	private void dfs(V node) {

		// check if the node is already visited and has not completed 
		// discovering it's children yet. If so, a cycle has been detected
		if (discovered.contains(node)) {
			cycle = true;

			// Perform DFS Actions Cycle Detected operation
			hierarchy.cycleDetected();
			parenthesizedList.cycleDetected();
			return;
		}

		// Perform DFS Actions Vertex Add operation
		hierarchy.processVertex((Vertex) node);
		parenthesizedList.processVertex((Vertex) node);

		// Perform DFS Actions Descend Vertex operation
		hierarchy.descendVertex((Vertex) node);
		parenthesizedList.descendVertex((Vertex) node);

		// add the node to the discovery list
		discovered.add(node);

		// mark the node as visited
		visited.add(node);

		// discover all of it's children
		ArrayList<V> list = adjacencyList.get(node);
		if (list != null) {
			for (V u : list)
				dfs(u);
		}

		// Perform DFS Actions Ascend Vertex operation
		hierarchy.ascendVertex((Vertex) node);
		parenthesizedList.ascendVertex((Vertex) node);

		// this node has been discovered completely, remove it from the list
		discovered.remove(node);
	}

	// method to print all the unvisited nodes/classes
	public void displayUnreachableClasses() {

		// Loop all over the adjacency list
		for (Map.Entry<V, ArrayList<V>> entry : adjacencyList.entrySet()) {
			// for each entry, check if there is unvisited/undiscovered nodes
			if (entry.getValue().size() > 0) {
				// if found, print and mark as visited to avoid double printing

				// check the node itself
				if (!visited.contains(entry.getKey())) {
					System.out.println(entry.getKey() + " is unreachable");
					visited.add(entry.getKey());
				}

				// check all of it's adjacent nodes
				for (V vertex : entry.getValue()) {
					if (!visited.contains(vertex)) {
						System.out.println(vertex + " is unreachable");
						visited.add(vertex);
					}
				}
			}
		}
	}
}