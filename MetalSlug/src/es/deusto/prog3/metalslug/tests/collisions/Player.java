package es.deusto.prog3.metalslug.tests.collisions;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Character {
	
	private Input input;
	private HashMap<String,HashMap<String, Animation>> animations = new HashMap<String,HashMap<String, Animation>>();;
	private HashMap<String,Animation> animation;

	private boolean move = false;
	private boolean firstRun = true;
	private String isFacing;
	
	private boolean movingLeft = false;
	
	public Player() {
		super(200, 200, 31, 38, 300);
		input = new Input(720);
		addAnimation("StandbyHead", AnimationImages.eriStandbyHead, 400);
		addAnimation("StandbyFoot", AnimationImages.eriStandbyFoot, 300);
		addAnimation("RunHead", AnimationImages.eriRunHead, 100);
		addAnimation("RunFoot1", AnimationImages.eriRunFoot1, 100);
		addAnimation("RunFoot2", AnimationImages.eriRunFoot2, 90);
	
		isFacing = "RIGHT";
	}

	private void addAnimation(String name, Image[] images, int duration) {
		animation = new HashMap<String, Animation>();
		
		
		animations.put(name, animation);
		animation.put("RIGHT", new Animation(images, duration));
		
		Animation LeftAnimation = new Animation();
		for(Image i : images) {
			LeftAnimation.addFrame(i.getFlippedCopy(true, false), duration);
		}
		animation.put("LEFT", LeftAnimation);
		
		
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
			move = true;
			
			isFacing = "LEFT";
		}else if(input.isKeyDown(Input.KEY_RIGHT)) {
			moveX(delta, false);
			movingLeft = false;
			
			move = true;
			isFacing = "RIGHT";
		}else {
			move = false;
			firstRun = true;
		}
		
		moveY(delta);
		detectPlatformCollisions();
	}
	
	public boolean getMovingLeft() {
		return movingLeft;
	}
	
	public void drawCabeza() {
		
		if(move) {
			animations.get("RunHead").get(isFacing).draw(x,y);
		}else if(!move){
			animations.get("StandbyHead").get(isFacing).draw(x,y);
			
		}
		
	}

	public void drawPiernas() {
		
		if(move && firstRun) {
			animations.get("RunFoot1").get(isFacing).draw(x,y);
			firstRun = false;
		}else if(move && !firstRun){
			animations.get("RunFoot2").get(isFacing).draw(x,y);
		}else if(!move) {
			animations.get("StandbyFoot").get(isFacing).draw(x,y);
		}
		
		
	}

}
