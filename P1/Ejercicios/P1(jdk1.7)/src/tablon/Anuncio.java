package tablon;

import java.util.ArrayList;
import java.util.Date;

import contacto.Contacto;


/**
 * This class represent an advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public abstract class Anuncio{

	/* Attributes */
	private int id = (int) Math.floor(Math.random()*(1000-0+1)+0);
	private String titulo = new String();
	private Contacto propietario = Contacto.getContacto();
	private ArrayList <Contacto> destinatarios  = new ArrayList<Contacto>(); 
	private String cuerpo = new String();
	private Date fechaPublicacion = new Date(0);
	private Fases fase;
	private TipoAnuncio tipo;
	
	
	/* Constructor */
	public Anuncio(String titulo, String cuerpo, Contacto propietario, ArrayList<Contacto> destinatarios, TipoAnuncio tipo,Fases fase) {
		
		this.titulo = titulo;
		this.propietario = propietario;
		this.destinatarios = destinatarios;
		this.fase = fase;
		this.tipo = tipo;
		
	}
	
	
	/* Setters and Getters */
	public int getId() {
		return this.id;
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
		return this.propietario;
	}
	
	
	public void setPropietario(Contacto usuario) {
		this.propietario = usuario;
	}
	
	
	public ArrayList<Contacto> getDestinatarios() {
		return this.destinatarios;
	}
	
	
	public void setDestinatarios(ArrayList<Contacto> nuevosDestintarios) {
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
		String destinatarios = this.getDestinatarios().get(0).getEmail() + " ";
		for(int i =1; i< this.getDestinatarios().size()-1; i++) {
			 destinatarios = destinatarios + this.getDestinatarios().get(i).getEmail() + " ";
		}
		return "Anuncio id: " + this.id +  "\n Tipo: " + this.tipo + "\n Email propietario: " + this.propietario.getEmail() + "\n Estado del anuncio:" + this.getFase()+ "\n Fecha de publicacion:" + this.fechaPublicacion + 
				"\n Titulo: " + titulo + "\n Cuerpo: " + cuerpo;
	}
	
	
}
