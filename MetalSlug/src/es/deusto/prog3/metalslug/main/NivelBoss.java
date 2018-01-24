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
	
	private Image background;
	private Input input;
	private boolean reset;
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0, 0);
		if(player.isDead()) {
			player.drawDeathAnimation();
			enemyBullets.clear();
		} else {
			player.drawPiernas();
			player.drawCabeza();	
		}			
		g.setColor(Color.cyan);
		for (Iterator<Bullet> iterator = playerBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.draw();
		}
		
		for(Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.draw();
		}
		g.setColor(Color.green);
		
		
		boss.draw();
		g.setColor(Color.red);
		g.fill(healthBar);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		platforms = new ArrayList<>();
		enemyBullets = new ArrayList<>();
		playerBullets = new ArrayList<>();
		
		if(player == null)
			player = new Player(platforms, playerBullets);
		else {
			player.resetPos();
			player.set(platforms, playerBullets);
		}
		player.setBoss(true);
		boss = new Boss(platforms, enemyBullets);
		platforms.add(new Platform(0, 604, 1280, 20, false));
		platforms.add(new Platform(-20, 0, 20, 720, false));
		platforms.add(new Platform(0, -20, 1280, 20, false));
		platforms.add(new Platform(1280, 0, 20, 720, false));
		platforms.add(new Platform(216, 539, 191, 65, true));
		platforms.add(new Platform(726, 539, 258, 65, true));
		platforms.add(new Platform(22, 410, 259, 65, true));
		platforms.add(new Platform(407, 410, 285, 65, true));
		platforms.add(new Platform(984, 410, 258, 65, true));
		healthBar = new Rectangle(140, 50, 1000, 20);
		input = gc.getInput();
		
		background = new Image("resources/data/BossBackground.png");
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException{
		// TODO Auto-generated method stub
		player.update(delta);
		if (player.isCanShoot() && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			addNewBullet(input.getMouseX(), input.getMouseY());
			player.setCanShoot(false);
		}
		for (Iterator<Bullet> iterator = enemyBullets.iterator(); iterator.hasNext();) {
			Bullet b = iterator.next();
			b.update(delta);
			if(b.getX() < 0 || b.getX() > 1280 || b.getY() < 0 || b.getY() > 720 || b.detectCollisionPlatforms(platforms, false) || b.detectCollisionCharacter(player)) {
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
		healthBar.setWidth(boss.getHealth() * 10);
		
		if(boss.getHealth() <= 0) {
			game.addState(new FPuntuacion(0, player));
			game.getState(4).init(gc, game);
			game.enterState(4);
		}
		
		if(player.isDead()) {
			reset = true;
			enemyBullets.clear();
			boss.setHealth(100);
		} else if(reset) {
			boss.setLocation(700, 200);
			reset = false;
		}
	}

	public NivelBoss(Player player) {
		super();
		this.player = player;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 100;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_SPACE) {
			player.jump();
		}
	}
	
	private void addNewBullet(int x, int y) {
		playerBullets.add(new Bullet(player.getShootingX(), player.getShootingY(), x, y, 1f));
	}

}
