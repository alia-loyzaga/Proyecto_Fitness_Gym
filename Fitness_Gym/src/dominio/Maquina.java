package dominio;

import java.time.LocalDate;

import enumerados.EstadoMaquina;

/**
 * Clase que representa una máquina del gimnasio dentro del sistema.
 * Incluye lógica de dominio para la gestión de mantenimiento
 * y control operativo del equipamiento deportivo.
 * 
 * @author Alia
 * @version 1.0
 */
public class Maquina {
	
	private String tipo;
	private String marca;
	private String numeroSerie;
	private LocalDate fechaCompra;
	private LocalDate fechaUltimoMantenimiento;
	private EstadoMaquina estado;
	private Sala sala;
	private int mesUltimoMantenimiento;
	
	
	
	public Maquina() {
		
	}


	public Maquina(String tipo, String marca, String numeroSerie, Sala sala) {
	
		this.tipo = tipo;
		this.marca = marca;
		this.numeroSerie = numeroSerie;
		this.sala = sala;
	}




	public Maquina(String tipo, String marca, String numeroSerie, LocalDate fechaCompra,
			LocalDate fechaUltimoMantenimiento, EstadoMaquina estado, Sala sala) {
		
		this.tipo = tipo;
		this.marca = marca;
		this.numeroSerie = numeroSerie;
		this.fechaCompra = fechaCompra;
		this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
		this.estado = estado;
		this.sala = sala;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getNumeroSerie() {
		return numeroSerie;
	}


	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}


	public LocalDate getFechaCompra() {
		return fechaCompra;
	}


	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}


	public LocalDate getFechaUltimoMantenimiento() {
		return fechaUltimoMantenimiento;
	}


	public void setFechaUltimoMantenimiento(LocalDate fechaUltimoMantenimiento) {
		this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
	}


	public EstadoMaquina getEstado() {
		return estado;
	}


	public void setEstado(EstadoMaquina estado) {
		this.estado = estado;
	}


	public Sala getSala() {
		return sala;
	}


	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public int getMesUltimoMantenimiento() {
		return mesUltimoMantenimiento;
	}


	public void setMesUltimoMantenimiento(int mesUltimoMantenimiento) {
		this.mesUltimoMantenimiento = mesUltimoMantenimiento;
	}
	

	//Métodos de dominio
	

	/**
	 * Determina si la máquina necesita realizar mantenimiento
	 * en función de su antigüedad y del tiempo transcurrido
	 * desde la última revisión.
	 * 
	 * Las máquinas con menos de 3 años requieren mantenimiento anual,
	 * mientras que las más antiguas requieren revisiones cada 6 meses.
	 * 
	 * @return true si necesita mantenimiento, false en caso contrario
	 */
	public boolean necesitaMantenimiento() {

	    boolean necesita;
	    int antiguedad;
	    int mesesTranscurridos;

	    antiguedad = LocalDate.now().getYear() - fechaCompra.getYear();
	    mesesTranscurridos = LocalDate.now().getMonthValue() - mesUltimoMantenimiento;

	    if (antiguedad < 3) {

	        if (mesesTranscurridos >= 12) {
	            necesita = true;
	        } else {
	            necesita = false;
	        }

	    } else {

	        if (mesesTranscurridos >= 6) {
	            necesita = true;
	        } else {
	            necesita = false;
	        }

	    }

	    return necesita;
	}
	
	

	@Override
	public String toString() {
		return "Maquina [tipo=" + tipo + ", marca=" + marca + ", numeroSerie=" + numeroSerie + ", fechaCompra="
				+ fechaCompra + ", fechaUltimoMantenimiento=" + fechaUltimoMantenimiento + ", estado=" + estado
				+ ", sala=" + sala + "]";
	}
	
	
	
	

	
	
}
