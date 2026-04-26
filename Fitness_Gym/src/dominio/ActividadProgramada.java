package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Clase que representa una actividad programada del gimnasio.
 */
public class ActividadProgramada {
	
	private int id;
	private Actividad actividad;
	private Sala sala;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private Entrenador entrenador;
	
	// Esta lista se utiliza a nivel de modelo,
	// pero en la implementación real las reservas
	// se gestionan mediante base de datos (DAO).
	private ArrayList<Reserva> listaReservas;
	
	
	//constructores
	
	/**Constructor vacío
	 * 
	 */
	
	public ActividadProgramada() {
		
	}
	
	/**
	 * Constructor con parametros
	 * @param actividad Actividad que se realiza en el gimnasio.
	 * @param sala Sala del gimnasio.
	 * @param fechaHoraInicio Fecha y hora de inicio de la actividad.
	 * @param fechaHoraFin Fecha y hora de fin de la actividad.
	 */
	
	public ActividadProgramada(Actividad actividad, Sala sala, LocalDateTime fechaHoraInicio,
			LocalDateTime fechaHoraFin, Entrenador entrenador) {
		this.actividad = actividad;
		this.sala = sala;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.listaReservas = new ArrayList<>();
		this.entrenador = entrenador;
	}
	
	//Métodos de acceso a atributos
	 public int getId() {
			return id;
		}

	public void setId(int id) {
			this.id = id;
		}
	
	public Actividad getActividad() {
        return actividad;
    }

	public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    
    public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	/**
     * Devuelve una representación en texto de la actividad programada;
     */
    @Override
    public String toString() {
        return "ActividadProgramada [actividad=" + actividad +
               ", sala=" + sala +
               ", inicio=" + fechaHoraInicio +
               ", fin=" + fechaHoraFin + "]";
    }
    
    
    //Métodos de dominio
    
    public boolean hayPlazasDisponibles() {

        boolean hayPlazas;

        if (listaReservas.size() < sala.getAforoMaximo()) {
            hayPlazas = true;
        } else {
            hayPlazas = false;
        }

        return hayPlazas;
    }
    
//Durante la implementación se refinaron las responsabilidades del modelo de dominio para adaptarlas a una arquitectura por capas más coherente, trasladando la lógica de coordinación de reservas a la capa de gestión y simplificando la entidad ActividadProgramada para mantener únicamente comportamiento intrínseco.

}
