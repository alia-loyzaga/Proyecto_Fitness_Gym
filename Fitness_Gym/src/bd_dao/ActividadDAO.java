package bd_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Actividad;
import enumerados.Nivel;

public class ActividadDAO {
	
	 private String sentencia;


	    // INSERTAR ACTIVIDAD
	    public void insertarActividad(Actividad actividad) {

	        sentencia = "INSERT INTO actividad (nombre, descripcion, nivel) VALUES (?, ?, ?)";

	        ConexionBD conexionBD = new ConexionBD();
	        conexionBD.abrirConexion();
	        
	        PreparedStatement ps = null;

	        try {

	            ps = conexionBD.getConexion().prepareStatement(sentencia);

	            ps.setString(1, actividad.getNombre());
	            ps.setString(2, actividad.getDescripcion());
	            ps.setString(3, actividad.getNivel().name());

	            ps.executeUpdate();
	            
	            

	        } catch (SQLException e) {
	            System.out.println("Error al insertar actividad");
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


	    // BUSCAR POR NOMBRE
	    public Actividad buscarPorNombre(String nombre) {

	        sentencia = "SELECT * FROM actividad WHERE nombre = ?";

	        ConexionBD conexionBD = new ConexionBD();
	        conexionBD.abrirConexion();

	        Actividad actividad = null;

	        try (PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)) {

	            ps.setString(1, nombre);

	            try (ResultSet resultado = ps.executeQuery()) {

	                if (resultado.next()) {

	                    actividad = new Actividad();

	                    actividad.setId(resultado.getInt("id"));
	                    actividad.setNombre(resultado.getString("nombre"));
	                    actividad.setDescripcion(resultado.getString("descripcion"));
	                    actividad.setNivel(Nivel.valueOf(resultado.getString("nivel")));
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al buscar actividad");
	        }

	        conexionBD.cerrarConexion();

	        return actividad;
	    }


	    // EXISTE ACTIVIDAD
	    public boolean existeActividad(String nombre) {

	        sentencia = "SELECT nombre FROM actividad WHERE nombre = ?";

	        ConexionBD conexionBD = new ConexionBD();
	        conexionBD.abrirConexion();

	        boolean existe = false;

	        try (PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)) {

	            ps.setString(1, nombre);

	            try (ResultSet resultado = ps.executeQuery()) {

	                if (resultado.next()) {
	                    existe = true;
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al comprobar actividad");
	        }

	        conexionBD.cerrarConexion();

	        return existe;
	    }


	    // ACTUALIZAR ACTIVIDAD
	    public void actualizarActividad(Actividad actividad) {

	        sentencia = "UPDATE actividad SET descripcion = ?, nivel = ? WHERE nombre = ?";

	        ConexionBD conexionBD = new ConexionBD();
	        conexionBD.abrirConexion();
	        
	        PreparedStatement ps = null;

	        try {

	            ps = conexionBD.getConexion().prepareStatement(sentencia);

	            ps.setString(1, actividad.getDescripcion());
	            ps.setString(2, actividad.getNivel().name());
	            ps.setString(3, actividad.getNombre());

	            ps.executeUpdate();
	    

	        } catch (SQLException e) {
	            System.out.println("Error al actualizar actividad");
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


	    // ELIMINAR ACTIVIDAD
	    public void eliminarActividad(String nombre) {

	        sentencia = "DELETE FROM actividad WHERE nombre = ?";

	        ConexionBD conexionBD = new ConexionBD();
	        conexionBD.abrirConexion();
	        
	        PreparedStatement ps = null;

	        try {

	            ps = conexionBD.getConexion().prepareStatement(sentencia);
	            ps.setString(1, nombre);

	            ps.executeUpdate();
	         

	        } catch (SQLException e) {
	            System.out.println("Error al eliminar actividad");
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
