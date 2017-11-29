package es.deusto.prog3.metalslug.tests.bullets;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;



public class TestGame extends BasicGame {
	
	Player player;
	static ArrayList<Rectangle> platforms = new ArrayList<>();
	private ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	private Input input;
	

	public TestGame(String title) {
		super(title);
		input = new Input(720);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new TestGame("Prueba"), 1280, 720, false);
			container.start();
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(player.getCenterX() > gc.getWidth()/2)
			g.translate(-player.getCenterX() + gc.getWidth()/2, 0);
		g.setColor(Color.red);
		g.fill(player);
		g.setColor(Color.white);
		for(Rectangle platform : platforms) {
			g.fill(platform);
		}
		
		g.setColor(Color.cyan);
		for(int i = 0; i < bulletList.size();i++) {
			Bullet bullet = bulletList.get(i);
			
			g.fillOval(bullet.location.getX(), bullet.location.getY(), 10, 5);
		}
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		player = new Player();
		platforms.add(new Rectangle(0, 700, 2800, 20));
		platforms.add(new Rectangle(200, 550, 100, 20));
		platforms.add(new Rectangle(500, 600, 100, 100));
		platforms.add(new Rectangle(2000, 600, 100, 100));
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		player.update(delta);
		
		
		for(int i = 0 ;i < bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			
			bullet.move(delta);
		}
		
	}
	
	public void mousePressed(int button, int x, int y) {
		addNewBullet(x,y);
	}
	
	private void addNewBullet(int x, int y){
	      bulletList.add(new Bullet(new Vector2f((int)player.getX()+25, (int)player.getY()),new Vector2f(x+player.getX()-950/2,y)));
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_SPACE) {
			player.jump();
		}
	}

}
