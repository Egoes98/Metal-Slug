package es.deusto.prog3.metalslug.tests.bullets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet {

    private Vector2f sta;
    private Vector2f dest;
	public Point location = new Point(0,0);
	float speed = 1; //how fast this moves.
	float deg;
	float dx;
	float dy;
	
	
  public Bullet(Vector2f sta, Vector2f dest){
     this.sta = sta;

     location.setLocation(sta);
     this.dest = dest;
     recalculateVector(dest);
  }
  
  public void recalculateVector(Vector2f dest){
     float rad = (float)(Math.atan2(dest.getX() - sta.getX(), sta.getY() - dest.getY()));
     
     
     
     this.dx = (float) Math.sin(rad) * speed;
     this.dy = -(float) Math.cos(rad) * speed;  
  }
  
  public void recalculateVector(){
     recalculateVector(dest);
  }
  
  public void move(int delta){
     float x = location.getX();
     float y = location.getY();
     
     x += dx*delta;
     y += dy*delta;
     
     location.setLocation(x, y);
  }

	
}
