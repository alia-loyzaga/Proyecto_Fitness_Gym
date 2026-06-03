package bd_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Sala;

/**
 * Clase DAO encargada de gestionar el acceso a la base de datos
 * para las salas del gimnasio.
 * 
 * Permite realizar operaciones CRUD sobre las salas.
 * 
 *@author Alia
 *@version 1.0
 *
 */
public class SalaDAO {
	
	/**
     * Variable que almacena la sentencia SQL a ejecutar en cada operación.
     */

	String sentencia;
	
	
	//insertar sala en la base de datos
	/**
	 * Inserta una nueva sala en la base de datos.
	 * 
	 * @param sala Sala a insertar.
	 */
	
	public void insertarSala(Sala sala) {
		
		//defino consulta
		sentencia = "INSERT INTO sala (nombre, metros, aforo) VALUES (?, ?, ?)";
		
		//abro conexion
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		
		PreparedStatement ps = null;
		
		try {
			
		//preparo consulta(creo la consulta lista para usar)
		 ps = conexionBD.getConexion().prepareStatement(sentencia);
		
		//Sustituyo ?
		ps.setString(1, sala.getNombre());
		ps.setDouble(2, sala.getMetrosCuadrados());
		ps.setInt(3, sala.getAforoMaximo());
		
		
		//ejecuto la sentencia
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
		
	
	//cierro conexion

	    conexionBD.cerrarConexion();
	
	
	
	}
	
	//buscar sala en la base de datos (select)
	/**
	 * Busca una sala por su nombre.
	 * 
	 * @param nombre Nombre de la sala.
	 * @return Sala encontrada o null si no existe.
	 */
	public Sala buscarPorNombre(String nombre) {

	    sentencia = "SELECT * FROM sala WHERE nombre = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    Sala sala = null;

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
	    ) {

	        ps.setString(1, nombre);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {

	                sala = new Sala();
	                sala.setId(resultado.getInt("id"));
	                sala.setNombre(resultado.getString("nombre"));
	                sala.setMetrosCuadrados(
	                        resultado.getDouble("metros"));
	                sala.setAforoMaximo(
	                        resultado.getInt("aforo"));

	            }

	        }

	    } catch (SQLException e) {
	        System.out.println("Error en la Conexión");
	    }

	    conexionBD.cerrarConexion();

	    return sala;
	}
	
	//Comprobar si existe una sala
	/**
	 * Comprueba si existe una sala con el nombre indicado.
	 * 
	 * @param nombre Nombre de la sala.
	 * @return true si existe; false en caso contrario.
	 */
	public boolean existeSala(String nombre) {

	    sentencia = "SELECT nombre FROM sala WHERE nombre = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    boolean existe = false;

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
	    ) {

	        ps.setString(1, nombre);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {
	                existe = true;
	            }

	        }

	    } catch (SQLException e) {
	        System.out.println("Error al comprobar sala");
	    }

	    conexionBD.cerrarConexion();

	    return existe;
	}
	
	//modificar los datos de la sala
	/**
	 * Actualiza los datos de una sala existente.
	 * 
	 * @param sala Sala con datos actualizados.
	 */
	public void actualizarSala(Sala sala) {
		
        sentencia = "UPDATE sala SET metros = ?, aforo = ? WHERE nombre = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	    	//creo la consulta y sustituyo ?
	        ps = conexionBD.getConexion().prepareStatement(sentencia);

	
	        ps.setDouble(1, sala.getMetrosCuadrados());
	        ps.setInt(2, sala.getAforoMaximo());
	        ps.setString(3, sala.getNombre());
	    

	        //ejecuto la sentencia
	        ps.executeUpdate();
	       

	    } catch (SQLException e) {
	        System.out.println("Error al actualizar sala");
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
	
	
	//eliminar sala de la base de datos
	/**
	 * Elimina una sala de la base de datos a través de su nombre.
	 * 
	 * @param nombre Nombre de la sala a eliminar.
	 */
	
	public void eliminarSala(String nombre) {

	    sentencia = "DELETE FROM sala WHERE nombre = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	        ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, nombre);

	        ps.executeUpdate();
	        
	       

	    } catch (SQLException e) {
	        System.out.println("Error al eliminar sala");
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
	/**
	 * Recupera todas las salas  almacenadas en la base de datos,
	 * 
	 * 
	 * @return Lista completa de salas.
	 */
	public List<Sala> obtenerTodas() {

	    List<Sala> lista = new ArrayList<>();

	    sentencia = "SELECT * FROM sala";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ResultSet rs = ps.executeQuery()
	    ) {

	        while (rs.next()) {

	            Sala sala = new Sala();

	            sala.setId(rs.getInt("id"));
	            sala.setNombre(rs.getString("nombre"));
	            sala.setMetrosCuadrados(rs.getDouble("metros"));
	            sala.setAforoMaximo(rs.getInt("aforo"));

	            lista.add(sala);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al obtener salas" + e.getMessage());
	    }

	    conexionBD.cerrarConexion();

	    return lista;
	}
}
