package gestion;

import bd_dao.AdministradorDAO;
import bd_dao.EntrenadorDAO;
import bd_dao.RecepcionistaDAO;
import bd_dao.SocioDAO;
import dominio.Administrador;
import dominio.Entrenador;
import dominio.Recepcionista;
import dominio.Socio;
import dominio.Usuario;

/**
 * Clase de la capa de gestión encargada de coordinar las operaciones
 * relacionadas con los usuarios.
 * 
 * Actúa como intermediaria entre la capa de presentación (Inicio/GUI)
 * y la capa de persistencia (DAO), aplicando la lógica de negocio.
 * 
 * @author Alia
 * @version 1.0
 */

public class UsuarioGestion {
	
	
	private SocioDAO socioDAO;
	private EntrenadorDAO entrenadorDAO;
	private AdministradorDAO administradorDAO;
	private RecepcionistaDAO recepcionistaDAO;
	

    // Constructor
	/**
	 * Constructor de la clase UsuarioGestion.
	 * 
	 * Inicializa los distintos objetos DAO necesarios para gestionar
	 * las operaciones de persistencia de cada tipo de usuario del sistema.
	 */
    public UsuarioGestion() {
        this.socioDAO = new SocioDAO();
        this.entrenadorDAO = new EntrenadorDAO();
        this.administradorDAO = new AdministradorDAO();
        this.recepcionistaDAO = new RecepcionistaDAO();
    }
    
    //comprobar si existe un Usuario
    
    /**
     * Comprueba si existe un usuario registrado en el sistema con el DNI indicado.
     * 
     * La búsqueda se realiza sobre todos los tipos de usuario disponibles
     * (socios, entrenadores, administradores y recepcionistas).
     * 
     * @param dni DNI del usuario a comprobar
     * @return true si existe algún usuario con ese DNI; false en caso contrario
     */
  
    private boolean existeUsuario(String dni) {
        
        boolean existe;

        existe = socioDAO.existeSocio(dni);

        if (!existe) {
            existe = entrenadorDAO.existeEntrenador(dni);
        }

        if (!existe) {
            existe = administradorDAO.existeAdministrador(dni);
        }

        if (!existe) {
            existe = recepcionistaDAO.existeRecepcionista(dni);
        }

        return existe;
    }
    
    /**
     * Comprueba si ya existe un socio registrado
     * con el DNI indicado.
     * 
     * @param dni DNI del socio a comprobar
     * @return Resultado indicando si existe o está disponible
     */
    
    public ResultadoGestion existeSocio(String dni) {

        ResultadoGestion resultado;

        if (socioDAO.existeSocio(dni)) {
            resultado = ResultadoGestion.YA_EXISTE;
        } else {
            resultado = ResultadoGestion.DISPONIBLE;
        }

        return resultado;
    }    

    // ALTA SOCIO
    
    /**
     * Da de alta un nuevo socio si no existe previamente un usuario con el mismo DNI.
     *
     * @param dni DNI del socio
     * @param nombre Nombre del socio
     * @param apellido1 Primer apellido
     * @param apellido2 Segundo apellido
     * @return Resultado de la operación de alta
     */
    
    public ResultadoGestion altaSocio(String dni, String nombre, String apellido1, String apellido2) {

        ResultadoGestion resultado;

        if (existeUsuario(dni)) {
            resultado = ResultadoGestion.YA_EXISTE;
        } else {
           
        	Socio socio = new Socio(dni, nombre, apellido1, apellido2);

            socioDAO.insertarSocio(socio);
           
            resultado = ResultadoGestion.OK;
        }

        return resultado;
    }
    
    //ALTA ENTRENADOR
    /**
     * Da de alta un nuevo entrenador si no existe previamente un usuario con el mismo DNI.
     *
     * @param dni DNI del entrenador
     * @param nombre Nombre del entrenador
     * @param apellido1 Primer apellido
     * @param apellido2 Segundo apellido
     * @return Resultado de la operación de alta
     */
    
    public ResultadoGestion altaEntrenador(String dni, String nombre, String apellido1, String apellido2) {

        ResultadoGestion resultado;

        if (existeUsuario(dni)) {
            resultado = ResultadoGestion.YA_EXISTE;
        } else {
           
        	Entrenador entrenador = new Entrenador(dni, nombre, apellido1, apellido2);

            entrenadorDAO.insertarEntrenador(entrenador);
           
            resultado = ResultadoGestion.OK;
        }

        return resultado;
    }
    
    //ALTA ADMINISTRADOR
    
    /**
     * Da de alta un nuevo administrador si no existe previamente un usuario con el mismo DNI.
     *
     * @param dni DNI del adminsitrador
     * @param nombre Nombre del administrador
     * @param apellido1 Primer apellido
     * @param apellido2 Segundo apellido
     * @return Resultado de la operación de alta
     */
    
