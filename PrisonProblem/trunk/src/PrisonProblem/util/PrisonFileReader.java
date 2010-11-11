package PrisonProblem.util;

import java.io.*;
import java.util.ArrayList;

public class PrisonFileReader {
	private String path = "";

	public PrisonFileReader(String path) {
		this.path = path;
	}

	public int[][] fileRead() {
		int[][] out = null;
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(this.path);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] start_params = null;

			strLine = br.readLine(); // Grab first line
			if (strLine != null) {
				start_params = strLine.split(" ");
			}

			ArrayList<String[]> circles = new ArrayList<String[]>();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				circles.add(strLine.split(" "));
			}
			// Close the input stream
			in.close();
			out = new int[circles.size()][4];

			out[0][0] = Integer.parseInt(start_params[0]);
			out[0][1] = Integer.parseInt(start_params[1]);
			out[0][2] = Integer.parseInt(start_params[2]);
			out[0][3] = Integer.parseInt(start_params[3]);

			for (int i = 1; i < circles.size(); i++) {
				out[i][0] = Integer.parseInt(circles.get(i-1)[0]);
				out[i][1] = Integer.parseInt(circles.get(i-1)[1]);
			}
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return out;
	}
}
