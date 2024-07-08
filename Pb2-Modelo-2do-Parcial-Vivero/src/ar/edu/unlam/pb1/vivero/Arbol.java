package ar.edu.unlam.pb1.vivero;

public class Arbol extends Planta implements Florales{

	private final Double GANANCIA_ARBOL = 2.3;
	private Double estadoFloracion;
	private Integer madurezFrutos;

	public Arbol(Integer codigo, String nombre, Double precio, Integer stock) {
		super(codigo, nombre, precio, stock);
		this.estadoFloracion = 0.0;
		this.madurezFrutos = 0;	
	}
	
	/**
	 * Los arboles pueden dar flores, las que posteriormente se convertiran en frutos. 
	 * Las flores tienen un rango de crecimiento, siendo 0 (el valor inicial) cuando no tiene flores aun y 100 cuando ya estan aptas para dar frutos.
	 * El precio del arbol se incrementa de acuerdo al avance de la floracion:
	 * - Menos de 33% de floracion incrementa un 5% su precio. 
	 * - Entre 34% y 66% incrementa un 7.5% su precio.
	 * - Mas del 66% y menos de 100% incrementa un 8.5% su precio.
	 * - Cuando el estado de floracion llega al 100%, se comienza la produccion de frutos
	 * */

	/**
	 * Para poder producir frutos, el estado de floracion debe ser 100%.
	 * La produccion de frutos se mide entre 1 y 5 siendo 5 el mejor escenario.
	 * Cuando un arbol produce frutos, su precio aumenta 10% inicialmente (por tener el estado de floracion al 100%) y
	 * agrega al precio, el porcentaje de madurez. Ejemplo: precioBase = 100 + 10% (por floracion) + 3% (madurez actual de los frutos)
	 * */
	
	@Override
	public Double obtenerPrecio() {
		Double precio = getPrecioBase();
		
//		florar();    ---> este lo comento pero deberia estar descomentado para que cuando pida el precio, me da un estado de floracion
		
		if (this.estadoFloracion > 0.0) { // si es mayor a 0 es pq tiene un porcentaje de floracion
			
			if (this.estadoFloracion < 33.0) {
				precio += (precio*(5.0/100)); // 5 %
			} 
			else if (this.estadoFloracion > 33.0 && this.estadoFloracion <= 66.0) {
				precio += (precio*(7.5/100)); // 7.5 %
			} 
			else if (this.estadoFloracion > 66.0 && this.estadoFloracion <= 100.0) {
				precio += (precio*(8.5/100)); // 8.5 %
			}
		}
		
		if (this.estadoFloracion.equals(100.0)) {
//			producirFrutos();  ---> lo comento para poder hacer un test pasandole el valor q quiero, pero deberia estar descomentado asi me tira un random
			precio += (precio*(10.0/100)); // ya por estar al 100% , vale un 10% más.
			precio += (precio*(this.madurezFrutos/100)); // y segun el porcentaje de la madurez, se le suma lo mismo al precio
		}
		
		precio += (precio*(this.GANANCIA_ARBOL/100)); // ganancia final
		return precio;
	}

	
	// metodos de la interface que tiene implrementada
	@Override
	public void florar() {
		this.estadoFloracion = (double)(int)(Math.random()*(100))+1; // --> lo modifique, primero lo casteo a int para quedarme 
	}                                                 // con la parte entera y desp a double para agregarle el ".0"

	@Override
	public void producirFrutos() {
		this.madurezFrutos = (int) (Math.random()*(5)+1); 
	}

	
	// getters y setters--------------------
	public Double getEstadoFloracion() {
		return estadoFloracion;
	}

	public void setEstadoFloracion(Double estadoFloracion) {
		this.estadoFloracion = estadoFloracion;
	}

	public Integer getMadurezFrutos() {
		return madurezFrutos;
	}

	public void setMadurezFrutos(Integer madurezFrutos) {
		this.madurezFrutos = madurezFrutos;
	}	
}
