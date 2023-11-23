package servidor.remote;

import java.util.Date;
import java.util.List;
import java.rmi.*;

import servidor.dominio.Actividad;
import servidor.dominio.Reto;
import servidor.dto.EntrenamientoDTO;
import servidor.dto.RetoDTO;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {

	public long login(String email, String password) throws RemoteException;
	
	public long registro(String email, String password, String nombre, String fecha_nac, int peso_kilo,
			int altura, int frecuencia_card, int frecuencia_card_max) throws RemoteException ;

	public void logout(long token) throws RemoteException;

	public boolean crearEntrenamiento(long token,long duracion, String titulo, String actividad, double distincia, Date fecha_ini) throws RemoteException;

	public boolean crearReto(long token, int objetivo, String descripcion, String nombre,
			String fecha_ini, String fecha_fin,String Actividades) throws RemoteException;

	public List<RetoDTO> obtenerRetosDisponibles(long token) throws RemoteException;

	public boolean aceptarReto(long token,String nombre) throws RemoteException;
	
	public List<RetoDTO> obtenerRetosActivos(long token) throws RemoteException ;
	
	public List<EntrenamientoDTO> obtenerEntrenamientos(long token) throws RemoteException;
	
	public  EntrenamientoDTO obtenerEntrenamientoPorTitulo(long token,String titulo) throws RemoteException;
}