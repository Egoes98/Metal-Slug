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
	Image fondo_fs, m_ij, n_best, b_comprobacion, texto;
	
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
	//tF Para saber si mostramos el TextField
	//mIJ Para saber si mostrar el Menu de insertar jugador
	//mC Para saber si se muestran los componentes de comprobacion
	//nB Se activa cuando la puntuacion nueva es mayor que la puntuacion de la BD y muestra una Imagen en pantalla
	
	public FPuntuacion(int state, Player player) {
		super();
		this.player = player;
		this.score = score;
		// TODO Auto-generated constructor stub

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		fondo_fs = new Image ("resources/Menu/Menu_fp/fondo_fp.png");
		input = gc.getInput();

		scorefont = new UnicodeFont("resources/ARCADECLASSIC.TTF", 40, false, false);
		scorefont.addAsciiGlyphs();
		scorefont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		scorefont.loadGlyphs();
		
		b_comprobacion = new Image("resources/Menu/Menu_fp/b_comprobacion.png");
		texto = new Image("resources/Menu/Menu_fp/texto.png");
		m_ij = new Image("resources/Menu/Menu_fp/m_ij.png");
		n_best = new Image("resources/Menu/Menu_fp/newBest.png");
		nJugador = new TextField(gc, scorefont, 510, 388, 250, 40);
		tF = true;
		mIJ = false;
		mC = false;
		nB = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		fondo_fs.draw(0,0);
		
		scorefont.drawString(760, 433, String.format("%06d", player.getScore()));
		
		if (!mIJ) {
			Color trans = new Color(0f, 0f, 0f, 0.5f);
			g.setColor(trans);
			g.fillRect(0, 0, 1280, 720);
			m_ij.draw(280,196);
			if(tF) {
				g.setColor(Color.white);
				nJugador.render(gc, g);
			}else {
				texto.draw(402,314);
				b_comprobacion.draw(310,448);
			}
		}else {
			scorefont.drawString(304, 433, String.format("%06d", BaseDeDatos.getPuntuacionJ(jugador)));
		}
		
		if(nB) {
			n_best.draw(430, 271);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(mIJ) {
			if(input.getMouseX() >= 427 && input.getMouseX() <= 798 && input.getMouseY() >= 628
					&& input.getMouseY() <= 706){
				if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					sbg.enterState(2);
				}
			}
		}
		
		if(!mIJ) {
			if(tF) {
				if(input.isKeyPressed(Input.KEY_ENTER)) {
					jugador = nJugador.getText().toLowerCase();
					tF = false;
				}
			}
			if(BaseDeDatos.existeJ(jugador)) {
				mC = true;
				if(input.getMouseX() >= 310 && input.getMouseX() <= 414 && input.getMouseY() >= 457 && input.getMouseY() <= 559 
						&& input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					mC = false;
					tF = true;
				}else if(input.getMouseX() >= 870 && input.getMouseX() <= 977 && input.getMouseY() >= 449 && input.getMouseY() <= 554 
						&& input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					mIJ = true;
					mC = false;
					if(BaseDeDatos.mayorP(jugador, player.getScore())) {
					}else {
						BaseDeDatos.agregarPuntuacion(jugador, player.getScore());
						nB = true;
					}
				}
			}else{
				BaseDeDatos.agregarPuntuacion(jugador, player.getScore());
				nB = true;
				mIJ = true;
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
