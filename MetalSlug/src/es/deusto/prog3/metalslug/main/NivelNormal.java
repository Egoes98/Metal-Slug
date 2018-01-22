package es.deusto.prog3.metalslug.main;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
	private Input input;
	
	private Image background;
	
	//Pausa
	Image menu_pausa;
	boolean pausa;

	public NivelNormal(int nivel) {
		super();
		this.nivel = nivel;
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		platforms = BaseDeDatos.getPlataformas(nivel);
		player = new Player(platforms, playerBullets);
		enemyBullets = new ArrayList<>();
		playerBullets = new ArrayList<>();
		granadas = new ArrayList<>();
		enemies = BaseDeDatos.getEnemigos(nivel);
		// enemies.add(new Enemy(1000, 200, 900, 1100));
		
		for(Enemy e : enemies) {
			e.setBullets(enemyBullets);
			e.setPlataformas(platforms);
			e.setPlayer(player);
		}
		input = new Input(720);
		background = new Image("resources/data/Mission" + nivel + ".png", false, Image.FILTER_NEAREST).getScaledCopy(3);
		
		//Menu Pausa
		menu_pausa = new Image("resources/data/pausa.png");
		pausa = false;
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
		//Pausa
	 	if (pausa) {
	        	Color trans = new Color(0f,0f,0f,0.5f);
	        	g.setColor(trans);
	        	g.fillRect(0,0, background.getWidth(), background.getHeight());
	        	g.resetTransform();
	        	menu_pausa.draw(250,140);
	   	}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if(!pausa){
			player.update(delta);
			if(player.isCanShoot() && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				addNewBullet(input.getMouseX(), input.getMouseY());
				player.setCanShoot(false);
			}
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
				if(gr.getStatus() == Granada.STATUS_EXPLODED) {
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
				game.getState(10 + nivel + 1).init(gc, game);
				game.getState(10 + nivel).leave(gc, game);
				game.enterState(nivel + 10 + 1);
			}
		}
		
		//Menu Pausa
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			if(pausa){
				pausa = false;
			} else {
				pausa = true;
			}
		}
		
		if(pausa) {
			if(input.getMouseX() >= 456 && input.getMouseX()<= 832 && input.getMouseY() >= 386 && input.getMouseY() <= 469){
				if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					pausa = false;
				}
			}
			
			if(input.getMouseX() >= 456 && input.getMouseX() <= 832 && input.getMouseY() >= 287 && input.getMouseX() <= 366){
				if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					System.out.println("Entra en menu");
				}
			}
			
		}
		
		if(player.getX() > background.getWidth()) {
			if(nivel + 1 == 3) {
				game.addState(new NivelBoss());
				game.getState(100).init(gc, game);
				game.getState(10 + nivel).leave(gc, game);
				game.enterState(100);
			} else {
			game.addState(new NivelNormal(nivel + 1));
			game.getState(10 + nivel + 1).init(gc, game);
			game.getState(10 + nivel).leave(gc, game);
			game.enterState(nivel + 10 + 1);
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 10 + nivel;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_SPACE) {
			player.jump();
		} else if (key == Input.KEY_G) {
			// TODO Granadas limitadas, comprobar a ver si quedan
			granadas.add(new Granada(player.getCenterX(), player.getCenterY(), player.getMovingLeft(), platforms));
		}
	}
	
	private void addNewBullet(int x, int y) {
		if(player.getCenterX() < 640) 
			playerBullets.add(new Bullet(player.getCenterX(), player.getCenterY(), x, y, 1f));
		else
			playerBullets.add(new Bullet(player.getCenterX(), player.getCenterY(), x - 640 + player.getCenterX(), y, 1f));
	}

}
