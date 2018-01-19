package es.deusto.prog3.metalslug.main;


import java.util.HashMap;

import javax.swing.JComboBox;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;

public class Menu_P extends BasicGameState{
	Image fondo;
	Image b_pnivel, b_pnivel_sele, b_ptotales, b_ptotales_sele, b_MM, b_MM_sele, cuadrado_p, boton_n1, boton_n2, boton_n3, boton_n4, boton_n5;
	
	int mouseY;
	int mouseX;
	
	boolean pnivel, ptotales, MM;
	
	int nivel = 0;
	
	boolean p_nivel, p_total, sel_nivel;
	
	HashMap<String, Integer> p;
	
	public Menu_P(int state) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		fondo = new Image ("resources/Menu/Menu_p/fondo_logo.jpg");
		
		b_pnivel = new Image("resources/Menu/Menu_p/P_N.png");
		b_pnivel_sele = new Image("resources/Menu/Menu_p/P_Nivel_sel.png");
		pnivel = false;
		
		b_ptotales = new Image("resources/Menu/Menu_p/P_Total.png");
		b_ptotales_sele = new Image("resources/Menu/Menu_p/P_Total_sel.png");
		ptotales = false;
		
		b_MM = new Image("resources/Menu/Menu_p/Menu_principal.png");
		b_MM_sele = new Image("resources/Menu/Menu_p/Menu_principal_sel.png");
		MM = false;
		
		cuadrado_p = new Image("resources/Menu/Menu_p/Cuadrado_p.png");
		
		boton_n1 = new Image("resources/Menu/Menu_p/boton_n1.png");
		boton_n2 = new Image("resources/Menu/Menu_p/boton_n2.png");
		boton_n3 = new Image("resources/Menu/Menu_p/boton_n3.png");
		boton_n4 = new Image("resources/Menu/Menu_p/boton_n4.png");
		boton_n5 = new Image("resources/Menu/Menu_p/boton_n5.png");
		
		p_nivel = false;
		p_total = false;
		sel_nivel = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		fondo.draw(0,0);
		cuadrado_p.draw(347, 250);
		
		if(pnivel) {
			b_pnivel_sele.draw(49,455);
		}else {
			b_pnivel.draw(49,455);
		}
		if(ptotales) {
			b_ptotales_sele.draw(49,291);
		}else {
			b_ptotales.draw(49,291);
		}
		if(MM) {
			b_MM_sele.draw(1074,633);
		}else {
			b_MM.draw(1074,633);
		}
		if(p_nivel) {
			boton_n1.draw(362,296);
			boton_n2.draw(362,356);
			boton_n3.draw(362,416);
			boton_n4.draw(362,476);
			boton_n5.draw(362,536);
		}
		
		if(p_nivel && sel_nivel) {
			int y = 296;
			for(String key:p.keySet()) {
				g.drawString("Jugador: " +key + "     Puntos:" + p.get(key), 433, y);
				y += 20;
			}
			
		}
		
		if(p_total) {
			int y = 296;
			for(String key:p.keySet()) {
				g.drawString("Jugador: " +key + " Puntos:" + p.get(key), 433, y);
				y += 20;
			}
		}
		
		g.drawString("X:" + mouseX + " Y:" +mouseY, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		if((mouseX >= 49 && mouseX <= 318) && (mouseY >= 167 && mouseY <= 264)) {
			pnivel = true;
			if(Mouse.isButtonDown(0)) {
				p_nivel = true;
				p_total = false;
			}
		} else {
			pnivel = false;
		}
		
		if((mouseX >= 49 && mouseX <= 318) && (mouseY >= 264 && mouseY <= 427)) {
			ptotales = true;
			if(Mouse.isButtonDown(0)) {
				p_total = true;
				p_nivel = false;
				sel_nivel = false;
				p = BaseDeDatos.puntuacionTotal();
			}
		} else {
			ptotales = false;
		}
		
		if((mouseX >= 1074 && mouseX <= 1260) && (mouseY >= 20 && mouseY <= 85)) {
			MM = true;
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(0);
			}
		} else {
			MM = false;
		}
		
	
			if((mouseX >= 362 && mouseX <= 407) && (mouseY >= 377 && mouseY <= 423)) {
				if(Mouse.isButtonDown(0)) {
					p = BaseDeDatos.puntuacionNivel(1);
					sel_nivel = true;
				}
			}
			if((mouseX >= 362 && mouseX <= 407) && (mouseY >= 317 && mouseY <= 363)) {
				if(Mouse.isButtonDown(0)) {
					p = BaseDeDatos.puntuacionNivel(2);
					sel_nivel = true;
				}
			}
			if((mouseX >= 362 && mouseX <= 407) && (mouseY >= 257 && mouseY <= 303)) {
				if(Mouse.isButtonDown(0)) {
					p = BaseDeDatos.puntuacionNivel(3);
					sel_nivel = true;
				}
			}
			if((mouseX >= 362 && mouseX <= 407) && (mouseY >= 137 && mouseY <= 183)) {
				if(Mouse.isButtonDown(0)) {
					p = BaseDeDatos.puntuacionNivel(4);
					sel_nivel = true;
				}
			}
			if((mouseX >= 362 && mouseX <= 407) && (mouseY >= 167 && mouseY <= 264)) {
				if(Mouse.isButtonDown(0)) {
					p = BaseDeDatos.puntuacionNivel(5);
					sel_nivel = true;
				}
			}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
