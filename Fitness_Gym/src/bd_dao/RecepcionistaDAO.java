package bd_dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;


import dominio.Recepcionista;

public class RecepcionistaDAO {

	String sentencia;
	
	
	//insertar recepcionista en la base de datos
	
	public void insertarRecepcionista(Recepcionista recepcionista) {
		
		//defino consulta
		sentencia = "INSERT INTO recepcionista (dni, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";
		
		//abro conexion
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		
		PreparedStatement ps = null;
		
		try {
			
		//preparo consulta(creo la consulta lista para usar)
		 ps = conexionBD.getConexion().prepareStatement(sentencia);
		
		//Sustituyo ?
		ps.setString(1, recepcionista.getDni());
		ps.setString(2, recepcionista.getNombre());
		ps.setString(3, recepcionista.getApellido1());
		ps.setString(4, recepcionista.getApellido2());
		
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
	
	//buscar recepcionista en la base de datos (select)
	public Recepcionista buscarPorDni(String dni) {

	    sentencia = "SELECT * FROM recepcionista WHERE dni = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    Recepcionista recepcionista = null;

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
	    ) {

	        ps.setString(1, dni);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {

	                recepcionista = new Recepcionista();
	                recepcionista.setDni(resultado.getString("dni"));
	                recepcionista.setNombre(resultado.getString("nombre"));
	                recepcionista.setApellido1(resultado.getString("apellido1"));
	                recepcionista.setApellido2(resultado.getString("apellido2"));

	            }

	        }

	    } catch (SQLException e) {
	        System.out.println("Error en la Conexión");
	    }

	    conexionBD.cerrarConexion();

	    return recepcionista;
	}
	
	//Comprobar si existe un recepcionista
	public boolean existeRecepcionista(String dni) {

	    sentencia = "SELECT dni FROM recepcionista WHERE dni = ?";

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
	        System.out.println("Error al comprobar recepcionista");
	    }

	    conexionBD.cerrarConexion();

	    return existe;
	}
	//modificar los datos del recepcionista
	public void actualizarRecepcionista(Recepcionista recepcionista) {
		
        sentencia = "UPDATE recepcionista SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	    	//creo la consulta y sustituyo ?
	        ps = conexionBD.getConexion().prepareStatement(sentencia);

	        ps.setString(1, recepcionista.getNombre());
	        ps.setString(2, recepcionista.getApellido1());
	        ps.setString(3, recepcionista.getApellido2());
	        ps.setString(4, recepcionista.getDni());

	        //ejecuto la sentencia
	        ps.executeUpdate();
	        
	     

	    } catch (SQLException e) {
	        System.out.println("Error al actualizar recepcionista");
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
	
	
	//eliminar recepcionista de la base de datos
	
	public void eliminarRecepcionista(String dni) {

	    sentencia = "DELETE FROM recepcionista WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	        ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, dni);

	        ps.executeUpdate();
	        
	       

	    } catch (SQLException e) {
	        System.out.println("Error al eliminar recepcionista");
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
