package prisonproblem;

public class Vertex {
	public boolean source;
	public boolean sink;
	public int number;

	public Vertex(int index) {
		this.number = index;
		source = false;
		sink = false;
	}

	public void setSource() {
		source = true;
		sink = false;
	}

	public void setSink() {
		sink = true;
		source = false;
	}

	public void setNormal() {
		source = false;
		sink = false;
	}
}