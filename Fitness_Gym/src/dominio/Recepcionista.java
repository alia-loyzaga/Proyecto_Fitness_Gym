package dominio;

/**
 * Clase que representa a un/a recepcionista del gimnasio.
 * Hereda de usuario.
 * 
 * @author Alia
 * @version 1.0
 */

public class Recepcionista extends Usuario {
	
	public Recepcionista() {
		
	}

	public Recepcionista(String dni, String nombre, String apellido1, String apellido2, String telefono, String email) {
		super(dni, nombre, apellido1, apellido2, telefono, email);
		
	}
	

	public Recepcionista(String dni, String nombre, String apellido1, String apellido2) {
		super(dni, nombre, apellido1, apellido2, null, null);
		
	}



	@Override
	public String toString() {
		return "Recepcionista [ " + super.toString() + "]";
	}


	
	
	
	
	

	
	
	

}
