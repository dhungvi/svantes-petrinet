package prisonproblem;

import java.util.Vector;

public class GraphData
{
	public Vector<Vertex> allVertices;
	public Vector<Edge> allEdges;

	public GraphData()
	{
		allVertices = new Vector<Vertex>();
		allEdges = new Vector<Edge>();
	}

	public void addVertex(Vertex vertex, int nr)
	{
		allVertices.addElement(vertex);
		Vertex dummy = new Vertex(nr);
		allVertices.addElement(dummy);
		
		// add egdes between node and dummy
		Edge e1 = new Edge(vertex, dummy);
		e1.capacity = 1;
		allEdges.addElement(e1);
	}
	
	public void addSourceVertex(Vertex vertex) {
		allVertices.addElement(vertex);
	}
	public void addSinkVertex(Vertex vertex) {
		allVertices.addElement(vertex);
	}

	public void addEdgeToSource(Edge edge) {
		allEdges.addElement(edge);
	}
	
	public void addEdgeToSink(Edge edge) {
		Vertex edge_start_dummy =  allVertices.get(edge.start.number+1); // node dummy
		Vertex edge_end_original = edge.end; // sink
		
		Edge dummy_out = new Edge(edge_start_dummy, edge_end_original);
		dummy_out.capacity = 1;
		allEdges.addElement(dummy_out);
	}
	
	public void addEdgeBetweenVertices(Edge edge)
	{
		Vertex edge_start_dummy =  allVertices.get(edge.start.number+1);
		Vertex edge_end_original = edge.end;
		
		Edge dummy_out = new Edge(edge_start_dummy, edge_end_original);
		dummy_out.capacity = 1;
		allEdges.addElement(dummy_out);
	}

}