package es.uco.pw.business.tablon;

/**
 * Public factory of advertisement
 * @author Carlos Revuelto
 * @author Jose Antonio Gonzalez
 */
public class AnunciosFactory extends AbstractAnunciosFactory {
	
	/**
	 *  Factory methods for each product
	 */
	
	public AnuncioGeneral createAnuncioGeneral() {
		
		return new AnuncioGeneral(0,null,null,null);
	}

	public AnuncioIndividualizado createAnuncioIndividualizado() {
		
		return new AnuncioIndividualizado(0,null,null,null, null);
	}

	public AnuncioTematico createAnuncioTematico() {
		
		return new AnuncioTematico(0,null, null,null,null);
	}

	public AnuncioFlash createAnuncioFlash() {
		
		return new AnuncioFlash(0,null,null,null,null,null);
	}

}
