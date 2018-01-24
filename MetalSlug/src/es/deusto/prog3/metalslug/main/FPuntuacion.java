package es.deusto.prog3.metalslug.main;


import java.util.HashMap;

import javax.swing.JComboBox;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;
import es.deusto.prog3.metalslug.game.entities.Player;

public class FPuntuacion extends BasicGameState{
	Image fondo_fs, m_ij;
	
	int mouseY;
	int mouseX;
	
	boolean MM;
	private Input input;
	
	private UnicodeFont scorefont;
	private Player player;
	
	int score = 0;
	String jugador = "";
	TextField nJugador;
	boolean tF, mIJ, mC, nB; 
	
	
	public FPuntuacion(int state, Player player) {
		super();
		this.player = player;
		this.score = player.getScore();
		// TODO Auto-generated constructor stub

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		fondo_fs = new Image ("resources/Menu/Menu_fp/fondo_fp.png");
		input = gc.getInput();

		scorefont = new UnicodeFont("resources/mini_pixel-7.TTF", 40, false, false);
		scorefont.addAsciiGlyphs();
		scorefont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		scorefont.loadGlyphs();
		
		m_ij = new Image("resources/Menu/Menu_fp/m_ij.png");
		nJugador = new TextField(gc, scorefont, 501, 441, 250, 40);
		tF = true;
		mIJ = true;
		mC = false;
		nB = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		fondo_fs.draw(0,0);
		
		scorefont.drawString(760, 433, String.format("%06d", player.getScore()));
		
		if (mIJ) {
			Color trans = new Color(0f, 0f, 0f, 0.5f);
			g.setColor(trans);
			g.fillRect(0, 0, 1280, 720);
			m_ij.draw();
			if(tF) {
				nJugador.render(gc, g);
			}else {
				
			}
		}else {
			//Temporal
			scorefont.drawString(304, 433, String.format("%06d", BaseDeDatos.getPuntuacionJ(jugador)));
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(!mIJ) {
			if(input.getMouseX() >= 427 && input.getMouseX() <= 798 && input.getMouseY() >= 628
					&& input.getMouseY() <= 706){
				if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					sbg.enterState(2);
				}
			}
		}
		
		if(mIJ) {
			if(tF) {
				if(input.isKeyPressed(Input.KEY_ENTER)) {
					jugador = nJugador.getText().toLowerCase();
					BaseDeDatos.agregarPuntuacion(jugador, player.getScore());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tF = false;
					//Temporal
					mIJ = false;
				}
			}
		}
	
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
