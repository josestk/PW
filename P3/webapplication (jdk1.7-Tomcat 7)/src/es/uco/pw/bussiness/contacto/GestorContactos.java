package es.uco.pw.bussiness.contacto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.data.common.DAOException;
import es.uco.pw.data.dao.contacto.ContactoDAO;



public class GestorContactos implements IGestorContactos{
	
	/* 1 - One unique instance */
	private static GestorContactos instance = null;
	
	/* Private constructor */
	private GestorContactos(){
		
	}

	/* Access point to the instance */
	public static GestorContactos getGestor() {
		
		if (instance == null) {	
			instance = new GestorContactos();
		}
		return instance;
	}

	private String sqlConfig;
	
	/* Methods */
	
	public void setPaths(String sqlConfig) {

		this.sqlConfig = sqlConfig;
	}

	public void DarDeAlta(Contacto contacto){
		
		ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
		try {
			contactoDao.insertContacto(contacto);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void DarDeBaja(String email){
		
		ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
		try {
			contactoDao.removeContacto(email);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	public void ActualizarContacto(String campo, String email, String nuevoDato){	
		
		if(campo.equalsIgnoreCase("nombre")) {
			
			try {

			ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
			Contacto contacto = contactoDao.getContacto(email);
			contacto.setNombre(nuevoDato);
				contactoDao.updateContacto(contacto);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(campo.equalsIgnoreCase("apellidos")) {
			
			try {
				
			ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
			Contacto contacto = contactoDao.getContacto(email);
			contacto.setApellidos(nuevoDato);
			
				contactoDao.updateContacto(contacto);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(campo.equalsIgnoreCase("fecha de nacimiento")){
			
			try {
				
			ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
			Contacto contacto = contactoDao.getContacto(email);
			
			SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
			Date nuevaFechaNacimiento = new Date(0);
			nuevaFechaNacimiento = fechaAux.parse(nuevoDato);
			contacto.setFechaNacimiento(nuevaFechaNacimiento);
			
			
				contactoDao.updateContacto(contacto);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(campo.equalsIgnoreCase("intereses")){
			
			try {
				
			ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
			Contacto contacto = contactoDao.getContacto(email);
			contacto.setIntereses(this.stringToIntereses(nuevoDato));
			contactoDao.updateContacto(contacto);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	

	public void ActualizarContacto(Contacto contacto) {
		
		try {
			
			ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
			contactoDao.updateContacto(contacto);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public ArrayList<Contacto> BuscarContacto(String campo, String identificador){
		
		ArrayList<Contacto> encontrados = new ArrayList<Contacto>();
		
			if(campo.equalsIgnoreCase("email")) {
				
				try {
					
					ContactoDAO contactoDao = new ContactoDAO(sqlConfig);
					
					Contacto contacto = contactoDao.getContacto(identificador);
					if(contacto.getEmail().equalsIgnoreCase(identificador)) {
						encontrados.add(contacto);
					}
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
				

		return encontrados;
	}
	

	
	
	public String emailsToString(ArrayList<Contacto> contactos) {
		
		 String info = new String();

		 if(!contactos.isEmpty()) {
			 
			 for(int i = 0; i < contactos.size(); i++) {
				 
				 info = contactos.get(i).getEmail();
			 }
		 }

	return info;
	}
	
	public ArrayList<Contacto> emailsToContacto(String emailsString){
		
		ArrayList<Contacto> contactos = new ArrayList<Contacto>();
		Contacto contacto = new Contacto();
		String[] stringParts = emailsString.split(",");
		
		for(int i = 0; i < stringParts.length; i++) {
			contacto.setEmail(stringParts[i]);
			contactos.add(contacto);			
		}
	
		return contactos;
	}
	

	public String interesesToString(ArrayList<Intereses> arrayIntereses) {
		
		String intereses = "VACIO";
		if(!arrayIntereses.isEmpty()) {
			intereses = arrayIntereses.get(0).toString();
			
			for(int i = 1; i < arrayIntereses.size(); i++) {
				intereses = intereses + "," +arrayIntereses.get(i).toString();
			}
		}
		return intereses;
	}
	

	public ArrayList<Intereses> stringToIntereses(String stringIntereses) {
		
		ArrayList<Intereses> intereses = new ArrayList<Intereses>();
		String[] stringParts = stringIntereses.split(",");
		
		for(int i = 0; i < stringParts.length; i++) {
			intereses.add(Intereses.valueOf(stringParts[i]));			
		}
	
		return intereses;
	}
}
	
