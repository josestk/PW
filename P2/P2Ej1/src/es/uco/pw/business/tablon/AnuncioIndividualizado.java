package es.uco.pw.business.tablon;

import java.io.Serializable;

import es.uco.pw.business.contacto.Contacto;

/**
 * This class represents one type of advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public class AnuncioIndividualizado extends Anuncio implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	/* Constructor */
	public AnuncioIndividualizado(int id,String titulo, String cuerpo, Contacto propietario, String destinatarios) {
		
		super(id,titulo,cuerpo,propietario,destinatarios, TipoAnuncio.individualizado,Fases.editado);

	}

	public String toString() {		
		return "\nAnuncio id: " + this.getId() +  "\n Tipo: " + this.getTipo() + "\n Email propietario: " + this.getPropietario().getEmail() + "\n Estado del anuncio: " + this.getFase()+"\n Fecha de publicacion: " + this.getFechaPublicacion() + 
				"\n Destinatarios: " + this.getDestinatarios()+"\n Titulo: " + this.getTitulo()+ "\n Cuerpo: " + this.getCuerpo()+ "\n";
	}
	
}
