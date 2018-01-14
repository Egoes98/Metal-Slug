package es.deusto.prog3.metalslug.tests.collisions;

import org.newdawn.slick.geom.Circle;

public class Granada extends Circle {
	
	private static final int GRAVITY = 1500;
	private int vx, vy;
	
	
	public Granada(float centerPointX, float centerPointY, boolean goingLeft) {
		super(centerPointX, centerPointY, 5);
		vy = -700;
		vx = goingLeft ? -500 : 500;
	}
	
	public void update(int delta) {
		moveX(delta);
		moveY(delta);
	}

	private void moveY(int delta) {
		// TODO Auto-generated method stub
		float deltaSeconds = delta/1000f;
		setY(getY() + vy * deltaSeconds);
		vy += GRAVITY * deltaSeconds;
	}

	private void moveX(int delta) {
		// TODO Auto-generated method stub
		float deltaSeconds = delta/1000f;
		setX(getX() + vx * deltaSeconds);
	}

}
