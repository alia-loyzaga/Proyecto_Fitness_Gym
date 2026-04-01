package dominio;

/**
 * Clase que representa a una actividad del gimnasio
 */

public class Actividad {
	
	private String nombre;
	private String descripcion;
	private String nivel;
	private double precio;
	

	//constructores
	
	/**
	 * Constructor vacío.
	 */
	public Actividad() {
		
	}
	
	/**
	 * Constructor con parámetros
	 * @param nombre Nombre de la actividad.
	 * @param descripcion Descripcion breve de la actividad.
	 * @param nivel Nivel de ejecución de la actividad(básico, intermedio, avanzado).
	 * @param precio Precio de la actividad.
	 */
	
	public Actividad(String nombre, String descripcion, String nivel, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.precio = precio;
	}
	
	//Métodos de acceso
	
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // toString

    /**
     * Devuelve una representación en texto de la actividad.
     */
    @Override
    public String toString() {
        return "Actividad [nombre=" + nombre + ", descripcion=" + descripcion +  ", nivel=" + nivel +
                ", precio=" + precio + "]";
    }
}
