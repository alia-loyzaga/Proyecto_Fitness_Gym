package inicio;

import java.awt.EventQueue;

import gui.MenuPrincipalGUI;


/**
 * Clase principal de inicio del sistema Fitness Gym.
 * 
 * Se encarga de lanzar la aplicación
 * iniciando la interfaz gráfica principal.
 * 
 * @author Alia
 * @version 1.0
 */
public class Inicio {

	public static void main(String[] args) {
		
	
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipalGUI frame = new MenuPrincipalGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
	} // fin main

} // fin Inicio



