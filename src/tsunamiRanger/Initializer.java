package tsunamiRanger;


import java.awt.Color;

public class Initializer {
	private double mapx;               // Current X position of map
	private double mapy;               // Current Y position of map
	private EZImage map;               // Map picture
	private String type;
	private EZSound finalwave;         // Sounds
	private EZSound boss;
	private EZSound BGM1;
	private EZSound missionstart;
	private EZSound BGM2;
	private boolean beginningflag;     // Flags for various positions
	private boolean missionstartflag;
	private boolean alarmflag;         // Final wave flags
	private boolean finalwaveflag;
	
	private EZImage title;             // Player controls
	private EZImage instructions;
	private EZImage logo;
	private EZText P;
	private EZText O;
	private EZSound optionsound;
	
	
	public Initializer(String string, double x, double y) {
		if (string == "map") {
			mapx = x;
			mapy = y;
			map = EZ.addImage("assets/map.png", 5250, 300);
			logo = EZ.addImage("assets/LOGO.png", 1460, 40);
			finalwave = EZ.addSound("assets/Sounds/Finalwave.wav");
		    boss = EZ.addSound("assets/Sounds/Boss.wav");
		    BGM1 = EZ.addSound("assets/Sounds/BGM2.wav");
		    missionstart = EZ.addSound("assets/Sounds/Mission1.wav");
		    BGM2 = EZ.addSound("assets/Sounds/BGM1.wav");
			finalwaveflag = true;
			beginningflag = true;
			alarmflag = true;
			missionstartflag = true;
			
			type = "map";
		}
		
		if (string == "control") {
			 instructions = EZ.addImage("assets/BAR_0.png", 750, 300);
		     title = EZ.addImage("assets/JUDUL_1.png", 750, 70);
		     P = EZ.addText(600, 490, "Press 'P' to Pause", Color.white, 20);
		     O = EZ.addText(890, 490, "Press 'O' to Resume", Color.white, 20);
		     optionsound = EZ.addSound("assets/Sounds/Optionsscreen.wav");
		     type = "control";
		}
	}
	
	public void show() {
		if (type == "control") {
			instructions.show();
			P.show();
			O.show();
		}
	}
	public void hide() {
		if (type == "control") {
			instructions.hide();
			P.hide();
			O.hide();
		}
	}
	
	public void pullToFront() {
		instructions.pullToFront();
		P.pullToFront();
		O.pullToFront();
	}
	
	public void playSound() {
	    if (type == "control") {
	    	optionsound.loop();
	    }
	}

	  // Stop options screen sound
	public void stopSound() {
	    if (type == "control") {
	    	optionsound.pause();
	    }
	}
		  
	public void translateObject(double posx, double posy) {
		if (type == "map") {
			if (mapx == 5249.0 && beginningflag == true) {
				BGM1.loop();
		        beginningflag = false;
		    }
		      // Mission start sound
		    if (mapx == 5175.0 && missionstartflag == true) {
		        missionstartflag = false;
		    }
		      // To get this number, x of map - EZ.intialize x -10
		      // If reach end of map
		    if (mapx >= -3740)
		        map.translateTo(mapx -= posx, posy);
		    
		    if (mapx < -3740 && finalwaveflag == true) {
		    	finalwave.play();
		        boss.play();
		        BGM1.stop();
		        BGM2.loop();
		        finalwaveflag = false;
		    }
		      // More final wave sounds
		    if (mapx < -1000 && alarmflag == true) {
		    	finalwave.play();
		        alarmflag = false;
		    }
		}
	}
	
	public double getCurrentMap() {
	    return mapx;
	}
	
}
