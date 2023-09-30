package es.uco.pw.data.dao.anuncios;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.bussiness.contacto.GestorContactos;
import es.uco.pw.bussiness.contacto.Interes;
import es.uco.pw.bussiness.contacto.Intereses;
import es.uco.pw.bussiness.anuncios.*;
import es.uco.pw.data.common.DAO;
import es.uco.pw.data.common.DAOException;

public class AnuncioTematicoDAO extends DAO{

	private String sqlConfig;

	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;
	private ResultSet rs = null;
	
	/**
	 * Constructor of AnuncioTematicoDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */	
	public AnuncioTematicoDAO(String sqlConfig) {
		this.sqlConfig = sqlConfig;
	}
	
	/**
	 * Saves the thematic announcement referenced in the data base
	 * @param anuncio. A flash announcement
	 * @throws DAOException
	 */
	public void insertAnuncioTematico(AnuncioTematico anuncio) throws DAOException{
	
		String intereses =  GestorContactos.getGestor().interesesToString(anuncio.getIntereses());
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
		
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioTematico"));
			stat.setInt(1,anuncio.getId());
			stat.setString(2,anuncio.getTitulo());
			stat.setString(3, anuncio.getCuerpo());
			stat.setString(4, anuncio.getPropietario().getEmail());
			stat.setString(5, String.valueOf(anuncio.getTipo()));
			stat.setString(6, String.valueOf(anuncio.getFase()));
			stat.setDate(7, new Date(anuncio.getFechaPublicacion().getTime()));
			stat.setString(8,intereses);
			
			if(stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la insercion");
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al insertar anunciotematico", ex);
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
			
			AnuncioTematicoContactoDAO aTIC = new AnuncioTematicoContactoDAO(sqlConfig);	
			AnuncioTematicoInteresesDAO aTII = new AnuncioTematicoInteresesDAO(sqlConfig);
			aTII.insertAnuncioTematicoIntereses(anuncio);
			aTIC.insertAnuncioTematicoContacto(anuncio);
		}	
	}

