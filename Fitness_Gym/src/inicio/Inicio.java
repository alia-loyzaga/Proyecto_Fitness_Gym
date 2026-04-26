package inicio;

import java.awt.EventQueue;

import GUI.SocioAltaGUI;


public class Inicio {

	public static void main(String[] args) {
		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SocioAltaGUI frame = new SocioAltaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	} // fin main

} // fin Inicio



