package tsunamiRanger;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.*;
import java.lang.*;

public class Player {
	// Player stats
	private int health;
	
	//	Player directions
	private int direction;
	private int posx;
	private int posy;
	private int playerState;
	private static final int MAPXLENGTH = 1440;
	
	// State value
	private static final int STAND = 1;
	private static final int SHOOT = 2;
	private static final int CROUCH = 3;
	private static final int JUMP = 4;
	private static final int LAND = 5;
	private static final int UP = 6;
	private static final int FIST = 7;
	private static final int KNIFE = 8;
	private static final int GUN = 9;
	private static final int GRENADE = 10;
	private static final int DEATH = 11;
	private static final int VICTORY = 12;
	private static final int LEFT = 100;
	private static final int RIGHT = 101;
	
	// Movement
	private static final float MOVE_SPEED = 3.5f;
	private static final float JUMP_SPEED = 4.25f;
	private static final float LAND_SPEED = 3.75f;
	private static final int JUMPLENGTH = 140;
	private static final int DELAY = 4;
	private static final int HEALTH = 500;
	
	// Constructor for Player
	public Player(int x, int y) {
		posx = x;
		posy = y;
		
		direction = RIGHT;
		playerState = STAND;
		// need asset for stand condition
		
	}
}
