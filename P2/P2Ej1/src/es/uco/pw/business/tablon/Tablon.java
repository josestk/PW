package es.uco.pw.business.tablon;


import java.util.ArrayList;
import java.util.Date;
import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.Interes;
import es.uco.pw.business.contacto.Intereses;
import es.uco.pw.data.dao.AnuncioFlashDAO;
import es.uco.pw.data.dao.AnuncioGeneralDAO;
import es.uco.pw.data.dao.AnuncioIndividualizadoDAO;
import es.uco.pw.data.dao.AnuncioTematicoDAO;
import es.uco.pw.data.dao.DAOException;



/**
 * This class represents a Tablon  
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public class Tablon implements ITablon{
	
	/* Attributes */
	@SuppressWarnings("unused")
	private AnuncioFlash anuncioFlash;
	@SuppressWarnings("unused")
	private AnuncioGeneral anuncioGeneral;
	@SuppressWarnings("unused")
	private AnuncioIndividualizado anuncioIndividualizado;
	@SuppressWarnings("unused")
	private AnuncioTematico anuncioTematico;
	
	private String pathSql;
	private String pathConfig;

	public void setPaths(String pathSql, String pathConfig) {
		this.pathSql = pathSql;
		this.pathConfig = pathConfig;
		
	}

	/* Constructor */
	public Tablon(AnunciosFactory factory) {
		
		anuncioFlash = factory.createAnuncioFlash();
		anuncioGeneral = factory.createAnuncioGeneral();
		anuncioIndividualizado = factory.createAnuncioIndividualizado();
		anuncioTematico = factory.createAnuncioTematico();
		
	}
	

	/* Guardar anuncio */ 
	
	public void guardarAnuncioFlash(AnuncioFlash anuncioFlash) throws DAOException {

		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		anuncio.insert(anuncioFlash);		 
	}

	public void guardarAnuncioGeneral(AnuncioGeneral anuncioGeneral) throws DAOException {
		
		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		anuncio.insert(anuncioGeneral);
	}

	public void guardarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado) throws DAOException {
		
		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		anuncio.insert(anuncioIndividualizado);
	}

	public void guardarAnuncioTematico(AnuncioTematico anuncioTematico) throws DAOException {
		
		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		anuncio.insert(anuncioTematico);
		
	}
	

	/* Eliminar anuncio */ 
	
	public void eliminarAnuncioFlash(AnuncioFlash anuncioFlash) throws DAOException {
		
		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		anuncio.remove(anuncioFlash.getId());		 
	}

	public void eliminarAnuncioGeneral(AnuncioGeneral anuncioGeneral) throws DAOException {

		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		anuncio.remove(anuncioGeneral.getId());
	}

	public void eliminarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado) throws DAOException {
		
		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		anuncio.remove(anuncioIndividualizado.getId());
	}

	public void eliminarAnuncioTematico(AnuncioTematico anuncioTematico) throws DAOException {
		
		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		anuncio.remove(anuncioTematico.getId());
		
	}

	/* Publicar anuncio */ 
	
	public void publicarAnuncioFlash(AnuncioFlash anuncioFlash) throws DAOException {
		
		Date fecha = new Date();
		anuncioFlash.setFechaPublicacion(fecha);
		anuncioFlash.setFase(Fases.publicado);
		
		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		anuncio.update(anuncioFlash);		 
	}

	public void publicarAnuncioGeneral(AnuncioGeneral anuncioGeneral) throws DAOException {
		
		Date fecha = new Date();
		anuncioGeneral.setFechaPublicacion(fecha);
		anuncioGeneral.setFase(Fases.publicado);
		
		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		anuncio.update(anuncioGeneral);
	}

	public void publicarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado) throws DAOException {
		
		Date fecha = new Date();
		anuncioIndividualizado.setFechaPublicacion(fecha);
		anuncioIndividualizado.setFase(Fases.publicado);
		
		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		anuncio.update(anuncioIndividualizado);
	}

	public void publicarAnuncioTematico(AnuncioTematico anuncioTematico) throws DAOException {
		
		Date fecha = new Date();
		anuncioTematico.setFechaPublicacion(fecha);
		anuncioTematico.setFase(Fases.publicado);
		
		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		anuncio.update(anuncioTematico);
		
	}

	
	/* Buscar por id */
	
	public AnuncioFlash buscarAnuncioFlashPorId(Integer id){

		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		
		try {
			return anuncio.getObject(id);
		} catch (DAOException e) {
			return null;
		}
	}

	public AnuncioGeneral buscarAnuncioGeneralPorId(Integer id)  {

		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		
		try {
			 return anuncio.getObject(id);
		} catch (DAOException e) {
			return null;
		}
	}
	
	public AnuncioIndividualizado buscarAnuncioIndividualizadoPorId(Integer id){

		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		
		try {
			return anuncio.getObject(id);
		} catch (DAOException e) {
			return null;
		}
	}
	
	public AnuncioTematico buscarAnuncioTematicoPorId(Integer id) {

		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		
		try {
			return anuncio.getObject(id);
		} catch (DAOException e) {
			return null;
		}
	}
	
	/* Buscar por propietario */ 
	public ArrayList<AnuncioFlash> buscarAnunciosFlashPorPropietario(String email) throws DAOException {

		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		
		return anuncio.getByPropietario(email);
	}

	public ArrayList<AnuncioGeneral> buscarAnunciosGeneralPorPropietario(String email) throws DAOException {

		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		
		return anuncio.getByPropietario(email);
	}
	
	public ArrayList<AnuncioIndividualizado> buscarAnunciosIndividualizadoPorPropietario(String email) throws DAOException {

		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		
		return anuncio.getByPropietario(email);
	}
	
	public ArrayList<AnuncioTematico> buscarAnunciosTematicoPorPropietario(String email) throws DAOException {

		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		
		return anuncio.getByPropietario(email);
	}
	
	
	
	/* Buscar por fecha */
	
	public ArrayList<AnuncioFlash> buscarAnunciosFlashPorFecha(Date fecha) throws DAOException {

		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		
		java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
		return anuncio.getByFecha(fechaSQL);
	}

	public ArrayList<AnuncioGeneral> buscarAnunciosGeneralPorFecha(Date fecha) throws DAOException {

		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		
		java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
		return anuncio.getByFecha(fechaSQL);
	}
	
	public ArrayList<AnuncioIndividualizado> buscarAnunciosIndividualizadoPorFecha(Date fecha) throws DAOException {

		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		
		java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
		return anuncio.getByFecha(fechaSQL);
	}
	
	public ArrayList<AnuncioTematico> buscarAnunciosTematicoPorFecha(Date fecha) throws DAOException {

		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		
		java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
		return anuncio.getByFecha(fechaSQL);
	}
	
	/* Buscar por destinatario */
	
	public ArrayList<AnuncioFlash> buscarAnunciosFlashPorDestinatario(String email) throws DAOException {

		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		
		return anuncio.getByDestinatario(email);
	}

	public ArrayList<AnuncioGeneral> buscarAnunciosGeneralPorDestinatario(String email) throws DAOException {

		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		
		return anuncio.getByDestinatario(email);
	}

	public ArrayList<AnuncioIndividualizado> buscarAnunciosIndividualizadoPorDestinatario(String email) throws DAOException{
		
		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		
		return anuncio.getByDestinatario(email);
	}
	
	public ArrayList<AnuncioTematico> buscarAnunciosTematicoPorDestinatario(String email) throws DAOException {

		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		
		return anuncio.getByDestinatario(email);
	}
		
	public ArrayList<AnuncioTematico> buscarAnunciosPorIntereses(Intereses interes) throws DAOException{
		
		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		
		Interes a = new Interes();
		a.setInteres(interes.toString());
		
		return anuncio.getByIntereses(a);
	}


	/* Editar anuncio */
	
	public void editarAnuncioFlash(AnuncioFlash anuncioFlash, String campo, String nuevoDato,Date nuevaFecha) throws DAOException {

		if(campo.equalsIgnoreCase("titulo")) {
			
			anuncioFlash.setTitulo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("cuerpo")) {

			anuncioFlash.setCuerpo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("fecha inicio de publicacion")) {

			anuncioFlash.setFechaInicio(nuevaFecha);
		}
		else if(campo.equalsIgnoreCase("fecha final de publicacion")){

			anuncioFlash.setFechaFinal(nuevaFecha);
		}
		anuncioFlash.setFase(Fases.editado);
		
		AnuncioFlashDAO anuncio = new AnuncioFlashDAO(pathSql,pathConfig);
		anuncio.update(anuncioFlash);
	}

	public void editarAnuncioGeneral(AnuncioGeneral anuncioGeneral, String campo, String nuevoDato) throws DAOException {
		
		if(campo.equalsIgnoreCase("titulo")) {

			anuncioGeneral.setTitulo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("cuerpo")) {

			anuncioGeneral.setCuerpo(nuevoDato);
		}
		anuncioGeneral.setFase(Fases.editado);
		
		AnuncioGeneralDAO anuncio = new AnuncioGeneralDAO(pathSql,pathConfig);
		anuncio.update(anuncioGeneral);
	}

	public void editarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado, String campo, String nuevoDato) throws DAOException {
		
		if(campo.equalsIgnoreCase("titulo")) {

			anuncioIndividualizado.setTitulo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("cuerpo")) {

			anuncioIndividualizado.setCuerpo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("destinatarios")) {
			
			anuncioIndividualizado.setDestinatarios(nuevoDato);
			
		}
		anuncioIndividualizado.setFase(Fases.editado);
		
		AnuncioIndividualizadoDAO anuncio = new AnuncioIndividualizadoDAO(pathSql,pathConfig);
		anuncio.update(anuncioIndividualizado);
	}

	public void editarAnuncioTematico(AnuncioTematico anuncioTematico, String campo, String nuevoDato,ArrayList<Intereses> nuevosIntereses) throws DAOException {
		
		if(campo.equalsIgnoreCase("titulo")) {

			anuncioTematico.setTitulo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("cuerpo")) {

			anuncioTematico.setCuerpo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("temas de interes")){
			
			if(!nuevosIntereses.isEmpty()) {

				anuncioTematico.setIntereses(nuevosIntereses);
			}
		}
		anuncioTematico.setFase(Fases.editado);

		AnuncioTematicoDAO anuncio = new AnuncioTematicoDAO(pathSql,pathConfig);
		anuncio.update(anuncioTematico);
		
	}

	public ArrayList<String> tablonUsuarioToString(Contacto usuario) throws DAOException{
		
		ArrayList<String> tablonUsuario = new ArrayList<String>();
		
		for(AnuncioFlash i: buscarAnunciosFlashPorDestinatario(usuario.getEmail())) {
			tablonUsuario.add(i.toString());
		}
		for(AnuncioGeneral i: buscarAnunciosGeneralPorDestinatario(usuario.getEmail())) {
			tablonUsuario.add(i.toString());
		}
		for(AnuncioIndividualizado i: buscarAnunciosIndividualizadoPorDestinatario(usuario.getEmail())) {
			tablonUsuario.add(i.toString());
		}
		
		for(Intereses i: usuario.getIntereses()) {
			for(AnuncioTematico j: buscarAnunciosPorIntereses(i)) {
				tablonUsuario.add(j.toString());
			}
		}
		
		return tablonUsuario;
	}
	
	
	
}
