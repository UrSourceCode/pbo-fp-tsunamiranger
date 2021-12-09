package tsunamiRanger;


import java.awt.Color;

public class Initializer {
	private double mapx;               // Current X position of map
	private double mapy;               // Current Y position of map
	private EZImage map;               // Map picture
	private String type;
	
	private boolean beginningflag;     // Flags for various positions
	private boolean missionstartflag;
	private boolean alarmflag;         // Final wave flags
	private boolean finalwaveflag;
	
	private EZImage title;             // Player controls
	private EZImage A;                 // Left
	private EZImage W;                 // Look up
	private EZImage S;                 // Crouch
	private EZImage D;                 // Right
	private EZImage Space;             // Jump
	private EZImage J;                 // Knife
	private EZImage K;                 // Shoot bullets
	private EZImage L;
	
	private EZText instructions;
	private EZText attack;
	private EZText jump;
	private EZText right;
	private EZText left;
	private EZText move;
	
	
	public Initializer(String string, double x, double y) {
		if (string == "map") {
			mapx = x;
			mapy = y;
			map = EZ.addImage("map.jpg", 1800, 600);
			finalwaveflag = true;
			beginningflag = true;
			alarmflag = true;
			missionstartflag = true;
			
			type = "map";
		}
		
		if (string == "control") {
			 instructions = EZ.addText(750, 50, "Instructions", Color.white, 50);
		     attack = EZ.addText(1075, 150, "Attack", Color.white, 50);
		     jump = EZ.addText(1080, 550, "Jump", Color.white, 35);
		     left = EZ.addText(250, 420, "Left", Color.white, 35);
		     right = EZ.addText(750, 420, "Right", Color.white, 35);
		     
		     type = "control";
		}
	}
	
	public void translateObject(double posx, double posy) {
		if (type == "map") {
			if (mapx == 1840.0 && beginningflag == true) {
		        //BGM1.loop();
		        beginningflag = false;
		    }
		      // Mission start sound
		    if (mapx == 1780.0 && missionstartflag == true) {
		        //missionstart.play();
		        missionstartflag = false;
		    }
		      // To get this number, x of map - EZ.intialize x -10
		      // If reach end of map
		    if (mapx >= -1000)
		        map.translateTo(mapx -= posx, posy);
		      // Final wave sounds
		    if (mapx < -1000 && finalwaveflag == true) {
//		        finalwave.play();
//		        boss.play();
//		        BGM1.stop();
//		        BGM2.loop();
		        finalwaveflag = false;
		    }
		      // More final wave sounds
		    if (mapx < -1000 && alarmflag == true) {
		        //finalwave.play();
		        alarmflag = false;
		    }
		}
	}
	
	public double getCurrentMap() {
	    return mapx;
	}
	
}
