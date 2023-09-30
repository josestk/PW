package tablon;

import java.util.ArrayList;

import contacto.Contacto;
/**
 * This class represents one type of advertisement
 * @author CarlosPC
 * @author Jose Antonio Gonzalez
 */
public class AnuncioGeneral  extends Anuncio{
	
	/* Attributes */
		
	private static ArrayList<Contacto> destinatarios = Contacto.getContactos();
	
	
	/* Constructor */
	public AnuncioGeneral(String titulo, String cuerpo, Contacto propietario) {
		
		super(titulo,cuerpo,propietario,destinatarios, TipoAnuncio.general, Fases.editado);
		
	}
	

}
