package ar.edu.unlam.pb1.vivero;

public class Hierba extends Planta{

	private final Double GANANCIA_HIERBA_MATA = 1.2;

	public Hierba(Integer codigo, String nombre, Double precio, Integer stock) {
		super(codigo, nombre, precio, stock);
	}

	@Override
	public Double obtenerPrecio() {
		return (getPrecioBase()*(this.GANANCIA_HIERBA_MATA/100));
	}
	
	
	
}
