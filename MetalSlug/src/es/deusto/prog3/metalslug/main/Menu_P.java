package es.deusto.prog3.metalslug.main;


import java.util.HashMap;

import javax.swing.JComboBox;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;

public class Menu_P extends BasicGameState{
	Image fondo;
	Image b_MM, b_MM_sele, cuadrado_p;
	
	int mouseY;
	int mouseX;
	
	boolean MM;
	
	HashMap<String, Integer> p;
	private UnicodeFont scorefont;
	
	
	
	public Menu_P(int state) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		scorefont = new UnicodeFont("resources/mini_pixel-7.ttf", 40, false, false);
		scorefont.addAsciiGlyphs();
		scorefont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		scorefont.loadGlyphs();
		fondo = new Image ("resources/Menu/Menu_p/fondo_logo.jpg");

		b_MM = new Image("resources/Menu/Menu_p/Menu_principal.png");
		b_MM_sele = new Image("resources/Menu/Menu_p/Menu_principal_sel.png");
		MM = false;
		
		cuadrado_p = new Image("resources/Menu/Menu_p/Cuadrado_p.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		fondo.draw(0,0);
		cuadrado_p.draw(347, 250);

		if(MM) {
			b_MM_sele.draw(1074,633);
		}else {
			b_MM.draw(1074,633);
		}
		
		int y = 296;
		for(String key : p.keySet()) {
			scorefont.drawString(433, y, "Jugador: " +key + " Puntos:" + p.get(key));
			y += 30;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		
		
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		if((mouseX >= 1074 && mouseX <= 1260) && (mouseY >= 20 && mouseY <= 85)) {
			MM = true;
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(0);
			}
		} else {
			MM = false;
		}
		
	
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public void updatePoints() {
		p = BaseDeDatos.getPuntuacionR();
	}
}
