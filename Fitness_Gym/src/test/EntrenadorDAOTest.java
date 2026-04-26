package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bd_dao.EntrenadorDAO;
import dominio.Entrenador;

public class EntrenadorDAOTest {

    private final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
    private final String dniPrueba = "88888888E";
    
    private void asegurarEntrenadorPrueba() {

        if (entrenadorDAO.existeEntrenador(dniPrueba)) {
            entrenadorDAO.eliminarEntrenador(dniPrueba);
        }

        Entrenador entrenador =
                new Entrenador(dniPrueba, "Pedro", "Ruiz", "Gomez");

        entrenadorDAO.insertarEntrenador(entrenador);
    }

    @Test
    public void testInsertarEntrenador() {
    	
    

        if (entrenadorDAO.existeEntrenador(dniPrueba)) {
            entrenadorDAO.eliminarEntrenador(dniPrueba);
        }

        Entrenador entrenador =
                new Entrenador(dniPrueba, "Pedro", "Ruiz", "Gomez");

        entrenadorDAO.insertarEntrenador(entrenador);

        assertTrue(entrenadorDAO.existeEntrenador(dniPrueba));
    }

    @Test
    public void testBuscarEntrenador() {
    	
    	asegurarEntrenadorPrueba();

        Entrenador entrenador =
                entrenadorDAO.buscarPorDni(dniPrueba);

        assertNotNull(entrenador);
        assertEquals("Pedro", entrenador.getNombre());
    }

    @Test
    public void testExisteEntrenador() {
    	
    	asegurarEntrenadorPrueba();

        assertTrue(entrenadorDAO.existeEntrenador(dniPrueba));
    }

    @Test
    public void testActualizarEntrenador() {
    	
    	asegurarEntrenadorPrueba();

        Entrenador entrenador =
                entrenadorDAO.buscarPorDni(dniPrueba);

        assertNotNull(entrenador);

        entrenador.setNombre("Luis");

        entrenadorDAO.actualizarEntrenador(entrenador);

        Entrenador actualizado =
                entrenadorDAO.buscarPorDni(dniPrueba);

        assertEquals("Luis", actualizado.getNombre());
    }

    @Test
    public void testEliminarEntrenador() {
    	
    	

        entrenadorDAO.eliminarEntrenador(dniPrueba);

        assertFalse(entrenadorDAO.existeEntrenador(dniPrueba));
    }
}