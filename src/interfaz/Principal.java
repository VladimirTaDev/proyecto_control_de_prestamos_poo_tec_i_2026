package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
import javax.swing.JList;
import javax.swing.JCheckBox;

import java.util.List;
import java.util.ArrayList;


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
	
	private JTextField textFieldNombreCategoria;
	private JComboBox comboBoxCategoriasPModificar;
	private JTextField textFieldNombreCategoriaModificar;
	private JButton btnModificarCategoria;
	private JButton btnCategoriaLimpiarModificar;
	private JComboBox comboBoxCategoriasPBorrar;
	private JComboBox comboBoxCategorias;
	private JTextArea textAreaConsultarCategorias;
	
	private JTextField textFieldNombreTipo;
	private JComboBox comboBoxTiposPModificar;
	private JTextField textFieldNombreTipoModificar;
	private JButton btnModificarTipo;
	private JButton btnTipoLimpiarModificar;
	private JComboBox comboBoxTiposPBorrar;
	private JComboBox comboBoxTipos;
	private JTextArea textAreaConsultarTipos;
	
	// Lista de los checkboxes creados
    private List<JCheckBox> listaCheckboxesItemsDisponibles = new ArrayList<>();
    private List<JCheckBox> listaCheckboxesItemsPrestados = new ArrayList<>();
    private JPanel panelItemsDisponiblesCheck;
    private JComboBox comboBoxPrestatarioNuevo;
    private JComboBox comboBoxPrestatarioFinalizar;
    private JButton btnFinalizarPrestamo;
    private JTextArea textAreaItemsDelPrestamo;
    private JComboBox comboBoxPrestamosActivos;
    private JComboBox comboBoxPrestatarioModificar;
    private JComboBox comboBoxPrestamosActivosModificar;
    private JPanel panelItemsPrestadosCheck;
	
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
		frameControlPrestamos.setResizable(false);
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
		btnModificarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPersona();
			}
		});
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
		btnBorrarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarPersona();
			}
		});
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
		
		JTabbedPane tabbedPaneCategorias = new JTabbedPane(JTabbedPane.RIGHT);
		panelCategorias.add(tabbedPaneCategorias);
		
		JPanel panelCrearCategoria = new JPanel();
		tabbedPaneCategorias.addTab("Crear", null, panelCrearCategoria, null);
		panelCrearCategoria.setLayout(null);
		
		JLabel lblNombreCategoria = new JLabel("Nombre:");
		lblNombreCategoria.setBounds(10, 11, 76, 14);
		panelCrearCategoria.add(lblNombreCategoria);
		
		textFieldNombreCategoria = new JTextField();
		textFieldNombreCategoria.setBounds(86, 8, 440, 20);
		panelCrearCategoria.add(textFieldNombreCategoria);
		textFieldNombreCategoria.setColumns(10);
		
		JButton btnCrearCategoria = new JButton("Crear");
		btnCrearCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCategoria();
			}
		});
		btnCrearCategoria.setBounds(41, 168, 89, 23);
		panelCrearCategoria.add(btnCrearCategoria);
		
		JButton btnCategoriaLimpiar = new JButton("Limpiar");
		btnCategoriaLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposCrearCategoria();
			}
		});
		btnCategoriaLimpiar.setBounds(181, 168, 89, 23);
		panelCrearCategoria.add(btnCategoriaLimpiar);
		
		JPanel panelModificarCategoria = new JPanel();
		panelModificarCategoria.setLayout(null);
		tabbedPaneCategorias.addTab("Modificar", null, panelModificarCategoria, null);
		
		JLabel lblNombreCategoriaModificar = new JLabel("Nombre:");
		lblNombreCategoriaModificar.setBounds(10, 44, 76, 14);
		panelModificarCategoria.add(lblNombreCategoriaModificar);
		
		textFieldNombreCategoriaModificar = new JTextField();
		textFieldNombreCategoriaModificar.setColumns(10);
		textFieldNombreCategoriaModificar.setBounds(86, 41, 440, 20);
		panelModificarCategoria.add(textFieldNombreCategoriaModificar);
		
		btnModificarCategoria = new JButton("Modificar");
		btnModificarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCategoria();
			}
		});
		btnModificarCategoria.setBounds(41, 168, 89, 23);
		panelModificarCategoria.add(btnModificarCategoria);
		
		btnCategoriaLimpiarModificar = new JButton("Limpiar");
		btnCategoriaLimpiarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCamposModificarCategoria();
			}
		});
		btnCategoriaLimpiarModificar.setBounds(181, 168, 89, 23);
		panelModificarCategoria.add(btnCategoriaLimpiarModificar);
		
		JLabel lblSeleccionarModificarCategoria = new JLabel("Seleccionar:");
		lblSeleccionarModificarCategoria.setBounds(10, 11, 76, 21);
		panelModificarCategoria.add(lblSeleccionarModificarCategoria);
		
		comboBoxCategoriasPModificar = new JComboBox();
		comboBoxCategoriasPModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCamposModificarCategoria();
			}
		});
		comboBoxCategoriasPModificar.setBounds(86, 11, 400, 22);
		panelModificarCategoria.add(comboBoxCategoriasPModificar);
		
		JPanel panelBorrarCategoria = new JPanel();
		tabbedPaneCategorias.addTab("Borrar", null, panelBorrarCategoria, null);
		panelBorrarCategoria.setLayout(null);
		
		JLabel lblSeleccionarCategoriaBorrar = new JLabel("Seleccionar:");
		lblSeleccionarCategoriaBorrar.setBounds(10, 11, 74, 21);
		panelBorrarCategoria.add(lblSeleccionarCategoriaBorrar);
		
		comboBoxCategoriasPBorrar = new JComboBox();
		comboBoxCategoriasPBorrar.setBounds(91, 11, 391, 22);
		panelBorrarCategoria.add(comboBoxCategoriasPBorrar);
		
		JButton btnBorrarCategoria = new JButton("Borrar");
		btnBorrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCategoria();
			}
		});
		btnBorrarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBorrarCategoria.setBounds(224, 62, 127, 53);
		panelBorrarCategoria.add(btnBorrarCategoria);
		
		JPanel panelConsultarCategoria = new JPanel();
		tabbedPaneCategorias.addTab("Consultar", null, panelConsultarCategoria, null);
		panelConsultarCategoria.setLayout(null);
		
		JLabel lblConsultarCategoria = new JLabel("Consultar:");
		lblConsultarCategoria.setBounds(10, 11, 62, 14);
		panelConsultarCategoria.add(lblConsultarCategoria);
		
		comboBoxCategorias = new JComboBox();
		comboBoxCategorias.setBounds(82, 7, 400, 22);
		panelConsultarCategoria.add(comboBoxCategorias);
		
		JScrollPane scrollPaneConsultarCategorias = new JScrollPane();
		scrollPaneConsultarCategorias.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneConsultarCategorias.setBounds(10, 60, 520, 179);
		panelConsultarCategoria.add(scrollPaneConsultarCategorias);
		
		textAreaConsultarCategorias = new JTextArea();
		textAreaConsultarCategorias.setWrapStyleWord(true);
		textAreaConsultarCategorias.setLineWrap(true);
		textAreaConsultarCategorias.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaConsultarCategorias.setEditable(false);
		scrollPaneConsultarCategorias.setViewportView(textAreaConsultarCategorias);
		
		JLabel lblDatosDeLaCategoria = new JLabel("Datos De La Categoría:");
		lblDatosDeLaCategoria.setBounds(10, 36, 145, 14);
		panelConsultarCategoria.add(lblDatosDeLaCategoria);
		
		JPanel panelTipos = new JPanel();
		tabbedPaneAdministracion.addTab("Tipos", null, panelTipos, null);
		panelTipos.setLayout(new BoxLayout(panelTipos, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPaneTipos = new JTabbedPane(JTabbedPane.RIGHT);
		panelTipos.add(tabbedPaneTipos);
		
		JPanel panelCrearTipo = new JPanel();
		tabbedPaneTipos.addTab("Crear", null, panelCrearTipo, null);
		panelCrearTipo.setLayout(null);
		
		JLabel lblNombreTipo = new JLabel("Nombre:");
		lblNombreTipo.setBounds(10, 11, 76, 14);
		panelCrearTipo.add(lblNombreTipo);
		
		textFieldNombreTipo = new JTextField();
		textFieldNombreTipo.setBounds(86, 8, 440, 20);
		panelCrearTipo.add(textFieldNombreTipo);
		textFieldNombreTipo.setColumns(10);
		
		JButton btnCrearTipo = new JButton("Crear");
		btnCrearTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearTipo();
			}
		});
		btnCrearTipo.setBounds(41, 168, 89, 23);
		panelCrearTipo.add(btnCrearTipo);
		
		JButton btnTipoLimpiar = new JButton("Limpiar");
		btnTipoLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposCrearTipo();
			}
		});
		btnTipoLimpiar.setBounds(181, 168, 89, 23);
		panelCrearTipo.add(btnTipoLimpiar);
		
		JPanel panelModificarTipo = new JPanel();
		panelModificarTipo.setLayout(null);
		tabbedPaneTipos.addTab("Modificar", null, panelModificarTipo, null);
		
		JLabel lblNombreTipoModificar = new JLabel("Nombre:");
		lblNombreTipoModificar.setBounds(10, 44, 76, 14);
		panelModificarTipo.add(lblNombreTipoModificar);
		
		textFieldNombreTipoModificar = new JTextField();
		textFieldNombreTipoModificar.setColumns(10);
		textFieldNombreTipoModificar.setBounds(86, 41, 440, 20);
		panelModificarTipo.add(textFieldNombreTipoModificar);
		
		btnModificarTipo = new JButton("Modificar");
		btnModificarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarTipo();
			}
		});
		btnModificarTipo.setBounds(41, 168, 89, 23);
		panelModificarTipo.add(btnModificarTipo);
		
		btnTipoLimpiarModificar = new JButton("Limpiar");
		btnTipoLimpiarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCamposModificarTipo();
			}
		});
		btnTipoLimpiarModificar.setBounds(181, 168, 89, 23);
		panelModificarTipo.add(btnTipoLimpiarModificar);
		
		JLabel lblSeleccionarModificarTipo = new JLabel("Seleccionar:");
		lblSeleccionarModificarTipo.setBounds(10, 11, 76, 21);
		panelModificarTipo.add(lblSeleccionarModificarTipo);
		
		comboBoxTiposPModificar = new JComboBox();
		comboBoxTiposPModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCamposModificarTipo();
			}
		});
		comboBoxTiposPModificar.setBounds(86, 11, 400, 22);
		panelModificarTipo.add(comboBoxTiposPModificar);
		
		JPanel panelBorrarTipo = new JPanel();
		tabbedPaneTipos.addTab("Borrar", null, panelBorrarTipo, null);
		panelBorrarTipo.setLayout(null);
		
		JLabel lblSeleccionarTipoBorrar = new JLabel("Seleccionar:");
		lblSeleccionarTipoBorrar.setBounds(10, 11, 74, 21);
		panelBorrarTipo.add(lblSeleccionarTipoBorrar);
		
		comboBoxTiposPBorrar = new JComboBox();
		comboBoxTiposPBorrar.setBounds(91, 11, 391, 22);
		panelBorrarTipo.add(comboBoxTiposPBorrar);
		
		JButton btnBorrarTipo = new JButton("Borrar");
		btnBorrarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarTipo();
			}
		});
		btnBorrarTipo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBorrarTipo.setBounds(224, 62, 127, 53);
		panelBorrarTipo.add(btnBorrarTipo);
		
		JPanel panelConsultarTipo = new JPanel();
		tabbedPaneTipos.addTab("Consultar", null, panelConsultarTipo, null);
		panelConsultarTipo.setLayout(null);
		
		JLabel lblConsultarTipo = new JLabel("Consultar:");
		lblConsultarTipo.setBounds(10, 11, 62, 14);
		panelConsultarTipo.add(lblConsultarTipo);
		
		comboBoxTipos = new JComboBox();
		comboBoxTipos.setBounds(82, 7, 400, 22);
		panelConsultarTipo.add(comboBoxTipos);
		
		JScrollPane scrollPaneConsultarTipos = new JScrollPane();
		scrollPaneConsultarTipos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneConsultarTipos.setBounds(10, 60, 520, 179);
		panelConsultarTipo.add(scrollPaneConsultarTipos);
		
		textAreaConsultarTipos = new JTextArea();
		textAreaConsultarTipos.setWrapStyleWord(true);
		textAreaConsultarTipos.setLineWrap(true);
		textAreaConsultarTipos.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaConsultarTipos.setEditable(false);
		scrollPaneConsultarTipos.setViewportView(textAreaConsultarTipos);
		
		JLabel lblDatosDelTipo = new JLabel("Datos Del Tipo:");
		lblDatosDelTipo.setBounds(10, 36, 105, 14);
		panelConsultarTipo.add(lblDatosDelTipo);
		
		JPanel panelPrestamos = new JPanel();
		tabbedPane.addTab("Préstamos", null, panelPrestamos, null);
		panelPrestamos.setLayout(new BoxLayout(panelPrestamos, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPanePrestamos = new JTabbedPane(JTabbedPane.TOP);
		panelPrestamos.add(tabbedPanePrestamos);
		
		JPanel panelNuevoPrestamo = new JPanel();
		tabbedPanePrestamos.addTab("Nuevo", null, panelNuevoPrestamo, null);
		panelNuevoPrestamo.setLayout(null);
		
		JLabel lblSececPrestatario = new JLabel("Prestatario");
		lblSececPrestatario.setBounds(10, 11, 64, 14);
		panelNuevoPrestamo.add(lblSececPrestatario);
		
		comboBoxPrestatarioNuevo = new JComboBox();
		comboBoxPrestatarioNuevo.setBounds(84, 7, 307, 22);
		panelNuevoPrestamo.add(comboBoxPrestatarioNuevo);
		
		JScrollPane scrollPaneItemsDisponibles = new JScrollPane();
		scrollPaneItemsDisponibles.setBounds(22, 66, 317, 145);
		panelNuevoPrestamo.add(scrollPaneItemsDisponibles);
		
		panelItemsDisponiblesCheck = new JPanel();
		scrollPaneItemsDisponibles.setViewportView(panelItemsDisponiblesCheck);
		panelItemsDisponiblesCheck.setLayout(new BoxLayout(panelItemsDisponiblesCheck, BoxLayout.Y_AXIS));
		
		JLabel lblItemsDisponibles = new JLabel("Items Disponibles:");
		lblItemsDisponibles.setBounds(20, 41, 108, 14);
		panelNuevoPrestamo.add(lblItemsDisponibles);
		
		JButton btnRealizarPrestamo = new JButton("Realizar Prestamo");
		btnRealizarPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hacerPrestamo();
			}
		});
		btnRealizarPrestamo.setBounds(51, 221, 147, 23);
		panelNuevoPrestamo.add(btnRealizarPrestamo);
		
		JButton btnLimpiarNuevoPrestamo = new JButton("Limpiar");
		btnLimpiarNuevoPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposNuevoPrestamo();
			}
		});
		btnLimpiarNuevoPrestamo.setBounds(393, 222, 89, 23);
		panelNuevoPrestamo.add(btnLimpiarNuevoPrestamo);
		
		JPanel panelModificarPrestamo = new JPanel();
		tabbedPanePrestamos.addTab("Modificar", null, panelModificarPrestamo, null);
		panelModificarPrestamo.setLayout(null);
		
		JLabel lblSelecPrestatarioModificar = new JLabel("Prestatario");
		lblSelecPrestatarioModificar.setBounds(10, 11, 64, 14);
		panelModificarPrestamo.add(lblSelecPrestatarioModificar);
		
		comboBoxPrestatarioModificar = new JComboBox();
		comboBoxPrestatarioModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPrestamosActivosModificar();
			}
		});
		comboBoxPrestatarioModificar.setBounds(84, 7, 307, 22);
		panelModificarPrestamo.add(comboBoxPrestatarioModificar);
		
		JLabel lblPrestamosActivosModificar = new JLabel("Prestamos Activos:");
		lblPrestamosActivosModificar.setBounds(10, 41, 120, 14);
		panelModificarPrestamo.add(lblPrestamosActivosModificar);
		
		comboBoxPrestamosActivosModificar = new JComboBox();
		comboBoxPrestamosActivosModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaDeItemsModificar();
			}
		});
		comboBoxPrestamosActivosModificar.setBounds(130, 37, 261, 22);
		panelModificarPrestamo.add(comboBoxPrestamosActivosModificar);
		
		JScrollPane scrollPaneItemsPrestados = new JScrollPane();
		scrollPaneItemsPrestados.setBounds(22, 90, 317, 121);
		panelModificarPrestamo.add(scrollPaneItemsPrestados);
		
		panelItemsPrestadosCheck = new JPanel();
		scrollPaneItemsPrestados.setViewportView(panelItemsPrestadosCheck);
		panelItemsPrestadosCheck.setLayout(new BoxLayout(panelItemsPrestadosCheck, BoxLayout.Y_AXIS));
		
		JLabel lblItemsPrestados = new JLabel("Items Prestados:");
		lblItemsPrestados.setBounds(20, 70, 108, 14);
		panelModificarPrestamo.add(lblItemsPrestados);
		
		JButton btnModificarPrestamo = new JButton("Modificar Prestamo");
		btnModificarPrestamo.setBounds(51, 221, 147, 23);
		panelModificarPrestamo.add(btnModificarPrestamo);
		
		JButton btnLimpiarModificarPrestamo = new JButton("Limpiar");
		btnLimpiarModificarPrestamo.setBounds(393, 222, 89, 23);
		panelModificarPrestamo.add(btnLimpiarModificarPrestamo);
		
		JPanel panelFinalizarPrestamo = new JPanel();
		tabbedPanePrestamos.addTab("Finalizar", null, panelFinalizarPrestamo, null);
		panelFinalizarPrestamo.setLayout(null);
		
		JLabel lblSelecPrestatarioFinalizar = new JLabel("Prestatario");
		lblSelecPrestatarioFinalizar.setBounds(10, 15, 64, 14);
		panelFinalizarPrestamo.add(lblSelecPrestatarioFinalizar);
		
		comboBoxPrestatarioFinalizar = new JComboBox();
		comboBoxPrestatarioFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPrestamosActivosFinalizar();
			}
		});
		comboBoxPrestatarioFinalizar.setBounds(84, 11, 307, 22);
		panelFinalizarPrestamo.add(comboBoxPrestatarioFinalizar);
		
		JLabel lblprestamosDelPrestatario = new JLabel("Prestamos Activos:");
		lblprestamosDelPrestatario.setBounds(10, 45, 120, 14);
		panelFinalizarPrestamo.add(lblprestamosDelPrestatario);
		
		JScrollPane scrollItemsDelPrestamo = new JScrollPane();
		scrollItemsDelPrestamo.setBounds(314, 70, 221, 145);
		panelFinalizarPrestamo.add(scrollItemsDelPrestamo);
		
		textAreaItemsDelPrestamo = new JTextArea();
		textAreaItemsDelPrestamo.setWrapStyleWord(true);
		textAreaItemsDelPrestamo.setLineWrap(true);
		textAreaItemsDelPrestamo.setEditable(false);
		scrollItemsDelPrestamo.setViewportView(textAreaItemsDelPrestamo);
		
		JLabel lblItemsDelPrestamo = new JLabel("Items Del Prestamo");
		lblItemsDelPrestamo.setBounds(314, 45, 120, 14);
		panelFinalizarPrestamo.add(lblItemsDelPrestamo);
		
		btnFinalizarPrestamo = new JButton("Finalizar");
		btnFinalizarPrestamo.setBounds(98, 192, 89, 23);
		panelFinalizarPrestamo.add(btnFinalizarPrestamo);
		
		comboBoxPrestamosActivos = new JComboBox();
		comboBoxPrestamosActivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaDeItemsFinalizar();
			}
		});
		comboBoxPrestamosActivos.setBounds(10, 70, 241, 22);
		panelFinalizarPrestamo.add(comboBoxPrestamosActivos);
		
		JPanel panelReportes = new JPanel();
		tabbedPane.addTab("Reportes", null, panelReportes, null);
		panelReportes.setLayout(new BoxLayout(panelReportes, BoxLayout.X_AXIS));
	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	// Carga desplegables para mostrarlos en el menú desplegable
	private void cargarDesplegables() {
		// Limpiar JComboBox
		// Ítems
		if (comboBoxCategoria != null) comboBoxCategoria.removeAllItems();
		if (comboBoxTipo != null) comboBoxTipo.removeAllItems();
		if (comboBoxItems != null) comboBoxItems.removeAllItems();
		if (comboBoxItemsPBorrar != null) comboBoxItemsPBorrar.removeAllItems();
		if (comboBoxItemsPModificar != null) comboBoxItemsPModificar.removeAllItems();
		if (comboBoxCategoriaModificar != null) comboBoxCategoriaModificar.removeAllItems();
		if (comboBoxTipoModificar != null) comboBoxTipoModificar.removeAllItems();
		// Personas
		if (comboBoxPersonasPModificar != null) comboBoxPersonasPModificar.removeAllItems();
		if (comboBoxPersonasPBorrar != null) comboBoxPersonasPBorrar.removeAllItems();
		if (comboBoxPersonas != null) comboBoxPersonas.removeAllItems();
		// Categorías
		if (comboBoxCategoriasPModificar != null) comboBoxCategoriasPModificar.removeAllItems();
		if (comboBoxCategoriasPBorrar != null) comboBoxCategoriasPBorrar.removeAllItems();
		if (comboBoxCategorias != null) comboBoxCategorias.removeAllItems();
		// Tipos
		if (comboBoxTiposPModificar != null) comboBoxTiposPModificar.removeAllItems();
		if (comboBoxTiposPBorrar != null) comboBoxTiposPBorrar.removeAllItems();
		if (comboBoxTipos != null) comboBoxTipos.removeAllItems();
		// Prestamos
		if (comboBoxPrestatarioNuevo != null) comboBoxPrestatarioNuevo.removeAllItems();
		
		// Cargar checkboxes nuevo préstamo
		if (panelItemsDisponiblesCheck != null) {
			panelItemsDisponiblesCheck.removeAll();
			listaCheckboxesItemsDisponibles.clear();
			// Generar un checkbox por cada ítem sin prestatario asignado
			for (String nombreItem : control.getListadoItemsDisponibles()) {
				JCheckBox checkBox = new JCheckBox(nombreItem);
				panelItemsDisponiblesCheck.add(checkBox);
				listaCheckboxesItemsDisponibles.add(checkBox);
			}
		}
		if (comboBoxPrestatarioFinalizar != null) comboBoxPrestatarioFinalizar.removeAllItems();
		if (comboBoxPrestatarioModificar != null) comboBoxPrestatarioModificar.removeAllItems();

		// Popular desplegables
		// Ítems
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
		
		// Personas
		for (String persona : control.getListadoPersonas()) {
			comboBoxPersonasPModificar.addItem(persona);
		}
		
		for (String persona : control.getListadoPersonas()) {
			comboBoxPersonasPBorrar.addItem(persona);
		}
		
		for (String persona : control.getListadoPersonas()) {
			comboBoxPersonas.addItem(persona);
		}
		
		// Categorías
		for (String cat : control.getListadoCategorias()) {
			comboBoxCategoriasPModificar.addItem(cat);
			comboBoxCategoriasPBorrar.addItem(cat);
			comboBoxCategorias.addItem(cat);
		}
		
		// Tipos
		for (String tipo : control.getListadoTipos()) {
			comboBoxTiposPModificar.addItem(tipo);
			comboBoxTiposPBorrar.addItem(tipo);
			comboBoxTipos.addItem(tipo);
		}

		// Préstamos
		for (String persona : control.getListadoPersonas()) {
			comboBoxPrestatarioNuevo.addItem(persona);
		}
		
		panelItemsDisponiblesCheck.revalidate();
		panelItemsDisponiblesCheck.repaint();
		for (String persona : control.getListadoPersonas()) {
			comboBoxPrestatarioFinalizar.addItem(persona);
		}
		for (String persona : control.getListadoPersonas()) {
			comboBoxPrestatarioModificar.addItem(persona);
		}

	}

	// Items
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
	
	// Personas
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
	
	private void modificarPersona() {
		String personaSeleccionada = (String) comboBoxPersonasPModificar.getSelectedItem();
		if (personaSeleccionada == null)
			return;

		String nuevoTelefono = textFieldTelefonoPersonaModificar.getText();
		String nuevoEmail = textFieldEmailPersonaModificar.getText();

		if (nuevoTelefono.isEmpty() || nuevoEmail.isEmpty()) {
			JOptionPane.showMessageDialog(frameControlPrestamos, "Ningún campo puede estar vacío.",
					"Error al modificar persona", JOptionPane.ERROR_MESSAGE);
			return;
		}

		control.modificarPersona(personaSeleccionada, nuevoTelefono, nuevoEmail);
		cargarDesplegables(); // Actualizar desplegables
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	private void borrarPersona() {
		String personaSeleccionada = (String) comboBoxPersonasPBorrar.getSelectedItem();

		int confirmacion = JOptionPane.showConfirmDialog(frameControlPrestamos,
				"¿Seguro que quiere borrar \"" + personaSeleccionada + "\"?", "Confirmar borrado",
				JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			control.borrarPersona(personaSeleccionada);
			cargarDesplegables(); // Actualizar desplegables
		}
		
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	// Categorías
	private void crearCategoria() {
		String nombre = textFieldNombreCategoria.getText();
		
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(frameControlPrestamos, "El nombre no puede estar vacío.",
					"Error al crear categoría", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		control.crearCategoria(nombre);
		cargarDesplegables(); // Actualizar desplegables
		limpiarCamposCrearCategoria();
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	private void limpiarCamposCrearCategoria() {
		textFieldNombreCategoria.setText("");
	}
	
	private void modificarCategoria() {
		String categoriaSeleccionada = (String) comboBoxCategoriasPModificar.getSelectedItem();
		if (categoriaSeleccionada == null)
			return;

		String nuevoNombre = textFieldNombreCategoriaModificar.getText();

		if (nuevoNombre.isEmpty()) {
			JOptionPane.showMessageDialog(frameControlPrestamos, "El nombre no puede estar vacío.",
					"Error al modificar categoría", JOptionPane.ERROR_MESSAGE);
			return;
		}

		control.modificarCategoria(categoriaSeleccionada, nuevoNombre);
		cargarDesplegables(); // Actualizar desplegables
		cargarCamposModificarCategoria(); // Cargar el nuevo nombre
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	private void cargarCamposModificarCategoria() {
		String categoriaSeleccionada = (String) comboBoxCategoriasPModificar.getSelectedItem();
		if (categoriaSeleccionada == null)
			return;

		textFieldNombreCategoriaModificar.setText(categoriaSeleccionada);
	}
	
	private void borrarCategoria() {
		String categoriaSeleccionada = (String) comboBoxCategoriasPBorrar.getSelectedItem();

		int confirmacion = JOptionPane.showConfirmDialog(frameControlPrestamos,
				"¿Seguro que quiere borrar \"" + categoriaSeleccionada + "\"?", "Confirmar borrado",
				JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			control.borrarCategoria(categoriaSeleccionada);
			cargarDesplegables(); // Actualizar desplegables
		}
		
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	// Tipos
	private void crearTipo() {
		String nombre = textFieldNombreTipo.getText();
		
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(frameControlPrestamos, "El nombre no puede estar vacío.",
					"Error al crear tipo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		control.crearTipo(nombre);
		cargarDesplegables(); // Actualizar desplegables
		limpiarCamposCrearTipo();
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	private void limpiarCamposCrearTipo() {
		textFieldNombreTipo.setText("");
	}
	
	private void modificarTipo() {
		String tipoSeleccionado = (String) comboBoxTiposPModificar.getSelectedItem();
		if (tipoSeleccionado == null)
			return;

		String nuevoNombre = textFieldNombreTipoModificar.getText();

		if (nuevoNombre.isEmpty()) {
			JOptionPane.showMessageDialog(frameControlPrestamos, "El nombre no puede estar vacío.",
					"Error al modificar tipo", JOptionPane.ERROR_MESSAGE);
			return;
		}

		control.modificarTipo(tipoSeleccionado, nuevoNombre);
		cargarDesplegables(); // Actualizar desplegables
		cargarCamposModificarTipo(); // Cargar el nuevo nombre
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	private void cargarCamposModificarTipo() {
		String tipoSeleccionado = (String) comboBoxTiposPModificar.getSelectedItem();
		if (tipoSeleccionado == null)
			return;

		textFieldNombreTipoModificar.setText(tipoSeleccionado);
	}
	
	private void borrarTipo() {
		String tipoSeleccionado = (String) comboBoxTiposPBorrar.getSelectedItem();

		int confirmacion = JOptionPane.showConfirmDialog(frameControlPrestamos,
				"¿Seguro que quiere borrar \"" + tipoSeleccionado + "\"?", "Confirmar borrado",
				JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			control.borrarTipo(tipoSeleccionado);
			cargarDesplegables(); // Actualizar desplegables
		}
		
		operacionRealizadaCorrectamente(); // Mensaje satisfactorio
	}
	
	// Préstamos
	private void limpiarCamposNuevoPrestamo() {
		cargarDesplegables(); // Actualizar desplegables
	}

	private void hacerPrestamo() {
        List<String> itemsSeleccionados = new ArrayList<>();

        // Revisar checkboxes guardados
        for (JCheckBox cb : listaCheckboxesItemsDisponibles) {
            if (cb.isSelected()) {
                itemsSeleccionados.add(cb.getText());
            }
        }

        // Validación
        if (itemsSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(frameControlPrestamos, "Debe seleccionar un ítem.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener prestatario
        String prestatario = (String) comboBoxPrestatarioNuevo.getSelectedItem();

        control.hacerPrestamo(prestatario, 5);

		// Obtener el último prestamo de la lista
		int indiceNuevoPrestamo = control.getListadoPrestamos().size() - 1;

		// Agregar ítems seleccionados al préstamo
		for (String nombreItem : itemsSeleccionados) {
			control.agregarItemAlPrestamo(indiceNuevoPrestamo, nombreItem);
		}
		
		// NOTA: Crear préstamo no implementado completamente
		// TODO: Agregar alerta y fecha de devolución

        limpiarCamposNuevoPrestamo();
        operacionRealizadaCorrectamente(); // Mensaje satisfactorio
    }
	
	private void cargarPrestamosActivosFinalizar() {
		// Obtener items del comboBoxPrestatarioFinalizar
		String prestatarioSeleccionado = (String) comboBoxPrestatarioFinalizar.getSelectedItem();
		if (prestatarioSeleccionado == null) {
            return;
        }
		
		// Obtener indices de prestamos activos del prestatario seleccionado
		List<Integer> prestamosActivos = control.obtenerIndexPrestamosDePersona(prestatarioSeleccionado);
		
		// LLenar comboBoxPrestamosActivos con indices
		comboBoxPrestamosActivos.removeAllItems();
		for (Integer index : prestamosActivos) {
			comboBoxPrestamosActivos.addItem(index);
		}
	}
	
	private void cargarListaDeItemsFinalizar() {
		// Obtener el índice del préstamo seleccionado
		Integer indicePrestamoSeleccionado = (Integer) comboBoxPrestamosActivos.getSelectedItem();
		if (indicePrestamoSeleccionado == null) {
			return;
		}
		
		// Obtener los ítems del préstamo seleccionado
		List<String> itemsDelPrestamo = control.getListadoItemsDeUnPrestamo(indicePrestamoSeleccionado);
		
		// Llenar listaItemsFinalizar con los ítems del préstamo textAreaItemsDelPrestamo
		textAreaItemsDelPrestamo.setText(""); // Limpiar texto
		for (String item : itemsDelPrestamo) {
			textAreaItemsDelPrestamo.append(item + "\n");
		}
		
	}
	
	private void cargarPrestamosActivosModificar() {
		// Obtener prestatario del comboBoxPrestatarioModificar
		String prestatarioSeleccionado = (String) comboBoxPrestatarioModificar.getSelectedItem();
		if (prestatarioSeleccionado == null) {
            return;
        }
		
		// Obtener indices de prestamos activos del prestatario seleccionado
		List<Integer> prestamosActivos = control.obtenerIndexPrestamosDePersona(prestatarioSeleccionado);
		
		// Llenar comboBoxPrestamosActivos con indices
		comboBoxPrestamosActivosModificar.removeAllItems();
		for (Integer index : prestamosActivos) {
			comboBoxPrestamosActivosModificar.addItem(index);
		}
	}
	
	private void cargarListaDeItemsModificar() {
		// llenar Items prestados para modificar
		if (panelItemsPrestadosCheck != null) {
			panelItemsPrestadosCheck.removeAll();
			listaCheckboxesItemsPrestados.clear();
			
			Integer indicePrestamoSeleccionado = (Integer) comboBoxPrestamosActivosModificar.getSelectedItem();
			if (indicePrestamoSeleccionado != null) {
				List<String> itemsDelPrestamo = control.getListadoItemsDeUnPrestamo(indicePrestamoSeleccionado);
				// Generar un checkbox por cada ítem
				for (String nombreItem : itemsDelPrestamo) {
					JCheckBox checkBox = new JCheckBox(nombreItem);
					panelItemsPrestadosCheck.add(checkBox);
					listaCheckboxesItemsPrestados.add(checkBox);
				}
			}
			panelItemsPrestadosCheck.revalidate();
			panelItemsPrestadosCheck.repaint();
		}
	}

}