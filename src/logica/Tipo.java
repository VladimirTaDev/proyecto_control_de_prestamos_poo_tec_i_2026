package logica;

import java.util.Collection;
import java.util.Map;

public class Tipo {
	private String formato; // Ejemplo: Libro, Revista, DVD, etc.
	private Map<String, Item> items; // TreeMap - Ordenamiento automático de llaves por orden alfabético

	public Tipo(String formato) {
		this.formato = formato;
		this.items = new java.util.TreeMap<>();
	}
	
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	public String getFormato() {
		return formato;
	}
		
	public String getNombre() {
		return formato;
	}
	
	public boolean agregarItem(Item item) {
		if (items.containsKey(item.getNombre())) {
			return false; // El item ya existe en este tipo
		}
		items.put(item.getNombre(), item);
		return true;
	}
	
	public boolean eliminarItem(String nombre) {
		if (!items.containsKey(nombre)) {
			return false; // El item no existe en este tipo
		}
		items.remove(nombre);
		return true;
	}
}
