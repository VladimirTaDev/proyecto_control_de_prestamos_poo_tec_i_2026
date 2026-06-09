package control;

import logica.*;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ControladoraPrestamo {
	private static final String ARCHIVO_DATOS = "DatosPrestamo.bin";
	private static ControladoraPrestamo instancia;
	
	private Map<String, Item> items; // TreeMap - Ordenamiento automático de llaves por orden alfabético
	private List<Prestamo> prestamos;
	private Map<String, Persona> personas; // TreeMap
	private Map<String, Categoria> categorias; // TreeMap - Ordenamiento automático de llaves por orden alfabético
	private List<Tipo> tipos;
	
	public ControladoraPrestamo() {
		items = new java.util.TreeMap<>();
		prestamos = new java.util.ArrayList<>();
		personas = new java.util.TreeMap<>();
		categorias = new java.util.TreeMap<>();
		tipos = new java.util.ArrayList<>();
	}
	
	public static ControladoraPrestamo getInstancia() {
		if (instancia == null) {
			instancia = new ControladoraPrestamo();
		}
		return instancia;
	}
	
	public boolean crearItem(String nombre, String descripcion, Categoria categoria, Tipo tipo) {
		if (items.containsKey(nombre)) {
			return false; // El item ya existe
		}
		Item nuevoItem = new Item(nombre, descripcion, tipo, categoria);
		items.put(nombre, nuevoItem);
		return true;
	}
	
	public boolean modificarItem(String nombre, String nuevaDescripcion, Categoria nuevaCategoria, Tipo nuevoTipo) {
		Item item = items.get(nombre);
		if (item == null) {
			return false; // El item no existe
		}
		item.setDescripcion(nuevaDescripcion);
		item.asignarCategoria(nuevaCategoria);
		item.asignarTipo(nuevoTipo);
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

}