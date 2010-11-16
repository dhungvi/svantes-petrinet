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

	public void addVertex(Vertex vertex)
	{
		allVertices.addElement(vertex);
	}

	public void deleteVertex(Vertex vertex)
	{
		allVertices.removeElement(vertex);
		for(; vertex.inEdges.size() > 0; deleteEdge((Edge)vertex.inEdges.elementAt(0))) { }
		for(; vertex.outEdges.size() > 0; deleteEdge((Edge)vertex.outEdges.elementAt(0))) { }
	}

	public void addEdge(Edge edge)
	{

//		for(int i = 0; i < edge.start.inEdges.size(); i++)
//		{
//			if(edge.end.outEdges.contains(edge.start.inEdges.elementAt(i)))
//			{
//				System.err.println("KOM IKKE MED 1");
//				return;
//			}
//		}
//
//		for(int j = 0; j < edge.start.outEdges.size(); j++)
//		{
//			if(edge.end.inEdges.contains(edge.start.outEdges.elementAt(j)))
//			{
//				System.err.println("KOM IKKE MED 2");
//				return;
//			}
//		}

		
		
		allEdges.addElement(edge);
		edge.start.outEdges.addElement(edge);
		edge.end.inEdges.addElement(edge);
	}

	public void deleteEdge(Edge edge)
	{
		allEdges.removeElement(edge);
		edge.start.outEdges.removeElement(edge);
		edge.end.inEdges.removeElement(edge);
	}

}