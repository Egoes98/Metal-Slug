package es.deusto.prog3.metalslug.tests.collisions;

import org.newdawn.slick.geom.Rectangle;

public class Platform extends Rectangle {
	
	private boolean atravesable;

	public Platform(float x, float y, float width, float height, boolean atravesable) {
		super(x, y, width, height);
		this.atravesable = atravesable;
		// TODO Auto-generated constructor stub
	}
	
	public boolean isAtravesable() {
		return atravesable;
	}
	
	

}
