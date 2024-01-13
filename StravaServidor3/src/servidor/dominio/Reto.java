package servidor.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Reto {
	private int objetivo;
	private String descripcion;
	@Id
	private String nombre;
	private List<Actividad> actividades;
	@ManyToOne
	private Usuario creador;
	@ManyToOne
	private Usuario usuario;
	private Date fecha_ini;
	private Date fecha_fin;
	private TipoObjectivo tipoObjetivo;
	
	
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

	public Reto(int objetivo, String descripcion, String nombre,Date fecha_ini, Date fecha_fin,List<Actividad> actividades,TipoObjectivo tipoObjetivo,Usuario creador,Usuario usuario) {
		super();
		this.objetivo = objetivo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.actividades = actividades;
		this.fecha_fin= fecha_fin;
		this.fecha_ini= fecha_ini;
		this.tipoObjetivo= tipoObjetivo;
		this.creador=creador;
		this.usuario=usuario;
	}
	
	public Reto() {
		super();
		
	}
	
	@Override
	public String toString() {
		return "Reto [objetivo=" + objetivo + ", descripcion=" + descripcion + ", nombre=" + nombre + ", actividades="
				+ actividades +fecha_fin;
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
	

	public TipoObjectivo getTipoObjectivo() {
		return tipoObjetivo;
	}

	public void setTipoObjectivo(TipoObjectivo tipoObjectivo) {
		this.tipoObjetivo = tipoObjectivo;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Reto) {
			if ((Reto) obj != null) {
				return this.nombre.equals(((Reto) obj).nombre);
			}
		}
		return false;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public TipoObjectivo getTipoObjetivo() {
		return tipoObjetivo;
	}

	public void setTipoObjetivo(TipoObjectivo tipoObjetivo) {
		this.tipoObjetivo = tipoObjetivo;
	}
}
