package servidor.gateways;

import java.rmi.Naming;
import java.rmi.RemoteException;

import server.remote.IRemoteFacade;

public class MetaGateway implements Gateway{
	private static MetaGateway instance;
	private IRemoteFacade meta;

	
	private MetaGateway() {
		try {		
			String URL = "//127.0.0.1:1099/Meta";
			this.meta = (IRemoteFacade) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("# Error locating remote fa�ade: " + ex);
		}
	}

	public static MetaGateway getInstance(){
		if(instance==null) {
			instance= new MetaGateway();
		}return instance;
	}
	
	
	public boolean validarEmail(String email) {
		try {
			return meta.validarEmail(email);
		}catch(RemoteException e) {
			e.printStackTrace();
		}return false;
	}
	public boolean validarUsuario(String email,String password) {
		try {
			return meta.validarUsuario(email,password);
		}catch(RemoteException e) {
			e.printStackTrace();
		}return false;
	}
}

