package es.uco.pw.business.contacto;

import java.util.ArrayList;

public interface IGestorContactos {

	/**
	 * Add a contact to contacts list.
	 * @param contacto. A contact.
	 */	
	public void DarDeAlta(Contacto contacto);
	
	/**
	 * Remove the contact with same email from the contacts list.
	 * @param email. The email of the contact.
	 */
	public void DarDeBaja(String email);
	
	/**
	 * Change a field from a contact through its email
	 * @campo  the field that it want to be modified
	 * @email  email of the person that it is going to be modified
	 * @nuevoDato the information that we want to add or replace it
	 * @return true if it has been modified or false if it have not been modified
	 */
	public void ActualizarContacto(String campo, String email, String nuevoDato);
	
	/**
	 * Update the contact with same email
	 * @param contacto. An updated contact.
	 * @return true if the contact was updated.
	 */
	public void ActualizarContacto(Contacto contacto);
	
	/**
	 * Check for the contact who has the field and identification received
	 * @param campo the field that it has been checking
	 * @param identificador parameter to identify the contact or contacts 
	 * @return An ArrayList<Contacto> with all the contacts found.
	 */
	public ArrayList<Contacto> BuscarContacto(String campo, String identificador);

	
	/* Auxiliary methods */
	
	/**
	 * Gives a string with the email of all contacts in arraylist contactos.
	 * @param contactos
	 * @return string with the contactos contained in the arrayList.
	 */
	public String emailsToString(ArrayList<Contacto> contactos);
	
	/**
	 * Creates contacts with the email contained in the string 
	 * @param emailsString
	 * @return contactos. An arrayList of contacts
	 */
	public ArrayList<Contacto> emailsToContacto(String emailsString);
	/**
	 * Convert a string with intereses into an arraylist of objects Intereses.
	 * @param stringIntereses
	 * @return ArrayList with the values of intereses contained in stringIntereses.
	 */
	public ArrayList<Intereses> stringToIntereses(String stringIntereses);
	
	/**
	 * Convert an arraylist of intereses values into string.
	 * @param arrayIntereses
	 * @return string with values contained in arrayIntereses.
	 */
	public String interesesToString(ArrayList<Intereses> arrayIntereses);
}
