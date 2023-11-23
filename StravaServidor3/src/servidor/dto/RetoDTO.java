package servidor.dto;

import java.io.Serializable;
import java.util.List;


public class RetoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int objetivo;
	private String descripcion;
	private String nombre;
	private String actividades;
	private String fecha_ini;
	private String fecha_fin;

	public RetoDTO(int objetivo, String descripcion, String nombre, String actividades,String fecha_ini,String fecha_fin) {
		super();
		this.objetivo = objetivo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.actividades= actividades;
		this.fecha_fin=fecha_fin;
		this.fecha_ini=fecha_ini;
	}


	public String getActividades() {
		return actividades;
	}


	public void setActividades(String actividades) {
		this.actividades = actividades;
	}


	public int getObjetivo() {
		return objetivo;
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

	
	public String getFecha_ini() {
		return fecha_ini;
	}


	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}


	public String getFecha_fin() {
		return fecha_fin;
	}


	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}


	@Override
	public String toString() {
		return "RetoDTO [objetivo=" + objetivo + ", descripcion=" + descripcion + ", nombre=" + nombre + "]"+actividades+ fecha_fin+fecha_ini;
	}

	public RetoDTO(int objetivo, String descripcion, String nombre) {
		super();
		this.objetivo = objetivo;
		this.descripcion = descripcion;
		this.nombre = nombre;
	}


	public RetoDTO() {
		// TODO Auto-generated constructor stub
		super();
		this.objetivo = 0;
		this.descripcion = null;
		this.nombre = null;
	}
	public boolean equals(Object obj) {
		if (obj instanceof RetoDTO) {
			if ((RetoDTO) obj != null) {
				return this.getNombre().equals(((RetoDTO) obj).getNombre())&& this.getFecha_ini().equals((((RetoDTO) obj)).getFecha_ini());
			}
		}
		return false;
	}
	
}
