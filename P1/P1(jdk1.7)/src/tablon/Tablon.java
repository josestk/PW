package tablon;

import java.util.ArrayList;
import java.util.Date;

import contacto.Contacto;
import contacto.Intereses;



/**
 * This class represents a Tablon  
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public class Tablon implements ITablon{
	
	/* Attributes */
	@SuppressWarnings("unused")
	private AnuncioFlash anuncioFlash;
	@SuppressWarnings("unused")
	private AnuncioGeneral anuncioGeneral;
	@SuppressWarnings("unused")
	private AnuncioIndividualizado anuncioIndividualizado;
	@SuppressWarnings("unused")
	private AnuncioTematico anuncioTematico;
	
	protected ArrayList<AnuncioIndividualizado> anunciosIndividualizados = new ArrayList<AnuncioIndividualizado>();
	protected ArrayList<AnuncioTematico> anunciosTematicos = new ArrayList<AnuncioTematico>();
	protected ArrayList<AnuncioFlash> anunciosFlash = new ArrayList<AnuncioFlash>();
	protected ArrayList<AnuncioGeneral> anunciosGenerales = new ArrayList<AnuncioGeneral>();
	
	/* Constructor */
	public Tablon(AnunciosFactory factory) {
		
		anuncioFlash = factory.createAnuncioFlash();
		anuncioGeneral = factory.createAnuncioGeneral();
		anuncioIndividualizado = factory.createAnuncioIndividualizado();
		anuncioTematico = factory.createAnuncioTematico();
		
	}

	
	public ArrayList<String> buscarAnuncioPropietario(Contacto propietario) {
		
		ArrayList<String> encontrados = new ArrayList<String>();

		if(!anunciosIndividualizados.isEmpty()) {
			
			for(AnuncioIndividualizado i : anunciosIndividualizados){
				
				if(propietario.equals(i.getPropietario()) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosTematicos.isEmpty()) {
			
			for(AnuncioTematico i : anunciosTematicos){
				
				if(propietario.equals(i.getPropietario()) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosGenerales.isEmpty()) {
			
			for(AnuncioGeneral i : anunciosGenerales){
				
				if(propietario.equals(i.getPropietario()) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosFlash.isEmpty()) {
			
			for(AnuncioFlash i : anunciosFlash){
				
				if(propietario.equals(i.getPropietario()) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		return encontrados;
	}


	public ArrayList<String> buscarAnuncioFecha(Date fecha) {
		
		ArrayList<String> encontrados = new ArrayList<String>();
		
		if(!anunciosIndividualizados.isEmpty()) {
			
			for(AnuncioIndividualizado i : anunciosIndividualizados){
				
				if((i.getFechaPublicacion().compareTo(fecha) >= 0) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosTematicos.isEmpty()) {
			
			for(AnuncioTematico i : anunciosTematicos){
				
				if((i.getFechaPublicacion().compareTo(fecha) >= 0) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosGenerales.isEmpty()) {
			
			for(AnuncioGeneral i : anunciosGenerales){
				
				if((i.getFechaPublicacion().compareTo(fecha) >= 0) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosFlash.isEmpty()) {
			
			for(AnuncioFlash i : anunciosFlash){
				
				if((i.getFechaPublicacion().compareTo(fecha) >= 0) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		return encontrados;
	}

	
	public ArrayList<String> buscarAnuncioDestinatarios(ArrayList<Contacto> destinatarios) {
		ArrayList<String> encontrados = new ArrayList<String>();
		
		if(!anunciosIndividualizados.isEmpty()) {
			
			for(AnuncioIndividualizado i : anunciosIndividualizados){
				
				if((destinatarios.containsAll(i.getDestinatarios())) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosTematicos.isEmpty()) {
			
			for(AnuncioTematico i : anunciosTematicos){
				
				if((destinatarios.containsAll(i.getDestinatarios())) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosGenerales.isEmpty()) {
			
			for(AnuncioGeneral i : anunciosGenerales){
				
				if((destinatarios.containsAll(i.getDestinatarios())) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		if(!anunciosFlash.isEmpty()) {
			
			for(AnuncioFlash i : anunciosFlash){
				
				if((destinatarios.containsAll(i.getDestinatarios())) && (i.getFase().equals(Fases.publicado))) {
					
					encontrados.add(i.toString());
				}
			}
		}
		
		return encontrados;
	}
	
	
	public ArrayList<String> buscarAnuncioIntereses(ArrayList<Intereses> intereses) {
		
		ArrayList<String> encontrados = new ArrayList<String>();
		
		if(!anunciosTematicos.isEmpty()) {
			for(int i = 0; i < anunciosTematicos.size(); i++){
				
				if(anunciosTematicos.get(i).getFase().equals(Fases.publicado)){
					
					for(int j = 0; j < intereses.size(); j++) {
						if(anunciosTematicos.get(i).getIntereses().contains(intereses.get(j)))
						{
							encontrados.add(anunciosTematicos.get(i).toString());
						}
						else if(encontrados.contains(anunciosTematicos.get(i).toString())){
							i++;
						}
					}	
				}
			}	
		}
		
		return encontrados;
	}

	
	public AnuncioTematico buscarAnuncioIdTematico(int id) {
		
		if(!anunciosTematicos.isEmpty()) {
			
			for(AnuncioTematico i : anunciosTematicos){
				
				return i;
			}
		}
		
		return null;
	}
	
	
	public AnuncioGeneral buscarAnuncioIdGeneral(int id) {
		
		if(!anunciosGenerales.isEmpty()) {
			
			for(AnuncioGeneral i : anunciosGenerales){
				
				return i;
			}
		}
		
		return null;
	}
	
	
	public AnuncioIndividualizado buscarAnuncioIdIndividualizado(int id) {
	
		if(!anunciosIndividualizados.isEmpty()) {
			
			for(AnuncioIndividualizado i : anunciosIndividualizados){
				
				return i;
			}
		}
		
		return null;
	}
	
	public AnuncioFlash buscarAnuncioIdFlash(int id) {
		
		if(!anunciosFlash.isEmpty()) {
			
			for(AnuncioFlash i : anunciosFlash){
				
				return i;
			}
		}
		
		return null;
	}

	
	public void editarAnuncio(AnuncioTematico anuncioTematico, String campo, String nuevoDato,ArrayList<Intereses> nuevosIntereses) {

			if(campo.equalsIgnoreCase("titulo")) {
				
				anunciosTematicos.remove(anuncioTematico);
				anuncioTematico.setTitulo(nuevoDato);
			}
			
			else if(campo.equalsIgnoreCase("cuerpo")) {
				
				anunciosTematicos.remove(anuncioTematico);
				anuncioTematico.setCuerpo(nuevoDato);
			}
			
			else if(campo.equalsIgnoreCase("temas de interes")){
				
				if(!nuevosIntereses.isEmpty()) {
					
					anunciosTematicos.remove(anuncioTematico);
					anuncioTematico.setIntereses(nuevosIntereses);
				}
			}

		anuncioTematico.setFase(Fases.editado);
		anunciosTematicos.add(anuncioTematico);
	}


	public void editarAnuncio(AnuncioGeneral anuncioGeneral, String campo, String nuevoDato) {

		if(campo.equalsIgnoreCase("titulo")) {
			
			anunciosGenerales.remove(anuncioGeneral);
			anuncioGeneral.setTitulo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("cuerpo")) {
			
			anunciosGenerales.remove(anuncioGeneral);
			anuncioGeneral.setCuerpo(nuevoDato);
		}

		anuncioGeneral.setFase(Fases.editado);
		anunciosGenerales.add(anuncioGeneral);
	}


	public void editarAnuncio(AnuncioIndividualizado anuncioIndividualizado, String campo, String nuevoDato,ArrayList<Contacto> nuevosDestinatarios) {

		if(campo.equalsIgnoreCase("titulo")) {
			
			anunciosIndividualizados.remove(anuncioIndividualizado);
			anuncioIndividualizado.setTitulo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("cuerpo")) {
			
			anunciosIndividualizados.remove(anuncioIndividualizado);
			anuncioIndividualizado.setCuerpo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("destinatarios")) {
			
			if(!nuevosDestinatarios.isEmpty()) {
				
				anunciosIndividualizados.remove(anuncioIndividualizado);
				anuncioIndividualizado.setDestinatarios(nuevosDestinatarios);
			}				
		}
		
		anuncioIndividualizado.setFase(Fases.editado);
		anunciosIndividualizados.add(anuncioIndividualizado);	
	}

	
	public void editarAnuncio(AnuncioFlash anuncioFlash, String campo, String nuevoDato,Date nuevaFecha) {

		if(campo.equalsIgnoreCase("titulo")) {
			
			anunciosFlash.remove(anuncioFlash);
			anuncioFlash.setTitulo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("cuerpo")) {
			
			anunciosFlash.remove(anuncioFlash);
			anuncioFlash.setCuerpo(nuevoDato);
		}
		
		else if(campo.equalsIgnoreCase("fecha inicio de publicacion")) {
			
			anunciosFlash.remove(anuncioFlash);
			anuncioFlash.setFechaInicio(nuevaFecha);
		}
		else if(campo.equalsIgnoreCase("fecha final de publicacion")){
			anunciosFlash.remove(anuncioFlash);
			anuncioFlash.setFechaFinal(nuevaFecha);
		}
		
		anuncioFlash.setFase(Fases.editado);
		anunciosFlash.add(anuncioFlash);
	}

	
	public void publicarAnuncioTematico(AnuncioTematico anuncioTematico) {

		if( (anuncioTematico.getFase().equals(Fases.editado) )){
			
			Date fechaHoy = new Date();
			anuncioTematico.setFechaPublicacion(fechaHoy);
			anuncioTematico.setFase(Fases.publicado);
			
		}
	}

	
	public void publicarAnuncioIndividualizado(AnuncioIndividualizado anuncioIndividualizado) {
		
		if( (anuncioIndividualizado.getFase().equals(Fases.editado) || (anuncioIndividualizado.getFase().equals(Fases.espera)))){
			
			Date fechaHoy = new Date();
			anuncioIndividualizado.setFechaPublicacion(fechaHoy);
			anuncioIndividualizado.setFase(Fases.publicado);
			
		}
	}

	
	public void publicarAnuncioGeneral(AnuncioGeneral anuncioGeneral) {

		if( (anuncioGeneral.getFase().equals(Fases.editado) || (anuncioGeneral.getFase().equals(Fases.espera)))){
			
			Date fechaHoy = new Date();
			anuncioGeneral.setFechaPublicacion(fechaHoy);
			anuncioGeneral.setFase(Fases.publicado);
			
		}
	}

	
	public void publicarAnuncioFlash(AnuncioFlash anuncioFlash) {

		if( (anuncioFlash.getFase().equals(Fases.editado) || (anuncioFlash.getFase().equals(Fases.espera)))){
			
			Date fechaHoy = new Date();
			if(fechaHoy.before(anuncioFlash.fechaInicio))
			{
				anuncioFlash.setFase(Fases.espera);
			}
			else{
				anuncioFlash.setFase(Fases.publicado);
				
			}
			anuncioFlash.setFechaPublicacion(fechaHoy);	
		}
	}
		
	
	public void crearAnuncios() {
		
		AnunciosFactory factory = new AnunciosFactory();
		ArrayList<Intereses> intereses = new ArrayList<Intereses>();

		Contacto usuario1 = Contacto.getContacto();
			usuario1.setEmail("maria@gmail.com");
			usuario1.setNombre("Maria");
			usuario1.setApellidos("Mu�oz Garcia");
			usuario1.setIntereses("fitness");
		
		AnuncioTematico anuncioT= factory.createAnuncioTematico();
			anuncioT.setPropietario(usuario1);
			anuncioT.setTitulo("Nueva rutina magnifica");
			anuncioT.setCuerpo("En este articulo os vengo a explicar una rutina para fortalecerte");	
			intereses.add(Intereses.fitness);
			anuncioT.setIntereses(intereses);
		
		
		Contacto usuario2 = Contacto.getContacto();
			usuario2.setEmail("carlos@gmail.com");
			usuario2.setNombre("Carlos");
			usuario2.setApellidos("Aguilera Jurado");
			usuario2.setIntereses("videojuegos,cine");
		
		AnuncioGeneral anuncioG= factory.createAnuncioGeneral();
			anuncioG.setPropietario(usuario2);
			anuncioG.setTitulo("Rewiew Assasing Creed");
			anuncioG.setCuerpo("Hoy os traigo en exclusiva la rewiew del nuevo Assasing Creed");
		
		AnuncioFlash anuncioF= factory.createAnuncioFlash();
			anuncioF.setPropietario(usuario2);
			anuncioF.setTitulo("Critica a Tenet");
			anuncioF.setCuerpo("En esta ocasion os traigo una critica a Tenet, ya que me ha parecido horrible");
			anuncioF.setFechaInicio(new Date());
			anuncioF.setFechaFinal(new Date(999999999));
		
		
		Contacto usuario3 = Contacto.getContacto();
			usuario3.setEmail("julian@gmail.com");
			usuario3.setNombre("Julian");
			usuario3.setApellidos("Adan Matei");
			usuario3.setIntereses("cine");
		
		AnuncioIndividualizado anuncioI = factory.createAnuncioIndividualizado();
			anuncioI.setPropietario(usuario3);
			anuncioI.setTitulo("Critica a Interestelar");
			anuncioI.setCuerpo("No me ha gustado nada la pelicula porque se me hizo muy lenta y larga");
			ArrayList<Contacto> destinatariosAI = Contacto.getContactos();
			destinatariosAI.remove(usuario1);
			destinatariosAI.remove(usuario2);
			destinatariosAI.remove(0);
			anuncioI.setDestinatarios(destinatariosAI);

	}
	
	
	public void LeerTablon(ArrayList<String> tablonusuario) {
		
		for(int i=0; i<tablonusuario.size(); i++) {
			
			System.out.println(tablonusuario.get(i));
		}
	}
}

