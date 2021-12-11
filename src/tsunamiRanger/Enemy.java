package tsunamiRanger;

import java.lang.String;
import java.util.*;

public class Enemy {
	private int posx;
	private int posy;
	private int destx;
	private int desty;
	private int rangex;
	private int rangey;
	private int NORTH = 1, SOUTH = 2, EAST = 3, WEST = 4;
	
	private boolean flag;
	private final int MAPXLENGTH = 1500;
	private final int MAPYLENGTH = 600;
	private final int HALFX = MAPXLENGTH/2;
	private final int HALFY = MAPYLENGTH/2;
	private int GROUNDHP = 15;
	private int AIRHP = 10;
	private int DEATHHP= -1;
	private int direction;
	private int ENEMYMOVESPEED = 1;
	private EZImage picture;
	private EZImage death;
	private String type;
	private static int deathcounter = 0;
	
	private boolean alive_death;
	private int health;
	private static final float SCALINGFACTOR = 2.5f;
	private static int playerscore[][] = new int[1][2];
	
	
	public Enemy(int x, int y, String character, int rx, int ry) {
		if (character == "piranha") {
			posx = x;
			posy = y;
			picture = EZ.addImage("", posx, posy);
			death = EZ.addImage("", posx, posy);
			flag = true;
			rangex = rx;
			rangey = ry;
			alive_death = true;
			death.pushToBack();
			death.hide();
			setRandomDirection();
			type = "piranha";
			health = AIRHP;
		}
		
		if (character == "crab") {
			posx = x;
			posy = y;
			picture = EZ.addImage("", posx, posy);
			death = EZ.addImage("", posx, posy);
			flag = true;
			rangex = rx;
			rangey = ry;
			alive_death = true;
			death.pushToBack();
			death.hide();
			setRandomDirection();
			type = "crab";
			health = GROUNDHP;
		}
	}
	
	public void unitsInit() {
		if (type == "piranha") {
			picture.scaleTo(SCALINGFACTOR);
			death.scaleTo(SCALINGFACTOR);
		}
		
		if (type == "crab") {
			picture.scaleTo(SCALINGFACTOR);
			death.scaleTo(SCALINGFACTOR);
		}
	}
	
	public int getXCenter() {
		return posx;
	}
	
	public int getYCenter() {
		return posy;
	}
	
	public boolean getAliveorDead() {
		return alive_death;
	}
	
	public void collision() {
		if (type == "piranha") {
			health -= 2;
			playerscore[0][0] += 10;
			
			if (health <= 0) {
				translateFirstDeathPictures();
				deadTrigger();
			}
			
			if (health <= DEATHHP && alive_death == true) {
				translateSecondDeathPictures();
			}
		}
		
		if (type == "crab") {
			health -= 2;
			playerscore[0][0] += 10;
			
			if (health <= 0) {
				translateFirstDeathPictures();
				deadTrigger();
			}
			
			if (health <= DEATHHP && alive_death == true) {
				translateSecondDeathPictures();
			}
		}
	}
	
	public void translateFirstDeathPictures() {
		picture.hide();
		picture.translateTo(0,0);
	}

	private void translateSecondDeathPictures() {
	    posx = 0;
	    posy = 0;
	    alive_death = false;
	    death.hide();
	    death.translateTo(posx, posy);
	    deathcounter++;
	}
	
	public int getPlayerScore() {
		return playerscore[0][0];
	}
	
	private void deadTrigger() {
		death.pullToFront();
		death.show();
	}
	
	public int getHealth() {
		return health;
	}
	
	public void move() {
		if (health > 0) {
			// Ground Enemy
			if (type == "crab") {
				if (posx > destx) moveLeft (ENEMYMOVESPEED);
				if (posx < destx) moveRight (ENEMYMOVESPEED);
				
				if (posx == destx) setRandomDirection();
			}
		
			else {
				if (posx > destx) moveLeft(ENEMYMOVESPEED);
		        if (posx < destx) moveRight(ENEMYMOVESPEED);
		        if (posy > desty) moveUp(ENEMYMOVESPEED);
		        if (posy < desty) moveDown(ENEMYMOVESPEED);
		        if ((posx == destx) && (posy == desty)) setRandomDirection();
			}
		}
	}
	
	public void setRandomDirection() {
		Random randomGenerator = new Random();
		
		int ranx = randomGenerator.nextInt(rangex);
		int rany = randomGenerator.nextInt(rangey);
		
		if (type == "piranha") {
			while (ranx <= HALFX + 200 || rany < 50 || rany > 580) {
				ranx = randomGenerator.nextInt(rangex);
				rany = randomGenerator.nextInt(rangey);
			}
		}
		
		if (type == "crab") {
			rany = 500;
			while (ranx <= HALFX + 200 || rany != 500) {
				ranx = randomGenerator.nextInt(rangex);
			}
		}
		
		setDestination(ranx, rany);
		
		if (ranx > HALFX && ranx <= MAPXLENGTH && rany >= 0 && rany <= HALFY)
			setDirection(EAST);
		if (ranx >= 0 && ranx <= HALFX && rany >= 0 && rany <= HALFY)
		    setDirection(NORTH);
		if (ranx >= 0 && ranx <= HALFX && rany > HALFY && rany <= MAPYLENGTH)
		    setDirection(WEST);
		if (ranx > HALFX && ranx <= MAPXLENGTH && rany > HALFY && rany <= MAPYLENGTH)
		    setDirection(SOUTH);
	}
	
	public void setDestination(int x, int y) {
		destx = x;
		desty = y;
	}
	
	public void moveLeft(int step) {
		posx = posx - step;
		setImagePosition (posx, posy);
	}
	
	public void moveRight(int step) {
		posx = posx + step;
		setImagePosition (posx, posy);
	}
	
	public void moveUp(int step) {
		posx = posx - step;
		setImagePosition (posx, posy);
	}
	
	public void moveDown(int step) {
		posx = posx - step;
		setImagePosition (posx, posy);
	}
	
	private void setImagePosition (int posx, int posy) {
		if (flag) {
			picture.translateTo(posx, posy);
			death.translateTo(posx, posy);
		}
	}
	
	public int returnDeathCOunter() {
		return deathcounter;
	}
	
	public void changeflag() {
		if (flag == true) flag = false;
		else flag = true;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
