package es.uco.pw.data.dao.anuncios;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.bussiness.anuncios.*;
import es.uco.pw.data.common.DAO;
import es.uco.pw.data.common.DAOException;

/**
 * DAO object of AnuncioFlash object
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public class AnuncioFlashDAO extends DAO{
	
	 private String sqlConfig;
	 
	 private ResultSet rs = null;
	 private Properties consultas = new Properties();
	 private PreparedStatement stat = null;
	 private Connection conn = null;
	 
	/**
	 * Constructor of AnuncioFlashDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */	 
	 public AnuncioFlashDAO(String sqlConfig) {
		 
		 this.sqlConfig = sqlConfig;
	 }
	 
	 
	/**
	 * Saves the flash announcement referenced in the data base
	 * @param anuncio. A flash announcement
	 * @throws DAOException
	 */
	public void insertAnuncioFlash(AnuncioFlash anuncio) throws DAOException{

		Properties consultas = new Properties();
		PreparedStatement stat = null;
		try {
			this.conn = getConnection(sqlConfig);

			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
		
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioFlash"));
			stat.setInt(1,anuncio.getId());
			stat.setString(2,anuncio.getTitulo());
			stat.setString(3, anuncio.getCuerpo());
			stat.setString(4, anuncio.getPropietario().getEmail());
			stat.setString(5,"");
			stat.setString(6, String.valueOf(anuncio.getTipo()));
			stat.setString(7, String.valueOf(anuncio.getFase()));
			stat.setDate(8, new Date(anuncio.getFechaPublicacion().getTime()));
			stat.setDate(9,new Date(anuncio.getFechaInicio().getTime()));
			stat.setDate(10,new Date(anuncio.getFechaFinal().getTime()));

			
			if (stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la insercion");
			}
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo en insertar anuncio flash", ex);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally {		
			
			if(stat != null) {				
				try {
					
					stat.close();
									
				}catch(SQLException ex) {
			
					throw new DAOException("Fallo en insertar anuncio flash", ex);
				}
			}
		}
	}

	/**
	 * Update the flash announcement in the data base with the same id that the announcement referenced
	 * @param anuncio. A flash announcement
	 * @throws DAOException
	 */
	public void updateAnuncioFlash(AnuncioFlash anuncio) throws DAOException{

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioFlash"));

			stat.setString(1,anuncio.getTitulo());
			stat.setString(2, anuncio.getCuerpo());
			stat.setString(3, anuncio.getPropietario().getEmail());
			stat.setString(4, anuncio.getDestinatarios());
			stat.setString(5, String.valueOf(anuncio.getTipo()));
			stat.setString(6, String.valueOf(anuncio.getFase()));
			stat.setDate(7, new Date(anuncio.getFechaPublicacion().getTime()));
			stat.setDate(8,new Date(anuncio.getFechaInicio().getTime()));
			stat.setDate(9,new Date(anuncio.getFechaFinal().getTime()));
			stat.setInt(10,anuncio.getId());
			
			if (stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la actualizacion");
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo en la actualizacion del anuncio flash", ex);
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally {
			
			if( stat != null) {
				
				try {
					
					stat.close();
					
					
				}catch(SQLException ex) {
					
					throw new DAOException("Fallo en el cierre de actualizacion",ex);
				}
			}
		}
	}

	/**
	 * Remove the flash announcement in the data base with the same id that the id referenced
	 * @param id. An integer.
	 * @throws DAOException
	 */
	public void removeAnuncioFlash(Integer id) throws DAOException{

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioFlash"));
			stat.setInt(1, id);
			
			if( stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la eliminacion del anuncio");
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al eiliminar anuncio flash",ex);
		}catch(Exception e){
			
			e.printStackTrace();
			
			}finally {
			
			if (stat != null) {
				
				try {
					
					stat.close();
					
				}catch(SQLException ex) {
					
					throw new DAOException("Fallo en cierre del delete",ex);
				}
			}
		}
		
	}

	/**
	 * Saves the information contained in rs into the real object.
	 * @param rs. A result set object
	 * @return AnuncioFlash object with the information found
	 * @throws SQLException
	 */
	private AnuncioFlash conversion(ResultSet rs) throws SQLException{
		
		Contacto propietario = new Contacto();
		
		propietario.setEmail(rs.getString("propietario"));
		
		AnuncioFlash anuncio = new AnuncioFlash(rs.getInt("id"),rs.getString("titulo"),rs.getString("cuerpo"),propietario,rs.getDate("fecha_inicio"),rs.getDate("fecha_final"));
		
		anuncio.setFase(Fases.valueOf(rs.getString("fase")));
		anuncio.setFechaPublicacion(rs.getDate("fecha_publicacion"));
		anuncio.setDestinatarios(rs.getString("destinatarios"));

		return anuncio;
	}
	
	/**
	 * Search for the flash announcement in the data base with the same id that the id referenced
	 * @param idBuscar. An integer
	 * @return AnuncioFlash object with the announcement found
	 * @throws DAOException
	 */
	public AnuncioFlash getAnuncioFlash(Integer idBuscar) throws DAOException{
		
		AnuncioFlash a = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnuncioFlash"));
			stat.setInt(1, idBuscar);
			rs = stat.executeQuery();
			
			if (rs.next()) {
				
				a = conversion(rs);
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo al obtener anuncio flash", ex);
		}catch(Exception e){
			e.printStackTrace();
			}finally {
			
			if ( rs != null) {
				try {
					
					rs.close();

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el cursor", ex);
				}
			}
			
			if (stat != null ) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el get", ex);
				}
			}
		}
		
		return a;
	}
	
	/**
	 * Search in the data base an advertisement published before the date referenced.
	 * @param fecha A date.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioFlash> getAnuncioFlashByFecha(Date fecha) throws DAOException{
	
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash anuncio = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByFecha"));
			stat.setDate(1, fecha);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				anuncio = conversion(rs);
				anuncios.add(anuncio);
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al obtener lista de anuncios", ex);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if ( rs != null) {
				try {
					
					rs.close();

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el cursor", ex);
				}
			}
			
			if (stat != null ) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el getByfecha", ex);
				}
			}
		}
		
		
		return anuncios;
		
	}
	
	
	
	/**
	 * Search in the data base flash advertisements published before the date referenced by fin and after the date referenced fin
	 * @param inicio, fin two dates.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioFlash> getAnuncioFlashByIntervalo(Date inicio, Date fin) throws DAOException{
	
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash anuncio = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByIntervalo"));
			stat.setDate(1, inicio);
			stat.setDate(2, fin);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				anuncio = conversion(rs);
				anuncios.add(anuncio);
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al obtener lista de anuncios", ex);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if ( rs != null) {
				try {
					
					rs.close();

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el cursor", ex);
				}
			}
			
			if (stat != null ) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el getByfecha", ex);
				}
			}
		}
		
		
		return anuncios;
		
	}
	
	
	
	/**
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner�s email.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioFlash> getAnuncioFlashByPropietario(String email) throws DAOException{
		
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash anuncio = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByPropietario"));
			stat.setString(1, email);
			rs = stat.executeQuery();
			
			while (rs.next()) {
				
				anuncio = conversion(rs);
				anuncios.add(anuncio);
			}

							
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo al obtener anuncio flash", ex);
		}catch(Exception e){
			e.printStackTrace();
			}finally {
			
			if ( rs != null) {
				try {
					
					rs.close();

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el cursor", ex);
				}
			}
			
			if (stat != null ) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el get", ex);
				}
			}
		}
		
		return anuncios;
	}
	
	/**
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 */
	public ArrayList<AnuncioFlash> getAnuncioFlashByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash anuncio = null;

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByDestinatario"));
			rs = stat.executeQuery();
			
			while (rs.next()) {
				
				anuncio = conversion(rs);
				anuncios.add(anuncio);
			}
		
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo al obtener anuncio flash", ex);
		}catch(Exception e){
			e.printStackTrace();
			}finally {
			
			if ( rs != null) {
				try {
					
					rs.close();

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el cursor", ex);
				}
			}
			
			if (stat != null ) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el get", ex);
				}
			}
		}
		
		return anuncios;
	}
	
	/**
	 * Search all advertisements published.
	 * @param email The email to search.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 */
	public ArrayList<AnuncioFlash> getAnuncioFlashByFase(String fase) throws DAOException{
		
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByFase"));
			stat.setString(1, fase);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				a = conversion(rs);
				anuncios.add(a);
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al obtener lista de anuncios", ex);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if ( rs != null) {
				try {
					
					rs.close();

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el cursor", ex);
				}
			}
			
			if (stat != null ) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el getByfecha", ex);
				}
			}
		}
		
		
		return anuncios;
	}
	
	/**
	 * Search all advertisements published.
	 * @param email The email to search.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 */
	public ArrayList<AnuncioFlash> getAnuncioFlashByFaseYPropietario(String fase, String propietario) throws DAOException{
		
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByFaseYPropietario"));
			stat.setString(1, fase);
			stat.setString(2, propietario);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				a = conversion(rs);
				anuncios.add(a);
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al obtener lista de anuncios", ex);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if ( rs != null) {
				try {
					
					rs.close();

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el cursor", ex);
				}
			}
			
			if (stat != null ) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en el getByfecha", ex);
				}
			}
		}
		
		
		return anuncios;
	}

}
