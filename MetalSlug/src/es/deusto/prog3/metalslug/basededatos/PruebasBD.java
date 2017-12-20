package es.deusto.prog3.metalslug.basededatos;
import java.util.HashMap;

public class PruebasBD {

	public static void main(String[] args) {

		//Variables que se usan para guardar en tablas
		//jugador: Recoge el nombre del jugador y un a vexz guardada la info se resetea
		// puntuacion: esta variable recoge la puntuacion que has conseguido al cabo del nivel y al guardarla se resetea a 0.
		String jugador;
		int puntos;
		
		//Para guardar lo que retorna la base de datos
		HashMap<String, Integer> puntT = new HashMap<>();
		HashMap<String, Integer> puntN = new HashMap<>();
		
		/*cargar datos
		jugador = "Anselmo";
		puntos = 5;
		BaseDeDatos.AñadirPuntuacion(jugador, puntos, 2);
		Estos datos de ejemplo ya estan en la base de datos pueden cambiarse para añadir mas datos*/
		
	
		puntN = BaseDeDatos.puntuacionNivel(1);
		System.out.println("Puntos Nivel 1");
		System.out.println("--------------");
		for(String key:puntN.keySet()) {
			System.out.println("Jugador: " +key + " Puntos:" + puntN.get(key));
		}
		System.out.println();
		
		puntN = BaseDeDatos.puntuacionNivel(2);
		System.out.println("Puntos Nivel 2");
		System.out.println("---------------");
		for(String key:puntN.keySet()) {
			System.out.println("Jugador: " +key + " Puntos:" + puntN.get(key));
		}
		
		puntT = BaseDeDatos.puntuacionTotal();
		System.out.println();
		System.out.println("Puntuacion Global");
		System.out.println("-----------------");
		for(String key:puntN.keySet()) {
			System.out.println("Jugador: " +key + " Puntos:" + puntT.get(key));
		}
	}
	
	//Metodos que estara preente al dfinal de cada nivel y servira para añadir los datos a la base
	//Con este metodo comprobaremos si el nombre del jugador ya existe en la abse de datos
	     //Caso afirmativo: Se preguntara si se quiere sobrescribir la puntuacion
				// Si quieres simpelmente se sobrescribiran
				//Si no quieres se te pedira poner otro nombre diferente
		//En caso negativo: Se añadiran los datos directamente
	public static void agregarPuntos() {
		String jugador;
		int puntos = 0;
		int nivel = 1;
		boolean existe,mayor,seguir;
		
		seguir = false;
		mayor = true;
		do {
			//El jugador mete su nick, yo pongo uno de ejemplo
			jugador="Jony";
			existe = BaseDeDatos.existeJ(jugador);
			
			if(existe) {
				//Se manda un mensaje al jugador por si quiere sobrescribir
				if(true) {//SI
					mayor = BaseDeDatos.mayorP(jugador, puntos);
					seguir=true;
				}
			}else {
				mayor = BaseDeDatos.mayorP(jugador, puntos);
				seguir=true;
			}
		}while(seguir);
		
		if(mayor) {
			BaseDeDatos.AñadirPuntuacion(jugador, puntos, nivel);
		}
	}
}
