package es.deusto.prog3.metalslug.game.entities;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.utils.CollisionUtils;

public class Boss extends Circle {
	
	private ArrayList<Shape> platforms;
	private ArrayList<Bullet> enemyBullets;
	private float vx, vy;
	private int aliveTime = 0;
	private int health;
	private double angle;

	public Boss(ArrayList<Shape> platforms, ArrayList<Bullet> enemyBullets) {
		super(600, 300, 60);
		this.platforms = platforms;
		this.enemyBullets = enemyBullets;
		vx = vy = 0.2f;
		health = 100;
		angle = 0;
	}
	
	public void update(int delta) {
		setX(getX() + vx * delta);
		setY(getY() + vy * delta);
		detectCollisions();
		aliveTime += delta;
		angle += 0.01*delta;
		if(aliveTime > 200) {
			aliveTime = 0;
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), angle, 0.5f));
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), angle + Math.PI/2, 0.5f));
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), angle + Math.PI, 0.5f));
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), angle - Math.PI/2, 0.5f));
		}
	}
	
	public void detectCollisions() {
		for(Shape platform : platforms) {
			if (this.intersects(platform)) {
				float leftOverlap = this.getMaxX() - platform.getMinX();
				float rightOverlap = platform.getMaxX() - this.getMinX();
				float bottomOverlap = this.getMaxY() - platform.getMinY();
				float topOverlap = platform.getMaxY() - this.getMinY();
				float overlappingSide = CollisionUtils.min(leftOverlap, rightOverlap, bottomOverlap, topOverlap);
				
				if (overlappingSide == leftOverlap || overlappingSide == rightOverlap) {
					vx = -vx;
					setX(getX() + Math.signum(vx)*3);
				} else if (overlappingSide == topOverlap || overlappingSide == bottomOverlap) {
					vy = -vy;
					setY(getY() + Math.signum(vy)*3);
				}
			}
		}
	}
	
	public void hit() {
		health -= 2;
	}
	
	public int getHealth() {
		return health;
	}
	
	

}
