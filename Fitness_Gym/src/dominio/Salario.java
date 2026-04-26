package dominio;

import java.time.LocalDate;

import enumerados.TipoContrato;

public class Salario {
	
	private double cantidad;
	private LocalDate fecha;
	private Entrenador entrenador;
	private double horas;
	
	/**
	 * Constructo con parámetros 
	 * @param cantidad Cantidad a percibir para un entrenador
	 * @param fecha
	 * @param entrenador Entrenador que recibe el salario
	 * @param horas Horas trabajadas si es jornada parcial
	 */
	public Salario(double cantidad, LocalDate fecha, Entrenador entrenador, double horas) {
		
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.entrenador = entrenador;
		this.horas = horas;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public double getHoras() {
		return horas;
	}

	public void setHoras(double horas) {
		this.horas = horas;
	}
	
	
	//Métodos de dominio
	
	public double calcularSalario() {

	    double salarioCalculado;

	    if (entrenador.getTipoContrato() == TipoContrato.TIEMPO_PARCIAL) {
	        salarioCalculado = cantidad * horas;
	    } else {
	        salarioCalculado = cantidad;
	    }

	    return salarioCalculado;
	}

	@Override
	public String toString() {
		return "Salario [cantidad=" + cantidad + ", fecha=" + fecha + ", entrenador=" + entrenador + "]";
	}
	
	
	

}
