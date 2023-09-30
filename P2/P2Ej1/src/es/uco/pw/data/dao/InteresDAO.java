package es.uco.pw.data.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.business.contacto.Interes;
import es.uco.pw.business.contacto.Intereses;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.dao.common.DAO;


public class InteresDAO extends DAO<Intereses,Integer>{
	
	private String pathSql;
	private String pathConfig;
	private Properties consultas = new Properties();
	private PreparedStatement stat = null;
	private Connection conn = null;
	
	public InteresDAO(String pathSql,String pathConfig){
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
	}
	/**
	 * Saves in the interes table an interes
	 * @param an interes
	 */
	@Override
	public void insert(Intereses a) throws DAOException {
	
		 try {
				this.conn = getConnection(pathConfig);
				consultas.load(new FileInputStream(new File(pathSql)));
				stat = conn.prepareStatement(consultas.getProperty("insertInteres"));
			
				
				stat.setString(1, a.toString());
				
			
				if (stat.executeUpdate() == 0) {
				
					throw new DAOException("Fallo en la insercion");
				}
			}catch(SQLException ex) {
			
				throw new DAOException("Fallo en insertar interes", ex);
			}catch(Exception e){
				e.printStackTrace();
				}finally {
			
				if(stat != null) {
				
					try {
					
						stat.close();
					
					}catch(SQLException ex) {
					
						throw new DAOException("Fallo al comunicarse con la base datos", ex);
					}
				}
			}
	}

	
	@Override
	public void update(Intereses a) throws DAOException {

	}

	
	@Override
	public void remove(Integer a) throws DAOException {
		
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("deleteInteres"));
			
			stat.setInt(1, a);
			
		} catch (SQLException e) {
			
			throw new DAOException("ERROR SQL, e");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
			if(stat != null) {

				try {
					
					stat.close();
					
				}catch(SQLException e){
					
					throw new DAOException("ERROR SQL, e");
				}
			}
		}
	}

	@Override
	public Intereses getObject(Integer a) throws DAOException {

		ResultSet rs = null;
		Interes interes = null;
		try {
			this.conn = getConnection(pathConfig);
			consultas.load(new FileInputStream(new File(pathSql)));
			stat = conn.prepareStatement(consultas.getProperty("getInteresById"));
			stat.setInt(1, a);
			rs = stat.executeQuery();
			
			if(rs.next()) {
				interes = new Interes();
				interes.setId(a);
				interes.setInteres(rs.getString("INTERES"));
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(stat != null && rs != null) {

				try {
					rs.close();
					stat.close();
					
				}catch(SQLException e){
					
					throw new DAOException("ERROR SQL, e");
				}
			}
		}
		
		
		return Intereses.valueOf(interes.getInteres());
	}

}



