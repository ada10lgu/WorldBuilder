package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import geometry.Spline;
import interfaces.ThreadListener;
import javafx.scene.layout.Border;
import geometry.Bezier;
import geometry.Point;
import world.Zone;

@SuppressWarnings("serial")
public class AppGUI extends JFrame implements Runnable {

	/*
	 * Forest Plains Mountain Desert Swamp
	 */
	Canvas can;
	CmdField cmd;
	JPanel textMng;
	private ThreadListener listener = null;
	
	// Data sender
	String input;
	
	/**
	 * Listener of actions
	 */
	final List<String> holder = new LinkedList<String>();
	
	public AppGUI() {
		/**
		 * initiate the graphical 
		 */
		input = null;
		
		setSize(1200, 900);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
		can = new Canvas();
		add(can);
		can.setOpaque(true);
		can.setBackground(Color.WHITE);
		can.setLayout(null);
				
		cmd = new CmdField();
		cmd.setSize(getWidth()*2/3, 30);
        cmd.setLocation(getWidth()/8, getHeight()*8/9);
		can.add(cmd);
		
		textMng = new JPanel();
	//	textMng.setOpaque(true);
		textMng.setBackground(Color.RED);
		textMng.setSize(getWidth()*2/3, getHeight()*15/18);
		textMng.setLocation(getWidth()/8, 20);
		can.add(textMng);
		
		
	}
	
	@Override
	public void run() {
		
		
		cmd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				synchronized (holder){
					holder.add(cmd.getText());
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
						e1.printStackTrace();
					}
				
				String nextCmd = holder.remove(0);
				if(nextCmd!=null){
					input = nextCmd;
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
			listener.onInput(input);
		}

	}	
	
	private class CmdField extends JTextField {
		
	}

}
