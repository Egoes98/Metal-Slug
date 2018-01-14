package es.deusto.prog3.metalslug.tests.collisions;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class Bullet extends Point {
	
	private float speed;
	private double sin, cos;

	public Bullet(Character player, float x, float y, float speed) {
		super(player.getCenterX(), player.getCenterY());
		this.speed = speed;
		double angle = Math.atan2(x - player.getCenterX(), player.getCenterY() - y);
		sin = Math.sin(angle);
		cos = Math.cos(angle);
	}
	
	public void update(int delta) {
		x += speed * sin * delta;
		y += speed * -cos * delta;
	}
	
	public boolean detectCollision(Character player) {
		boolean collided = false;
		
		for(Rectangle platform : TestGame.platforms) {
			if(platform.contains(getX(), getY())) {
				collided = true;
			}
		}
		
		for(Slope slope : TestGame.slopes) {
			if(slope.contains(getX(), getY())) {
				collided = true;
			}
		}
		
		if(getY() < 100 || getY() > 720) {
			collided = true;
		}
		
		if(player.getCenterX() < 640) {
			if(x < 0 || x > 1280) {
				collided = true;
			}	
		} else {
			if(x < player.getCenterX() - 640 || x > player.getCenterX() + 640) {
				collided = true;
			}
		}
		
		return collided;
	}

}
