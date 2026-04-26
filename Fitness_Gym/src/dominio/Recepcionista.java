package dominio;

/**
 * Clase que representa a un/a recepcionista del gimnasio.
 * Hereda de usuario.
 */

public class Recepcionista extends Usuario {
	
	/**
	 * Constructor vacío
	 */

	public Recepcionista() {
		
	}

	
	/**
	 * Constructor con parámetros
	 * @param dni DNI 
	 * @param nombre Nombre
	 * @param apellido1 Primer apellido
	 * @param apellido2 Segundo apellido
	 * @param telefono Telefono de contacto
	 * @param email Correo electronico
	 */
	public Recepcionista(String dni, String nombre, String apellido1, String apellido2, String telefono, String email) {
		super(dni, nombre, apellido1, apellido2, telefono, email);
		
	}
	
	/**
	 * Constructo básico 
	 * @param dni DNI
	 * @param nombre Nombre
	 * @param apellido1 Primer apellido
	 * @param apellido2 Segundo apellido
	 */
	public Recepcionista(String dni, String nombre, String apellido1, String apellido2) {
		super(dni, nombre, apellido1, apellido2, null, null);
		
	}



	@Override
	public String toString() {
		return "Recepcionista [ " + super.toString() + "]";
	}


	
	
	
	
	

	
	
	

}
