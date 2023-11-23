package servidor.dto;

import java.io.Serializable;
import java.util.Date;

import servidor.dominio.Entrenamiento;

public class EntrenamientoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long duracion;
	private String titulo;
	private double distancia;
	private Date fecha_ini;
	private String actividad;
	public EntrenamientoDTO() {
		super();
	}



	public EntrenamientoDTO(long duracion, String titulo, double distancia, Date fecha_ini,String actividad) {
		super();
		this.duracion = duracion;
		this.titulo = titulo;
		this.distancia = distancia;
		this.fecha_ini = fecha_ini;
		this.actividad= actividad;
	}
	
	

	public String getActividad() {
		return actividad;
	}



	public void setActividad(String actividad) {
		this.actividad = actividad;
	}



	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EntrenamientoDTO [duracion=" + duracion + ", titulo=" + titulo + ", distancia=" + distancia
				+ ", fecha_ini=" + fecha_ini + "]";
	}


	public boolean equals(Object obj) {
		if (obj instanceof EntrenamientoDTO) {
			if ((EntrenamientoDTO) obj != null) {
				return this.getTitulo().equals(((EntrenamientoDTO) obj).getTitulo());
			}
		}
		return false;
	}

}
