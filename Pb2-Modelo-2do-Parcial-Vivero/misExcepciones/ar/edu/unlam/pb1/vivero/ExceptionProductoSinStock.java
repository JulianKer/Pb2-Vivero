package ar.edu.unlam.pb1.vivero;

public class ExceptionProductoSinStock extends Exception {
	
	public ExceptionProductoSinStock(String mensaje) {
		super(mensaje);
	}
}
