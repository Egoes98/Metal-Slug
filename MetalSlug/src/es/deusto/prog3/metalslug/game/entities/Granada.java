package es.deusto.prog3.metalslug.game.entities;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.game.data.AnimationImages;

public class Granada extends Circle {

	private static final int GRAVITY = 1500;
	public static final String STATUS_FLYING = "FLYING";
	public static final String STATUS_EXPLODING = "EXPLODING";
	public static final String STATUS_EXPLODED = "EXPLODED";
	private String status;
	private int vx, vy;
	private Animation explosion;
	private static Image granada;
	private ArrayList<Shape> platforms;

	static {
		try {
			granada = new Image("resources/data/granada.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Granada(float centerPointX, float centerPointY, boolean goingLeft, ArrayList<Shape> platforms) {
		super(centerPointX, centerPointY, 5);
		this.platforms = platforms;
		vy = -700;
		vx = goingLeft ? -500 : 500;
		status = STATUS_FLYING;
		explosion = new Animation(AnimationImages.explosion, 50);
		explosion.setLooping(false);
	}

	public void update(int delta) {
		moveX(delta);
		moveY(delta);
		if(explosion.getFrame() == explosion.getFrameCount() - 1) {
			status = STATUS_EXPLODED;
		}
	}

	private void moveY(int delta) {
		if (status == STATUS_FLYING) {
			float deltaSeconds = delta / 1000f;
			setY(getY() + vy * deltaSeconds);
			vy += GRAVITY * deltaSeconds;
		}
	}

	private void moveX(int delta) {
		if (status == STATUS_FLYING) {
			float deltaSeconds = delta / 1000f;
			setX(getX() + vx * deltaSeconds);
		}
	}

	public void detectCollisions() {
		for (Shape r : platforms) {
			if (this.intersects(r)) {
				if(r instanceof Rectangle)
					this.setCenterY(r.getMinY());
				if(r instanceof Slope) {
					Slope s = (Slope) r;
					this.setCenterY(s.getMaxYIn(x));
				}
				this.setRadius(50);
				status = STATUS_EXPLODING;
			}
		}
	}
	
	public void detectCollisions(Boss boss) {
		if(this.intersects(boss)) {
			if(this.status == STATUS_FLYING) {
				status = STATUS_EXPLODING;
			}
			boss.hit();
		}
	}

	public String getStatus() {
		return status;
	}
	
	public void drawExplosion() {
		explosion.draw(getX() - 90, getY() - 270);
	}
	
	public void draw() {
		granada.drawCentered(x, y);
	}

	public void detectCollisions(ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		for(Enemy e : enemies) {
			if(this.intersects(e)) {
				e.die();
				if(status == STATUS_FLYING)
					status = STATUS_EXPLODING;
			}
		}
	}

}