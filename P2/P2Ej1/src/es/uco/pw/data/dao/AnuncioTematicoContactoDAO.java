package es.uco.pw.data.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.business.tablon.AnuncioTematico;
import es.uco.pw.data.dao.common.DAO;

public  class AnuncioTematicoContactoDAO extends DAO <AnuncioTematico, Integer>{

	private String pathSql;
	private String pathConfig;
	
	private Connection conn = null;
	private PreparedStatement stat = null;
	private Properties consultas = new Properties();
	
	public AnuncioTematicoContactoDAO(String pathSql, String pathConfig) {
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
	}

	@Override
	public void insert(AnuncioTematico a) throws DAOException {

		try {
			consultas.load(new FileInputStream(new File(this.pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioTematicoContacto"));
			stat.setInt(1, a.getId());
			stat.setString(2, a.getDestinatarios());
			
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

	@Override
	public void update(AnuncioTematico a) throws DAOException {
		
		try {
			consultas.load(new FileInputStream(new File(this.pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioTematicoContacto"));
			stat.setInt(2, a.getId());
			stat.setString(1, a.getDestinatarios());
			
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

	
	@Override
	public void remove(Integer a) throws DAOException {
		
		try {
			consultas.load(new FileInputStream(new File(this.pathSql)));
			this.conn = getConnection(pathConfig);
			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioTematicoContacto"));
			stat.setInt(1, a);
			
			
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
	public ArrayList<Contacto> getDestinatariosAnuncioTematico(Integer a) throws DAOException {
		
		ArrayList<Contacto> destinatarios = new ArrayList<Contacto>();
		ResultSet rs = null;

		try {
			consultas.load(new FileInputStream(new File(this.pathSql)));
			this.conn = getConnection(pathConfig);
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
	
	@Override
	public AnuncioTematico getObject(Integer a) throws DAOException {

	return null;
	}
}

	
