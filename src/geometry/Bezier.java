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
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	public Point calBez(double... t){
		double[] value = new double[a];
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
		Point answer = new Point(value);
		return answer;
	}
	
	public Bezier split(double t){
		
		// point 1 is first point in bezier curve
		// tangent of first point
		double deri1 = derivate(0)[0];
		double k1y = points.get(0).getY();
		
		// point for splitting
		Point p3 = calBez(t);
		double deri3 = derivate(t)[0];
		double k3y = p3.getY();
		// y = deri3*x + k3x
		double x2 = (k3y-k1y)/(deri1-deri3);
		double y2 = x2*deri3 + k3y;
		Point p2 = new Point(x2,y2);
		
		// last point 
		double deri5 = derivate(1)[0];
		double k5y = points.get(points.size()-1).getY();
		  
		double x4 = (k5y-k3y)/(deri3-deri5);
		double y4 = x2*deri5 + k5y;
		Point p4 = new Point(x4,y4);
		
		// this bezier new points
		ArrayList<Point> tmp = new ArrayList<Point>();
		tmp.add(points.remove(0));
		tmp.add(p2);
		tmp.add(p3);
		points.remove(0);
		
		// new bezier point 
		Bezier bez = new Bezier();
		bez.addPoint(p3);
		bez.addPoint(p4);
		bez.addPoint(points.remove(0));
		
		// set new points for the bezier
		points = tmp;
		
		return bez;
	}
	
	public double[] derivate(double t){
		double[] answer = new double[2];
		answer[0] = 2*(points.get(0).getX()*t-points.get(0).getX()+points.get(1).getX()-2*points.get(1).getX()*t+points.get(2).getX()*t);
		answer[1] = 2*(points.get(0).getY()*t-points.get(0).getY()+points.get(1).getY()-2*points.get(1).getY()*t+points.get(2).getY()*t);
		return answer;
	}
	
	
	public boolean modify(Point p0, Point pn){
		for(Point p : points){
			if(p.getX()==p0.getX() && p.getY()==p0.getY()){
				p=pn;
				return true;
			}
		}
		return false;
	}
}
