package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import geometry.Spline;
import interfaces.ThreadListener;
import geometry.Bezier;
import geometry.Point;
import world.Zone;

@SuppressWarnings("serial")
public class AppGUI extends JFrame implements Runnable {

	/*
	 * Forest Plains Mountain Desert Swamp
	 */
	private ArrayList<Zone> points;
	Canvas can;
	CmdField cmd;
	private ThreadListener listener = null;
	
	// Data sender
	String output;
	
	/**
	 * Listener of actions
	 */
	final List<String> holder = new LinkedList<String>();
	
	public AppGUI() {
		/**
		 * initiate the graphical 
		 */
		output = null;
		
		setSize(1200, 900);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
		can = new Canvas();
		add(can);
		can.setOpaque(true);
		can.setBackground(Color.WHITE);
		can.setLayout(null);
				
		cmd = new CmdField();
		cmd.setSize(getHeight()*2/3, 30);
        cmd.setLocation(getHeight()/8, 800);
		can.add(cmd);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		cmd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				synchronized (holder){
					holder.add(cmd.getName());
					System.out.println(cmd.getText());
					holder.notify();
				}

			}

		});
		
		setVisible(true);
		
		while(true){
			//logic
			synchronized (holder) {
				// input wait
				while (holder.isEmpty())
					try {
						holder.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				String nextCmd = holder.remove(0);
				if(nextCmd!=null){
					output = nextCmd;
					
					infoListener();
				}
				
			}
		}
	}
	
	public void addListener(ThreadListener listener) {
		this.listener = listener;
	}
	
	private void infoListener(){
		if (listener!=null) {
			listener.onInput(output);
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
		
		
		double c0 = 0.5;
		double c1 = 0.5;
		double c2 = 0.5;
		g.setColor(Color.BLUE);
		g.fillOval((int)((1-c0)*knots[0].getX()+c0*knots[1].getX()) - 5 , (int)((1-c0)*knots[0].getY()+c0*knots[1].getY()) - 5, 10, 10);
		g.fillOval((int)((1-c1)*knots[1].getX()+c1*knots[2].getX()) - 5, (int)((1-c1)*knots[1].getY()+c1*knots[2].getY()) - 5, 10, 10);
		g.fillOval((int)((1-c2)*knots[2].getX()+c2*knots[3].getX()) - 5, (int)((1-c2)*knots[2].getY()+c2*knots[3].getY()) - 5, 10, 10);
		
		sp = new Spline(knots[0]);
		sp.addKnot(knots[1], c0);
		sp.addKnot(knots[2], c1);
		sp.addKnot(knots[3], c2);
		for(int t10000=0;t10000<10000;t10000++){
			double t = (double)t10000;
			t = t/10000;
			double[] xy = sp.calStrictSpline(t);
			g.setColor(Color.WHITE);
			g.fillRect((int)xy[0], (int)xy[1], 1, 1);
		}
	}

	
	
	
	private class CmdField extends JTextField {
		
	}

	private class Canvas extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			
			testBezier(g);			//test bezier drawing
			
			testSplines(g);			//test spline drawing
		}
	}
}
