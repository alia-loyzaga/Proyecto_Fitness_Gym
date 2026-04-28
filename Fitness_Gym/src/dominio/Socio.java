package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enumerados.EstadoSocio;
import enumerados.TipoPlan;

/**
 * Clase que representa a un socio del gimnasio.
 * Hereda de usuario y contiene información específica.
 * 
 * @author Alia
 * @version 1.0
 */

public class Socio extends Usuario {

	private LocalDate fechaAlta;
	private TipoPlan tipoPlan; //full o flexible
	private EstadoSocio estado; //activo, moroso, baja
	private List<Reserva>listaReservas;
	
	//construtores
	
	public Socio() {
		
	}

	
	public Socio(String dni, String nombre, String apellido1, String apellido2, String telefono,
			String email, LocalDate fechaAlta, TipoPlan tipoPlan, EstadoSocio estado ) {

		super(dni, nombre, apellido1,apellido2, telefono, email);
		this.fechaAlta = fechaAlta;
		this.tipoPlan = tipoPlan;
		this.estado = estado;
		this.listaReservas = new ArrayList<>();
		
		
	}
	
	public Socio(String dni, String nombre, String apellido1, String apellido2) {
		super(dni, nombre, apellido1, apellido2, null, null);
		this.listaReservas = new ArrayList<>();
		
	}
	
	//Getters y Setters;
	
	 	public LocalDate getFechaAlta() {
			return fechaAlta;
		}
	 	
		public void setFechaAlta(LocalDate fechaAlta) {
			this.fechaAlta = fechaAlta;
		}
		
		public TipoPlan getTipoPlan() {
			return tipoPlan;
			
		}
		public void setTipoPlan(TipoPlan tipoPlan) {
			this.tipoPlan = tipoPlan;
		}
		
		public EstadoSocio getEstado() {
			return estado;
		}
		
		public void setEstado(EstadoSocio estado) {
			this.estado = estado;
		}
		
		public List<Reserva> getListaReservas() {
			return listaReservas;
		}
		
		public void setListaReservas(List<Reserva> listaReservas) {
			this.listaReservas = listaReservas;
		}
 
    
    //Métodos simplificados
    /**
     * Indica si el socio es moroso.
     * En esta versión inicial se basa en el atributo estado.
     * @return true si el socio es moroso, false en caso contrario
     */
    public boolean esMoroso() {
    	return estado == EstadoSocio.MOROSO;
    	
    }
    
   
	/**
     * Actualiza el estado del socio.
     * @param nuevoEstado Nuevo estado del socio
     */
    
    public void actualizarEstado(EstadoSocio nuevoEstado) {
    	estado = nuevoEstado;
    }
    
    /**
     * Indica si tiene una reserva de una actividad en una hora determinada
     * @return true si la tiene, false en caso contrario.
     */
   
    	public boolean tieneReservaEnHorario(ActividadProgramada nueva) {

    	    boolean tiene = false;

    	    for (Reserva r : listaReservas) {

    	        ActividadProgramada existente = r.getActividadProgramada();

    	        if (nueva.getFechaHoraInicio().compareTo(existente.getFechaHoraFin()) < 0 &&
    	            nueva.getFechaHoraFin().compareTo(existente.getFechaHoraInicio()) > 0) {

    	            tiene = true;
    	        }
    	    }

    	    return tiene;
    	}
    
	
   /**
    * Resprentacion en texto de Socio.
    */
    @Override
    public String toString() {
    	return "Socio [dni" + getDni() + ", Nombre: " + getNombre() + ", Apellidos: "
    			+ getApellido1() + getApellido2() + ", Teléfono: " + getTelefono()  + ", Email: " + 
    			getEmail() + ", Tipo de plan: " + tipoPlan + ", estado: " + estado + "]";
    	
    }
	
}
