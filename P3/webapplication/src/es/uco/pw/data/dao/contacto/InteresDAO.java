package es.uco.pw.data.dao.contacto;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.bussiness.contacto.Interes;
import es.uco.pw.bussiness.contacto.Intereses;
import es.uco.pw.data.common.*;

/**
 * DAO class of Intereses 
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public class InteresDAO extends DAO{
	
	private String sqlConfig;
	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;
	
	/**
	 * Constructor of ContactoDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */
	public InteresDAO(String sqlConfig){

		this.sqlConfig = sqlConfig;
	}

	/**
	 * Saves a interest into the data base
	 * @param interes. A contact
	 * @throws DAOException
	 */
	public void insertInteres(Intereses interes) throws DAOException {
	
		 try {
				this.conn = getConnection(sqlConfig);
				consultas.load(getClass().getResourceAsStream("sql.properties"));

				stat = conn.prepareStatement(consultas.getProperty("insertInteres"));
			
				
				stat.setString(1, interes.toString());
				
			
				if (stat.executeUpdate() == 0) {
				
					throw new DAOException("Fallo en la insercion");
				}
			}catch(SQLException ex) {
			
				throw new DAOException("Fallo en insertar interes", ex);
			}catch(Exception e){
				e.printStackTrace();
				}finally {
			
				if(stat != null) {
				
					try {
					
						stat.close();
						

					}catch(SQLException ex) {
					
						throw new DAOException("Fallo al comunicarse con la base datos", ex);
					}
				}
			}
	}
	
	/**
	 * Remove the interesfrom the data base
	 * @param interes. The interes that will be removed
	 * @throws DAOException
	 */
	public void removeInteres(Integer interes) throws DAOException {
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("deleteInteres"));
			
			stat.setInt(1, interes);
			
		} catch (SQLException e) {
			
			throw new DAOException("ERROR SQL, e");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
			if(stat != null) {

				try {
					
					stat.close();
					

				}catch(SQLException e){
					
					throw new DAOException("ERROR SQL, e");
				}
			}
		}
	}

	/**
	 * Search in the data base for a interest
	 * @param a. A string with the interest to search
	 * @return An interest
	 * @throws DAOException
	 */
	public Intereses getInteres(Integer a) throws DAOException {

		ResultSet rs = null;
		Interes interes = null;
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getInteresById"));
			stat.setInt(1, a);
			rs = stat.executeQuery();
			
			if(rs.next()) {
				interes = new Interes();
				interes.setId(a);
				interes.setInteres(rs.getString("INTERES"));
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(stat != null && rs != null) {

				try {
					rs.close();
					stat.close();
					
				}catch(SQLException e){
					
					throw new DAOException("ERROR SQL, e");
				}
			}
		}
		
		
		return Intereses.valueOf(interes.getInteres());
	}

}



