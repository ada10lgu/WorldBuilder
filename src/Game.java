
import generator.WorldGenerator;
import gui.AppGUI;
import interfaces.ThreadListener;


public class Game extends Thread implements ThreadListener{
	
	// GUI and interfaces
	AppGUI gui;
	
	// Generators
	WorldGenerator wg;
	
	// Managers 
	
	
	public static void main(String[] args) {
		(new Game()).start();
	}
	
	@Override
	public void run() {
		gui = new AppGUI();
		wg = null;
		gui.addListener(this);
		(new Thread(gui)).start();
	}

	@Override
	public void onInput(String input) {
		
		
		if(wg==null){
			wg = new WorldGenerator(input.trim().replace(" ", ""));
			wg.generate();
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






