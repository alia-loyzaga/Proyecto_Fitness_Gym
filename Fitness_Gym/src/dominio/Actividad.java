package dominio;

import enumerados.Nivel;

/**
 * Clase que representa una actividad deportiva dentro del sistema Fitness Gym.
 * 
 * Define la información básica de cada actividad, incluyendo su identificación,
 * nombre, descripción, nivel de dificultad y precio.
 * 
 * Sirve como entidad de dominio para la gestión de actividades
 * ofrecidas por el gimnasio.
 * 
 * @author Alia
 * @version 1.0
 */

public class Actividad {
	
	private int id;
	private String nombre;
	private String descripcion;
	private Nivel nivel;
	private double precio;
	

	//constructores
	
	
	public Actividad() {
		
	}
	
	
	
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
    
    // toString

    

	@Override
	public String toString() {
		return "Actividad [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel=" + nivel
				+ ", precio=" + precio + "]";
	}

    
   
}
