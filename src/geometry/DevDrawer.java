package geometry;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import gui.Canvas;

public class DevDrawer implements Runnable{
	
	final List<String> holder = new LinkedList<String>();
	String input;

	public static void main(String[] args) {
		DevDrawer frame = new DevDrawer();
		SwingUtilities.invokeLater(frame);
	}
	
	@Override
	public void run() {
		input = null;
		
		JFrame frame = new JFrame("My JFrame Example");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 900));
        
        Canvas can = new Canvas();
        can.setSize(new Dimension(frame.getWidth(), frame.getHeight() ));
        frame.add(can);
        frame.pack();
        
        can.setInterval(10000);
        Bezier b = new Bezier();
        b.addPoint(new Point(0,0));
        b.addPoint(new Point(600, 0));
        b.addPoint(new Point(600, 600));
        can.addBezier(b);
        
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(300, 300);
        points[2] = new Point(100, 600);
        points[3] = new Point(700, 100);
        Spline sp = new Spline(points);
        can.addSpline(sp);
        
        JTextField text = new JTextField();
        text.setSize(new Dimension(frame.getWidth(), 15));
        text.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	text.setText("");
	        }

			@Override
			public void focusLost(FocusEvent arg0) {
				text.setText("Command Promopt");
			}
	    });
        
        text.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				synchronized (holder){
					holder.add(text.getText());
					holder.notify();
				}

			}

		});
        
        frame.add(text);
        frame.setVisible(true);
		
	/**	while(true){
			//logic
			synchronized (holder) {
				// input wait
				while (holder.isEmpty())
					try {
						holder.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				
				String nextCmd = holder.remove(0);
				if(nextCmd!=null){
					input = nextCmd;

					/// command when putting in
				}
				
			}
		} */
	}
}
