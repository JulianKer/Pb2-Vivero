package ar.edu.unlam.pb1.vivero;

public class Arbusto extends Planta{
	
	private final Double GANANCIA_ARBUSTO = 1.6;
	 
	public Arbusto(Integer codigo, String nombre, Double precio, Integer stock) {
		super(codigo, nombre, precio, stock);
	}

	@Override
	public Double obtenerPrecio() {
		return (getPrecioBase()*(this.GANANCIA_ARBUSTO/100));
	}

}
