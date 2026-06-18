package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class Principal {

	private JFrame frameControlPrestamos;

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
		initialize();
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

}
