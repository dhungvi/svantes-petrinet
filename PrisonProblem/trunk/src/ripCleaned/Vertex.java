package ripCleaned;

import java.util.Vector;

public class Vertex
{
    public Vector outEdges;
    public Vector inEdges;
    public boolean highlighted;
    public boolean source;
    public boolean sink;

    public Vertex()
    {
        outEdges = new Vector();
        inEdges = new Vector();
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