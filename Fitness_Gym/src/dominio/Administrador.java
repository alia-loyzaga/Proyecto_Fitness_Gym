package dominio;

/**
 * Clase que representa a un administrador del gimnasio.
 * Hereda de usuario.
 * 
 *@author Alia
 *@version 1.0
 */

public class Administrador extends Usuario {
	
	
	public Administrador() {
		
	}


	public Administrador(String dni, String nombre, String apellido1, String apellido2, String telefono, String email) {
		super(dni, nombre, apellido1, apellido2, telefono, email);
		
	}
	
	
	public Administrador(String dni, String nombre, String apellido1, String apellido2) {
		super(dni, nombre, apellido1, apellido2, null, null);
		
	}

	@Override
	public String toString() {
		return "Administrador [ " + super.toString() + "]";
	}

	

	
}
