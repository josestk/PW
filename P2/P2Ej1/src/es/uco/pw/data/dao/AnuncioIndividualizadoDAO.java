package es.uco.pw.data.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.tablon.AnuncioIndividualizado;
import es.uco.pw.business.tablon.Fases;
import es.uco.pw.data.dao.common.DAO;



public class AnuncioIndividualizadoDAO extends DAO<AnuncioIndividualizado, Integer>{
	
	private String pathSql;
	private String pathConfig;
	private PreparedStatement stat = null;
	private Properties consultas = new Properties();
	private ResultSet rs = null;
	private Connection conn = null;
	public AnuncioIndividualizadoDAO(String pathSql, String pathConfig) {
		
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
	}
	
	@Override
	public void insert(AnuncioIndividualizado a) throws DAOException{


		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioIndividualizado"));
			stat.setInt(1,a.getId());
			stat.setString(2,a.getTitulo());
			stat.setString(3, a.getCuerpo());
			stat.setString(4, a.getPropietario().getEmail());
			stat.setString(5, a.getDestinatarios());
			stat.setString(6, String.valueOf(a.getTipo()));
			stat.setString(7, String.valueOf(a.getFase()));
			stat.setDate(8, new Date(a.getFechaPublicacion().getTime()));
			
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

	@Override
	public void update(AnuncioIndividualizado a) throws DAOException{

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioIndividualizado"));

			stat.setString(1,a.getTitulo());
			stat.setString(2, a.getCuerpo());
			stat.setString(3, a.getPropietario().getEmail());
			stat.setString(4, a.getDestinatarios());
			stat.setString(5, String.valueOf(a.getTipo()));
			stat.setString(6, String.valueOf(a.getFase()));
			stat.setDate(7, new Date(a.getFechaPublicacion().getTime()));
			stat.setInt(8,a.getId());
			
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

	@Override
	public void remove(Integer id) throws DAOException {

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));

			
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
	 * Saves the info contained in rs into the real object.
	 * @param rs.
	 * @return AnuncioIndividualizado. 
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

	@Override
	public AnuncioIndividualizado getObject(Integer id) throws DAOException{

		AnuncioIndividualizado a = null;

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));


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
	public ArrayList<AnuncioIndividualizado> getByFecha(Date fecha) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioIndividualizado> getByPropietario(String email) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
	public ArrayList<AnuncioIndividualizado> getByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioIndividualizado> anuncios = new ArrayList<AnuncioIndividualizado>();
		AnuncioIndividualizado a = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
		
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
}
