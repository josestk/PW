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
import es.uco.pw.business.tablon.AnuncioGeneral;
import es.uco.pw.business.tablon.Fases;

import es.uco.pw.data.dao.common.DAO;


public class AnuncioGeneralDAO extends DAO<AnuncioGeneral, Integer>{

	 private String pathSql;
	 private String pathConfig;
	 
	 private ResultSet rs = null;
	 private Properties consultas = new Properties();
	 private PreparedStatement stat = null;
	 private Connection conn = null;
	 
	 public AnuncioGeneralDAO(String pathSql, String pathConfig) {
		 
		 this.pathSql = pathSql;
		 this.pathConfig = pathConfig;
	 }
	
	@Override
	public void insert(AnuncioGeneral a) throws DAOException{

		try {
			
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioGeneral"));
			
			stat.setInt(1,a.getId());
			stat.setString(2, a.getCuerpo());
			stat.setString(3, a.getTitulo());
			stat.setString(4, a.getPropietario().getEmail());
			stat.setString(5,"-");
			stat.setString(6, String.valueOf(a.getTipo()));
			stat.setString(7, String.valueOf(a.getFase()));
			stat.setDate(8, new Date(a.getFechaPublicacion().getTime()) );
			
			if ( stat.executeUpdate() == 0) { // cuantas filas se han introducido 
				
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

	@Override
	public void update(AnuncioGeneral a) throws DAOException{

		try {
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioGeneral"));
			
			stat.setString(1,a.getCuerpo());
			stat.setString(2,a.getTitulo());
			stat.setString(3,a.getPropietario().getEmail());
			stat.setString(4, String.valueOf(a.getDestinatarios()));
			stat.setString(5, String.valueOf(a.getTipo()));
			stat.setString(6, String.valueOf(a.getFase()));
			stat.setDate(7,new Date(a.getFechaPublicacion().getTime()));
			stat.setInt(8, a.getId());
			
			
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

	@Override
	public void remove(Integer id) throws DAOException{

		try {
			
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
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
	 * Saves the info contained in rs into the real object.
	 * @param rs.
	 * @return AnuncioGeneral. 
	 * @throws SQLException
	 */
	private AnuncioGeneral conversion(ResultSet rs) throws SQLException{
		
		Contacto propietario = new Contacto();
		propietario.setEmail(rs.getString("propietario"));
		
		AnuncioGeneral anuncio = new AnuncioGeneral(rs.getInt("id"),rs.getString("titulo"),rs.getString("cuerpo"),propietario);
		
		anuncio.setFase(Fases.valueOf(rs.getString("fase")));
		anuncio.setFechaPublicacion(rs.getDate("fecha_publicacion"));

		return anuncio;

	}

	@Override
	public AnuncioGeneral getObject(Integer id) throws DAOException{

		AnuncioGeneral a = null;

		try {
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
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
	public ArrayList<AnuncioGeneral> getByFecha(Date fecha) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
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
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioGeneral> getByPropietario(String email) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
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
	public ArrayList<AnuncioGeneral> getByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioGeneral> anuncios = new ArrayList<AnuncioGeneral>();
		AnuncioGeneral a = null;
		
		try {
			
			consultas.load(new FileInputStream(new File(pathSql)));
			this.conn = getConnection(pathConfig);
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
}
