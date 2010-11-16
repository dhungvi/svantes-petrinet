package graphDataAlgo;
//import ripCleaned.Edge;
//import ripCleaned.GraphData;
//import ripCleaned.PEDFilereader;
//import ripCleaned.Vertex;

class graphDataEdmondsKarp
{
	public static final int WHITE = 0, GRAY = 1, BLACK = 2;
	private int[][] flow, res_capacity;
	int[][] capacity;
	private int[] parent, color, queue;
	private int[] min_capacity;
	private int size, sourceIndex, sinkIndex, first, last;
	public int number = -1;
	private int max_flow;

	public static void main(String[] args) {
		new graphDataEdmondsKarp("ped/test.ped");
		new graphDataEdmondsKarp("ped/problem001.ped");
		new graphDataEdmondsKarp("ped/problem010.ped");
		new graphDataEdmondsKarp("ped/problem011.ped");
		new graphDataEdmondsKarp("ped/problem015.ped");
		new graphDataEdmondsKarp("ped/problem016.ped");
		new graphDataEdmondsKarp("ped/problem017.ped");
		new graphDataEdmondsKarp("ped/problem018.ped");
		new graphDataEdmondsKarp("ped/problem019.ped");
		new graphDataEdmondsKarp("ped/problem020.ped");
		new graphDataEdmondsKarp("ped/problem021.ped");
		new graphDataEdmondsKarp("ped/problem022.ped");
		new graphDataEdmondsKarp("ped/problem023.ped");
		new graphDataEdmondsKarp("ped/problem024.ped");
		new graphDataEdmondsKarp("ped/problem031.ped");
		new graphDataEdmondsKarp("ped/problem032.ped");
		new graphDataEdmondsKarp("ped/problem033.ped");
		new graphDataEdmondsKarp("ped/problem034.ped");
		new graphDataEdmondsKarp("ped/problem035.ped");
		new graphDataEdmondsKarp("ped/problem036.ped");
		new graphDataEdmondsKarp("ped/problem041.ped");
	}

	PEDFilereader ped;

	public graphDataEdmondsKarp(String file) {
		// Get the file data
		ped = new PEDFilereader();
		ped.readFile(file);
		
		int[][] lamp_map = ped.getLamp_map();
		for (int[] lamp : lamp_map) {
			//System.out.println("x="+lamp[0]+" y="+lamp[1]);
		}
		
		int count_top=0, count_bottom=0, count_middle=0;

		// Convert it to a graph
		GraphData graphdata = new GraphData();

		// dummy source
		Vertex sourceVertex = new Vertex(getNextNumber());
		sourceVertex.setSource();
		graphdata.addVertex(sourceVertex, this.getNextNumber());
		sourceIndex = sourceVertex.number;
		
		// dummy sink
		Vertex sinkVertex = new Vertex(getNextNumber());
		sinkVertex.setSink();
		graphdata.addVertex(sinkVertex, this.getNextNumber());
		sinkIndex = sinkVertex.number;

		// Create all the Vertexes
		Vertex[] vertexes = new Vertex[lamp_map.length];
		for (int i=0; i<vertexes.length; i++) {
			vertexes[i] = new Vertex(getNextNumber());
			vertexes[i].setNormal();
			graphdata.addVertex(vertexes[i], this.getNextNumber());
		}

		// Create all the Edges
		int radius = ped.getRadius();
		int width = ped.getTunnelWidth();
		int length = ped.getTunnelLength();
		// test if the lamps is touching the sides of the tunnel
		for (int i=0; i<vertexes.length; i++) {

			int current_x = lamp_map[i][0];
			int current_y = lamp_map[i][1];

			if (current_y+radius >= width-1) { // the "top" of the tunnel is touched
				// create the edges between source and vertex
				Edge e1 = new Edge(sourceVertex, vertexes[i]);
				e1.flow = 1; //1				
				
				graphdata.addEdge(e1);

				Edge e2 = new Edge(vertexes[i], sourceVertex);
				e2.flow = 0;
				graphdata.addEdge(e2);

				count_top++;
			}

			if (current_y-radius <= 1) { // the "bottom" of the tunnel is touched
				// create the edge between vertex and sink
				Edge e1 = new Edge(vertexes[i], sinkVertex);
				e1.flow = 0;
				graphdata.addEdge(e1);

				Edge e2 = new Edge(sinkVertex, vertexes[i]);
				e2.flow = 1; //1
				graphdata.addEdge(e2);

				count_bottom++;

			}

			for (int j=0; j<lamp_map.length; j++) {
				if (j == i) continue; //don't compare the same vertex
				int[] lamp = lamp_map[j];
				double distance = (lamp[0] - current_x)*(lamp[0] - current_x) + (lamp[1] - current_y)*(lamp[1] - current_y);
				if (distance <= (radius*2)*(radius*2)) { //the lamps is overlapping 
					Edge e = new Edge(vertexes[i], vertexes[j]);
					if (e.end.outEdges.contains(e.start)) {
						e.flow = 0;
					} else {
						e.flow = 1; //1
					}
					graphdata.addEdge(e);
					count_middle++;
				}
			}
		}
		
		size = graphdata.allVertices.size();
		
		capacity = new int[size][size];
		for(Edge e : graphdata.allEdges) {
			capacity[e.start.number][e.end.number] = 1;
		}
		System.out.println("number of edges: " + graphdata.allEdges.size());
		
		flow = new int[size][size];
		for(Edge e : graphdata.allEdges) {
			flow[e.start.number][e.end.number] = e.flow;
		}
		
		maxFlow();
		
		System.err.println("Max flow for "+ file +" is: "+ max_flow);
	}

	private void maxFlow()  // Edmonds-Karp algorithm with O(V³E) complexity
	{
		res_capacity = new int[size][size];
		parent = new int[size];
		min_capacity = new int[size];
		color = new int[size];
		queue = new int[size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				res_capacity[i][j] = capacity[i][j];

		while (BFS(sourceIndex))
		{
			max_flow += min_capacity[sinkIndex];
			int v = sinkIndex, u;
			while (v != sourceIndex)
			{
				u = parent[v];
				flow[u][v] += min_capacity[sinkIndex];
				flow[v][u] -= min_capacity[sinkIndex];
				res_capacity[u][v] -= min_capacity[sinkIndex];
				res_capacity[v][u] += min_capacity[sinkIndex];
				v = u;
			}
		}
	}

	private boolean BFS(int source)  // Breadth First Search in O(V<sup>2</sup>)
	{
		for (int i = 0; i < size; i++)
		{
			color[i] = WHITE;
			min_capacity[i] = Integer.MAX_VALUE;
		}

		first = last = 0;
		queue[last++] = source;
		color[source] = GRAY;

		while (first != last)  // While "queue" not empty..
		{
			int v = queue[first++];
			for (int u = 0; u < size; u++)
				if (color[u] == WHITE && res_capacity[v][u] > 0)
				{
					min_capacity[u] = Math.min(min_capacity[v], res_capacity[v][u]);
					parent[u] = v;
					color[u] = GRAY;
					if (u == sinkIndex) return true;
					queue[last++] = u;
				}
		}
		return false;
	}
	
	public int getNextNumber() {
		number++;
		return number;
	}

}
