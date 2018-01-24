package es.deusto.prog3.metalslug.basededatos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.game.entities.Platform;
import es.deusto.prog3.metalslug.game.entities.Slope;

public class TestBD {

	@Test
	public void testInsertarValores() throws ClassNotFoundException {
		
		//Se debe hacer la primera vez que ejecutas el test.
		
		//Prueba Insertar y Scara puntuaciones por jugador
		//BaseDeDatos.agregarPuntuacion("Alvaro", 500);
		//BaseDeDatos.agregarPuntuacion("Egoitz", 600);
		
		assertTrue(500 == BaseDeDatos.getPuntuacionJ("Alvaro"));
		assertTrue(600 == BaseDeDatos.getPuntuacionJ("Egoitz"));
		
		//Pruebas para sacar puntuaciones por ranking
		HashMap<String, Integer> puntuaciones;
		puntuaciones = BaseDeDatos.getPuntuacionR();
		
		assertTrue(500 == puntuaciones.get("Alvaro"));
		assertTrue(600 == puntuaciones.get("Egoitz"));
	}
	
	@Test
	public void testInsertarPlataformas() {
		ArrayList<Shape> plataformas = new ArrayList<>();
		/*
		try {
			BaseDeDatos.crearTablas();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Platform origp = new Platform(0, 1, 2, 3, true);
		Slope origs = new Slope(3, 2, 1, 0);
		plataformas.add(origp);
		plataformas.add(origs);
		
		BaseDeDatos.guardarPlataformas(plataformas, 1);
		
		ArrayList<Shape> plat2;
		plat2 = BaseDeDatos.getPlataformas(1);
		
		assertTrue(plataformas.size() == plat2.size());
				
		Platform platform = (Platform) plat2.get(0);
		Slope slope = (Slope) plat2.get(1);
		// Equals no funciona por alguna razón, comprobar todos los atributos
		assertTrue(origp.getX() == platform.getX());
		assertTrue(origp.getY() == platform.getY());
		assertTrue(origp.getWidth() == platform.getWidth());
		assertTrue(origp.getHeight() == platform.getHeight());
		assertTrue(origp.isAtravesable() == platform.isAtravesable());
		
		assertArrayEquals(slope.getPoints(), origs.getPoints(), 0);
	}
	

}
