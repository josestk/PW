package pw.p1.introduccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * A subclass that inherits from Tablon
 * */

public class BusquedasTablon extends Tablon {
	

	public List<String> verTitulos() {
		
		List<String> titulos = new ArrayList<String>();
		
		for(int i = 0; i < Tablon.size(); i++)
		{
			titulos.add(Tablon.get(i).getTitulo());
		}
		return titulos;
	}
	
	
	public boolean eliminarAnuncio(String titulo,String autor) {
		
		for(int i = 0; i < Tablon.size(); i++) {
			
			if(titulo.equals(Tablon.get(i).getTitulo()) && autor.equals(Tablon.get(i).getUsuario()) ) {
				Tablon.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Anuncio> verAnunciosUsuario(String usuario){
		
		ArrayList<Anuncio> AnunciosUsuario = new ArrayList<Anuncio>();
		
		for(int i = 0; i < Tablon.size(); i++) {
			
			if(usuario.equals(Tablon.get(i).getUsuario())) {
				AnunciosUsuario.add(Tablon.get(i));
			}
		}
		return AnunciosUsuario;
	}
	
	public ArrayList<Anuncio> verAnunciosFechas(Date inicio, Date fin){
		
		ArrayList<Anuncio> AnunciosFechas = new ArrayList<Anuncio>();		
		
		for(int i = 0; i < Tablon.size(); i++) {
			
			if((inicio.compareTo(Tablon.get(i).getFecha()) <= 0) && (Tablon.get(i).getFecha().compareTo(fin) <= 0)){
				AnunciosFechas.add(Tablon.get(i));
			}
		}
		return AnunciosFechas;
	}
	
	
}
