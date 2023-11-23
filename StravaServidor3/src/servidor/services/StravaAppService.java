package servidor.services;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import servidor.dominio.Actividad;
import servidor.dominio.Entrenamiento;
import servidor.dominio.Reto;
import servidor.dominio.Usuario;
import servidor.dto.EntrenamientoAssembler;
import servidor.dto.EntrenamientoDTO;

//TODO: Implement Singleton Pattern
public class StravaAppService {
	
	private static StravaAppService instance;
	//TODO: remove when DAO Pattern is implemented
	private List<Entrenamiento> entrenamientos = new ArrayList<>();
	private List<Reto> retosDisponibles = new ArrayList<>();
	
	
	private StravaAppService() {
		//TODO: remove when DAO Pattern is implemented
		this.initilizeData();
	}
	
	public static StravaAppService getInstance() {
		if (instance == null) {
			instance = new StravaAppService();
		}
		return instance;
	}
	
	private void initilizeData() {
		//Create Users
		Usuario user2 = new Usuario();
		user2.setEmail("sample@gmail.com");
		user2.setNombre("runner33");
		user2.setFecha_nac(new Date(2003,1,29));
								
		//Create Entrenamientos
		Date fecha= new Date(System.currentTimeMillis());
		Entrenamiento e1 = new Entrenamiento(30,"Correr al aire libre",Actividad.running,13,Calendar.getInstance().getTime());
		Entrenamiento e2 = new Entrenamiento(60, "ciclismo de interior", Actividad.ciclismo,24 , new Date(2023,4,20,16,0));	
		Entrenamiento e3 = new Entrenamiento(50, "ciclismo de interior", Actividad.running,10, new Date(2023,4,20,18,0));
		user2.anadirEntrenamiento(e3);
		List<Actividad> running= new ArrayList<Actividad>();
		running.add(Actividad.running);
		running.add(Actividad.ciclismo);
		//Create Articles
		fecha= new Date(2023, 10,26,16,30);
		DateFormat dt= new SimpleDateFormat("yyyy/MM/dd");
		try {
		this.crearReto("Reto1", 200, "El reto consiste en recorrer 200 km ya sea en bici o corriendo", dt.parse("2023/04/23"),dt.parse("2023/12/01"),running , user2);
		this.crearReto("Reto2", 200, "El reto consiste en recorrer 200 km ya sea en bici o corriendo", dt.parse("2023/04/23"),dt.parse("2023/12/01"),running , user2);		
		this.crearReto("Reto3", 200, "El reto consiste en recorrer 200 km ya sea en bici o corriendo", dt.parse("2023/04/23"),dt.parse("2023/10/01"),running , user2);		
		}catch (ParseException e) {
			}
		}
	
	public boolean crearReto(String nombre,int objetivo, String descripcion, Date fecha_ini, Date fecha_fin,List<Actividad> actividades, Usuario usuario) {
		Reto reto= new Reto(objetivo,descripcion,nombre,fecha_ini,fecha_fin,actividades);
		return usuario.crearReto(reto)&&this.retosDisponibles.add(reto);
	}

	public boolean aceptarReto(String nombre, Usuario usuario) {
		for(Reto reto : this.retosDisponibles) {
			if(reto.getNombre().equals(nombre)){
				System.out.println(reto);
				return usuario.anadirReto(reto); 
				//reto.anadirUsuario(usuario)&&
			}
		}
		return false;
	}
	public List<EntrenamientoDTO> obtenerEntrenamientos(Usuario usuario) throws RemoteException{
		if(usuario!= null) {
			EntrenamientoAssembler EA =EntrenamientoAssembler.getInstance();
			return EA.entreToDTO(usuario.getListaEntrenamientos());
		}else {
			throw new RemoteException("Error al obtener los entrenamientos");
		}
	}
	public boolean crearEntrenamiento(long duracion, String titulo, Actividad actividad, double distincia, Date fecha_ini,Usuario usuario) {
		Entrenamiento entre= new Entrenamiento(duracion,titulo,actividad,distincia,fecha_ini);
		return usuario.anadirEntrenamiento(entre);
	}
	
	public List<Reto> obtenerRetosActivos(Usuario usuario){
		if(usuario!= null){
			System.out.println(usuario.obtenerRetosActivos());
			return usuario.obtenerRetosActivos();
		}return null;
	}
	
	public List<Reto> obtenerRetosDisponibles(){
		return this.retosDisponibles;
	}
	
	
	public Entrenamiento obtenerEntrenamientoPorTitulo(Usuario usuario, String titulo) throws RemoteException{
		if(usuario!= null){
			return usuario.obtenerEntrenamientoPorTitulo(titulo);
		}return null;
	}
	
}
