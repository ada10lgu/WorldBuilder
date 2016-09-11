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
			
			testBezier(g);			//test bezier drawing
			
			testSplines(g);			//test spline drawing
		}
	}

	Bezier b;
	
	private void testBezier(Graphics g){
		Point[] point = new Point[4];
		point[0] = new Point(0,0);
		point[1] = new Point(0,500);
		point[2] = new Point(700,300);
		point[3] = new Point(1000,700);
		
		g.setColor(Color.RED);
		g.fillOval((int)point[0].getX() - 5 , (int)point[0].getY() - 5, 10, 10);
		g.fillOval((int)point[1].getX() - 5 , (int)point[1].getY() - 5, 10, 10);
		g.fillOval((int)point[2].getX() - 5 , (int)point[2].getY() - 5, 10, 10);
		g.fillOval((int)point[3].getX() - 5 , (int)point[3].getY() - 5, 10, 10);
		
		b = new Bezier();
		b.addPoint(point);
		for(int t100=0;t100<10000;t100++){
			double t = (double)t100;
			t = t/10000;
			double[] xy = b.calBez(t);
			g.setColor(Color.WHITE);
			g.fillRect((int)xy[0], (int)xy[1], 1, 1);
		}
	}
	
	Spline sp;
	
	private void testSplines(Graphics g){
		Point[] knots = new Point[4];
		knots[0] = new Point(0,0);
		knots[1] = new Point(200,300);
		knots[2] = new Point(300,0);
		knots[3] = new Point(900,700);
		
		g.setColor(Color.GREEN);
		g.fillOval((int)knots[0].getX() - 5, (int)knots[0].getY() - 5, 10, 10);
		g.fillOval((int)knots[1].getX() - 5, (int)knots[1].getY() - 5, 10, 10);
		g.fillOval((int)knots[2].getX() - 5, (int)knots[2].getY() - 5, 10, 10);
		g.fillOval((int)knots[3].getX() - 5, (int)knots[3].getY() - 5, 10, 10);
		
		g.setColor(Color.BLUE);
		g.fillOval(((int)knots[0].getX()+(int)knots[1].getX())/2 - 5 , ((int)knots[0].getY()+(int)knots[1].getY())/2 - 5, 10, 10);
		g.fillOval(((int)knots[1].getX()+(int)knots[2].getX())/2 - 5, ((int)knots[1].getY()+(int)knots[2].getY())/2 - 5, 10, 10);
		g.fillOval(((int)knots[2].getX()+(int)knots[3].getX())/2 - 5, ((int)knots[2].getY()+(int)knots[3].getY())/2 - 5, 10, 10);
		
		sp = new Spline(knots);
		for(int t10000=0;t10000<10000;t10000++){
			double t = (double)t10000;
			t = t/10000;
			double[] xy = sp.calSpline(t);
			g.setColor(Color.WHITE);
			g.fillRect((int)xy[0], (int)xy[1], 1, 1);
		}
	}
	
}
