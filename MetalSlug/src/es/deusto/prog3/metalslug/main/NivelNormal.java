package es.deusto.prog3.metalslug.main;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;
import es.deusto.prog3.metalslug.game.entities.Bullet;
import es.deusto.prog3.metalslug.game.entities.Enemy;
import es.deusto.prog3.metalslug.game.entities.Granada;
import es.deusto.prog3.metalslug.game.entities.Player;

public class NivelNormal extends BasicGameState {
	
	private int nivel;
	
	private Player player;
	private ArrayList<Shape> platforms;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> enemyBullets;
	private ArrayList<Bullet> playerBullets;
	private ArrayList<Granada> granadas;
	
	private Image background;

	public NivelNormal(int nivel) {
		super();
		this.nivel = nivel;
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		platforms = BaseDeDatos.getPlataformas(nivel);
		player = new Player(platforms);
		enemyBullets = new ArrayList<>();
		playerBullets = new ArrayList<>();
		granadas = new ArrayList<>();
		enemies = BaseDeDatos.getEnemigos(nivel);
		
		for(Enemy e : enemies) {
			e.setBullets(enemyBullets);
			e.setPlataformas(platforms);
		}
		
		background = new Image("resources/data/Mission" + nivel + ".png", false, Image.FILTER_NEAREST).getScaledCopy(3);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
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
		
		g.setColor(Color.cyan);
		for (Iterator<Bullet> iterator = playerBullets.iterator(); iterator.hasNext();) {
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
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		player.update(delta);
		for(Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext(); ) {
			Bullet ibullet = iterator.next();
			ibullet.update(delta);
			if(ibullet.detectCollisionCharacter(player)) {
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
			e.update(delta, playerBullets, granadas);
			if(e.isDead()) {
				iterator.remove(); // Sustituir por animación de morir?
								   
			}
		}
		
		for(Iterator<Bullet> iterator = playerBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.update(delta);
			if(b.detectCollisionCharacter(enemies)) {
				iterator.remove();
			}
		}
		if(player.getX() > background.getWidth()) {
			game.addState(new NivelNormal(nivel + 1));
			game.enterState(nivel + 10 + 1);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 10 + nivel;
	}

}
