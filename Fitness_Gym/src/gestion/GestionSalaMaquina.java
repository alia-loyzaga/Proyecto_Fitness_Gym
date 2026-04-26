package gestion;



import java.util.List;

import bdDAO.MaquinaDAO;
import bdDAO.SalaDAO;
import dominio.Maquina;
import dominio.Sala;

/**
 * Clase de la capa de gestión encargada de coordinar las operaciones
 * relacionadas con la gestión de salas y máquinas
 * 
 *.
 * 
 * Actúa como intermediaria entre la capa de presentación (GUI)
 * y la capa de persistencia (DAO), aplicando la lógica de negocio
 * correspondiente antes de delegar en la capa DAO.
 */

public class GestionSalaMaquina {
	
	private SalaDAO salaDAO;
	private MaquinaDAO maquinaDAO;
	

	/**
	 * Constructor de la clase GestionSalaMaquina.
	 * 
	 * Inicializa los objetos DAO necesarios para gestionar
	 * las operaciones de persistencia de salas y maquinaria.
	 */
	
    public GestionSalaMaquina() {
        salaDAO = new SalaDAO();
        maquinaDAO = new MaquinaDAO();
       
    }


  
    // GESTIÓN DE SALAS

    /**
     * Da de alta una nueva sala en el sistema si no existe previamente
     * una sala con el mismo nombre.
     * 
     * @param nombre Nombre de la sala
     * @param metrosCuadrados Metros cuadrados de la sala
     * @param aforoMaximo Capacidad máxima de personas de la sala
     * @return Resultado de la operación de alta
     */

    public ResultadoGestion altaSala(String nombre, double metrosCuadrados, int aforoMaximo) {

        ResultadoGestion resultado;

        if (salaDAO.existeSala(nombre)) {
            resultado = ResultadoGestion.YA_EXISTE;
        } else {

            Sala sala = new Sala(nombre, metrosCuadrados, aforoMaximo);

            salaDAO.insertarSala(sala);

            resultado = ResultadoGestion.OK;
        }

        return resultado;
    }
    
    /**
     * Busca una sala en el sistema por su nombre.
     * 
     * @param nombre Nombre de la sala a buscar
     * @return Sala encontrada o null si no existe
     */


    public Sala buscarSalaPorNombre(String nombre) {

        return salaDAO.buscarPorNombre(nombre);
    }

    
    /**
     * Actualiza los datos de una sala existente.
     * 
     * La actualización solo se realiza si la sala existe previamente.
     * 
     * @param nombre Nombre de la sala
     * @param metrosCuadrados Nuevos metros cuadrados
     * @param aforoMaximo Nuevo aforo máximo
     * @return Resultado de la operación de actualización
     */

    public ResultadoGestion actualizarSala(String nombre, double metrosCuadrados, int aforoMaximo) {

        ResultadoGestion resultado;

        if (!salaDAO.existeSala(nombre)) {
            resultado = ResultadoGestion.ERROR;
        } else {

            Sala sala = new Sala(nombre, metrosCuadrados, aforoMaximo);

            salaDAO.actualizarSala(sala);

            resultado = ResultadoGestion.OK;
        }

        return resultado;
    }

    /**
     * Elimina una sala existente del sistema.
     * 
     * La eliminación solo se realiza si la sala existe previamente.
     * 
     * @param nombre Nombre de la sala a eliminar
     * @return Resultado de la operación de eliminación
     */

    public ResultadoGestion eliminarSala(String nombre) {

        ResultadoGestion resultado;

        if (!salaDAO.existeSala(nombre)) {
            resultado = ResultadoGestion.ERROR;
        } else {

            salaDAO.eliminarSala(nombre);

            resultado = ResultadoGestion.OK;
        }

        return resultado;
    }

    //GESTION DE MAQUINAS
    
    public ResultadoGestion altaMaquina(String tipo, String marca, String numeroSerie, Sala sala) {

        ResultadoGestion resultado;

        Maquina maquina = new Maquina(tipo, marca, numeroSerie, sala);

        maquinaDAO.insertarMaquina(maquina);

        resultado = ResultadoGestion.OK;

        return resultado;
    }

    public List<Maquina> obtenerMaquinasDeSala(int idSala) {

        return maquinaDAO.obtenerMaquinasPorSala(idSala);
    }
    
    public boolean hayMaquinasEnRevision(Sala sala) {

        boolean hay = false;

        List<Maquina> maquinas = maquinaDAO.obtenerMaquinasPorSala(sala.getId());

        for (Maquina m : maquinas) {
            if (m.getEstado().name().equals("EN_REVISION")) {
                hay = true;
            }
        }

        return hay;
    }

}
