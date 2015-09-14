import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MBR {
	int V;
	Point [] S;
	
	public MBR(int V, Point [] S) {
		// TODO Auto-generated constructor stub
		this.V = V;
		this.S = S;
	}
	
	double calculateAngle(Point a, Point b) {
		if (a.x-b.x == 0) return -Math.acos(0);
		return -Math.atan((b.y-a.y)/(b.x-a.x));
	}
	
	void findMBR() {
		double area = Double.MAX_VALUE;
		double fminX = 0, fminY = 0, fmaxX = 0, fmaxY = 0;
		double fDegree = 0;
		for (int i=0;i<V;i++) {
			int j = (i+1)%V;
			//System.out.println(i + " " + j);
			double degree = calculateAngle(S[i],S[j]);
			//System.out.println(degree);
			Double min_area = Double.MAX_VALUE;
			double minX = Double.MAX_VALUE;
			double minY = Double.MAX_VALUE;
			double maxX = Double.MIN_VALUE;
			double maxY = Double.MIN_VALUE;
			for (int k=0;k<V;k++) {
				double xPrime = S[k].x*Math.cos(degree) - S[k].y*Math.sin(degree);
				if (xPrime > maxX) maxX = xPrime;
				if (xPrime < minX) minX = xPrime;
				double yPrime = S[k].x*Math.sin(degree) + S[k].y*Math.cos(degree);
				if (yPrime > maxY) maxY = yPrime;
				if (yPrime < minY) minY = yPrime;
				min_area = (maxX - minX) * (maxY - minY);
				//System.out.println(m_area);
				
			}
			if (area > min_area) {
				area = min_area;
				fminX = minX; fmaxX = maxX; fminY = minY; fmaxY = maxY;
				fDegree = degree;
			}
			/*
			StraightLine line = new StraightLine(S[i], S[j]);
			System.out.println("Points " + S[i] + " " + S[j] + " " + line);
			double maxDistance = Double.MIN_VALUE;
			Point maxPoint = null;
			for (int k=0;k<V;k++) {
				double distance = line.distance(S[k]);
				if (distance > maxDistance) {
					maxDistance = distance;
					maxPoint = S[k];
				}
				//System.out.println(S[k] + " " + line.distance(S[k]));
			}
			System.out.println("Max point " + maxPoint + " Max distance " + maxDistance);
			StraightLine perpendicular = new StraightLine(S[i], S[j]);
			*/
		}
		System.out.println(area);
		Point [] rect = new Point[4];
		rect [0] = new Point(fminX, fminY);
		rect [1] = new Point(fminX, fmaxY);
		rect [2] = new Point(fmaxX, fminY);
		rect [3] = new Point(fmaxX, fmaxY);
		for (int k=0;k<4;k++) {
			//System.out.println(rect[k]);
			rect[k].x = rect[k].x*Math.cos(-fDegree) - rect[k].y*Math.sin(-fDegree);
			rect[k].y = rect[k].x*Math.sin(-fDegree) + rect[k].y*Math.cos(-fDegree);
			//System.out.println(rect[k]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("output.txt");
		int V = 0;
		Point[] S = null;
		try {
			Scanner input = new Scanner(file);
			if (input.hasNext()) {
				V = input.nextInt();
				S = new Point[V];
			}
			int iter = 0;
			while (input.hasNext()) {
				S[iter++] = new Point(input.nextDouble(), input.nextDouble());
			}
			//System.out.println("--- Points ---");
			//for (int i = 0; i < S.length; i++) {
			//	System.out.println(S[i]);
			//}
			MBR mbr = new MBR(V,S);
			mbr.findMBR();
			input.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}


	}

}
