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
	private List<Persona> personas;
	private Set<Categoria> categorias; // TreeSet - Ordenamiento automático de elementos por orden alfabético y no permite duplicados
	private List<Tipo> tipos;
	
	public ControladoraPrestamo() {
		items = new java.util.TreeMap<>();
		prestamos = new java.util.ArrayList<>();
		personas = new java.util.ArrayList<>();
		categorias = new java.util.TreeSet<>();
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

}
