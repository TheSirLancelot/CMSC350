package project4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Filename: ParenthesizedList.java
 * 
 * @author William Weir 
 * Date: 8 March, 2022 
 * Description: class handling the printing of a parethesized list
 */
public class ParenthesizedList implements DFSActions<Vertex> {

	Queue<String> res = new LinkedList<>();

	@Override
	public void processVertex(Vertex vertex) {
		res.add(vertex.toString());
	}

	@Override
	public void descendVertex(Vertex vertex) {
		res.add("(");
	}

	@Override
	public void ascendVertex(Vertex vertex) {
		res.add(")");
	}

	@Override
	public void cycleDetected() {
		res.add("*");
	}

	// formatting for print
	@Override
	public String toString() {

		String ans = "";
		ans += "( ";
		while (res.size() > 0) {
			String c = res.peek();
			res.remove();

			if (c == "(") {
				if (res.peek() == ")") {
					res.remove();
					continue;
				} else if (res.peek() == "*") {
					ans += res.peek() + " ";
					res.remove();
					res.remove();
					continue;
				}
			}
			
			ans += c + " ";
		}

		ans += ")\n";

		return ans;
	}
}