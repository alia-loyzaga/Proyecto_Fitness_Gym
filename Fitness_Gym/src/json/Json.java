package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import bdDAO.SalaDAO;
import dominio.ActividadProgramada;
import dominio.Sala;
import gestion.GestionActividades;

public class Json {
	
	public void generarFicheroSalasActividades() {

        SalaDAO salaDAO = new SalaDAO();
        GestionActividades gestionActividades = new GestionActividades();

        List<Sala> salas = salaDAO.obtenerTodas();
        List<ActividadProgramada> actividadesProgramadas = gestionActividades.obtenerActividadesProgramadas();

        File carpeta = new File("json");

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try {

            FileWriter fw = new FileWriter("json/salas_actividades.json");

            fw.write("[\n");

            for (int i = 0; i < salas.size(); i++) {

                Sala sala = salas.get(i);

                fw.write("  {\n");
                fw.write("    \"sala\": \"" + sala.getNombre() + "\",\n");
                fw.write("    \"aforo\": " + sala.getAforoMaximo() + ",\n");
                fw.write("    \"actividades\": [\n");

                boolean primeraActividad = true;

                for (ActividadProgramada ap : actividadesProgramadas) {

                    if (ap.getSala().getNombre().equals(sala.getNombre())) {

                        if (!primeraActividad) {
                            fw.write(",\n");
                        }

                        fw.write("      {\n");
                        fw.write("        \"actividad\": \"" + ap.getActividad().getNombre() + "\",\n");
                        fw.write("        \"fechaInicio\": \"" + ap.getFechaHoraInicio() + "\",\n");
                        fw.write("        \"fechaFin\": \"" + ap.getFechaHoraFin() + "\",\n");
                        fw.write("        \"entrenador\": \"" + ap.getEntrenador().getDni() + "\"\n");
                        fw.write("      ");

                        primeraActividad = false;
                    }
                }

                fw.write("\n    ]\n");
                fw.write("  }");

                if (i < salas.size() - 1) {
                    fw.write(",");
                }

                fw.write("\n");
            }

            fw.write("]");

            fw.close();

            System.out.println("JSON generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al generar JSON.");
        }
    }

}
