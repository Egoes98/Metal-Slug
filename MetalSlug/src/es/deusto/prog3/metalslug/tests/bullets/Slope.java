package es.deusto.prog3.metalslug.tests.bullets;

import org.newdawn.slick.geom.Polygon;

public class Slope extends Polygon {
	
	private float slopeAngle;
	
	public Slope(float x1, float y1, float x2, float y2) {
		super();
		float x3, y3;
		if(y1 < y2) {
			x3 = x2;
			y3 = y1;
		} else {
			x3 = x1;
			y3 = y2;
		}
		
		float[] points = new float[] {x1, y1, x2, y2, x3, y3};
		this.points = points;
		slopeAngle = (float) Math.asin(x1 - x2);
	}

	public float getSlopeAngle() {
		return slopeAngle;
	}
}
