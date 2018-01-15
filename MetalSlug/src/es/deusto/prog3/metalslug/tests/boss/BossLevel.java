package es.deusto.prog3.metalslug.tests.boss;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class BossLevel extends BasicGame {
	
	private Player player;
	private Boss boss;
	private ArrayList<Shape> platforms = new ArrayList<>();
	private ArrayList<Bullet> playerBullets = new ArrayList<>();
	private ArrayList<Bullet> enemyBullets = new ArrayList<>();

	public BossLevel(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.cyan);
		for (Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			g.fill(new Circle(b.getX(), b.getY(), 2));
		}
		g.setColor(Color.white);
		for (Iterator<Shape> iterator = platforms.iterator(); iterator.hasNext();) {
			Shape s = iterator.next();
			g.fill(s);
		}
		
		
		g.setColor(Color.red);
		g.fill(player);
		g.setColor(Color.green);
		g.fill(boss);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		player = new Player(platforms);
		boss = new Boss(platforms, enemyBullets);
		platforms.add(new Rectangle(0, 0, 20, 720));
		platforms.add(new Rectangle(0, 0, 1280, 20));
		platforms.add(new Rectangle(0, 700, 1280, 20));
		platforms.add(new Rectangle(1260, 0, 20, 720));
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		player.update(delta);
		for (Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.update(delta);
		}
		boss.update(delta);
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new BossLevel("Boss"), 1280, 720, false);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
