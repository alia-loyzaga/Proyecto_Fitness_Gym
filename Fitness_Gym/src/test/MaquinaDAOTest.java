package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import bd_dao.MaquinaDAO;
import dominio.Maquina;
import dominio.Sala;
import enumerados.EstadoMaquina;

/**
 * Clase de pruebas destinada a validar
 * las funcionalidades principales de MaquinaDAO.
 * 
 * @author Alia
 * @version 1.0
 */
public class MaquinaDAOTest {

    private final MaquinaDAO maquinaDAO = new MaquinaDAO();

    private final String numeroSeriePrueba = "TEST98765";

    private final int salaIdPrueba = 2;

    @Test
    public void testInsertarMaquina() {

        Sala sala = new Sala();
        sala.setId(salaIdPrueba);

        Maquina maquina = new Maquina(
                "Cinta",
                "Technogym",
                numeroSeriePrueba,
                LocalDate.now().minusYears(1),
                LocalDate.now().minusMonths(2),
                EstadoMaquina.OPERATIVA,
                sala
        );

        assertTrue(maquinaDAO.insertarMaquina(maquina));
    }

    @Test
    public void testObtenerMaquinasPorSala() {

        if (maquinaDAO.obtenerMaquinasPorSala(salaIdPrueba).isEmpty()) {

            Sala sala = new Sala();
            sala.setId(salaIdPrueba);

            Maquina maquina = new Maquina(
                    "Cinta",
                    "Technogym",
                    numeroSeriePrueba,
                    LocalDate.now().minusYears(1),
                    LocalDate.now().minusMonths(2),
                    EstadoMaquina.OPERATIVA,
                    sala
            );

            maquinaDAO.insertarMaquina(maquina);
        }

        List<Maquina> maquinas =
                maquinaDAO.obtenerMaquinasPorSala(salaIdPrueba);

        assertNotNull(maquinas);
        assertFalse(maquinas.isEmpty());
    }
    @Test
    public void testActualizarEstadoMaquina() {

        boolean actualizado =
                maquinaDAO.actualizarEstado(
                        numeroSeriePrueba,
                        EstadoMaquina.EN_REVISION.name()
                );

        assertTrue(actualizado);
    }
}