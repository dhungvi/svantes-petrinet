package ripCleaned;

import java.util.Vector;
import javax.swing.JLabel;

// Referenced classes of package links.applet.graphtheory:
//            GraphAlgorithm, Vertex, Edge, GraphData

public class FordFulkersonAlgorithm
{
	
    public boolean editWhileRunning;
    boolean done;
    GraphData graphData;
//    JLabel explanation;
    int stage;

    Vertex source;
    Vertex sink;
    Vector augmentingPath;
    Vector allVerticesReached;
    Vector allEdgesReached;

    public FordFulkersonAlgorithm(GraphData graphdata)
    {
        done = false;
        graphData = graphdata;
        stage = 0;
        augmentingPath = new Vector();
        allVerticesReached = new Vector();
        allEdgesReached = new Vector();
        editWhileRunning = false;

        int i = 0;
        int j = 0;
        boolean flag = false;
        for(int k = 0; k < graphData.allVertices.size(); k++)
        {
            Vertex vertex = (Vertex)graphData.allVertices.elementAt(k);
            if(vertex.source)
            {
                i++;
                source = vertex;
            }
            if(vertex.sink)
            {
                j++;
                sink = vertex;
            }
            vertex.unhighlight();
        }

        
        
        /**
         * messages below is only used for debugging, delete before delivering, so we don't need to waste time on these checks
         */
        if(i == 0 && j == 0)
        {
            done = true;
            System.err.println("Error: No source or sink vertex selected");
        } else
        if(i == 0)
        {
            done = true;
            System.err.println("Error: No source vertex selected");
        } else
        if(j == 0)
        {
            done = true;
            System.err.println("Error: No sink vertex selected");
        } else
        if(i > 1)
        {
            done = true;
            System.err.println("Error: Only one source allowed");
        } else
        if(j > 1)
        {
            done = true;
            System.err.println("Error: Only one sink allowed");
        } 
        
        
        
        
    }

    public boolean next()
    {
        if(done)
        {
            return done;
        }
        if(stage == 0)
        {
            for(int i = 0; i < graphData.allEdges.size(); i++)
            {
                ((Edge)graphData.allEdges.elementAt(i)).flow = 0;
            }

            System.out.println("Initialize flow to 0.");
            stage = 2;
        } else
        if(stage == 1)
        {
            for(int j = 0; j < graphData.allVertices.size(); j++)
            {
                Vertex vertex = (Vertex)graphData.allVertices.elementAt(j);
                vertex.unhighlight();
            }

            augmentingPath = new Vector();
            allVerticesReached = new Vector();
            allEdgesReached = new Vector();
            stage = 2;
        } else
        if(stage == 2)
        {
            source.highlight();
            if(findAugmentingPath(source))
            {
                stage = 4;
            } else
            {
                stage = 3;
                for(int k = 0; k < allVerticesReached.size(); k++)
                {
                    ((Vertex)allVerticesReached.elementAt(k)).highlight();
                }
            }
            source.unhighlight();
            System.out.println("Finding the augmenting path");
        } else
        if(stage == 3)
        {
        	System.out.println("No augmenting path can be found.");
            stage = 5;
        } else
        if(stage == 4)
        {
            int l = 0x7fffffff;
            Vertex vertex1 = source;
            for(int i2 = 0; i2 < augmentingPath.size(); i2++)
            {
                Edge edge1 = (Edge)augmentingPath.elementAt(i2);
                if(edge1.start.equals(vertex1))
                {
                    if(edge1.capacity - edge1.flow < l)
                    {
                        l = edge1.capacity - edge1.flow;
                    }
                    vertex1 = edge1.end;
                } else
                {
                    if(edge1.flow < l)
                    {
                        l = edge1.flow;
                    }
                    vertex1 = edge1.start;
                }
            }

            vertex1 = source;
            for(int j2 = 0; j2 < augmentingPath.size(); j2++)
            {
                Edge edge2 = (Edge)augmentingPath.elementAt(j2);
                if(edge2.start.equals(vertex1))
                {
                    edge2.flow += l;
                    vertex1 = edge2.end;
                } else
                {
                    edge2.flow -= l;
                    vertex1 = edge2.start;
                }
            }

            System.out.println("Updating flow.");
            stage = 1;
        } else
        if(stage == 5)
        {
            int i1 = 0;
            for(int l1 = 0; l1 < source.outEdges.size(); l1++)
            {
                i1 += ((Edge)source.outEdges.elementAt(l1)).flow;
            }

            System.out.println("Flow = " + i1);
            done = true;
        }
        return done;
    }

    private boolean findAugmentingPath(Vertex vertex)
    {
        if(vertex.equals(sink))
        {
            vertex.unhighlight();
            return true;
        }
        for(int i = 0; i < vertex.outEdges.size(); i++)
        {
            Edge edge = (Edge)vertex.outEdges.elementAt(i);
            if(edge.flow < edge.capacity && !edge.end.highlighted)
            {
                augmentingPath.addElement(edge);
                edge.end.highlight();
                allEdgesReached.addElement(edge);
                allVerticesReached.addElement(edge.end);
                if(findAugmentingPath(edge.end))
                {
                    return true;
                }
                augmentingPath.removeElementAt(augmentingPath.size() - 1);
                edge.end.unhighlight();
            }
        }

        for(int j = 0; j < vertex.inEdges.size(); j++)
        {
            Edge edge1 = (Edge)vertex.inEdges.elementAt(j);
            if(edge1.flow > 0 && !edge1.start.highlighted)
            {
                augmentingPath.addElement(edge1);
                edge1.start.highlight();
                allEdgesReached.addElement(edge1);
                allVerticesReached.addElement(edge1.start);
                if(findAugmentingPath(edge1.start))
                {
                    return true;
                }
                augmentingPath.removeElementAt(augmentingPath.size() - 1);
                edge1.start.unhighlight();
            }
        }

        return false;
    }

}
