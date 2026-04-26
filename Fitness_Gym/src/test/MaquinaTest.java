package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dominio.Maquina;
import dominio.Sala;
import enumerados.EstadoMaquina;

public class MaquinaTest {

    @Test
    public void testNecesitaMantenimientoFalse() {

        Sala sala = new Sala("Musculación", 200, 50);

        Maquina maquina = new Maquina(
                "Cinta",
                "Technogym",
                "ABC123",
                LocalDate.now().minusYears(2),
                LocalDate.now().minusMonths(5),
                EstadoMaquina.OPERATIVA,
                sala
        );

        maquina.setMesUltimoMantenimiento(LocalDate.now().minusMonths(5).getMonthValue());

        assertFalse(maquina.necesitaMantenimiento());
    }

    @Test
    public void testNecesitaMantenimientoTrue() {

        Sala sala = new Sala("Cardio", 200, 50);

        Maquina maquina = new Maquina(
                "Bicicleta",
                "LifeFitness",
                "XYZ789",
                LocalDate.now().minusYears(5),
                LocalDate.now().minusMonths(8),
                EstadoMaquina.OPERATIVA,
                sala
        );

        maquina.setMesUltimoMantenimiento(LocalDate.now().minusMonths(8).getMonthValue());

        assertTrue(maquina.necesitaMantenimiento());
    }
}