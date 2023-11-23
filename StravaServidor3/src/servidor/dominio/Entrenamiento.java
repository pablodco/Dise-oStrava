package servidor.dominio;

import java.util.Date;


public class Entrenamiento {
	private long duracion;
	private String titulo;
	private Actividad actividad;
	private double distancia;
	private Date fecha_ini;

	public Entrenamiento(long duracion, String titulo, Actividad actividad, double distancia, Date fecha_ini) {
		super();
		this.duracion = duracion;
		this.titulo = titulo;
		this.actividad = actividad;
		this.distancia = distancia;
		this.fecha_ini = fecha_ini;
	}

	public Entrenamiento() {
		super();
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

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public double getdistancia() {
		return distancia;
	}

	public void setdistancia(double distancia) {
		this.distancia = distancia;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Entrenamiento) {
			if ((Entrenamiento) obj != null) {
				return this.getTitulo().equals(((Entrenamiento) obj).titulo);
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Entrenamiento [duracion=" + duracion + ", titulo=" + titulo + ", actividad=" + actividad
				+ ", distancia=" + distancia + ", fecha_ini=" + fecha_ini + "]";
	}
	
}
