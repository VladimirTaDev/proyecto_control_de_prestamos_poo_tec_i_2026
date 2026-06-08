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
	
	public void setItem(Item item) {
		items.put(item.getNombre(), item);
	}
	
	public Collection<Item> getItems() {
		return this.items.values();
	}
	
	public String getNombre() {
		return formato;
	}

}
