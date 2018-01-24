package es.deusto.prog3.metalslug.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;

public class TestPuntuacion extends BasicGame {

	private TextField nJugador;
	private UnicodeFont scorefont;
	private Image bg, volver, nb;
	private int score = 123456;

	private boolean guardado, newBest;

	public TestPuntuacion(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AppGameContainer container = new AppGameContainer(new TestPuntuacion("Prueba"), 1280, 720, false);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		bg.draw();
		nJugador.render(container, g);
		scorefont.drawString(450, 300, String.format("SCORE: %06d", score));
		if(guardado) {
			volver.draw(427, 628);
		}
		if(newBest) {
			nb.draw(700, 300);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		guardado = false;
		newBest = false;
		bg = new Image("resources/Menu_fp/fondo_fp2.png");
		volver = new Image("resources/Menu_fp/volver.png");
		nb = new Image("resources/Menu/Menu_fp/newBest.png");
		scorefont = new UnicodeFont("resources/mini_pixel-7.ttf", 60, false, false);
		scorefont.addAsciiGlyphs();
		scorefont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		scorefont.loadGlyphs();
		nJugador = new TextField(container, scorefont, 330, 480, 620, 80);

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		super.mousePressed(button, x, y);
		if (button == 0) {
			if (new Rectangle(427, 628, 373, 79).contains(x, y)) {
				if (!guardado) {
					if(true /*BaseDeDatos.existeJ("jugador") && BaseDeDatos.mayorP("jugador", score)*/) {
						newBest = true;
					}
					System.out.println("Guardado jugador " + nJugador.getText());
					nJugador.setAcceptingInput(false);
					guardado = true;
				} else {
					System.exit(0);
				}
			}
		}
	}

}
