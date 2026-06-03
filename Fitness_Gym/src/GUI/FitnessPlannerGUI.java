package gui;


import json.Json;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.ActividadProgramada;
import gestion.GestionActividades;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
/**
 * Interfaz gráfica destinada a mostrar
 * la planificación de actividades del gimnasio.
 * 
 * Permite consultar actividades programadas
 * y generar datos en formato JSON.
 * 
 * @author Alia
 * @version 1.0
 */
public class FitnessPlannerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private GestionActividades gestion;

	private JTextArea textArea;
	

	/**
	 * Create the frame.
	 */
	
	
	
	public FitnessPlannerGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		gestion = new GestionActividades();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 90, 500, 220);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton boton = new JButton("Fitness Planner");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<ActividadProgramada>lista = gestion.obtenerActividadesProgramadas();
			
				
				textArea.setText("");
				
				for(ActividadProgramada a:lista) {
					
					textArea.append(
							"ID: " + a.getId() + " - " +
							a.getActividad().getNombre() + " - " +
									a.getSala().getNombre() + " - " +
									a.getEntrenador().getNombre() + " - " +
									a.getFechaHoraInicio() + "\n"
							);
					
				}
				
				
				
				
			}
		});
		boton.setBounds(200, 30, 250, 30);
		contentPane.add(boton);
		
		JButton btnJson = new JButton("Generar JSON");
		btnJson.setBounds(200, 340, 250, 30);
		contentPane.add(btnJson);

		btnJson.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        Json json = new Json();
		        json.generarFicheros();

		        textArea.append("\nJSON generado correctamente.\n");
		    }
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(200, 390, 250, 30);
		contentPane.add(btnVolver);

		btnVolver.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        dispose();
		    }
		});
	
	

	}
}
