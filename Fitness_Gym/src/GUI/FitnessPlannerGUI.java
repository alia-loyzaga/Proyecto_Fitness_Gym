package GUI;


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

public class FitnessPlannerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private GestionActividades gestion;

	private JTextArea textArea;
	

	/**
	 * Create the frame.
	 */
	
	
	
	public FitnessPlannerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JButton boton = new JButton("Mostrar planificación");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<ActividadProgramada>lista = gestion.obtenerActividadesProgramadas();
				System.out.println("Número de actividades: " + lista.size());
				
				textArea.setText("");
				
				for(ActividadProgramada a:lista) {
					
					textArea.append(
							a.getActividad().getNombre() + " - " +
								    a.getSala().getNombre() + " - " +
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
		        json.generarFicheroSalasActividades();

		        textArea.append("\nJSON generado correctamente.\n");
		    }
		});
	
	

	}
}
