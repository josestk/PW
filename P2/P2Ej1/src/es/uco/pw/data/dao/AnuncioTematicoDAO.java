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
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.business.contacto.Interes;
import es.uco.pw.business.tablon.AnuncioTematico;
import es.uco.pw.business.tablon.Fases;
import es.uco.pw.data.dao.common.DAO;

public class AnuncioTematicoDAO extends DAO<AnuncioTematico, Integer>{

	private String pathSql;
	private String pathConfig;

	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;
	private ResultSet rs = null;
	
	public AnuncioTematicoDAO(String pathSql, String pathConfig) {
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
	}
	
	@Override
	public void insert(AnuncioTematico a) throws DAOException{
		// TODO Auto-generated method stub
		
		String intereses =  GestorContactos.getGestor().interesesToString(a.getIntereses());
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
		
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioTematico"));
			stat.setInt(1,a.getId());
			stat.setString(2,a.getTitulo());
			stat.setString(3, a.getCuerpo());
			stat.setString(4, a.getPropietario().getEmail());
			stat.setString(5, String.valueOf(a.getTipo()));
			stat.setString(6, String.valueOf(a.getFase()));
			stat.setDate(7, new Date(a.getFechaPublicacion().getTime()));
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
			
			AnuncioTematicoContactoDAO aTIC = new AnuncioTematicoContactoDAO(pathSql,pathConfig);	
			AnuncioTematicoInteresesDAO aTII = new AnuncioTematicoInteresesDAO(pathSql,pathConfig);
			aTII.insert(a);
			aTIC.insert(a);
		}	
	}

	@Override
	public void update(AnuncioTematico a) throws DAOException{

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioTematico"));

			stat.setString(1,a.getTitulo());
			stat.setString(2, a.getCuerpo());
			stat.setString(3, a.getPropietario().getEmail());
			stat.setString(4, String.valueOf(a.getFase()));
			stat.setDate(5, new Date(a.getFechaPublicacion().getTime()));
			stat.setString(6, GestorContactos.getGestor().interesesToString(a.getIntereses()));
			stat.setInt(7,a.getId());
			
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
			AnuncioTematicoContactoDAO aTIC = new AnuncioTematicoContactoDAO(pathSql,pathConfig);	
			AnuncioTematicoInteresesDAO aTII = new AnuncioTematicoInteresesDAO(pathSql,pathConfig);
			aTII.update(a);
			aTIC.update(a);
		}
	}

	@Override
	public void remove(Integer id) throws DAOException{

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));

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
			AnuncioTematicoContactoDAO aTIC = new AnuncioTematicoContactoDAO(pathSql,pathConfig);	
			AnuncioTematicoInteresesDAO aTII = new AnuncioTematicoInteresesDAO(pathSql,pathConfig);
			aTII.remove(id);
			aTIC.remove(id);
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
		
		AnuncioTematico anuncio = new AnuncioTematico(rs.getInt("id"),rs.getString("titulo"),rs.getString("cuerpo"),propietario,GestorContactos.getGestor().stringToIntereses(rs.getString("intereses")));
		
		anuncio.setDestinatarios(rs.getString("destinatarios"));
		anuncio.setFase(Fases.valueOf(rs.getString("fase")));
		anuncio.setFechaPublicacion(rs.getDate("fecha_publicacion"));

		return anuncio;
		
	}

	@Override
	public AnuncioTematico getObject(Integer id) throws DAOException {

		AnuncioTematico anuncio = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("getAnuncioTematico"));
			stat.setInt(1, id);
			stat.setInt(2, id);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				
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
	public ArrayList<AnuncioTematico> getByFecha(Date fecha) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioTematico> getByPropietario(String email){
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
	public ArrayList<AnuncioTematico> getByDestinatario(String email) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		email = "%"+ email+ "%";
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
	public ArrayList<AnuncioTematico> getByIntereses(Interes interes) throws DAOException{
		
		ArrayList<AnuncioTematico> anuncios = new ArrayList<AnuncioTematico>();
		AnuncioTematico anuncio = null;
		
		
		String stringInteres = "%"+ interes.getInteres() + "%";
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
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
}
