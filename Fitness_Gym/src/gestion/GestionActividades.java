package gestion;

import java.time.LocalDateTime;
import java.util.List;

import bd_dao.ActividadDAO;
import bd_dao.ActividadProgramadaDAO;
import bd_dao.MaquinaDAO;
import dominio.Actividad;
import dominio.ActividadProgramada;
import dominio.Entrenador;
import dominio.Maquina;
import dominio.Sala;
import enumerados.Nivel;

/**
 * Clase de la capa de gestión encargada de coordinar las operaciones
 * relacionadas con la gestión de actividades y actividades programadas.
 * 
 * Actúa como intermediaria entre la capa de presentación (GUI) y la capa de
 * persistencia (DAO), aplicando la lógica de negocio correspondiente antes de
 * delegar en la capa DAO.
 * 
 * @author Alia
 * @version 1.0
 */

public class GestionActividades {

	private ActividadDAO actividadDAO;
	private ActividadProgramadaDAO actividadProgramadaDAO;
	private MaquinaDAO maquinaDAO;
	

	/**
	 * Constructor de la clase GestionActividades.
	 * 
	 * Inicializa los objetos DAO necesarios para gestionar las operaciones de
	 * persistencia de actividades y actividades programadas.
	 */

	public GestionActividades() {
		actividadDAO = new ActividadDAO();
		actividadProgramadaDAO = new ActividadProgramadaDAO();
		maquinaDAO = new MaquinaDAO();
		
	}
	

	public GestionActividades(ActividadDAO actividadDAO, ActividadProgramadaDAO actividadProgramadaDAO,
			MaquinaDAO maquinaDAO) {
	
		this.actividadDAO = actividadDAO;
		this.actividadProgramadaDAO = actividadProgramadaDAO;
		this.maquinaDAO = maquinaDAO;
	}


	// GESTIÓN ACTIVIDADES

	/**
	 * Da de alta una nueva actividad en el sistema si no existe previamente una
	 * actividad con el mismo nombre.
	 * 
	 * @param id          Identificador de la actividad
	 * @param nombre      Nombre de la actividad
	 * @param descripcion Descripción de la actividad
	 * @param nivel       Nivel de dificultad de la actividad
	 * @param precio      Precio de la actividad
	 * @return Resultado de la operación de alta
	 */
	public ResultadoGestion altaActividad(int id, String nombre, String descripcion, Nivel nivel, double precio) {

		// Validaciones simplificadas en esta versión
		
		ResultadoGestion resultado;

		if (actividadDAO.existeActividad(nombre)) {
			resultado = ResultadoGestion.YA_EXISTE;
		} else {

			Actividad actividad = new Actividad(id, nombre, descripcion, nivel, precio);

			actividadDAO.insertarActividad(actividad);

			resultado = ResultadoGestion.OK;
		}

		return resultado;
	}

	/**
	 * Busca una actividad en el sistema por su nombre.
	 * 
	 * @param nombre Nombre de la actividad a buscar
	 * @return Actividad encontrada o null si no existe
	 */

	public Actividad buscarActividadPorNombre(String nombre) {

		return actividadDAO.buscarPorNombre(nombre);
	}

	/**
	 * Actualiza los datos de una actividad existente.
	 * 
	 * La actualización solo se realiza si la actividad existe previamente.
	 * 
	 * @param id          Identificador de la actividad
	 * @param nombre      Nombre de la actividad
	 * @param descripcion Nueva descripción
	 * @param nivel       Nuevo nivel de dificultad
	 * @param precio      Nuevo precio
	 * @return Resultado de la operación de actualización
	 */

	public ResultadoGestion actualizarActividad(int id, String nombre, String descripcion, Nivel nivel, double precio) {

		ResultadoGestion resultado;

		if (!actividadDAO.existeActividad(nombre)) {
			resultado = ResultadoGestion.ERROR;
		} else {

			Actividad actividad = new Actividad(id, nombre, descripcion, nivel, precio);

			actividadDAO.actualizarActividad(actividad);

			resultado = ResultadoGestion.OK;
		}

		return resultado;
	}

	/**
	 * Elimina una actividad existente del sistema.
	 * 
	 * La eliminación solo se realiza si la actividad existe previamente.
	 * 
	 * @param nombre Nombre de la actividad a eliminar
	 * @return Resultado de la operación de eliminación
	 */

	public ResultadoGestion eliminarActividad(String nombre) {

		ResultadoGestion resultado;

		if (!actividadDAO.existeActividad(nombre)) {
			resultado = ResultadoGestion.ERROR;
		} else {

			actividadDAO.eliminarActividad(nombre);

			resultado = ResultadoGestion.OK;
		}

		return resultado;
	}

	// GESTIÓN ACTIVIDADES PROGRAMADAS
	
	//validacion solapamiento en horario
	/**
	 * Comprueba si existe solapamiento horario
	 * en una sala para una nueva actividad programada.
	 * 
	 * @param sala Sala a comprobar
	 * @param inicio Fecha y hora de inicio
	 * @param fin Fecha y hora de finalización
	 * @return true si existe conflicto horario, false en caso contrario
	 */
	
	private boolean haySolapamiento(Sala sala, LocalDateTime inicio, LocalDateTime fin) {

	    boolean solapa = false;

	    List<ActividadProgramada> lista = actividadProgramadaDAO.obtenerTodas();

	    for (ActividadProgramada ap : lista) {

	    	if (ap.getSala().getId() == sala.getId() &&
	    		    inicio.compareTo(ap.getFechaHoraFin()) < 0 &&
	    		    fin.compareTo(ap.getFechaHoraInicio()) > 0) {
	    		    solapa = true;
	    		}
	    }

	    return solapa;
	}
	
