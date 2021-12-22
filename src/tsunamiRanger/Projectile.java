package tsunamiRanger;

public class Projectile {
	private double posx;
	private double posy;
	private EZImage projectileBullet[] = new EZImage [BULLETPICS];
	private EZImage bullet;
	
	private static final int BULLETPICS = 2;
	private static final int ENEMYPROJECTILESPEED = 2;                       // Speed of enemy projectiles
	private static final double PLAYERSHOOTSPEED = .15;                      // Player shoot speed
	//private static final double PROJECTILEROTATE = .125;                     // Rotation of player projectiles
	private static final float SCALINGFACTOR = 2.5f;                         // Scale objects to same factor
	private static final int DELAY = 1;                                      // Animation delay
	private String type;                                                     // The unit type
	private static final int MAPXSIZE = 1450;
	private boolean using;                                                   // If projectile is currently on map
	private boolean projectileup;
	
	public Projectile (int x, int y, String letter) {
		posx = x;
		posy = y;
		type = "master";
		
		if (letter == "playerBullet") {
			bullet = EZ.addImage("", x, y);
			bullet.hide();
			type = "playerbullet";
			using = false;
			projectileup = false;
		}
		
		if (letter == "piranhaBullet") {
			bullet = EZ.addImage("", x, y);
			bullet.hide();
			type = "piranhaBullet";
		}
		
		if (letter == "crabBullet") {
			bullet = EZ.addImage("", x, y);
			bullet.hide();
			type = "crabBullet";
		}
	}
	
	public boolean beingUsed() { // true jika dipakai
		return using;
	}
	
	public double getXpos() {
		return posx;
	}
	
	public double getYpos() {
		return posy;
	}
	
	public String returnType() {
		return type;
	}
	
	public void translateObject (int x, int y) {
		if (type == "playerBullet") {
			posx = x;
			posy = y;
			bullet.translateTo(posx, posy);
			projectileup = false;
		}
	}
	
	public void translateObjectUp (int x, int y) {
		if (type == "playerBullet") {
			posx = x;
		    posy = y;
		    bullet.translateTo(posx, posy);
		    projectileup = true;
		}
	}
	
	public void hide() {
		if (type == "playerBullet")
			bullet.hide();
	}
	
	public void resetEnemyProjectile(int x, int y) {
		if (type == "piranhaBullet") {
			posx = x;
			posy = y;
			bullet.hide();
			bullet.translateTo(posx -= 60, posy -= 25);
		}
		
		if (type == "crabBullet") {
			posx = x;
			posy = y;
			bullet.hide();
			bullet.translateTo(posx -= 40, posy -= 10);
		}
	}
	
	public void processEnemyProjectile(int x, int y, int health) {
		if (x < 1500) { //if within map
			if (type == "piranhaBullet") {
				if (health > 0)
			        translatePiranhaBullet(x, y);
			    else {
			        bullet.translateTo(-10, 0);
			        bullet.hide();
			    }  
			}
			
			if (type == "crabBullet") {
				if (health > 0)
			        translateCrabBullet(x, y);
			    else {
			        bullet.translateTo(-10, 0);
			        bullet.hide();
			    }  
			}
		}
	}
	
	public void translatePiranhaBullet(int x, int y) {
		bullet.show();
		bullet.pullToFront();
		bullet.translateTo(posx -= ENEMYPROJECTILESPEED, posy);
		if (bullet.getXCenter() <= 0) {
		    resetEnemyProjectile(x, y);
		}
	}
	
	public void translateCrabBullet(int x, int y) {
		bullet.show();
		bullet.pullToFront();
		bullet.translateTo(posx -= ENEMYPROJECTILESPEED, posy);
		if (bullet.getXCenter() <= 0) {
		    resetEnemyProjectile(x, y);
		}
	}
	
	// Controls player's bullet if shooting horizontally
	public void translateBullet(int x, int y) {
	    bullet.show();
	    bullet.pullToFront();
	    bullet.translateTo(posx += PLAYERSHOOTSPEED, posy);
	    if (bullet.getXCenter() > MAPXSIZE || bullet.isPointInElement(x, y)) {
	      bullet.hide();
	      bulletExplosionAnimation(bullet.getXCenter(), bullet.getYCenter());
	      bullet.translateTo(0, 0);
	      using = false;
	    }
	  }

	  // Controls player's bullet if shooting up
	public void translateBulletUp(int x, int y) {
	    bullet.show();
	    bullet.pullToFront();
	    bullet.translateTo(posx, posy -= PLAYERSHOOTSPEED);
	    bullet.rotateTo(-90);

	    if (bullet.getYCenter() <= 10 || bullet.isPointInElement(x, y)) {
	      bullet.hide();
	      bulletExplosionAnimation(bullet.getXCenter(), bullet.getYCenter());
	      bullet.translateTo(0, 0);
	      using = false;
	      projectileup = false;
	      bullet.rotateTo(0);
	    }
	}
	
	public void processProjectile(int x, int y) {
		if (type == "playerbullet" && projectileup == false) {
		    translateBullet(x, y);
		}
		
		if (type == "playerbullet" && projectileup == true) {
		    translateBulletUp(x, y);
		}
	}
	
	public void switchState() {
		if (using == true) {
			using = false;
		} else {
			using = true;
		}
	}
	
	
	public void projectileInit() {
		if (type == "playerBullet")
		    bullet.scaleTo(.9);
		
		if (type == "piranhaBullet")
		    bullet.scaleTo(.9);
		
		if (type == "crabBullet")
		    bullet.scaleTo(.9);
	}
	
	public void animationInit() {
		if (type == "playerBullet") {
			for (int i = 0; i < BULLETPICS; i++) {
				projectileBullet[i] = EZ.addImage("ProjectileBulletExplosion/" + i + ".png", -10, 0);
		        projectileBullet[i].hide();
		        projectileBullet[i].scaleTo(SCALINGFACTOR + 1);
		    }
		}
	}
	
	public boolean isPointInElement(int x, int y) {
		if (type == "playerBullet") {
			if (bullet.isPointInElement(x, y)) return true;
			else return false;
		}
		
		if (type == "crabBullet" || type == "piranhaBullet") {
			if (bullet.isPointInElement(x, y)) return true;
			else return false;
		}
		return false;
	}
	
	
	public void bulletExplosionAnimation(int posx, int posy) {
		translateBulletExplosionAnimation(posx, posy);
		for (int i = 0; i < BULLETPICS; i++) {
			projectileBullet[i].show();
			for (int counter = 0; counter < DELAY; counter++) {
				EZ.refreshScreen();
			}
			projectileBullet[i].hide();
		}
	}
	  
	public void translateBulletExplosionAnimation(int posx, int posy) {
		 for (int i = 0; i < BULLETPICS; i++) {
			projectileBullet[i].translateTo(posx, posy);
		}
	}
}
