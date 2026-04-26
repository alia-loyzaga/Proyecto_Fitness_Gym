package bdDAO;

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
		
		try {
			
		//preparo consulta(creo la consulta lista para usar)
		PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
		
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
		
	
	//cierro conexion
	if (conexionBD != null) {
	    conexionBD.cerrarConexion();
	}
	
	
	}
	
	//buscar entrenador en la base de datos (select)
	public Entrenador buscarPorDni(String dni) {
		
		sentencia = "SELECT * FROM entrenador WHERE dni = ?";
		
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		Entrenador entrenador = null;
		
		try {
			
		//creo la consulta y sustituyo ?
		PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
		ps.setString(1, dni);
		
		//ejecuto SELECT en la base de datos y se devuelve un resultado(una tabla)
		
		ResultSet resultado = ps.executeQuery();
		
		//me posiciono en la fila y creo el entrenador
		if (resultado.next()) {
			
		    entrenador = new Entrenador();
			entrenador.setDni(resultado.getString("dni"));
			entrenador.setNombre(resultado.getString("nombre"));
			entrenador.setApellido1(resultado.getString("apellido1"));
			entrenador.setApellido2(resultado.getString("apellido2"));
			
		}
			
		}catch(SQLException e) {
			System.out.println("  --> Error en la Conexión");
	    }
		
		//cierro conexion
		if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
		
		//devuelve un entrenador
		return entrenador;
		
		
	}
	
	//Comprobar si existe un entrenador
	public boolean existeEntrenador(String dni) {
		

	    sentencia = "SELECT dni FROM entrenador WHERE dni = ?";
	    
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
	        System.out.println(" --> Error al comprobar entrenador");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
		

	    return existe;
	}
	
	//modificar los datos del entrenador
	public void actualizarEntrenador(Entrenador entrenador) {
		
        sentencia = "UPDATE entrenador SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    try {

	    	//creo la consulta y sustituyo ?
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);

	        ps.setString(1, entrenador.getNombre());
	        ps.setString(2, entrenador.getApellido1());
	        ps.setString(3, entrenador.getApellido2());
	        ps.setString(4, entrenador.getDni());

	        //ejecuto la sentencia
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println(" --> Error al actualizar entrenador");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
	}
	
	
	//eliminar entrenador de la base de datos
	
	public void eliminarEntrenador(String dni) {

	    sentencia = "DELETE FROM entrenador WHERE dni = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    try {

	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, dni);

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println(" --> Error al eliminar entrenador");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
	}
}
