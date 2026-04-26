package bdDAO;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Maquina;
import dominio.Sala;
import enumerados.EstadoMaquina;

public class MaquinaDAO {
	
	
	// INSERTAR MÁQUINA
    public boolean insertarMaquina(Maquina m) {
        boolean insertado = false;

        String sentencia = "INSERT INTO maquina (estado, fechaCompra, fechaUltimoMantenimiento, marca, numeroSerie, sala_id, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

      //abro conexion
      		ConexionBD conexionBD = new ConexionBD();
      		conexionBD.abrirConexion();
      		
        try {
            PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);

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
        
      //cierro conexion
    	if (conexionBD != null) {
    	    conexionBD.cerrarConexion();
    	}

        return insertado;
        
      
    }

    // OBTENER MÁQUINAS POR SALA
    public List<Maquina> obtenerMaquinasPorSala(int salaId) {
        List<Maquina> lista = new ArrayList<>();
        

        String sentencia = "SELECT * FROM maquina WHERE sala_id = ?";
        
      //abro conexion
  		ConexionBD conexionBD = new ConexionBD();
  		conexionBD.abrirConexion();

        try {
            PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);;
            ps.setInt(1, salaId);

            ResultSet rs = ps.executeQuery();

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

                // String → ENUM
                maquina.setEstado(EstadoMaquina.valueOf(rs.getString("estado")));

                // Solo seteamos id de sala (no cargamos objeto completo)
                Sala sala = new Sala();
                sala.setId(rs.getInt("sala_id"));
                maquina.setSala(sala);

                lista.add(maquina);
            
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
      //cierro conexion
    	if (conexionBD != null) {
    	    conexionBD.cerrarConexion();
    	}

        return lista;
    }

    // ACTUALIZAR ESTADO
    public boolean actualizarEstado(String numeroSerie, String nuevoEstado) {
        boolean actualizado = false;

        String sentencia = "UPDATE maquina SET estado = ? WHERE numeroSerie = ?";
        
      //abro conexion
  		ConexionBD conexionBD = new ConexionBD();
  		conexionBD.abrirConexion();

        try {
            PreparedStatement ps = conexionBD.getConexion().prepareStatement(sentencia);;

            ps.setString(1, nuevoEstado);
            ps.setString(2, numeroSerie);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                actualizado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
      //cierro conexion
    	if (conexionBD != null) {
    	    conexionBD.cerrarConexion();
    	}

        return actualizado;
    }
}


