package es.deusto.prog3.metalslug.tests.boss;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.tests.collisions.Bullet;
import es.deusto.prog3.metalslug.tests.collisions.Player;

public class BossLevel extends BasicGame {
	
	private Player player;
	private ArrayList<Shape> platforms = new ArrayList<>();
	private ArrayList<Bullet> playerBullets = new ArrayList<>();

	public BossLevel(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		player = new Player();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
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
