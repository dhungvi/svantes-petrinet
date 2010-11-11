package PrisonProblem.util;

import java.util.ArrayList;

public class Node {
	
	public ArrayList<Node> neighbors;
	public ArrayList<Integer> flows;
	public Node previous;
	public boolean isSearched;
	public static final int capacity = 2;
	
	public Node() {
		neighbors = new ArrayList<Node>();
		flows = new ArrayList<Integer>();
		previous = null;
		isSearched = false;
	}

}
