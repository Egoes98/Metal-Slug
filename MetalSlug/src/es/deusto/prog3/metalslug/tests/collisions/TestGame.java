package es.deusto.prog3.metalslug.tests.collisions;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class TestGame extends BasicGame {
	
	Player player;
	static ArrayList<Rectangle> platforms = new ArrayList<>();
	static ArrayList<Slope> slopes = new ArrayList<>();
	static ArrayList<Bullet> bullets = new ArrayList<>();
	static ArrayList<Granada> granadas = new ArrayList<>();

	public TestGame(String title) {
		super(title);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new TestGame("Prueba"), 1280, 720, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(player.getCenterX() > gc.getWidth()/2)
			g.translate(-player.getCenterX() + gc.getWidth()/2, 0);
		g.setColor(Color.red);
		g.fill(player);
		g.setColor(Color.white);
		for(Rectangle platform : platforms) {
			g.fill(platform);
		}
		for(Slope slope : slopes) {
			g.fill(slope);
		}
		g.setColor(Color.cyan);
		for (Bullet b : bullets) {
			g.fill(new Circle(b.getX(), b.getY(), 2));
		}
		g.setColor(Color.green);
		for(Granada gr : granadas) {
			g.fill(gr);
		}
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		player = new Player();
		platforms.add(new Rectangle(0, 700, 2800, 20));
		platforms.add(new Rectangle(200, 550, 100, 20));
		platforms.add(new Rectangle(500, 600, 100, 100));
		platforms.add(new Rectangle(2000, 600, 100, 100));
		slopes.add(new Slope(1000, 700, 1200, 600));
		platforms.add(new Rectangle(1200, 600, 300, 300));
		
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		player.update(delta);
<<<<<<< HEAD
		for (Bullet b : bullets) {
			b.move(delta);
		}
		for (Granada gr : granadas) {
			gr.update(delta);
		}
=======
		Iterator<Bullet> iterator = bullets.iterator();
		while(iterator.hasNext()) {
			Bullet ibullet = iterator.next();
			ibullet.move(delta);
			if(ibullet.detectCollision(player)) {
				iterator.remove();
			}
		};
>>>>>>> branch 'master' of https://github.com/Egoes98/Metal-Smug.git
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_SPACE) {
			player.jump();
		} else if (key == Input.KEY_G) {
			// TODO Granadas limitadas, comprobar a ver si quedan
			granadas.add(new Granada(player.getCenterX(), player.getCenterY(), player.getMovingLeft()));
		}
	}
	
	public void mousePressed(int button, int x, int y) {
		addNewBullet(x,y);
	}

	private void addNewBullet(int x, int y) {
		if(player.getCenterX() < 640) 
			bullets.add(new Bullet(player, x, y));
		else
			bullets.add(new Bullet(player, x - 640 + player.getCenterX(), y));
	}

}
