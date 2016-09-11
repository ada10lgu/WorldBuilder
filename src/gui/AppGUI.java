package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import geometry.Spline;
import geometry.Bezier;
import geometry.Point;
import world.Zone;

@SuppressWarnings("serial")
public class AppGUI extends JFrame {

	/*
	 * Forest Plains Mountain Desert Swamp
	 */

	private ArrayList<Zone> points;

	public AppGUI() {

		setSize(1200, 900);
		add(new Canvas());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class Canvas extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			testBezier();
			for(int t100=0;t100<1000;t100++){
				double t = (double)t100;
				t = t/1000;
				double[] xy = getXY(t);
				System.out.println("xy: "+(int)xy[0]+" "+(int)xy[1]);
				g.setColor(Color.WHITE);
				g.fillRect((int)xy[0], (int)xy[1], 1, 1);
			}
		}
	}

	Bezier b;
	
	private void testBezier(){
		
		Point[] point = new Point[4];
		point[0] = new Point(0,0);
		point[1] = new Point(200,300);
		point[2] = new Point(300,0);
		point[3] = new Point(1000,900);
		b = new Bezier();
		b.addPoint(point);
	}
	private double[] getXY(double t){
		double[] xy = b.calBez(t);
		return xy;
	}
}
