package control;

import logica.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ControladoraPrestamo {
	private static final String ARCHIVO_DATOS = "DatosPrestamo.bin";
	private static ControladoraPrestamo instancia;
	
	private Map<String, Item> items; // TreeMap - Ordenamiento automático de llaves por orden alfabético
	private List<Prestamo> prestamos; // ArrayList
	private Map<String, Persona> personas; // TreeMap
	private Map<String, Categoria> categorias; // TreeMap - Ordenamiento automático de llaves por orden alfabético
	private Map<String, Tipo> tipos; // TreeMap - Ordenamiento automático de llaves por orden alfabético
	
	public ControladoraPrestamo() {
		items = new java.util.TreeMap<>();
		prestamos = new java.util.ArrayList<>();
		personas = new java.util.TreeMap<>();
		categorias = new java.util.TreeMap<>();
		tipos = new java.util.TreeMap<>();
		
		// TEMPORAL: Carga datos de prueba Metal Gear
		cargarDatosPrueba();
	}
	
	public static ControladoraPrestamo getInstancia() {
		if (instancia == null) {
			instancia = new ControladoraPrestamo();
		}
		return instancia;
	}
	
	// Retorna detalles del item empaquetados
	public Map<String, String> getDetalleItem(String nombreItem) {
		Item item = items.get(nombreItem);

		if (item == null) {
			return null;
		}

		Map<String, String> datos = new java.util.TreeMap<>();

		datos.put("nombre", item.getNombre());
		datos.put("descripcion", item.getDescripcion());
		datos.put("tema", item.getCategoria().getTema());
		datos.put("formato", item.getTipo().getFormato());

		return datos;
	}
	
	// Retorna detalles de persona empaquetados
	public Map<String, String> getDetallePersona(String nombrePersona) {
		Persona persona = personas.get(nombrePersona);

		if (persona == null) {
			return null;
		}

		Map<String, String> datos = new java.util.TreeMap<>();

		datos.put("nombre", persona.getNombre());
		datos.put("telefono", persona.getTelefono());
		datos.put("email", persona.getEmail());

		return datos;
	}
	
	public boolean crearItem(String nombre, String descripcion, String nombreCategoria, String nombreTipo) {
		if (items.containsKey(nombre)) {
			return false; // El item ya existe
		}
		Categoria categoria = categorias.get(nombreCategoria);
		Tipo tipo = tipos.get(nombreTipo);
		if (categoria == null || tipo == null) {
			return false; // La categoría o el tipo no existen
		}
		Item nuevoItem = new Item(nombre, descripcion, tipo, categoria);
		items.put(nombre, nuevoItem);
		categoria.setItem(nuevoItem);
		tipo.agregarItem(nuevoItem);
		return true;
	}

	public boolean modificarItem(String nombre, String nuevaDescripcion, String nuevaCategoria, String nuevoTipo) {
		Item item = items.get(nombre);
		if (item == null) {
			return false; // El item no existe
		}
		Categoria categoria = categorias.get(nuevaCategoria);
		Tipo tipo = tipos.get(nuevoTipo);
		if (categoria == null || tipo == null) {
			return false; // La nueva categoría o el nuevo tipo no existen
		}
		// Obtener antiguos antes de cambiar
		Categoria categoriaAntigua = item.getCategoria();
		Tipo tipoAntiguo = item.getTipo();

		// Remover item antiguos de sus categorías y tipos anteriores
		categoriaAntigua.eliminarItem(nombre);
		tipoAntiguo.eliminarItem(nombre);

		// Asignar los nuevos
		item.setDescripcion(nuevaDescripcion);
		item.asignarCategoria(categoria); // Asignar nueva categoría al item
		item.asignarTipo(tipo); // Asignar el nuevo tipo al item
		categoria.setItem(item); // Item a categoría
		tipo.agregarItem(item); // Item a tipo
		return true;
	}
		
	public boolean borrarItem(String nombre) {
		if (!items.containsKey(nombre)) {
			return false; // El item no existe
		}
		
		// Obtener el item, categoría y tipo a eliminar
		Item item = items.get(nombre);
	    Categoria cat = item.getCategoria();
	    Tipo tipo = item.getTipo();
	    cat.eliminarItem(nombre);
	    tipo.eliminarItem(nombre);
		
		items.remove(nombre);
		return true;
	}
	
	public boolean agregarCategoriaAItem(String nombreItem, String nombreCategoria) {
		Item item = items.get(nombreItem);
		Categoria categoria = categorias.get(nombreCategoria);
		if (item == null || categoria == null) {
			return false;
		}
		// Se asigna a ambos lados
		item.asignarCategoria(categoria); // Item > Categoria
		categoria.setItem(item); // Categoria > Item
		return true;
	}
	
	public boolean eliminarCategoriaDeItem(String nombreItem, String nombreCategoria) {
		Item item = items.get(nombreItem);
		Categoria categoria = categorias.get(nombreCategoria);
		if (item == null || categoria == null) {
			return false;
		}
		// Se remueve de ambos lados
		item.removerCategoria(categoria); // Item > quita Categoria
		categoria.eliminarItem(nombreItem); // Categoria > quita Item
		return true;
	}
	
	public boolean cambiarTipoDeItem(String nombreItem, String nombreTipo) {
		Item item = items.get(nombreItem);
		Tipo nuevoTipo = tipos.get(nombreTipo);
		if (item == null || nuevoTipo == null) {
			return false;
		}
		// Obtener el tipo antiguo para remover el item de ese tipo
		Tipo tipoAntiguo = item.getTipo();
		if (tipoAntiguo != null) {
			tipoAntiguo.eliminarItem(nombreItem);
		}
		// Asignar el nuevo tipo al item y agregar el item al nuevo tipo
		item.asignarTipo(nuevoTipo); // Item > Tipo
		nuevoTipo.agregarItem(item); // Tipo > Item
		return true;
	}

	public boolean asignarAlertaAPrestamo(int indicePrestamo, String mensaje, int enDias, boolean esRecurrente,
			int intervalo) {
		if (indicePrestamo < 0 || indicePrestamo >= prestamos.size()) {
			return false; // No hay prestamos en esa posición
		}

		// Se calcula la fecha sumando días a la fecha actual
		java.time.LocalDateTime fechaAlerta = java.time.LocalDateTime.now().plusDays(enDias);

		Alerta alerta = new Alerta(mensaje, fechaAlerta, esRecurrente, intervalo);
		Prestamo prestamo = prestamos.get(indicePrestamo);
		prestamo.setAlerta(alerta);
		return true;
	}

	public String consultarItem(String nombre) {
		Item item = items.get(nombre);
		if (item == null) {
			return "El item no existe.";
		}
		return "Nombre: " + item.getNombre() + "\nDescripción: " + item.getDescripcion() + "\nCategorías: "
				+ item.getCategoria().getTema() + "\nTipo: " + item.getTipo().getNombre();
	}
	
	// Listado de items disponibles para prestamo
	public List<String> getListadoItemsDisponibles() {
		List<String> disponibles = new ArrayList<>();
		for (Item item : items.values()) {
			// Disponible si no hay prestamo asignado
			if (item.getPrestamoActual() == null) {
				disponibles.add(item.getNombre());
			}
		}
		return disponibles;
	}

	// Listado de items prestados por indice de prestamo
	public List<String> getListadoItemsDeUnPrestamo(int indicePrestamo) {
		List<String> prestados = new ArrayList<>();
		if (indicePrestamo < 0) {
			return prestados; // No hay prestamos en esa posición
		}
		
		for (String item : prestamos.get(indicePrestamo).getItemsPrestados().keySet()) {
			prestados.add(item);
			}
		return prestados;
	}
	
	public boolean crearPerosna(String nombre, String telefono, String email) {
		if (personas.containsKey(nombre)) {
			return false; // La persona ya existe
		}
		Persona nuevaPersona = new Persona(nombre, telefono, email);
		personas.put(nombre, nuevaPersona);
		return true;
	}
	
	public boolean modificarPersona(String nombre, String nuevoTelefono, String nuevoEmail) {
		Persona persona = personas.get(nombre);
		if (persona == null) {
			return false; // La persona no existe
		}
		persona.setTelefono(nuevoTelefono);
		persona.setEmail(nuevoEmail);
		return true;
	}
	
	public boolean borrarPersona(String nombre) {
		if (!personas.containsKey(nombre)) {
			return false; // La persona no existe
		}
		personas.remove(nombre);
		return true;
	}
	
	public String consultarPersona(String nombre) {
		Persona persona = personas.get(nombre);
		if (persona == null) {
			return "La persona no existe.";
		}
		return "Nombre: " + persona.getNombre() + "\nTeléfono: " + persona.getTelefono() + "\nEmail: " + persona.getEmail();
	}
	
	// Obtiene indices de prestamos en la controladora de un prestatario
	public List<Integer> obtenerIndexPrestamosDePersona(String nombrePersona) {
		List<Integer> indices = new ArrayList<>();
		Persona persona = personas.get(nombrePersona);
		
		// Obtener indices de prestamos de la persona (indices en controladora)
		for (Prestamo prestamo : persona.getPrestamos()) {
	        int indice = prestamos.indexOf(prestamo);
	        if (indice != -1) {
	            indices.add(indice);
	        }
	    }
		
		return indices;
	}
	
	public boolean crearCategoria(String tema) {
		if (categorias.containsKey(tema)) {
			return false; // La categoría ya existe
		}
		Categoria nuevaCategoria = new Categoria(tema);
		categorias.put(tema, nuevaCategoria);
		return true;
	}
	
	public boolean modificarCategoria(String temaActual, String nuevoTema) {
		Categoria categoria = categorias.get(temaActual);
		if (categoria == null) {
			return false; // La categoría no existe
		}
		// Eliminar
		categorias.remove(temaActual);
		// Cambiar el tema
		categoria.setTema(nuevoTema);
		// Re-insertar
		categorias.put(nuevoTema, categoria);
		return true;
	}
	
	public boolean borrarCategoria(String nombreCategoria) {
		if (!categorias.containsKey(nombreCategoria)) {
			return false; // La categoría no existe
		}
		categorias.remove(nombreCategoria);
		return true;
	}
	
	public String consultarCategoria(String nombreCategoria) {
		Categoria categoria = categorias.get(nombreCategoria);
		if (categoria == null) {
			return "La categoría no existe.";
		}
		return "Tema: " + categoria.getTema();
	}
	
	public boolean crearTipo(String nombreTipo) {
		if (tipos.containsKey(nombreTipo)) {
			return false; // El tipo ya existe
		}
		Tipo nuevoTipo = new Tipo(nombreTipo);
		tipos.put(nombreTipo, nuevoTipo);
		return true;
	}
	
	public boolean modificarTipo(String nombreActual, String nuevoNombre) {
		Tipo tipo = tipos.get(nombreActual);
		if (tipo == null) {
			return false; // El tipo no existe
		}
		// Eliminar
		tipos.remove(nombreActual);
		// Cambiar el formato
		tipo.setFormato(nuevoNombre);
		// Re-insertar
		tipos.put(nuevoNombre, tipo);
		return true;
	}
	
	public boolean borrarTipo(String nombreTipo) {
		if (!tipos.containsKey(nombreTipo)) {
			return false; // El tipo no existe
		}
		tipos.remove(nombreTipo);
		return true;
	}
	
	public String consultarTipo(String nombreTipo) {
		Tipo tipo = tipos.get(nombreTipo);
		if (tipo == null) {
			return "El tipo no existe.";
		}
		return "Formato: " + tipo.getFormato();
	}
	
	public boolean hacerPrestamo(String nombrePrestatario, int diasLimite) {
		Persona prestatario = personas.get(nombrePrestatario);
		if (prestatario == null) {
			return false; // El prestatario no existe
		}
		Prestamo nuevoPrestamo = new Prestamo(prestatario, diasLimite);
		prestamos.add(nuevoPrestamo);
		prestatario.setPrestamo(nuevoPrestamo); // Agrega el préstamo a la persona
		return true;
	}
	
	public boolean agregarItemAlPrestamo(int indicePrestamo, String nombreItem) {
		if (indicePrestamo < 0 || indicePrestamo >= prestamos.size()) {
			return false; // Índice de préstamo no válido
		}
		Item item = items.get(nombreItem);
		if (item == null) {
			return false; // El item no existe
		}
		Prestamo prestamo = prestamos.get(indicePrestamo);
		prestamo.setItem(item);
		item.asignarPrestamoActual(prestamo); // Agrega el préstamo al item
		return true;
	}
	
	public boolean eliminarItemDelPrestamo(int indicePrestamo, String nombreItem) {
		if (indicePrestamo < 0 || indicePrestamo >= prestamos.size()) {
			return false; // Índice de préstamo no válido
		}
		Item item = items.get(nombreItem);
		if (item == null) {
			return false; // El item no existe
		}
		Prestamo prestamo = prestamos.get(indicePrestamo);
		prestamo.removerItem(item);
		item.removerPrestamoActual(); // Elimina el préstamo del item
		return true;
	}
				
	public boolean finalizarPrestamo(int indicePrestamo) {
		if (indicePrestamo < 0 || indicePrestamo >= prestamos.size()) {
			return false; // Índice de préstamo no válido
		}
		
		Prestamo prestamo = prestamos.get(indicePrestamo);
		
		// Remover préstamo de cada item prestado
		for (Item item : prestamo.getItemsPrestados().values()) {
			item.removerPrestamoActual();
		}
		// Remover items del Prestamo y Prestamo de Persona
		prestamo.getItemsPrestados().clear();
		prestamo.getPrestatario().eliminarPrestamo(prestamo);
		// Remover préstamo de la lista de préstamos
		prestamos.remove(indicePrestamo);
		return true;
	}
	
	// Métodos para obtener listados de personas, items, categorías y tipos
	public List<String> getListadoPersonas() {
		return new java.util.ArrayList<>(personas.keySet());
	}
	
	public List<String> getListadoItems() {
		return new java.util.ArrayList<>(items.keySet());
	}
	
	public List<String> getListadoCategorias() {
		return new java.util.ArrayList<>(categorias.keySet());
	}
	
	public List<String> getListadoTipos() {
		return new java.util.ArrayList<>(tipos.keySet());
	}
	
	// Listado de prestamos para buscar por índice
	public List<Prestamo> getListadoPrestamos() {
        return new java.util.ArrayList<>(prestamos);
    }
	
	public String reportePorUsuario() {
		StringBuilder reporte = new StringBuilder();
		
		// Itera las personas
		for (Persona persona : personas.values()) {
			reporte.append("Usuario: ").append(persona.getNombre()).append("\n");
			
			// Itera los préstamos de la persona
			for (Prestamo prestamo : prestamos) {
				if (prestamo.getPrestatario().equals(persona)) {
					reporte.append("  Fecha de préstamo: ").append(prestamo.getFechaPrestamo()).append("\n");
					
					// Itera los items del préstamo
					for (String item : prestamo.getItemsPrestados().keySet()) {
						reporte.append("    Item: ").append(item).append("\n");
					}
				}
			}
		}
		// Retorna el reporte como String
		return reporte.toString();
	}

	public String reportePorItem() {
		StringBuilder reporte = new StringBuilder();
		
		// Itera los items
		for (Item item : items.values()) {
			reporte.append("Item: ").append(item.getNombre()).append("\n");
			
			// Revisa si tiene un préstamo activo
			if (item.getPrestamoActual() != null) {
				Prestamo prestamo = item.getPrestamoActual();
				reporte.append("  Prestado a: ").append(prestamo.getPrestatario().getNombre()).append("\n");
				reporte.append("  Fecha de préstamo: ").append(prestamo.getFechaPrestamo()).append("\n");
			} else {
				reporte.append("  Estado: Disponible\n");
			}
		}
		// Retorna el reporte como String
		return reporte.toString();
	}

	public String reportePorCategoria() {
		StringBuilder reporte = new StringBuilder();
		
		// Itera categorías
		for (String nombreCategoria : categorias.keySet()) {
			reporte.append("Categoría: ").append(nombreCategoria).append("\n");
			Categoria categoria = categorias.get(nombreCategoria);
			
			// Itera items pertenecientes a esta categoría
			for (Item item : items.values()) {
				if (item.getCategoria().equals(categoria)) {
					reporte.append("  Item: ").append(item.getNombre()).append("\n");
				}
			}
		}
		// Retorna el reporte como String
		return reporte.toString();
	}

	public String reportePorTipo() {
		StringBuilder reporte = new StringBuilder();
		
		// Itera tipos
		for (String nombreTipo : tipos.keySet()) {
			reporte.append("Tipo: ").append(nombreTipo).append("\n");
			Tipo tipo = tipos.get(nombreTipo);
			
			// Itera items pertenecientes a este tipo
			for (Item item : items.values()) {
				if (item.getTipo().equals(tipo)) {
					reporte.append("  Item: ").append(item.getNombre()).append("\n");
				}
			}
		}
		// Retorna el reporte como String
		return reporte.toString();
	}

	// TEMPORAL: Carga datos de prueba Metal Gear
	private void cargarDatosPrueba() {
		// Personass
		crearPerosna("Ivan Raikov", "555-0101", "ivan@gru.ru");
		crearPerosna("Olga Gurlukovich", "555-0202", "olga@gru.ru");
		crearPerosna("Sergei Gurlukovich", "555-0303", "sergei@gru.ru");

		// Tipos
		crearTipo("Tecnología");
		crearTipo("Supervivencia");
		crearTipo("Curación");
		crearTipo("Visión");

		// Categorías
		crearCategoria("Comunicaciones");
		crearCategoria("Visión");
		crearCategoria("Camuflaje");
		crearCategoria("Raciones");
		crearCategoria("Equipamiento");
		crearCategoria("Medicina");

		// Items
		crearItem("Codec", "Dispositivo de comunicación a través de nanomáquinas.", "Comunicaciones", "Tecnología");
		crearItem("Thermal Goggles", "Gafas de visión térmica para detectar calor corporal.", "Visión", "Tecnología");
		crearItem("Cardboard Box", "Caja de cartón ideal para pasar desapercibido.", "Camuflaje", "Supervivencia");
		crearItem("Calorie Mate", "Bloque nutritivo sabor chocolate que restaura estamina.", "Raciones", "Supervivencia");
		crearItem("Gafas de Visión Nocturna", "Amplifica la luz ambiental, permitiendo ver con claridad en entornos completamente oscuros.", "Equipamiento", "Visión");
		crearItem("Ración", "Comida militar empaquetada. Restaura una porción moderada de tu salud al consumirla.", "Raciones", "Curación");
		crearItem("Medicina para el Resfriado", "Cura estornudos y tos, evitando que tu posición sea revelada por el ruido.", "Medicina", "Curación");
		
		// Préstamos
		hacerPrestamo("Ivan Raikov", 5);
		agregarItemAlPrestamo(0, "Codec");
		agregarItemAlPrestamo(0, "Calorie Mate");

		hacerPrestamo("Olga Gurlukovich", 10);
		agregarItemAlPrestamo(1, "Thermal Goggles");
		agregarItemAlPrestamo(1, "Cardboard Box");
	}

}