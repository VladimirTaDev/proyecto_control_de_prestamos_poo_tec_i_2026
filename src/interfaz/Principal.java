package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import control.ControladoraPrestamo;
import logica.Categoria;
import logica.Tipo;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Principal {

	private ControladoraPrestamo control;
	
	private JFrame frameControlPrestamos;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JComboBox comboBoxCategoria;
	private JComboBox comboBoxTipo;
	private JComboBox comboBoxItems;
	private JTextArea textAreaConsultarItems;
	

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
		textFieldNombre.setBounds(76, 8, 499, 20);
		panelCrearItem.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(10, 44, 63, 14);
		panelCrearItem.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(76, 41, 499, 20);
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
		comboBoxTipo.setBounds(76, 108, 229, 22);
		panelCrearItem.add(comboBoxTipo);
		
		JButton btnCrearItem = new JButton("Crear");
		btnCrearItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearItem();
			}
		});
		btnCrearItem.setBounds(41, 168, 89, 23);
		panelCrearItem.add(btnCrearItem);
		
		JButton btnItemLimpiar = new JButton("Limpiar");
		btnItemLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposCrearItem();
			}
		});
		btnItemLimpiar.setBounds(181, 168, 89, 23);
		panelCrearItem.add(btnItemLimpiar);
		
		JPanel panelModificarItem = new JPanel();
		tabbedPaneItems.addTab("Modificar", null, panelModificarItem, null);
		
		JPanel panelBorrarItem = new JPanel();
		tabbedPaneItems.addTab("Borrar", null, panelBorrarItem, null);
		
		JPanel panelConsultarItem = new JPanel();
		tabbedPaneItems.addTab("Consultar", null, panelConsultarItem, null);
		panelConsultarItem.setLayout(null);
		
		JLabel lblConsultarItem = new JLabel("Consultar:");
		lblConsultarItem.setBounds(10, 11, 62, 14);
		panelConsultarItem.add(lblConsultarItem);
		
		comboBoxItems = new JComboBox();
		comboBoxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDetalleItem((String) comboBoxItems.getSelectedItem(), textAreaConsultarItems);
			}
		});
		comboBoxItems.setBounds(82, 7, 415, 22);
		panelConsultarItem.add(comboBoxItems);
		
		JScrollPane scrollPaneConsultarItems = new JScrollPane();
		scrollPaneConsultarItems.setBounds(10, 60, 562, 329);
		panelConsultarItem.add(scrollPaneConsultarItems);
		
		textAreaConsultarItems = new JTextArea();
		textAreaConsultarItems.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaConsultarItems.setEditable(false);
		scrollPaneConsultarItems.setViewportView(textAreaConsultarItems);
		
		JLabel lblDatosDelItem = new JLabel("Datos Del Item:");
		lblDatosDelItem.setBounds(10, 36, 105, 14);
		panelConsultarItem.add(lblDatosDelItem);
		
		JPanel panelCategorias = new JPanel();
		tabbedPaneAdministracion.addTab("Categorías", null, panelCategorias, null);
		
		JPanel panelTipos = new JPanel();
		tabbedPaneAdministracion.addTab("Tipos", null, panelTipos, null);
		
		JPanel panelPrestamos = new JPanel();
		tabbedPane.addTab("Préstamos", null, panelPrestamos, null);
		
		JPanel panelReportes = new JPanel();
		tabbedPane.addTab("Reportes", null, panelReportes, null);
	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	// Carga desplegables para mostrarlos en el menú desplegable
	private void cargarDesplegables() {
		// Limpiar JComboBox
		if (comboBoxCategoria != null) comboBoxCategoria.removeAllItems();
		if (comboBoxTipo != null) comboBoxTipo.removeAllItems();
		if (comboBoxItems != null) comboBoxItems.removeAllItems();

		for (String cat : control.getListadoCategorias()) {
			comboBoxCategoria.addItem(cat);
		}

		for (String tipo : control.getListadoTipos()) {
			comboBoxTipo.addItem(tipo);
		}
		
		for (String item : control.getListadoItems()) {
			comboBoxItems.addItem(item);
		}
	}
	
	private void cargarDetalleItem(String nombreItem, JTextArea textArea) {
		if (nombreItem == null) {
			return;
		}
		String detalleItem = control.consultarItem(nombreItem);
		textArea.setText(detalleItem);
	}
	
	private void crearItem() {
		String nombre = textFieldNombre.getText();
		String descripcion = textFieldDescripcion.getText();
		String categoria = (String) comboBoxCategoria.getSelectedItem();
		String tipo = (String) comboBoxTipo.getSelectedItem();
		
		if (nombre.isEmpty() || descripcion.isEmpty()) {
			// Muestra pop up error
			JOptionPane.showMessageDialog(frameControlPrestamos, "El nombre y la descripción no pueden estar vacíos.",
					"Error al crear ítem", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		control.crearItem(nombre, descripcion, categoria, tipo);
		
		// Actualizar el desplegables
		cargarDesplegables();
		
		limpiarCamposCrearItem();
	}
	
	private void limpiarCamposCrearItem() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		comboBoxCategoria.setSelectedIndex(0);
		comboBoxTipo.setSelectedIndex(0);
	}
}
