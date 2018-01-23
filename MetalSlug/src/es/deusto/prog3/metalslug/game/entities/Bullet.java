package es.deusto.prog3.metalslug.game.entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Bullet extends Point {
	
	private float speed;
	private double sin, cos;
	private float sourceX, sourceY;
	private double angle;
	private Image sprite;

	public Bullet(float sourceX, float sourceY, float x, float y, float speed) {
		this(sourceX, sourceY, Math.atan2(x - sourceX, sourceY - y), speed);
	}
	
	public Bullet(float sourceX, float sourceY, double angle, float speed) {
		super(sourceX, sourceY);
		this.speed = speed;
		this.angle = Math.toDegrees(angle);
		this.cos = Math.cos(angle);
		this.sin = Math.sin(angle);
		try {
			sprite = new Image("resources/data/Bullet.png", false, Image.FILTER_NEAREST).getScaledCopy(2);
			sprite.rotate((float) this.angle - 90);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(int delta) {
		x += speed * sin * delta;
		y += speed * -cos * delta;
	}
	
	public boolean detectCollisionPlatforms(ArrayList<Shape> platforms, boolean checkAtravesable) {
		
		for(Shape platform : platforms) {
			if(platform.contains(this.getX(), this.getY())) {
				if(platform instanceof Platform) {
					Platform p = (Platform) platform;
					if(p.isAtravesable() && !checkAtravesable) {
						return true;
					} else if (!p.isAtravesable()) {
						return true;
					}
				}
				if(platform instanceof Slope) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean detectCollisionCharacter(Character character) {
		if(character.contains(getX(), getY())) {
			character.die();
			return true;
		}
		
		return false;
	}
	public boolean detectCollisionCharacter(ArrayList<Enemy> characters) {
		for(Enemy c : characters) {
			if(detectCollisionCharacter(c)) {
				return true;
			}
		}
		
		return false;
	}

	public void draw() {
		sprite.drawCentered(x, y);
	}
	
	

}
