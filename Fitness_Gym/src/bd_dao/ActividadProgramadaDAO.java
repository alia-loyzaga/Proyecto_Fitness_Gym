package bd_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dominio.Actividad;
import dominio.ActividadProgramada;
import dominio.Entrenador;
import dominio.Sala;

/**
 * DAO encargado de gestionar la persistencia de actividades programadas en la base de datos.
 * Permite insertar, consultar, actualizar y eliminar actividades programadas.
 * 
 * Cada actividad programada relaciona una actividad con una sala en un rango de fecha/hora.
 * 
 * Se utiliza la tabla actividad_programada de la base de datos.
 * 
 * @author Alia
 */


public class ActividadProgramadaDAO {

	private String sentencia;
	

	/**
	 * Comprueba si existe una actividad programada registrada
	 * en la base de datos con el identificador indicado.
	 * 
	 * @param id Identificador de la actividad programada a comprobar
	 * @return true si existe una actividad programada con ese ID;
	 *         false en caso contrario
	 */
	
	public boolean existeActividadProgramada(int id) {

	    sentencia = "SELECT id FROM actividad_programada WHERE id = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    boolean existe = false;
	   

	    try (PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)){

	    	
	        ps.setInt(1, id);

	        try (ResultSet resultado = ps.executeQuery()){

	        if (resultado.next()) {
	            existe = true;
	        }
	        }
	       

	    } catch (SQLException e) {
	        System.out.println("Error al comprobar actividad programada");
	    }
	    
	    conexionBD.cerrarConexion();

