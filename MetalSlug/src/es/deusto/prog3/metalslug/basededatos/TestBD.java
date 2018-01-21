package es.deusto.prog3.metalslug.basededatos;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

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
	
	
	

}
