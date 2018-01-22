package es.deusto.prog3.metalslug.main;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;
import es.deusto.prog3.metalslug.game.entities.Platform;
import es.deusto.prog3.metalslug.game.entities.Slope;

public class GenerarBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Shape> platforms;
		try {
			BaseDeDatos.crearTablas();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Nivel 1
		platforms = new ArrayList<>();
		platforms.add(new Platform(1700, 680, 7947, 40, false));
		platforms.add(new Platform(1760, 525, 154, 5, true));
		platforms.add(new Platform(1911, 417, 175, 5, true));
		platforms.add(new Platform(2086, 362, 436, 50, false));
		platforms.add(new Platform(3868, 382, 528, 20, false));
		platforms.add(new Platform(4734, 382, 528, 40, false));
		platforms.add(new Platform(3576, 526, 236, 10, true));
		platforms.add(new Platform(4492, 526, 186, 10, true));
		platforms.add(new Platform(5554, 526, 132, 10, true));
		platforms.add(new Platform(5554, 440, 132, 10, true));
		platforms.add(new Platform(5787, 335, 855, 32, false));
		platforms.add(new Slope(0, 609, 466, 513));
		platforms.add(new Slope(466, 513, 925, 652));
		platforms.add(new Slope(923, 652, 1274, 560));
		platforms.add(new Slope(1274, 560, 1716, 680));
		platforms.add(new Platform(0, 640, 1500, 80, false));
		platforms.add(new Platform(-20, 0, 20, 720, false));
		platforms.add(new Platform(7947, 0 , 20, 720, false));
		BaseDeDatos.guardarPlataformas(platforms, 1);
		
		// Nivel 2
		platforms.clear();
		platforms.add(new Platform(0, 535, 1027, 40, false));
		platforms.add(new Slope(771, 535, 1027, 420));
		platforms.add(new Platform(0, 579, 8454, 40, false));
		platforms.add(new Platform(1518, 430, 189, 100, false));
		platforms.add(new Platform(2034, 430, 214, 100, false));
		platforms.add(new Platform(2700, 430, 858, 100, false));
		platforms.add(new Slope(4320, 560, 4054, 420));
		platforms.add(new Platform(4054, 560, 1979, 40, false));
		platforms.add(new Slope(5778, 560, 6033, 420));
		platforms.add(new Platform(7017, 430, 243, 100, false));
		platforms.add(new Platform(7695, 430, 759, 100, false));
		platforms.add(new Platform(4827, 389, 267, 10, true));
		platforms.add(new Platform(5285, 389, 267, 10, true));
		platforms.add(new Platform(-20, 0, 20, 720, false));
		platforms.add(new Platform(7947, 0 , 20, 720, false));

		BaseDeDatos.guardarPlataformas(platforms, 2);
	}

}
