package servidor.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {

	private String nombre;
	private String email;
	private Date fecha_nac;
	private int peso_kilo;
	private int altura;
	private int frec_card_max;
	private int frec_card_rep;
	private List<Entrenamiento> listaEntrenamientos= new ArrayList<Entrenamiento>();
	private List<Reto> listaRetos= new ArrayList<Reto>(20);
	private List<Reto> listaRetosCreados = new ArrayList<Reto>();

	public Usuario(String nombre, String email, Date fecha_nac, int peso_kilo, int altura, int frec_card_max,
			int frec_card_rep, List<Entrenamiento> listaEntrenamientos, List<Reto> listaRetos,
			List<Reto> listaRetosCreados) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.fecha_nac = fecha_nac;
		this.peso_kilo = peso_kilo;
		this.altura = altura;
		this.frec_card_max = frec_card_max;
		this.frec_card_rep = frec_card_rep;
		this.listaEntrenamientos = listaEntrenamientos;
		this.listaRetos = listaRetos;
		this.listaRetosCreados = listaRetosCreados;
	}
	
	public Usuario(String nombre, String email, Date fecha_nac, int peso_kilo, int altura, int frec_card_max,
			int frec_card_rep) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.fecha_nac = fecha_nac;
		this.peso_kilo = peso_kilo;
		this.altura = altura;
		this.frec_card_max = frec_card_max;
		this.frec_card_rep = frec_card_rep;
	}

	public Usuario(String nombre, String email, Date fecha_nac) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.fecha_nac = fecha_nac;
	}

	public Usuario() {
		super();
		this.nombre = null;
		this.email = null;
		this.fecha_nac = null;
		this.peso_kilo = 0;
		this.altura = 0;
		this.frec_card_max = 0;
		this.frec_card_rep = 0;
	}

	public Usuario(Usuario usuario) {
		super();
		this.nombre = usuario.nombre;
		this.email = usuario.email;
		this.fecha_nac = usuario.fecha_nac;
		this.peso_kilo = usuario.peso_kilo;
		this.altura = usuario.altura;
		this.frec_card_max = usuario.frec_card_max;
		this.frec_card_rep = usuario.frec_card_rep;
		this.listaEntrenamientos = usuario.listaEntrenamientos;
		this.listaRetos = usuario.listaRetos;
		this.listaRetosCreados = usuario.listaRetosCreados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public int getPeso_kilo() {
		return peso_kilo;
	}

	public void setPeso_kilo(int peso_kilo) {
		this.peso_kilo = peso_kilo;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getFrec_card_max() {
		return frec_card_max;
	}

	public void setFrec_card_max(int frec_card_max) {
		this.frec_card_max = frec_card_max;
	}

	public int getFrec_card_rep() {
		return frec_card_rep;
	}

	public void setFrec_card_rep(int frec_card_rep) {
		this.frec_card_rep = frec_card_rep;
	}

	public List<Entrenamiento> getListaEntrenamientos() {
		return listaEntrenamientos;
	}

	public void setListaEntrenamientos(List<Entrenamiento> listaEntrenamientos) {
		this.listaEntrenamientos = listaEntrenamientos;
	}

	public List<Reto> getlistaRetos() {
		return listaRetos;
	}

	public void setlistaRetos(List<Reto> listaRetos) {
		this.listaRetos = listaRetos;
	}

	public List<Reto> getListaRetosCreados() {
		return listaRetosCreados;
	}

	public void setListaRetosCreados(List<Reto> listaRetosCreados) {
		this.listaRetosCreados = listaRetosCreados;
	}

	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((Usuario) obj).email);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", fecha_nac=" + fecha_nac + ", peso_kilo="
				+ peso_kilo + ", altura=" + altura + ", frec_card_max=" + frec_card_max + ", frec_card_rep="
				+ frec_card_rep + ", listaEntrenamientos=" + listaEntrenamientos + ", listaRetos=" + listaRetos
				+ ", listaRetosCreados=" + listaRetosCreados + "]";
	}

	public boolean anadirReto(Reto reto) {
		if (reto == null) {
			return false;
		}
		this.listaRetos.add(reto);
		return true;
		
	}

	public boolean crearReto(Reto reto) {
		if (reto != null) {
			this.listaRetosCreados.add(reto);
			return true;
		}
		return false;
	}

	public boolean anadirEntrenamiento(Entrenamiento entre) {
		if (entre != null) {
			this.listaEntrenamientos.add(entre);
			return true;
		}
		return false;
	}

	public List<Reto> obtenerRetosActivos() {
		try {
			List<Reto> listaRetosActivos = new ArrayList<Reto>();
			for (Reto reto : this.listaRetos) {
				System.out.println(reto);
				System.out.println(reto.getFecha_fin());
				System.out.println(reto.getFecha_ini());
				if (reto.getFecha_fin().after(new Date())) {
					listaRetosActivos.add(reto);
					
				}
			}System.out.println(listaRetosActivos);
			return listaRetosActivos;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("La lista no contiene elementos");
			return null;
		}
	}
		
	public Entrenamiento obtenerEntrenamientoPorTitulo(String titulo) {
		try {
			for (Entrenamiento entre : this.listaEntrenamientos) {
				if (entre.getTitulo().equals(titulo)) {
					return entre;
				}
			}
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("La lista no contiene elementos");
			return null;
		}
	}
}
