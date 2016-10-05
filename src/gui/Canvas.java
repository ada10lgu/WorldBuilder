package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import geometry.Bezier;
import geometry.Point;
import geometry.Spline;

public class Canvas extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Bezier> beziers = new ArrayList<Bezier>();
	private ArrayList<Spline> splines = new ArrayList<Spline>();
	private int interval;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawLine(0, 0, getWidth(), getHeight());
        
        int co = 0;
        for(Bezier b : beziers){
        	Point p0 = b.calBez(0);
        	for(int i=0;i<interval;i++){
        		double t = ((double)i)/interval;
        		Point p1 = b.calBez(t);
        		g.drawLine((int)p0.getX(), (int)p0.getY(), (int)p1.getX(), (int)p1.getY());
        		p0 = p1;
        		g.setColor(new Color(co, co ,co));
        //		co++;
        		if(co>254){
        			co=0;
        		}
        	}
        }
        
        for(Spline sp : splines){
        	Point p0 = sp.calStrictSpline(0);
        	for(int i=1;i<interval;i++){
        		double u = ((double)i)/interval;
        		Point p1 = sp.calStrictSpline(u);
        		g.drawLine((int)p0.getX(), (int)p0.getY(), (int)p1.getX(), (int)p1.getY());
        		p0 = p1;
        	}
        }
    }
	
	public void setInterval(int t){
		this.interval = t;
	}

	public void addBezier(Bezier b){
		beziers.add(b);
	}
	
	public void addSpline(Spline sp){
		splines.add(sp);
	}
}

