package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bd_dao.SocioDAO;
import dominio.Socio;

public class SocioDAOTest {

    private final SocioDAO socioDAO = new SocioDAO();
    private final String dniPrueba = "99999999Z";

    @Test
    public void testInsertarSocio() {

        if (socioDAO.existeSocio(dniPrueba)) {
            socioDAO.eliminarSocio(dniPrueba);
        }

        Socio socio = new Socio(dniPrueba, "Juan", "Perez", "Lopez");

        socioDAO.insertarSocio(socio);

        assertTrue(socioDAO.existeSocio(dniPrueba));
    }

    @Test
    public void testBuscarSocio() {

        Socio socio = socioDAO.buscarPorDni(dniPrueba);

        assertNotNull(socio);
        assertEquals("Juan", socio.getNombre());
    }

    @Test
    public void testExisteSocio() {

        assertTrue(socioDAO.existeSocio(dniPrueba));
    }

    @Test
    public void testActualizarSocio() {

        Socio socio = socioDAO.buscarPorDni(dniPrueba);

        socio.setNombre("Carlos");

        socioDAO.actualizarSocio(socio);

        Socio actualizado = socioDAO.buscarPorDni(dniPrueba);

        assertEquals("Carlos", actualizado.getNombre());
    }

    @Test
    public void testEliminarSocio() {

        socioDAO.eliminarSocio(dniPrueba);

        assertFalse(socioDAO.existeSocio(dniPrueba));
    }
}
