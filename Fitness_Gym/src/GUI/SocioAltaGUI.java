package gui;

import gestion.UsuarioGestion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gestion.ResultadoGestion;


public class SocioAltaGUI extends JFrame {


	private static final long serialVersionUID = 1L;

	// Unión capa GUI (interfaz gráfica) con Gestión (Controlador)
	 private UsuarioGestion socioGestion;
	

	// JPanel
	private JPanel contentPane;

	// Etiquetas
	private JLabel lblTituloPanel;
	private JLabel lblDNI;
	private JLabel lblNombre;
	private JLabel lblApellido1;
	private JLabel lblApellido2;
	private JLabel lblAdvertencia;

	// Botones
	private JButton btnEnviar;

	// Campos de Texto
	private JTextField tFDni;
	private JTextField tFNombre;
	private JTextField tFApellido1;
	private JTextField tFApellido2;

	// main --- Ya no lanzamos la GUI desde la propia clase

	// --------- Constructor -------------

	public SocioAltaGUI() {

		// Unión GUI con Gestión (Controlador)

		 socioGestion = new UsuarioGestion();

		// Configuración de JFrame (ventana)
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		// Métodos para elementos del JFrame
		configurarPaneles();
		configuraEtiquetas();
		configurarCamposTexto();
		configurarBotones();
	}

	// --------- Configura Etiquetas -------------

	private void configurarPaneles() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	} // fin configurarPaneles

	// --------- Configura Etiquetas -------------

	private void configuraEtiquetas() {

		// Etiqueta general - Titulo
		lblTituloPanel = new JLabel("Alta Cliente");
		lblTituloPanel.setFont(new Font("Tw Cen MT", Font.PLAIN, 21));
		lblTituloPanel.setBounds(35, 11, 102, 23);
		contentPane.add(lblTituloPanel);

		// Etiqueta DNI
		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(35, 62, 46, 14);
		contentPane.add(lblDNI);

		// Etiqueta Nombre
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(35, 112, 80, 14);
		contentPane.add(lblNombre);

		// Etiqueta Apellido1
		lblApellido1 = new JLabel("1º Apellido");
		lblApellido1.setBounds(35, 137, 84, 14);
		contentPane.add(lblApellido1);

		// Etiqueta Apellido2
		lblApellido2 = new JLabel("2º Apellido");
		lblApellido2.setBounds(35, 162, 84, 14);
		contentPane.add(lblApellido2);

		// Etiqueta Avertencia - Incialmente sin texto
		lblAdvertencia = new JLabel();
		lblAdvertencia.setForeground(new Color(0, 0, 0));
		lblAdvertencia.setBounds(35, 245, 350, 20);
		contentPane.add(lblAdvertencia);

	} // fin configurarEtiquetas

	// --------- Configura Botones -------------

	private void configurarBotones() {
		// Botón Enviar
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(30, 213, 89, 23);
		contentPane.add(btnEnviar);

		// Listener Boton Enviar
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listenerBotonEnviar();
			}
		});

		JButton btnPlanner = new JButton("Ver planificación");
		btnPlanner.setBounds(150, 213, 160, 23);
		contentPane.add(btnPlanner);

		btnPlanner.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        FitnessPlannerGUI planner = new FitnessPlannerGUI();
		        planner.setVisible(true);

		    }
		});
	} // fin configurarBotones

	// --------- Configura Campos Texto -------------

	private void configurarCamposTexto() {

		// Campo de Texto - DNI
		tFDni = new JTextField();
		tFDni.setBounds(152, 59, 152, 20);
		contentPane.add(tFDni);
		tFDni.setColumns(10);

		// Listener para verificar si dni existe
		tFDni.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				listenerRellenoDni();
			}
		});

		// Campo de Texto - Nombre
		tFNombre = new JTextField();
		tFNombre.setBounds(152, 109, 152, 20);
		contentPane.add(tFNombre);
		tFNombre.setColumns(10);

		// Campo de Texto - Apellido1
		tFApellido1 = new JTextField();
		tFApellido1.setBounds(152, 134, 152, 20);
		contentPane.add(tFApellido1);
		tFApellido1.setColumns(10);

		// Campo de Texto - Apellido2
		tFApellido2 = new JTextField();
		tFApellido2.setBounds(152, 162, 152, 20);
		contentPane.add(tFApellido2);
		tFApellido2.setColumns(10);

	} // fin configurarCamposTexto

	// ---- Listeners ------------

	private void listenerBotonEnviar() {

		ResultadoGestion resultado;

		// Intenta realizar el alta utilizando la capa de Gestión (Controlador)
		// Recibe el resultado
		resultado = socioGestion.altaSocio(tFDni.getText(), tFNombre.getText(), tFApellido1.getText(),
				tFApellido2.getText());

		// Según el resultado informa al usuario
		switch (resultado) {
			case OK:
				lblAdvertencia.setForeground(Color.GREEN);
				lblAdvertencia.setText("✔️   Socio incluido");
				break;
			case YA_EXISTE:
				lblAdvertencia.setForeground(Color.RED);
				lblAdvertencia.setText("⚠️   ADVERTENCIA: Existe DNI");
				break;
			case ERROR:
				lblAdvertencia.setForeground(Color.RED);
				lblAdvertencia.setText("⚠️   ADVERTENCIA: Algo ha ido mal");
				break;
			default:
				lblAdvertencia.setForeground(Color.RED);
				lblAdvertencia.setText("⚠️   ADVERTENCIA: Algo ha ido mal");
				break;
		}
	} // fin listenerBotonEnviar

	private void listenerRellenoDni() {

		// Comprueba si existe utilizando la capa de Gestión (Controlador)
		ResultadoGestion resultado = socioGestion.existeSocio(tFDni.getText());
		
		// Si resultado == existe
		if (resultado == ResultadoGestion.YA_EXISTE) {
			lblAdvertencia.setForeground(Color.RED);
			lblAdvertencia.setText("⚠️   ADVERTENCIA: Existe DNI");
		}
		else {
			lblAdvertencia.setText("");
		}
		
	} // fin listenerRellenoDni

} // fin SocioAltaGUI


