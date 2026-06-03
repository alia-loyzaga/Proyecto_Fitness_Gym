package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bd_dao.SalaDAO;
import dominio.Sala;
/**
 * Clase de pruebas destinada a validar
 * las funcionalidades principales de SalaDAO.
 * 
 * @author Alia
 * @version 1.0
 */
public class SalaDAOTest {

    private final SalaDAO salaDAO = new SalaDAO();
    private final String nombrePrueba = "SalaTest";

    @Test
    public void testInsertarSala() {

        if (salaDAO.existeSala(nombrePrueba)) {
            salaDAO.eliminarSala(nombrePrueba);
        }

        Sala sala = new Sala(nombrePrueba, 120, 25);

        salaDAO.insertarSala(sala);

        assertTrue(salaDAO.existeSala(nombrePrueba));
    }

    @Test
    public void testBuscarSala() {

        Sala sala = salaDAO.buscarPorNombre(nombrePrueba);

        assertNotNull(sala);
        assertEquals(nombrePrueba, sala.getNombre());
    }

    @Test
    public void testExisteSala() {

        assertTrue(salaDAO.existeSala(nombrePrueba));
    }

    @Test
    public void testActualizarSala() {

        Sala sala = salaDAO.buscarPorNombre(nombrePrueba);

        sala.setAforoMaximo(40);

        salaDAO.actualizarSala(sala);

        Sala actualizada = salaDAO.buscarPorNombre(nombrePrueba);

        assertEquals(40, actualizada.getAforoMaximo());
    }

    @Test
    public void testEliminarSala() {

        salaDAO.eliminarSala(nombrePrueba);

        assertFalse(salaDAO.existeSala(nombrePrueba));
    }
}
