package bdDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private String db; 			// BD a conectar
    private String usuario; 	// Usuario que realiza la conexión
    private String password; 	// Contraseña del usuario
    private String servidor; 	// Servidor donde se encuentra la base de datos
    private Connection conexion;

    
    public ConexionBD() {
        this.db = "pyto";
        this.usuario = "root";
        this.password = "root";
        this.servidor = "jdbc:mysql://localhost:3306/";
        this.conexion = null;
    }
    
    public Connection getConexion() {
        return conexion;
    }
    
    
	/**
	 * Abre la Conexióna la BD
	 * 
	 * @return boolean True si conexión ok
	 */
    public boolean abrirConexion() {
    	boolean conexionOk = false;
        try {
            conexion = DriverManager.getConnection(servidor + db + "?serverTimezone=UTC", 
                    usuario, password);
            conexionOk = true;

        } catch (SQLException ex) {
            System.out.println(ex + "\n  --> Usuario, o base de datos, o pass incorrectos");
        }
        return conexionOk;
    } // fin abrirConexion
    
    
    /**
     * Cierra la conexión
     */
    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            } 
        } catch (SQLException ex) {
            System.out.println(ex + "\n --> Problema al cerrar la conexión. "
                    + "Comprueba la integridad de los datos");
        }
    } // fin cerrarConexion
    
    
    /**
     * Ejectua una sentencia UPDATE en la BD
     * 
     * @param sentencia String con la sentencia a ejecutar en BD
     * @return número de filas afectadas en la BD
     */
    public int ejecutaUpdate(String sentencia) {
        int n = 0;
        Statement st = null;
        try {
            st = conexion.createStatement();
            n = st.executeUpdate(sentencia);
        } catch (SQLException ex) {
            System.out.println(ex + "\n  --> Error al ejecutar Update()");
        }
        return n;
    } // Fin ejecutaUpdate
    
    
    /**
     * Ejectua una sentencia QUERY en la BD
     * 
     * @param busqueda en String
     * @return resultado de la consulta
     */
    public ResultSet ejecutaConsulta(String sentencia) {
        ResultSet resultado = null;
        Statement st = null;
        try {
            st = conexion.createStatement();
            resultado = st.executeQuery(sentencia);
        } catch (SQLException ex) {
            System.out.println(ex + "\n  --> Error al ejecutar Query()");
        }
        return resultado;
    } // fin ejecutaConsulta
    

}
