package es.deusto.prog3.metalslug.tests.collisions;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class TestGame extends BasicGame {
	
	Player player;
	static ArrayList<Shape> platforms = new ArrayList<>(); // TODO una clase especial para platform
	static ArrayList<Bullet> bullets = new ArrayList<>();
	static ArrayList<Granada> granadas = new ArrayList<>();
	static ArrayList<Bullet> enemyBullets = new ArrayList<>();
	static ArrayList<Enemy> enemies = new ArrayList<>();
	private Image background;

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
		if(player.getCenterX() > gc.getWidth()/2 && player.getCenterX() < background.getWidth() - gc.getWidth()/2)
			g.translate(-player.getCenterX() + gc.getWidth()/2, 0);
		else if(player.getCenterX() > background.getWidth() - gc.getWidth()/2) {
			g.translate(-background.getWidth() + gc.getWidth(), 0);
		}
		background.draw(0, 0);
		player.drawPiernas();
		player.drawCabeza();	
		for(Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
			Enemy e = iterator.next();
			g.fill(e);
		}
		
		g.setColor(Color.white);
		
		for(Iterator<Shape> iterator = platforms.iterator(); iterator.hasNext();) {
			Shape platform = iterator.next();
			g.fill(platform);
		}
		
		
		g.setColor(Color.cyan);
		for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			g.fill(new Circle(b.getX(), b.getY(), 2));
		}
		
		for(Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			g.fill(new Circle(b.getX(), b.getY(), 2));
		}
		g.setColor(Color.green);
		for(Iterator<Granada> iterator = granadas.iterator(); iterator.hasNext();) {
			Granada gr = iterator.next();
			g.fill(gr);
		}
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		player = new Player();
		platforms.add(new Platform(0, 680, 7947, 40, false));
		platforms.add(new Platform(1760, 525, 154, 100, true));
		platforms.add(new Platform(1911, 417, 175, 263, false));
		platforms.add(new Platform(2086, 362, 436, 50, false));
		platforms.add(new Platform(3868, 382, 528, 40, false));
		platforms.add(new Platform(4734, 382, 528, 40, false));
		
		
		background = new Image("resources/data/Mission1.png", false, Image.FILTER_NEAREST).getScaledCopy(3);
		
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		player.update(delta);
		for(Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
			Bullet ibullet = iterator.next();
			ibullet.update(delta);
			if(ibullet.detectCollision(player)) {
				iterator.remove();
			}
		}
		
		for (Iterator<Granada> iterator = granadas.iterator(); iterator.hasNext();) {
			Granada gr = iterator.next();
			gr.update(delta);
			gr.detectCollisions();
			if(gr.getStatus() == Granada.STATUS_EXPLODING) {
				// TODO comprobar colisiones con enemigos
				iterator.remove();
			}
		}
		
		for(Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
			Enemy e = iterator.next();
			e.update(delta, bullets, granadas);
			if(e.isDead()) {
				iterator.remove(); // Sustituir por animación de morir?
								   
			}
		}
		
		for(Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.update(delta);
		}
		
		
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
			bullets.add(new Bullet(player, x, y, 1f));
		else
			bullets.add(new Bullet(player, x - 640 + player.getCenterX(), y, 1f));
	}

}
