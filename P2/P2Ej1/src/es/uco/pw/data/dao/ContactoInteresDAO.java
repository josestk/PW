package es.uco.pw.data.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.business.contacto.*;
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.data.dao.common.DAO;

public class ContactoInteresDAO extends DAO<Contacto, String>{
		 
	private String pathSql;
	private String pathConfig;
	
	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;
	
	public ContactoInteresDAO(String pathSql,String pathConfig){
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
	}
	
	@Override
	public void insert(Contacto a) throws DAOException {		
		
		String intereses = GestorContactos.getGestor().interesesToString(a.getIntereses());
		try {
			if(this.conn == null) {
				this.conn = getConnection(pathConfig);
			}
			consultas.load(new FileInputStream(new File(this.pathSql)));
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

	@Override
	public void update(Contacto a) throws DAOException {
		
		String intereses = GestorContactos.getGestor().interesesToString(a.getIntereses());
		try {
			if(this.conn == null) {
				this.conn = getConnection(pathConfig);
			}
			consultas.load(new FileInputStream(new File(this.pathSql)));
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

	@Override
	public void remove(String a) throws DAOException {

		try {
			if(this.conn == null) {
				this.conn = getConnection(pathConfig);
			}
			consultas.load(new FileInputStream(new File(this.pathSql)));
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

	@Override
	public Contacto getObject(String a) throws DAOException {
		
		Contacto contacto = new Contacto();
		ResultSet rs = null;
		String intereses = new String();
		try {
			if(this.conn == null) {
				this.conn = getConnection(pathConfig);
			}
			consultas.load(new FileInputStream(new File(this.pathSql)));
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
