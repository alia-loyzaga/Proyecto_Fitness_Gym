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

import dominio.Usuario;
import gestion.UsuarioGestion;
/**
 * Interfaz gráfica destinada al inicio de sesión
 * de socios registrados en el sistema.
 * 
 * Permite validar el acceso mediante DNI y nombre.
 * 
 *@author Alia
 *@version 1.0
 */
public class LoginSocioGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField tFDni;
    private JTextField tFNombre;
    private JLabel lblAdvertencia;

    private UsuarioGestion usuarioGestion;

    public LoginSocioGUI() {

        usuarioGestion = new UsuarioGestion();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar sesión");
        lblTitulo.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        lblTitulo.setBounds(140, 20, 200, 30);
        contentPane.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI");
        lblDni.setBounds(50, 80, 80, 20);
        contentPane.add(lblDni);

        tFDni = new JTextField();
        tFDni.setBounds(150, 80, 180, 20);
        contentPane.add(tFDni);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(50, 120, 80, 20);
        contentPane.add(lblNombre);

        tFNombre = new JTextField();
        tFNombre.setBounds(150, 120, 180, 20);
        contentPane.add(tFNombre);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(150, 170, 120, 30);
        contentPane.add(btnEntrar);

        lblAdvertencia = new JLabel("");
        lblAdvertencia.setBounds(50, 220, 350, 20);
        contentPane.add(lblAdvertencia);

        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Usuario usuario = usuarioGestion.buscarUsuarioPorDni(tFDni.getText());

                if (usuario != null && usuario.getNombre().equalsIgnoreCase(tFNombre.getText())) {
                	
                    PanelSocioGUI panel = new PanelSocioGUI();
                    panel.setVisible(true);
                    

                    dispose();

                } else {

                    lblAdvertencia.setForeground(Color.RED);
                    lblAdvertencia.setText("⚠️ DNI o nombre incorrectos");
                }
            }
        });
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(300, 213, 100, 23);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MenuPrincipalGUI menu = new MenuPrincipalGUI();
                menu.setVisible(true);

                dispose();
            }
        });
    }
}