package es.uco.pw.data.dao.anuncios;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.bussiness.contacto.GestorContactos;
import es.uco.pw.bussiness.anuncios.*;
import es.uco.pw.data.common.DAO;
import es.uco.pw.data.common.DAOException;

/**
 * DAO class for AnuncioTematicoInteresDAO table
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public class AnuncioTematicoInteresesDAO extends DAO{

	private String sqlConfig;
	private Connection conn = null;
	private PreparedStatement stat = null;
	private Properties consultas = new Properties();
	
	/**
	 * Constructor of AnuncioTematicoDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */	
	public AnuncioTematicoInteresesDAO(String sqlConfig) {
		this.sqlConfig = sqlConfig;
		
	}

	/**
	 * Saves the interests of a thematic announcement into the data base
	 * @param anuncio. A thematic announcement with the id and interests wanted to be saved
	 * @throws DAOException
	 */
	public void insertAnuncioTematicoIntereses(AnuncioTematico anuncio) throws DAOException {

		String intereses =  GestorContactos.getGestor().interesesToString(anuncio.getIntereses());
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioTematicoIntereses"));
			stat.setInt(1,anuncio.getId());
			stat.setString(2, intereses);
			
			if(stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha podido insertar correctamente");
			}
		
		}catch(SQLException ex) {
			
			throw new DAOException("Error al insertar",ex);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			if(stat != null) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en insert",ex);
				}
			}
		}
	}

	/**
	 * Update the interests in the data base with the same id
	 * @param anuncio. A thematic announcement with new data in it
	 * @throws DAOException
	 */
	public void updateAnuncioTematicoIntereses(AnuncioTematico anuncio) throws DAOException {

		PreparedStatement stat = null;
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioTematicoIntereses"));
			stat.setInt(2,anuncio.getId());
			stat.setString(1, GestorContactos.getGestor().interesesToString(anuncio.getIntereses()));
			
			if(stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha podido actualizar correctamente");
			}
		
		}catch(SQLException ex) {
			
			throw new DAOException("Error al actualizar",ex);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			if(stat != null) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en update",ex);
				}
			}
		}
	}

	/**
	 * Remove the interest of the thematic announcement with the id referenced from the data base
	 * @param id. The id of the thematic announcement with the interest that will be removed
	 * @throws DAOException
	 */
	public void removeAnuncioTematicoIntereses(Integer id) throws DAOException {
		
		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioTematicoIntereses"));
			stat.setInt(1,id);
			
			if(stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha podido eliminar correctamente");
			}
		
		}catch(SQLException ex) {
			
			throw new DAOException("Error al eliminar",ex);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			if(stat != null) {
				
				try {
					
					stat.close();
					

				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar en remove",ex);
				}
			}
		}
	}
	
	/**
	 * Saves the info contained in rs into the real object.
	 * @param rs.
	 * @return AnuncioTematico. 
	 * @throws SQLException
	 */
	private AnuncioTematico conversion(ResultSet rs) throws SQLException{

		AnuncioTematico anuncio = new AnuncioTematico(rs.getInt("id"),null,null,null,GestorContactos.getGestor().stringToIntereses(rs.getString("intereses")));
		
		return anuncio;
		
	}
	
	/**
	 * Search in the data base for the interests with the id referenced
	 * @param id. The id of the thematic announcement with the interests wanted in it
	 * @return AnuncioTematico with all the interests wanted
	 * @throws DAOException
	 */
	public AnuncioTematico getAnuncioTematicoIntereses(Integer id) throws DAOException {
		
		ResultSet rs = null;
		AnuncioTematico anuncio = null;

		try {
			this.conn = getConnection(sqlConfig);
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));

			stat = conn.prepareStatement(consultas.getProperty("getAnuncioTematicoIntereses"));
			stat.setInt(1,id);
			rs = stat.executeQuery(); 
			if (rs.next()) {
				
				anuncio = conversion(rs);
			}
			
			else {
				
				throw new DAOException("No existe anuncio con ese id");
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
		
		
		return anuncio;
	}

}
