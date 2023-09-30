package es.uco.pw.data.dao.contacto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.bussiness.contacto.*;
import es.uco.pw.bussiness.contacto.GestorContactos;
import es.uco.pw.data.common.*;

/**
 * DAO class for ContactoInteresDAO table
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public class ContactoInteresDAO extends DAO{
		 
	private String sqlConfig;
	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;

	/**
	 * Constructor of ContactoDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */
	public ContactoInteresDAO(String sqlConfig){
		this.sqlConfig = sqlConfig;
	}
	
	/**
	 * Saves the interests of a contact into the data base
	 * @param contacto. A contact with the email and interests wanted to be saved
	 * @throws DAOException
	 */
	public void insertContactoInteres(Contacto a) throws DAOException {		
		
		String intereses = GestorContactos.getGestor().interesesToString(a.getIntereses());
		try {

			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("insertContactoIntereses"));		
			stat.setString(1, a.getEmail());	
			stat.setString(2, intereses);
			
			if (stat.executeUpdate() == 0) {
			
				throw new DAOException("Fallo en la insercion");
			}

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
	 * Update the interests in the data base with the same email
	 * @param contacto. A contact with new data in it
	 * @throws DAOException
	 */
	public void updateContactoInteres(Contacto a) throws DAOException {
		
		String intereses = GestorContactos.getGestor().interesesToString(a.getIntereses());
		
		if(!intereses.isEmpty()) {

			try {

				this.conn = getConnection(sqlConfig);
				consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

				stat = conn.prepareStatement(consultas.getProperty("updateContactoInteres"));		
				stat.setString(2, a.getEmail());	
				stat.setString(1,intereses);
				
				if (stat.executeUpdate() == 0) {
				
					throw new DAOException("Fallo en la insercion");
				}
	
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
	}
	
	/**
	 * Remove the interest of the contact with the email referenced from the data base
	 * @param email. The email of the contact with the interests that will be removed
	 * @throws DAOException
	 */
	public void removeContactoInteres(String a) throws DAOException {

		try {
			
			this.conn = getConnection(sqlConfig);
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			stat = conn.prepareStatement(consultas.getProperty("deleteContactoIntereses"));
			
			stat.setString(1, a);
			
			if (stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la actualizacion");
			}
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
	 * Search in the data base for the interests with the email referenced
	 * @param email. The email of the contact wanted
	 * @return Contacto with the interests wanted
	 * @throws DAOException
	 */
	public Contacto getContactoInteres(String a) throws DAOException {
		
		Contacto contacto = new Contacto();
		ResultSet rs = null;
		String intereses = new String();
		try {
			if(this.conn == null) {
				this.conn = getConnection(sqlConfig);
			}
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			stat = conn.prepareStatement(consultas.getProperty("getContactoIntereses"));
			stat.setString(1, a);
			rs = stat.executeQuery();
			
			contacto = new Contacto();
			if(rs.next()) {
				
				intereses = rs.getString("interes");
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
					
					stat.close();
					rs.close();
					

				}catch(SQLException e){
					
					throw new DAOException("ERROR SQL, e");
				}
			}
		}
		if(!intereses.isEmpty()) {
			contacto.setIntereses(GestorContactos.getGestor().stringToIntereses(intereses));
		}
		return contacto;
	}

}
