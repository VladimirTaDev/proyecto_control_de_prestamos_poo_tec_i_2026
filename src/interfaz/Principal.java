package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import control.ControladoraPrestamo;
import logica.Categoria;
import logica.Tipo;

import javax.swing.JComboBox;

public class Principal {

	private ControladoraPrestamo control;
	
	private JFrame frameControlPrestamos;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JComboBox comboBoxCategoria;
	private JComboBox comboBoxTipo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frameControlPrestamos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		control = ControladoraPrestamo.getInstancia();
		
		initialize();
		
		cargarDesplegables(); // Cargar los datos desplegables al iniciar la aplicación
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameControlPrestamos = new JFrame();
		frameControlPrestamos.setTitle("Control De Prestamos");
		frameControlPrestamos.setBounds(100, 100, 695, 500);
		frameControlPrestamos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frameControlPrestamos.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelAdministracion = new JPanel();
		tabbedPane.addTab("Administración", null, panelAdministracion, null);
		panelAdministracion.setLayout(new BoxLayout(panelAdministracion, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPaneAdministracion = new JTabbedPane(JTabbedPane.TOP);
		panelAdministracion.add(tabbedPaneAdministracion);
		
		JPanel panelPersonas = new JPanel();
		tabbedPaneAdministracion.addTab("Personas", null, panelPersonas, null);
		
		JPanel panelItems = new JPanel();
		tabbedPaneAdministracion.addTab("Ítems", null, panelItems, null);
		panelItems.setLayout(new BoxLayout(panelItems, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPaneItems = new JTabbedPane(JTabbedPane.RIGHT);
		panelItems.add(tabbedPaneItems);
		
		JPanel panelCrearItem = new JPanel();
		tabbedPaneItems.addTab("Crear", null, panelCrearItem, null);
		panelCrearItem.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 46, 14);
		panelCrearItem.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(76, 8, 516, 20);
		panelCrearItem.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(10, 44, 63, 14);
		panelCrearItem.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(76, 41, 516, 20);
		panelCrearItem.add(textFieldDescripcion);
		
		JLabel lblCategoria = new JLabel("Categoría:");
		lblCategoria.setBounds(10, 77, 63, 14);
		panelCrearItem.add(lblCategoria);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 112, 63, 14);
		panelCrearItem.add(lblTipo);
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(76, 73, 229, 22);
		panelCrearItem.add(comboBoxCategoria);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setEditable(true);
		comboBoxTipo.setBounds(76, 108, 229, 22);
		panelCrearItem.add(comboBoxTipo);
		
		JPanel panelModificarItem = new JPanel();
		tabbedPaneItems.addTab("Modificar", null, panelModificarItem, null);
		
		JPanel panelBorrarItem = new JPanel();
		tabbedPaneItems.addTab("Borrar", null, panelBorrarItem, null);
		
		JPanel panelConsultarItem = new JPanel();
		tabbedPaneItems.addTab("Consultar", null, panelConsultarItem, null);
		
		JPanel panelCategorias = new JPanel();
		tabbedPaneAdministracion.addTab("Categorías", null, panelCategorias, null);
		
		JPanel panelTipos = new JPanel();
		tabbedPaneAdministracion.addTab("Tipos", null, panelTipos, null);
		
		JPanel panelPrestamos = new JPanel();
		tabbedPane.addTab("Préstamos", null, panelPrestamos, null);
		
		JPanel panelReportes = new JPanel();
		tabbedPane.addTab("Reportes", null, panelReportes, null);
	}
	
	// Carga categorías y tipos para mostrarlos en el menú desplegable
	private void cargarDesplegables() {
		for (String cat : control.getListadoCategorias()) {
			comboBoxCategoria.addItem(cat);
		}

		for (String tipo : control.getListadoTipos()) {
			comboBoxTipo.addItem(tipo);
		}
	}
}
