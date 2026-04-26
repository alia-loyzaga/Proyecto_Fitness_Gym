package dominio;

import java.time.LocalDate;
import java.util.List;

import enumerados.EstadoEntrenador;
import enumerados.TipoContrato;

/**
 * Clase que representa a un entrenador del gimnasio.
 * Hereda de usuario y contiene información específica.
 */

public class Entrenador extends Usuario {
	

	private LocalDate fechaAlta;
	private TipoContrato tipoContrato; //tiempo completo o parcial
	private EstadoEntrenador estado; //baja o activo
	private List<String>listaActividades;
	
	
	/**
	 * Constructor vacío
	 */
	
	public Entrenador() {
		
	}
	
	
	/**
	 * Constructos con parametros
	 * @param dni DNI del entrenador.
	 * @param nombre Nombre del entrenador.
	 * @param apellido1 Primer apellido del entrenador.
	 * @param apellido2 Segundo apellido del entrenador.
	 * @param telefono Telefono del entrenador.
	 * @param email Correo electrónico del entrenador.
	 * @param fechaAlta Fecha de alta en el sistema del entrenador.
	 * @param tipoContrato Tipo de contrato, tiempo completo o parcial.
	 * @param estado Estado del entrenador (activo o baja)
	 */

	public Entrenador(String dni, String nombre, String apellido1, String apellido2, String telefono, String email, LocalDate
			 fechaAlta, TipoContrato tipoContrato, EstadoEntrenador estado) {
		super(dni, nombre, apellido1, apellido2, telefono, email);
		this.fechaAlta = fechaAlta;
		this.tipoContrato = tipoContrato;
		this.estado = estado;
		
	}
	
	/**
	 * Constructo básico de entrenador
	 * @param dni DNI 
	 * @param nombre Nombre
	 * @param apellido1 Primer apellido
	 * @param apellido2 Segundo apellido
	 */
	
	public Entrenador(String dni, String nombre, String apellido1, String apellido2) {
		super(dni, nombre, apellido1, apellido2, null, null);
		
	
		
	}
	
	

	
	//Métodos de acceso.

	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}


	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}


	public EstadoEntrenador getEstado() {
		return estado;
	}


	public void setEstado(EstadoEntrenador estado) {
		this.estado = estado;
	}


	public List<String> getListaActividades() {
		return listaActividades;
	}


	public void setListaActividades(List<String> listaActividades) {
		this.listaActividades = listaActividades;
	}
	



	@Override
	public String toString() {
		return "Entrenador [ " +  super.toString() + "fechaAlta=" + fechaAlta + ", tipoContrato=" + tipoContrato + ", estado=" + estado
				+ ", listaActividades=" + listaActividades + "]";
	}


	





}
