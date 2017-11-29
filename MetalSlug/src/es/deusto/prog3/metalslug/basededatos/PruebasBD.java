package es.deusto.prog3.metalslug.game.basededatos;
import java.util.HashMap;

public class PruebasBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, Integer> puntuacion = new HashMap<>();
		 HashMap<String, Integer> puntT = new HashMap<>();
		 HashMap<String, Integer> puntN = new HashMap<>();
		
		puntuacion.put("Egoitz", 1);
		puntuacion.put("Player", 1);
		puntuacion.put("Rama",1);
		BaseDeDatos.AñadirDatos(puntuacion,1);
		
		puntuacion.replace("Egoitz", 1);
		puntuacion.replace("Rama",1);
		puntuacion.replace("Player", 1);
		BaseDeDatos.AñadirDatos(puntuacion,2);
		
		puntT = BaseDeDatos.puntuacionTotal();
		
		for(String key:puntT.keySet()) {
			System.out.println("Jugador: " + key);
			System.out.println("Puntos: " + puntT.get(key));
			System.out.println("--------");
		}
		
		/*puntN = BaseDeDatos.puntuacionNivel(1);
		
		for(String key:puntN.keySet()) {
			System.out.println("Jugador: " + key);
			System.out.println("Puntos: " + puntN.get(key));
			System.out.println("--------");
		}*/
	}

}
