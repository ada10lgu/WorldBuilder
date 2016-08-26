package algorithm;

import geometry.Point;

import java.util.ArrayList;

public class Bezier {

	ArrayList<Point> points;
	ArrayList<long[][]> bxyz;
	int n;								//number of points
	int a;								//number of axis
	long[] value;
	public Bezier() {
		points = new ArrayList<Point>();
		value = null;
		n = 0;
		a = 0;
	}

	public boolean addPoints(ArrayList<Point> pos) {
		int m = pos.get(0).axisNr();
		if(m==0){
			points.addAll(pos);
			n = n + pos.size();
			a = m;
			return true;
		}else if(m == a){
			points.addAll(pos);
			n = n + pos.size();
			return true;
		}
		return false;

	}
	
	public void addPoint(Point p) {
		points.add(p);
		n++;
	}

	private void init() {
		while(bxyz.size()<a){
			bxyz.add(new long[n][n]);
		}
		for (int i = 0; i < n; i++) {
			for(int j=0;i<a;i++){
				bxyz.get(0)[0][i] = points.get(i).getX();
				bxyz.get(1)[0][i] = points.get(i).getY();
				if(a==3){
					bxyz.get(2)[0][i] = points.get(i).getZ();
				}
			}
		}
	}

	public long Bez(long b0, long b1, long t) {
		long b = b0 * (1 - t) + b1 * t;
		return b;
	}
	
	public long[] calBez(long... t){
		value = new long[3];
		init();
		long tc = t[0]; 					//t current
		for (int k = 1; k < n; k++) {
			if(k<=t.length){
				tc = t[k-1];
			}
			for (int j = 0; j < n - k; j++) {
				for(int i=0;i<bxyz.size();i++){
					bxyz.get(i)[k][j] = this.Bez(bxyz.get(i)[k-1][j], bxyz.get(i)[k-1][j+1], tc);
				}
			}
		}
		return value;
	}
}
