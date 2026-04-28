package bd_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Administrador;

/**
 * Clase DAO encargada de gestionar el acceso a la base de datos
 * para los administradores del sistema.
 * 
 * Permite realizar operaciones CRUD sobre administradores:
 * insertar, buscar, comprobar existencia, actualizar y eliminar.
 * 
 * @author Alia
 * @version 1.0
 */
public class AdministradorDAO {
	
	/**
     * Variable que almacena la sentencia SQL a ejecutar en cada operación.
     */

	String sentencia;

	// insertar administrador en la base de datos
	
	/**
	 * Inserta un nuevo administrador en la base de datos.
	 * 
	 * @param administrador Objeto administrador a insertar
	 */

	public void insertarAdministrador(Administrador administrador) {

		// defino consulta
		sentencia = "INSERT INTO administrador (dni, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";

		// abro conexion
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		PreparedStatement ps = null;

		try {

			// preparo consulta(creo la consulta lista para usar)
			ps = conexionBD.getConexion().prepareStatement(sentencia);

			// Sustituyo ?
			ps.setString(1, administrador.getDni());
			ps.setString(2, administrador.getNombre());
			ps.setString(3, administrador.getApellido1());
			ps.setString(4, administrador.getApellido2());

			// ejecuto la sentencia
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error en la Conexión");
		}

		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			 // Ignorado: error al cerrar recursos
		}

		// cierro conexion

		conexionBD.cerrarConexion();

	}

	// buscar administrador en la base de datos (select)
	/**
	 * Busca un administrador por su DNI.
	 * 
	 * @param dni DNI del administrador
	 * @return Administrador encontrado o null si no existe
	 */
	public Administrador buscarPorDni(String dni) {

	    sentencia = "SELECT * FROM administrador WHERE dni = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    Administrador administrador = null;

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
	    ) {

	        ps.setString(1, dni);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {

	                administrador = new Administrador();
	                administrador.setDni(resultado.getString("dni"));
	                administrador.setNombre(resultado.getString("nombre"));
	                administrador.setApellido1(resultado.getString("apellido1"));
	                administrador.setApellido2(resultado.getString("apellido2"));

	            }

	        }

	    } catch (SQLException e) {
	        System.out.println("Error en la Conexión");
	    }

	    conexionBD.cerrarConexion();

	    return administrador;
	}

	// Comprobar si existe un administrador
	/**
	 * Comprueba si existe un administrador con el DNI indicado.
	 * 
	 * @param dni DNI del administrador
	 * @return true si existe, false en caso contrario
	 */
	public boolean existeAdministrador(String dni) {

	    sentencia = "SELECT dni FROM administrador WHERE dni = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    boolean existe = false;

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
	    ) {

	        ps.setString(1, dni);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {
	                existe = true;
	            }

	        }

	    } catch (SQLException e) {
	        System.out.println("Error al comprobar administrador");
	    }

	    conexionBD.cerrarConexion();

	    return existe;
	}

	// modificar los datos del administrador
	/**
	 * Actualiza los datos de un administrador existente.
	 * 
	 * @param administrador Administrador con datos actualizados
	 */
	public void actualizarAdministrador(Administrador administrador) {

		sentencia = "UPDATE administrador SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		PreparedStatement ps = null;

		try {

			// creo la consulta y sustituyo ?
			ps = conexionBD.getConexion().prepareStatement(sentencia);

			ps.setString(1, administrador.getNombre());
			ps.setString(2, administrador.getApellido1());
			ps.setString(3, administrador.getApellido2());
			ps.setString(4, administrador.getDni());

			// ejecuto la sentencia
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al actualizar administrador");
		}

		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			 // Ignorado: error al cerrar recursos
		}

		conexionBD.cerrarConexion();

	}

	// eliminar recepcionista de la base de datos

	/**
	 * Elimina un administrador de la base de datos.
	 * 
	 * @param dni DNI del administrador a eliminar
	 */
	public void eliminarAdministrador(String dni) {

		sentencia = "DELETE FROM administrador WHERE dni = ?";
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		PreparedStatement ps = null;

		try {

			ps = conexionBD.getConexion().prepareStatement(sentencia);
			ps.setString(1, dni);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al eliminar administrador");
		}

		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			 // Ignorado: error al cerrar recursos
		}

		conexionBD.cerrarConexion();

	}

}
