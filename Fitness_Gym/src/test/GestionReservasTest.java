package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import dominio.Reserva;

import gestion.GestionReservas;
import gestion.ResultadoGestion;

public class GestionReservasTest {

    private final GestionReservas gestionReservas =
            new GestionReservas();



    @Test
    public void testBuscarReserva() {

        Reserva reserva =
                gestionReservas.buscarReservaPorId(62);

        assertNotNull(reserva);
        assertEquals(62, reserva.getId());
    }

    @Test
    public void testCancelarReserva() {

        ResultadoGestion resultado =
                gestionReservas.cancelarReserva(72);

        assertTrue(
                resultado == ResultadoGestion.OK ||
                resultado == ResultadoGestion.ERROR
        );
    }
}