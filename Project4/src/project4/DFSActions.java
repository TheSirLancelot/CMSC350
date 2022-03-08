package project4;

/**
 * Filename: DFSActions.java
 * 
 * @author William Weir 
 * Date: 8 March, 2022 
 * Description: Simple interface to handle DFSActions based on graph
 */
public interface DFSActions<V> {
	public void processVertex(V vertex);

	public void descendVertex(V vertex);

	public void ascendVertex(V vertex);

	public void cycleDetected();
}