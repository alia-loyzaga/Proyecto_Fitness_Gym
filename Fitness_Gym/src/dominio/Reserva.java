package dominio;

import enumerados.EstadoReserva;

/**
 * Clase que representa una reserva de actividad realizada por un socio
 * dentro del sistema Fitness Gym.
 * 
 * Gestiona la relación entre el socio y la actividad programada,
 * así como el estado de la reserva durante su ciclo de vida
 * (confirmación o cancelación).
 * 
 * @author Alia
 * @version 1.0
 */
public class Reserva {
	
	private int id;
	private ActividadProgramada actividadProgramada;
	private Socio socio;
	private EstadoReserva estado; //pendiente o confirmada.
	
	//constructores
	
	
	public Reserva() {
		
	}
	
	public Reserva(int id, ActividadProgramada actividadProgramada, Socio socio, EstadoReserva estado) {
		this.id = id;
		this.actividadProgramada = actividadProgramada;
		this.socio = socio;
		this.estado = estado;
	}
	
	
	
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

   
    @Override
    public String toString() {
        return "Reserva [actividad=" + actividadProgramada +
               ", socio=" + socio +
               ", estado=" + estado + ", id = " + id + "]";
    }
    
    //Métodos de dominio
   
    
    /**
     * Cancela la reserva actual cambiando su estado a cancelada.
     */
    public void cancelarReserva() {
        estado = EstadoReserva.CANCELADA;
    }
    
    /**
     * Confirma la reserva actual cambiando su estado a confirmada.
     */
    
    public void confirmarReserva() {
        estado = EstadoReserva.CONFIRMADA;
    }
    

}
