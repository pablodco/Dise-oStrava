package servidor.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reto {
	private int objetivo;
	private String descripcion;
	private String nombre;
	private List<Actividad> actividades;
	private List<Usuario> listaParticipantes;
	private Date fecha_ini;
	private Date fecha_fin;
	
	
	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getObjetivo() {
		return objetivo;
	}

	public Reto(int objetivo, String descripcion, String nombre,Date fecha_ini, Date fecha_fin,List<Actividad> actividades) {
		super();
		this.objetivo = objetivo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.actividades = actividades;
		this.listaParticipantes =new ArrayList<Usuario>();
		this.fecha_fin= fecha_fin;
		this.fecha_ini= fecha_ini;
	}
	
	public Reto() {
		super();
		
	}
	
	@Override
	public String toString() {
		return "Reto [objetivo=" + objetivo + ", descripcion=" + descripcion + ", nombre=" + nombre + ", actividades="
				+ actividades + ", listaParticipantes=" + listaParticipantes + "]"+fecha_fin;
	}

	public void setObjetivo(int objetivo) {
		this.objetivo = objetivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Usuario> getListaParticipantes() {
		return listaParticipantes;
	}

	public void setListaParticipantes(List<Usuario> listaParticipantes) {
		this.listaParticipantes = listaParticipantes;
	}
	public boolean anadirUsuario(Usuario usuario) {
		if(usuario!=null) {
			this.listaParticipantes.add(usuario);
			return true;
		}return false;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Reto) {
			if ((Reto) obj != null) {
				return this.nombre.equals(((Reto) obj).nombre);
			}
		}
		return false;
	}
}
