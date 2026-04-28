package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dominio.Actividad;
import dominio.ActividadProgramada;
import dominio.Entrenador;
import dominio.Reserva;
import dominio.Sala;
import dominio.Socio;
import enumerados.EstadoReserva;
import enumerados.EstadoSocio;
import enumerados.Nivel;
import enumerados.TipoPlan;
/**
 * Clase de pruebas destinada a validar
 * las funcionalidades principales de Socio.
 * 
 * @author Alia
 * @version 1.0
 */
public class SocioTest {

	 @Test
	    void testEsMorosoTrue() {
	        Socio socio = new Socio();
	        socio.setEstado(EstadoSocio.MOROSO);

	        assertTrue(socio.esMoroso());
	    }

	    @Test
	    void testEsMorosoFalse() {
	        Socio socio = new Socio();
	        socio.setEstado(EstadoSocio.ACTIVO);

	        assertFalse(socio.esMoroso());
	    }

	    @Test
	    void testActualizarEstado() {
	        Socio socio = new Socio();
	        socio.setEstado(EstadoSocio.ACTIVO);

	        socio.actualizarEstado(EstadoSocio.BAJA);

	        assertEquals(EstadoSocio.BAJA, socio.getEstado());
	    }


	@Test
	void testTieneReservaEnHorarioTrue() {
		Socio socio = new Socio("12345678A", "Ana", "Lopez", "Garcia");
		socio.setTipoPlan(TipoPlan.FULL);
		socio.setEstado(EstadoSocio.ACTIVO);

		Actividad actividad = new Actividad(1, "Yoga", "Clase yoga", Nivel.BASICO, 15.0);
		Sala sala = new Sala("Sala 1", 100, 20);
		Entrenador entrenador = new Entrenador();

		ActividadProgramada existente = new ActividadProgramada(actividad, sala, LocalDateTime.of(2026, 4, 27, 10, 0),
				LocalDateTime.of(2026, 4, 27, 11, 0), entrenador);

		Reserva reserva = new Reserva(1, existente, socio, EstadoReserva.CONFIRMADA);
		socio.getListaReservas().add(reserva);

		ActividadProgramada nueva = new ActividadProgramada(actividad, sala, LocalDateTime.of(2026, 4, 27, 10, 30),
				LocalDateTime.of(2026, 4, 27, 11, 30), entrenador);

		assertTrue(socio.tieneReservaEnHorario(nueva));
	}

	@Test
	void testTieneReservaEnHorarioFalse() {
		Socio socio = new Socio("12345678A", "Ana", "Lopez", "Garcia");
		socio.setTipoPlan(TipoPlan.FULL);
		socio.setEstado(EstadoSocio.ACTIVO);

		Actividad actividad = new Actividad(1, "Yoga", "Clase yoga", Nivel.BASICO, 15.0);
		Sala sala = new Sala("Sala 1", 100, 20);
		Entrenador entrenador = new Entrenador();

		ActividadProgramada existente = new ActividadProgramada(actividad, sala, LocalDateTime.of(2026, 4, 27, 10, 0),
				LocalDateTime.of(2026, 4, 27, 11, 0), entrenador);

		Reserva reserva = new Reserva(1, existente, socio, EstadoReserva.CONFIRMADA);
		socio.getListaReservas().add(reserva);

		ActividadProgramada nueva = new ActividadProgramada(actividad, sala, LocalDateTime.of(2026, 4, 27, 12, 0),
				LocalDateTime.of(2026, 4, 27, 13, 0), entrenador);

		assertFalse(socio.tieneReservaEnHorario(nueva));
	}

}
