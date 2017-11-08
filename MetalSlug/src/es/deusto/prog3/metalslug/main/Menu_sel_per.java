package es.deusto.prog3.metalslug.main;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import net.java.games.input.Keyboard;

public class Menu_sel_per extends BasicGameState {
	Image fondo;
	Image player1_pointer;
	Image per1,per1_sel;

	Boolean sel_per1 = false, sel_per2 = false, sel_per3 = false, sel_per4 = false;
	int mouseY, mouseX;

	public Menu_sel_per(int state) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		fondo = new Image ("resources/Menu/Player_sel/fondo.png");
		player1_pointer = new Image("resources/Menu/Player_sel/p_pointer.png");
		
		per1 = new Image ("resources/Menu/Player_sel/p1.png");
		per1_sel = new Image ("resources/Menu/Player_sel/p1_sel.png");
		
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		fondo.draw(0,0);
		
		//Personaje 1
		if(sel_per1) {
			per1_sel.draw(39,116);
			player1_pointer.draw(82,75);
		}else {
			per1.draw(39,116);
		}
		
	/*	//Personaje 2
		if(sel_per2) {
			player1_pointer.draw(225,75);
		}else {
		}
		
		//Personaje 3
		if(sel_per3) {
			player1_pointer.draw(370,75);
		}else {
			per1.draw(39,116);
		}
		
		//Personaje 4
		if(sel_per4) {
			player1_pointer.draw(514,75);
		}else {
		} */
	} 

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		//Personaje 1
		if((mouseX >= 38 && mouseX <= 173) && (mouseY <= 245 && mouseY >= 52)) {
			sel_per1 = true;
		}else {
			sel_per1 = false;
		}
		
		//Personaje 2
		if((mouseX >= 182 && mouseX <= 316) && (mouseY <= 245 && mouseY >= 52)) {
			sel_per2 = true;
		}else {
			sel_per2 = false;
		}
		
		//Personaje 3
		if((mouseX >= 323 && mouseX <= 460) && (mouseY <= 245 && mouseY >= 52)) {
			sel_per3 = true;
		}else {
			sel_per3 = false;
		}
		
		//Personaje 4
		if((mouseX >= 468 && mouseX <= 603) && (mouseY <= 245 && mouseY >= 52)) {
			sel_per4 = true;
		}else {
			sel_per4 = false;
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
