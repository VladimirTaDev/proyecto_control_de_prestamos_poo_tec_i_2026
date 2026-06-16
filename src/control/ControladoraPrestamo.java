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
		item.setDescripcion(nuevaDescripcion);
		item.asignarTipo(tipo);
		categoria.setItem(item);
		tipo.agregarItem(item);
		return true;
	}
		
	public boolean borrarItem(String nombre) {
		if (!items.containsKey(nombre)) {
			return false; // El item no existe
		}
		items.remove(nombre);
		return true;
	}
	
	public String consultarItem(String nombre) {
		Item item = items.get(nombre);
		if (item == null) {
			return "El item no existe.";
		}
		return "Nombre: " + item.getNombre() + "\nDescripción: " + item.getDescripcion() + "\nCategorías: "
				+ item.getCategoria() + "\nTipo: " + item.getTipo();
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
	
	public List<Item> getListadoItems() {
		return new java.util.ArrayList<>(items.values());
	}
	
	public List<Categoria> getListadoCategorias() {
		return new java.util.ArrayList<>(categorias.values());
	}
	
	public List<Tipo> getListadoTipos() {
		return new java.util.ArrayList<>(tipos.values());
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

}