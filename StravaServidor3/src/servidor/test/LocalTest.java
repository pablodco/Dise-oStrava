package servidor.test;

import servidor.dominio.Actividad;
import servidor.dominio.Entrenamiento;
import servidor.dominio.Usuario;
import servidor.dto.EntrenamientoDTO;
import servidor.dto.RetoDTO;
import servidor.remote.IRemoteFacade;
import servidor.remote.RemoteFacade;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LocalTest {
	private static DateFormat formatoSinHora = new SimpleDateFormat("yyyy/mm/dd");
	private static DateFormat formatoConHora = new SimpleDateFormat("yyyy/mm/dd HH:MM");
	public static void main(String[] args) {
		RemoteFacade facade = null;
		List<RetoDTO> categories = null;
		RetoDTO category = null;
		List<EntrenamientoDTO> articles = null;
		EntrenamientoDTO article = null;
		long token = -1l;
		
		try {
			facade = new RemoteFacade();
			try {
			Entrenamiento entre= new Entrenamiento(60,"correr 20",Actividad.running,12,formatoConHora.parse("2003/01/29 16:00"));
			
			Usuario user= new Usuario("pablodel@gmail.com","Pablo",formatoSinHora.parse("2003/01/29"),0, 0, 0, 0);
			user.anadirEntrenamiento(entre);
			}catch (ParseException e){
				e.printStackTrace();
			}
			
			token=facade.registro("pablo@gmail.com","Futbol11" ,"Pablo","2003/01/29",0, 0, 0, 0);
			
			facade.login( "pablodel@gmail.com", "Futbol11");
			System.out.println(token);
			facade.crearEntrenamiento(token,60,"correr 20",Actividad.running.toString(),12,new Date());
//			token= facade.login("thomas.e2001@gmail.com", "hola");
			
			List<RetoDTO> retosActivos=  facade.obtenerRetosActivos(token);
			List<RetoDTO> retosDisponibles=facade.obtenerRetosDisponibles(token);
			System.out.println(facade.aceptarReto(token, "A por el bote"));
			
//			facade.crearReto(token, 0, null, null, null, null,null);
			List<RetoDTO> retosDisponibles2=facade.obtenerRetosDisponibles(token);
			facade.crearEntrenamiento(token, 30, "Correr1", Actividad.running.toString(), 5,new Date());
			List<EntrenamientoDTO> entrenamientos= facade.obtenerEntrenamientos(token);
			for(RetoDTO reto: retosDisponibles2) {
				System.out.println(reto.toString());
			}
			for(RetoDTO reto: retosActivos) {
				System.out.println(reto.toString());
			}
			for(EntrenamientoDTO entre2: entrenamientos) {
				System.out.println(entre2.toString());
			}
			
		}catch (RemoteException e){
			System.out.println(e);
		}
		//Force exit to stop RMI Server
		System.exit(0);
	}
}
