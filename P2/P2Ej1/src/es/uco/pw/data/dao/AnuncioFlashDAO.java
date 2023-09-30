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

import es.uco.pw.business.tablon.AnuncioFlash;
import es.uco.pw.business.tablon.Fases;
import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.data.dao.common.DAO;

public class AnuncioFlashDAO extends DAO<AnuncioFlash, Integer>{
	
	 private String pathSql;
	 private String pathConfig;
	 
	 private ResultSet rs = null;
	 private Properties consultas = new Properties();
	 private PreparedStatement stat = null;
	 private Connection conn = null;
	 
	 public AnuncioFlashDAO(String pathSql,String pathConfig) {
		 
		 this.pathSql = pathSql;
		 this.pathConfig = pathConfig;
	 }
	 
	 
	@Override
	public void insert(AnuncioFlash a) throws DAOException{

		Properties consultas = new Properties();
		PreparedStatement stat = null;
		try {
			this.conn = getConnection(pathConfig);

			consultas.load(new FileInputStream(new File(pathSql)));			
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioFlash"));
			stat.setInt(1,a.getId());
			stat.setString(2,a.getTitulo());
			stat.setString(3, a.getCuerpo());
			stat.setString(4, a.getPropietario().getEmail());
			stat.setString(5, "-");
			stat.setString(6, String.valueOf(a.getTipo()));
			stat.setString(7, String.valueOf(a.getFase()));
			stat.setDate(8, new Date(a.getFechaPublicacion().getTime()));
			stat.setDate(9,new Date(a.getFechaInicio().getTime()));
			stat.setDate(10,new Date(a.getFechaFinal().getTime()));

			
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

	@Override
	public void update(AnuncioFlash a) throws DAOException{

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));

			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioFlash"));

			stat.setString(1,a.getTitulo());
			stat.setString(2, a.getCuerpo());
			stat.setString(3, a.getPropietario().getEmail());
			stat.setString(4, "-");
			stat.setString(5, String.valueOf(a.getTipo()));
			stat.setString(6, String.valueOf(a.getFase()));
			stat.setDate(7, new Date(a.getFechaPublicacion().getTime()));
			stat.setDate(8,new Date(a.getFechaInicio().getTime()));
			stat.setDate(9,new Date(a.getFechaFinal().getTime()));
			stat.setInt(10,a.getId());
			
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

	@Override
	public void remove(Integer id) throws DAOException{

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));

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
	 * Saves the info contained in rs into the real object.
	 * @param rs.
	 * @return AnuncioFlash. 
	 * @throws SQLException
	 */
	private AnuncioFlash conversion(ResultSet rs) throws SQLException{
		
		Contacto propietario = new Contacto();
		
		propietario.setEmail(rs.getString("propietario"));
		
		AnuncioFlash anuncio = new AnuncioFlash(rs.getInt("id"),rs.getString("titulo"),rs.getString("cuerpo"),propietario,rs.getDate("fecha_inicio"),rs.getDate("fecha_final"));
		
		anuncio.setFase(Fases.valueOf(rs.getString("fase")));
		anuncio.setFechaPublicacion(rs.getDate("fecha_publicacion"));

		return anuncio;
	}
	
	@Override
	public AnuncioFlash getObject(Integer idBuscar) throws DAOException{
		
		AnuncioFlash a = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
	public ArrayList<AnuncioFlash> getByFecha(Date fecha) throws DAOException{
	
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash anuncio = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioFlash> getByPropietario(String email) throws DAOException{
		
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash anuncio = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByPropietario"));
			stat.setString(1, email);
			rs = stat.executeQuery();
			
			if (rs.next()) {
				
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
	public ArrayList<AnuncioFlash> getByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioFlash> anuncios = new ArrayList<AnuncioFlash>();
		AnuncioFlash anuncio = null;

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("getAnunciosFlashByDestinatario"));
			rs = stat.executeQuery();
			
			if (rs.next()) {
				
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

}
