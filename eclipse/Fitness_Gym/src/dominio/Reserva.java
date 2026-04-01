package dominio;

public class Reserva {
	
	private ActividadProgramada actividadProgramada;
	private Socio socio;
	private String estado; //pendiente o confirmada.
	
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
	public Reserva(ActividadProgramada actividadProgramada, Socio socio, String estado) {
		this.actividadProgramada = actividadProgramada;
		this.socio = socio;
		this.estado = estado;
	}
	
	//Métodos de acceso a atributos
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Representación en texto de la reserva.
     */
    @Override
    public String toString() {
        return "Reserva [actividad=" + actividadProgramada +
               ", socio=" + socio +
               ", estado=" + estado + "]";
    }

}
