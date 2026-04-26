package bdDAO;

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
		
		try {
			
		//preparo consulta(creo la consulta lista para usar)
		PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
		
		//Sustituyo ?
		ps.setString(1, socio.getDni());
		ps.setString(2, socio.getNombre());
		ps.setString(3, socio.getApellido1());
		ps.setString(4, socio.getApellido2());
		
		//ejecuto la sentencia
		ps.executeUpdate();
		
		
		} catch (SQLException e) {
			System.out.println("  --> Error en la Conexión");
	}
		
	
	//cierro conexion
	if (conexionBD != null) {
	    conexionBD.cerrarConexion();
	}
	
	
	}
	
	//buscar socio en la base de datos (select)
	public Socio buscarPorDni(String dni) {
		
		sentencia = "SELECT * FROM socio WHERE dni = ?";
		
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		Socio socio = null;
		
		try {
			
		//creo la consulta y sustituyo ?
		PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
		ps.setString(1, dni);
		
		//ejecuto SELECT en la base de datos y se devuelve un resultado(una tabla)
		
		ResultSet resultado = ps.executeQuery();
		
		//me posiciono en la fila y creo el socio
		if (resultado.next()) {
			
		    socio = new Socio();
			socio.setDni(resultado.getString("dni"));
			socio.setNombre(resultado.getString("nombre"));
			socio.setApellido1(resultado.getString("apellido1"));
			socio.setApellido2(resultado.getString("apellido2"));
			
		}
			
		}catch(SQLException e) {
			System.out.println("  --> Error en la Conexión");
	    }
		
		//cierro conexion
		if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
		
		//devuelve un socio
		return socio;
		
		
	}
	
	//Comprobar si existe un socio
	public boolean existeSocio(String dni) {
		

	    sentencia = "SELECT dni FROM socio WHERE dni = ?";
	    
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    //bandera boleana
	    boolean existe = false;

	    try {

	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, dni);

	        ResultSet resultado = ps.executeQuery();

	        //me posiciono en una fila y si existe entonces existe true.
	        if (resultado.next()) {
	            existe = true;
	        }

	    } catch (SQLException e) {
	        System.out.println(" --> Error al comprobar socio");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
		

	    return existe;
	}
	
	//modificar los datos del socio
	public void actualizarSocio(Socio socio) {
		
        sentencia = "UPDATE socio SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    try {

	    	//creo la consulta y sustituyo ?
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);

	        ps.setString(1, socio.getNombre());
	        ps.setString(2, socio.getApellido1());
	        ps.setString(3, socio.getApellido2());
	        ps.setString(4, socio.getDni());

	        //ejecuto la sentencia
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println(" --> Error al actualizar socio");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
	}
	
	
	//eliminar socio de la base de datos
	
	public void eliminarSocio(String dni) {

	    sentencia = "DELETE FROM socio WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    try {

	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, dni);

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println(" --> Error al eliminar socio");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
	}
}
