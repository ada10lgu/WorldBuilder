
import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

import generator.DescriptGenerator;
import geometry.*;
import graph_world_generator.*;
import gui.AppGUI;
import gui.Canvas;
import interfaces.ThreadListener;
import node_world_structure.World;


public class Game extends Thread implements ThreadListener{
	
	// GUI and interfaces
	AppGUI gui;
	
	// Generators
	WorldGenerator wg;
	World w;
	DescriptGenerator descGen;
	
	// Managers 
	
	
	public static void main(String[] args) {
		(new Game()).start();
	}
	
	@Override
	public void run() {
		gui = new AppGUI();
		gui.addListener(this);
		(new Thread(gui)).start();
	}

	@Override
	public void onInput(String input) {
		
		
		if(wg==null){
			wg = new WorldGenerator(input);
			Random r = new Random(input.hashCode());
			descGen = new DescriptGenerator(r);
			w = wg.generate();
			gui.addWord(descGen.descript(w));
		}else{
			String[] line = input.trim().split("\\s+");
			char first = line[0].charAt(0);
			switch(first){
				case '/':
					// command case with call
					System.out.println("command: "+input);
					break;
				default:
					// default print just text
					System.out.println("default "+input);
			}
		}
	}
	
}






