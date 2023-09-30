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
 * DAO object for AnuncioIndividual object
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public class AnuncioIndividualizadoDAO extends DAO{
	
	private String sqlConfig;
	private PreparedStatement stat = null;
	private Properties consultas = new Properties();
	private ResultSet rs = null;
	private Connection conn = null;
	
	/**
	 * Constructor of AnuncioInvidualizadoDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */	
	public AnuncioIndividualizadoDAO(String sqlConfig) {
		
		this.sqlConfig = sqlConfig;
	}
	
	/**
	 * Saves the individual announcement referenced in the data base
	 * @param anuncio. A flash announcement
	 * @throws DAOException
	 */
	public void insertAnuncioIndividualizado(AnuncioIndividualizado anuncio) throws DAOException{


		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioIndividualizado"));
			stat.setInt(1,anuncio.getId());
			stat.setString(2,anuncio.getTitulo());
			stat.setString(3, anuncio.getCuerpo());
			stat.setString(4, anuncio.getPropietario().getEmail());
			stat.setString(5, anuncio.getDestinatarios());
			stat.setString(6, String.valueOf(anuncio.getTipo()));
			stat.setString(7, String.valueOf(anuncio.getFase()));
			stat.setDate(8, new Date(anuncio.getFechaPublicacion().getTime()));
			
			if (stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la insercion");
			}
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo en insertar anuncio individual", ex);
		}catch(Exception e){
			e.printStackTrace();
			}finally {
			
			if(stat != null) {
				
				try {
					
					stat.close();
					
					
				}catch(SQLException ex) {
					
					throw new DAOException("Fallo en insertar anuncio individual", ex);
				}
			}
		}
	}

	/**
	 * Update the individual announcement in the data base with the same id that the announcement referenced
	 * @param anuncio. A individual announcement
	 * @throws DAOException
	 */
	public void updateAnuncioIndividualizado(AnuncioIndividualizado anuncio) throws DAOException{

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioIndividualizado"));

			stat.setString(1,anuncio.getTitulo());
			stat.setString(2, anuncio.getCuerpo());
			stat.setString(3, anuncio.getPropietario().getEmail());
			stat.setString(4, anuncio.getDestinatarios());
			stat.setString(5, String.valueOf(anuncio.getTipo()));
			stat.setString(6, String.valueOf(anuncio.getFase()));
			stat.setDate(7, new Date(anuncio.getFechaPublicacion().getTime()));
			stat.setInt(8,anuncio.getId());
			
			if (stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la actualizacion");
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo en la actualizacion del anuncio individual", ex);
			
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
		
	}

	/**
	 * Remove the individual announcement in the data base with the same id that the id referenced
	 * @param id. An integer.
	 * @throws DAOException
	 */
	public void removeAnuncioIndividualizado(Integer id) throws DAOException {

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			
			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioIndividualizado"));
			stat.setInt(1,id);
			
			if( stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la eliminacion del anuncio");
			}
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo en la eliminacion del usuario",ex);
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
	 * @return AnuncioIndividualizado object with the information found
	 * @throws SQLException
	 */
	private AnuncioIndividualizado conversion(ResultSet rs) throws SQLException{
		
		Contacto propietario = new Contacto();
		propietario.setEmail(rs.getString("propietario"));
		
		AnuncioIndividualizado anuncio = new AnuncioIndividualizado(rs.getInt("id"),rs.getString("titulo"),rs.getString("cuerpo"),propietario,rs.getString("destinatarios"));
		
		anuncio.setFase(Fases.valueOf(rs.getString("fase")));
		anuncio.setFechaPublicacion(rs.getDate("fecha_publicacion"));

		return anuncio;

	}

	/**
	 * Search for the individual announcement in the data base with the same id that the id referenced
	 * @param idBuscar. An integer
	 * @return AnuncioIndividualizado object with the announcement found
	 * @throws DAOException
	 */
	public AnuncioIndividualizado getAnuncioIndividualizado(Integer id) throws DAOException{

		AnuncioIndividualizado a = null;

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));


			stat = conn.prepareStatement(consultas.getProperty("getAnuncioIndividualizado"));
			stat.setInt(1, id);
			rs = stat.executeQuery();
			
			if (rs.next()) {
				
				a = conversion(rs);
			}
			

			
		}catch(SQLException ex) {
			
			throw new DAOException("Fallo al obtener anuncio general", ex);
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
	 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioIndividualizado> getAnuncioIndividualizadoByFecha(Date fecha) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosIndividualizadosByFecha"));
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
		 * Search in the data base individuals advertisements published before the date referenced by fin and after the date referenced fin
		 * @param inicio, fin two dates.
		 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
		 * @throws DAOException
		 */
		public ArrayList<AnuncioIndividualizado> getAnuncioIndividualizadoByIntervalo(Date inicio, Date fin) throws DAOException{
			
			ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
			AnuncioIndividualizado a = null;
			
			try {
				this.conn = getConnection(sqlConfig);
				consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
				stat = conn.prepareStatement(consultas.getProperty("getAnunciosIndividualizadosByIntervalo"));
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
	 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioIndividualizado> getAnuncioIndividualizadoByPropietario(String email) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosIndividualizadosByPropietario"));
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
					
					throw new DAOException("Error al cerrar en el getByPropietario", ex);
				}
			}
		}
		
		return anuncios;
		
	}

	/**
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
	 */
	public ArrayList<AnuncioIndividualizado> getAnuncioIndividualizadoByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
		
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosIndividualizadosByDestinatario"));
			email = "%" + email + "%";
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
					
					throw new DAOException("Error al cerrar en el getByPropietario", ex);
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
	public ArrayList<AnuncioIndividualizado> getAnuncioIndividualizadoByFase(String fase) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosIndividualizadosByFase"));
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
	public ArrayList<AnuncioIndividualizado> getAnunciosIndividualizadosByFaseYPropietario(String fase, String propietario) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosIndividualizadosByFaseYPropietario"));
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
