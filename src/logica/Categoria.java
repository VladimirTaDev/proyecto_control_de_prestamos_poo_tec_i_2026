package logica;

import java.util.Map;

public class Categoria {
	private String tema;
	private Map <String, Item> items; // TreeMap - Ordenamiento automático de llaves por orden alfabético

	public Categoria(String tema) {
		this.tema = tema;
		this.items = new java.util.TreeMap<>();
	}
	
	public String getTema() {
		return tema;
	}
	
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public void setItem(Item item) {
		items.put(item.getNombre(), item);
	}
	
	public boolean eliminarItem(String nombreItem) {
		if (!items.containsKey(nombreItem)) {
			return false; // El item no existe
		}
		items.remove(nombreItem);
		return true;
	}

}
