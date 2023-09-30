package es.uco.pw.data.dao.anuncios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.bussiness.contacto.GestorContactos;
import es.uco.pw.bussiness.anuncios.*;
import es.uco.pw.data.common.DAO;
import es.uco.pw.data.common.DAOException;

/**
 * DAO class for AnuncioTematicoContactoDAO table
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 */
public  class AnuncioTematicoContactoDAO extends DAO{

	private String sqlConfig;
	
	private Connection conn = null;
	private PreparedStatement stat = null;
	private Properties consultas = new Properties();
	
	/**
	 * Constructor of AnuncioTematicoDAO
	 * @param sqlConfig. String with all the SQL configuration divided by comma
	 */
	public AnuncioTematicoContactoDAO(String sqlConfig) {
		this.sqlConfig = sqlConfig;
	}

	/**
	 * Saves the contact of a thematic announcement into the data base
	 * @param AnuncioTematico. A thematic announcement with the id and interests wanted to be saved
	 * @throws DAOException
	 */
	public void insertAnuncioTematicoContacto(AnuncioTematico anuncio) throws DAOException {

		try {
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioTematicoContacto"));
			stat.setInt(1, anuncio.getId());
			stat.setString(2, anuncio.getDestinatarios());
			
			if(stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha podido insertar");
			}
		}catch(SQLException ex) {
			
			throw new DAOException("Error al insertar", ex);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if(stat != null) {
				
				try {
					
					stat.close();
					
					
				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar el insert", ex);
				}
			}
		}
		
	}

	/**
	 * Update the contacts in the data base with the same id
	 * @param anuncio. A thematic announcement with new data in it
	 * @throws DAOException
	 */
	public void updateAnuncioTematicoContacto(AnuncioTematico anuncio) throws DAOException {
		
		try {
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioTematicoContacto"));
			stat.setInt(2, anuncio.getId());
			stat.setString(1, anuncio.getDestinatarios());
			
			if(stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha podido actualizar");
			}
		}catch(SQLException ex) {
			
			throw new DAOException("Error al actualizar", ex);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if(stat != null) {
				
				try {
					
					stat.close();
					
					
				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar el update", ex);
				}
			}
		}
	}

	
	/**
	 * Remove the contacts of the thematic announcement with the id referenced from the data base
	 * @param id. The id of the thematic announcement with the interest that will be removed
	 * @throws DAOException
	 */
	public void removeAnuncioTematicoContacto(Integer id) throws DAOException {
		
		try {
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioTematicoContacto"));
			stat.setInt(1, id);
			
			
			if(stat.executeUpdate() == 0) {
				
				throw new DAOException("No se ha podido eliminar");
			}
		}catch(SQLException ex) {
			
			throw new DAOException("Error al eliminar", ex);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			if(stat != null) {
				
				try {
					
					stat.close();
					
					
				}catch(SQLException ex) {
					
					throw new DAOException("Error al cerrar el remove", ex);
				}
			}
		}
	}
	
	/**
	 * Return all the recipients from an advertisement from the data base
	 * @param a
	 * @return ArrayList<Contacto>
	 * @throws DAOException
	 */
	public ArrayList<Contacto> getDestinatariosAnuncioTematicoContacto(Integer a) throws DAOException {
		
		ArrayList<Contacto> destinatarios = new ArrayList<Contacto>();
		ResultSet rs = null;

		try {
			consultas.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
			this.conn = getConnection(sqlConfig);
			stat = conn.prepareStatement(consultas.getProperty("getAnuncioTematicoDestinatarios"));
			stat.setInt(1,a);
			rs = stat.executeQuery();
			
			if (rs.next()) {
				
				destinatarios = GestorContactos.getGestor().emailsToContacto(rs.getString("destinatarios"));
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
		
		return destinatarios;
	}
	

}

	
