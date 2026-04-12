package dominio;
/**
 * Clase que representa una sala del gimnasio.
 */

public class Sala {

	private String nombre;
	private int metrosCuadrados;
	private int aforoMaximo;
	
	//constructores
	/**
	 * Constructor vacío
	 */
	public Sala() {
		
	}
	
	/**
	 * Constructor con parámetros
	 * @param nombre Nombre de la sala.
	 * @param metrosCuadrados Tamaño de la sala en metros cuadrados.
	 * @param aforoMaximo Capacidad máxima de personas en la sala.
	 */
	
	public Sala(String nombre, int metrosCuadrados, int aforoMaximo) {
		this.nombre = nombre;
		this.metrosCuadrados = metrosCuadrados;
		this.aforoMaximo = aforoMaximo;
	}
	
	//Métodos de acceso a atributos
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    // toString
    /**
     * Devuelve una respresentación en texto de la sala.
     */

    @Override
    public String toString() {
        return "Sala [nombre=" + nombre + ", metrosCuadrados=" + metrosCuadrados +
               ", aforoMaximo=" + aforoMaximo + "]";
    }
	
}
