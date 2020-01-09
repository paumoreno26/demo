package co.com.utilitarios.exepcion;

/***
 * Clase para el manejo de Excepciones de periferia y convertirlas en un Wrap de la excepcion con el codigo
 */
public class ExcepcionPeriferia extends Exception{

	private static final long serialVersionUID = -9086272208085998724L;

	public ExcepcionPeriferia(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ExcepcionPeriferia(String mensaje) {
		super(mensaje);
	}
}
