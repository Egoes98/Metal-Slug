package es.deusto.prog3.metalslug.tests.collisions;

import org.newdawn.slick.geom.Rectangle;

public class Character extends Rectangle {

	private static final int GRAVITY = 1500;
	protected boolean hasjumped;
	protected int vy;
	private int speed;

	public Character(float x, float y, float width, float height, int speed) {
		super(x, y, width, height);
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}
	
	protected void detectPlatformCollisions() {
		// TODO Auto-generated method stub
		boolean intersected = false;
		for(Rectangle platform : TestGame.platforms) {
			if(this.intersects(platform)) {
				intersected = true;
				float leftOverlap = this.getMaxX() - platform.getMinX();
				float rightOverlap = platform.getMaxX() - this.getMinX();
				float bottomOverlap = this.getMaxY() - platform.getMinY();
				float topOverlap = platform.getMaxY() - this.getMinY();
				float overlappingSide = CollisionUtils.min(leftOverlap, rightOverlap, bottomOverlap, topOverlap);
				
				if(overlappingSide == leftOverlap && bottomOverlap > 10) {
					setX(getX() - leftOverlap);
				} else if(overlappingSide == rightOverlap && bottomOverlap > 10) {
					setX(getX() + rightOverlap);
				} else if(overlappingSide == bottomOverlap) {
					setY(getY() - bottomOverlap);
					if(vy > 0)
						vy = 0;
					hasjumped = false;
				} else if(overlappingSide == topOverlap) {
					if(vy < 0)
						vy = 0;
					setY(getY() + topOverlap + 1);
					
				}
			}
		}
		
		for(Slope slope : TestGame.slopes) {
			// TODO get it to jump properly
			if(slope.contains(getCenterX(), getMaxY() - 0.1f)) {
				intersected = true;
				this.setY(slope.getMaxYIn(getCenterX()) - height);
				vy = 0;
				hasjumped = false;
			}
		}
		
		if(!intersected) {
			hasjumped = true;
		}
		
	}

	protected void moveY(int delta) {
		// TODO Auto-generated method stub
		float deltaSeconds = delta/1000f;
		setY(getY() + vy * deltaSeconds);
		vy += GRAVITY * deltaSeconds;
	}

	protected void moveX(int delta, boolean isLeft) {
		// TODO Auto-generated method stub
		float deltaSeconds = delta/1000f;
		setX(getX() + (isLeft ? -speed*deltaSeconds : speed*deltaSeconds));
	}

}
