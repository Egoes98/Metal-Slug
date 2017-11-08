package es.deusto.prog3.metalslug.main;

import org.newdawn.slick.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.state.*;

public class Main_menu extends BasicGameState{
	Image fondo;
	Image campaing, campaing_sele, scores, scores_sele, options, options_sele, exit_game, exit_game_sele;

	Boolean sel_c = false, sel_s = false, sel_o = false, sel_e = false;
	
	int mouseY;
	int mouseX;
	
	
	public Main_menu(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		fondo = new Image ("resources/Menu/Main_menu/fondo_logo.png");
		
		campaing = new Image("resources/Menu/Main_menu/boton_campaña.png");
		campaing_sele = new Image("resources/Menu/Main_menu/boton_campaña_sele.png");
		
		scores = new Image("resources/Menu/Main_menu/boton_score.png");
		scores_sele = new Image("resources/Menu/Main_menu/boton_score_sele.png");
		
		options = new Image("resources/Menu/Main_menu/boton_options.png");
		options_sele = new Image("resources/Menu/Main_menu/boton_options_sele.png");
		
		exit_game = new Image("resources/Menu/Main_menu/boton_exit.png"); 
		exit_game_sele = new Image("resources/Menu/Main_menu/boton_exit_sele.png"); 
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException { // Para pintar el contenido
		fondo.draw(0,0);
		
		if(sel_c) {
			campaing_sele.draw(96,136);
		}else {
			campaing.draw(96,136);
		}
		
		if(sel_s) {
			scores_sele.draw(352,136);
		}else {
			scores.draw(352,136);
		}
		
		if(sel_o) {
			options_sele.draw(96,251);
		}else {
			options.draw(96,251);
		}
		
		if(sel_e) {
			exit_game_sele.draw(352,251);
		}else {
			exit_game.draw(352,251);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException { //Para cuando las cosas cambian
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		if((mouseX >= 96 && mouseX <= 277) && (mouseY >= 158 && mouseY <= 223)) {
			sel_c = true;
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(1);
			}
		}else {
			sel_c = false;
		}
		
		if((mouseX >= 352 && mouseX <= 533) && (mouseY >= 158 && mouseY <= 223)) {
			sel_s = true;
			if(Mouse.isButtonDown(0)) {
				
			}
		}else {
			sel_s = false;
		}
		
		if((mouseX >= 96 && mouseX <= 277) && (mouseY >= 43 && mouseY <= 108)) {
			sel_o = true;
			if(Mouse.isButtonDown(0)) {
				
			}
		}else {
			sel_o = false;
		}
		
		if((mouseX >= 352 && mouseX <= 533) && (mouseY >= 43 && mouseY <= 108)) {
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
