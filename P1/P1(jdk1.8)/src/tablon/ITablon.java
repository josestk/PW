package tablon;


import java.util.ArrayList;
import java.util.Date;

import contacto.Contacto;
import contacto.Intereses;

/**
 * An interface that implements all the methods 
 * @author Carlos Revuelto
 * @author Jose Antonio
 */
public interface ITablon {
	
	/**
	 * Search all advertisements of "contacto"  
	 * @param contacto
	 * @return ArrayList<String> with all the info of the "contactos" found
	 */
	public ArrayList<String> buscarAnuncioPropietario(Contacto contacto); 
	
	
	/**
	 * Search all advertisements with a date less than "fecha"
	 * @param fecha
	 * @return ArrayList<String> with all the info of the "contactos" found
	 */
	public ArrayList<String> buscarAnuncioFecha(Date fecha); //Fecha publicación en todos menos en flash que podrá ser también inicio y fin
	
	
	/**
	 * Search all advertisements with common "destinatarios"
	 * @param destinatarios
	 * @return ArrayList<String> with all the info of the "contactos" found
	 */
	public ArrayList<String> buscarAnuncioDestinatarios(ArrayList<Contacto> destinatarios); //Por destintarios
	
	
	/**
	 * Search all advertisements with common "intereses"
	 * @param intereses
	 * @return ArrayList<String> with all the info of the "contactos" found
	 */
	public ArrayList<String> buscarAnuncioIntereses(ArrayList<Intereses> intereses);
	
	
	/**
	 * Search the advertisement with the same id
	 * @param id
	 * @return AnuncioTematico found
	 */
	public AnuncioTematico buscarAnuncioIdTematico(int id);
	
	
	/**
	 * Search the advertisement with the same id
	 * @param id
	 * @return AnucioGeneral found
	 */
	public AnuncioGeneral buscarAnuncioIdGeneral(int id);
	
	
	/**
	 * Search the advertisement with the same id
	 * @param id
	 * @return AnuncioIndividualizado found
	 */
	public AnuncioIndividualizado buscarAnuncioIdIndividualizado(int id);
	
	
	/**
	 * Search the advertisement with the same id
	 * @param id
	 * @return AnuncioFlash found
	 */
	public AnuncioFlash buscarAnuncioIdFlash(int id);
	
	
	/**
	 * Change the status of anuncioTematico from "editado" to "publicado"
	 * @param anuncioTematico
	 */
	public void publicarAnuncioTematico(AnuncioTematico anuncioTematico);
	
	
	/**
	 * Change the status of anuncioIndividualizado from "editado" to "publicado"
	 * @param anuncioIndividualizado
	 */
	public void publicarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado);
	
	
	/**
	 * Change the status of anuncioGeneral the from "editado" to "publicado"
	 * @param anuncioGeneral
	 */
	public void publicarAnuncioGeneral(AnuncioGeneral anuncioGeneral);
	
	
	/**
	 * Change the status of anuncioFlash the from "editado" to "publicado"
	 * @param anuncioFlash
	 */
	public void publicarAnuncioFlash(AnuncioFlash anuncioFlash);
	
	
	/**
	 * Change a field of anuncioTematico referenced by "campo" with new values 
	 * @param anuncioTematico
	 * @param campo
	 * @param nuevoDato
	 * @param nuevosDestinatarios
	 * @param nuevosIntereses
	 */
	public void editarAnuncio(AnuncioTematico anuncioTematico, String campo, String nuevoDato,ArrayList<Intereses> nuevosIntereses);
	
	
	/**
	 * Change a field of anuncioGeneral referenced by "campo" with new values
	 * @param anuncioGeneral
	 * @param campo
	 * @param nuevoDato
	 */
	public void editarAnuncio(AnuncioGeneral anuncioGeneral, String campo, String nuevoDato);
	
	
	/**
	 * Change a field of anuncioIndividualizado referenced by "campo" with new values
	 * @param anuncioIndividualizado
	 * @param campo
	 * @param nuevoDato
	 * @param nuevosDestinatarios
	 */
	public void editarAnuncio(AnuncioIndividualizado anuncioIndividualizado, String campo, String nuevoDato,ArrayList<Contacto> nuevosDestinatarios);
	
	
	/**
	 * Change a field of anuncioFlash referenced by "campo" with new values
	 * @param anuncioFlash
	 * @param campo
	 * @param nuevoDato
	 * @param nuevaFecha
	 */
	public void editarAnuncio(AnuncioFlash anuncioFlash, String campo, String nuevoDato,Date nuevaFecha);

	
	/**
	 * Print all the advertisements passed
	 * @param tablonusuario
	 */
	public void LeerTablon(ArrayList<String> tablonusuario);
	
	
	/**
	 * Creates static advertisements 
	 */
	public void crearAnuncios();
}
