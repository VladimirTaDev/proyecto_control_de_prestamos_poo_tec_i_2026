package logica;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Prestamo implements java.io.Serializable {
	private Persona prestatario;
	private LocalDateTime fechaPrestamo;
	private LocalDateTime fechaLimite;
	private Map<String, Item> itemsPrestados; // TreeMap - Ordenamiento automático de llaves por orden alfabético
	private Alerta alertaAsignada;

	public Prestamo(Persona prestatario, int diasLimite) {
		this.prestatario = prestatario;
		this.fechaPrestamo = LocalDateTime.now();
		this.fechaLimite = fechaPrestamo.plusDays(diasLimite); // Ejemplo: plazo de préstamo de 14 días
		this.itemsPrestados = new java.util.TreeMap<>(); 
	}
	
	public void setPrestatario(Persona prestatario) {
		this.prestatario = prestatario;
	}
	
	public Persona getPrestatario() {
		return prestatario;
	}
	
	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}
	
	public LocalDateTime getFechaPrestamo() {
		return fechaPrestamo;
	}
	
	public Map<String, Item> getItemsPrestados() {
		return itemsPrestados;
	}
	
	public void setFechaLimite(LocalDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	
	public int calcularDiasRetraso() {
		LocalDateTime ahora = LocalDateTime.now();
		if (ahora.isAfter(fechaLimite)) {
			return (int) java.time.Duration.between(fechaLimite, ahora).toDays(); // Calcula los días de retraso
		}
		return 0; // No hay retraso
	}
	
	public void setItem(Item item) {
		itemsPrestados.put(item.getNombre(), item);
	}
	
	public void removerItem(Item item) {
		itemsPrestados.remove(item.getNombre());
	}
	
	public void setAlerta(Alerta alerta) {
		this.alertaAsignada = alerta;
	}

}
