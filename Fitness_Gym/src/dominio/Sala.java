package dominio;

import java.util.ArrayList;

/**
 * Clase que representa una sala del gimnasio.
 */

public class Sala {

	private String nombre;
	private double metrosCuadrados;
	private int aforoMaximo;
	private int id;
	
	private ArrayList<Maquina> listaMaquinas;
	private ArrayList<Actividad> listaActividadesPermitidas;
	
	
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
	
	public Sala(String nombre, double metrosCuadrados, int aforoMaximo) {
		this.nombre = nombre;
		this.metrosCuadrados = metrosCuadrados;
		this.aforoMaximo = aforoMaximo;
		this.listaMaquinas = new ArrayList<>();
		this.listaActividadesPermitidas = new ArrayList<>();
	}
	
	//Métodos de acceso a atributos
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public double getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(double metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    

    @Override
	public String toString() {
		return "Sala [nombre=" + nombre + ", metrosCuadrados=" + metrosCuadrados + ", aforoMaximo=" + aforoMaximo
				+ ", id=" + id + ", listaMaquinas=" + listaMaquinas + ", listaActividadesPermitidas="
				+ listaActividadesPermitidas + "]";
	}
    
    

	
    
	
}
