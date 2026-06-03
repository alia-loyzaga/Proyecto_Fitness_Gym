package dominio;

/**
 * Clase abstracta que representa a un usuario del sistema.
 * Contiene los datos comunes a cada tipo de usuario.
 * 
 * @author Alia
 * @version 1.0
 */
public abstract class Usuario {
	
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono;
	private String email;
	
	//constructores
	
	
	
	protected Usuario() {
		
	}

	protected Usuario(String dni, String nombre, String apellido1, String apellido2, String telefono, String email) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.email = email;
	}

	//métodos de acceso a atributos.
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
		
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
		
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
		
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
		
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public String toString() {
		return "DNI=" + dni + ", Nombre=" + nombre + ", Apellido1=" + apellido1 + ", Apellido2=" + apellido2
				+ ", Telefono=" + telefono + ", Email=" + email;
	}
	
	
}
