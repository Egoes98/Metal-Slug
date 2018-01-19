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
		try {
			


			addAnimation("StandbyHead", new Image[] {new Image("resources/EriAnimations/Standby/StandbyCabeza1.png"),
					 								 new Image("resources/EriAnimations/Standby/StandbyCabeza2.png"),
					 								 new Image("resources/EriAnimations/Standby/StandbyCabeza3.png"),
					 								 new Image("resources/EriAnimations/Standby/StandbyCabeza4.png")}, 400);
			
			addAnimation("StandbyFoot", new Image[] {new Image("resources/EriAnimations/Standby/StandbyPiernas1.png")}, 300);
			
			
			addAnimation("RunHead", new Image[] {new Image("resources/EriAnimations/Run/RunHead1.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead2.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead3.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead4.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead5.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead6.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead7.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead8.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead9.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead10.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead11.png"),
					 							 new Image("resources/EriAnimations/Run/RunHead12.png")}, 100);
			
			addAnimation("RunFoot1", new Image[] {new Image("resources/EriAnimations/Run/RunFoot1.png"),
					 							 new Image("resources/EriAnimations/Run/RunFoot2.png"),
					 							 new Image("resources/EriAnimations/Run/RunFoot3.png"),
					 							 new Image("resources/EriAnimations/Run/RunFoot4.png")}, 100);
			
			addAnimation("RunFoot2", new Image[] {new Image("resources/EriAnimations/Run/RunFoot5.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot6.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot7.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot8.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot9.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot10.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot11.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot12.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot13.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot14.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot15.png"),
					 							  new Image("resources/EriAnimations/Run/RunFoot16.png")}, 90);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		detectCollisions();
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
