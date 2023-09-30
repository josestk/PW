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
 * DAO object of AnuncioGeneral object
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public class AnuncioGeneralDAO extends DAO{


	 private String sqlConfig;
	 
	 private ResultSet rs = null;
	 private Properties consultas = new Properties();
	 private PreparedStatement stat = null;
	 private Connection conn = null;
	 
	/**
	 * Constructor of AnuncioFlashDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */	
	 public AnuncioGeneralDAO(String sqlConfig) {
		 
		this.sqlConfig = sqlConfig;
	 }
	
	/**
	 * Saves the general announcement referenced in the data base
	 * @param anuncio. A general announcement
	 * @throws DAOException
	 */
	public void insertAnuncioGeneral(AnuncioGeneral anuncio) throws DAOException{

		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioGeneral"));
			
			stat.setInt(1,anuncio.getId());
			stat.setString(2, anuncio.getCuerpo());
			stat.setString(3, anuncio.getTitulo());
			stat.setString(4, anuncio.getPropietario().getEmail());
			stat.setString(5,"");
			stat.setString(6, String.valueOf(anuncio.getTipo()));
			stat.setString(7, String.valueOf(anuncio.getFase()));
			stat.setDate(8, new Date(anuncio.getFechaPublicacion().getTime()) );
			
			if ( stat.executeUpdate() == 0) { 
				
				throw new DAOException("No se ha insertado correctamente");
			}
		} catch(SQLException ex){
			
			throw new DAOException("Error al introducir en SQL", ex);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
			if(stat != null) {
				
				try {
					stat.close();
					
				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en insert", ex);
				}
			}
		} 
		
	}

	/**
	 * Update the general announcement in the data base with the same id that the announcement referenced
	 * @param anuncio. A general announcement
	 * @throws DAOException
	 */
	public void updateAnuncioGeneral(AnuncioGeneral anuncio) throws DAOException{

		try {
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioGeneral"));
			
			stat.setString(1,anuncio.getCuerpo());
			stat.setString(2,anuncio.getTitulo());
			stat.setString(3,anuncio.getPropietario().getEmail());
			stat.setString(4, String.valueOf(anuncio.getDestinatarios()));
			stat.setString(5, String.valueOf(anuncio.getTipo()));
			stat.setString(6, String.valueOf(anuncio.getFase()));
			stat.setDate(7,new Date(anuncio.getFechaPublicacion().getTime()));
			stat.setInt(8, anuncio.getId());
			
			
			if( stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha insertado correctamente");		
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al actualizar la base de datos", ex);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
			if( stat !=null ) {
				
				try {	
					stat.close();
					
				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en update", ex);
				}
			}
		}	
	}

	/**
	 * Remove the general announcement in the data base with the same id that the id referenced
	 * @param id. An integer.
	 * @throws DAOException
	 */
	public void removeAnuncioGeneral(Integer id) throws DAOException{

		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioGeneral"));
			stat.setInt(1, id);
			
			if( stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha eliminado correctamente");
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al eliminar", ex);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		}
			
			if( stat != null) {
				
				try {
					
					stat.close();
					
				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en delete", ex);
				}
			}
		}
	
	/**
	 * Saves the information contained in rs into the real object.
	 * @param rs. A result set object
	 * @return AnuncioGeneral object with the information found
	 * @throws SQLException
	 */
	private AnuncioGeneral conversion(ResultSet rs) throws SQLException{
		
		Contacto propietario = new Contacto();
		propietario.setEmail(rs.getString("propietario"));
		
		AnuncioGeneral anuncio = new AnuncioGeneral(rs.getInt("id"),rs.getString("titulo"),rs.getString("cuerpo"),propietario);
		String destinatarios = rs.getString("destinatarios");
		anuncio.setFase(Fases.valueOf(rs.getString("fase")));
		anuncio.setFechaPublicacion(rs.getDate("fecha_publicacion"));
		anuncio.setDestinatarios(destinatarios);

		return anuncio;

	}

	/**
	 * Search for the general announcement in the data base with the same id that the id referenced
	 * @param idBuscar. An integer
	 * @return AnuncioGeneral object with the announcement found
	 * @throws DAOException
	 */
	public AnuncioGeneral getAnuncioGeneral(Integer id) throws DAOException{

		AnuncioGeneral a = null;

		try {
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnuncioGeneral"));
			stat.setInt(1,id);
			rs = stat.executeQuery(); 
			
			if (rs.next()) {
				
				a = conversion(rs);

			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al conectar en get", ex);
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
					
					throw new DAOException("Error al cerrar en el get", ex);
				}
			}
		}
		
		
		return a;
	}

	/**
	 * Search in the data base an advertisement published before the date referenced.
	 * @param fecha A date.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioGeneral> getAnuncioGeneralByFecha(Date fecha) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosGeneralesByFecha"));
			stat.setDate(1, fecha);
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
	 * Search in the data base general advertisements published before the date referenced by fin and after the date referenced fin
	 * @param inicio, fin two dates.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioGeneral> getAnuncioGeneralByIntervalo(Date inicio, Date fin) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosGeneralesByIntervalo"));
			stat.setDate(1, inicio);
			stat.setDate(2, fin);
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
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner�s email.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioGeneral> getAnuncioGeneralByPropietario(String email) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosGeneralesByPropietario"));
			stat.setString(1, email);
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
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 */
	public ArrayList<AnuncioGeneral> getAnuncioGeneralByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosGeneralesByDestinatario"));
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
	public ArrayList<AnuncioGeneral> getAnuncioGeneralByFase(String fase) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosGeneralesByFase"));
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
	public ArrayList<AnuncioGeneral> getAnuncioGeneralByFaseYPropietario(String fase, String propietario) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosGeneralesByFaseYPropietario"));
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
