package es.uco.pw.bussiness.anuncios;

import java.io.Serializable;

import es.uco.pw.bussiness.contacto.Contacto;

/**
 * This class represents one type of advertisement
 * @author CarlosPC
 * @author Jose Antonio Gonzalez
 */
public class AnuncioGeneral  extends Anuncio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/* Constructor */
	public AnuncioGeneral(int id,String titulo, String cuerpo, Contacto propietario) {
		
		super(id,titulo,cuerpo,propietario,"-", TipoAnuncio.general, Fases.editado);
		
	}
	

}
