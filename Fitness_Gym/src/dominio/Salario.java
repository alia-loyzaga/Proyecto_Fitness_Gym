package dominio;

import java.time.LocalDate;

import enumerados.TipoContrato;

/**
 * Clase que representa el salario asociado a un entrenador
 * dentro del sistema Fitness Gym.
 * 
 * Gestiona la información económica relacionada con pagos
 * salariales según el tipo de contrato del empleado.
 * 
 * @author Alia
 * @version 1.0
 */
public class Salario {
	
	private double cantidad;
	private LocalDate fecha;
	private Entrenador entrenador;
	private double horas;
	
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
	/**
	 * Calcula el salario del entrenador en función
	 * de su tipo de contrato.
	 * 
	 * Los contratos a tiempo parcial se calculan
	 * según cantidad por horas trabajadas,
	 * mientras que los contratos completos
	 * mantienen una cantidad fija.
	 * 
	 * @return Salario calculado
	 */
	
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
