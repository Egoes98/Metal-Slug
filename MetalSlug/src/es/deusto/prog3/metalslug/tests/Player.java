package es.deusto.prog3.metalslug.tests;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Rectangle {
	
	private Input input;
	private int vy;
	private boolean hasjumped;
	private static final int GRAVITY = 1500; // In pixels/s^2

	public Player() {
		super(640, 200, 30, 30);
		input = new Input(720);
		
	}
	
	protected void jump() {
		// TODO Auto-generated method stub
		if(!hasjumped) {
			vy = -800;
			setY(getY() - 2);
		}
		hasjumped = true;
	}

	private static final long serialVersionUID = 1L;
	
	public void update(int delta) {
		if(input.isKeyDown(Input.KEY_LEFT))
			moveX(delta, true);
		if(input.isKeyDown(Input.KEY_RIGHT))
			moveX(delta, false);
		
		moveY(delta);
	}

	private void moveY(int delta) {
		// TODO Auto-generated method stub
		float deltaSeconds = delta/1000f;
		for (int i = 0; i < TestGame.platforms.size(); i++) {
			Rectangle rectangle = TestGame.platforms.get(i);
			if(this.intersects(rectangle)) {
				vy = 0;
				hasjumped = false;
				while(this.intersects(rectangle)) {
					this.setY(getY() + (this.getY() < rectangle.getY() ? -0.1f : 0.1f));
				}
				return;
			}
		}
		vy += GRAVITY * deltaSeconds;
		setY(getY() + vy * deltaSeconds);
	}

	private void moveX(int delta, boolean isLeft) {
		// TODO Auto-generated method stub
		float deltaSeconds = delta/1000f;
		for (int i = 0; i < TestGame.platforms.size(); i++) {
			Rectangle rectangle = TestGame.platforms.get(i);
			if(this.intersects(rectangle) && isLeft && this.getY() >= rectangle.getY() && this.getX() >= rectangle.getMaxX() - 5) {
				setX(rectangle.getMaxX() + 0.1f);
				return;
			}
			
			
			if(this.intersects(rectangle) && !isLeft && this.getY() >= rectangle.getY() && this.getX() <= rectangle.getX() + 5) {
				setX(rectangle.getX() - getWidth() - 0.1f);
				return;
			}
		}
		
		setX(getX() + (isLeft ? -300*deltaSeconds : 300*deltaSeconds));
	}

	

}
