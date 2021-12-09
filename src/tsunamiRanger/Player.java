 package tsunamiRanger;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.*;
import java.lang.*;

import javax.swing.event.MenuKeyEvent;

public class Player {
	// Player image assets
	
	
	// Player stats
	private EZImage Idle;
//	private EZImage Jump;
//	private EZImage WalkL;
//	private EZImage WalkR;
//	private EZImage Taunt;
	private EZImage Attack;
//	private EZImage Dying;
//	private EZImage Right;
//	private EZImage Left;
	private int health;
	
	
	private EZImage playerIdle[] = new EZImage[IDLEPICS];
	private EZImage playerAttack[] = new EZImage [ATTACKPICS];
	
	//	Player directions
	private int direction;
	private int posx;
	private int posy;
	private int playerState;
	private static final int MAPXLENGTH = 1440;
	
	// State value
	private static final int IDLE = 1;
	private static final int JUMP = 2;
	private static final int WALKR = 3;
	private static final int WALKL = 4;
	private static final int TAUNT = 5;
	private static final int ATTACK = 6;
	private static final int DYING = 7;
	private static final int VICTORY = 8;
	private static final int RIGHT = 10;
	private static final int LEFT = 11;
	
	// Movement
	private static final float MOVE_SPEED = 3.5f;
	private static final float JUMP_SPEED = 4.25f;
	private static final float LAND_SPEED = 3.75f;
	private static final int JUMPLENGTH = 140;
	private static final int DELAY = 4;
	private static final int HEALTH = 500;
	
	
	// Smooth Movement
	private static final int ATTACKPICS = 11;
	private static final int IDLEPICS = 11;
	private static final int DYINGPICS = 14;
	private static final int JUMPPICS = 12;
	private static final int WALKINGPICS = 17;
	private static final int VICTORYPICS = 0;
	
	
	private static final float SCALINGFACTOR = 2.5f;
	// Constructor for Player
	public Player(int x, int y) {
		posx = x;
		posy = y;
		
		Idle = EZ.addImage("assets/Idle/0", x, y);
		
		direction = RIGHT;
		playerState = IDLE;
		hidePlayer();
		Idle.show();
		health = HEALTH;
		// need asset for stand condition
		
	}
	
	// Animations
	public void animationInit() {
		Idle.scaleTo(SCALINGFACTOR);
		
		for (int i=0; i < IDLEPICS; i++) {
			playerIdle[i] = EZ.addImage("assets/Idle/" + i + ".png", posx, posy);
			playerIdle[i].hide();
			
		}
		
		for (int i=0; i < ATTACKPICS; i++) {
			playerIdle[i] = EZ.addImage("assets/Attacking/" + i + ".png", posx, posy);
			playerIdle[i].hide();
			
		}
		
		
	}
	
	// Refresh Screen
	
	// need turn
	public void idleAnimation(int posx, int posy) {
		translateIdleAnimation(posx, posy);
	    for (int i = 0; i < IDLEPICS; i++) {
	    	playerIdle[i].show();
	    	EZ.refreshScreen();
	    	playerIdle[i].hide();
	    }
	}
	
	public void attackAnimation(int posx, int posy) {
		translateIdleAnimation(posx, posy);
	    for (int i = 0; i < ATTACKPICS; i++) {
	    	playerAttack[i].show();
	    	EZ.refreshScreen();
	    	playerAttack[i].hide();
	    }
	}
	
	
	
	public void translateIdleAnimation(int posx, int posy) {
		for (int i = 0; i < IDLEPICS; i++) {
			playerIdle[i].translateTo(posx, posy);
		}
	}
	
	public void translateAttackAnimation(int posx, int posy) {
		for (int i = 0; i < ATTACKPICS; i++) {
			playerAttack[i].translateTo(posx, posy);
		}
	}
	
	public boolean isPointInElement(int x, int y) {
		if (Idle.isPointInElement(x, y))
			return true;
		else
			return false;
	}
	
	public int getHealth() {
	    return health;
	}

	// Returns current x position
	public int getXpos() {
	    return posx;
	}

	// Returns current y position
	public int getYpos() {
	    return posy;
	}
	
	
	public void hidePlayer() {
		Idle.hide();
		Attack.hide();
	}
	
	public void translatePlayer (int x, int y) {
		Idle.translateTo(x, y);
	}
	
	public int currentState() {
		return playerState;
	}
	
	
	// Control
	public char processPlayer() {
		switch (playerState) {
		case IDLE:
			hidePlayer();
			Idle.show();
			
			if (EZInteraction.wasKeyPressed('p')) {
				hidePlayer();
				attackAnimation(getXpos(), getYpos());
				Attack.show();
				return 'h';
				
			}
		break;
		}
		return 'h'; // DUMMY
	}
}
