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
	
	/**
	 * 
	 * @param sourceX Coordenada x de inicio
	 * @param sourceY Coordenada y de inicio
	 * @param x Coordenada x de destino
	 * @param y Coordenada y de destino
	 * @param speed
	 */
	public Bullet(float sourceX, float sourceY, float x, float y, float speed) {
		this(sourceX, sourceY, Math.atan2(x - sourceX, sourceY - y), speed);
	}
	
	/**
	 * 
	 * @param sourceX Coordenada x de inicio
	 * @param sourceY Coordenada y de inicio
	 * @param angle
	 * @param speed
	 */
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
	
	/**
	 * Actualiza la posición de la bala de acuerdo a la velocidad, el ángulo y delta
	 * @param delta Tiempo de actualización
	 */
	public void update(int delta) {
		x += speed * sin * delta;
		y += speed * -cos * delta;
	}
	
	/**
	 * Comprueba colisiones de la bala con plataformas
	 * @param platforms
	 * @param checkAtravesable true = no chocan con plataformas atravesables
	 * @return Si ha chocado
	 */
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
	
	/**
	 * Detecta colisiones con character, y mata al character si colisiona
	 * @param character
	 * @return Si ha chocado
	 */
	public boolean detectCollisionCharacter(Character character) {
		if(character.contains(getX(), getY())) {
			character.die();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Detecta colisiones con una lista de characters, matando al character con el que colisiona
	 * @param characters
	 * @return Si ha chocado
	 */
	public boolean detectCollisionCharacter(ArrayList<Enemy> characters) {
		for(Enemy c : characters) {
			if(detectCollisionCharacter(c)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Dibuja el sprite de la bala en pantalla
	 */
	public void draw() {
		sprite.drawCentered(x, y);
	}
	
	/*
	 * Comprueba si se ha salido de la pantalla
	 */
	public boolean detectCollisionOutOfBounds(Player player) {
		
		
		if(getY() < 100 || getY() > 720) {
			return true;
		}
		
		if(player.getCenterX() < 640) {
			if(x < 0|| x > 1280) {
			return true;
			}	
		}else {
			if(x < player.getCenterX() - 640 || x > player.getCenterX() + 640) {
				return true;
			}
		}
		return false;
	}
	
	

}