    public ResultadoGestion altaAdministrador(String dni, String nombre, String apellido1, String apellido2) {

        ResultadoGestion resultado;

        if (existeUsuario(dni)) {
            resultado = ResultadoGestion.YA_EXISTE;
        } else {
           
        	Administrador administrador = new Administrador(dni, nombre, apellido1, apellido2);

        	administradorDAO.insertarAdministrador(administrador);
           
            resultado = ResultadoGestion.OK;
        }

        return resultado;
    }

    //ALTA RECEPCIONISTA
    /**
     * Da de alta un nuevo recepcionista si no existe previamente un usuario con el mismo DNI.
     *
     * @param dni DNI del recepcionista
     * @param nombre Nombre del recepcionista
     * @param apellido1 Primer apellido
     * @param apellido2 Segundo apellido
     * @return Resultado de la operación de alta
     */
    
    public ResultadoGestion altaRecepcionista(String dni, String nombre, String apellido1, String apellido2) {

        ResultadoGestion resultado;

        if (existeUsuario(dni)) {
            resultado = ResultadoGestion.YA_EXISTE;
        } else {
           
        	Recepcionista recepcionista = new Recepcionista(dni, nombre, apellido1, apellido2);

        	recepcionistaDAO.insertarRecepcionista(recepcionista);
           
            resultado = ResultadoGestion.OK;
        }

        return resultado;
    }

  

    // BUSCAR usuario
    /**
     * Busca un usuario del sistema por su DNI.
     * 
     * La búsqueda se realiza sobre todos los tipos de usuario registrados
     * en el sistema hasta localizar una coincidencia.
     * 
     * @param dni DNI del usuario a buscar
     * @return El usuario encontrado si existe; null en caso contrario
     */
    public Usuario buscarUsuarioPorDni(String dni) {

        Usuario usuario;

        usuario = socioDAO.buscarPorDni(dni);

        if (usuario == null) {
            usuario = entrenadorDAO.buscarPorDni(dni);
        }

        if (usuario == null) {
            usuario = administradorDAO.buscarPorDni(dni);
        }

        if (usuario == null) {
            usuario = recepcionistaDAO.buscarPorDni(dni);
        }

        return usuario;
    }
    
   
    //ACTUALIZAR SOCIO
    /**
     * Actualiza la información de un socio existente en el sistema.
     * 
     * La actualización solo se realiza si existe previamente un socio
     * registrado con el DNI indicado.
     * 
     * @param dni DNI del socio a actualizar
     * @param nombre Nuevo nombre del socio
     * @param apellido1 Nuevo primer apellido del socio
     * @param apellido2 Nuevo segundo apellido del socio
     * @return Resultado de la operación de actualización
     */
    
    public ResultadoGestion actualizarSocio(String dni, String nombre, String apellido1, String apellido2) {
    	
    	ResultadoGestion resultadoActual;
    	Socio socio;

        if (!socioDAO.existeSocio(dni)) {
            resultadoActual = ResultadoGestion.ERROR;
        }

        else {
        	
        	socio = new Socio(dni, nombre, apellido1, apellido2);
        	socioDAO.actualizarSocio(socio);
        	resultadoActual = ResultadoGestion.OK;
        }
           
           return resultadoActual;
        
    }
    
    //ACTUALIZAR ENTRENADOR
    /**
     * Actualiza la información de un entrenador existente en el sistema.
     * 
     * La actualización solo se realiza si existe previamente un entrenador
     * registrado con el DNI indicado.
     * 
     * @param dni DNI del entrenador a actualizar
     * @param nombre Nuevo nombre del entrenador
     * @param apellido1 Nuevo primer apellido 
     * @param apellido2 Nuevo segundo apellido 
     * @return Resultado de la operación de actualización
     */
    public ResultadoGestion actualizarEntrenador(String dni, String nombre, String apellido1, String apellido2) {
    	
    	ResultadoGestion resultadoActual;
    	Entrenador entrenador;

        if (!entrenadorDAO.existeEntrenador(dni)) {
            resultadoActual = ResultadoGestion.ERROR;
        }

        else {
        	
        	entrenador = new Entrenador(dni, nombre, apellido1, apellido2);
        	entrenadorDAO.actualizarEntrenador(entrenador);
        	resultadoActual = ResultadoGestion.OK;
        }
           
           return resultadoActual;
        
    }
    
