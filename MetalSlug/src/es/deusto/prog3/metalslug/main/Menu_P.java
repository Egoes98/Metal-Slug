package es.deusto.prog3.metalslug.main;

import java.util.HashMap;

import javax.swing.JComboBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;

public class Menu_P extends BasicGameState{
	Image fondo;
	Image b_pnivel_on, b_pnivel_off, b_ptotales_on, b_ptotales_off;
	
	int nivel = 0;
	
	boolean p_nivel, p_total;
	
	HashMap<String, Integer> p;
	
	public Menu_P(int state) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		fondo = new Image ("resources/Menu/Main_menu720/fondo_logo.jpg");
		
		b_pnivel_on = new Image("");
		b_pnivel_off = new Image("");
		
		b_ptotales_on = new Image("");
		b_ptotales_off = new Image("");
		
		p_nivel = false;
		p_total = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		
		
		if(p_nivel) {
			switch (nivel) {
			case 1:
				p = BaseDeDatos.puntuacionNivel(1);
				break;
			case 2:
				p = BaseDeDatos.puntuacionNivel(2);
				break;
			case 3:
				p = BaseDeDatos.puntuacionNivel(3);
				break;
			case 4:
				p = BaseDeDatos.puntuacionNivel(4);
				break;

			default:
				break;
			}
			int y = 100;
			for(String key:p.keySet()) {
				g.drawString("Jugador: " +key + " Puntos:" + p.get(key), 100, y);
				y += 10;
			}
		}
		
		//El modo de imprimir esta claramente sin terminar porque la cosa seria meterlo en algo similar a una JList
		if(p_total) {
			int y = 100;
			p = BaseDeDatos.puntuacionTotal();
			for(String key:p.keySet()) {
				g.drawString("Jugador: " +key + " Puntos:" + p.get(key), 100, y);
				y += 10;
			}
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		
		if(true /* Pulsado boton de nivel*/) {
			//Aparece el slices y eliges nivel
		}
		
		if(true /*Apretando el boton de puntuaciones Totales*/) {
			p_total = true;
		}
		
		if(true /* Pulsado boton menu*/) {
			sbg.enterState(0);
		}
	
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
