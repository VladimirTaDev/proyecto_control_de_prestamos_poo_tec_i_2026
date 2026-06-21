package logica;

import java.util.List;

public class Persona {
	private String nombre;
	private String telefono;
	private String email;
	private List<Prestamo> prestamos;

	public Persona(String nombre, String telefono, String email) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.prestamos = new java.util.ArrayList<>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		// TODO: Validar formato de teléfono
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		// TODO: Validar formato de email
		this.email = email;
	}
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	
	public void setPrestamo(Prestamo prestamo) {
		prestamos.add(prestamo);
	}
	
	public void eliminarPrestamo(Prestamo prestamo) {
		prestamos.remove(prestamo);
	}
	
}
