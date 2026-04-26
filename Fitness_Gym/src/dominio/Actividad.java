package dominio;

import enumerados.Nivel;

/**
 * Clase que representa a una actividad del gimnasio
 */

public class Actividad {
	
	private int id;
	private String nombre;
	private String descripcion;
	private Nivel nivel;
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
	
	public Actividad(int id,String nombre, String descripcion, Nivel nivel, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.precio = precio;
	}
	
	//Métodos de acceso

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

	@Override
	public String toString() {
		return "Actividad [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel=" + nivel
				+ ", precio=" + precio + "]";
	}

    // toString

    /**
     * Devuelve una representación en texto de la actividad.
     */
   
}
