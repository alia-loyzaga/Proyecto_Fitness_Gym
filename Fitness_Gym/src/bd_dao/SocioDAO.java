package bd_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Socio;

public class SocioDAO {

	
	String sentencia;
	
	
	//insertar socio en la base de datos
	
	public void insertarSocio(Socio socio) {
		
		//defino consulta
		sentencia = "INSERT INTO socio (dni, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";
		
		//abro conexion
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		
		PreparedStatement ps = null;
		
		try {
			
		//preparo consulta(creo la consulta lista para usar)
		 ps = conexionBD.getConexion().prepareStatement(sentencia);
		
		//Sustituyo ?
		ps.setString(1, socio.getDni());
		ps.setString(2, socio.getNombre());
		ps.setString(3, socio.getApellido1());
		ps.setString(4, socio.getApellido2());
		
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
	
	//buscar socio en la base de datos (select)
	public Socio buscarPorDni(String dni) {

	    sentencia = "SELECT * FROM socio WHERE dni = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    Socio socio = null;

	    try (
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
	    ) {

	        ps.setString(1, dni);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {

	                socio = new Socio();
	                socio.setDni(resultado.getString("dni"));
	                socio.setNombre(resultado.getString("nombre"));
	                socio.setApellido1(resultado.getString("apellido1"));
	                socio.setApellido2(resultado.getString("apellido2"));

	            }

	        }

	    } catch (SQLException e) {
	        System.out.println("Error en la Conexión");
	    }

	    conexionBD.cerrarConexion();

	    return socio;
	}
	
	//Comprobar si existe un socio
	public boolean existeSocio(String dni) {

	    sentencia = "SELECT dni FROM socio WHERE dni = ?";

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
	        System.out.println("Error al comprobar socio");
	    }

	    conexionBD.cerrarConexion();

	    return existe;
	}
	
	//modificar los datos del socio
	public void actualizarSocio(Socio socio) {
		
        sentencia = "UPDATE socio SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	    	//creo la consulta y sustituyo ?
	        ps = conexionBD.getConexion().prepareStatement(sentencia);

	        ps.setString(1, socio.getNombre());
	        ps.setString(2, socio.getApellido1());
	        ps.setString(3, socio.getApellido2());
	        ps.setString(4, socio.getDni());

	        //ejecuto la sentencia
	        ps.executeUpdate();
	        

	    } catch (SQLException e) {
	        System.out.println("Error al actualizar socio");
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
	
	
	//eliminar socio de la base de datos
	
	public void eliminarSocio(String dni) {

	    sentencia = "DELETE FROM socio WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    PreparedStatement ps = null;

	    try {

	        ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, dni);

	        ps.executeUpdate();
	        

	    } catch (SQLException e) {
	        System.out.println("Error al eliminar socio");
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
