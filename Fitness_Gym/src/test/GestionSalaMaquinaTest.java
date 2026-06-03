package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.Sala;
import gestion.GestionSalaMaquina;
import gestion.ResultadoGestion;
/**
 * Clase de pruebas destinada a validar
 * las funcionalidades principales de GestionSalaMaquina.
 * 
 * @author Alia
 * @version 1.0
 */
public class GestionSalaMaquinaTest {

    private final GestionSalaMaquina gestionSalaMaquina =
            new GestionSalaMaquina();

    private final String nombreSalaPrueba = "SalaTest";

    private void asegurarSalaBase() {

        gestionSalaMaquina.eliminarSala(nombreSalaPrueba);

        gestionSalaMaquina.altaSala(
                nombreSalaPrueba,
                120,
                40
        );
    }

    @Test
    public void testAltaSala() {

        gestionSalaMaquina.eliminarSala(nombreSalaPrueba);

        ResultadoGestion resultado =
                gestionSalaMaquina.altaSala(
                        nombreSalaPrueba,
                        120,
                        40
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }

    @Test
    public void testAltaSalaDuplicada() {

        asegurarSalaBase();

        ResultadoGestion resultado =
                gestionSalaMaquina.altaSala(
                        nombreSalaPrueba,
                        120,
                        40
                );

        assertEquals(ResultadoGestion.YA_EXISTE, resultado);
    }

    @Test
    public void testBuscarSala() {

        asegurarSalaBase();

        Sala sala =
                gestionSalaMaquina.buscarSalaPorNombre(
                        nombreSalaPrueba
                );

        assertNotNull(sala);
        assertEquals(nombreSalaPrueba, sala.getNombre());
    }

    @Test
    public void testActualizarSala() {

        asegurarSalaBase();

        ResultadoGestion resultado =
                gestionSalaMaquina.actualizarSala(
                        nombreSalaPrueba,
                        150,
                        50
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }

    @Test
    public void testEliminarSala() {

        asegurarSalaBase();

        ResultadoGestion resultado =
                gestionSalaMaquina.eliminarSala(
                        nombreSalaPrueba
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }
}