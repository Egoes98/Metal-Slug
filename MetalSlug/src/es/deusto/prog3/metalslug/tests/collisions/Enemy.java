package es.deusto.prog3.metalslug.tests.collisions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Enemy extends Character {

	private Player player;
	private boolean shooting = false;
	private Thread changeDirection;
	private Thread stopT;
	private boolean movingLeft;
	private boolean dead;
	private int timeCounter;
	private HashMap<String,HashMap<Boolean, Animation>> animations = new HashMap<String,HashMap<Boolean, Animation>>();
	private HashMap<Boolean, Animation> animation;
	private boolean move = true;
	private boolean stop = false;

	public Enemy(float x, float y, Player player) {
		super(x, y, 78, 111, 100);
		addAnimation("Correr", AnimationImages.soldierWalk, 100);
		addAnimation("Disparar", AnimationImages.soldierShoot, 300);
		this.player = player;
		dead = false;

		changeDirection = new Thread(() -> {
			movingLeft = true;
			while (move) {
				movingLeft = !movingLeft;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
		});
		
		changeDirection.start();
	}

	private void shoot() {
		TestGame.enemyBullets.add(new Bullet(this, player.getCenterX(), player.getCenterY(), 0.5f));
		shooting = true;
		move = false;
	}

	private float distanceTo(float[] a, float[] b) {
		// TODO Auto-generated method stub
		return (float) Math.hypot(a[0] - b[0], a[1] - b[1]);
	}

	public void update(int delta, ArrayList<Bullet> playerbullets, ArrayList<Granada> granadas) {
		timeCounter += delta;
		
		if(!shooting) {
			moveX(delta, movingLeft);
		}else if(shooting) {
			move = false;
			if(!movingLeft) {
				movingLeft = !movingLeft;
				changeDirection.stop();
			}
		}

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
	
	private void addAnimation(String name, Image[] images, int duration) {
		animation = new HashMap<Boolean, Animation>();
		
		
		animations.put(name, animation);
		animation.put(true, new Animation(images, duration));
		
		Animation LeftAnimation = new Animation();
		for(Image i : images) {
			LeftAnimation.addFrame(i.getFlippedCopy(true, false), duration);
		}
		animation.put(false, LeftAnimation);
	
	}
	
	public void draw() {
	
		if(shooting) {
			animations.get("Disparar").get(movingLeft).draw(x,y);
		}else if(!shooting) {
			animations.get("Correr").get(movingLeft).draw(x,y);
		}

	}

	public boolean isDead() {
		return dead;
	}

}
