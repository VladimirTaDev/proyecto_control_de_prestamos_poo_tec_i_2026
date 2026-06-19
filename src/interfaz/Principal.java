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
import javax.swing.ScrollPaneConstants;

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
	
	private JTextField textFieldPersonaNombre;
	private JTextField textFieldPersonaTelefono;
	private JTextField textFieldPersonaEmail;
	private JComboBox comboBoxPersonasPModificar;
	private JTextField textFieldTelefonoPersonaModificar;
	private JTextField textFieldEmailPersonaModificar;
	private JButton btnModificarPersona;
	private JButton btnPersonaLimpiarModificar;
	private JComboBox comboBoxPersonasPBorrar;
	private JComboBox comboBoxPersonas;
	private JTextArea textAreaConsultarPersonas;
	

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
		frameControlPrestamos.setBounds(100, 100, 650, 350);
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
		panelPersonas.setLayout(new BoxLayout(panelPersonas, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPanePersonas = new JTabbedPane(JTabbedPane.RIGHT);
		panelPersonas.add(tabbedPanePersonas);
		
		JPanel panelCrearPersona = new JPanel();
		tabbedPanePersonas.addTab("Crear", null, panelCrearPersona, null);
		panelCrearPersona.setLayout(null);
		
		JLabel lblPersonaNombre = new JLabel("Nombre:");
		lblPersonaNombre.setBounds(10, 11, 76, 14);
		panelCrearPersona.add(lblPersonaNombre);
		
		textFieldPersonaNombre = new JTextField();
		textFieldPersonaNombre.setBounds(86, 8, 440, 20);
		panelCrearPersona.add(textFieldPersonaNombre);
		textFieldPersonaNombre.setColumns(10);
		
		JLabel lblPersonaTelefono = new JLabel("Teléfono:");
		lblPersonaTelefono.setBounds(10, 44, 76, 14);
		panelCrearPersona.add(lblPersonaTelefono);
		
		textFieldPersonaTelefono = new JTextField();
		textFieldPersonaTelefono.setColumns(10);
		textFieldPersonaTelefono.setBounds(86, 41, 440, 20);
		panelCrearPersona.add(textFieldPersonaTelefono);
		
		JLabel lblPersonaEmail = new JLabel("Email:");
		lblPersonaEmail.setBounds(10, 77, 76, 14);
		panelCrearPersona.add(lblPersonaEmail);
		
		textFieldPersonaEmail = new JTextField();
		textFieldPersonaEmail.setColumns(10);
		textFieldPersonaEmail.setBounds(86, 74, 440, 20);
		panelCrearPersona.add(textFieldPersonaEmail);
		
		JButton btnCrearPersona = new JButton("Crear");
		btnCrearPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPersona();
			}
		});
		btnCrearPersona.setBounds(41, 168, 89, 23);
		panelCrearPersona.add(btnCrearPersona);
		
		JButton btnPersonaLimpiar = new JButton("Limpiar");
		btnPersonaLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposCrearPersona();
			}
		});
		btnPersonaLimpiar.setBounds(181, 168, 89, 23);
		panelCrearPersona.add(btnPersonaLimpiar);
		
		JPanel panelModificarPersona = new JPanel();
		panelModificarPersona.setLayout(null);
		tabbedPanePersonas.addTab("Modificar", null, panelModificarPersona, null);
		
		JLabel lblSeleccionarModificarPersona = new JLabel("Seleccionar:");
		lblSeleccionarModificarPersona.setBounds(10, 11, 76, 21);
		panelModificarPersona.add(lblSeleccionarModificarPersona);
		
		comboBoxPersonasPModificar = new JComboBox();
		comboBoxPersonasPModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCamposModificarPersona();
			}
		});
		comboBoxPersonasPModificar.setBounds(86, 11, 400, 22);
		panelModificarPersona.add(comboBoxPersonasPModificar);
		
		JLabel lblTelefonoModificar = new JLabel("Teléfono:");
		lblTelefonoModificar.setBounds(10, 44, 76, 14);
		panelModificarPersona.add(lblTelefonoModificar);
		
		textFieldTelefonoPersonaModificar = new JTextField();
		textFieldTelefonoPersonaModificar.setColumns(10);
		textFieldTelefonoPersonaModificar.setBounds(86, 41, 440, 20);
		panelModificarPersona.add(textFieldTelefonoPersonaModificar);
		
		JLabel lblEmailPersonaModificar = new JLabel("Email:");
		lblEmailPersonaModificar.setBounds(10, 77, 76, 14);
		panelModificarPersona.add(lblEmailPersonaModificar);
		
		textFieldEmailPersonaModificar = new JTextField();
		textFieldEmailPersonaModificar.setColumns(10);
		textFieldEmailPersonaModificar.setBounds(86, 74, 440, 20);
		panelModificarPersona.add(textFieldEmailPersonaModificar);
		
		btnModificarPersona = new JButton("Modificar");
		btnModificarPersona.setBounds(41, 168, 89, 23);
		panelModificarPersona.add(btnModificarPersona);
		
		btnPersonaLimpiarModificar = new JButton("Limpiar");
		btnPersonaLimpiarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposModificarPersona();
			}
		});
		
		btnPersonaLimpiarModificar.setBounds(181, 168, 89, 23);
		panelModificarPersona.add(btnPersonaLimpiarModificar);
		
		JPanel panelBorrarPersona = new JPanel();
		tabbedPanePersonas.addTab("Borrar", null, panelBorrarPersona, null);
		panelBorrarPersona.setLayout(null);
		
		JLabel lblSeleccionarPersonaBorrar = new JLabel("Seleccionar:");
		lblSeleccionarPersonaBorrar.setBounds(10, 11, 74, 21);
		panelBorrarPersona.add(lblSeleccionarPersonaBorrar);
		
		comboBoxPersonasPBorrar = new JComboBox();
		comboBoxPersonasPBorrar.setBounds(91, 11, 391, 22);
		panelBorrarPersona.add(comboBoxPersonasPBorrar);
		
		JButton btnBorrarPersona = new JButton("Borrar");
		btnBorrarPersona.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBorrarPersona.setBounds(224, 62, 127, 53);
		panelBorrarPersona.add(btnBorrarPersona);
		
		JPanel panelConsultarPersona = new JPanel();
		tabbedPanePersonas.addTab("Consultar", null, panelConsultarPersona, null);
		panelConsultarPersona.setLayout(null);
		
		JLabel lblConsultarPersona = new JLabel("Consultar:");
		lblConsultarPersona.setBounds(10, 11, 62, 14);
		panelConsultarPersona.add(lblConsultarPersona);
		
		comboBoxPersonas = new JComboBox();
		comboBoxPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPersona();
			}
		});
		comboBoxPersonas.setBounds(82, 7, 400, 22);
		panelConsultarPersona.add(comboBoxPersonas);
		
		JScrollPane scrollPaneConsultarPersonas = new JScrollPane();
		scrollPaneConsultarPersonas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneConsultarPersonas.setBounds(10, 60, 520, 179);
		panelConsultarPersona.add(scrollPaneConsultarPersonas);
		
		textAreaConsultarPersonas = new JTextArea();
		textAreaConsultarPersonas.setWrapStyleWord(true);
		textAreaConsultarPersonas.setLineWrap(true);
		textAreaConsultarPersonas.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaConsultarPersonas.setEditable(false);
		scrollPaneConsultarPersonas.setViewportView(textAreaConsultarPersonas);
		
		JLabel lblDatosDeLaPersona = new JLabel("Datos De La Persona:");
		lblDatosDeLaPersona.setBounds(10, 36, 130, 14);
		panelConsultarPersona.add(lblDatosDeLaPersona);
		
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
		textFieldNombre.setBounds(86, 8, 440, 20);
		panelCrearItem.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(10, 44, 76, 14);
		panelCrearItem.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(86, 41, 440, 20);
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
		textFieldDescripcionItemModificar.setBounds(86, 41, 440, 20);
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
		btnModificarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarItem();
			}
		});
		btnModificarItem.setBounds(41, 168, 89, 23);
		panelModificarItem.add(btnModificarItem);
		
		btnItemLimpiarModificar = new JButton("Limpiar");
		btnItemLimpiarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposModificarItem();
			}
		});
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
		comboBoxItemsPModificar.setBounds(86, 11, 400, 22);
		panelModificarItem.add(comboBoxItemsPModificar);
		
		JPanel panelBorrarItem = new JPanel();
		tabbedPaneItems.addTab("Borrar", null, panelBorrarItem, null);
		panelBorrarItem.setLayout(null);
		
		JLabel lblSeleccionarItem = new JLabel("Seleccionar:");
		lblSeleccionarItem.setBounds(10, 11, 74, 21);
		panelBorrarItem.add(lblSeleccionarItem);
		
		comboBoxItemsPBorrar = new JComboBox();
		comboBoxItemsPBorrar.setBounds(91, 11, 391, 22);
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
		comboBoxItems.setBounds(82, 7, 400, 22);
		panelConsultarItem.add(comboBoxItems);
		
		JScrollPane scrollPaneConsultarItems = new JScrollPane();
		scrollPaneConsultarItems.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneConsultarItems.setBounds(10, 60, 520, 179);
		panelConsultarItem.add(scrollPaneConsultarItems);
		
		textAreaConsultarItems = new JTextArea();
		textAreaConsultarItems.setWrapStyleWord(true);
		textAreaConsultarItems.setLineWrap(true);
		textAreaConsultarItems.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaConsultarItems.setEditable(false);
		scrollPaneConsultarItems.setViewportView(textAreaConsultarItems);
		
		JLabel lblDatosDelItem = new JLabel("Datos Del Item:");
		lblDatosDelItem.setBounds(10, 36, 105, 14);
		panelConsultarItem.add(lblDatosDelItem);
		
		JPanel panelCategorias = new JPanel();
		tabbedPaneAdministracion.addTab("Categorías", null, panelCategorias, null);
		panelCategorias.setLayout(new BoxLayout(panelCategorias, BoxLayout.X_AXIS));
		
		JPanel panelTipos = new JPanel();
		tabbedPaneAdministracion.addTab("Tipos", null, panelTipos, null);
		panelTipos.setLayout(new BoxLayout(panelTipos, BoxLayout.X_AXIS));
		
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
		if (comboBoxPersonasPModificar != null) comboBoxPersonasPModificar.removeAllItems();
		if (comboBoxPersonasPBorrar != null) comboBoxPersonasPBorrar.removeAllItems();
		if (comboBoxPersonas != null) comboBoxPersonas.removeAllItems();

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
		
		for (String persona : control.getListadoPersonas()) {
			comboBoxPersonasPModificar.addItem(persona);
		}
		
		for (String persona : control.getListadoPersonas()) {
			comboBoxPersonasPBorrar.addItem(persona);
		}
		
		for (String persona : control.getListadoPersonas()) {
			comboBoxPersonas.addItem(persona);
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
		
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
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
		
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
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
	
	private void limpiarCamposModificarItem() {
		textFieldDescripcionItemModificar.setText("");
		comboBoxCategoriaModificar.setSelectedIndex(0);
		comboBoxTipoModificar.setSelectedIndex(0);
		comboBoxItemsPModificar.setSelectedIndex(0);
	}

	private void modificarItem() {
		String itemSeleccionado = (String) comboBoxItemsPModificar.getSelectedItem();
		if (itemSeleccionado == null)
			return;

		String nuevaDescripcion = textFieldDescripcionItemModificar.getText();
		String nuevaCategoria = (String) comboBoxCategoriaModificar.getSelectedItem();
		String nuevoTipo = (String) comboBoxTipoModificar.getSelectedItem();

		if (nuevaDescripcion.isEmpty()) {
			JOptionPane.showMessageDialog(frameControlPrestamos, "La descripción no puede estar vacía.",
					"Error al modificar ítem", JOptionPane.ERROR_MESSAGE);
			return;
		}

		control.modificarItem(itemSeleccionado, nuevaDescripcion, nuevaCategoria, nuevoTipo);

		// Actualizar desplegables
		cargarDesplegables();
		
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	private void operacionRealizadaCorrectamente() {
		JOptionPane.showMessageDialog(frameControlPrestamos, "Operación realizada correctamente.",
				"Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void crearPersona() {
		String nombre = textFieldPersonaNombre.getText();
		String telefono = textFieldPersonaTelefono.getText();
		String email = textFieldPersonaEmail.getText();
		
		if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
			JOptionPane.showMessageDialog(frameControlPrestamos, "Ningún campo puede estar vacíos.",
					"Error al crear persona", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		control.crearPerosna(nombre, telefono, email);
		
		cargarDesplegables(); // Actualizar desplegables
		limpiarCamposCrearPersona();
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	private void limpiarCamposCrearPersona() {
		textFieldPersonaNombre.setText("");
		textFieldPersonaTelefono.setText("");
		textFieldPersonaEmail.setText("");
	}
	
	private void consultarPersona() {
		String personaSeleccionada = (String) comboBoxPersonas.getSelectedItem();
		if (personaSeleccionada == null)
			return;
		
		String detallePersona = control.consultarPersona(personaSeleccionada);
		textAreaConsultarPersonas.setText(detallePersona);
	}
	
	private void limpiarCamposModificarPersona() {
		textFieldTelefonoPersonaModificar.setText("");
		textFieldEmailPersonaModificar.setText("");
		comboBoxPersonasPModificar.setSelectedIndex(0);
	}
	
	private void cargarCamposModificarPersona() {
		String personaSeleccionada = (String) comboBoxPersonasPModificar.getSelectedItem();
		if (personaSeleccionada == null)
			return;

		Map<String, String> elementos = control.getDetallePersona(personaSeleccionada);

		if (elementos != null) {
			textFieldTelefonoPersonaModificar.setText(elementos.get("telefono"));
			textFieldEmailPersonaModificar.setText(elementos.get("email"));
		}
		
	}
}
