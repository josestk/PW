package tablon;

import java.util.ArrayList;
import java.util.Date;

import contacto.Contacto;

/**
 * This class represents one type of advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public class AnuncioFlash extends Anuncio{
	
	/* Attributes */
	Date fechaInicio = new Date(0);
	Date fechaFinal = new Date(0);
	
	private static ArrayList<Contacto> destinatarios = Contacto.getContactos();
	
	/* Constructor */
	public AnuncioFlash(String titulo, String cuerpo, Contacto propietario, Date fechainicio, Date fechafinal) {
		
		super(titulo,cuerpo,propietario,destinatarios, TipoAnuncio.flash,Fases.editado);
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

		return "Anuncio id: " + this.getId() + "\n Tipo: " + this.getTipo() + "\n Estado del anuncio:" + this.getFase()+ "\n Fecha de publicacion:" + this.getFechaPublicacion() 
			   +"\n Fecha inicio de publicacion: "+this.getFechaInicio()+"\n Fecha final de publicacion: "+this.getFechaFinal()+ "\n Email propietario: " + this.getPropietario().getEmail() +"\n Titulo: " + this.getTitulo() 
			   +  "\n Cuerpo: " + this.getCuerpo();
		
	}
	
}
