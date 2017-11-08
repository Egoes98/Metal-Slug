package es.deusto.prog3.metalslug.game.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;

public abstract class Character extends Ellipse {
	
	private Animation sprite;

	public Character(float centerPointX, float centerPointY, Animation sprite) {
		super(centerPointX, centerPointY, 0, 0);
		// TODO Auto-generated constructor stub
		this.sprite = sprite;
		this.setRadii(sprite.getWidth() / 2, sprite.getHeight() / 2);
	}

	

}
