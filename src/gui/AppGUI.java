package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.MouseEvent;

import geometry.Spline;
import interfaces.ThreadListener;
import sun.font.TextLabel;

@SuppressWarnings("serial")
public class AppGUI extends JFrame implements Runnable {

	/*
	 * objects:
	 */
	JPanel panel;
	CmdField cmd;
	JPanel pres;
	JPanel contPanel;
	Canvas GFX_Panel;
	private ThreadListener listener = null;
	private ArrayList<JPanel> presList;
	
	// Constants 
	public String Test_Field_Message;
	
	// Data sender
	String input;
	
	// settings and resolutions will be replaced with ini file later
	private final int resH=700;
	private final int resW=900;
	
	/**
	 * Listener of actions
	 */
	final List<String> holder = new LinkedList<String>();
	
	public AppGUI() {
		// initiate constants
		input = null;
		presList = new ArrayList<JPanel>();
		Test_Field_Message = "Input Seed for generation of world";
		
		
		// initiate graphic
		pres = new JPanel();
		contPanel = new JPanel();
		cmd = new CmdField();
		
		
		// Main content panel and frame setup
		setSize(resW, resH);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contPanel.setBorder(BorderFactory.createEmptyBorder(resH/70, resW/10,resH/70, resW/9));
		contPanel.setLayout(new BorderLayout());
		contPanel.setBackground(Color.DARK_GRAY);
		contPanel.setSize(getWidth()*1/3, getHeight());
		add(contPanel);

		//layout for Text painter
		pres.setLayout(new BoxLayout(pres, BoxLayout.Y_AXIS));
		pres.setBackground(Color.BLUE);
		
		FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
		for(int i=0;i<40;i++){
			JPanel panel = new JPanel();
			panel.setLayout(flowlayout);
			panel.setBackground(Color.GRAY);
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.setAlignmentY(Component.TOP_ALIGNMENT);
			panel.add(new JLabel(" "));
			presList.add(panel);
			pres.add(panel, BorderLayout.BEFORE_LINE_BEGINS);
		}
		contPanel.add(pres, BorderLayout.BEFORE_LINE_BEGINS);
		
		// command and input field setup
		cmd.setSize(getWidth()*1/2, 30);
		cmd.setText("Input Seed");
		
		cmd.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	            cmd.setText("");
	        }

			@Override
			public void focusLost(FocusEvent arg0) {
				cmd.setText(Test_Field_Message);
			}
	    });

		contPanel.add(cmd,BorderLayout.PAGE_END);
        
		
		// Start and Visibility 
        setVisible(true);
        pres.revalidate();
        pres.repaint();
        pres.updateUI();
        revalidate();
		repaint();

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
		
		// continue managing incomming text
	}
	
	
	
	// Row Text adder for Presentation below (fixes and functionality needed)
	
	private int cuRow=0;
	public void addWord(String word){
		String[] words = word.split(" ");
		for(int i=0;i<words.length;i++){
			JLabel label = new JLabel(words[i]);
			if(rowSpace()<label.getPreferredSize().getWidth()){
				cuRow++;
				rowSize=0;
			}
			rowSize=(int) (rowSize+label.getPreferredSize().getWidth());
			presList.get(cuRow).add(label);
			pres.revalidate();
			pres.repaint();
		}
	}
	
	
	private int rowSize=0;
	private int rowSpace(){
		int ans=resW*3/5-rowSize;
		return ans;
	}
	
	public void addListener(ThreadListener listener) {
		this.listener = listener;
	}
	
	private void infoListener(){
		if (listener!=null) {
			if(input.trim().length()>0){
				listener.onInput(input);
				cmd.setText("");
			}else{
				cmd.setText("");
			}
		}

	}	
	
	private class CmdField extends JTextField {
		
	}

}
