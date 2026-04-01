package gestion;

import bdDAO.SocioDAO;
import dominio.Socio;

/**
 * Clase de la capa de gestión encargada de coordinar las operaciones
 * relacionadas con los socios.
 * 
 * Actúa como intermediaria entre la capa de presentación (Inicio/GUI)
 * y la capa de persistencia (DAO), aplicando la lógica de negocio.
 */

public class SocioGestion {
	
	private SocioDAO socioDAO;
	

    // Constructor
    public SocioGestion() {
        this.socioDAO = new SocioDAO();
    }
    
    /**
     * Da de alta un socio si no existe previamente.
     */

    // ALTA SOCIO
    public ResultadoGestion altaSocio(String dni, String nombre, String apellido1, String apellido2) {

        Socio socio = new Socio(dni, nombre, apellido1, apellido2);
        ResultadoGestion resultadoAlta;
        

        // comprobar si existe
        if (socioDAO.existeSocio(dni)) {
            resultadoAlta = ResultadoGestion.YA_EXISTE;
        }

        // insertar (socioDAO ya gestiona las excepciones
        socioDAO.insertarSocio(socio);

        resultadoAlta = ResultadoGestion.OK;
        
        return resultadoAlta;
    }

    /**
     * Comprueba si existe un socio.
     */
    // EXISTE SOCIO
    public ResultadoGestion existeSocio(String dni) {
    	
    	ResultadoGestion resultado;

        if (socioDAO.existeSocio(dni)) {
            resultado = ResultadoGestion.YA_EXISTE;
        } else {
            resultado = ResultadoGestion.DISPONIBLE;
        }
        
        return resultado;
    }
    
    /**
     * Busca un socio por DNI.
     */

    // BUSCAR SOCIO 
    public Socio buscarPorDni(String dni) {
        return socioDAO.buscarPorDni(dni);
    }
    
    /**
     * Actualiza un socio si existe.
     */

    //ACTUALIZAR SOCIO
    public ResultadoGestion actualizarSocio(String dni, String nombre, String apellido1, String apellido2) {
    	
    	ResultadoGestion resultadoActual;

        if (!socioDAO.existeSocio(dni)) {
            resultadoActual = ResultadoGestion.ERROR;
        }

        Socio socio = new Socio(dni, nombre, apellido1, apellido2);

         socioDAO.actualizarSocio(socio);
           resultadoActual = ResultadoGestion.OK;
           
           return resultadoActual;
        
    }
    
    /**
     * Elimina un socio si existe.
     */

    // ELIMINAR SOCIO
    public ResultadoGestion eliminarSocio(String dni) {
    	
    	ResultadoGestion resultadoEliminar;

        if (!socioDAO.existeSocio(dni)) {
            resultadoEliminar = ResultadoGestion.ERROR;
            
        }

        socioDAO.eliminarSocio(dni);

       resultadoEliminar =  ResultadoGestion.OK;
        
       return resultadoEliminar;
    }

}
