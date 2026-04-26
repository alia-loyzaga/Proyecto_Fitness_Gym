package test;



import static org.junit.jupiter.api.Assertions.*;

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

public class ActividadProgramadaTest {

    @Test
    public void testHayPlazasDisponiblesTrue() {

        Actividad actividad = new Actividad(1, "Yoga", "Clase", Nivel.BASICO, 15);
        Sala sala = new Sala("Sala 1", 100, 2);
        Entrenador entrenador = new Entrenador();

        ActividadProgramada actividadProgramada = new ActividadProgramada(
                actividad,
                sala,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                entrenador
        );

        Socio socio = new Socio("12345678A", "Ana", "Lopez", "Garcia");
        socio.setEstado(EstadoSocio.ACTIVO);
        socio.setTipoPlan(TipoPlan.FULL);

        Reserva reserva = new Reserva(1, actividadProgramada, socio, EstadoReserva.CONFIRMADA);

        actividadProgramada.getListaReservas().add(reserva);

        assertTrue(actividadProgramada.hayPlazasDisponibles());
    }

    @Test
    public void testHayPlazasDisponiblesFalse() {

        Actividad actividad = new Actividad(1, "Yoga", "Clase", Nivel.BASICO, 15);
        Sala sala = new Sala("Sala 1", 100, 1);
        Entrenador entrenador = new Entrenador();

        ActividadProgramada actividadProgramada = new ActividadProgramada(
                actividad,
                sala,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                entrenador
        );

        Socio socio = new Socio("12345678A", "Ana", "Lopez", "Garcia");
        socio.setEstado(EstadoSocio.ACTIVO);
        socio.setTipoPlan(TipoPlan.FULL);

        Reserva reserva = new Reserva(1, actividadProgramada, socio, EstadoReserva.CONFIRMADA);

        actividadProgramada.getListaReservas().add(reserva);

        assertFalse(actividadProgramada.hayPlazasDisponibles());
    }
}