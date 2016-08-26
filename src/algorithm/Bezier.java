package algorithm;

import geometry.Point;

import java.util.ArrayList;

/*
t is ratio of curve, example P⁰=[0][0], P¹=[1][2]
b¹=(1-t)*P⁰ +t*P¹ ---> b¹=[t][2*t]
t is also distance on the curve and the output becomes [x][y] 
for that point
*/
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

	public void addPoints(ArrayList<Point> pos) {
		while(!pos.isEmpty()){
			this.addPoint(pos.get(0));
			this.addPoint(pos.remove(0));
		}
	}
	
	public boolean addPoint(Point p) {
		if(p.axisNr() == a || points.isEmpty()){
			a = p.axisNr();
			points.add(p);
			n++;
			return true;
		}
		return false;
	}

	private void init() {
		while(bxyz.size()<a){
			bxyz.add(new long[n][n]);
		}
		for (int i = 0; i < n; i++) {
			for(int j=0;i<a;i++){
				for(int k=0;k<a;k++){
					bxyz.get(k)[0][i] = points.get(i).get(k);
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
