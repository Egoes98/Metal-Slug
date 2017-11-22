package es.deusto.prog3.metalslug.main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainGame extends StateBasedGame {

	public static final String gamename = "Metal Smug";
	public static final int main_menu = 0;
	public static final int menu_sel_per = 1;
	
	public MainGame(String gamename) {
		super(gamename);
		this.addState(new MainMenu(main_menu));
		this.addState(new MenuSelPer(menu_sel_per));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(main_menu).init(gc, this);
		this.getState(menu_sel_per).init(gc, this);
		this.enterState(main_menu);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer appgc;
		appgc = new AppGameContainer(new MainGame(gamename));
		appgc.setDisplayMode(1280, 720, false);
		appgc.start();
	}
}