	/**
	 * Update the thematic announcement in the data base with the same id that the announcement referenced
	 * @param anuncio. A thematic announcement
	 * @throws DAOException
	 */
	public void updateAnuncioTematico(AnuncioTematico anuncio) throws DAOException{

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioTematico"));

			stat.setString(1,anuncio.getTitulo());
			stat.setString(2, anuncio.getCuerpo());
			stat.setString(3, anuncio.getPropietario().getEmail());
			stat.setString(4, String.valueOf(anuncio.getFase()));
			stat.setDate(5, new Date(anuncio.getFechaPublicacion().getTime()));
			stat.setString(6, GestorContactos.getGestor().interesesToString(anuncio.getIntereses()));
			stat.setInt(7,anuncio.getId());
			
			if (stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la actualizacion");
			}
			
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al actualizar anuncio tematico", ex);
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
			AnuncioTematicoContactoDAO aTIC = new AnuncioTematicoContactoDAO(sqlConfig);	
			AnuncioTematicoInteresesDAO aTII = new AnuncioTematicoInteresesDAO(sqlConfig);
			aTII.updateAnuncioTematicoIntereses(anuncio);
			aTIC.updateAnuncioTematicoContacto(anuncio);
		}
	}

	/**
	 * Remove the thematic announcement in the data base with the same id that the id referenced
	 * @param id. An integer.
	 * @throws DAOException
	 */
	public void removeAnuncioTematico(Integer id) throws DAOException{

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioTematico"));
			stat.setInt(1, id);
			
			if( stat.executeUpdate() == 0) {
				
				throw new DAOException("Fallo en la eliminacion del anuncio");
			}
			
		}catch(SQLException ex) {
			
			throw new DAOException("Error al eliminar anuncio tematico",ex);
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
			AnuncioTematicoContactoDAO aTIC = new AnuncioTematicoContactoDAO(sqlConfig);	
			AnuncioTematicoInteresesDAO aTII = new AnuncioTematicoInteresesDAO(sqlConfig);
			aTII.removeAnuncioTematicoIntereses(id);
			aTIC.removeAnuncioTematicoContacto(id);
		}
		
	}
	
	/**
	 * Saves the info contained in rs into the real object.
	 * @param rs.
	 * @return AnuncioTematico. 
	 * @throws SQLException
	 */
	private AnuncioTematico conversion(ResultSet rs) throws SQLException{
		
		Contacto propietario = new Contacto();
		propietario.setEmail(rs.getString("propietario"));
		
		AnuncioTematico anuncio = new AnuncioTematico(rs.getInt("id"),rs.getString("titulo"),rs.getString("cuerpo"),propietario,GestorContactos.getGestor().stringToIntereses(rs.getString("interes")));
		
		anuncio.setDestinatarios(rs.getString("destinatarios"));
		anuncio.setFase(Fases.valueOf(rs.getString("fase")));
		anuncio.setFechaPublicacion(rs.getDate("fecha_publicacion"));

		return anuncio;
		
	}

	/**
	 * Search for the thematic announcement in the data base with the same id that the id referenced
	 * @param idBuscar. An integer
	 * @return AnuncioTematico object with the announcement found
	 * @throws DAOException
	 */
	public AnuncioTematico getAnuncioTematico(Integer id) throws DAOException {

		AnuncioTematico anuncio = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnuncioTematico"));
			stat.setInt(1, id);
			stat.setInt(2, id);
			rs = stat.executeQuery();
			
			if(rs.next()) {
				
				anuncio = conversion(rs);

			}
			

		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if ( rs != null) {
					
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			if (stat != null ) {

				try {
					stat.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}	
		
		return anuncio;
		
	}

	/**
	 * Search in the data base an advertisement published before the date referenced.
	 * @param fecha A date.
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioTematico> getAnuncioTematicoByFecha(Date fecha) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByFecha"));
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
	 * Search in the data base thematic advertisements published before the date referenced by fin and after the date referenced fin
	 * @param inicio, fin two dates
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioTematico> getAnuncioTematicoByIntervalo(Date inicio, Date fin) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByIntervalo"));
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
	 * @param email The owner's email.
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioTematico> getAnuncioTematicoByPropietario(String email){
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByPropietario"));
			stat.setString(1, email);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				anuncio = conversion(rs);
				anuncios.add(anuncio);
			}
			

		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if ( rs != null) {
					
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			if (stat != null ) {

				try {
					stat.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		
		return anuncios;
		
	}
	
	/**
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 */
	public ArrayList<AnuncioTematico> getAnuncioTematicoByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		email = "%"+ email+ "%";
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByDestinatario"));
			stat.setString(1, email);
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
	 * Search all advertisements with common "interes"
	 * @param interes The interes to search. 
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException 
	 */
	public ArrayList<AnuncioTematico> getAnuncioTematicoByIntereses(Interes interes) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		
		String stringInteres = "%"+ interes.getInteres() + "%";
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByIntereses"));
			stat.setString(1, stringInteres);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				anuncio = conversion(rs);
				anuncios.add(anuncio);
			}

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
	 * Search all advertisements with common "interes" with the user logged
	 * @param interes The interes to search. 
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException 
	 */
	public ArrayList<AnuncioTematico> getAnuncioTematicoByInteresesPropietario(ArrayList<Intereses> interes) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		int check = 0;
		
		String stringInteres = GestorContactos.getGestor().interesesToString(interes);

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByIntereses"));
			stat.setString(1, stringInteres);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				anuncio = conversion(rs);
				for(int i=0; i<anuncios.size(); i++) {
					if(anuncios.get(i).getId() == anuncio.getId()) {
						
						check = 1;
					}
				}
				
				if(check == 0) {
					
					anuncios.add(anuncio);

				}
			}

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
	public ArrayList<AnuncioTematico> getAnuncioTematicoByFase(String fase) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico a = null;
		
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByFase"));
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
	public ArrayList<AnuncioTematico> getAnunciosTematicosByFaseYPropietario(String fase, String propietario) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico a = null;
		int check = 0;
		try {
			
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosTematicosByFaseYPropietario"));
			stat.setString(1, fase);
			stat.setString(2, propietario);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
				a = conversion(rs);
				for(int i=0; i<anuncios.size(); i++) {
					if(anuncios.get(i).getId() == a.getId()) {
						
						check = 1;
					}
				}
				
				if(check == 0) {
					
					anuncios.add(a);

				}
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
