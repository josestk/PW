package es.uco.pw.data.dao.contacto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.Intereses;
import es.uco.pw.data.common.*;

public class ContactoDAO extends DAO<Contacto,String>{
		
	private String pathSql;
	private String pathConfig;
	
	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;
	
	public ContactoDAO(String pathSql,String pathConfig){
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
	}
	
	@Override
	public void insert(Contacto a) throws DAOException {		
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));

			stat = conn.prepareStatement(consultas.getProperty("insertContacto"));
		
			stat.setString(1, a.getEmail());
			stat.setString(2, a.getNombre());
			stat.setString(3, a.getApellidos());
			stat.setDate(4, new Date(a.getFechaNacimiento().getTime()) );
			stat.setInt(5, a.getEdad());
			stat.setString(6, a.getPassword());
			
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
		ContactoInteresDAO contactoInteres = new ContactoInteresDAO(pathSql,pathConfig);
		
		contactoInteres.insert(a);
	}

	@Override
	public void update(Contacto a) throws DAOException {
		
		
		try {
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("updateContacto"));
			
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellidos());
			stat.setDate(3, new Date(a.getFechaNacimiento().getTime()) );
			stat.setInt(4, a.getEdad());
			stat.setString(5, a.getPassword());
			stat.setString(6, a.getEmail());

			
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
		ContactoInteresDAO contactoInteres = new  ContactoInteresDAO(pathSql,pathConfig);
		
		contactoInteres.update(a);
	}

	@Override
	public void remove(String a) throws DAOException {

		try {
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("deleteContacto"));
			
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
		
		ContactoInteresDAO contactoInteres = new  ContactoInteresDAO(pathSql,pathConfig);
		
		contactoInteres.remove(a);
	}

	@Override
	public Contacto getObject(String a) throws DAOException {
		
		Contacto contacto = new Contacto();
		ResultSet rs = null;
		
		try {

			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("getContactoByEmail"));
			stat.setString(1, a);
			rs = stat.executeQuery();
			
			
			if(rs.next()) {
				contacto.setEmail(rs.getString("email"));
				contacto.setNombre(rs.getString("nombre"));
				contacto.setApellidos(rs.getString("apellidos"));
				contacto.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				contacto.setEdad(rs.getInt("edad"));
				contacto.setPassword(rs.getString("password"));
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

		ContactoInteresDAO contactoInteres = new  ContactoInteresDAO(pathSql,pathConfig);
		
		ArrayList<Intereses> intereses = contactoInteres.getObject(a).getIntereses();
		contacto.setIntereses(intereses);
		return contacto;
	}
	
}