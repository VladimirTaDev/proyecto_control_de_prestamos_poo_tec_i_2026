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
import java.util.Map;
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
	private JComboBox comboBoxItemsPBorrar;
	private JTextField textFieldDescripcionItemModificar;
	private JComboBox comboBoxItemsPModificar;
	private JComboBox comboBoxCategoriaModificar;
	private JComboBox comboBoxTipoModificar;
	private JButton btnModificarItem;
	private JButton btnItemLimpiarModificar;
	

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
		lblNombre.setBounds(10, 11, 76, 14);
		panelCrearItem.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(86, 8, 489, 20);
		panelCrearItem.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(10, 44, 76, 14);
		panelCrearItem.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(86, 41, 489, 20);
		panelCrearItem.add(textFieldDescripcion);
		
		JLabel lblCategoria = new JLabel("Categoría:");
		lblCategoria.setBounds(10, 77, 76, 14);
		panelCrearItem.add(lblCategoria);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 112, 76, 14);
		panelCrearItem.add(lblTipo);
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(86, 73, 219, 22);
		panelCrearItem.add(comboBoxCategoria);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(86, 108, 219, 22);
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
		panelModificarItem.setLayout(null);
		tabbedPaneItems.addTab("Modificar", null, panelModificarItem, null);
		
		JLabel lblDescripcionModificar = new JLabel("Descripción:");
		lblDescripcionModificar.setBounds(10, 44, 76, 14);
		panelModificarItem.add(lblDescripcionModificar);
		
		textFieldDescripcionItemModificar = new JTextField();
		textFieldDescripcionItemModificar.setColumns(10);
		textFieldDescripcionItemModificar.setBounds(86, 41, 489, 20);
		panelModificarItem.add(textFieldDescripcionItemModificar);
		
		JLabel lblCategoriaItemModificar = new JLabel("Categoría:");
		lblCategoriaItemModificar.setBounds(10, 77, 76, 14);
		panelModificarItem.add(lblCategoriaItemModificar);
		
		JLabel lblTipoModificar = new JLabel("Tipo:");
		lblTipoModificar.setBounds(10, 112, 76, 14);
		panelModificarItem.add(lblTipoModificar);
		
		comboBoxCategoriaModificar = new JComboBox();
		comboBoxCategoriaModificar.setBounds(86, 73, 219, 22);
		panelModificarItem.add(comboBoxCategoriaModificar);
		
		comboBoxTipoModificar = new JComboBox();
		comboBoxTipoModificar.setBounds(86, 108, 219, 22);
		panelModificarItem.add(comboBoxTipoModificar);
		
		btnModificarItem = new JButton("Crear");
		btnModificarItem.setBounds(41, 168, 89, 23);
		panelModificarItem.add(btnModificarItem);
		
		btnItemLimpiarModificar = new JButton("Limpiar");
		btnItemLimpiarModificar.setBounds(181, 168, 89, 23);
		panelModificarItem.add(btnItemLimpiarModificar);
		
		JLabel lblSeleccionarModificarItem = new JLabel("Seleccionar:");
		lblSeleccionarModificarItem.setBounds(10, 11, 76, 21);
		panelModificarItem.add(lblSeleccionarModificarItem);
		
		comboBoxItemsPModificar = new JComboBox();
		comboBoxItemsPModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCamposModificarItem();
			}
		});
		comboBoxItemsPModificar.setBounds(86, 11, 411, 22);
		panelModificarItem.add(comboBoxItemsPModificar);
		
		JPanel panelBorrarItem = new JPanel();
		tabbedPaneItems.addTab("Borrar", null, panelBorrarItem, null);
		panelBorrarItem.setLayout(null);
		
		JLabel lblSeleccionarItem = new JLabel("Seleccionar:");
		lblSeleccionarItem.setBounds(10, 11, 62, 21);
		panelBorrarItem.add(lblSeleccionarItem);
		
		comboBoxItemsPBorrar = new JComboBox();
		comboBoxItemsPBorrar.setBounds(82, 11, 415, 22);
		panelBorrarItem.add(comboBoxItemsPBorrar);
		
		JButton btnBorrarItem = new JButton("Borrar");
		btnBorrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarItem();
			}
		});
		btnBorrarItem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBorrarItem.setBounds(224, 62, 127, 53);
		panelBorrarItem.add(btnBorrarItem);
		
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
		if (comboBoxItemsPBorrar != null) comboBoxItemsPBorrar.removeAllItems();
		if (comboBoxItemsPModificar != null) comboBoxItemsPModificar.removeAllItems();
		if (comboBoxCategoriaModificar != null) comboBoxCategoriaModificar.removeAllItems();
		if (comboBoxTipoModificar != null) comboBoxTipoModificar.removeAllItems();

		// Popular desplegables
		for (String cat : control.getListadoCategorias()) {
			comboBoxCategoria.addItem(cat);
		}

		for (String tipo : control.getListadoTipos()) {
			comboBoxTipo.addItem(tipo);
		}
		
		for (String item : control.getListadoItems()) {
			comboBoxItems.addItem(item);
		}
		
		for (String item : control.getListadoItems()) {
			comboBoxItemsPBorrar.addItem(item);
		}
		
		for (String item : control.getListadoItems()) {
			comboBoxItemsPModificar.addItem(item);
		}
		
		for (String cat : control.getListadoCategorias()) {
			comboBoxCategoriaModificar.addItem(cat);
		}
		
		for (String tipo : control.getListadoTipos()) {
			comboBoxTipoModificar.addItem(tipo);
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
	
	private void borrarItem() {
		String itemSeleccionado = (String) comboBoxItemsPBorrar.getSelectedItem();

		int confirmacion = JOptionPane.showConfirmDialog(frameControlPrestamos,
				"¿Seguro que quiere borrar \"" + itemSeleccionado + "\"?", "Confirmar borrado",
				JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			control.borrarItem(itemSeleccionado);
			// Actualizar el desplegables
			cargarDesplegables();
		}
	}
	
	private void cargarCamposModificarItem() {
		String itemSeleccionado = (String) comboBoxItemsPModificar.getSelectedItem();
		if (itemSeleccionado == null)
			return;

		// Rellenar los datos empaquetados
		Map<String, String> elementos = control.getDetalleItem(itemSeleccionado);

		if (elementos != null) {
			textFieldDescripcionItemModificar.setText(elementos.get("descripcion"));
			comboBoxCategoriaModificar.setSelectedItem(elementos.get("tema"));
			comboBoxTipoModificar.setSelectedItem(elementos.get("formato"));
		}
	}
}
