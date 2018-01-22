package es.deusto.prog3.metalslug.main;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import es.deusto.prog3.metalslug.game.entities.Boss;
import es.deusto.prog3.metalslug.game.entities.Bullet;
import es.deusto.prog3.metalslug.game.entities.Granada;
import es.deusto.prog3.metalslug.game.entities.Platform;
import es.deusto.prog3.metalslug.game.entities.Player;

public class NivelBoss extends BasicGameState {

	private Player player;
	private ArrayList<Shape> platforms;
	private ArrayList<Bullet> enemyBullets;
	private Boss boss;
	private ArrayList<Bullet> playerBullets;
	private Rectangle healthBar;
	private ArrayList<Granada> granadas;
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		player.drawPiernas();
		player.drawCabeza();			
		g.setColor(Color.cyan);
		for (Iterator<Bullet> iterator = playerBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			g.fill(new Circle(b.getX(), b.getY(), 10));
		}
		
		for(Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			g.fill(new Circle(b.getX(), b.getY(), 2));
		}
		g.setColor(Color.green);
		for(Iterator<Granada> iterator = granadas.iterator(); iterator.hasNext();) {
			Granada gr = iterator.next();
			if(gr.getStatus() == Granada.STATUS_EXPLODING) {
				gr.drawExplosion();
			}
			else 
				g.fill(gr);
		}
		
		g.fill(boss);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException{
		// TODO Auto-generated method stub
		platforms = new ArrayList<>();
		enemyBullets = new ArrayList<>();
		playerBullets = new ArrayList<>();
		player = new Player(platforms, playerBullets);
		boss = new Boss(platforms, enemyBullets);
		platforms.add(new Platform(0, 0, 20, 720, false));
		platforms.add(new Platform(0, 0, 1280, 20, false));
		platforms.add(new Platform(0, 700, 1280, 20, false));
		platforms.add(new Platform(1260, 0, 20, 720, false));
		healthBar = new Rectangle(140, 50, 1000, 20);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException{
		// TODO Auto-generated method stub
		player.update(delta);
		for (Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.update(delta);
			if(b.getX() < 0 || b.getX() > 1280 || b.getY() < 0 || b.getY() > 720) {
				iterator.remove();
			}
		}
		
		for (Iterator<Bullet> iterator = playerBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.update(delta);
			if(boss.contains(b.getX(), b.getY())) {
				iterator.remove();
				boss.hit();
				healthBar.setWidth(boss.getHealth()*10);
			}
			if(b.getX() < 0 || b.getX() > 1280 || b.getY() < 0 || b.getY() > 720) {
				iterator.remove();
			}
		}
		
		boss.update(delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 100;
	}

}
