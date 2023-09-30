package pw.p1.introduccion;



import java.util.ArrayList;


public interface ITablon {
	
	/**
	 * Add an announce
	 * @param arg0
	 * @return
	 */
	public boolean publicarAnuncio(Anuncio arg0);
	
	/**
	 * Return all the announces
	 * @return
	 */
	public ArrayList<Anuncio> verAnuncios();
	
	/**
	 * Print the announces
	 */
	public void imprimirAnuncios();

	/**
	 * Return all the titles
	 * @return
	 */

}
