package bdDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.ActividadProgramada;
import dominio.Reserva;
import dominio.Socio;

public class ReservaDAO {

	/**
	 * DAO encargado de gestionar la persistencia de reservas en la base de datos
	 * mediante la tabla inscripcion.
	 */

	private String sentencia;

	/**
	 * Inserta una nueva reserva en la base de datos.
	 * 
	 * @param reserva Reserva a insertar.
	 */
	public void insertarReserva(Reserva reserva) {

		sentencia = "INSERT INTO inscripcion (socio_dni, actividad_programada_id) VALUES (?, ?)";

		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		try {

			PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);

			ps.setString(1, reserva.getSocio().getDni());
			ps.setInt(2, reserva.getActividadProgramada().getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(" --> Error al insertar reserva");
		}

		conexionBD.cerrarConexion();
	}

	/**
	 * Busca una reserva por su identificador.
	 * 
	 * @param id ID de la reserva.
	 * @return Reserva encontrada o null si no existe.
	 */
	public Reserva buscarPorId(int id) {

		sentencia = "SELECT * FROM inscripcion WHERE id = ?";

		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		Reserva reserva = null;

		try {

			PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
			ps.setInt(1, id);

			ResultSet resultado = ps.executeQuery();

			if (resultado.next()) {

				reserva = new Reserva();

				reserva.setId(resultado.getInt("id"));

				Socio socio = new Socio();
				socio.setDni(resultado.getString("socio_dni"));

				ActividadProgramada actividadProgramada = new ActividadProgramada();
				actividadProgramada.setId(resultado.getInt("actividad_programada_id"));

				reserva.setSocio(socio);
				reserva.setActividadProgramada(actividadProgramada);
			}

		} catch (SQLException e) {
			System.out.println(" --> Error al buscar reserva");
		}

		conexionBD.cerrarConexion();

		return reserva;
	}

	/**
	 * Comprueba si existe una reserva con el ID indicado.
	 * 
	 * @param id ID de la reserva.
	 * @return true si existe; false en caso contrario.
	 */
	public boolean existeReserva(int id) {

		sentencia = "SELECT id FROM inscripcion WHERE id = ?";

		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		boolean existe = false;

		try {

			PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
			ps.setInt(1, id);

			ResultSet resultado = ps.executeQuery();

			if (resultado.next()) {
				existe = true;
			}

		} catch (SQLException e) {
			System.out.println(" --> Error al comprobar reserva");
		}

		conexionBD.cerrarConexion();

		return existe;
	}

	/**
	 * Elimina una reserva de la base de datos.
	 * 
	 * @param id ID de la reserva a eliminar.
	 */
	public void eliminarReserva(int id) {

		sentencia = "DELETE FROM inscripcion WHERE id = ?";

		ConexionBD conexionBD = new ConexionBD();
		conexionBD.abrirConexion();

		try {

			PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(" --> Error al eliminar reserva");
		}

		conexionBD.cerrarConexion();
	}

}
