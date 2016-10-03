package geometry;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import gui.Canvas;

public class DevDrawer implements Runnable{

	public static void main(String[] args) {
		DevDrawer frame = new DevDrawer();
		SwingUtilities.invokeLater(frame);
	}
	
	@Override
	public void run() {
		JFrame frame = new JFrame("My JFrame Example");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 900));
        
        Canvas can = new Canvas();
        can.setSize(new Dimension(frame.getWidth(), frame.getHeight() ));
        frame.add(can);
        frame.pack();
        frame.setVisible(true);
        
        Bezier b = new Bezier();
        b.addPoint(new Point(0,0));
        b.addPoint(new Point(600, 0));
        b.addPoint(new Point(600, 600));
        can.drawBezier(b, Color.BLACK, frame.getGraphics());
	}

}
