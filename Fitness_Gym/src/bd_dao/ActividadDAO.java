package bd_dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Actividad;
import enumerados.Nivel;

/**
 * Clase DAO encargada de gestionar el acceso a la base de datos
 * para las actividades del gimnasio.
 * 
 * Permite realizar operaciones CRUD sobre actividades:
 * insertar, buscar, actualizar y eliminar.
 * 
 * @author Alia
 * @version 1.0
 */

public class ActividadDAO {
	
	 /**
     * Variable que almacena la sentencia SQL a ejecutar en cada operación.
     */
	
	 private String sentencia;

	  /**
	     * Inserta una nueva actividad en la base de datos.
	     * 
	     * @param actividad Objeto Actividad con nombre, descripción y nivel.
	     */

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


	    /**
	     * Busca una actividad por su nombre.
	     * 
	     * @param nombre Nombre de la actividad a buscar.
	     * @return Objeto Actividad si existe, null si no se encuentra.
	     */
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

	    /**
	     * Comprueba si una actividad ya existe en la base de datos.
	     * 
	     * @param nombre Nombre de la actividad.
	     * @return true si existe, false en caso contrario.
	     */

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

	    /**
	     * Actualiza la descripción y nivel de una actividad existente.
	     * 
	     * @param actividad Actividad con datos actualizados.
	     */

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

	    /**
	     * Elimina una actividad de la base de datos según su nombre.
	     * 
	     * @param nombre Nombre de la actividad a eliminar.
	     */

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
