package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.Actividad;
import enumerados.Nivel;
import gestion.GestionActividades;
import gestion.ResultadoGestion;
/**
 * Clase de pruebas destinada a validar
 * las funcionalidades principales de GestionActividades.
 * 
 * @author Alia
 * @version 1.0
 */
public class GestionActividadesTest {

    private final GestionActividades gestionActividades =
            new GestionActividades();

    private final String nombrePrueba = "PilatesGestion";

    private void asegurarActividadBase() {

        gestionActividades.eliminarActividad(nombrePrueba);

        gestionActividades.altaActividad(
                500,
                nombrePrueba,
                "Actividad de prueba",
                Nivel.BASICO,
                20
        );
    }

    @Test
    public void testAltaActividad() {

        gestionActividades.eliminarActividad(nombrePrueba);

        ResultadoGestion resultado =
                gestionActividades.altaActividad(
                        500,
                        nombrePrueba,
                        "Actividad de prueba",
                        Nivel.BASICO,
                        20
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }

    @Test
    public void testAltaActividadDuplicada() {

        asegurarActividadBase();

        ResultadoGestion resultado =
                gestionActividades.altaActividad(
                        500,
                        nombrePrueba,
                        "Actividad de prueba",
                        Nivel.BASICO,
                        20
                );

        assertEquals(ResultadoGestion.YA_EXISTE, resultado);
    }

    @Test
    public void testBuscarActividad() {

        asegurarActividadBase();

        Actividad actividad =
                gestionActividades.buscarActividadPorNombre(
                        nombrePrueba
                );

        assertNotNull(actividad);
        assertEquals(nombrePrueba, actividad.getNombre());
    }

    @Test
    public void testActualizarActividad() {

        asegurarActividadBase();

        ResultadoGestion resultado =
                gestionActividades.actualizarActividad(
                        500,
                        nombrePrueba,
                        "Descripcion actualizada",
                        Nivel.INTERMEDIO,
                        25
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }

    @Test
    public void testEliminarActividad() {

        asegurarActividadBase();

        ResultadoGestion resultado =
                gestionActividades.eliminarActividad(
                        nombrePrueba
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }
}
