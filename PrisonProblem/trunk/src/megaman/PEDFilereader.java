package megaman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class PEDFilereader {

	public int tunnelWidth,tunnelLength,noOfLamps,radius;
	public int[][] lamp_map;

	public void readFile(String filename) {
		File inFile = new File(filename);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			String line = br.readLine();
			Scanner lineScan = new Scanner(line);

			tunnelWidth = lineScan.nextInt();
			tunnelLength = lineScan.nextInt();
			noOfLamps = lineScan.nextInt();
			radius = lineScan.nextInt();
			lamp_map = new int[noOfLamps][2];
			for (int i = 0; i < noOfLamps; i++) {
				line = br.readLine();
				lineScan = new Scanner(line);
				lamp_map[i][0] = lineScan.nextInt();	// x
				lamp_map[i][1] = lineScan.nextInt();	// y	
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTunnelWidth() {
		return tunnelWidth;
	}

	public int getTunnelLength() {
		return tunnelLength;
	}

	public int getNoOfLamps() {
		return noOfLamps;
	}

	public int getRadius() {
		return radius;
	}

	public int[][] getLamp_map() {
		return lamp_map;
	}
	
}