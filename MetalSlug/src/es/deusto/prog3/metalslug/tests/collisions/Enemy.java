package es.deusto.prog3.metalslug.tests.collisions;

import org.newdawn.slick.geom.Rectangle;

public class Enemy extends Rectangle {
	
	private Player player;
	private Thread updateShooting;
	private boolean shooting;
	private Thread changeDirection;
	private boolean movingRight;
	
	{
		updateShooting = new Thread(() -> {
			while(true) {
				if(distanceTo(this.getCenter(), player.getCenter()) < 200) {
					shooting = true;
				} else {
					shooting = false;
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) { }
			}
		});
		
		changeDirection = new Thread(() -> {
			movingRight = true;
			while(true) {
				movingRight = !movingRight;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public Enemy(float x, float y) {
		super(x, y, 30, 30);
		updateShooting.start();
		// TODO Auto-generated constructor stub
	}

	private float distanceTo(float[] a, float[] b) {
		// TODO Auto-generated method stub
		return (float) Math.hypot(a[0] - b[0], a[1] - b[1]);
	}

}