    //ACTUALIZAR ADMINISTRADOR
    /**
     * Actualiza la información de un administrador existente en el sistema.
     * 
     * La actualización solo se realiza si existe previamente un adminsitrador
     * registrado con el DNI indicado.
     * 
     * @param dni DNI del adminsitrador a actualizar
     * @param nombre Nuevo nombre del adminsitrador
     * @param apellido1 Nuevo primer apellido del 
     * @param apellido2 Nuevo segundo apellido del 
     * @return Resultado de la operación de actualización
     */
    public ResultadoGestion actualizarAdministrador(String dni, String nombre, String apellido1, String apellido2) {
    	
    	ResultadoGestion resultadoActual;
    	Administrador administrador;

        if (!administradorDAO.existeAdministrador(dni)) {
            resultadoActual = ResultadoGestion.ERROR;
        }

        else {
        	
        	administrador = new Administrador(dni, nombre, apellido1, apellido2);
        	administradorDAO.actualizarAdministrador(administrador);
        	resultadoActual = ResultadoGestion.OK;
        }
           
           return resultadoActual;
        
    }
    
    //ACTUALIZAR RECEPCIONISTA
    /**
     * Actualiza la información de un recepcionsita existente en el sistema.
     * 
     * La actualización solo se realiza si existe previamente un recepcionista
     * registrado con el DNI indicado.
     * 
     * @param dni DNI del recepcionsita a actualizar
     * @param nombre Nuevo nombre del recepcionista
     * @param apellido1 Nuevo primer apellido del 
     * @param apellido2 Nuevo segundo apellido del 
     * @return Resultado de la operación de actualización
     */
    public ResultadoGestion actualizarRecepcionista(String dni, String nombre, String apellido1, String apellido2) {
    	
    	ResultadoGestion resultadoActual;
    	Recepcionista recepcionista;

        if (!recepcionistaDAO.existeRecepcionista(dni)) {
            resultadoActual = ResultadoGestion.ERROR;
        }

        else {
        	
        	recepcionista = new Recepcionista(dni, nombre, apellido1, apellido2);
        	recepcionistaDAO.actualizarRecepcionista(recepcionista);
        	resultadoActual = ResultadoGestion.OK;
        }
           
           return resultadoActual;
        
    }
    
    // ELIMINAR SOCIO
    /**
     * Elimina un socio existente del sistema.
     * 
     * La eliminación solo se realiza si existe previamente un socio
     * registrado con el DNI indicado.
     * 
     * @param dni DNI del socio a eliminar
     * @return Resultado de la operación de eliminación
     */
    public ResultadoGestion eliminarSocio(String dni) {
    	
    	ResultadoGestion resultadoEliminar;

        if (!socioDAO.existeSocio(dni)) {
            resultadoEliminar = ResultadoGestion.ERROR;
            
        }else {

        	socioDAO.eliminarSocio(dni);

        	resultadoEliminar =  ResultadoGestion.OK;
        }
        
       return resultadoEliminar;
    }
    
    //ELIMINAR ENTRENADOR
    
    /**
     * Elimina un entrenador existente del sistema.
     * 
     * La eliminación solo se realiza si existe previamente un entrenador
     * registrado con el DNI indicado.
     * 
     * @param dni DNI del entrenador a eliminar
     * @return Resultado de la operación de eliminación
     */
   
    public ResultadoGestion eliminarEntrenador(String dni) {
    	
    	ResultadoGestion resultadoEliminar;

        if (!entrenadorDAO.existeEntrenador(dni)) {
            resultadoEliminar = ResultadoGestion.ERROR;
            
        }else {

        	entrenadorDAO.eliminarEntrenador(dni);

        	resultadoEliminar =  ResultadoGestion.OK;
        }
        
       return resultadoEliminar;
    }
    
    //ELIMINAR ADMINISTRADOR
    /**
     * Elimina un administador existente del sistema.
     * 
     * La eliminación solo se realiza si existe previamente un administrador
     * registrado con el DNI indicado.
     * 
     * @param dni DNI del administrador a eliminar
     * @return Resultado de la operación de eliminación
     */
    
  public ResultadoGestion eliminarAdministrador(String dni) {
    	
    	ResultadoGestion resultadoEliminar;

        if (!administradorDAO.existeAdministrador(dni)) {
            resultadoEliminar = ResultadoGestion.ERROR;
            
        }else {

        	administradorDAO.eliminarAdministrador(dni);

        	resultadoEliminar =  ResultadoGestion.OK;
        }
        
       return resultadoEliminar;
    }
  
  //ELIMINAR RECEPCIONISTA
  /**
   * Elimina un recepcionista existente del sistema.
   * 
   * La eliminación solo se realiza si existe previamente un recepcionista
   * registrado con el DNI indicado.
   * 
   * @param dni DNI del recepcionsita a eliminar
   * @return Resultado de la operación de eliminación
   */
  
  public ResultadoGestion eliminarRecepcionista(String dni) {
  	
  	ResultadoGestion resultadoEliminar;

      if (!recepcionistaDAO.existeRecepcionista(dni)) {
          resultadoEliminar = ResultadoGestion.ERROR;
          
      }else {

    	  recepcionistaDAO.eliminarRecepcionista(dni);

      	resultadoEliminar =  ResultadoGestion.OK;
      }
      
     return resultadoEliminar;
  }


}
