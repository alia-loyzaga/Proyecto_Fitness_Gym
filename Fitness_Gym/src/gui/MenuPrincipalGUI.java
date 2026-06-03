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
 * Interfaz gráfica principal del sistema Fitness Gym.
 * 
 * Permite al usuario acceder al registro
 * o inicio de sesión dentro de la aplicación.
 * 
 * @author Alia
 * @version 1.0
 */
public class MenuPrincipalGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public MenuPrincipalGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Fitness Gym");
        lblTitulo.setFont(new Font("Tw Cen MT", Font.PLAIN, 28));
        lblTitulo.setBounds(150, 30, 200, 40);
        contentPane.add(lblTitulo);

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setBounds(130, 100, 180, 30);
        contentPane.add(btnRegistro);

        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                SocioAltaGUI registro = new SocioAltaGUI();
                registro.setVisible(true);

                dispose();
            }
        });

        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(130, 160, 180, 30);
        contentPane.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                LoginSocioGUI login = new LoginSocioGUI();
                login.setVisible(true);

                dispose();
            }
        });
    }
}