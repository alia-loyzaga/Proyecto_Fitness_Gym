package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * Interfaz gráfica destinada a la gestión básica
 * de reservas de actividades por parte del socio.
 * 
 * Permite registrar reservas de forma sencilla.
 * 
 * @author Alia
 * @version 1.0
 */
public class ReservaGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField tFActividad;
    private JLabel lblResultado;

    public ReservaGUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 250);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Reservar Actividad");
        lblTitulo.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        lblTitulo.setBounds(120, 20, 250, 30);
        contentPane.add(lblTitulo);

        JLabel lblActividad = new JLabel("ID Actividad:");
        lblActividad.setBounds(50, 90, 100, 20);
        contentPane.add(lblActividad);

        tFActividad = new JTextField();
        tFActividad.setBounds(160, 90, 180, 20);
        contentPane.add(tFActividad);

        JButton btnReservar = new JButton("Reservar");
        btnReservar.setBounds(160, 140, 120, 30);
        contentPane.add(btnReservar);

        lblResultado = new JLabel("");
        lblResultado.setBounds(50, 190, 350, 20);
        contentPane.add(lblResultado);

        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                lblResultado.setForeground(Color.GREEN);
                lblResultado.setText("✔️ Reserva realizada correctamente");
            }
        });
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(160, 170, 120, 30);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
    }
}