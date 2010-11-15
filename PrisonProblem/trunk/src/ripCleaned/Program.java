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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Program {

	public static void main(String[] args) {
		new Program();
	}
	
	PEDFilereader ped;

	public Program() {
		// Get the file data
		ped = new PEDFilereader();
		ped.readFile("jannik.ped");

		System.out.println("W="+ped.getTunnelWidth()+ " L="+ped.getTunnelLength()+ " N="+ped.getNoOfLamps()+ " r="+ped.getRadius());

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


			// 
			if (current_x+radius < 0 || current_x-radius > length) {
				System.out.println("Continue!");
				continue; // if the lamp does not light inside the length of tunnel. then we do not care about it. 
			}
			
			if (current_y+radius < 0 || current_y-radius > width) {
				System.out.println("Continue!");
				continue; // if the lamp does not light inside the length of tunnel. then we do not care about it. 
			}



			System.out.println("Test Passed");


			if (current_y+radius >= width) { // the "top" of the tunnel is touched
				// create the edge from source to vertex
				Edge e = new Edge(source, vertexes[i]);
				e.capacity = 1;
				graphdata.addEdge(e);

				System.out.println("TOP = " +i);
				count_top++;
			}

			if (current_y-radius <= 0) { // the "bottom" of the tunnel is touched
				// create the edge from vertex to sink
				Edge e = new Edge(vertexes[i], sink);
				e.capacity = 1;
				graphdata.addEdge(e);

				System.out.println("Bottom = " +i);
				count_bottom++;

			}

			for (int j=0; j<lamp_map.length; j++) {
				if (j == i) continue; //don't compare the same vertex
				int[] lamp = lamp_map[j];
				double distance = Math.sqrt( (current_x - lamp[0])*(current_x - lamp[0]) + (current_y - lamp[1])*(current_y - lamp[1])  ); 
				if (distance < radius*2) { //the lamps is overlapping 
					System.out.println("Vertex("+lamp_map[i][0]+","+lamp_map[i][1]+") - EDGE = " + i+", "+j+"       --- Distance = "+distance);
					Edge e = new Edge(vertexes[i], vertexes[j]);
					e.capacity = 1;
					graphdata.addEdge(e);
					count_middle++;
				}
			}

		}
		
		
		
		//Jeg tror at fejlen er at pilene også peger tilbage. De skal kun pege frem ad mod sink, på en eller anden måde.
		

		// Instantiate the algorithm and run
		FordFulkersonAlgorithm currentAlgorithm = new FordFulkersonAlgorithm(graphdata);
		boolean flag = currentAlgorithm.done;
		while(!flag)
		{
			flag = currentAlgorithm.next();
		}

		System.err.println("ended");
		System.err.println("Top = " +count_top+ " Bottom = " +count_bottom+ " Middle = " +count_middle);
	}





	private class GraphPainter extends JFrame {

		public GraphPainter() {
			
			JPanel main = new JPanel();
			main.setLayout(new BorderLayout());
			main.add(new MyJPanel(), BorderLayout.CENTER);
			this.getContentPane().add(main);
		}

		private class MyJPanel extends JPanel {
			
			public MyJPanel() {
				super();
				this.setPreferredSize(new Dimension(ped.getTunnelLength(),ped.getTunnelWidth()));
				this.setBorder(BorderFactory.createLineBorder(Color.black));
			}

			@Override
			public void paint(Graphics g) {
				for (int[] lamp : ped.getLamp_map()) {
					g.drawOval(lamp[0]-ped.getRadius(), lamp[1]-ped.getRadius(), ped.getRadius()*2, ped.getRadius()*2);
				}
			}
		}
	}
	
	
}
