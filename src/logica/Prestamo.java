package logica;

import java.time.LocalDateTime;
import java.util.List;

public class Prestamo {
	private Persona prestatario;
	private LocalDateTime fechaPrestamo;
	private LocalDateTime fechaLimite;
	private List<Item> itemsPrestados;
	private Alerta alertaAsignada;

	public Prestamo(Persona prestatario, int diasLimite) {
		this.prestatario = prestatario;
		this.fechaPrestamo = LocalDateTime.now();
		this.fechaLimite = fechaPrestamo.plusDays(diasLimite); // Ejemplo: plazo de préstamo de 14 días
		this.itemsPrestados = new java.util.ArrayList<>();
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
		itemsPrestados.add(item);
	}
	
	public void removerItem(Item item) {
		itemsPrestados.remove(item);
	}
	
	public void setAlerta(Alerta alerta) {
		this.alertaAsignada = alerta;
	}

}
