package ripCleaned;

/**
 * Directed edge
 */
public class Edge
{

    public Vertex start;
    public Vertex end;
    public int capacity;
    public int flow;

    public Edge(Vertex start, Vertex end)
    {
        this.start = start;
        this.end = end;
        capacity = 0;
        flow = 0;
    }

}
