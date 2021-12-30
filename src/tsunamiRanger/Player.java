package tsunamiRanger;

import java.awt.event.KeyEvent;

public class Player {
	// Player image assets
	
	
	// Player stats
	private EZImage Stand;
	private EZImage Shoot;
	private EZImage Crouch;
	private EZImage Jump;
	private EZImage Land;
	private EZImage Up;
	private EZImage Knife;
	private EZImage Grenade;
	private EZImage Death1;
	private EZImage Death2;
	private EZImage Victory;
	private EZImage Left;
	private int health;
	
	
	private EZImage playerShooting[] = new EZImage[SHOOTINGPICS];
	private EZImage playerWalking[] = new EZImage[WALKINGPICS];
	private EZImage playerKnife[] = new EZImage[KNIFEPICS];
	private EZImage playerTurn[] = new EZImage[TURNPICS];
	private EZImage playerUp[] = new EZImage[UPPICS];
	private EZImage playerReload[] = new EZImage[RELOADPICS];
	private EZImage playerIdle[] = new EZImage[IDLEPICS];
	private EZImage playerGrenade[] = new EZImage[GRENADEPICS];
	private EZImage playerCrouchGrenade[] = new EZImage[CROUCHGRENADEPICS];
	private EZImage playerCrouchShoot[] = new EZImage[CROUCHSHOOTPICS];
	private EZImage playerVictory[] = new EZImage[VICTORYPICS];
	
	//	Player directions
	private int direction;
	private int posx;
	private int posy;
	private int playerState;
	private static final int MAPXLENGTH = 1440;
	
	// State value
	private static final int STAND = 1;
	private static final int CROUCH = 3;
	private static final int JUMP = 4;
	private static final int LAND = 5;
	private static final int UP = 6;
	private static final int LEFT = 101;
	private static final int RIGHT = 100;
	
	// Movement
	private static final float MOVE_SPEED = 3.75f;
	private static final float JUMP_SPEED = 4f;
	private static final float LAND_SPEED = 4f;
	private static final int JUMPLENGTH = 160;
	private static final int DELAY = 3;
	private static final int HEALTH = 500;
	
	
	// Smooth Movement
	private static final int SHOOTINGPICS = 0;
	private static final int WALKINGPICS = 0;
	private static final int KNIFEPICS = 0;
	private static final int TURNPICS = 0;
	private static final int UPPICS = 0;
	private static final int RELOADPICS = 18;
	private static final int RELOADTIME = 150;
	private static final int IDLEPICS = 0;
	private static final int GRENADEPICS = 0;
	private static final int CROUCHGRENADEPICS = 0;
	private static final int CROUCHSHOOTPICS = 0;
	private static final int VICTORYPICS = 6;
	
	private int reloadCounter = 0;
	private float jumpCounter = 0;
	private static final float SCALINGFACTOR = 2.5f;
	
	// Constructor for Player
	public Player(int x, int y) {
		posx = x;
		posy = y;
		
		Stand = EZ.addImage("assets/PlayerStanding/0.png", posx, posy);
		Shoot = EZ.addImage("assets/PlayerShooting/7.png", posx, posy);
		Crouch = EZ.addImage("assets/Player/PlayerCrouch.png", posx, posy + 15);
		Jump = EZ.addImage("assets/Player/PlayerJump.png", posx, posy);
		Land = EZ.addImage("assets/Player/PlayerLand.png", posx, posy);
		Up = EZ.addImage("assets/Player/PlayerUp.png", posx, posy);
		Left = EZ.addImage("assets/Player/PlayerLeft.png", posx, posy);
		Knife = EZ.addImage("assets/Player/PlayerKnife.png", posx, posy);
		Grenade = EZ.addImage("assets/Player/PlayerGrenade.png", posx, posy);
		Death1 = EZ.addImage("assets/Player/PlayerDeath1.png", posx, posy);
		Death2 = EZ.addImage("assets/Player/PlayerDeath2.png", posx, posy);
		Victory = EZ.addImage("assets/Player/PlayerVictory.png", posx, posy);
		
		direction = RIGHT;
		playerState = STAND;
		hidePlayer();
		Stand.show();
		health = HEALTH;
		// need asset for stand condition
		
	}
	
