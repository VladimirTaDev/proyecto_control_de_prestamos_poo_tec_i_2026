package logica;

import java.util.Set;

public class Item implements java.io.Serializable {
	private String nombre;
	private String descripcion;
	private Set<Categoria> categorias; // HashSet - No permite duplicados
	private Tipo tipoAsignado;
	private Prestamo prestamoActual = null;
	
	public Item(String nombre, String descripcion, Tipo tipo, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categorias = new java.util.HashSet<>();
		this.tipoAsignado = tipo;
		this.categorias.add(categoria);
		this.prestamoActual = prestamoActual;
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
		return this.prestamoActual != null;
	}
	
	public void asignarTipo(Tipo tipo) {
		this.tipoAsignado = tipo;
	}
	
	public Tipo getTipo() {
		return tipoAsignado;
	}
	
	public Prestamo getPrestamoActual() {
		return prestamoActual;
	}
	
	public Categoria getCategoria() {
		// TODO: Implementar retornar lista de categorías.
		if (categorias.isEmpty()) {
			return null; // No hay categorías asignadas
		}
		return categorias.iterator().next(); // Retorna la primera categoría asignada
	}
	
	public void asignarCategoria(Categoria categoria) {
		categorias.add(categoria);
	}
	
	public void removerCategoria(Categoria categoria) {
		categorias.remove(categoria);
	}
	
	public void asignarPrestamoActual(Prestamo prestamoActual) {
		this.prestamoActual = prestamoActual;
	}
	
	public void removerPrestamoActual() {
		this.prestamoActual = null;
	}
	
}
