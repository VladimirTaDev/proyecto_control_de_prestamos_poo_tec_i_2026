package control;

import logica.*;
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
				
	// No implementado correctamente, se asume que el item se retorna del préstamo
	public boolean retornarItemDelPrestamo(int indicePrestamo, String nombreItem) {
		
		return true; // El item se retornó del préstamo
	}
	
	// No implementado correctamente, se asume que el préstamo se finaliza al retornar el item
	public boolean finalizarPrestamo(int indicePrestamo) {
		// prestamo.finalizar(); ???
		return true; // El préstamo se finalizó
	}
	
	// Métodos para obtener listados de personas, items, categorías y tipos
	public List<Persona> getListadoPersonas() {
		return new java.util.ArrayList<>(personas.values());
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

	// TEMPORAL: Carga datos de prueba Metal Gear
	private void cargarDatosPrueba() {
		// Personass
		crearPerosna("Ivan Raikov", "555-0101", "ivan@gru.ru");
		crearPerosna("Olga Gurlukovich", "555-0202", "olga@gru.ru");
		crearPerosna("Sergei Gurlukovich", "555-0303", "sergei@gru.ru");

		// Tipos
		crearTipo("Tecnología");
		crearTipo("Supervivencia");

		// Categorías
		crearCategoria("Comunicaciones");
		crearCategoria("Visión");
		crearCategoria("Camuflaje");
		crearCategoria("Raciones");

		// Items
		crearItem("Codec", "Dispositivo de comunicación a través de nanomáquinas.", "Comunicaciones", "Tecnología");
		crearItem("Thermal Goggles", "Gafas de visión térmica para detectar calor corporal.", "Visión", "Tecnología");
		crearItem("Cigar", "Cigarro clásico, útil para detectar láseres infrarrojos.", "Raciones", "Supervivencia");
		crearItem("Cardboard Box", "Caja de cartón ideal para pasar desapercibido.", "Camuflaje", "Supervivencia");
		crearItem("Calorie Mate", "Bloque nutritivo sabor chocolate que restaura estamina.", "Raciones", "Supervivencia");

		// Préstamos
		hacerPrestamo("Ivan Raikov", 5);
		agregarItemAlPrestamo(0, "Codec");
		agregarItemAlPrestamo(0, "Calorie Mate");

		hacerPrestamo("Olga Gurlukovich", 10);
		agregarItemAlPrestamo(1, "Thermal Goggles");
		agregarItemAlPrestamo(1, "Cardboard Box");
	}

}