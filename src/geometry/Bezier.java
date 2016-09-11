package geometry;


import java.util.ArrayList;

/*
t is ratio of curve, example P°=[0][0], P¹=[1][2]
bÂ¹=(1-t)*P° +t*PÂ¹ ---> bÂ¹=[t][2*t]
t is also distance on the curve and the output becomes [x][y] 
for that point
*/
public class Bezier {

	ArrayList<Point> points;
	ArrayList<double[][]> bxyz;
	int n;								//number of points
	int a;								//number of axis
	double[] value;
	public Bezier() {
		points = new ArrayList<Point>();
		bxyz = new ArrayList<double[][]>();
		n = 0;
		a = 0;
	}

	public void addPoint(Point...p) {
		for(int k=0;k<p.length;k++){
			if(p[k].axisNr() == a || points.isEmpty()){
				a = p[k].axisNr();
				points.add(p[k]);
				n++;
			}
		}
	}

	private void init() {
		while(bxyz.size()<a){
			bxyz.add(new double[n][n]);
		}
		for (int i = 0; i < n; i++) {
			for(int k=0;k<a;k++){
				bxyz.get(k)[0][i] = points.get(i).get(k);
			}
		}
	}

	public double Bez(double b0, double b1, double t) {
		double b = b0 * (1 - t) + b1 * t;
		return b;
	}
	
	public double[] calBez(double... t){
		value = new double[a];
		init();
		double tc = t[0]; 					//t current
		for (int k = 0; k < a; k++) {
			for (int j = 1; j < n; j++) {
				for(int i=0;i<n-j;i++){
					double b0 = bxyz.get(k)[j-1][i];
					double b = bxyz.get(k)[j-1][i+1];
					bxyz.get(k)[j][i] = this.Bez(b0, b, tc);
				}
			}
			value[k] = bxyz.get(k)[n-1][0];
		}
		return value;
	}
}
