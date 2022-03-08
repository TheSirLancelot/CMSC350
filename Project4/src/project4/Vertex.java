package project4;

/**
 * Filename: Vertex.java
 * 
 * @author William Weir 
 * Date: 8 March, 2022 
 * Description: basic Vertex class, adds a name 
 */
public class Vertex {
	private String name;

	public Vertex(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}