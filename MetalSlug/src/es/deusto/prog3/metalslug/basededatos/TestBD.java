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
		//BaseDeDatos.crearTablas();
		BaseDeDatos.agregarPuntuacion("Alvaro", 500, 1);
		BaseDeDatos.agregarPuntuacion("Alvaro", 600, 2);
		
		HashMap<String, Integer> puntuacionNivel;
		HashMap<String, Integer> puntuacionTotal;
		puntuacionNivel = BaseDeDatos.puntuacionNivel(1);
		puntuacionTotal = BaseDeDatos.puntuacionTotal();
		
		
		assertTrue(500 == puntuacionNivel.get("Alvaro"));
		assertTrue(1100 == puntuacionTotal.get("Alvaro"));
		
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
