package es.deusto.prog3.metalslug.tests.boss;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Boss extends Circle {
	
	private ArrayList<Shape> platforms;
	private ArrayList<Bullet> enemyBullets;
	private float vx, vy;
	private int aliveTime = 0;

	public Boss(ArrayList<Shape> platforms, ArrayList<Bullet> enemyBullets) {
		super(600, 300, 80);
		this.platforms = platforms;
		this.enemyBullets = enemyBullets;
		vx = vy = 0.2f;
	}
	
	public void update(int delta) {
		setX(getX() + vx * delta);
		setY(getY() + vy * delta);
		detectCollisions();
		aliveTime += delta;
		if(aliveTime > 500) {
			aliveTime = 0;
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), getCenterX() + 1, getCenterY(), 0.8f));
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), getCenterX() - 1, getCenterY(), 0.8f));
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), getCenterX(), getCenterY() + 1, 0.8f));
			enemyBullets.add(new Bullet(getCenterX(), getCenterY(), getCenterX(), getCenterY() - 1, 0.8f));
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
				} else if (overlappingSide == topOverlap || overlappingSide == bottomOverlap) {
					vy = -vy;
				}
			}
		}
	}
	
	

}
