package tablon;

import java.util.ArrayList;

import contacto.Contacto;

/**
 * This class represents one type of advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public class AnuncioIndividualizado extends Anuncio{
	
	/* Constructor */
	public AnuncioIndividualizado(String titulo, String cuerpo, Contacto propietario, ArrayList<Contacto> destinatarios) {
		
		super(titulo,cuerpo,propietario,destinatarios, TipoAnuncio.individualizado,Fases.editado);

	}

	public String toString() {
		String destinatarios = this.getDestinatarios().get(0).getEmail() + " ";
		for(int i =1; i< this.getDestinatarios().size()-1; i++) {
			 destinatarios = destinatarios + this.getDestinatarios().get(i).getEmail() + " ";
		}
		
		return "Anuncio id: " + this.getId() +  "\n Tipo: " + this.getTipo() + "\n Email propietario: " + this.getPropietario().getEmail() + "\n Estado del anuncio:" + this.getFase()+"\n Fecha de publicacion:" + this.getFechaPublicacion() + 
				"\n Destinatarios: " + destinatarios +"\n Titulo: " + this.getTitulo()+ "\n Cuerpo: " + this.getCuerpo();
	}
	
}
