package dominio;

/**
 * Clase que representa a un administrador del gimnasio.
 * Hereda de usuario.
 */

public class Administrador extends Usuario {
	
	/**
	 * Constructor vacío
	 */

	public Administrador() {
		
	}

	/**
	 * Constructor con parámetros
	 * @param dni DNI
	 * @param nombre Nombre
	 * @param apellido1 Primer apellido
	 * @param apellido2 Segundo apellido
	 * @param telefono Teléfono de contacto
	 * @param email Correo electrónico.
	 */
	public Administrador(String dni, String nombre, String apellido1, String apellido2, String telefono, String email) {
		super(dni, nombre, apellido1, apellido2, telefono, email);
		
	}
	
	
	/**
	 * Construtor básico 
	 * @param dni DNI
	 * @param nombre Nombre
	 * @param apellido1 Primer apellido
	 * @param apellido2 Segundo apellido
	 */
	public Administrador(String dni, String nombre, String apellido1, String apellido2) {
		super(dni, nombre, apellido1, apellido2, null, null);
		
	}

	@Override
	public String toString() {
		return "Administrador [ " + super.toString() + "]";
	}

	

	
}
