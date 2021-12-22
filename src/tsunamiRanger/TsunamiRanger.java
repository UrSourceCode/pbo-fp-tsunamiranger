package tsunamiRanger;

import java.awt.Color;
public class TsunamiRanger {

	public static void main(String[] args) {
		//Initialize EZ and framerate
		EZ.initialize(1500,600);
		EZ.setFrameRate(180);
		
		Initializer map = new Initializer("map", 5250, 300);
		Initializer control = new Initializer ("control", 750, 300);
		boolean pauseflag = true;
		
		while (true) {
			map.translateObject(.0,300);
			
			EZ.refreshScreen();
			
		}
	}

}
