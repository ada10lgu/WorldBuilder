import gui.AppGUI;
import interfaces.ThreadListener;


public class Game extends Thread implements ThreadListener{
	
	public static void main(String[] args) {
		(new Game()).start();
	}
	
	@Override
	public void run() {
		AppGUI gui = new AppGUI();
		
		gui.addListener(this);
		
		(new Thread(gui)).start();
	}

	@Override
	public void onInput(String input) {
		// TODO Auto-generated method stub
		System.out.println(input);
	}
	
}






