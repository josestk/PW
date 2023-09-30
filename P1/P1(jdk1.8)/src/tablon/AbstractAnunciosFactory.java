package tablon;

/**
 * This interface represents the methods that
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */

public abstract class AbstractAnunciosFactory {
	
	/**
	 * Create a General advertisement 
	 */
	public abstract AnuncioGeneral createAnuncioGeneral();

	/**
	 * Constructor of a customize advertisement 
	 */
	public abstract AnuncioIndividualizado createAnuncioIndividualizado();

	/**
	 * Constructor of a Thematic advertisement 
	 */
	public abstract AnuncioTematico createAnuncioTematico();

	/**
	 * Constructor of a Flash advertisement 
	 */
	public abstract AnuncioFlash createAnuncioFlash();

}
