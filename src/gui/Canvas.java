package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import geometry.Bezier;
import geometry.Spline;

public class Canvas extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Graphics g;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void drawBezier(Bezier b, Color c, Graphics g){
		g.setColor(c);
		for(int t100=0;t100<10000;t100++){
			double t = (double)t100;
			t = t/10000;
			double[] xy = b.calBez(t);
			g.fillRect((int)xy[0], (int)xy[1], 1, 1);
		}
	}
	
	public void drawSplines(Spline sp, Color c, Graphics g){
		int mult = 10;
		int x = 350;
		int y = 300;
		g.setColor(c);
		double[] xy0 = sp.calStrictSpline(0);
		for(int tX=1;tX<10000;tX++){
			double t = (double)tX;
			t = t/10000;
			double[] xy1 = sp.calStrictSpline(t);
			g.drawLine(x+(mult*(int)xy0[0]), y+(mult*(int)xy0[1]), x+(mult*(int)xy1[0]), y+(mult*(int)xy1[1]));
			xy0 = xy1;
		}
	}
	
}

