package contacto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class represent an object Contact
 * @author Carlos Revuelto Quero
 * @author Jose Antonio Gonzalez Aguilera
 */
public class Contacto {
	
	
	/* Instance */
	private static Contacto instance = null;
	
	
	/* Attributes */
	private String email;
	private String nombre;
	private String apellidos;
	private ArrayList<Intereses> intereses = new ArrayList<Intereses>();
	private int edad;
	private Date fechaNacimiento;
	private static ArrayList<Contacto> Gestor = new ArrayList<Contacto>();
	
	protected static String path = new String();
	/* Private Constructor */
	private Contacto() {
		
		this.email = " ";
		this.nombre = " ";
		this.apellidos = " ";
		this.edad = 0;
		this.fechaNacimiento = null;

	}
	
	
	/* Access point to the instance */
	public static Contacto getContacto() {
		
		if (instance == null) {	
			instance = new Contacto();
		}
		return instance;
		
	}
	
	
	/**
	 * Auxiliary instances 
	 */
	Contacto contAux = null;
	Scanner entrada = new Scanner(System.in);
	
	/* Getters and setters */
	
	public String getEmail(){
		
		return email;
		
	}

	
	public void setEmail(String email){
		
		this.email = email;
		
	}

	
	public String getNombre() {
		
		return nombre;
		
	}

	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}

	
	public String getApellidos() {
		
		return apellidos;
		
	}

	
	public void setApellidos(String apellidos) {
		
		this.apellidos = apellidos;
		
	}

	
	public ArrayList<Intereses> getIntereses() {
		
		return intereses;
		
	}
	

	public void setIntereses(String stringIntereses){
		
		String[] stringInteresesParts = stringIntereses.split(",");
		
		for(int i = 0; i < stringInteresesParts.length; i++){
			
			if(!stringInteresesParts[i].isEmpty()){
				
				this.setIntereses(Intereses.valueOf(stringInteresesParts[i]));
				
			}
			else{	
				
				this.setIntereses(Intereses.VACIO);	
				
			}	
		}		
	}
	
	
	public void setIntereses(Intereses interes) {
		
		this.intereses.add(interes);
		
	}

	
	public int getEdad() {
		
		return edad;
		
	}

	
	public void setEdad(int edad) {
		
		this.edad = edad;
		
	}

	
	public Date getFechaNacimiento() {
		
		return fechaNacimiento;
		
	}

	
	public void setFechaNacimiento(Date fechaNacimiento) {
		
		this.fechaNacimiento = fechaNacimiento;
		CalcularEdad();
		
	}

	public static String getPath() {
		return path;
	}


	public static void setPath(String path) {
		Contacto.path = path;
	}


	/**
	 * 
	 * @return ArrayList<Contacto> with all the "Contactos" in the data file
	 */
	public static ArrayList<Contacto> getContactos(){
		
		fileToArray(path);
		ArrayList<Contacto> aux = Gestor;
		return aux;
		
	}
	
	/* Other methods */
	
	/**
	 *	
	 * @return string with the info of the contact
	 */
	public String ImprimirContacto() {
		
		String Info =" -Email: "+this.email + "   -Nombre: "+this.nombre+"   -Apellidos: "+this.apellidos+"   -FechaNacimiento: "+this.fechaNacimiento+ "   -Edad: " + this.edad + "   -Intereses: "+this.intereses.toString();
		return Info;
		
	}
	
	
	/**
	 * Load the data file to the array "Gestor"
	 */
	public static void fileToArray(String path) {
		
		setPath(path);
		
		Properties pFile  = new Properties();
		InputStream input = null;
		
		SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaAux2 = new Date();
		
		try {
			
			input = new FileInputStream(path);
			pFile.load(input);
					
			String emails  = pFile.getProperty("emails");
			String nombres = pFile.getProperty("nombres");
			String apellidos = pFile.getProperty("apellidos");
			String interesesAux = pFile.getProperty("intereses");
			String fechas = pFile.getProperty("fechasNacimiento");
			
			String[] emailParts = emails.split(",");
			String[] nombresParts = nombres.split(",");		
			String[] apellidosParts = apellidos.split(",");
			String[] fechasParts = fechas.split(",");
			String[] interesesParts = interesesAux.split(",");			

			if (!emails.equals("")){
				
				for(int i = 0; i < emailParts.length; i++){
					 
					Contacto contAux = new Contacto();
						
					contAux.setNombre("VACIO");
					contAux.setEmail("VACIO");
					contAux.setApellidos("VACIO");
					contAux.setFechaNacimiento(fechaAux2);
					contAux.setIntereses(Intereses.VACIO);
		
					
					if(i <= emailParts.length-1){
						
						contAux.setEmail(emailParts[i]);
						
					}
					
					if(i <= nombresParts.length-1){
						
						contAux.setNombre(nombresParts[i]);
						
					}
					
					if(i <= apellidosParts.length-1){
						
						contAux.setApellidos(apellidosParts[i]);
						
					}
					
					if(i <= fechasParts.length-1){
						
						if(!fechasParts[i].equals("")) {
							fechaAux2 = fechaAux.parse(fechasParts[i]);
							contAux.setFechaNacimiento(fechaAux2);
						}
					}
					
					if(i <= interesesParts.length-1){
						
						String[] interesAux = interesesParts[i].split("-");		
						contAux.intereses.remove(0);
						
						for(int j = 0; j < interesAux.length; j++){
							
							contAux.setIntereses(interesAux[j]);
							
						}
						
						Gestor.add(contAux);
						
				}
			}	
		}
			
			pFile.store(new FileWriter(path), "FICHERO DE BASE DE DATOS");
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Update the data base file
	 */
	private void ArrayToFile(String path) {
		
		Properties pFile  = new Properties();
		InputStream input = null;
		
		SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");

		String emails = Gestor.get(0).getEmail() + ",";
		String nombres = Gestor.get(0).getNombre() + ",";
		String apellidos = Gestor.get(0).getApellidos() + ",";
		String fechas = fechaAux.format(Gestor.get(0).getFechaNacimiento()) + ",";
		String interesesAux = null;
		
		for(int i = 0; i < Gestor.get(0).getIntereses().size(); i++) {
			
			interesesAux = Gestor.get(0).getIntereses().get(i) + "-";
			
		}
		
		interesesAux = interesesAux + ",";

		for(int i = 1; i < Gestor.size(); i++){
			
			emails = emails + Gestor.get(i).getEmail() + ",";
			nombres = nombres + Gestor.get(i).getNombre() + ",";
			apellidos = apellidos + Gestor.get(i).getApellidos() + ",";
			fechas = fechas + fechaAux.format(Gestor.get(i).getFechaNacimiento()) + ",";
			
			for(int j = 0; j < Gestor.get(i).getIntereses().size(); j++){
				
				interesesAux = interesesAux + Gestor.get(i).getIntereses().get(j) + "-";
				
			}
			
			interesesAux = interesesAux + ",";
			
		}
		
		
		try {
			
			input = new FileInputStream(path);	
			pFile.load(input);
			
			pFile.setProperty("emails", emails);
			pFile.setProperty("nombres", nombres);
			pFile.setProperty("apellidos", apellidos);
			pFile.setProperty("fechasNacimiento", fechas);
			pFile.setProperty("intereses", interesesAux);

			pFile.store(new FileWriter(path), "FICHERO DE BASE DE DATOS");
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	/**
	 * Saves in the data base a contact
	 * @return false if there is any default value or true if can save it in the data base
	 */	
	public boolean DarDeAlta(){
		
		if(this.getEmail() == " "){
			
			return false;
			
		}
		
		else if(this.getNombre() == " "){
			
			return false;
			
		}
		
		else if(this.getApellidos() == ""){
			
			return false;
			
		}
		
		else if(this.getFechaNacimiento() == new Date()){
			
			return false;
			
		}
		
		else if(this.getIntereses().get(0).equals(Intereses.VACIO)){
			
			return false;
			
		}
		
		else {
			
			Gestor.add(this);
			ArrayToFile(path);
			return true;
			
		}
		
	}
	
	
	/**
	 * Remove a contact from the "Gestor"
	 * @param email
	 * @return eliminado
	 */
	public boolean DarDeBaja(String email){
		
		boolean eliminado = false;
		
		for(int i = 0; i < Gestor.size(); i++){
			
			if(Gestor.get(i).getEmail() == email){
				
				Gestor.remove(i);
				eliminado = true;
				
			}
			
		}
		
		ArrayToFile(path);	
		return eliminado;
		
	}

	
	/**
	 * Prints all the contacts in the data file
	 */
	public boolean ImprimirContactos() {
		
		if(Gestor.isEmpty() == true) {
			
			System.out.print("La base de datos está completamente vacía\n");
			return true;
		}
		
		else {

			for(int i = 0; i < Gestor.size(); i++){
				
				System.out.println("Contacto "+  (i+1) +": "+Gestor.get(i).ImprimirContacto());

			}
			
		}
		return false;
	}
	
	
	/**
	 * Change a field from a contact through its email
	 * @campo  the field that it want to be modified
	 * @email  email of the person that it is going to be modified
	 * @nuevoDato the information that we want to add or replace it
	 * @return true if it has been modified or false if it have not been modified
	 */
	public boolean Actualizar(String campo, String email, String nuevoDato){	
		if(campo.equalsIgnoreCase("email")) 
		{
			for(int i=0; i<Gestor.size(); i++) 
			{	
				if(email.equals(Gestor.get(i).getEmail())) 
				{	
					Gestor.get(i).setEmail(nuevoDato);
					ArrayToFile(path);
					return true; 
				}
			}
		}
		
		if(campo.equalsIgnoreCase("nombre")) 
		{
			for(int i=0; i<Gestor.size(); i++) 
			{	
				if(email.equals(Gestor.get(i).getEmail())) 
				{	
					Gestor.get(i).setNombre(nuevoDato);
					ArrayToFile(path);
					return true; 
				}
			}
		}
		
		if(campo.equalsIgnoreCase("apellidos")) 
		{
			for(int i=0; i<Gestor.size(); i++) 
			{	
				if(email.equals(Gestor.get(i).getEmail())) 
				{	
					Gestor.get(i).setApellidos(nuevoDato);
					ArrayToFile(path);
					return true; 
				}
			}
		}
		
		if(campo.equalsIgnoreCase("fecha de nacimiento"))
		{
			
			for(int i=0; i<Gestor.size(); i++) 
			{	
				if(email.equals(Gestor.get(i).getEmail()))
				{	
					SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
					Date nuevaFechaNacimiento = new Date(0);
					
					try {
						nuevaFechaNacimiento = fechaAux.parse(nuevoDato);
						Gestor.get(i).setFechaNacimiento(nuevaFechaNacimiento);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Gestor.get(i).CalcularEdad();
					ArrayToFile(path);
					return true; 
				}
			}
		}
		
		if(campo.equalsIgnoreCase("intereses"))
		{
			for(int i=0; i<Gestor.size(); i++) 
			{	
				if(email.equals(Gestor.get(i).getEmail())) 
				{	
					String[] interesesAux = nuevoDato.split(",");
					
					for(int j = 0; j < interesesAux.length; j++)
					{
						Gestor.get(i).setIntereses(nuevoDato);
					}
					ArrayToFile(path);
					return true; 
				}
			}
		}
		return false;
		
	}
	
	
	/**
	 * Check for the contact who has the field and identification received
	 * @param campo the field that it has been checking
	 * @param identificador parameter to identify the contact or contacts 
	 * @return An ArrayList<Contacto> with all the contacts found.
	 */
	public ArrayList<Contacto> Buscar(String campo, String identificador){
		
		ArrayList<Contacto> encontrados = new ArrayList<Contacto>();
		
			if(campo.equalsIgnoreCase("email")) {
				
				for(int i=0; i<Gestor.size(); i++) {
					
					if(identificador.equalsIgnoreCase(Gestor.get(i).getEmail())) {
						
						 encontrados.add(Gestor.get(i));
						 
					}
					
				}
				
				return encontrados;
				
			}
				
			if(campo.equalsIgnoreCase("nombre")) {
				
				for(int i=0; i<Gestor.size(); i++) {
					
					if(identificador.equalsIgnoreCase(Gestor.get(i).getNombre())) {	
						
						 encontrados.add(Gestor.get(i));
					}
					
				}
				
				return encontrados;
				
			}
			
			if(campo.equalsIgnoreCase("apellidos")) {
				
				for(int i=0; i<Gestor.size(); i++) {
					
					if(identificador.equalsIgnoreCase(Gestor.get(i).getApellidos())) {
						
						encontrados.add(Gestor.get(i));
						
						
					}
					
				}
				
				return encontrados;
				
			}
			
			if(campo.equalsIgnoreCase("fecha de nacimiento")){
				
				SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
				Date nuevaFechaNacimiento = new Date(0);
				
				for(int i=0; i<Gestor.size(); i++) {
					
					try {
						
						nuevaFechaNacimiento = fechaAux.parse(identificador);
						
					}catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(nuevaFechaNacimiento == Gestor.get(i).getFechaNacimiento()){	
					
						 encontrados.add(Gestor.get(i));
						
						
					}
					
				}
				
				return encontrados;
				
			}
			
			if(campo.equalsIgnoreCase("intereses")){
				
				Intereses aux = Intereses.valueOf(identificador);
				
				for(int i=0; i<Gestor.size(); i++) {
					
					for(int j = 0; j < Gestor.get(i).getIntereses().size(); j++){
						
						if(aux.equals(Gestor.get(i).getIntereses().get(j))) {
							
							 encontrados.add(Gestor.get(i));
					
						}
						
					}
					
				}
				
				return encontrados;
				
			}
	
			if(campo.equalsIgnoreCase("nombre y apellidos")){
				
				for(int i = 0; i < Gestor.size(); i++){
					
					if(identificador.equalsIgnoreCase(Gestor.get(i).getNombre() + " " + Gestor.get(i).getApellidos())){
						
						 encontrados.add(Gestor.get(i));
						
					}
					
				}
				
				return encontrados;
				
			}
		
			if(campo.equalsIgnoreCase("edad")){
				
				for(int i = 0; i < Gestor.size(); i++){
					
					if(Integer.parseInt(identificador) == (Gestor.get(i).getEdad())){
						
						 encontrados.add(Gestor.get(i));
						
					}
					
				}
				
				return encontrados;
				
			}
	
		return encontrados;
	}
	
	
	/**
	 * Calculate the age of the contacto who call it
	 * @return the age of the contact
	 */
	private int CalcularEdad(){
		
		LocalDate auxFechaNacimiento = this.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period edad = Period.between(auxFechaNacimiento, LocalDate.now());
		setEdad(edad.getYears());
		
		return edad.getYears();
		
	}
	

}
