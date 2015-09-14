import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GiftWrapping {
	Point[] S;
	int V;
	PrintWriter out;

	public GiftWrapping(Point[] S) {
		// TODO Auto-generated constructor stub
		this.S = S;
		V = S.length;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", false)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Point TrivialExtreme() {
		double minx = Double.MAX_VALUE;
		double miny = Double.MAX_VALUE;
		for (int i = 0; i < S.length; i++) {
			if (S[i].y == miny && S[i].x < minx) {
				miny = S[i].y;
				minx = S[i].x;
			} else if (S[i].y < miny) {
				miny = S[i].y;
				minx = S[i].x;
			}
		}
		return new Point(minx, miny);

	}

	double ccw(Point p1, Point p2, Point p3) {
		return ((p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x));
	}

	boolean compare(Point a, Point b) {
		return ((a.x == b.x) && (a.y == b.y));
	}

	void ConvexHull() {
		ArrayList<Point> P = new ArrayList<Point>();
		// System.out.println(TrivialExtreme());
		Point pointOnHull = TrivialExtreme();
		int i = 0;
		Point endPoint = null;
		do {
			P.add(i, pointOnHull);
			endPoint = S[0];
			// System.out.println("Point on hull " + pointOnHull);
			for (int j = 0; j < S.length; j++) {
				// System.out.println(P.get(i) + " " + endPoint + " " + S[j] + "
				// " + ccw(P.get(i), endPoint, S[j]));
				if (compare(endPoint, pointOnHull) || ccw(P.get(i), endPoint, S[j]) > 0) {
					endPoint = S[j];
				}
			}
			// System.out.println("Endpoint " + endPoint);
			i++;
			pointOnHull = endPoint;
		} while (!compare(endPoint, P.get(0)));
		// System.out.println("--- Hull ---");
		out.println(P.size());
		for (int j = 0; j < P.size(); j++) {
			out.println(P.get(j));
		}
		out.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("input.txt");
		int V;
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
			System.out.println("--- Points ---");
			for (int i = 0; i < S.length; i++) {
				System.out.println(S[i]);
			}
			GiftWrapping gw = new GiftWrapping(S);
			gw.ConvexHull();
			input.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}

	}

}
