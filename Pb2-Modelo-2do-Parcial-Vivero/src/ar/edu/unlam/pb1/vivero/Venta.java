package ar.edu.unlam.pb1.vivero;

public class Venta {

	private Integer id;
	private Integer unidades;
	private Planta planta;
	public Double precioUnitario; // Precio final de la planta al momento de realizar la venta
	
	public Venta(Integer idDeVenta, Integer cantidadVendidas, Planta plantaVendida, Double precioBase) {
		this.id = idDeVenta;
		this.unidades = cantidadVendidas;
		this.planta = plantaVendida;
		this.precioUnitario = precioBase;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
}
