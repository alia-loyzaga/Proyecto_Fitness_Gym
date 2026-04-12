package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un socio del gimnasio.
 * Hereda de usuario y contiene información específica.
 */

public class Socio extends Usuario {

	private LocalDate fechaAlta;
	private String tipoPlan; //full o flexible
	private String estado; //activo, moroso, baja
	private List<String>listaReservas;
	
	//construtores
	/**
	 * Constructor vacío
	 */
	public Socio() {
		
	}
	/**
	 * Constructor con parámetros
	 * @param dni Dni del socio.
	 * @param nombre Nombre del socio.
	 * @param apellido1 Apellidos del socio(apellido1, apellido2).
	 * @param apellido2 
	 * @param telefono Teléfono de contacto.
	 * @param email Correo electrónico.
	 * @param fechaAlta Fecha de alta en el sistema del socio.
	 * @param tipoPlan Tipo de plan contratado.
	 * @param estado Estado del socio (activo, baja, moroso).
	 */
	
	public Socio(String dni, String nombre, String apellido1, String apellido2, String telefono,
			String email, LocalDate fechaAlta, String tipoPlan, String estado ) {

		super(dni, nombre, apellido1,apellido2, telefono, email);
		this.fechaAlta = fechaAlta;
		this.tipoPlan = tipoPlan;
		this.estado = estado;
		this.listaReservas = new ArrayList<>();
		
		
	}
	/**
	 * Constructor básico de socio
	 * @param dni Dni del socio
	 * @param nombre Nombre del socio
	 * @param apellido1 Primer apellido
	 * @param apellido2 Segundo apellido
	 */
	public Socio(String dni, String nombre, String apellido1, String apellido2) {
		super(dni, nombre, apellido1, apellido2, null, null);
		
	}
	
	//Métodos de acceso a atributos;
	public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<String> listaReservas) {
        this.listaReservas = listaReservas;
    }
    
    //Métodos simplificados
    /**
     * Indica si el socio es moroso.
     * En esta versión inicial se basa en el atributo estado.
     * @return true si el socio es moroso, false en caso contrario
     */
    public boolean esMoroso() {
    	return estado.equals("Moroso");
    	
    }
    
    /**
     * Actualiza el estado del socio.
     * @param nuevoEstado Nuevo estado del socio
     */
    public void actualizarEstado(String nuevoEstado) {
    	estado = nuevoEstado;
    }
    
    /**
     * Indica si tiene una reserva de una actividad en una hora determinada
     * @return true si la tiene, false en caso contrario.
     */
    public boolean tieneReservaEnHorario() {
    	return false;
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
