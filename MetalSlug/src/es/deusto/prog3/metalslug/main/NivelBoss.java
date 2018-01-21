package es.deusto.prog3.metalslug.main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import es.deusto.prog3.metalslug.game.entities.Bullet;
import es.deusto.prog3.metalslug.game.entities.Enemy;
import es.deusto.prog3.metalslug.game.entities.Player;

public class NivelBoss extends BasicGameState {

	private Player player;
	private ArrayList<Shape> platforms;
	private ArrayList<Bullet> enemyBullet;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 100;
	}

}
