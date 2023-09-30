package es.uco.pw.data.dao.contacto;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.bussiness.contacto.Intereses;
import es.uco.pw.data.common.*;

/**
 * DAO class of Contacto class
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public class ContactoDAO extends DAO{
		
	private String sqlConfig;
	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;

	/**
	 * Constructor of ContactoDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */
	public ContactoDAO(String sqlConfig){
		this.sqlConfig = sqlConfig;
	}
	
	/**
	 * Saves a contact into the data base
	 * @param contacto. A contact
	 * @throws DAOException
	 */
	public void insertContacto(Contacto contacto) throws DAOException {		
		
		try {

			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("insertContacto"));
		
			stat.setString(1, contacto.getEmail());
			stat.setString(2, contacto.getNombre());
			stat.setString(3, contacto.getApellidos());
			stat.setDate(4, new Date(contacto.getFechaNacimiento().getTime()) );
			stat.setInt(5, contacto.getEdad());
			stat.setString(6, contacto.getPassword());
			stat.setString(7, contacto.getFiltro());
			
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
		ContactoInteresDAO contactoInteres = new ContactoInteresDAO(sqlConfig);
		
		contactoInteres.insertContactoInteres(contacto);
	}
	
	
	/**
	 * Update a contact in the data base with the same email
	 * @param contacto. A contact with new data in it
	 * @throws DAOException
	 */
	public void updateContacto(Contacto contacto) throws DAOException {
		
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("updateContacto"));
			
			stat.setString(1, contacto.getNombre());
			stat.setString(2, contacto.getApellidos());
			stat.setDate(3, new Date(contacto.getFechaNacimiento().getTime()) );
			stat.setInt(4, contacto.getEdad());
			stat.setString(5, contacto.getPassword());
			stat.setString(6, contacto.getFiltro());
			stat.setString(7, contacto.getEmail());

			if (stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la actualizacion");
			}
			
		}catch(Exception e)
		{e.printStackTrace();
		}finally {
			
			if( stat != null) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Fallo en el cierre de actualizacion",ex);
				}
			}
		}
		ContactoInteresDAO contactoInteres = new  ContactoInteresDAO(sqlConfig);
		
		contactoInteres.updateContactoInteres(contacto);
	}

	/**
	 * Remove the contact with the email referenced from the data base
	 * @param email. The email of the contact that will be removed
	 * @throws DAOException
	 */
	public void removeContacto(String email) throws DAOException {

		try {

			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("deleteContacto"));
			
			stat.setString(1, email);
			
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
		
		ContactoInteresDAO contactoInteres = new  ContactoInteresDAO(sqlConfig);
		contactoInteres.removeContactoInteres(email);
	}

	/**
	 * Search in the data base for a contact with the email referenced
	 * @param email. The email of the contact wanted
	 * @return Contacto object with the contact found
	 * @throws DAOException
	 */
	public Contacto getContacto(String email) throws DAOException {		

		Contacto contacto = new Contacto();
		ResultSet rs = null;
		
		try {

			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getContactoByEmail"));
			stat.setString(1, email);
			rs = stat.executeQuery();
			
			if(rs.next()) {
				contacto.setEmail(rs.getString("email"));
				contacto.setNombre(rs.getString("nombre"));
				contacto.setApellidos(rs.getString("apellidos"));
				contacto.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				contacto.setEdad(rs.getInt("edad"));
				contacto.setPassword(rs.getString("password"));
				contacto.setFiltro(rs.getString("filtro"));
			}


			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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

		ContactoInteresDAO contactoInteres = new  ContactoInteresDAO(sqlConfig);
		
		ArrayList<Intereses> intereses = contactoInteres.getContactoInteres(email).getIntereses();
		contacto.setIntereses(intereses);
		return contacto;
	}
	
}