package gestion;

import bd_dao.ReservaDAO;
import dominio.ActividadProgramada;
import dominio.Reserva;
import dominio.Socio;

/**
 * Clase de la capa de gestión encargada de coordinar las operaciones
 * relacionadas con la gestión de reservas.
 */

public class GestionReservas {
	
	private ReservaDAO reservaDAO;



	/**
	* Constructor de la clase.
	*/
	public GestionReservas() {
	reservaDAO = new ReservaDAO();
	}
	
	
	private boolean validarReserva(Socio socio, ActividadProgramada actividadProgramada) {

	    boolean valida = true;

	    if (!actividadProgramada.hayPlazasDisponibles()) {
	        valida = false;
	    } 
	    if (socio.esMoroso()) {
	        valida = false;
	    }
	    if (socio.tieneReservaEnHorario(actividadProgramada)) {
	        valida = false;
	    }

	    return valida;
	}


	/**
	* Crea una nueva reserva si cumple las validaciones de negocio.
	* 
	* @param actividadProgramada Actividad programada reservada
	* @param socio Socio que realiza la reserva
	* @return Resultado de la operación
	*/
	public ResultadoGestion reservar(ActividadProgramada actividadProgramada, Socio socio) {

	    ResultadoGestion resultado;

	    if (!validarReserva(socio, actividadProgramada)) {
	        resultado = ResultadoGestion.ERROR;
	    } else {

	        Reserva reserva = new Reserva(actividadProgramada, socio);

	        reservaDAO.insertarReserva(reserva);

	        resultado = ResultadoGestion.OK;
	    }

	    return resultado;
	}


	/**
	* Busca una reserva por su ID.
	* 
	* @param id Identificador de la reserva
	* @return Reserva encontrada o null si no existe
	*/
	public Reserva buscarReservaPorId(int id) {

		return reservaDAO.buscarPorId(id);
	}


	/**
	* Cancela una reserva eliminándola de la base de datos.
	* 
	* @param id Identificador de la reserva
	* @return Resultado de la operación
	*/
	public ResultadoGestion cancelarReserva(int id) {

		ResultadoGestion resultado;

		if (!reservaDAO.existeReserva(id)) {

			resultado = ResultadoGestion.ERROR;

		} else {

			reservaDAO.eliminarReserva(id);

			resultado = ResultadoGestion.OK;
		}

		return resultado;
	}

}
