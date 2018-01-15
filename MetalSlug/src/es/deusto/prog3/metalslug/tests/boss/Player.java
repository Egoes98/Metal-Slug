package es.deusto.prog3.metalslug.tests.boss;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Character {
	
	private Input input;
	private boolean movingLeft = false;
	public Player() {
		super(200, 200, 30, 30, 300);
		input = new Input(720);		
	}
	
	protected void jump() {
		// TODO Auto-generated method stub
		System.out.println(hasjumped);
		if(!hasjumped) {
			vy = -800;
			setY(getY() - 2);
		}
		hasjumped = true;
	}

	private static final long serialVersionUID = 1L;
	
	public void update(int delta) {
		if(input.isKeyDown(Input.KEY_LEFT)) {
			moveX(delta, true);
			movingLeft = true;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			moveX(delta, false);
			movingLeft = false;
		}
		
		moveY(delta);
		detectCollisions();
	}
	
	public boolean getMovingLeft() {
		return movingLeft;
	}
	

}
