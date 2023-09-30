package es.uco.pw.business.tablon;


import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.Intereses;
import es.uco.pw.data.dao.DAOException;


/**
 * An interface that implements all the methods 
 * @author Carlos Revuelto
 * @author Jose Antonio
 */
public interface ITablon {
	
	/**
	 * Saves an advertisement into the data base
	 * @param anuncioFlash Flash type advertisement
	 * @throws DAOException
	 */
	public void guardarAnuncioFlash(AnuncioFlash anuncioFlash) throws DAOException;
	
	/**
	 * Saves an advertisement into the data base
	 * @param anuncioGeneral General type advertisement
	 * @throws DAOException
	 */
	public void guardarAnuncioGeneral(AnuncioGeneral anuncioGeneral) throws DAOException;	
	
	/**
	 * Saves an advertisement into the data base
	 * @param anuncioIndividualizado Individual type advertisement
	 * @throws DAOException
	 */
	public void guardarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado) throws DAOException;
	
	/**
	 * Saves an advertisement into the data base
	 * @param anuncioTematico Individual type advertisement
	 * @throws DAOException
	 */
	public void guardarAnuncioTematico(AnuncioTematico anuncioTematico) throws DAOException;
	
	
	/**
	 * Search in the data base an advertisement with the id referenced.
	 * @param id The id of the advertisement wanted.
	 * @return an AnuncioFlash advertisement 
	 */
	public AnuncioFlash buscarAnuncioFlashPorId(Integer id);
	
	/**
	 * Search in the data base an advertisement with the id referenced.
	 * @param id The id of the advertisement wanted.
	 * @return an AnuncioGeneral advertisement 
	 */
	public AnuncioGeneral buscarAnuncioGeneralPorId(Integer id);
	
	/**
	 * Search in the data base an advertisement with the id referenced.
	 * @param id The id of the advertisement wanted.
	 * @return an AnuncioIndividualizado advertisement 
	 */
	public AnuncioIndividualizado buscarAnuncioIndividualizadoPorId(Integer id);
	
	/**
	 * Search in the data base an advertisement with the id referenced.
	 * @param id The id of the advertisement wanted.
	 * @return an AnuncioTematico advertisement 
	 */
	public AnuncioTematico buscarAnuncioTematicoPorId(Integer id);
	
	
	/**
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioFlash> buscarAnunciosFlashPorPropietario(String email) throws DAOException;
	
	/**
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioGeneral> buscarAnunciosGeneralPorPropietario(String email) throws DAOException;
	
	/**
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioIndividualizado> buscarAnunciosIndividualizadoPorPropietario(String email) throws DAOException;
	
	/**
	 * Search in the data base an advertisement with the owner's email referenced.
	 * @param email The owner’s email.
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioTematico> buscarAnunciosTematicoPorPropietario(String email) throws DAOException;
	
	
	/**
	 * Search in the data base an advertisement published before the date referenced.
	 * @param fecha A date.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioFlash> buscarAnunciosFlashPorFecha(Date fecha) throws DAOException;
	
	/**
	 * Search in the data base an advertisement published before the date referenced.
	 * @param fecha A date.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioGeneral> buscarAnunciosGeneralPorFecha(Date fecha) throws DAOException;
	
	/**
	 * Search in the data base an advertisement published before the date referenced.
	 * @param fecha A date.
	 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioIndividualizado> buscarAnunciosIndividualizadoPorFecha(Date fecha) throws DAOException;
	
	/**
	 * Search in the data base an advertisement published before the date referenced.
	 * @param fecha A date.
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException
	 */
	public ArrayList<AnuncioTematico> buscarAnunciosTematicoPorFecha(Date fecha) throws DAOException;
	
	
	/**
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioFlash> with all the advertisements found.
	 */
	public ArrayList<AnuncioFlash> buscarAnunciosFlashPorDestinatario(String email) throws DAOException;
	
	/**
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioGeneral> with all the advertisements found.
	 */
	public ArrayList<AnuncioGeneral> buscarAnunciosGeneralPorDestinatario(String email) throws DAOException ;
	
	/**
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioIndividualizado> with all the advertisements found.
	 */
	public ArrayList<AnuncioIndividualizado> buscarAnunciosIndividualizadoPorDestinatario(String email) throws DAOException;
	
	/**
	 * Search all advertisements with "email" in "destinatarios".
	 * @param email The email to search.
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 */
	public ArrayList<AnuncioTematico> buscarAnunciosTematicoPorDestinatario(String email) throws DAOException;

	
	
	/**
	 * Search all advertisements with common "interes"
	 * @param interes The interes to search. 
	 * @return ArrayList<AnuncioTematico> with all the advertisements found.
	 * @throws DAOException 
	 */
	public ArrayList<AnuncioTematico> buscarAnunciosPorIntereses(Intereses interes) throws DAOException;
	
	
	
	/**
	 * Change the status of anuncioFlash the from "editado" to "publicado"
	 * @param anuncioFlash A Flash type advertisement.
	 * @throws DAOException 
	 */
	public void publicarAnuncioFlash(AnuncioFlash anuncioFlash) throws DAOException;
	
	/**
	 * Change the status of anuncioGeneral the from "editado" to "publicado"
	 * @param anuncioGeneral A General type advertisement.
	 */
	public void publicarAnuncioGeneral(AnuncioGeneral anuncioGeneral) throws DAOException;
	
	/**
	 * Change the status of anuncioIndividualizado from "editado" to "publicado"
	 * @param anuncioIndividualizado A Individual type advertisement.
	 * @throws DAOException 
	 */
	public void publicarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado) throws DAOException;
	
	/**
	 * Change the status of anuncioTematico from "editado" to "publicado"
	 * @param anuncioTematico A tematic type advertisement.
	 * @throws DAOException 
	 */
	public void publicarAnuncioTematico(AnuncioTematico anuncioTematico) throws DAOException;

	
	/**
	 * Change a field of anuncioFlash referenced by "campo" with new values 
	 * @param anuncioFlash A Flash type advertisement
	 * @param campo A String with the field to change.
	 * @param nuevoDato A String with the new value.
	 * @param nuevaFecha A Date with new value.
	 * @throws DAOException
	 */
	public void editarAnuncioFlash(AnuncioFlash anuncioFlash, String campo, String nuevoDato,Date nuevaFecha) throws DAOException;
	
	/**
	 * Change a field of anuncioGeneral referenced by "campo" with new values 
	 * @param anuncioGeneral A General type advertisement.
	 * @param campo A String with the field to change.
	 * @param nuevoDato A String with the new value.
	 * @throws DAOException
	 */
	public void editarAnuncioGeneral(AnuncioGeneral anuncioGeneral, String campo, String nuevoDato) throws DAOException;
	
	/**
	 * Change a field of anuncioIndividualizado referenced by "campo" with new values 
	 * @param anuncioIndividualizado A Individual type advertisement.
	 * @param campo A String with the field to change.
	 * @param nuevoDato A String with the new value.
	 * @throws DAOException
	 */
	public void editarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado, String campo, String nuevoDato) throws DAOException;

	/**
	 * Change a field of anuncioTematico referenced by "campo" with new values 
	 * @param anuncioTematico A Tematic type advertisement.
	 * @param campo A String with the field to change.
	 * @param nuevoDato A String with the new value.
	 * @param nuevosIntereses A ArrayList<Intereses> with new values to change.
	 * @throws DAOException
	 */
	public void editarAnuncioTematico(AnuncioTematico anuncioTematico, String campo, String nuevoDato,ArrayList<Intereses> nuevosIntereses) throws DAOException;

	
	/**
	 * Converts to String all the advertisement addressed to "usuario"
	 * @param usuario A contact.
	 * @throws DAOException 
	 */
	public ArrayList<String> tablonUsuarioToString(Contacto usuario) throws DAOException;

}
