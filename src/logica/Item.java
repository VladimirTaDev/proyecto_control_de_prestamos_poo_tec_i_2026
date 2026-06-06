package logica;

import java.util.Set;

public class Item {
	private String nombre;
	private String descripcion;
	private Set<Categoria> categorias; // TreeSet - Ordenamiento automático de elementos por orden alfabético y no permite duplicados

	public Item(String nombre, String descripcion, Tipo tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categorias = new java.util.TreeSet<>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean esPrestadp() {
		return !categorias.isEmpty();
	}
	
	public void asignarTipo(Tipo tipo) {
		// No implementado.
	}
	
	public void asignarCategoria(Categoria categoria) {
		categorias.add(categoria);
	}
	
	public void removerCategoria(Categoria categoria) {
		categorias.remove(categoria);
	}
	
	public void asignarPrestamoActual() {
		// No implementado.
		// Parametro: Prestamo prestamo
	}
	
	public void removerPrestamoActual() {
		// No implementado.
		// Parametro: Prestamo prestamo
	}

}
