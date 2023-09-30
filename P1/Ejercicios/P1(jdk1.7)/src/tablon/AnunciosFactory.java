package tablon;

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
		
		return new AnuncioGeneral(null,null,null);
	}

	public AnuncioIndividualizado createAnuncioIndividualizado() {
		
		return new AnuncioIndividualizado(null,null,null, null);
	}

	public AnuncioTematico createAnuncioTematico() {
		
		return new AnuncioTematico(null,null,null, null,null);
	}

	public AnuncioFlash createAnuncioFlash() {
		
		return new AnuncioFlash(null,null,null,null,null);
	}

}
