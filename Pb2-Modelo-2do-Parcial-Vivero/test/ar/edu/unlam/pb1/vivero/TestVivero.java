package ar.edu.unlam.pb1.vivero;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class TestVivero {

	@Test (expected = ExceptionYaExisteUnaPlantaConEseId.class)
	public void queElMetodoAgregarPlantaArrojeUnaExcepcionDeValidacion() throws ExceptionYaExisteUnaPlantaConEseId {
		String nombreVivero = "viverito";
		Vivero vivero = new Vivero(nombreVivero);
		
		Integer codigo1 = 1;
		String nombre1 = "Hierba1";
		Double precioBase1 = 100.0;
		Integer stock1 = 10;
		
		Planta hierba = new Hierba(codigo1, nombre1, precioBase1, stock1);
		
		String nombre2 = "Arbusto1";
		Double precioBase2 = 350.0;
		Integer stock2 = 5;
		
		Planta arbusto = new Arbusto(codigo1, nombre2, precioBase2, stock2);
		
		vivero.agregarPlanta(hierba);
		vivero.agregarPlanta(arbusto);
	}
	
	@Test (expected = ExceptionPlantaInexistente.class)
	public void queElMetodoVenderPlantaArrojeUnaExcepcion() throws ExceptionPlantaInexistente{
		String nombreVivero = "viverito";
		Vivero vivero = new Vivero(nombreVivero);
		
		Integer codigo1 = 1;
		String nombre1 = "Hierba1";
		Double precioBase1 = 100.0;
		Integer stock1 = 10;
		Planta hierba = new Hierba(codigo1, nombre1, precioBase1, stock1);
		
		Integer codigo2 = 2;
		String nombre2 = "Arbusto1";
		Double precioBase2 = 350.0;
		Integer stock2 = 5;
		Planta arbusto = new Arbusto(codigo2, nombre2, precioBase2, stock2);
		
		Integer codigo3 = 3;
		String nombre3 = "Arbol1";
		Double precioBase3 = 500.0;
		Integer stock3 = 24;
		Planta arbol = new Arbusto(codigo3, nombre3, precioBase3, stock3);
		
		Integer codigoInexistente = 5;
		Integer cantidadAVender = 10;
		
		
		try {
			vivero.agregarPlanta(hierba);
			vivero.agregarPlanta(arbusto);
			vivero.agregarPlanta(arbol);
			
			vivero.venderPlanta(codigoInexistente, cantidadAVender);
			
		}catch(ExceptionYaExisteUnaPlantaConEseId | ExceptionProductoSinStock e) {
			e.getMessage(); // --> puedo mostrar el mensaje segun la excepcion capturada
		}
	}
	
	@Test 
	public void queMeDeElMetodoObtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta(){
		String nombreVivero = "viverito";
		Vivero vivero = new Vivero(nombreVivero);
		
		Integer codigo1 = 1;
		String nombre1 = "Hierba1";
		Double precioBase1 = 100.0;
		Integer stock1 = 10;
		Planta hierba = new Hierba(codigo1, nombre1, precioBase1, stock1);
		
		Integer codigo2 = 2;
		String nombre2 = "Arbusto1";
		Double precioBase2 = 350.0;
		Integer stock2 = 5;
		Planta arbusto = new Arbusto(codigo2, nombre2, precioBase2, stock2);
		
		
		
		
		Integer codigo3 = 3;
		String nombre3 = "Limonero";
		Double precioBase3 = 1000.0;
		Integer stock3 = 24;
		Planta limonero = new Arbol(codigo3, nombre3, precioBase3, stock3);
		
		Integer codigo4 = 4;
		String nombre4 = "Olivo";
		Double precioBase4 = 1500.0;
		Integer stock4 = 12;
		Planta olivo = new Arbol(codigo4, nombre4, precioBase4, stock4);
		
		Integer codigo5 = 5;
		String nombre5 = "Higuera";
		Double precioBase5 = 2000.0;
		Integer stock5 = 5;
		Planta higuera = new Arbol(codigo5, nombre5, precioBase5, stock5);
		
		Integer cantidadAVenderDeLimoneros = 10;		
		Integer cantidadAVenderDeOlivos = 5;
		Integer cantidadAVenderDeHigueras = 2;
		
		Set<Venta> coleccionEsperada;
		
		try {
			// ---- agrego y vendo plantas que NO sean arboles----
			vivero.agregarPlanta(hierba);
			vivero.agregarPlanta(arbusto);
			
			vivero.venderPlanta(codigo1, 5);
			vivero.venderPlanta(codigo2, 5);
			// ---------------------------------------------------
			
			
			// ---- agrego y vendo plantas que SI SON arboles----
			vivero.agregarPlanta(limonero);
			vivero.agregarPlanta(olivo);
			vivero.agregarPlanta(higuera);
			
			vivero.venderPlanta(codigo3, cantidadAVenderDeLimoneros);
			vivero.venderPlanta(codigo4, cantidadAVenderDeOlivos);
			vivero.venderPlanta(codigo5, cantidadAVenderDeHigueras);
			// ---------------------------------------------------
			
			coleccionEsperada = vivero.obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta();
			
			System.out.println("Ordenados de menor a mayor: \n");
			for (Venta venta : coleccionEsperada) {                                        // hago el mismo calculo que hace el comparadorPersonalizado
				System.out.println("arbol: " + venta.getPlanta().getNombre() + " valor: " + (venta.getUnidades()*((Arbol)venta.getPlanta()).obtenerPrecio()));
			}
			
		}catch(ExceptionYaExisteUnaPlantaConEseId | ExceptionProductoSinStock | ExceptionPlantaInexistente e) {
			e.getMessage(); // --> puedo mostrar el mensaje segun la excepcion capturada
		}		
	}
	
	
	@Test 
	public void queMeDeElMetodoObtenerReporteDePlantasAgrupadasPorTipo(){
		String nombreVivero = "viverito";
		Vivero vivero = new Vivero(nombreVivero);
		
		Integer codigo1 = 1;
		String nombre1 = "Hierba";
		Double precioBase1 = 100.0;
		Integer stock1 = 10;
		Planta hierba = new Hierba(codigo1, nombre1, precioBase1, stock1);
		
		Integer codigo2 = 2;
		String nombre2 = "Arbusto";
		Double precioBase2 = 350.0;
		Integer stock2 = 5;
		Planta arbusto = new Arbusto(codigo2, nombre2, precioBase2, stock2);
		
		Integer codigo3 = 3;
		String nombre3 = "Limonero";
		Double precioBase3 = 1000.0;
		Integer stock3 = 24;
		Planta limonero = new Arbol(codigo3, nombre3, precioBase3, stock3);
		
		Integer codigo4 = 4;
		String nombre4 = "Olivo";
		Double precioBase4 = 1500.0;
		Integer stock4 = 12;
		Planta olivo = new Arbol(codigo4, nombre4, precioBase4, stock4);
		
		Integer codigo5 = 5;
		String nombre5 = "Higuera";
		Double precioBase5 = 2000.0;
		Integer stock5 = 5;
		Planta higuera = new Arbol(codigo5, nombre5, precioBase5, stock5);
		
		
		Map<String, Map<Integer, Planta>> coleccionObtenida;
		
		try {
			// ---- agrego y vendo plantas de DISTINTOS tipos ----
			vivero.agregarPlanta(hierba);
			vivero.agregarPlanta(arbusto);
			vivero.agregarPlanta(limonero);
			vivero.agregarPlanta(olivo);
			vivero.agregarPlanta(higuera);
			
			vivero.venderPlanta(codigo1, 5);
			vivero.venderPlanta(codigo2, 5);
			vivero.venderPlanta(codigo3, 10);
			vivero.venderPlanta(codigo4, 5); 
			vivero.venderPlanta(codigo5, 2);
			// ---------------------------------------------------
			
			coleccionObtenida = vivero.obtenerReporteDePlantasAgrupadasPorTipo();
			
//  CON ESTO, MUESTRO EL ARRAY OBTENIDO PERO LO COMENTO PQ USO SYSOS Y NO DEBO, MAS ABAJO ESTA HECHO CON ASSERTS

//			for (Map.Entry<String, Map<Integer, Planta>> entry : coleccionObtenida.entrySet()) {
//				String key = entry.getKey();
//				Map<Integer, Planta> val = entry.getValue();
//				
//				System.out.println("\nVentas de tipo: " + key + " -----------------------");
//				for (Map.Entry<Integer, Planta> otroEntry : val.entrySet()) {
//					Integer clave = otroEntry.getKey();
//					Planta valor = otroEntry.getValue(); 
//					
//					System.out.println("Clave: " + clave + "  -->  Nombre: " + valor.getNombre());
//				}
//			}
			
			Map<String, Map<Integer, Planta>> coleccionEsperada = new TreeMap<>();
			
			Map<Integer, Planta> ventaHierba = new TreeMap<>();		
			ventaHierba.put(1, hierba);
			
			Map<Integer, Planta> ventaArbusto = new TreeMap<>();	
			ventaArbusto.put(2, arbusto);
			
			Map<Integer, Planta> ventaArbol = new TreeMap<>();			
			ventaArbol.put(3, limonero);
			ventaArbol.put(4, olivo);
			ventaArbol.put(5, higuera);
			
			coleccionEsperada.put("HIERBA", ventaHierba);
			coleccionEsperada.put("ARBUSTO", ventaArbusto);
			coleccionEsperada.put("ARBOL", ventaArbol);
			
			// recorro la coleccion obtenida y verifico que cada map de cada value sea igual al esperado ------------
			for (Map.Entry<String, Map<Integer, Planta>> entry : coleccionObtenida.entrySet()) {
				String key = entry.getKey();
				Map<Integer, Planta> val = entry.getValue();
				
				if (key.equals("HIERBA")) { 
					assertEquals(val, ventaHierba);					
				}
				else if (key.equals("ARBUSTO")) {
					assertEquals(val, ventaArbusto);
				}
				else if(key.equals("ARBOL")) {
					assertEquals(val, ventaArbol);
				}
			}
			// ---------------------------------------------------------------------------------------------------------
			
			// aseguro que sean iguales las colecciones "generales" de todas las ventas
			assertEquals(coleccionEsperada, coleccionObtenida);
			
			
		}catch(ExceptionYaExisteUnaPlantaConEseId | ExceptionProductoSinStock | ExceptionPlantaInexistente e) {
			e.getMessage(); // --> puedo mostrar el mensaje segun la excepcion capturada
		}		
	}
	
	@Test 
	public void queMeDeElMetodoObtenerLasPlantasFlorales(){
		String nombreVivero = "viverito";
		Vivero vivero = new Vivero(nombreVivero);
		
		Integer codigo1 = 1;
		String nombre1 = "Hierba";
		Double precioBase1 = 100.0;
		Integer stock1 = 10;
		Planta hierba = new Hierba(codigo1, nombre1, precioBase1, stock1);
		
		Integer codigo2 = 2;
		String nombre2 = "Arbusto";
		Double precioBase2 = 350.0;
		Integer stock2 = 5;
		Planta arbusto = new Arbusto(codigo2, nombre2, precioBase2, stock2);
		
		Integer codigo3 = 3;
		String nombre3 = "Limonero";
		Double precioBase3 = 1000.0;
		Integer stock3 = 24;
		Planta limonero = new Arbol(codigo3, nombre3, precioBase3, stock3);
		
		Integer codigo4 = 4;
		String nombre4 = "Olivo";
		Double precioBase4 = 1500.0;
		Integer stock4 = 12;
		Planta olivo = new Arbol(codigo4, nombre4, precioBase4, stock4);
		
		Integer codigo5 = 5;
		String nombre5 = "Higuera";
		Double precioBase5 = 2000.0;
		Integer stock5 = 5;
		Planta higuera = new Arbol(codigo5, nombre5, precioBase5, stock5);
		
		List<Florales> coleccionObtenida = new ArrayList<>();
		List<Florales> coleccionEsperada = new ArrayList<>();
		
		try {
			// ---- solo agrego plantas de DISTINTOS tipos ----
			vivero.agregarPlanta(hierba);
			vivero.agregarPlanta(arbusto);
			vivero.agregarPlanta(limonero);
			vivero.agregarPlanta(olivo);
			vivero.agregarPlanta(higuera);
			// ---------------------------------------------------
			
			coleccionObtenida = vivero.obtenerTodasLasPlantasFlorales();
			
			coleccionEsperada.add((Florales)limonero);
			coleccionEsperada.add((Florales)olivo);
			coleccionEsperada.add((Florales)higuera);
			
			assertEquals(coleccionEsperada, coleccionObtenida);
			
			
		}catch(ExceptionYaExisteUnaPlantaConEseId e) {
			e.getMessage(); // --> puedo mostrar el mensaje segun la excepcion capturada
		}		
	}
	
	@Test
	public void queObtengaElPrecioDeUnaClaseEnEstadoDeFloracion() {
		String nombreVivero = "viverito";
		Vivero vivero = new Vivero(nombreVivero);
		
		Integer codigo3 = 3;
		String nombre3 = "Limonero";
		Double precioBase3 = 1000.0;
		Integer stock3 = 24;
		Planta limonero = new Arbol(codigo3, nombre3, precioBase3, stock3);
		
		Integer unidades = 4;
		Double precioObtenido; 
		Double precioEsperado;
		
		// aca por que lo fuerzo yo a que sea 100 por ciento, pero en realidad hice que sea automatico tirando un random pero esta comentado
		Double estadoDeFLoracion = 100.0;
		((Arbol) limonero).setEstadoFloracion(estadoDeFLoracion);
		
		
		try {
			// ---- agrego y vendo una planta de FLORALES para validar su precio ----
			vivero.agregarPlanta(limonero);
			vivero.venderPlanta(codigo3, unidades);
			// ----------------------------------------------------------------
			
			precioEsperado = (unidades*((Arbol) limonero).obtenerPrecio());
			precioObtenido = (vivero.buscarVentaPorId(1).getUnidades()* vivero.buscarVentaPorId(1).getPlanta().obtenerPrecio());
			
			assertEquals(precioEsperado, precioObtenido, 0.001);
			
		}catch(ExceptionYaExisteUnaPlantaConEseId | ExceptionPlantaInexistente | ExceptionProductoSinStock e) {
			e.getMessage(); // --> puedo mostrar el mensaje segun la excepcion capturada
		}	
	}
	
	@Test
	public void queObtengaElPrecioDeUnaClaseEnEstadoDeProduccion() {
		String nombreVivero = "viverito";
		Vivero vivero = new Vivero(nombreVivero);
		
		Integer codigo3 = 3;
		String nombre3 = "Limonero";
		Double precioBase3 = 1000.0;
		Integer stock3 = 24;
		Planta limonero = new Arbol(codigo3, nombre3, precioBase3, stock3);
		
		Integer unidades = 4;
		Double precioObtenido; 
		Double precioEsperado;
		
		// aca por que lo fuerzo yo a que sea 100 por ciento, pero en realidad hice que sea automatico tirando un random pero esta comentado
		Double estadoDeFLoracion = 100.0;
		((Arbol) limonero).setEstadoFloracion(estadoDeFLoracion);
		// y ademas le seteo yo mismo un estado de produccion pero tambien lo hice automatico je.
		((Arbol) limonero).setMadurezFrutos(5);
		
		
		try {
			// ---- agrego y vendo una planta de FLORALES para validar su precio ----
			vivero.agregarPlanta(limonero);
			vivero.venderPlanta(codigo3, unidades);
			// ----------------------------------------------------------------
			
			precioEsperado = (unidades*((Arbol) limonero).obtenerPrecio());
			precioObtenido = (vivero.buscarVentaPorId(1).getUnidades()* vivero.buscarVentaPorId(1).getPlanta().obtenerPrecio());
			
			assertEquals(precioEsperado, precioObtenido, 0.001);
			
		}catch(ExceptionYaExisteUnaPlantaConEseId | ExceptionPlantaInexistente | ExceptionProductoSinStock e) {
			e.getMessage(); // --> puedo mostrar el mensaje segun la excepcion capturada
		}	
	}
}
