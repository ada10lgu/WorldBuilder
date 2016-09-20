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
		int mult = 50;
		int x = 350;
		int y = 300;
		g.setColor(c);
		double[] xy0 = sp.calStrictSpline(0);
		for(int tX=1;tX<1000;tX++){
			double t = (double)tX;
			t = t/1000;
			g.setColor(new Color((int)(255*t),(int)(255*t),0));
			double[] xy1 = sp.calStrictSpline(t);
		//	g.drawRect((int)(x+mult*xy0[0]), (int)(y+mult*xy0[1]), 1, 1);
			g.drawLine((int)(x+mult*xy0[0]), (int)(y+mult*xy0[1]), (int)(x+mult*xy1[0]), (int)(y+mult*xy1[1]));
			xy0 = xy1;
		}
	}
	
}

