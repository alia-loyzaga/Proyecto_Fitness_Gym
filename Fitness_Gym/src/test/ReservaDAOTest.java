package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bd_dao.ReservaDAO;
import dominio.ActividadProgramada;
import dominio.Reserva;
import dominio.Socio;
/**
 * Clase de pruebas destinada a validar
 * las funcionalidades principales de ReservaDAO.
 * 
 * @author Alia
 * @version 1.0
 */
public class ReservaDAOTest {

    private final ReservaDAO reservaDAO = new ReservaDAO();

    private final String dniPrueba = "12345678A";
    private final int actividadProgramadaIdPrueba = 31;
    private final int reservaIdPrueba = 72;

    @Test
    public void testInsertarReserva() {

        Socio socio = new Socio();
        socio.setDni(dniPrueba);

        ActividadProgramada actividadProgramada = new ActividadProgramada();
        actividadProgramada.setId(actividadProgramadaIdPrueba);

        Reserva reserva = new Reserva();
        reserva.setSocio(socio);
        reserva.setActividadProgramada(actividadProgramada);

        assertDoesNotThrow(() -> reservaDAO.insertarReserva(reserva));
    }

    @Test
    public void testBuscarReserva() {

    	Reserva reserva = reservaDAO.buscarPorId(reservaIdPrueba);

        assertNotNull(reserva);
        assertEquals(dniPrueba, reserva.getSocio().getDni());
    }

    @Test
    public void testExisteReserva() {

    	assertTrue(reservaDAO.existeReserva(reservaIdPrueba));
    }

    @Test
    public void testEliminarReserva() {

    	reservaDAO.eliminarReserva(reservaIdPrueba);

        assertFalse(reservaDAO.existeReserva(1));
    }
}