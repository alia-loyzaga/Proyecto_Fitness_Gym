package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * Panel principal del socio dentro del sistema Fitness Gym.
 * 
 * Permite acceder a funcionalidades como
 * Fitness Planner y gestión de reservas.
 * 
 * @author Alia
 * @version 1.0
 */
public class PanelSocioGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PanelSocioGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Panel de Socio");
        lblTitulo.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
        lblTitulo.setBounds(150, 30, 250, 30);
        contentPane.add(lblTitulo);

        JButton btnPlanner = new JButton("Fitness Planner");
        btnPlanner.setBounds(150, 100, 180, 35);
        contentPane.add(btnPlanner);

        btnPlanner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FitnessPlannerGUI planner = new FitnessPlannerGUI();
                planner.setVisible(true);
            }
        });

        JButton btnReserva = new JButton("Reservar Actividad");
        btnReserva.setBounds(150, 170, 180, 35);
        contentPane.add(btnReserva);

        btnReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	ReservaGUI reserva = new ReservaGUI();
            	reserva.setVisible(true);
            }
        });
        JButton btnCerrarSesion = new JButton("Volver");
        btnCerrarSesion.setBounds(150, 240, 180, 35);
        contentPane.add(btnCerrarSesion);

        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MenuPrincipalGUI menu = new MenuPrincipalGUI();
                menu.setVisible(true);

                dispose();
            }
        });
    }
}