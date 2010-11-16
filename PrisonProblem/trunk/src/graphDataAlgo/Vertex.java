package graphDataAlgo;

import java.util.Vector;

public class Vertex
{
    public Vector<Edge> outEdges;
    public Vector<Edge> inEdges;
    public boolean highlighted;
    public boolean source;
    public boolean sink;
    public Edge prev_edge;
    public int number;

    public Vertex(int index)
    {
    	this.number = index;
        outEdges = new Vector<Edge>();
        inEdges = new Vector<Edge>();
        highlighted = false;
        source = false;
        sink = false;
    }


    public void highlight()
    {
        if(!highlighted)
        {
            highlighted = true;
        }
    }

    public void unhighlight()
    {
        if(highlighted)
        {
            highlighted = false;
        }
    }

    public void setSource()
    {
        source = true;
        sink = false;
    }

    public void setSink()
    {
        sink = true;
        source = false;
    }

    public void setNormal()
    {
        source = false;
        sink = false;
    }
}