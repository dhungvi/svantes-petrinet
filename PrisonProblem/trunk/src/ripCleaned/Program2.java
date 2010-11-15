package ripCleaned;

/***
 * 
 * 
 * 
 * 
 *  Hjemmeside eksempel
 * http://www.ibiblio.org/links/applets/appindex/graphtheory.html
 * 
 * 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Program2 {

	public static void main(String[] args) {
		new Program2("problem032.ped");
//		new Program2("problem010.ped");
//		new Program2("problem011.ped");
//		new Program2("problem015.ped");
//		new Program2("problem016.ped");
//		new Program2("problem017.ped");
//		new Program2("problem018.ped");
//		new Program2("problem020.ped");
	}
	
	PEDFilereader ped;

	public Program2(String file) {
		// Get the file data
		ped = new PEDFilereader();
		ped.readFile(file);

//		System.out.println("W="+ped.getTunnelWidth()+ " L="+ped.getTunnelLength()+ " N="+ped.getNoOfLamps()+ " r="+ped.getRadius());
//
		int[][] lamp_map = ped.getLamp_map();
		for (int[] lamp : lamp_map) {
			System.out.println("x="+lamp[0]+" y="+lamp[1]);
		}

		GraphPainter paint = new GraphPainter();
		paint.pack();
		paint.setVisible(true);
		paint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		

		int count_top=0, count_bottom=0, count_middle=0;

		
		// Convert it to a graph
		GraphData graphdata = new GraphData();

		// dummy source
		Vertex source = new Vertex();
		source.setSource();
		graphdata.addVertex(source);

		// dummy sink
		Vertex sink = new Vertex();
		sink.setSink();
		graphdata.addVertex(sink);

		// Create all the Vertexes
		Vertex[] vertexes = new Vertex[lamp_map.length];
		for (int i=0; i<vertexes.length; i++) {
			vertexes[i] = new Vertex();
			vertexes[i].setNormal();
			graphdata.addVertex(vertexes[i]);
		}

		// Create all the Edges
		int radius = ped.getRadius();
		int width = ped.getTunnelWidth();
		int length = ped.getTunnelLength();
		// test if the lamps is touching the sides of the tunnel
		for (int i=0; i<vertexes.length; i++) {

			int current_x = lamp_map[i][0];
			int current_y = lamp_map[i][1];


//			// 
//			if (current_x+radius < 0 || current_x-radius > length) {
//				System.out.println("Continue!");
//				continue; // if the lamp does not light inside the length of tunnel. then we do not care about it. 
//			}
//			
//			if (current_y+radius < 0 || current_y-radius > width) {
//				System.out.println("Continue!");
//				continue; // if the lamp does not light inside the length of tunnel. then we do not care about it. 
//			}



			if (current_y+radius >= width-1) { // the "top" of the tunnel is touched
				// create the edge from source to vertex
				Edge e1 = new Edge(source, vertexes[i]);
				e1.flow = 1;
				graphdata.addEdge(e1);
				
				Edge e2 = new Edge(vertexes[i], source);
				e2.flow = 1;
				graphdata.addEdge(e2);
				
				count_top++;
			}

			if (current_y-radius <= 1) { // the "bottom" of the tunnel is touched
				// create the edge from vertex to sink
				Edge e1 = new Edge(vertexes[i], sink);
				e1.flow = 1;
				graphdata.addEdge(e1);
				
				Edge e2 = new Edge(sink, vertexes[i]);
				e2.flow = 1;
				graphdata.addEdge(e2);

				count_bottom++;

			}

			for (int j=0; j<lamp_map.length; j++) {
				if (j == i) continue; //don't compare the same vertex
				int[] lamp = lamp_map[j];
				double distance = (current_x - lamp[0])*(current_x - lamp[0]) + (current_y - lamp[1])*(current_y - lamp[1]);
				if (distance <= (radius*2)*(radius*2)) { //the lamps is overlapping 
					Edge e = new Edge(vertexes[i], vertexes[j]);
					e.flow = 1;
					graphdata.addEdge(e);
					count_middle++;
				}
			}
		}
		
		
		
		//Jeg tror at fejlen er at pilene også peger tilbage. De skal kun pege frem ad mod sink, på en eller anden måde.
		

//		/**
//		 * TEST code
//		 */
//		graphdata = new GraphData();
//		
//		Vertex s = new Vertex();
//		s.setSource();
//		Vertex t = new Vertex();
//		t.setSink();
//		Vertex v1 = new Vertex();
//		Vertex v2 = new Vertex();
//		
//		graphdata.addVertex(s);
//		graphdata.addVertex(t);
//		graphdata.addVertex(v1);
//		graphdata.addVertex(v2);
//		
//		// gennem vertex v1
//		Edge s_v1 = new Edge(s, v1);
//		s_v1.flow = 1;
//		graphdata.addEdge(s_v1);
//		
//		Edge v1_s = new Edge(v1, s);
//		v1_s.flow = 1;
//		graphdata.addEdge(v1_s);
//		
//		Edge v1_t = new Edge(v1, t);
//		v1_t.flow = 1;
//		graphdata.addEdge(v1_t);
//		
//		Edge t_v1 = new Edge(t, v1);
//		t_v1.flow = 1;
//		graphdata.addEdge(t_v1);
//		
//		// gennem vertex v2
//		Edge s_v2 = new Edge(s, v2);
//		s_v2.flow = 1;
//		graphdata.addEdge(s_v2);
//		
//		Edge v2_s = new Edge(v2, s);
//		v2_s.flow = 1;
//		graphdata.addEdge(v2_s);
//		
//		Edge v2_t = new Edge(v2, t);
//		v2_t.flow = 1;
//		graphdata.addEdge(v2_t);
//		
//		Edge t_v2 = new Edge(t, v2);
//		t_v2.flow = 1;
//		graphdata.addEdge(t_v2);
		
		// Instantiate the algorithm and run
		FordFulkersonAlgorithm2 currentAlgorithm = new FordFulkersonAlgorithm2(graphdata);
		System.out.println("MAX FLOW ("+file+") ====== " + currentAlgorithm.max_flow());

	//	System.err.println("ended");
	//	System.err.println("Top = " +count_top+ " Bottom = " +count_bottom+ " Middle = " +count_middle);
	}





	private class GraphPainter extends JFrame {

		public GraphPainter() {
			
			JPanel main = new JPanel();
			main.setLayout(new BorderLayout());
			main.add(new MyJPanel(), BorderLayout.CENTER);
			this.getContentPane().add(main);
		}

		private class MyJPanel extends JPanel {
			
			private int scale_factor = 10;
			
			public MyJPanel() {
				super();
				this.setPreferredSize(new Dimension(ped.getTunnelLength()*scale_factor,ped.getTunnelWidth()*scale_factor));
				this.setBorder(BorderFactory.createLineBorder(Color.black));
			}

			@Override
			public void paint(Graphics g) {
				
				
				
				for (int[] lamp : ped.getLamp_map()) {
					
					int x = lamp[0] * scale_factor;
					int y = (ped.getTunnelWidth() - lamp[1]) * scale_factor;
					int radius = ped.getRadius() * scale_factor;
					
					
					g.drawOval(x-radius, y-radius, radius*2, radius*2);
				}
			}
		}
	}
	
	
}
