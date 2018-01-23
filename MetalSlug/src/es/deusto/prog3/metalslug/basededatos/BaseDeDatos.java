package es.deusto.prog3.metalslug.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import es.deusto.prog3.metalslug.game.entities.Enemy;
import es.deusto.prog3.metalslug.game.entities.Platform;
import es.deusto.prog3.metalslug.game.entities.Slope;

public class BaseDeDatos {
	static Connection connection = null;
	static Statement statement;

	public static void conectar() throws ClassNotFoundException {// Conecta la base de datos
		Class.forName("org.sqlite.JDBC");

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void desconectar() {// Desconecta la base de datos
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public static void crearTablas() throws ClassNotFoundException {// crea las tablas de puntuacion y plataformas.
		try {
			conectar();
			statement.executeUpdate("create table puntuacion (jugador String, puntos int, nivel int);");
			statement.executeUpdate(
					"create table plataformas (tipo string, x1 float, y1 float, x2 float, y2 float, atravesable tinyint, nivel int);");
			statement.executeUpdate("create table enemigos (x float, y float, minx float, maxx float, nivel int);");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		desconectar();
	}

	public static void agregarPuntuacion(String jugador, int puntos, int nivel) {// Añade la puntuacion al final de cada
																					// nivel
		try {
			conectar();
			boolean repetido = false;
			ResultSet rs = statement.executeQuery("select jugador from puntuacion where nivel=" + nivel + "");
			while (rs.next()) {
				// Leer el resultset
				if (rs.getString("jugador").equals(jugador)) {
					repetido = true;
					break;
				}
			}
			if (repetido) {
				statement.executeUpdate("update puntuacion set puntos=" + puntos + " WHERE jugador='" + jugador
						+ "' AND nivel=" + nivel + ";");
			} else {
				statement.executeUpdate(
						"insert into puntuacion values('" + jugador + "', " + puntos + ", " + nivel + ")");
			}
			repetido = false;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desconectar();
	}

	public static HashMap<String, Integer> puntuacionNivel(int nivel) {// Saca puntuacion por nivel pedido
		HashMap<String, Integer> puntN = new HashMap<>();
		try {
			conectar();
			ResultSet rs = statement.executeQuery("select jugador, puntos from puntuacion where nivel=" + nivel + "");
			while (rs.next()) {
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

	public static HashMap<String, Integer> puntuacionTotal() {// Saca puntuaciones totales
		HashMap<String, Integer> puntT = new HashMap<>();
		try {
			conectar();
			ResultSet rp = statement
					.executeQuery("select jugador, sum(puntos) as pT from puntuacion group by jugador;");
			while (rp.next()) {
				puntT.put(rp.getString("jugador"), rp.getInt("pT"));
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		desconectar();
		return puntT;
	}

	public static boolean existeJ(String jugador) {// Comprueba si existe ese jugador
		boolean existe = false;
		try {
			conectar();
			ResultSet rs = statement.executeQuery("select jugador from puntuacion;");
			while (rs.next()) {
				if (rs.getString("jugador").equals(jugador)) {
					existe = true;
					break;
				}
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		desconectar();
		return existe;
	}

	public static boolean mayorP(String jugador, int puntos) {// Comprueba si la puntuacion obtenida es mejor que la que
																// ya tenia en caso de repeticion
		boolean mayor = false;
		try {
			conectar();
			ResultSet rs = statement.executeQuery("select puntos from puntuacion where jugador='" + jugador + "';");
			while (rs.next()) {
				int p = rs.getInt("puntos");
				if (puntos < p) {
					mayor = true;
				}
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		desconectar();
		return mayor;
	}

	public static void guardarPlataformas(ArrayList<Shape> plataformas, int nivel) {
		try {
			conectar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "";
		for (Shape shape : plataformas) {
			if (shape instanceof Platform) {
				Platform pl = (Platform) shape;
				sql = "insert into plataformas values ('Platform'," + pl.getX() + "," + pl.getY() + "," + pl.getWidth()
						+ "," + pl.getHeight() + "," + (pl.isAtravesable() ? 1 : 0) + "," + nivel + ")";
			} else if (shape instanceof Slope) {
				Slope slope = (Slope) shape;
				float[] point0 = slope.getPoint(0);
				float[] point1 = slope.getPoint(1);
				sql = "insert into plataformas values ('Slope'," + point0[0] + "," + point0[1] + "," + point1[0] + ","
						+ point1[1] + "," + 0 + "," + nivel + ")";
			}
			try {
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<Shape> getPlataformas(int nivel) {
		try {
			conectar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Shape> plataformas = new ArrayList<>();
		try {
			ResultSet plats = statement.executeQuery("select * from plataformas where nivel=" + nivel + "");
			while (plats.next()) {
				if (plats.getString(1).equals("Platform")) {
					plataformas.add(new Platform(plats.getFloat(2), plats.getFloat(3), plats.getFloat(4),
							plats.getFloat(5), plats.getBoolean(6)));
				} else if (plats.getString(1).equals("Slope")) {
					plataformas
							.add(new Slope(plats.getFloat(2), plats.getFloat(3), plats.getFloat(4), plats.getFloat(5)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plataformas;

	}

	public static void guardarEnemigo(float x, float y, float minX, float maxX, int nivel) {
		try {
			conectar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "";
		try {
			sql = "insert into enemigos values (" + x + "," + y + "," + minX + ","
						+ maxX + "," + nivel + ");";
			statement.executeUpdate(sql);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static ArrayList<Enemy> getEnemigos(int nivel) {
		try {
			conectar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Enemy> enemigos =  new ArrayList<>();
		try {
			ResultSet enems = statement.executeQuery("select * from enemigos where nivel=" + nivel + "");
			while (enems.next()) {
				enemigos.add(new Enemy(enems.getFloat(1), enems.getFloat(2), enems.getFloat(3), enems.getFloat(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enemigos;

	}
	public static void borrar() {
		try {
			conectar();
			statement.executeUpdate("drop table puntuacion");
			statement.executeUpdate("drop table plataformas");
			statement.executeUpdate("drop table enemigos");
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		desconectar();
	}
	
}