	// Animations
	public void animationInit() {
		Stand.scaleTo(SCALINGFACTOR);
		Shoot.scaleTo(SCALINGFACTOR);
	    Crouch.scaleTo(SCALINGFACTOR);
	    Jump.scaleTo(SCALINGFACTOR);
	    Land.scaleTo(SCALINGFACTOR);
	    Up.scaleTo(SCALINGFACTOR);
	    Knife.scaleTo(SCALINGFACTOR);
	    Grenade.scaleTo(SCALINGFACTOR);
	    Death1.scaleTo(SCALINGFACTOR);
	    Death2.scaleTo(SCALINGFACTOR);
	    Victory.scaleTo(SCALINGFACTOR);
	    Left.scaleTo(SCALINGFACTOR);
		
	    for (int i = 0; i < SHOOTINGPICS; i++) {
	        playerShooting[i] = EZ.addImage("assets/PlayerShooting/" + i + ".png", posx, posy);
	        playerShooting[i].hide();
	        playerShooting[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < WALKINGPICS; i++) {
	        playerWalking[i] = EZ.addImage("assets/PlayerWalking/" + i + ".png", posx, posy);
	        playerWalking[i].hide();
	        playerWalking[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < KNIFEPICS; i++) {
	        playerKnife[i] = EZ.addImage("assets/PlayerKnife/" + i + ".png", posx, posy);
	        playerKnife[i].hide();
	        playerKnife[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < TURNPICS; i++) {
	        playerTurn[i] = EZ.addImage("assets/PlayerDirection/" + i + ".png", posx, posy);
	        playerTurn[i].hide();
	        playerTurn[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < UPPICS; i++) {
	        playerUp[i] = EZ.addImage("assets/PlayerUp/" + i + ".png", posx, posy);
	        playerUp[i].hide();
	        playerUp[i].scaleTo(SCALINGFACTOR);
	    }
	    for (int i = 0; i < RELOADPICS; i++) {
	        playerReload[i] = EZ.addImage("assets/PlayerReload/" + i + ".png", posx, posy);
	        playerReload[i].hide();
	        playerReload[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < IDLEPICS; i++) {
	        playerIdle[i] = EZ.addImage("assets/PlayerIdle/" + i + ".png", posx, posy);
	        playerIdle[i].hide();
	        playerIdle[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < GRENADEPICS; i++) {
	        playerGrenade[i] = EZ.addImage("assets/PlayerGrenade/" + i + ".png", posx, posy);
	        playerGrenade[i].hide();
	        playerGrenade[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < CROUCHGRENADEPICS; i++) {
	        playerCrouchGrenade[i] = EZ.addImage("assets/PlayerCrouchGrenade/" + i + ".png", posx, posy);
	        playerCrouchGrenade[i].hide();
	        playerCrouchGrenade[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < CROUCHSHOOTPICS; i++) {
	        playerCrouchShoot[i] = EZ.addImage("assets/PlayerCrouchShoot/" + i + ".png", posx, posy);
	        playerCrouchShoot[i].hide();
	        playerCrouchShoot[i].scaleTo(SCALINGFACTOR);
	    }
	    
	    for (int i = 0; i < VICTORYPICS; i++) {
	        playerVictory[i] = EZ.addImage("assets/PlayerVictory/" + i + ".png", -20, 0);
	        playerVictory[i].hide();
	        playerVictory[i].scaleTo(SCALINGFACTOR);
	  
	    }
	}
	
	// Refresh Screen
	
	// need turn
	public void shootingAnimation(int posx, int posy) {
		translateShootingAnimation(posx, posy);
	    for (int i = 0; i < IDLEPICS; i++) {
	    	playerShooting[i].show();
	    	EZ.refreshScreen();
	    	playerShooting[i].hide();
	    }
	}
	
	 public void victoryAnimation() {
	    for (int i = 0; i < VICTORYPICS; i++) {
			playerVictory[i].show();
			for (int j = 0; j < 20; j++) {
				EZ.refreshScreen();
			}
			playerVictory[i].hide();
	    }
	 }
	
	 
	 public void walkingAnimation() {
	    for (int i = 0; i < WALKINGPICS; i++) {
			posx += MOVE_SPEED;
			translateWalkingAnimation(getXpos(), getYpos());
			playerWalking[i].show();
			EZ.refreshScreen();
			playerWalking[i].hide();
	    }
	}
	 
	 
	 public void knifeAnimation(int posx, int posy) {
		translateKnifeAnimation(posx, posy);
	    for (int i = 0; i < KNIFEPICS; i++) {
			playerKnife[i].show();
			for (int counter = 0; counter < DELAY; counter++) {
				EZ.refreshScreen();
			}
			playerKnife[i].hide();
	    }
	}
	 
	 public void turnAnimation(char side) {
	    translateTurnAnimation(getXpos(), getYpos());
	    if (side == 'd' && direction == LEFT) {
			for (int i = TURNPICS - 1; i < 0; i--) {
				playerTurn[i].show();
				EZ.refreshScreen();

				playerTurn[i].hide();
			}
	    }
	    if (side == 'a' && direction == RIGHT) {
	    	for (int i = 0; i < TURNPICS; i++) {
	        	playerTurn[i].show();
	        	EZ.refreshScreen();
	        	playerTurn[i].hide();
	      	}
	    }
	}
	 
	 public void UpAnimation(int posx, int posy) {
	    translateUpAnimation(posx - 10, posy - 16);
	    for (int i = 0; i < UPPICS; i++) {
			playerUp[i].show();
			EZ.refreshScreen();
			playerUp[i].hide();
	    }
	}
	 
	 public void reloadAnimation(int posx, int posy) {
	    translateReloadAnimation(posx, posy);
	    for (int i = 0; i < RELOADPICS; i++) {
			playerReload[i].show();
			EZ.refreshScreen();
			for (int counter = 0; counter < DELAY; counter++) {
				EZ.refreshScreen();
			}
			playerReload[i].hide();
	    }
	}
	 
	public void idleAnimation(int posx, int posy) {
		translateIdleAnimation(posx, posy);
	    for (int i = 0; i < IDLEPICS; i++) {
	    	playerIdle[i].show();
	    	EZ.refreshScreen();
	    	playerIdle[i].hide();
	    }
	}
	
	public void grenadeAnimation(int posx, int posy) {
	    translateGrenadeAnimation(posx, posy);
	    for (int i = 0; i < GRENADEPICS; i++) {
			playerGrenade[i].show();
			EZ.refreshScreen();
			playerGrenade[i].hide();
	    }
	}
	
	public void crouchGrenadeAnimation(int posx, int posy) {
	    translateCrouchGrenadeAnimation(posx - 10, posy + 10);
	    for (int i = 0; i < CROUCHGRENADEPICS; i++) {
			playerCrouchGrenade[i].show();
			EZ.refreshScreen();
			playerCrouchGrenade[i].hide();
	    }
	}
	
	public void crouchShootAnimation(int posx, int posy) {
	    translateCrouchShootAnimation(posx - 10, posy + 10);
	    for (int i = 0; i < CROUCHSHOOTPICS; i++) {
			playerCrouchShoot[i].show();
			EZ.refreshScreen();
			playerCrouchShoot[i].hide();
	    }
	}
	
	public void translateVictoryAnimation(int posx, int posy) {
	    for (int i = 0; i < VICTORYPICS; i++) {
	      	playerVictory[i].translateTo(posx, posy);
	    }
	}

	public void translateCrouchShootAnimation(int posx, int posy) {
	    for (int i = 0; i < CROUCHSHOOTPICS; i++) {
	      	playerCrouchShoot[i].translateTo(posx, posy);
	    }
	}

	public void translateCrouchGrenadeAnimation(int posx, int posy) {
	    for (int i = 0; i < CROUCHGRENADEPICS; i++) {
	      	playerCrouchGrenade[i].translateTo(posx, posy);
	    }
	}

	public void translateGrenadeAnimation(int posx, int posy) {
	    for (int i = 0; i < GRENADEPICS; i++) {
	      	playerGrenade[i].translateTo(posx, posy);
	    }
	}

	public void translateIdleAnimation(int posx, int posy) {
	    for (int i = 0; i < IDLEPICS; i++) {
	      	playerIdle[i].translateTo(posx, posy);
	    }
	}

	public void translateReloadAnimation(int posx, int posy) {
	    for (int i = 0; i < RELOADPICS; i++) {
	      	playerReload[i].translateTo(posx, posy);
	    }
	}

	public void translateUpAnimation(int posx, int posy) {
	    for (int i = 0; i < UPPICS; i++) {
	      	playerUp[i].translateTo(posx, posy);
	    }
	}

	public void translateTurnAnimation(int posx, int posy) {
	    for (int i = 0; i < TURNPICS; i++) {
	      	playerTurn[i].translateTo(posx, posy);
	    }
	}

	public void translateKnifeAnimation(int posx, int posy) {
	    for (int i = 0; i < KNIFEPICS; i++) {
	      	playerKnife[i].translateTo(posx, posy);
	    }
	}

	public void translateWalkingAnimation(int posx, int posy) {
	    for (int i = 0; i < WALKINGPICS; i++) {
	      	playerWalking[i].translateTo(posx, posy);
	    }
	}

	public void translateShootingAnimation(int posx, int posy) {
	    for (int i = 0; i < SHOOTINGPICS; i++) {
	      	playerShooting[i].translateTo(posx, posy);
	    }
	}
	
	public boolean isPointInElement(int x, int y) {
		if (Stand.isPointInElement(x, y) || Jump.isPointInElement(x, y))
			return true;
		else
			return false;
	}
	
	public void collision() {
		health--;
		//If dead
		if (health <= 0) {
		  //Currently player can't die
		}
	}
	
	  // Returns player health
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
	
	  // Returns reload number
	public int getReloadCounter() {
		return reloadCounter;
	}
	
	  // Controls when to reload
	public void reload() {
		// Reload
		if (reloadCounter == RELOADTIME) {
			hidePlayer();
			reloadAnimation(getXpos(), getYpos());
			Stand.show();
			reloadCounter = 0;
		}
	}
	
	
	public void hidePlayer() {
		Stand.hide();
	    Shoot.hide();
	    Crouch.hide();
	    Jump.hide();
	    Land.hide();
	    Up.hide();
	    Knife.hide();
	    Grenade.hide();
	    Death1.hide();
	    Death2.hide();
	    Victory.hide();
	    Left.hide();
	}
	
	public void translatePlayer (int x, int y) {
		Stand.translateTo(x, y);
	    Shoot.translateTo(x, y);
	    Crouch.translateTo(x, y + 15);
	    Jump.translateTo(x, y);
	    Land.translateTo(x, y);
	    Up.translateTo(x, y);
	    Knife.translateTo(x, y);
	    Grenade.translateTo(x, y);
	    Death1.translateTo(x, y);
	    Death2.translateTo(x, y);
	    Victory.translateTo(x, y);
	    Left.translateTo(x, y);
	}
	
	public int currentState() {
		return playerState;
	}
	
	
	// Control
	public char processPlayer() {
	    switch (playerState) {
			case STAND:

				hidePlayer();
				Stand.show();

				// Shoot
				if (EZInteraction.wasKeyPressed('k')) {
					hidePlayer();
					shootingAnimation(getXpos(), getYpos());
					Shoot.show();
					reloadCounter++;
					return 'k';
				}
				// Knife
				else if (EZInteraction.isKeyDown('j')) {
					hidePlayer();
					knifeAnimation(getXpos(), getYpos());
					Knife.show();
					reloadCounter++;
					return 'q';
				}
				// Jump
				else if (EZInteraction.wasKeyPressed(KeyEvent.VK_SPACE)) {
					playerState = JUMP;
					jumpCounter = 0;
					hidePlayer();
					Jump.show();
				}
				// Crouch
				else if (EZInteraction.isKeyDown('s')) {
					playerState = CROUCH;
					hidePlayer();
					Crouch.show();
				}
				// Looking up
				else if (EZInteraction.isKeyDown('w')) {
					playerState = UP;
					hidePlayer();
					Up.show();
				}
				// Grenade
				else if (EZInteraction.wasKeyPressed('l')) {
					hidePlayer();
					grenadeAnimation(getXpos(), getYpos());
					Stand.show();
					reloadCounter++;
					return 'l';
				}
				// Move right
				else if (EZInteraction.isKeyDown('d')) {
					if (posx <= MAPXLENGTH)
						posx += MOVE_SPEED;
					hidePlayer();
					//turnAnimation('d');
					walkingAnimation();
					translatePlayer(posx, posy);
					Stand.show();
					//direction = RIGHT;
				}
				// Move left
				else if (EZInteraction.isKeyDown('a')) {
					if (posx >= 50)
						posx -= MOVE_SPEED;
					hidePlayer();
					//turnAnimation('a');
					translatePlayer(posx, posy);
					Left.show();
					//direction = LEFT;
				}

			break;

			case UP:
				// If looking up key is held down
				if (EZInteraction.isKeyDown('w')) {
					hidePlayer();
					//UpAnimation(getXpos(), getYpos());
					Up.show();

					// Move left
					if (EZInteraction.isKeyDown('a')) {
						if (posx >= 50)
						posx -= MOVE_SPEED;
						translatePlayer(posx, posy);
					}
					// Move right
					if (EZInteraction.isKeyDown('d')) {
						if (posx <= MAPXLENGTH)
						posx += MOVE_SPEED;
						translatePlayer(posx, posy);
					}
					// If shoot, return key for shooting up
					if (EZInteraction.wasKeyPressed('k')) {
						return 'z';
					}
					// If grenade, return key for grenade up
					if (EZInteraction.wasKeyPressed('l')) {
						return 'x';
				}
				}

				// If release up key
				else {
					playerState = STAND;
					hidePlayer();
					Stand.show();
				}
			break;

			case CROUCH:
				// If holding crouch key down
				if (EZInteraction.isKeyDown('s')) {
					hidePlayer();
					Crouch.show();

					// Shooting in crouch, return key for crouch shoot
					if (EZInteraction.wasKeyPressed('k')) {
						hidePlayer();
						crouchShootAnimation(getXpos(), getYpos());
						//reload();
						Crouch.show();
						//reloadCounter++;
						return 'm';
					}

				// Grenade in crouch, return key for grenade shoot
					if (EZInteraction.wasKeyPressed('l')) {
						hidePlayer();
						crouchGrenadeAnimation(getXpos(), getYpos());
						//reload();
						Crouch.show();
						//reloadCounter++;
						return 'n';
					}
				}
				// If release crouch key
				else {
				playerState = STAND;
				hidePlayer();
				Stand.show();
				}
			break;

			case JUMP:
				// Jump up
				jumpCounter += JUMP_SPEED;
				// Jump up until jump height
				if (jumpCounter > JUMPLENGTH) {
					playerState = LAND;
					hidePlayer();
					Land.show();
				}
				// If reach height, then land
				else {
					posy -= LAND_SPEED;
					translatePlayer(posx, posy);
					// Shooting while jumping, return key for jump shoot
					if (EZInteraction.wasKeyPressed('k')) {
						reloadCounter++;
						return 'o';
					}
					// Grenade while jumping, return key for jump shoot
					if (EZInteraction.wasKeyPressed('l')) {
						reloadCounter++;
						return 'i';
					}
				}
			break;

			case LAND:
				// Bring player down
				jumpCounter -= JUMP_SPEED;
				//Land until reach ground
				if (jumpCounter <= 0) {
				playerState = STAND;
				hidePlayer();
				Stand.show();
				}
				// If reach ground
				else {
					posy += LAND_SPEED;
					translatePlayer(posx, posy);
					// Shoot while landing, return key for land shoot
					if (EZInteraction.wasKeyPressed('k')) {
						reloadCounter++;
						return 'u';
					}
					// Grenade while jumping, return key for land shoot
					if (EZInteraction.wasKeyPressed('l')) {
						reloadCounter++;
						return 't';
					}
				}
	    }

	    // Dummy key
	    return 'h';
	}
}
