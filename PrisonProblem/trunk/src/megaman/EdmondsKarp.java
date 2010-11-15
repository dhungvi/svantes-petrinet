package megaman;

import java.util.*;

public class EdmondsKarp
{
	public static void main(String[] args) throws Exception
	{
		
		new EdmondsKarp("problem001.ped");
		new EdmondsKarp("problem010.ped");
		new EdmondsKarp("problem011.ped");
		new EdmondsKarp("problem015.ped");
		new EdmondsKarp("problem016.ped");
		new EdmondsKarp("problem017.ped");
		new EdmondsKarp("problem018.ped");
		new EdmondsKarp("problem020.ped");
		new EdmondsKarp("problem021.ped");
		new EdmondsKarp("problem022.ped");
		new EdmondsKarp("problem023.ped");
		new EdmondsKarp("problem024.ped");
		new EdmondsKarp("problem031.ped");
		new EdmondsKarp("problem032.ped");
		new EdmondsKarp("problem033.ped");
		new EdmondsKarp("problem034.ped");
		new EdmondsKarp("problem035.ped");
		new EdmondsKarp("problem036.ped");
		new EdmondsKarp("problem041.ped");
		
	}
		
	public EdmondsKarp(String fileName) {	
		PEDFilereader ped = new PEDFilereader();
		ped.readFile(fileName);

		int width = ped.getTunnelWidth();//in.nextInt();
		int noOfNodes = ped.getNoOfLamps();//in.nextInt();
		int radius = ped.getRadius();//in.nextInt();
		int m = noOfNodes+2; // Number of vertices incl. source and sink

		int[][] lamp_map = ped.getLamp_map();
		int[][] capacity = new int[m][m]; // Capacity of edges //we add with 2 to make room for source and target
		for (int i=1; i<capacity.length-1; i++) {
			for (int j=1; j<capacity.length-1; j++) {

				int current_x = lamp_map[i-1][0];
				int current_y = lamp_map[i-1][1];

				if (current_y+radius >= width-1) { // the "top" of the tunnel is touched
					// create the edge from source to vertex
					capacity[0][i] = 1;
					capacity[i][0] = 1;
				}

				if (current_y-radius <= 1) { // the "bottom" of the tunnel is touched
					// create the edge from vertex to sink
					capacity[m-1][i] = 1;
					capacity[i][m-1] = 1;
				}

				if (j == i) continue; //don't compare the same vertex
				int lamp_x = lamp_map[j-1][0];
				int lamp_y = lamp_map[j-1][1];
				double distance = (current_x - lamp_x)*(current_x - lamp_x) + (current_y - lamp_y)*(current_y - lamp_y);
				if (distance+2 <= (radius*2)*(radius*2)) { //the lamps is overlapping 
					capacity[i][j] = 1;
					capacity[j][i] = 1;
				}
			}
		}

		
		int source = 0;
		int sink = m-1;

		/* Now do the Edmonds-Karp algorithm */
		int totalFlow = 0;
		int[][] flow = new int[m][m];
		while (true)
		{
			// Breadth-first search
			int minCapacity = 0;
			int[] parents = new int[m];
			int[] capacityOfPath = new int[m];

			for (int i = 0; i < parents.length; i++)
			{
				parents[i] = -1;
			}

			parents[source] = -2;
			capacityOfPath[source] = Integer.MAX_VALUE;
			Queue<Integer> nodeQ = new LinkedList<Integer>();
			nodeQ.offer(source);
			boolean found = false;
			while (!nodeQ.isEmpty() && !found)
			{
				int current = nodeQ.poll();
				for (int n = 0; n < capacity[current].length; n++)
				{
					if (capacity[current][n] - flow[current][n] > 0 && parents[n] == -1)
					{
						parents[n] = current;
						capacityOfPath[n] = Math.min(capacityOfPath[current], capacity[current][n] - flow[current][n]);
						if (n != sink)
						{
							nodeQ.offer(n);
						}
						else
						{
							found = true;
							minCapacity = capacityOfPath[n];
							break;
						}
					}
				}
			}

			if (minCapacity == 0) // Break if no more paths can be found
			{
				break;
			}
			totalFlow += minCapacity;
			
			// Update flow
			int current = sink;
			while (current != source)
			{
				int p = parents[current];
				flow[p][current] += minCapacity;
				flow[current][p] -= minCapacity;
				current = p;
			}
		}
		
		System.out.printf("Maximal flow is: %d\n", totalFlow);
	}
}