package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.Usuario;
import gestion.ResultadoGestion;
import gestion.UsuarioGestion;
/**
 * Clase de pruebas destinada a validar
 * las funcionalidades principales de UsuarioGestion.
 * 
 * @author Alia
 * @version 1.0
 */
public class UsuarioGestionTest {

    private final UsuarioGestion usuarioGestion = new UsuarioGestion();

    private final String dniPrueba = "77777777S";

    private void asegurarSocioBase() {

        usuarioGestion.eliminarSocio(dniPrueba);

        usuarioGestion.altaSocio(
                dniPrueba,
                "Laura",
                "Test",
                "Gestion"
        );
    }

    @Test
    public void testAltaSocio() {

        usuarioGestion.eliminarSocio(dniPrueba);

        ResultadoGestion resultado =
                usuarioGestion.altaSocio(
                        dniPrueba,
                        "Laura",
                        "Test",
                        "Gestion"
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }

    @Test
    public void testAltaSocioDuplicado() {

        asegurarSocioBase();

        ResultadoGestion resultado =
                usuarioGestion.altaSocio(
                        dniPrueba,
                        "Laura",
                        "Test",
                        "Gestion"
                );

        assertEquals(ResultadoGestion.YA_EXISTE, resultado);
    }

    @Test
    public void testBuscarUsuarioPorDni() {

        asegurarSocioBase();

        Usuario usuario =
                usuarioGestion.buscarUsuarioPorDni(dniPrueba);

        assertNotNull(usuario);
        assertEquals(dniPrueba, usuario.getDni());
    }

    @Test
    public void testActualizarSocio() {

        asegurarSocioBase();

        ResultadoGestion resultado =
                usuarioGestion.actualizarSocio(
                        dniPrueba,
                        "Maria",
                        "Actualizada",
                        "Gestion"
                );

        assertEquals(ResultadoGestion.OK, resultado);
    }

    @Test
    public void testEliminarSocio() {

        asegurarSocioBase();

        ResultadoGestion resultado =
                usuarioGestion.eliminarSocio(dniPrueba);

        assertEquals(ResultadoGestion.OK, resultado);
    }
}
