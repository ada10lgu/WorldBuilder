package gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.prism.Graphics;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import interfaces.ThreadListener;

@SuppressWarnings("serial")
public class AppGUI extends JFrame implements Runnable {

	/*
	 * Forest Plains Mountain Desert Swamp
	 */
	JPanel panel;
	CmdField cmd;
	Canvas can;
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
		
		setSize(900, 700);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setBackground(Color.BLACK);
		
		GridBagConstraints cons = new GridBagConstraints();
		
		panel = new JPanel(new GridBagLayout());
		panel.setOpaque(true);
		panel.setBackground(Color.DARK_GRAY);
		panel.setSize(getWidth()*4/5, getHeight());
		add(panel);
		
		
		
		can = new Canvas();
		can.setOpaque(false);
		can.setBackground(Color.RED);
		can.setSize(getWidth(), getHeight()/10);
		
		cons.fill=GridBagConstraints.HORIZONTAL;
		cons.fill=GridBagConstraints.VERTICAL;
		cons.insets= new Insets(30,60,60,30);
		cons.gridx=0;
		cons.gridy=0;
		cons.gridwidth=3;
		cons.gridheight=3;
		cons.weightx=1;
		cons.weighty=1;
		cons.anchor = GridBagConstraints.PAGE_START;
		
		panel.add(can, cons);
		
		cmd = new CmdField();
		cmd.setSize(getWidth()*2/3, 30);
        
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx=0;
		cons.gridy=1;
		cons.gridwidth=10;
		cons.weightx=0;
		cons.weighty=1;
		cons.anchor = GridBagConstraints.PAGE_END;
        panel.add(cmd,cons);
        
        
      //  pack();
        setVisible(true);
        
        can.getGraphics().setColor(Color.RED);
        can.getGraphics().fillRect(30, 60, 20, 60);
        
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
	
	public void addWord(String word){
		GridBagConstraints cons1 = new GridBagConstraints();
		JLabel word1 = new JLabel(word);
		JLabel word2;
		for(int i=0;i>can.getWidth();i++){
			for(int k=0;k>can.getHeight();k++){
				if(can.getComponentAt(i, k)!=null){								/// fills and re-organizes the printed words, needs rearrange them all.
					word2=(JLabel) can.getComponentAt(i, k);
					
				}
			}
		}
		
        cons1.gridx=0;
        cons1.gridy=0;
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
