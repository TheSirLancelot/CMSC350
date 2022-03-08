package project4;

import java.util.ArrayList;

/**
 * Filename: DirectedGraph.java
 * 
 * @author William Weir 
 * Date: 8 March, 2022 
 * Description: class to add edges and verticies to a graph
 */
public class DirectedGraph extends Graph<Vertex> {
	
	// method to create a directed edge and add it to the graph
	public void addEdge(String u, String v) {

		// Check if the source node already has some connected edges
		ArrayList<Vertex> list = adjacencyList.get(getVertex(u));

		// if already not in the Adjacency list
		// Map it to a new Vertex and initialize
		if (list == null) {
			list = new ArrayList<>();
		}

		// add an edge from source to destination
		list.add(getVertex(v));

		// update the adjacency list
		adjacencyList.put(getVertex(u), list);
	}

	// method to check if a node is already mapped to a vertex, if not map it
	public Vertex getVertex(String u) {

		// if this node(String) showed up for the first time
		// map it to a correspond vertex
		if (!vertices.containsKey(u)) {
			vertices.put(u, new Vertex(u));
		}

		return vertices.get(u);
	}
}