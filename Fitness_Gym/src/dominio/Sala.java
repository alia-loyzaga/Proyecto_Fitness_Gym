package dominio;

import java.util.ArrayList;

/**
 * Clase que representa una sala del gimnasio,
 * incluyendo su informacíon básica y su capacidad.
 * 
 * @author Alia
 * @version 1.0
 */

public class Sala {

	private String nombre;
	private double metrosCuadrados;
	private int aforoMaximo;
	private int id;
	
	private ArrayList<Maquina> listaMaquinas;
	private ArrayList<Actividad> listaActividadesPermitidas;
	
	
	//constructores

	public Sala() {
		
	}
	
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
