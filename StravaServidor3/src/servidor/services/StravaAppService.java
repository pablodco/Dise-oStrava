package servidor.services;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import servidor.dao.EntrenamientoDAO;
import servidor.dao.RetoDAO;
import servidor.dao.UsuarioDAO;
import servidor.dominio.Actividad;
import servidor.dominio.Entrenamiento;
import servidor.dominio.Reto;
import servidor.dominio.TipoObjectivo;
import servidor.dominio.Usuario;
import servidor.dto.EntrenamientoAssembler;
import servidor.dto.EntrenamientoDTO;

//TODO: Implement Singleton Pattern
public class StravaAppService {

	private static StravaAppService instance;
	// TODO: remove when DAO Pattern is implemented
	private List<Entrenamiento> entrenamientos = new ArrayList<>();
	private List<Reto> retosDisponibles = new ArrayList<>();

	private StravaAppService() {
		// TODO: remove when DAO Pattern is implemented
		this.initilizeData();
	}

	public static StravaAppService getInstance() {
		if (instance == null) {
			instance = new StravaAppService();
		}
		return instance;
	}

	private void initilizeData() {
		// Create Users
		Usuario user2 = new Usuario();
		user2.setEmail("sample@gmail.com");
		user2.setNombre("runner33");
		List<Actividad> running = new ArrayList<Actividad>();
		running.add(Actividad.running);
		running.add(Actividad.ciclismo);
		List<Actividad> e= new ArrayList<Actividad>();
		e.add(Actividad.running);// Create Articles
		DateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
		try {
			this.crearReto("Reto1", 200, "El reto consiste en recorrer 200 km ya sea en bici o corriendo",
					dt.parse("2023/04/23"), dt.parse("2023/12/01"), running, user2,TipoObjectivo.DISTANCIA);
			this.crearReto("Reto2", 200, "El reto consiste en recorrer 200 km ya sea en bici o corriendo",
					dt.parse("2023/04/23"), dt.parse("2023/12/01"), e, user2,TipoObjectivo.DISTANCIA);
			this.crearReto("Reto3", 200, "El reto consiste en recorrer 200 km ya sea en bici o corriendo",
					dt.parse("2023/04/23"), dt.parse("2023/10/01"), running, user2,TipoObjectivo.DISTANCIA);
		} catch (ParseException ex) {
		}
	}

	public boolean crearReto(String nombre, int objetivo, String descripcion, Date fecha_ini, Date fecha_fin,
			List<Actividad> actividades, Usuario usuario,TipoObjectivo tipo) {
		Reto reto = new Reto(objetivo, descripcion, nombre, fecha_ini, fecha_fin, actividades,tipo,usuario,	null);
		RetoDAO.getInstance().store(reto);
		return usuario.crearReto(reto) && this.retosDisponibles.add(reto);
	}

	public boolean aceptarReto(String nombre, Usuario usuario) {
		for (Reto reto : this.retosDisponibles) {
			if (reto.getNombre().equals(nombre)) {
				return usuario.anadirReto(reto);
				// reto.anadirUsuario(usuario)&&
			}
		}
		return false;
	}

	public List<Entrenamiento> obtenerEntrenamientos(Usuario usuario) throws RemoteException {
		if (usuario != null) {
			UsuarioDAO.getInstance().find(usuario.getEmail()).getListaEntrenamientos();
			return UsuarioDAO.getInstance().find(usuario.getEmail()).getListaEntrenamientos();
		} else {
			throw new RemoteException("Error al obtener los entrenamientos");
		}
	}

	public boolean crearEntrenamiento(long duracion, String titulo, Actividad actividad, double distincia,
			Date fecha_ini, Usuario usuario) {
			Entrenamiento entre = new Entrenamiento(duracion, titulo, actividad, distincia, fecha_ini,usuario);
			EntrenamientoDAO.getInstance().store(entre);
		return usuario.anadirEntrenamiento(entre);
	}

	public List<Reto> obtenerRetosActivos(Usuario usuario) {
		if (usuario != null) {
			System.out.println(usuario);
			System.out.println(usuario.obtenerRetosActivos());
			return UsuarioDAO.getInstance().find(usuario.getEmail()).obtenerRetosActivos();
		}
		return null;
	}

	public List<Reto> obtenerRetosDisponibles() {
		return RetoDAO.getInstance().findAll();
	}

	public double obtenerPorcentajeDeReto(String nombreReto, Usuario usuario) {
		if (usuario != null) {
			Reto reto= RetoDAO.getInstance().find(nombreReto);
			return usuario.obtenerPorcentajeDeReto(reto);
		}
		return 0;
	}

	public Entrenamiento obtenerEntrenamientoPorTitulo(Usuario usuario, String titulo) throws RemoteException {
		if (usuario != null) {
			return usuario.obtenerEntrenamientoPorTitulo(titulo);
		}
		return null;
	}

}
