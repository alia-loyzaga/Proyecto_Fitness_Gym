package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bd_dao.ActividadDAO;
import dominio.Actividad;
import enumerados.Nivel;

public class ActividadDAOTest {

    private final ActividadDAO actividadDAO = new ActividadDAO();
    private final String nombrePrueba = "YogaTest";

    @Test
    public void testInsertarActividad() {

        if (actividadDAO.existeActividad(nombrePrueba)) {
            actividadDAO.eliminarActividad(nombrePrueba);
        }

        Actividad actividad = new Actividad(
                0,
                nombrePrueba,
                "Actividad de prueba",
                Nivel.BASICO,
                15
        );

        actividadDAO.insertarActividad(actividad);

        assertTrue(actividadDAO.existeActividad(nombrePrueba));
    }

    @Test
    public void testBuscarActividad() {

        Actividad actividad = actividadDAO.buscarPorNombre(nombrePrueba);

        assertNotNull(actividad);
        assertEquals(nombrePrueba, actividad.getNombre());
    }

    @Test
    public void testExisteActividad() {

        assertTrue(actividadDAO.existeActividad(nombrePrueba));
    }

    @Test
    public void testActualizarActividad() {

        if (!actividadDAO.existeActividad(nombrePrueba)) {
            Actividad nueva = new Actividad(
                    0,
                    nombrePrueba,
                    "Actividad de prueba",
                    Nivel.BASICO,
                    15
            );
            actividadDAO.insertarActividad(nueva);
        }

        Actividad actividad = actividadDAO.buscarPorNombre(nombrePrueba);

        assertNotNull(actividad);

        actividad.setDescripcion("Nueva descripcion");

        actividadDAO.actualizarActividad(actividad);

        Actividad actualizada = actividadDAO.buscarPorNombre(nombrePrueba);

        assertEquals("Nueva descripcion", actualizada.getDescripcion());
    }

    @Test
    public void testEliminarActividad() {

        actividadDAO.eliminarActividad(nombrePrueba);

        assertFalse(actividadDAO.existeActividad(nombrePrueba));
    }
}