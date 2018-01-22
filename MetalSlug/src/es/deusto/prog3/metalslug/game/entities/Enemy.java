package es.deusto.prog3.metalslug.game.entities;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.geom.Shape;

public class Enemy extends Character {

	private Player player;
	private boolean shooting;
	private boolean movingLeft;
	private int timeCounter;
	private float minMovementX, maxMovementX;
	
	private ArrayList<Bullet> bullets;
	private boolean moving;

	public Enemy(float x, float y, float minX, float maxX) {
		super(x, y, 30, 30, 100, null);
		this.minMovementX = minX;
		this.maxMovementX = maxX;
		moving = true;
	}

	private void shoot() {
		bullets.add(new Bullet(this.getCenterX(), this.getCenterY(), player.getCenterX(), player.getCenterY(), 0.5f));
	}

	private float distanceTo(float[] a, float[] b) {
		// TODO Auto-generated method stub
		return (float) Math.hypot(a[0] - b[0], a[1] - b[1]);
	}

	public void update(int delta, ArrayList<Bullet> playerbullets, ArrayList<Granada> granadas) {
		timeCounter += delta;
		if(moving)
			moveX(delta, movingLeft);

		moveY(delta);
		detectPlatformCollisions();
		if (distanceTo(this.getCenter(), player.getCenter()) < 500) {
			moving = false;
			if(timeCounter > 1000) {
				shoot();
				timeCounter = 0;
			}
		} else {
			moving = true;
		}
		if(getX() < minMovementX || getX() > maxMovementX) {
			movingLeft = !movingLeft;
		}

	}
	
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public float getMinMovementX() {
		return minMovementX;
	}

	public float getMaxMovementX() {
		return maxMovementX;
	}
	
	

}
