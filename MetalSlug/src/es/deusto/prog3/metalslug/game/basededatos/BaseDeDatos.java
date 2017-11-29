package es.deusto.prog3.metalslug.game.basededatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseDeDatos{
	 static Connection connection = null;
	 static Statement statement;
	 static int niveles = 5;
	 
	 public static void conectar() throws ClassNotFoundException {
		  Class.forName("org.sqlite.JDBC");

		  try {
			  connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			  statement = connection.createStatement();
			  statement.setQueryTimeout(30);
		  }catch(SQLException e) {
			  System.err.println(e.getMessage());
		  }
	 }
	 public static void desconectar() {
		 try{
			 if(connection != null) {
				 connection.close();
			 }
		 }
		 catch(SQLException e){
				  System.err.println(e);
		 }
	 }
	 public static void crearTablas() throws ClassNotFoundException {
			try {
				conectar();
				//Cuantos niveles tiene el juego
				for(int n = 1; n <= niveles; n++) {
					statement.executeUpdate("create table pnivel"+n+" (jugador String, puntos int)");
				}
			  }catch(SQLException e) {
				  System.err.println(e.getMessage());
			  }
			desconectar();
		 }
	 public static void AñadirDatos(HashMap<String, Integer> puntuacion, int nivel) {
		  try {
			  conectar();
			  boolean repetido = false;
			  for(String key : puntuacion.keySet()){
				  ResultSet rs = statement.executeQuery("select jugador from pnivel"+nivel);
				  while(rs.next()){
			   	      if(rs.getString("jugador").equals(key)) {
			   	    	  repetido = true;
			   	    	  break;
			   	      }
				  }
				  if(repetido) {
					  statement.executeUpdate("update pnivel"+nivel+" set puntos="+ puntuacion.get(key) +" WHERE jugador="+ "'" + key + "'" +";");
				  }else {
					  statement.executeUpdate("insert into pnivel"+nivel+" values("+ "'" +key + "'" +" , "+ puntuacion.get(key) +")");	
				  }
				  repetido = false;
			  }
		  } catch (SQLException | ClassNotFoundException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  desconectar();
	  }
	 public static HashMap<String, Integer> puntuacionNivel(int nivel) {
		 HashMap<String, Integer> puntN = new HashMap<>();
		 try {
			 conectar();
			 ResultSet rs = statement.executeQuery("select * from pnivel"+nivel);
			 while(rs.next()){
				 // Leer el resultset
				 puntN.put(rs.getString("jugador"), rs.getInt("puntos"));
			 }
		 } catch (SQLException | ClassNotFoundException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 desconectar();
		return puntN;
	 } 
	 public static HashMap<String, Integer> puntuacionTotal() {
		 HashMap<String, Integer> puntT = new HashMap<>();
		 String key;
		 int puntuacion = 0;

		 try {
			 conectar();
			 for(int i = 1; i <= niveles; i++){
				 ResultSet rs = statement.executeQuery("select jugador from pnivel"+i);
				 while(rs.next()){
					 key = rs.getString("jugador");
					 if(puntT.containsKey(key)) {
					 }else {
						 for(int n = 1; n <= niveles; n++){
							 ResultSet p = statement.executeQuery("select * from pnivel"+n+" where jugador='Egoitz';");
							 p.next();
							 //La siguiente linea es la qu eda el error.
							 puntuacion += p.getInt("puntos");
						 }
						 puntT.put(key, puntuacion);
					 }
				 }
			 } 
		 } catch (SQLException | ClassNotFoundException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 desconectar();
		return puntT;
	 }
		 
}
