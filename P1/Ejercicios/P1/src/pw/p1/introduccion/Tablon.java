package pw.p1.introduccion;


import java.util.* ;


public class Tablon implements ITablon {
	
	/* Attributes*/
	
	protected ArrayList<Anuncio> Tablon;
	
	
	/**
	 * Empty constructor
	 */
	
	public Tablon() {
		this.Tablon = new ArrayList<Anuncio>();
	}
	
	/**
	 * Añade un nuevo anuncio al tablon
	 */
	
	public boolean publicarAnuncio(Anuncio arg0) {
		return Tablon.add(arg0);
	}

	/**
	 * Devuelve todos los anuncios del tablon
	 */
	
	public ArrayList<Anuncio> verAnuncios() {
		return Tablon;
	}

	/**
	 * Imprime todos los anuncios del tablon
	 */
	
	public void imprimirAnuncios(){
		
		for(int i = 0; i < Tablon.size(); i++)
		{
			System.out.println(Tablon.get(i).toString());
		}
	}

	
}
