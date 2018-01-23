package es.deusto.prog3.metalslug.main;

import org.newdawn.slick.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.state.*;

public class MainMenu extends BasicGameState{
	Image fondo;
	Image campaing, campaing_sele, scores, scores_sele, options, options_sele, exit_game, exit_game_sele;

	Boolean sel_c = false, sel_s = false, sel_o = false, sel_e = false;
	
	int mouseY;
	int mouseX;
	
	
	public MainMenu(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		fondo = new Image ("resources/Menu/Main_menu720/fondo_logo.jpg");
		
		campaing = new Image("resources/Menu/Main_menu720/boton_campana.png");
		campaing_sele = new Image("resources/Menu/Main_menu720/boton_campana_sele.png");
		
		scores = new Image("resources/Menu/Main_menu720/boton_score.png");
		scores_sele = new Image("resources/Menu/Main_menu720/boton_score_sele.png");
		
		options = new Image("resources/Menu/Main_menu720/boton_options.png");
		options_sele = new Image("resources/Menu/Main_menu720/boton_options_sele.png");
		
		exit_game = new Image("resources/Menu/Main_menu720/boton_exit.png"); 
		exit_game_sele = new Image("resources/Menu/Main_menu720/boton_exit_sele.png"); 
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException { // Para pintar el contenido
		fondo.draw(0,0);
		
		if(sel_c) {
			campaing_sele.draw(192,272);
		}else {
			campaing.draw(192,272);
		}
		
		if(sel_s) {
			scores_sele.draw(704,272);
		}else {
			scores.draw(704,272);
		}
		
		if(sel_o) {
			options_sele.draw(192,502);
		}else {
			options.draw(192,502);
		}
		
		if(sel_e) {
			exit_game_sele.draw(704,502);
		}else {
			exit_game.draw(704,502);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException { //Para cuando las cosas cambian
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		if((mouseX >= 192 && mouseX <= 554) && (mouseY >= 316 && mouseY <= 446)) {
			sel_c = true;
			if(Mouse.isButtonDown(0)) {
				sbg.getState(11).init(gc, sbg);
				sbg.enterState(11);
			}
		} else {
			sel_c = false;
		}
		
		if((mouseX >= 704 && mouseX <= 1066) && (mouseY >= 316 && mouseY <= 446)) {
			sel_s = true;
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(2);
			}
		} else {
			sel_s = false;
		}
		
		if((mouseX >= 192 && mouseX <= 554) && (mouseY >= 86 && mouseY <= 216)) {
			sel_o = true;
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(3);
			}
		} else {
			sel_o = false;
		}
		
		if((mouseX >= 704 && mouseX <= 1066) && (mouseY >= 83 && mouseY <= 216)) {
			sel_e = true;
			if(Mouse.isButtonDown(0)) {
				System.exit(0);
			}
		}else {
			sel_e = false;
		}
	}


	public int getID() {
		return 0;
	}

}
