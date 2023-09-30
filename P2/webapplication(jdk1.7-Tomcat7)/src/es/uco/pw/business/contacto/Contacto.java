package es.uco.pw.business.contacto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * This class represent an object Contact
 * @author Carlos Revuelto Quero
 * @author Jose Antonio Gonzalez Aguilera
 */
public class Contacto implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/* Attributes */
	private String email = new String();
	private String nombre = new String();
	private String apellidos = new String();
	private Date fechaNacimiento = new Date();
	private int edad = 0;
	private ArrayList<Intereses> intereses = new ArrayList<Intereses>();
	private String password = new String();
	
	public Contacto() {
		this.password = "password";
	}
	
	public Contacto(String email, String nombre, String apellidos, Date fechaNacimiento, ArrayList<Intereses> intereses) {
		this.email = " ";
		this.nombre = " ";
		this.apellidos = " ";
		this.fechaNacimiento = fechaNacimiento;
		this.CalcularEdad();
		this.intereses = intereses;
		this.password = "password";
	}
	

	
	/* Getters and setters */

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
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


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		this.CalcularEdad();
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public ArrayList<Intereses> getIntereses() {
		return intereses;
	}


	public void setIntereses(ArrayList<Intereses> intereses) {
		this.intereses = intereses;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String toString() {
		return " -Email: "+this.email + "   -Nombre: "+this.nombre+"   -Apellidos: "+this.apellidos+"   -FechaNacimiento: "+this.fechaNacimiento+ "   -Edad: " + this.edad + "   -Intereses: "+this.intereses.toString() + "\n";
	}

	/* Other methods */	
	/**
	 * Calculate the age of the contacto who call it
	 * @return the age of the contact
	 */
	private void CalcularEdad(){
		
		Calendar fechaNacimiento = Calendar.getInstance();
		Calendar fechaActual = Calendar.getInstance();
		fechaNacimiento.setTime(this.getFechaNacimiento());
		
		this.edad = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
		 
			
	}
	
	
}
