package bd_dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Maquina;
import dominio.Sala;
import enumerados.EstadoMaquina;

/**
 * Clase DAO encargada de gestionar el acceso a la base de datos
 * para las máquinas del gimnasio.
 * 
 * Permite realizar operaciones de inserción, consulta y actualización
 * relacionadas con las máquinas y su estado dentro de las salas.
 * 
 * @author Alia
 * @version 1.0
 */
public class MaquinaDAO {
	
	
	// INSERTAR MÁQUINA
	/**
	 * Inserta una nueva máquina en la base de datos.
	 * 
	 * @param m Máquina a insertar
	 * @return true si la inserción fue correcta, false en caso contrario
	 */
    public boolean insertarMaquina(Maquina m) {
        boolean insertado = false;

        String sentencia = "INSERT INTO maquina (estado, fechaCompra, fechaUltimoMantenimiento, marca, numeroSerie, sala_id, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

      //abro conexion
      		ConexionBD conexionBD = new ConexionBD();
      		conexionBD.abrirConexion();
      		
      		 PreparedStatement ps = null;
      		
        try {
            ps = conexionBD.getConexion().prepareStatement(sentencia);

            ps.setString(1, m.getEstado().name()); // ENUM a String
            ps.setDate(2, Date.valueOf(m.getFechaCompra()));
            ps.setDate(3, Date.valueOf(m.getFechaUltimoMantenimiento()));
            ps.setString(4, m.getMarca());
            ps.setString(5, m.getNumeroSerie());
            ps.setInt(6, m.getSala().getId()); // objeto a id
            ps.setString(7, m.getTipo());


            int filas = ps.executeUpdate();
            

            if (filas > 0) {
                insertado = true;
            }
            
           

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			 // Ignorado: error al cerrar recursos
		}

        
      //cierro conexion
    
    	    conexionBD.cerrarConexion();
    	

        return insertado;
        
      
    }

    // OBTENER MÁQUINAS POR SALA
    /**
     * Obtiene todas las máquinas asociadas a una sala concreta.
     * 
     * @param salaId Identificador de la sala
     * @return Lista de máquinas pertenecientes a la sala
     */
    public List<Maquina> obtenerMaquinasPorSala(int salaId) {

        List<Maquina> lista = new ArrayList<>();

        String sentencia = "SELECT tipo, marca, numeroSerie, fechaCompra, fechaUltimoMantenimiento, estado, sala_id FROM maquina WHERE sala_id = ?";

        ConexionBD conexionBD = new ConexionBD();
        conexionBD.abrirConexion();

        try (
            PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia)
        ) {

            ps.setInt(1, salaId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Maquina maquina = new Maquina();

                    maquina.setTipo(rs.getString("tipo"));
                    maquina.setMarca(rs.getString("marca"));
                    maquina.setNumeroSerie(rs.getString("numeroSerie"));

                    Date fechaCompra = rs.getDate("fechaCompra");
                    if (fechaCompra != null) {
                        maquina.setFechaCompra(fechaCompra.toLocalDate());
                    }

                    Date fechaMant = rs.getDate("fechaUltimoMantenimiento");
                    if (fechaMant != null) {
                        maquina.setFechaUltimoMantenimiento(fechaMant.toLocalDate());
                    }

                    maquina.setEstado(
                            EstadoMaquina.valueOf(rs.getString("estado")));

                    Sala sala = new Sala();
                    sala.setId(rs.getInt("sala_id"));

                    maquina.setSala(sala);

                    lista.add(maquina);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conexionBD.cerrarConexion();

        return lista;
    }

    // ACTUALIZAR ESTADO
    /**
     * Actualiza el estado de una máquina según su número de serie.
     * 
     * @param numeroSerie Número de serie de la máquina
     * @param nuevoEstado Nuevo estado a asignar
     * @return true si la actualización fue correcta, false en caso contrario
     */
    public boolean actualizarEstado(String numeroSerie, String nuevoEstado) {
        boolean actualizado = false;

        String sentencia = "UPDATE maquina SET estado = ? WHERE numeroSerie = ?";
        
      //abro conexion
  		ConexionBD conexionBD = new ConexionBD();
  		conexionBD.abrirConexion();
  		
  		PreparedStatement ps = null;

        try {
            ps = conexionBD.getConexion().prepareStatement(sentencia);

            ps.setString(1, nuevoEstado);
            ps.setString(2, numeroSerie);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                actualizado = true;
            }
            
           

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			 // Ignorado: error al cerrar recursos
		}
        
      //cierro conexion
   
    	    conexionBD.cerrarConexion();
    	

        return actualizado;
    }
}


