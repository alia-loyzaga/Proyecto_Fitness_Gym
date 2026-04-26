package bd_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Entrenador;


public class EntrenadorDAO {

	String sentencia;
	
	
	//insertar entrenador en la base de datos
	
	public void insertarEntrenador(Entrenador entrenador) {
		
		//defino consulta
		sentencia = "INSERT INTO entrenador (dni, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";
		
		//abro conexion
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		
		PreparedStatement ps = null;
		
		try {
			
		//preparo consulta(creo la consulta lista para usar)
		 ps = conexionBD.getConexion().prepareStatement(sentencia);
		
		//Sustituyo ?
		ps.setString(1, entrenador.getDni());
		ps.setString(2, entrenador.getNombre());
		ps.setString(3, entrenador.getApellido1());
		ps.setString(4, entrenador.getApellido2());
		
		//ejecuto la sentencia
		ps.executeUpdate();
		
		
		
		} catch (SQLException e) {
			System.out.println("  --> Error en la Conexión");
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
	
	//buscar entrenador en la base de datos (select)
	public Entrenador buscarPorDni(String dni) {

	    sentencia = "SELECT * FROM entrenador WHERE dni = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    Entrenador entrenador = null;

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
	    ) {

	        ps.setString(1, dni);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {

	                entrenador = new Entrenador();
	                entrenador.setDni(resultado.getString("dni"));
	                entrenador.setNombre(resultado.getString("nombre"));
	                entrenador.setApellido1(resultado.getString("apellido1"));
	                entrenador.setApellido2(resultado.getString("apellido2"));

	            }

	        }

	    } catch (SQLException e) {
	        System.out.println("Error en la Conexión");
	    }

	    conexionBD.cerrarConexion();

	    return entrenador;
	}
	
	//Comprobar si existe un entrenador
	public boolean existeEntrenador(String dni) {

	    sentencia = "SELECT dni FROM entrenador WHERE dni = ?";

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
	        System.out.println("Error al comprobar entrenador");
	    }

	    conexionBD.cerrarConexion();

	    return existe;
	}
	//modificar los datos del entrenador
	public void actualizarEntrenador(Entrenador entrenador) {
		
        sentencia = "UPDATE entrenador SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	    	//creo la consulta y sustituyo ?
	        ps = conexionBD.getConexion().prepareStatement(sentencia);

	        ps.setString(1, entrenador.getNombre());
	        ps.setString(2, entrenador.getApellido1());
	        ps.setString(3, entrenador.getApellido2());
	        ps.setString(4, entrenador.getDni());

	        //ejecuto la sentencia
	        ps.executeUpdate();
	        
	    } catch (SQLException e) {
	        System.out.println("Error al actualizar entrenador");
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
	
	
	//eliminar entrenador de la base de datos
	
	public void eliminarEntrenador(String dni) {

	    sentencia = "DELETE FROM entrenador WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	        ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, dni);

	        ps.executeUpdate();
	    

	    } catch (SQLException e) {
	        System.out.println("Error al eliminar entrenador");
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
