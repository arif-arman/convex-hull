
public class Point {
	
	double x;
	double y;
	
	public Point() {
		// TODO Auto-generated constructor stub
		this.x = 0;
		this.y = 0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = this.x + " " + this.y;
		return s;
	}

}
