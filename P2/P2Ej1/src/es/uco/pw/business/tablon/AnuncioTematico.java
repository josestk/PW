package es.uco.pw.business.tablon;

import java.io.Serializable;
import java.util.ArrayList;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.Intereses;

/**
 * This class represents one type of advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */

public class AnuncioTematico extends Anuncio implements Serializable{

	private static final long serialVersionUID = 1L;
	/* Attributes*/
	ArrayList <Intereses> intereses = new ArrayList<Intereses>();
	
	/* Constructor */
	public AnuncioTematico(int id,String titulo, String cuerpo, Contacto propietario, ArrayList<Intereses> intereses) {
		
		super(id,titulo,cuerpo,propietario,"-", TipoAnuncio.tematico,Fases.editado);
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
		
		return 	"\nAnuncio id: " + this.getId() + "\n Tipo: " + this.getTipo() + "\n Estado del anuncio: " + this.getFase()+"\n Fecha de publicacion: " + this.getFechaPublicacion() + "\n Email propietario: " + this.getPropietario().getEmail()
				+ "\n Titulo: " + this.getTitulo() + "\n Cuerpo: " + this.getCuerpo()+ "\n Temas de interes: " + this.getIntereses()+ "\n";
		
	}
	
	
}
