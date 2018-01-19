package es.deusto.prog3.metalslug.tests.boss;

import java.util.ArrayList;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Bullet extends Point {
	
	private float speed;
	private double sin, cos;
	private float sourceX, sourceY;
	private ArrayList<Shape> platforms;

	public Bullet(float sourceX, float sourceY, float x, float y, float speed) {
		this(sourceX, sourceY, Math.atan2(x - sourceX, sourceY - y), speed);
	}
	
	public Bullet(float sourceX, float sourceY, double angle, float speed) {
		super(sourceX, sourceY);
		this.speed = speed;
		this.cos = Math.cos(angle);
		this.sin = Math.sin(angle);
	}
	
	public void update(int delta) {
		x += speed * sin * delta;
		y += speed * -cos * delta;
	}
	
	public boolean detectCollision(Character player) {
		boolean collided = false;
		
		for(Shape platform : platforms) {
			if(platform.contains(getX(), getY())) {
				collided = true;
			}
		}
		
		if(getY() < 100 || getY() > 720) {
			collided = true;
		}
		
		return collided;
	}

}
