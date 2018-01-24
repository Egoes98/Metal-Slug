package es.deusto.prog3.metalslug.game.entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.game.data.AnimationImages;

public class Player extends Character {
	
	private Input input;
	private HashMap<String,HashMap<String, Animation>> animations = new HashMap<String,HashMap<String, Animation>>();;
	private HashMap<String,Animation> animation;

	private boolean move = false;
	private boolean firstRun = true;
	private String isFacing;
	
	private boolean movingLeft = false;
	private boolean shoot = false;
	
	private boolean canShoot = false;
	
	private int lives;
	private ArrayList<Bullet> bullets;
	private boolean hasShot;
	private boolean done = false;
	
	private int score;
	private boolean restart = false;
	
	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public Player(ArrayList<Shape> platforms, ArrayList<Bullet> bullets) {
		super(200, 200, 93, 114, 300, platforms);
		this.bullets = bullets;
		input = new Input(720);
		addAnimation("StandbyHead", AnimationImages.eriStandbyHead, 400);
		addAnimation("StandbyFoot", AnimationImages.eriStandbyFoot, 300);
		addAnimation("RunHead", AnimationImages.eriRunHead, 100);
		addAnimation("RunFoot1", AnimationImages.eriRunFoot1, 100);
		addAnimation("RunFoot2", AnimationImages.eriRunFoot2, 90);
		addAnimation("Shoot", AnimationImages.eriShoot, 50);
		addAnimation("JumpHead1", AnimationImages.eriJumpHead1, 270);
		addAnimation("JumpFoot1", AnimationImages.eriJumpFoot1, 270);
		addAnimation("JumpHead2", AnimationImages.eriJumpHead2, 270);
		addAnimation("JumpFoot2", AnimationImages .eriJumpFoot2, 270);
		addAnimation("Die", AnimationImages.eriDie, 100);
		animations.get("Die").get("RIGHT").setLooping(false);
		animations.get("Die").get("LEFT").setLooping(false);
		isFacing = "RIGHT";
		lives = 3;
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

	public void jump() {
		// TODO Auto-generated method stub
		if(!hasjumped) {
			vy = -800;
			setY(getY() - 2);
		}
		hasjumped = true;
	}

	private static final long serialVersionUID = 1L;
	
	public void update(int delta) {
		
		if(input.isKeyDown(Input.KEY_A)) {
			moveX(delta, true);
			movingLeft = true;
			move = true;
			
			isFacing = "LEFT";
		}else if(input.isKeyDown(Input.KEY_D)) {
			moveX(delta, false);
			movingLeft = false;
			
			move = true;
			isFacing = "RIGHT";
		}else {
			move = false;
			firstRun = true;
		}
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			shoot  = true;
		}else {
			shoot = false;
		}
		
		moveY(delta);
		detectPlatformCollisions();
		
		if(animations.get("Shoot").get(isFacing).getFrame() == 1) {
			if(!hasShot) {
				canShoot = true;
				hasShot = true;
			}
		} else {
			canShoot = false;
			hasShot = false;
		}
		
		
	}
	
	public boolean getMovingLeft() {
		return movingLeft;
	}

	public void drawCabeza() {
		
		if(move && shoot && isFacing == "LEFT") {
			animations.get("Shoot").get(isFacing).draw(x-72,y);
		}else if(move && shoot) {
			animations.get("Shoot").get(isFacing).draw(x+3,y);
		}else if(move && hasjumped && isFacing == "LEFT"){
			animations.get("JumpHead2").get(isFacing).draw(x+18,y-12);
		}else if(move && hasjumped && isFacing == "RIGHT"){
			animations.get("JumpHead2").get(isFacing).draw(x-10,y-12);
		}else if(move) {
			animations.get("RunHead").get(isFacing).draw(x,y);
		}else if(!move && shoot && isFacing == "LEFT"){
			animations.get("Shoot").get(isFacing).draw(x-72,y);
		}else if(!move && shoot){
			animations.get("Shoot").get(isFacing).draw(x+3,y);
		}else if (!move && hasjumped) {
			animations.get("JumpHead1").get(isFacing).draw(x,y);
		}else if (!move) {
			animations.get("StandbyHead").get(isFacing).draw(x,y);
		}
		
	}

	public void drawPiernas() {
		if(move && firstRun) {
			animations.get("RunFoot1").get(isFacing).draw(x,y);
			firstRun = false;
		}else if(move && !firstRun){
			animations.get("RunFoot2").get(isFacing).draw(x,y);
		}else if(!move && hasjumped) {
			animations.get("JumpFoot1").get(isFacing).draw(x,y);
		}else if(!move) {
			animations.get("StandbyFoot").get(isFacing).draw(x,y);
		}
	}
	
	public boolean getDone() {
		return done ;
	}
	
	public int getLives() {
		return lives;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		super.die();
		if(done) lives -= 1;

		if(lives > 0 && done) {
			this.setLocation(200, 200);
			this.setDead(false);
			done = false;
			restart  = true;
		}
			
	}

	public boolean isCanShoot() {
		return canShoot;
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}

	public float getShootingX() {
		if(isFacing.equals("RIGHT")) {
			return getMaxX();
		} else {
			return getMinX();
		}
	}
	
	public float getShootingY() {
		return getY() + 30;
	}
	
	public void stop() {
		
			animations.get("RunFoot1").get(isFacing).stop();
			animations.get("RunFoot2").get(isFacing).stop();
			animations.get("JumpFoot1").get(isFacing).stop();
			animations.get("StandbyFoot").get(isFacing).stop();
			animations.get("JumpHead2").get(isFacing).stop();		
			animations.get("JumpHead2").get(isFacing).stop();
			animations.get("RunHead").get(isFacing).stop();		
			animations.get("JumpHead2").get(isFacing).stop();		
			animations.get("RunHead").get(isFacing).stop();			
			animations.get("JumpHead1").get(isFacing).stop();		
			animations.get("StandbyHead").get(isFacing).stop();
		
	}
	
	public void start() {
		
			animations.get("RunFoot1").get(isFacing).start();
			animations.get("RunFoot2").get(isFacing).start();		
			animations.get("JumpFoot1").get(isFacing).start();	
			animations.get("StandbyFoot").get(isFacing).start();			
			animations.get("StandbyFoot").get(isFacing).start();				
			animations.get("JumpHead2").get(isFacing).start();		
			animations.get("JumpHead2").get(isFacing).start();		
			animations.get("RunHead").get(isFacing).start();	
			animations.get("JumpHead1").get(isFacing).start();		
			animations.get("StandbyHead").get(isFacing).start();
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int i) {
		score += i;
	}
	public void drawDeathAnimation() {
		animations.get("Die").get(isFacing).draw(x,y);
		if(animations.get("Die").get(isFacing).getFrame() == 17) {
			done = true;
			die();
			if(lives > 0) {
				animations.get("Die").get(isFacing).restart();
			}
			
		}
	}

	public void set(ArrayList<Shape> platforms, ArrayList<Bullet> playerBullets) {
		// TODO Auto-generated method stub
		super.setPlataformas(platforms);
		this.bullets = playerBullets;
	}

	public void resetPos() {
		// TODO Auto-generated method stub
		this.setLocation(200, 200);
	}
}
