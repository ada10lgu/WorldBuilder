package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import geometry.Bezier;
import geometry.Point;
import geometry.Spline;

public class Canvas extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Graphics g;
	@Override
	public void paint(Graphics g) {
		this.g=g;
	
	/**	
		Bezier b = new Bezier();
		Point[] point = new Point[4];
		point[0] = new Point(0,0);
		point[1] = new Point(0,500);
		point[2] = new Point(700,300);
		point[3] = new Point(1000,700); 
		b.addPoint(point);
		drawBezier(b, Color.WHITE);
	*/
	}
	
	public void drawBezier(Bezier b, Color c){
		g.setColor(c);
		for(int t100=0;t100<10000;t100++){
			double t = (double)t100;
			t = t/10000;
			double[] xy = b.calBez(t);
			g.fillRect((int)xy[0], (int)xy[1], 1, 1);
		}
	}
	
	public void drawSplines(Spline sp, Color c){
		g.setColor(c);
		for(int t10000=0;t10000<10000;t10000++){
			double t = (double)t10000;
			t = t/10000;
			double[] xy = sp.calStrictSpline(t);
			g.fillRect((int)xy[0], (int)xy[1], 1, 1);
		}
	}
	
}
