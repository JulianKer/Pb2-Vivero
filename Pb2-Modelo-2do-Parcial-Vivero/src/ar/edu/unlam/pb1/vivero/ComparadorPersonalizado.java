package ar.edu.unlam.pb1.vivero;

import java.util.Comparator;

public class ComparadorPersonalizado implements Comparator<Venta> {

	@Override
	public int compare(Venta venta1, Venta venta2) {
		
		//     de la planta de la venta 1 la casteo a tipo Arbol, obtiene su precio al momento de venderla y la * por la cantidad 
		Double totalDeVenta1 = (((Arbol)venta1.getPlanta()).obtenerPrecio() * venta1.getUnidades());
		
		//     de la planta de la venta 2 la casteo a tipo Arbol, obtiene su precio al momento de venderla y la * por la cantidad 
		Double totalDeVenta2 = (((Arbol)venta2.getPlanta()).obtenerPrecio() * venta2.getUnidades());
		
		      // las ordeno por orden natural que tiene el compareTo (osea muestra de menor a mayor)
		return  totalDeVenta1.compareTo(totalDeVenta2);
	}

	

	
}
