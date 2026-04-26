package bdDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Sala;


public class SalaDAO {

	String sentencia;
	
	
	//insertar sala en la base de datos
	
	public void insertarSala(Sala sala) {
		
		//defino consulta
		sentencia = "INSERT INTO sala (nombre, metrosCuadrados, aforoMaximo) VALUES (?, ?, ?)";
		
		//abro conexion
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		
		try {
			
		//preparo consulta(creo la consulta lista para usar)
		PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
		
		//Sustituyo ?
		ps.setString(1, sala.getNombre());
		ps.setDouble(2, sala.getMetrosCuadrados());
		ps.setInt(3, sala.getAforoMaximo());
		
		
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
	
	//buscar sala en la base de datos (select)
	public Sala buscarPorNombre(String nombre) {
		
		sentencia = "SELECT * FROM sala WHERE nombre = ?";
		
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		Sala sala = null;
		
		try {
			
		//creo la consulta y sustituyo ?
		PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
		ps.setString(1, nombre);
		
		//ejecuto SELECT en la base de datos y se devuelve un resultado(una tabla)
		
		ResultSet resultado = ps.executeQuery();
		
		//me posiciono en la fila y creo la sala
		if (resultado.next()) {
			
		    sala = new Sala();
		    sala.setId(resultado.getInt("id"));
			sala.setNombre(resultado.getString("nombre"));
			sala.setMetrosCuadrados(resultado.getDouble("metrosCuadrados"));
			sala.setAforoMaximo(resultado.getInt("aforoMaximo"));
		
			
		}
			
		}catch(SQLException e) {
			System.out.println("  --> Error en la Conexión");
	    }
		
		//cierro conexion
		if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
		
		//devuelve una sala
		return sala;
		
		
	}
	
	//Comprobar si existe una sala
	public boolean existeSala(String nombre) {
		

	    sentencia = "SELECT nombre FROM sala WHERE nombre = ?";
	    
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    //bandera boleana
	    boolean existe = false;

	    try {

	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, nombre);

	        ResultSet resultado = ps.executeQuery();

	        //me posiciono en una fila y si existe entonces existe true.
	        if (resultado.next()) {
	            existe = true;
	        }

	    } catch (SQLException e) {
	        System.out.println(" --> Error al comprobar sala");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
		

	    return existe;
	}
	
	//modificar los datos de la sala
	public void actualizarSala(Sala sala) {
		
        sentencia = "UPDATE sala SET metrosCuadrados = ?, aforoMaximo = ? WHERE nombre = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    try {

	    	//creo la consulta y sustituyo ?
	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);

	
	        ps.setDouble(1, sala.getMetrosCuadrados());
	        ps.setInt(2, sala.getAforoMaximo());
	        ps.setString(3, sala.getNombre());
	    

	        //ejecuto la sentencia
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println(" --> Error al actualizar sala");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
	}
	
	
	//eliminar sala de la base de datos
	
	public void eliminarSala(String nombre) {

	    sentencia = "DELETE FROM sala WHERE nombre = ?";
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    try {

	        PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
	        ps.setString(1, nombre);

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println(" --> Error al eliminar sala");
	    }

	    if (conexionBD != null) {
		    conexionBD.cerrarConexion();
		}
	}
	
	public List<Sala> obtenerTodas(){
		
		List<Sala>lista = new ArrayList<>();
		
		sentencia = "SELECT * FROM sala";
	
	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();
	    
	    try {
			PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
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
		
	    if(conexionBD != null) {
	    	conexionBD.cerrarConexion();
	    }
	    
	    return lista;
		
	}
}
