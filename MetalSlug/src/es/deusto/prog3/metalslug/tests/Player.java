package es.deusto.prog3.metalslug.tests;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Ellipse {
	
	
	private HashMap<String,Animation> movingAnimations;
	private HashMap<String, Image> sprites;
	private Input input;
	private Image image;
	private int vy;
	private boolean hasjumped;
	private static final int GRAVITY = 1500; // In pixels/s^2
	
	private boolean isLeft = true;
	private boolean move = false;
	
	private String isFacing; //por defecto true sera derecha, y false izquierda

	public Player() {
		super(640, 200, 0, 0);
		input = new Input(720);
		try {
			image = new Image("resources/data/sprite_1.png");
			setSprite(image);
			setAnimation(new Image[] {new Image("resources/data/sprite_1.png"),
									  new Image("resources/data/sprite_2.png"),
									  new Image("resources/data/sprite_3.png")}, 150);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setRadii(image.getWidth() / 2, image.getHeight() / 2);
		isFacing = "RIGHT";
		
	}
	
	private void setSprite(Image image) {
		this.sprites = new HashMap<String,Image>();
		sprites.put("RIGHT", image);
		sprites.put("LEFT", image.getFlippedCopy(true, false));
		
	}

	protected void setAnimation(Image[] images, int duration) {
		movingAnimations = new HashMap<String,Animation>();
		
		movingAnimations.put("RIGHT", new Animation(images,duration));
		
		Animation facingLeftAnimation = new Animation();
		for(Image i : images) {
			facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), duration);
		}
		movingAnimations.put("LEFT", facingLeftAnimation);
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
		if(input.isKeyDown(Input.KEY_LEFT)) {
			moveX(delta, isLeft);
			move = true;
			isFacing = "LEFT";
		}else if(input.isKeyDown(Input.KEY_RIGHT)) {
			moveX(delta, !isLeft);
			move = true;
			isFacing = "RIGHT";
		}else {
			move = false;
		}

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
	
	
	

	public void draw() {
		// TODO Auto-generated method stub
		if(move) {
			movingAnimations.get(isFacing).draw(x,y);
		}else if(!move){
			sprites.get(isFacing).draw(x,y);
		}
		
	}

	

}
