package es.deusto.prog3.metalslug.main;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu_Opciones extends BasicGameState{
	Image fondo;
	Image b_fs, b_fs_sele;
	Image b_MM, b_MM_sele;
	
	boolean fs_sele,MM_sele;
	int mouseX,mouseY;
	
	public Menu_Opciones(int state) {
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		fondo = new Image("resources/Menu/Menu_op/fondo_logo.jpg");
		
		b_fs = new Image("resources/Menu/Menu_op/b_fs.png");
		b_fs_sele = new Image("resources/Menu/Menu_op/b_fs_sele.png");
		fs_sele = false;
		
		b_MM = new Image("resources/Menu/Menu_p/Menu_principal.png");
		b_MM_sele = new Image("resources/Menu/Menu_p/Menu_principal_sel.png");
		MM_sele = false;
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		fondo.draw(0,0);
		
		if(fs_sele) {
			b_fs_sele.draw(192,225);
		}else{
			b_fs.draw(192,225);
		}
		if(MM_sele) {
			b_MM_sele.draw(1074,633);
		}else {
			b_MM.draw(1074,633);
		}
		g.drawString("X:" + mouseX + " Y:" + mouseY, 100, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		if((mouseX >= 1074 && mouseX <= 1260) && (mouseY >= 20 && mouseY <= 85)) {
			MM_sele = true;
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(0);
			}
		} else {
			MM_sele = false;
		}
		
		if((mouseX >= 192 && mouseX <= 548) && (mouseY >= 407 && mouseY <= 493)) {
			if(Mouse.isButtonDown(0)) {
				if(fs_sele) {
					gc.setFullscreen(false);
					fs_sele = false;
				}else {
					fs_sele = true;
					gc.setFullscreen(true);
				}
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
