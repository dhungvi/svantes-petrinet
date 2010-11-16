package graphDataAlgo;

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
		this.addInternalEdge(new Edge(vertex, dummy));
		this.addInternalEdge(new Edge(dummy, vertex));
	}


	public void addEdge(Edge edge)
	{
		allEdges.addElement(edge);
		Vertex v = this.allVertices.get(edge.start.number+1);
		v.outEdges.addElement(edge);
		edge.end.inEdges.addElement(edge);
	}

	public void addInternalEdge(Edge edge)
	{
		allEdges.addElement(edge);
		edge.start.outEdges.addElement(edge);
		edge.end.inEdges.addElement(edge);
	}

}