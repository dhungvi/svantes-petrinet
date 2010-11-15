package ripCleaned;

import java.util.LinkedList;
import java.util.Vector;

public class FordFulkersonAlgorithm2
{

	public boolean editWhileRunning;
	boolean done;
	GraphData graphData;
	int stage;

	Vertex source;
	Vertex sink;
	Vector augmentingPath;
	Vector allVerticesReached;
	Vector allEdgesReached;

	public FordFulkersonAlgorithm2(GraphData graphdata)
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


	public int max_flow() {
		int count = 0;
		while (bfs()) {
			count++;
		}
		return count;
	}


	public boolean bfs() {
		LinkedList<Vertex> Q = new LinkedList<Vertex>(); //queue Q
		Q.add(source);  // push source to Q
		source.highlighted = true; // mark source as visited

		//keep an array from with the semnification: from[x] is the 
		//previous vertex visited in the shortest path from the source to x;
		//initialize from with -1 (or any other sentinel value) 
		Vertex where;
		search : while (!Q.isEmpty()) {// while Q is not empty
			where = Q.removeFirst(); //pop from Q 
			for (Edge out : where.outEdges) { //for each vertex next adjacent to where
				Vertex next = out.end;
				if (!next.highlighted && out.flow > 0 ) {//if next is not visited and capacity[where][next] > 0
					Q.add(next); //push next to Q
					next.highlighted = true; // mark next as visited
					next.prev_edge = out; // from[next] = where
				}

				
				if (next.equals(sink)) { //if next = sink
					break search; // exit while loop
				}
			} //end for
		} //end while


		//    // we compute the path capacity
		//    where = sink, path_cap = infinity
		//    while from[where] > -1
		//      prev = from[where] // the previous vertex 
		//      path_cap = min(path_cap, capacity[prev][where])
		//      where = prev
		//    end while

		// we update the residual network;
		//if no path is found the while 
		// loop will not be entered
		
		where = this.sink;
		while(where.prev_edge != null) { //while from[where] > -1
			
			
			Vertex prev = where.prev_edge.start;

			//update flow - xxxxxx
			//      capacity[prev][where] -= path_capacity
			//          capacity[where][prev] += path_capacity

			
			
			where.prev_edge.flow -= 1;
			for (Edge e : where.prev_edge.start.inEdges) {
				if (e.start.equals(where)) {
					e.flow += 1;
					break;
				}
			}

			
			// flow updated - xxxxxxx    
			where = prev;

		}//end while
		
		boolean pathFound = this.sink.prev_edge != null;
		
		//clear vertexes
		for (Vertex v : graphData.allVertices) {
			v.prev_edge = null;
			v.highlighted = false;
		}

		return pathFound;

	}
}