	//validacion entrenador
	/**
	 * Comprueba si un entrenador ya tiene asignada
	 * otra actividad en el mismo intervalo horario.
	 * 
	 * @param entrenador Entrenador a validar
	 * @param inicio Fecha y hora de inicio
	 * @param fin Fecha y hora de finalización
	 * @return true si el entrenador está ocupado, false en caso contrario
	 */
	private boolean entrenadorOcupado(Entrenador entrenador, LocalDateTime inicio, LocalDateTime fin) {

	    boolean ocupado = false;

	    List<ActividadProgramada> lista = actividadProgramadaDAO.obtenerTodas();

	    for (ActividadProgramada ap : lista) {

	        if (ap.getEntrenador().getDni().equals(entrenador.getDni())&&
	        		inicio.compareTo(ap.getFechaHoraFin()) < 0 &&
	                fin.compareTo(ap.getFechaHoraInicio())> 0 ) {

	                ocupado = true;
	            }
	        
	    }

	    return ocupado;
	}
	
	//validacion de maquina
	/**
	 * Comprueba si alguna máquina de la sala se encuentra en estado de revisión.
	 * 
	 * @param sala Sala a comprobar
	 * @return true si existe alguna máquina en revisión, false en caso contrario
	 */
	private boolean maquinasEnMalEstado(Sala sala) {

	    boolean hayProblema = false;

	    List<Maquina> maquinas = maquinaDAO.obtenerMaquinasPorSala(sala.getId());

	    for (Maquina m : maquinas) {
	        if (m.getEstado().name().equals("EN_REVISION")) {
	            hayProblema = true;
	        }
	    }

	    return hayProblema;
	}

	/**
	 * Da de alta una nueva actividad programada en el sistema.
	 * 
	 * Asocia una actividad a una sala en un rango de fecha y hora determinado.
	 * 
	 * @param actividad       Actividad a programar
	 * @param sala            Sala donde se realizará la actividad
	 * @param fechaHoraInicio Fecha y hora de inicio
	 * @param fechaHoraFin    Fecha y hora de fin
	 * @return Resultado de la operación de alta
	 */
	public ResultadoGestion altaActividadProgramada(
	        Actividad actividad,
	        Sala sala,
	        LocalDateTime inicio,
	        LocalDateTime fin,
	        Entrenador entrenador) {

	    ResultadoGestion resultado;

	    if (haySolapamiento(sala, inicio, fin)) {

	        resultado = ResultadoGestion.ERROR;

	    } else if (entrenadorOcupado(entrenador, inicio, fin)) {

	        resultado = ResultadoGestion.ERROR;

	    } else if (maquinasEnMalEstado(sala)) {

	        resultado = ResultadoGestion.ERROR;

	    } else {

	        ActividadProgramada ap = new ActividadProgramada(
	                actividad, sala, inicio, fin, entrenador);

	        actividadProgramadaDAO.insertarActividadProgramada(ap);

	        resultado = ResultadoGestion.OK;
	    }

	    return resultado;
	}

	/**
	 * Busca una actividad programada en el sistema por su identificador.
	 * 
	 * @param id Identificador de la actividad programada
	 * @return Actividad programada encontrada o null si no existe
	 */

	public ActividadProgramada buscarActividadProgramadaPorId(int id) {

		return actividadProgramadaDAO.buscarPorId(id);
	}

	/**
	 * Actualiza los datos de una actividad programada existente.
	 * 
	 * La actualización solo se realiza si existe previamente una actividad
	 * programada con el ID indicado.
	 * 
	 * @param id              Identificador de la actividad programada
	 * @param actividad       Actividad asociada
	 * @param sala            Sala donde se realizará la actividad
	 * @param fechaHoraInicio Fecha y hora de inicio
	 * @param fechaHoraFin    Fecha y hora de fin
	 * @return Resultado de la operación de actualización
	 */

	public ResultadoGestion actualizarActividadProgramada(int id, Actividad actividad, Sala sala,
			LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Entrenador entrenador) {

		ResultadoGestion resultado;

		if (!actividadProgramadaDAO.existeActividadProgramada(id)) {
			resultado = ResultadoGestion.ERROR;

		} else {

			ActividadProgramada actividadProgramada = new ActividadProgramada(actividad, sala, fechaHoraInicio,
					fechaHoraFin, entrenador);

			actividadProgramada.setId(id);

			actividadProgramadaDAO.actualizarActividadProgramada(actividadProgramada);

			resultado = ResultadoGestion.OK;
		}

		return resultado;
	}

	/**
	 * Elimina una actividad programada existente del sistema.
	 * 
	 * La eliminación solo se realiza si existe previamente una actividad programada
	 * con el ID indicado.
	 * 
	 * @param id Identificador de la actividad programada a eliminar
	 * @return Resultado de la operación de eliminación
	 */

	public ResultadoGestion eliminarActividadProgramada(int id) {

		ResultadoGestion resultado;

		if (!actividadProgramadaDAO.existeActividadProgramada(id)) {
			resultado = ResultadoGestion.ERROR;

		} else {

			actividadProgramadaDAO.eliminarActividadProgramada(id);

			resultado = ResultadoGestion.OK;
		}

		return resultado;
	}
	/**
	 * Obtiene todas las actividades programadas registradas en el sistema.
	 * 
	 * @return Lista de actividades programadas disponibles
	 */
	public List<ActividadProgramada> obtenerActividadesProgramadas() {

	    List<ActividadProgramada> lista;

	    lista = actividadProgramadaDAO.obtenerTodas();

	    return lista;
	}

}
