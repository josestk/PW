package es.uco.pw.data.dao.common;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.data.dao.DAOException;


/**
 * 
 * @author CarlosPC
 * Interfaz genérica
 * @param <T>
 * @param <K>
 */
public abstract class DAO <T,K> {
	
	/**
	 * @return a connection 
	 */
	public Connection getConnection(String pathConfig) throws FileNotFoundException, IOException{
		
		Connection con = null;
		
		try {
			Properties config = new Properties();
			config.load(new FileInputStream(new File(pathConfig)));
			con=DriverManager.getConnection(config.getProperty("cadena"),config.getProperty("user"), config.getProperty("password"));

			Class.forName("com.mysql.jdbc.Driver");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return con;
	}
	
	/**
	 * Insert in to the data base T type object  "a"
	 * @param a
	 */
	public abstract void insert(T a) throws DAOException;
	
	/**
	 * Update in to the data base T type object  "a"
	 * @param id
	 */
	public abstract void update(T a) throws DAOException;
	
	/**
	 * Remove in to the data base T type object  "a"
	 * @param id
	 */
	public abstract void remove(K a) throws DAOException;
	
	/**
	 * Search in the data base K type object "a"
	 * @param id
	 * @return
	 */
	public abstract T getObject(K a) throws DAOException;
	

}
