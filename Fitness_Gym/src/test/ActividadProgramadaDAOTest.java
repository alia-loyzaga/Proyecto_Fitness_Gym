package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import bd_dao.ActividadProgramadaDAO;
import dominio.Actividad;
import dominio.ActividadProgramada;
import dominio.Entrenador;
import dominio.Sala;

public class ActividadProgramadaDAOTest {

    private final ActividadProgramadaDAO actividadProgramadaDAO =
            new ActividadProgramadaDAO();

    private final int idPrueba = 32;

    @Test
    public void testBuscarActividadProgramada() {

        ActividadProgramada actividadProgramada =
                actividadProgramadaDAO.buscarPorId(idPrueba);

        assertNotNull(actividadProgramada);
        assertEquals(idPrueba, actividadProgramada.getId());
    }

    @Test
    public void testExisteActividadProgramada() {

        assertTrue(
                actividadProgramadaDAO.existeActividadProgramada(idPrueba)
        );
    }

    @Test
    public void testActualizarActividadProgramada() {

        ActividadProgramada actividadProgramada =
                actividadProgramadaDAO.buscarPorId(idPrueba);

        assertNotNull(actividadProgramada);

        actividadProgramada.setFechaHoraFin(
                actividadProgramada.getFechaHoraFin().plusHours(1)
        );

        actividadProgramadaDAO.actualizarActividadProgramada(
                actividadProgramada
        );

        ActividadProgramada actualizada =
                actividadProgramadaDAO.buscarPorId(idPrueba);

        assertEquals(
                actividadProgramada.getFechaHoraFin(),
                actualizada.getFechaHoraFin()
        );
    }

    @Test
    public void testInsertarActividadProgramada() {

        Actividad actividad = new Actividad();
        actividad.setId(1);

        Sala sala = new Sala();
        sala.setId(2);

        Entrenador entrenador = new Entrenador();
        entrenador.setDni("12345678A");

        ActividadProgramada nueva = new ActividadProgramada();

        nueva.setActividad(actividad);
        nueva.setSala(sala);
        nueva.setEntrenador(entrenador);
        nueva.setFechaHoraInicio(LocalDateTime.now().plusDays(15));
        nueva.setFechaHoraFin(LocalDateTime.now().plusDays(15).plusHours(1));

        assertDoesNotThrow(() ->
                actividadProgramadaDAO.insertarActividadProgramada(nueva)
        );
    }
}