	    return existe;
	}


	/**
	 * Inserta una nueva actividad programada en la base de datos.
	 * 
	 * Persiste la fecha/hora de inicio y fin de la actividad, así como
	 * la actividad y sala asociadas mediante sus identificadores.
	 * 
	 * @param actividadProgramada actividad programada a insertar.
	 */
	public void insertarActividadProgramada(ActividadProgramada actividadProgramada) {

		sentencia = "INSERT INTO actividad_programada (fecha_inicio, fecha_fin, actividad_id, sala_id, dni_entrenador)VALUES (?, ?, ?, ?, ?)";

		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		
		PreparedStatement ps = null;

		try {

		    ps = conexionBD.getConexion().prepareStatement(sentencia);

			ps.setTimestamp(1, Timestamp.valueOf(actividadProgramada.getFechaHoraInicio()));
			ps.setTimestamp(2, Timestamp.valueOf(actividadProgramada.getFechaHoraFin()));
			ps.setInt(3, actividadProgramada.getActividad().getId());
			ps.setInt(4, actividadProgramada.getSala().getId());
			ps.setString(5, actividadProgramada.getEntrenador().getDni());

			ps.executeUpdate();
			
	

		} catch (SQLException e) {
			System.out.println("Error al insertar actividad programada");
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
	 * Busca una actividad programada por su identificador.
	 * 
	 * Reconstruye el objeto ActividadProgramada con sus datos básicos
	 * y referencias mínimas a Actividad y Sala mediante sus IDs.
	 * 
	 * @param id identificador de la actividad programada.
	 * @return actividad programada encontrada o null si no existe.
	 */

	public ActividadProgramada buscarPorId(int id) {

	    sentencia = "SELECT * FROM actividad_programada WHERE id = ?";

	    ConexionBD conexionBD = new ConexionBD();
	    conexionBD.abrirConexion();

	    ActividadProgramada actividadProgramada = null;

	    try (PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)) {

	        ps.setInt(1, id);

	        try (ResultSet resultado = ps.executeQuery()) {

	            if (resultado.next()) {

	                actividadProgramada = new ActividadProgramada();

	                actividadProgramada.setId(resultado.getInt("id"));
	                actividadProgramada.setFechaHoraInicio(
	                        resultado.getTimestamp("fecha_inicio").toLocalDateTime());
	                actividadProgramada.setFechaHoraFin(
	                        resultado.getTimestamp("fecha_fin").toLocalDateTime());

	                Actividad actividad = new Actividad();
	                actividad.setId(resultado.getInt("actividad_id"));

	                Sala sala = new Sala();
	                sala.setId(resultado.getInt("sala_id"));

	                actividadProgramada.setActividad(actividad);
	                actividadProgramada.setSala(sala);

	                Entrenador entrenador = new Entrenador();
	                entrenador.setDni(resultado.getString("dni_entrenador"));

	                actividadProgramada.setEntrenador(entrenador);
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar actividad programada");
	    }

	    conexionBD.cerrarConexion();

	    return actividadProgramada;
	}
	
	/**
	 * Actualiza los datos de una actividad programada existente.
	 * 
	 * Modifica fecha/hora de inicio, fecha/hora de fin, actividad y sala asociadas.
	 * 
	 * @param actividadProgramada actividad programada con los datos actualizados.
	 */


	public void actualizarActividadProgramada(ActividadProgramada actividadProgramada) {

		sentencia = "UPDATE actividad_programada SET fecha_inicio = ?, fecha_fin = ?, actividad_id = ?, sala_id = ? WHERE id = ?";

		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		PreparedStatement ps = null;
		
		try {

			ps = conexionBD.getConexion().prepareStatement(sentencia);

			ps.setTimestamp(1, Timestamp.valueOf(actividadProgramada.getFechaHoraInicio()));
			ps.setTimestamp(2, Timestamp.valueOf(actividadProgramada.getFechaHoraFin()));
			ps.setInt(3, actividadProgramada.getActividad().getId());
			ps.setInt(4, actividadProgramada.getSala().getId());
			ps.setInt(5, actividadProgramada.getId());

			ps.executeUpdate();
		

		} catch (SQLException e) {
			System.out.println("Error al actualizar actividad programada");
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
	 * Elimina una actividad programada de la base de datos por su ID.
	 * 
	 * @param id identificador de la actividad programada a eliminar.
	 */

	public void eliminarActividadProgramada(int id) {

		sentencia = "DELETE FROM actividad_programada WHERE id = ?";

		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();
		
		PreparedStatement ps  = null;

		try {

		    ps = conexionBD.getConexion().prepareStatement(sentencia);
			ps.setInt(1, id);

			ps.executeUpdate();
			
			

		} catch (SQLException e) {
			System.out.println("Error al eliminar actividad programada");
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
	
	public List<ActividadProgramada> obtenerTodas() {

	    List<ActividadProgramada> lista = new ArrayList<>();

	    sentencia =
	        "SELECT ap.id, ap.fecha_inicio, ap.fecha_fin, ap.dni_entrenador, " +
	        "       a.nombre AS nombre_actividad, " +
	        "       s.nombre AS nombre_sala " +
	        "FROM actividad_programada ap " +
	        "JOIN actividad a ON ap.actividad_id = a.id " +
	        "JOIN sala s ON ap.sala_id = s.id";

	    ConexionBD conexion = new ConexionBD();
	    conexion.abrirConexion();

	    try (PreparedStatement ps = conexion.getConexion().prepareStatement(sentencia);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            ActividadProgramada ap = new ActividadProgramada();

	            ap.setId(rs.getInt("id"));
	            ap.setFechaHoraInicio(
	                    rs.getTimestamp("fecha_inicio").toLocalDateTime());
	            ap.setFechaHoraFin(
	                    rs.getTimestamp("fecha_fin").toLocalDateTime());

	            Actividad actividad = new Actividad();
	            actividad.setNombre(rs.getString("nombre_actividad"));

	            Sala sala = new Sala();
	            sala.setNombre(rs.getString("nombre_sala"));

	            ap.setActividad(actividad);
	            ap.setSala(sala);

	            Entrenador entrenador = new Entrenador();
	            entrenador.setDni(rs.getString("dni_entrenador"));

	            ap.setEntrenador(entrenador);

	            lista.add(ap);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener actividades.");
	    }

	    conexion.cerrarConexion();

	    return lista;
	}
	
}
