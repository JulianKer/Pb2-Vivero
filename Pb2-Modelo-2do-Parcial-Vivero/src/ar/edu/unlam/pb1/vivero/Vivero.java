package ar.edu.unlam.pb1.vivero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Vivero {
	
	/**
	 * Se deberan realizar los siguientes tests
	 * 
	 * - ---------------- 1 test para el metodo agregarPlanta() que arroje la excepcion de validacion
	 * - ---------------- 1 test para el metodo venderPlanta() que arroje una excepcion a eleccion
	 * - ---------------- 1 test para el metodo obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta()
	 * - ---------------- 1 test para el metodo obtenerReporteDePlantasAgrupadasPorTipo()
	 * - ---------------- 1 test para el metodo obtenerTodasLasPlantasFlorales()
	 * - 1 test para el metodo obtenerPrecio() de la clase Planta  // ESTE NO SE PORQUE LO PIDE, yo lo hice abstracto ese metodo asiq una clase "planta" no va a hacer nada si llamo a este metodo xd
	 * - ---------------- 1 test para el metodo obtenerPrecio() de alguna clase que implemente la interfaz Florales en estado de floracion
	 * - ---------------- 1 test para el metodo obtenerPrecio() de alguna clase que implemente la interfaz Florales en estado de produccion
	 * */

	private String nombre;

	// No se pueden registrar plantas duplicadas. 2 plantas son iguales cuando tiene
	// el mismo Id
	private Set<Planta> plantas;
	private List<Venta> ventas;

	public Vivero(String nombre) {
		this.nombre = nombre;
		this.plantas = new HashSet<>();
		this.ventas = new ArrayList<>();
	}

	// No puede haber 2 plantas con el mismo Id , Si se duplica lanza una Exception
	// Planta Duplicada Exception
	
	public Boolean agregarPlanta(Planta planta) throws ExceptionYaExisteUnaPlantaConEseId {
		if (this.plantas.add(planta)) {
			return this.plantas.contains(planta);
		}
		throw new ExceptionYaExisteUnaPlantaConEseId("Ya existe una planta con el mismo ID.");
	}

	
	/*
	 * Registra una venta y descuenta del stock de la planta la cantidad deseada. Si no se encuentra la planta lanza
	 * una exception Planta Inexistente. Si no hay Stock Lanza Una Exception
	 * ProdutoSinStockException
	 */
	public void venderPlanta(Integer codigoPlanta, Integer cantidadAVender) throws ExceptionPlantaInexistente, ExceptionProductoSinStock{
		Integer idDeVenta = this.ventas.size()+1;
		Double precioUnitarioFinal = 0.0;
		
		Planta plantaEncontrada = buscarPlantaPorCodigo(codigoPlanta);
		plantaEncontrada.restarAlStock(cantidadAVender);
		
		if (plantaEncontrada instanceof Hierba) {
			precioUnitarioFinal = ((Hierba) plantaEncontrada).obtenerPrecio();
			
		}else if (plantaEncontrada instanceof Arbusto) {
			precioUnitarioFinal = ((Arbusto) plantaEncontrada).obtenerPrecio();
			
		}else {
			precioUnitarioFinal = ((Arbol) plantaEncontrada).obtenerPrecio();
		}
			
		Venta ventaNueva = new Venta(idDeVenta, cantidadAVender, plantaEncontrada, precioUnitarioFinal);
		this.ventas.add(ventaNueva);
	} 

	
	public Planta buscarPlantaPorCodigo(Integer codigoPlanta) throws ExceptionPlantaInexistente{
		for (Planta planta : this.plantas) {
			if (planta.getCodigo().equals(codigoPlanta)) {
				return planta;
			}
		}
		throw new ExceptionPlantaInexistente("Esa planta NO existe.");
	}

	
	
	/*
	 * Obtener un listado de todos los arboles vendidos ordenados por el total de
	 * venta (Cantidad * precioDeLaPlanta)
	 * 
	 */
	public Set<Venta> obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta() {
		Set<Venta> ventasDeArbolesOrdenadosPorValorDeVenta = new TreeSet<>(new ComparadorPersonalizado());
		
		for (Venta venta : this.ventas) {                            // de cada venta 
			if (venta.getPlanta() instanceof Arbol) {                // si la planta esta instanciada como "Arbol",
				ventasDeArbolesOrdenadosPorValorDeVenta.add(venta);  // la agrego a la coleccion que voy a devolver,
			}                                                        // que las ordena por el total de la venta.
		}
		return ventasDeArbolesOrdenadosPorValorDeVenta;
	}
	
	

	/*
	 * Obtener Un reporte de las plantas vendidas agrupados por tipo Plantas
	 * 
	 * 
	 * Ejemplo: para una key "arbol", debemos completar las plantas de este tipo
	 * 
	 */

	public Map<String, Map<Integer, Planta>> obtenerReporteDePlantasAgrupadasPorTipo() {
		
		Map<String, Map<Integer, Planta>> reporteADevolver = new TreeMap<>();
		
		Map<Integer, Planta> ventasHierba = new TreeMap<>();
		Map<Integer, Planta> ventasArbusto = new TreeMap<>();
		Map<Integer, Planta> ventasArbol = new TreeMap<>();
		
		
		for (Venta venta : this.ventas) {
			if (venta.getPlanta() instanceof Hierba) {
				ventasHierba.put(venta.getPlanta().getCodigo(), venta.getPlanta()); // de key uso el codigo de la planta
				
			} 
			else if (venta.getPlanta() instanceof Arbusto) {
				ventasArbusto.put(venta.getPlanta().getCodigo(), venta.getPlanta()); // de key uso el codigo de la planta
				
			}
			else if (venta.getPlanta() instanceof Arbol) {
				ventasArbol.put(venta.getPlanta().getCodigo(), venta.getPlanta()); // de key uso el codigo de la planta
			}
		}
		String claveHierba = "HIERBA";
		String claveArbusto = "ARBUSTO";
		String claveArbol = "ARBOL";
		
		reporteADevolver.put(claveHierba, ventasHierba);  // de key uso el string de HIERBA
		reporteADevolver.put(claveArbusto, ventasArbusto);  // de key uso el string de ARBUSTO
		reporteADevolver.put(claveArbol, ventasArbol);  // de key uso el string de ARBOL
		
		return reporteADevolver;
	}

	/**
	 * Obtener una lista de plantas que implementen la interfaz correspondiente
	 * */
	public List<Florales> obtenerTodasLasPlantasFlorales() {
		List<Florales> plantasFlorales = new ArrayList<>();
		
		for (Planta planta : this.plantas) {
			if (planta instanceof Florales) {
				plantasFlorales.add((Florales)planta);
			}
		}
		return plantasFlorales;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// getters y setters -------------------------------------------------------
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(Set<Planta> plantas) {
		this.plantas = plantas;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	// este lo hago para poder llamarlo desde el test
	public Venta buscarVentaPorId(Integer id) {
		for (Venta venta : this.ventas) {
			if (venta.getId().equals(id)) {
				return venta;
			}
		}
		return null;
	}
}
