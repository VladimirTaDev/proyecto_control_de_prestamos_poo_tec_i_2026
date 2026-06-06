package logica;

import java.time.LocalDateTime;

public class Alerta {
	private String mensae;
	private LocalDateTime fechaAlerta;
	private boolean esRecurrente;
	private int intervalo;

	public Alerta(String mensae, LocalDateTime fechaAlerta, boolean esRecurrente, int intervalo) {
		this.mensae = mensae;
		this.fechaAlerta = fechaAlerta;
		this.esRecurrente = esRecurrente;
		this.intervalo = intervalo;
	}
	
	public void setMensae(String mensae) {
		this.mensae = mensae;
	}
	
	public String getMensae() {
		return mensae;
	}
	
	public LocalDateTime getFechaAlerta() {
		return fechaAlerta;
	}
	
	public void setFechaAlerta(LocalDateTime fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}
}