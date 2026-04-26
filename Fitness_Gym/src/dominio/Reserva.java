package dominio;

import enumerados.EstadoReserva;


public class Reserva {
	
	private int id;
	private ActividadProgramada actividadProgramada;
	private Socio socio;
	private EstadoReserva estado; //pendiente o confirmada.
	
	//constructores
	
	/**
	 * Constructor vacío
	 */
	public Reserva() {
		
	}
	
	/**
	 * Constructor con parámetros
	 * @param actividadProgramada Actividad programada.
	 * @param socio Socio que realiza la reserva.
	 * @param estado Estado de la reserva.
	 */
	public Reserva(int id, ActividadProgramada actividadProgramada, Socio socio, EstadoReserva estado) {
		this.id = id;
		this.actividadProgramada = actividadProgramada;
		this.socio = socio;
		this.estado = estado;
	}
	
	/**
	 * Constructor básico
	 * @param actividadProgramada Actividad programada
	 * @param socio Socio que realiza la reserva
	 */
	
	public Reserva(ActividadProgramada actividadProgramada, Socio socio) {
		super();
		this.actividadProgramada = actividadProgramada;
		this.socio = socio;
	}
	
	

	//Métodos de acceso a atributos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ActividadProgramada getActividadProgramada() {
        return actividadProgramada;
    }

    

	public void setActividadProgramada(ActividadProgramada actividadProgramada) {
        this.actividadProgramada = actividadProgramada;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    /**
     * Representación en texto de la reserva.
     */
    @Override
    public String toString() {
        return "Reserva [actividad=" + actividadProgramada +
               ", socio=" + socio +
               ", estado=" + estado + ", id = " + id + "]";
    }
    
    //Métodos de dominio
   
    
    
    public void cancelarReserva() {
        estado = EstadoReserva.CANCELADA;
    }
    
    
    public void confirmarReserva() {
        estado = EstadoReserva.CONFIRMADA;
    }
    

}
