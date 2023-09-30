package es.uco.pw.data.common;




import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * 
 * @author CarlosPC
 * Interfaz genérica
 * @param <T>
 * @param <K>
 */
public abstract class DAO <T,K> {
	
	String cadena;
	
	/**
	 * 
	 * @return a connection 
	 */
public Connection getConnection(String pathConfig) throws FileNotFoundException, IOException{
		
		Connection con = null;
		String[] cadena = pathConfig.split(",");
		
		
		try {
			
			con=DriverManager.getConnection(cadena[0], cadena[1], cadena[2]);

			Class.forName("com.mysql.jdbc.Driver");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return con;
	}
	
	/**
	 * 
	 * @param a
	 */
	public abstract void insert(T a) throws DAOException;
	
	/**
	 * 
	 * @param id
	 */
	public abstract void update(T a) throws DAOException;
	
	/**
	 * 
	 * @param id
	 */
	public abstract void remove(K a) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public abstract T getObject(K a) throws DAOException;
	

}
