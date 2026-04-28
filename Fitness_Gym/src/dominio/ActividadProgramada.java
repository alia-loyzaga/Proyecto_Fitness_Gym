package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Clase que representa una actividad programada dentro del sistema Fitness Gym.
 * 
 * Relaciona una actividad concreta con una sala, un entrenador
 * y un horario determinado, permitiendo gestionar la planificación
 * de sesiones deportivas del gimnasio.
 * 
 * Incluye lógica básica para el control de plazas disponibles
 * y organización de actividades programadas.
 * 
 * @author Alia
 * @version 1.0
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
	
	public ActividadProgramada() {
		
	}
	
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
	
	public ArrayList<Reserva> getListaReservas() {
	    return listaReservas;
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
    /**
     * Calcula las plazas libres disponibles de la actividad programada.
     * 
     * Actualmente, en esta versión simplificada del sistema,
     * las plazas libres se igualan al aforo máximo de la sala
     * para facilitar la generación de datos y exportación JSON.
     * 
     * En futuras versiones, este cálculo deberá ajustarse
     * restando las reservas realizadas al aforo total.
     * 
     * @return Número de plazas libres disponibles
     */
    
    public int getPlazasLibres() {

        int plazasLibres;

        plazasLibres = sala.getAforoMaximo();

        return plazasLibres;
    }
 
    /**
     * Comprueba si la actividad programada dispone de plazas disponibles.
     * 
     * @return true si hay plazas libres, false en caso contrario
     */
    
    public boolean hayPlazasDisponibles() {

        boolean hayPlazas;

        if (getPlazasLibres() > 0) {
            hayPlazas = true;
        } else {
            hayPlazas = false;
        }

        return hayPlazas;
    }
    

}
