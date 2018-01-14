package es.deusto.prog3.metalslug.tests.collisions;

import org.newdawn.slick.geom.Point;

public class Bullet extends Point {
	
	private final float speed = 1;
	private double angle;
	private double sin, cos;

	public Bullet(Player player, float x, float y) {
		super(player.getCenterX(), player.getCenterY());
		angle = Math.atan2(x - player.getCenterX(), player.getCenterY() - y);
		sin = Math.sin(angle);
		cos = Math.cos(angle);
	}
	
	public void move(int delta) {
		x += speed * sin * delta;
		y += speed * -cos * delta;
	}

}
