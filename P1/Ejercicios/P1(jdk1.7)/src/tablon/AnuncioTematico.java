package tablon;

import java.util.ArrayList;

import contacto.Contacto;
import contacto.Intereses;

/**
 * This class represents one type of advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */

public class AnuncioTematico extends Anuncio {

	/* Attributes*/
	ArrayList <Intereses> intereses = new ArrayList<Intereses>();
	
	/* Constructor */
	public AnuncioTematico(String titulo, String cuerpo, Contacto propietario, ArrayList<Contacto> destinatarios, ArrayList<Intereses> intereses) {
		
		super(titulo,cuerpo,propietario,destinatarios, TipoAnuncio.tematico,Fases.editado);
		this.intereses = intereses;
	}
	
	/* setters and getters */
	public ArrayList<Intereses> getIntereses(){
		
		return this.intereses;
	}
	
	public void setIntereses(ArrayList<Intereses> Nuevos) {
		
		this.intereses = Nuevos;
	}

	public String toString() {
		
		return 	"Anuncio id: " + this.getId() + "\n Tipo: " + this.getTipo() + "\n Estado del anuncio:" + this.getFase()+"\n Fecha de publicacion:" + this.getFechaPublicacion() + "\n Email propietario: " + this.getPropietario().getEmail()
				+ "\n Titulo: " + this.getTitulo() + "\n Cuerpo: " + this.getCuerpo()+ "\n Temas de interes: " + this.getIntereses();
		
	}
	
	
}

