package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import dominio.Reserva;
import enumerados.EstadoReserva;

public class ReservaTest {

	 @Test
	    public void testConfirmarReserva() {

	        Reserva reserva = new Reserva();
	        reserva.setEstado(EstadoReserva.PENDIENTE);

	        reserva.confirmarReserva();

	        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
	    }

	    @Test
	    public void testCancelarReserva() {

	        Reserva reserva = new Reserva();
	        reserva.setEstado(EstadoReserva.PENDIENTE);

	        reserva.cancelarReserva();

	        assertEquals(EstadoReserva.CANCELADA, reserva.getEstado());
	    }
}
