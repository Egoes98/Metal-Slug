package es.deusto.prog3.metalslug.main;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.basededatos.BaseDeDatos;
import es.deusto.prog3.metalslug.game.entities.Enemy;
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
		BaseDeDatos.guardarPlataformas(platforms, 1);
		BaseDeDatos.guardarEnemigo(810, 600, 600, 1140, 1);
		// Nivel 2
		platforms.clear();
		platforms.add(new Platform(0, 563, 865, 40, false));
        	platforms.add(new Slope(642, 580, 875, 489));
        	platforms.add(new Platform(0, 601, 7232, 40, false));
        	platforms.add(new Platform(1300, 489, 137, 100, false));
        	platforms.add(new Platform(1729, 489, 178, 100, false));
        	platforms.add(new Platform(2310, 489, 720, 100, false));
        	platforms.add(new Slope(3684, 579, 3456, 489));
        	platforms.add(new Platform(3456, 579, 1690, 40, false));
        	platforms.add(new Slope(4964, 589, 5175, 480));
        	platforms.add(new Platform(5997, 489, 175, 100, false));
        	platforms.add(new Platform(6581, 489, 656, 100, false));
        	platforms.add(new Platform(4133, 437, 189, 10, true));
        	platforms.add(new Platform(4516, 437, 189, 10, true));
		platforms.add(new Platform(-20, 0, 20, 720, false));

		BaseDeDatos.guardarPlataformas(platforms, 2);
		
		//Enemigos
		//Nivel1
		//Nivel2
		BaseDeDatos.guardarEnemigo(1314,470,1314,1422, 2);
		BaseDeDatos.guardarEnemigo(2397,470,2397,2925, 2);
		BaseDeDatos.guardarEnemigo(2925,470,2397,2925, 2);
		BaseDeDatos.guardarEnemigo(3462,470,3462,3675, 2);
		BaseDeDatos.guardarEnemigo(4125,300,4125,4302, 2);
		BaseDeDatos.guardarEnemigo(4503,300,4503,4692, 2);
		BaseDeDatos.guardarEnemigo(6726,470,6726,7143, 2);
		BaseDeDatos.guardarEnemigo(921,550,921,1239, 2);
		BaseDeDatos.guardarEnemigo(1476,550,1476,1638, 2);
		BaseDeDatos.guardarEnemigo(1953,550,1953,2193, 2);
		BaseDeDatos.guardarEnemigo(5187,550,5187,5889, 2);
		BaseDeDatos.guardarEnemigo(6249,550,1314,6468, 2);
	}

}
