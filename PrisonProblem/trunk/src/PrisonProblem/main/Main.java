package PrisonProblem.main;
import PrisonProblem.util.PrisonFileReader;

public class Main {

	public static void main(String[] args) {
		PrisonFileReader fileReader = new PrisonFileReader("/home/tomasz/test.ped");
		int[][] jizz = fileReader.fileRead();
		System.out.println(jizz[0][0] + " " + jizz[0][1] + " " + jizz[0][2] + " " + jizz[0][3]);
		for (int i = 1; i <= jizz.length -1; i++) {
			System.out.println(jizz[i][0] + " " + jizz[i][1]);
		}
	}

}
