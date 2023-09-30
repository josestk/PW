package es.uco.pw.data.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.business.tablon.AnuncioTematico;
import es.uco.pw.data.dao.common.DAO;

public class AnuncioTematicoInteresesDAO extends DAO<AnuncioTematico, Integer>{

	private String pathSql;
	private String pathConfig;
	private Connection conn = null;
	private PreparedStatement stat = null;
	private Properties consultas = new Properties();
	
	public AnuncioTematicoInteresesDAO(String pathSql,String pathConfig) {
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
		
	}

	@Override
	public void insert(AnuncioTematico a) throws DAOException {

		String intereses =  GestorContactos.getGestor().interesesToString(a.getIntereses());
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("insertAnuncioTematicoIntereses"));
			stat.setInt(1,a.getId());
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

	@Override
	public void update(AnuncioTematico a) throws DAOException {

		PreparedStatement stat = null;
		Properties consultas = new Properties();
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("updateAnuncioTematicoIntereses"));
			stat.setInt(2,a.getId());
			stat.setString(1, GestorContactos.getGestor().interesesToString(a.getIntereses()));
			
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

	@Override
	public void remove(Integer a) throws DAOException {
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("deleteAnuncioTematicoIntereses"));
			stat.setInt(1,a);
			
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
	
	@Override
	public AnuncioTematico getObject(Integer a) throws DAOException {
		ResultSet rs = null;
		AnuncioTematico anuncio = null;

		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));

			stat = conn.prepareStatement(consultas.getProperty("getAnuncioTematicoIntereses"));
			stat.setInt(1,a);
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
