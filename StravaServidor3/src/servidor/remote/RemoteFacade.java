package servidor.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.SysexMessage;

import servidor.dominio.Actividad;
import servidor.dominio.Entrenamiento;
import servidor.dominio.MetodoLogin;
import servidor.dominio.Reto;
import servidor.dominio.TipoObjectivo;
import servidor.dominio.Usuario;
import servidor.dto.EntrenamientoAssembler;
import servidor.dto.EntrenamientoDTO;
import servidor.dto.RetoAssembler;
import servidor.dto.RetoDTO;
import servidor.services.LoginAppService;
import servidor.services.StravaAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {
	private static final long serialVersionUID = 1L;

	// Data structure for manage Server State
	private Map<Long, Usuario> serverState = new HashMap<>();
	private static DateFormat formatoConHora = new SimpleDateFormat("DD/MM/YYYY HH:mm");
	private static DateFormat formatoSinHora = new SimpleDateFormat("DD/MM/YYYY");
	// TODO: Remove this instances when Singleton Pattern is implemented

	public RemoteFacade() throws RemoteException {
		super();
	}

	@Override
	public synchronized long login(String email, String password, String metodo) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
		// If login() success user is stored in the Server State
		// If user is not logged in
		System.out.println(MetodoLogin.valueOf(metodo));
		Usuario user = LoginAppService.getInstance().login(email, password, MetodoLogin.valueOf(metodo));
		long token = Calendar.getInstance().getTimeInMillis();
		serverState.put(token, user);
		for (Map.Entry<Long, Usuario> entry : serverState.entrySet()) {
			if (entry.getValue().equals(user)) {
				return entry.getKey();
			}
		}
		throw new RemoteException("No existe el  usuario");
	}

	@Override
	public synchronized long registro(String email, String password, String nombre, String fecha_nac, int peso_kilo,
			int altura, int frecuencia_card, int frecuencia_card_max, String metodo) throws RemoteException {

		// Perform login() using LoginAppService
		try {
			Usuario user = LoginAppService.getInstance().registro(email, password, nombre,
					formatoSinHora.parse(fecha_nac), peso_kilo, altura, frecuencia_card, frecuencia_card_max,
					MetodoLogin.valueOf(metodo));
			if (user != null) {
				// If user is not logged in
				Long token = Calendar.getInstance().getTimeInMillis();
				this.serverState.put(token, user);
				return (token);
			} else {
				throw new RemoteException("Usuario no registrado");
			}
		} catch (ParseException e) {
			throw new RemoteException("EL formato de fecha no es valido");
		}

	}

	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout(): " + token);

		if (this.serverState.containsKey(token)) {
			// Logout means remove the User from Server State
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}

	@Override
	public synchronized boolean crearEntrenamiento(long token, long duracion, String titulo, String actividad,
			double distancia, Date fecha_ini) throws RemoteException {
		if (this.serverState.keySet().contains(token)) {
			StravaAppService.getInstance().crearEntrenamiento(duracion, titulo, Actividad.valueOf(actividad), distancia,
					fecha_ini, this.serverState.get(token));
			return true;
		}
		throw new RemoteException("Error al crear el entrenamiento");
	}

	@Override
	public synchronized List<EntrenamientoDTO> obtenerEntrenamientos(long token) throws RemoteException {
		if (this.serverState.containsKey(token)) {
			return EntrenamientoAssembler.getInstance().entreToDTO(
			StravaAppService.getInstance().obtenerEntrenamientos(this.serverState.get(token)));
		} else {
			throw new RemoteException("Error al obtener los entrenamientos");
		}
	}

	@Override
	public synchronized boolean crearReto(long token, int objetivo, String descripcion, String nombre, Date fecha_ini,
			Date fecha_fin, String actividades, String tipo) throws RemoteException {
		if (this.serverState.keySet().contains(token)) {
			List<Actividad> ListaActividades = new ArrayList<Actividad>();
			for (String s : actividades.split(",")) {
				ListaActividades.add(Actividad.valueOf(s));
			}
			StravaAppService.getInstance().crearReto(nombre, objetivo, descripcion, fecha_ini, fecha_fin,
					ListaActividades, this.serverState.get(token), TipoObjectivo.valueOf(tipo));
			return true;
		}
		return false;
	}

	@Override
	public synchronized List<RetoDTO> obtenerRetosDisponibles(long token) throws RemoteException {
		if (this.serverState.keySet().contains(token)) {
			List<Reto> listaRestos = StravaAppService.getInstance().obtenerRetosDisponibles();
			if (listaRestos != null) {
				// Convert domain object to DTO
				return RetoAssembler.getInstance().retoToDTO(listaRestos);
			} else {
				throw new RemoteException("Hubo un error al obtenr los retos activos");
			}
		} else {
			throw new RemoteException("El usuario no existe o no esta loggeado");
		}
	}

	@Override
	public synchronized List<RetoDTO> obtenerRetosActivos(long token) throws RemoteException {
		if (this.serverState.keySet().contains(token)) {
			List<Reto> listaRestos = StravaAppService.getInstance().obtenerRetosActivos(this.serverState.get(token));
			if (listaRestos != null) {
				// Convert domain object to DTO
				return RetoAssembler.getInstance().retoToDTO(listaRestos);
			} else {
				throw new RemoteException("Hubo un error al obtener los retos activos");
			}
		} else {
			throw new RemoteException("El usuario no existe o no esta loggeado");
		}
	}

	@Override
	public synchronized boolean aceptarReto(long token, String nombre) throws RemoteException {
		if (this.serverState.keySet().contains(token)) {
			return StravaAppService.getInstance().aceptarReto(nombre, this.serverState.get(token));
		} else {
			throw new RemoteException("Error al aceptar el reto");
		}
	}

	@Override
	public synchronized EntrenamientoDTO obtenerEntrenamientoPorTitulo(long token, String titulo)
			throws RemoteException {
		if (this.serverState.keySet().contains(token)) {
			try {
				Entrenamiento entre = StravaAppService.getInstance()
						.obtenerEntrenamientoPorTitulo(serverState.get(token), titulo);
				EntrenamientoDTO entreDTO = EntrenamientoAssembler.getInstance().entreToDTO(entre);
				return entreDTO;
			} catch (Exception e) {
				throw new RemoteException("No se encuentra el entrenamiento");
			}
		} else {
			throw new RemoteException("No se encuentra el entre");
		}
	}

	public synchronized double obtenerPorcentajeDeReto(RetoDTO reto, long token) throws RemoteException {
		if (this.serverState.containsKey(token)) {
			List<Actividad> listaActividades = new ArrayList<Actividad>();
			System.out.println(reto.getActividades());
			String[] partes = reto.getActividades().split(",");
			listaActividades.add(Actividad.valueOf(partes[0]));
			if (partes.length == 2) {
				listaActividades.add(Actividad.valueOf(partes[1]));
			}
			System.out.println(listaActividades);
			Reto reto2 = new Reto(reto.getObjetivo(), reto.getDescripcion(), reto.getNombre(), reto.getFecha_ini(),
					reto.getFecha_fin(), listaActividades, TipoObjectivo.valueOf(reto.getTipoObjectivo()),this.serverState.get(token),this.serverState.get(token));
			return StravaAppService.getInstance().obtenerPorcentajeDeReto(reto2.getNombre(), this.serverState.get(token));
		} else {
			throw new RemoteException("No se ha encontrado el token");
		}
	}
}