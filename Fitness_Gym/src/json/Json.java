package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import bd_dao.SalaDAO;
import dominio.ActividadProgramada;
import dominio.Sala;
import gestion.GestionActividades;

/**
 * Clase encargada de generar y exportar
 * datos del sistema en formato JSON.
 * 
 * Facilita la integración de información
 * entre la aplicación Java y el proyecto web.
 * 
 * @author Alia
 * @version 1.0
 */
public class Json {
	
	/**
	 * Genera los ficheros JSON necesarios
	 * para exportar datos del sistema al entorno web.
	 */
	public void generarFicheros() {

        SalaDAO salaDAO = new SalaDAO();
        GestionActividades gestionActividades = new GestionActividades();

        List<Sala> salas = salaDAO.obtenerTodas();
        List<ActividadProgramada> actividadesProgramadas = gestionActividades.obtenerActividadesProgramadas();

        String finLinea = "\",\n";
        File carpeta = new File("json");

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        
        
        try (FileWriter fw = new FileWriter("json/salas_actividades.json")){

            fw.write("[\n");

            for (int i = 0; i < salas.size(); i++) {

                Sala sala = salas.get(i);

                fw.write("  {\n");
                fw.write("    \"sala\": \"" + sala.getNombre() + finLinea);
                fw.write("    \"aforo\": " + sala.getAforoMaximo() + ",\n");
                fw.write("    \"actividades\": [\n");

                boolean primeraActividad = true;

                for (ActividadProgramada ap : actividadesProgramadas) {

                    if (ap.getSala().getNombre().equals(sala.getNombre())) {

                        if (!primeraActividad) {
                            fw.write(",\n");
                        }

                        fw.write("      {\n");
                        fw.write("        \"actividad\": \"" + ap.getActividad().getNombre() + finLinea);
                        fw.write("        \"fechaInicio\": \"" + ap.getFechaHoraInicio() + finLinea);
                        fw.write("        \"fechaFin\": \"" + ap.getFechaHoraFin() + finLinea);
                        fw.write("        \"plazasLibres\": " + sala.getAforoMaximo() + ",\n");
                        
                        if(ap.getEntrenador() != null && ap.getEntrenador().getDni() != null) {
                        	 fw.write("        \"entrenador\": \"" 
                        		        + ap.getEntrenador().getNombre() + " "
                        		        + ap.getEntrenador().getApellido1()
                        		        + "\"\n");
                        } else {
                            fw.write("        \"entrenador\": \"Pendiente\"\n");
                        }
                        
                        fw.write("      }");

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

          

            System.out.println("JSON generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al generar JSON.");
            
        }
        
	}

}
