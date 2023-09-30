package es.uco.pw.business.tablon;


import java.util.Date;

import es.uco.pw.business.contacto.Contacto;


/**
 * This class represent an advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public abstract class Anuncio{

	/* Attributes */
	private int id = 0;
	private String titulo = new String();
	private Contacto propietario;
	private String destinatarios  = new String();
	private String cuerpo = new String();
	private Date fechaPublicacion = new Date(0);
	private Fases fase;
	private TipoAnuncio tipo;
	
	
	/* Constructor */
	public Anuncio(int id,String titulo, String cuerpo, Contacto propietario, String destinatarios, TipoAnuncio tipo,Fases fase) {
		
		this.id = id;
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.propietario = propietario;
		this.destinatarios = destinatarios;
		this.fase = fase;
		this.tipo = tipo;
		
	}
	
	
	/* Setters and Getters */
	public int getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		
		return this.titulo;
	}
	
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
		
	}
	
	
	public String getCuerpo() {
		return this.cuerpo;
	}
	
	
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
		
	}
	
	
	public Contacto getPropietario() {
		return propietario;
	}
	
	
	public void setPropietario(Contacto usuario) {
		this.propietario = usuario;
	}
	
	
	public String getDestinatarios() {
		return this.destinatarios;
	}
	
	
	public void setDestinatarios(String nuevosDestintarios) {
		this.destinatarios = nuevosDestintarios;
	}
	
	
	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}
	
	
	public void setFechaPublicacion(Date fechaAPublicar) {
		this.fechaPublicacion = fechaAPublicar;
		
	}
	
	
	public Fases getFase() {
		return this.fase;
	}
	
	
	public void setFase(Fases fase) {
		this.fase =  fase;
	}


	public TipoAnuncio getTipo() {
		return tipo;
	}


	public void setTipo(TipoAnuncio tipo) {
		this.tipo = tipo;
	}


	public String toString() {

		return "\nAnuncio id: " + this.id +  "\n Tipo: " + this.tipo + "\n Email propietario: " + this.propietario.getEmail() + "\n Estado del anuncio: " + this.getFase()+ "\n Fecha de publicacion: " + this.fechaPublicacion + 
				"\n Titulo: " + this.titulo + "\n Cuerpo: " + this.cuerpo + "\n";
	}
	
	
}
