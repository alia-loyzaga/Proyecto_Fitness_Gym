package dominio;

import java.time.LocalDateTime;

/**
 * Clase que representa una actividad programada del gimnasio.
 */
public class ActividadProgramada {
	
	private Actividad actividad;
	private Sala sala;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	
	
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
			LocalDateTime fechaHoraFin) {
		this.actividad = actividad;
		this.sala = sala;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
	}
	
	//Métodos de acceso a atributos
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

}
