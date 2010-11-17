package prisonproblem;

import java.util.LinkedList;
import java.util.Queue;

public class PrisonSolver
{
	private int[][] capacity;
	private int sourceIndex, sinkIndex;
	private int number = -1;
	private PEDFilereader ped;
	public String fileNameToRead;

	public static void main(String[] args) {
		new PrisonSolver("ped/jannik.ped");
		new PrisonSolver("ped/problem001.ped");
		new PrisonSolver("ped/problem010.ped");
		new PrisonSolver("ped/problem011.ped");
		new PrisonSolver("ped/problem015.ped");
		new PrisonSolver("ped/problem016.ped");
		new PrisonSolver("ped/problem017.ped");
		new PrisonSolver("ped/problem018.ped");
		new PrisonSolver("ped/problem020.ped");
		new PrisonSolver("ped/problem021.ped");
		new PrisonSolver("ped/problem022.ped");
		new PrisonSolver("ped/problem023.ped");
		new PrisonSolver("ped/problem024.ped");
		new PrisonSolver("ped/problem031.ped");
		new PrisonSolver("ped/problem032.ped");
		new PrisonSolver("ped/problem033.ped");
		new PrisonSolver("ped/problem034.ped");
		new PrisonSolver("ped/problem035.ped");
		new PrisonSolver("ped/problem036.ped");
		new PrisonSolver("ped/problem041.ped");
	}

	public PrisonSolver(String fileName) {
		fileNameToRead = fileName;
		getMaxFlow(buildGraph(fileName));
	}

	public GraphData buildGraph(String fileName) {
		// Get the file data
		ped = new PEDFilereader();
		ped.readFile(fileName);
		int[][] lamp_map = ped.getLamp_map();

		// Convert it to a graph
		GraphData graphdata = new GraphData();

		// dummy source
		Vertex sourceVertex = new Vertex(getNextNumber());
		sourceVertex.setSource();
		graphdata.addSourceVertex(sourceVertex);
		sourceIndex = sourceVertex.number;

		// dummy sink
		Vertex sinkVertex = new Vertex(getNextNumber());
		sinkVertex.setSink();
		graphdata.addSinkVertex(sinkVertex);
		sinkIndex = sinkVertex.number;

		// Create all the Vertexes
		Vertex[] vertexes = new Vertex[lamp_map.length];
		for (int i=0; i<vertexes.length; i++) {
			vertexes[i] = new Vertex(getNextNumber());
			vertexes[i].setNormal();
			graphdata.addVertex(vertexes[i], getNextNumber()); //also get a index for the dummy
		}

		// Create all the Edges
		int radius = ped.getRadius();
		int width = ped.getTunnelWidth();
		for (int i=0; i<vertexes.length; i++) {

			int current_x = lamp_map[i][0];
			int current_y = lamp_map[i][1];

			if (current_y+radius >= width-1) { // the "top" of the tunnel is touched
				// create the edges between source and vertex
				Edge e1 = new Edge(sourceVertex, vertexes[i]);
				e1.capacity = 1;
				graphdata.addEdgeToSource(e1);
			}

			if (current_y-radius <= 1) { // the "bottom" of the tunnel is touched
				// create the edge between vertex and sink
				Edge e1 = new Edge(vertexes[i], sinkVertex);
				e1.capacity = 1;
				graphdata.addEdgeToSink(e1);
			}

			for (int j=0; j<lamp_map.length; j++) {
				if (j == i) continue; //don't compare the same vertex
				int[] lamp = lamp_map[j];
				double distance = (lamp[0] - current_x)*(lamp[0] - current_x) + (lamp[1] - current_y)*(lamp[1] - current_y);
				if (distance <= (radius*2)*(radius*2)) { //the lamps is overlapping 
					Edge e = new Edge(vertexes[i], vertexes[j]);
					graphdata.addEdgeBetweenVertices(e);
				}
			}
		}
		return graphdata;
	}

	public int getMaxFlow(GraphData graphdata) {

		int m = graphdata.allVertices.size();
		capacity = new int[m][m];
		int[][] flow = new int[m][m];
		for(Edge e : graphdata.allEdges) {
			capacity[e.start.number][e.end.number] = e.capacity;
			capacity[e.end.number][e.start.number] = e.capacity;
		}

		int source = sourceIndex; // dummy source
		int sink = sinkIndex; // original sink
		int totalFlow = 0;
		
		while (true) {
			// Breadth-first search
			int minCapacity = 0;
			int[] parents = new int[m];
			int[] capacityOfPath = new int[m];

			for (int i = 0; i < parents.length; i++) {
				parents[i] = -1;
			}

			parents[source] = -2;
			capacityOfPath[source] = Integer.MAX_VALUE;
			Queue<Integer> nodeQ = new LinkedList<Integer>();
			nodeQ.offer(source);
			boolean found = false;
			while (!nodeQ.isEmpty() && !found) {
				int current = nodeQ.poll();
				for (int n = 0; n < capacity[current].length; n++) {
					if (capacity[current][n] - flow[current][n] > 0 && parents[n] == -1) {
						parents[n] = current;
						capacityOfPath[n] = Math.min(capacityOfPath[current], capacity[current][n] - flow[current][n]);
						if (n != sink) {
							nodeQ.offer(n);
						} else {
							found = true;
							minCapacity = capacityOfPath[n];
							break;
						}
					}
				}
			}

			// Break if no more paths can be found
			if (minCapacity == 0) {
				break;
			}
			totalFlow += minCapacity;

			// Update flow
			int current = sink;
			while (current != source) {
				int p = parents[current];
				flow[p][current] += minCapacity;
				flow[current][p] -= minCapacity;
				current = p;
			}
		}

		System.out.printf("("+fileNameToRead+") Maximal flow is: %d\n", totalFlow);
		return totalFlow;
	}

	public int getNextNumber() {
		number++;
		return number;
	}
}
