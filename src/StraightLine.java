
public class StraightLine {
	double a, b ,c;
	
	public StraightLine(Point p1, Point p2) {
		// TODO Auto-generated constructor stub
		a = p2.y - p1.y;
		b = p1.x - p2.x;
		c = -p1.y*(p1.x - p2.x) +  p1.x*(p1.y-p2.y);
	}
	
	// construct perpendicular line
	public StraightLine(Point p1, Point p2, boolean var) {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = a + "x " + b + "y " + c;
		return s;
	}
	
	double distance(Point p) {
		double d = 0;
		d = Math.abs(a*p.x + b*p.y + c) / Math.sqrt(a*a + b*b);
		return d;
	}

}

