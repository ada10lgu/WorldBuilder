package algorithm;

import geometry.Point;

import java.util.ArrayList;

public class Bezier {

	ArrayList<Point> points;
	long[][] bx;
	long[][] by;
	int n;
	long[] valueXY;

	public Bezier() {
		points = new ArrayList<Point>();
		valueXY = new long[2];
		int n = 0;
	}

	public void addPoints(ArrayList<Point> pos) {
		points.addAll(pos);
		n = n + pos.size();
	}
	
	public void addPoint(Point p) {
		points.add(p);
		n++;
	}

	private void init() {
		for (int i = 0; i < n; i++) {
			bx[0][i] = points.get(i).getX();
			by[0][i] = points.get(i).getY();
		}
	}

	public long[] calBezier(long t) {
		long[][] bx = new long[n][n];
		long[][] by = new long[n][n];
		init();
		for (int k = 1; k < n; k++) {
			for (int j = 0; j < n - k; j++) {
				bx[k][j] = bx[k - 1][j] * (1 - t) + bx[k - 1][j + 1] * t;
				by[k][j] = by[k - 1][j] * (1 - t) + by[k - 1][j + 1] * t;
			}
		}
		valueXY[0] = bx[n][0];
		valueXY[1] = by[n][0];
		return valueXY;
	}

}
