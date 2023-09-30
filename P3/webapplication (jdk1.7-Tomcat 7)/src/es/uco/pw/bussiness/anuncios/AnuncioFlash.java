package es.uco.pw.bussiness.anuncios;

import java.io.Serializable;
import java.util.Date;

import es.uco.pw.bussiness.contacto.Contacto;

/**
 * This class represents one type of advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public class AnuncioFlash extends Anuncio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/* Attributes */
	Date fechaInicio = new Date(0);
	Date fechaFinal = new Date(0);
	
	/* Constructor */
	public AnuncioFlash(int id,String titulo, String cuerpo, Contacto propietario, Date fechainicio, Date fechafinal) {
		
		super(id,titulo,cuerpo,propietario,"-", TipoAnuncio.flash,Fases.editado);
		this.fechaInicio = fechainicio;
		this.fechaFinal = fechafinal;
		
	}
		
	/* Getters and setters */
	public Date getFechaInicio() {

		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date FechaAPublicar) {

		this.fechaInicio = FechaAPublicar;
		
	}
	
	
	public Date getFechaFinal() {

		return this.fechaFinal;
	}
	
	public void setFechaFinal(Date FechaAPublicar) {

		this.fechaFinal = FechaAPublicar;
		
	}
	

	public String toString() {

		return "\nAnuncio id: " + this.getId() + "\n Tipo: " + this.getTipo() + "\n Estado del anuncio: " + this.getFase()+ "\n Fecha de publicacion: " + this.getFechaPublicacion() 
			   +"\n Fecha inicio de publicacion: "+this.getFechaInicio()+"\n Fecha final de publicacion: "+this.getFechaFinal()+ "\n Email propietario: " + this.getPropietario().getEmail() +"\n Titulo: " + this.getTitulo() 
			   +  "\n Cuerpo: " + this.getCuerpo();
		
	}
	
}
