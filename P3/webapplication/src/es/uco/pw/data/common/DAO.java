package es.uco.pw.data.common;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Generic DAO class.
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public abstract class DAO {
	
	String cadena;
	
	/**
	 * Connect to SQL data base
	 * @param sqlConfig. An String with the configuration parameters to connect to SQL Data base
	 * @return a connection 
	 */
public Connection getConnection(String sqlConfig) throws FileNotFoundException, IOException{
		
		Connection con = null;
		String[] cadena = sqlConfig.split(",");
	
		
		try {
			
			con=DriverManager.getConnection(cadena[0], cadena[1], cadena[2]);

			Class.forName("com.mysql.jdbc.Driver");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return con;
	}
	
}
