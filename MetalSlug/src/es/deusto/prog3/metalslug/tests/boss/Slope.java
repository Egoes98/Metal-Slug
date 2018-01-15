package es.deusto.prog3.metalslug.tests.boss;

import org.newdawn.slick.geom.Polygon;

public class Slope extends Polygon {
	
	private double slopeAngle; // -pi to pi, 0 being flat
	
	public Slope(float x1, float y1, float x2, float y2) { // Leftmost point must be x1, y1
		super();
		float x3, y3;
		if(y1 < y2) {
			x3 = x1;
			y3 = y2;
		} else {
			x3 = x2;
			y3 = y1;
		}
		
		addPoint(x1, y1);
		addPoint(x2, y2);
		addPoint(x3, y3);
		slopeAngle =  Math.atan((y1 - y2) / (x2 - x1));
		this.setClosed(true);
		
	}

	public double getSlopeAngle() {
		return slopeAngle;
	}
	
	public float getMaxYIn(float x) {
		float distanceToLeft = x - points[0];
		float startingPoint = points[1];
		
		float heightInX = (float) (distanceToLeft * Math.tan(slopeAngle));
		
		return startingPoint - heightInX;
		
	}
}
