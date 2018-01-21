package es.deusto.prog3.metalslug.game.entities;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.geom.Shape;

public class Enemy extends Character {

	private Player player;
	private boolean shooting;
	private boolean movingLeft;
	private boolean dead;
	private int timeCounter;
	private float minX, maxX;
	
	private ArrayList<Bullet> bullets;

	public Enemy(float x, float y, Player player, float minX, float maxX, ArrayList<Bullet> bullets, ArrayList<Shape> platforms) {
		super(x, y, 30, 30, 100, platforms);
		this.player = player;
		dead = false;
		this.minX = minX;
		this.maxX = maxX;
		this.bullets = bullets;
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
		moveX(delta, movingLeft);

		moveY(delta);
		detectPlatformCollisions();
		for (Iterator<Bullet> iterator = playerbullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			if (this.contains(b.getX(), b.getY())) {
				dead = true;
			}
		}
		for (Iterator<Granada> iterator = granadas.iterator(); iterator.hasNext();) {
			Granada g = iterator.next();
			if (this.intersects(g)) {
				dead = true;
			}
		}

		if (timeCounter > 1000 && distanceTo(this.getCenter(), player.getCenter()) < 500) {
			shoot();
			timeCounter = 0;
		}

	}

	public boolean isDead() {
		return dead;
	}
	
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

}
