package servidor.services;

import java.util.Date;
import java.util.HashMap;

import servidor.dominio.MetodoLogin;
import servidor.dominio.Usuario;
import servidor.gateways.GoogleGateway;
import servidor.gateways.MetaGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {
	
	
	private static LoginAppService instance;
	private HashMap<String, String> mapaPassword = new HashMap<String, String>();
	private HashMap<String, Usuario> mapaUsuarios= new HashMap<String, Usuario>();
	
	public static LoginAppService getInstance() {
		if (instance == null) {
			instance = new LoginAppService();
		}
		return instance;
	}
	public Usuario login(String email, String password) {
//		if(MetodoLogin.Google==0) {
//			GoogleGateway.getInstance().login(email,password);
//		}if(MetodoLogin.Meta==0) {
//			MetaGateway.getInstance().login(email,password);
//		}
		String sha2 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		if (this.mapaPassword.get(email).equals(sha2)) {
			return mapaUsuarios.get(email);
		} else {
			return null;
		}
	}
	
	public Usuario registro(String email, String password,String nombre,Date fecha_nac,int peso_kilo,int altura,int frecuencia_card,int frecuencia_card_max) {
		Usuario user = new Usuario(nombre,email,fecha_nac,peso_kilo,altura,frecuencia_card_max,frecuencia_card);
		// Generate the hash of the password
		String sha2 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		mapaUsuarios.put(user.getEmail(), user);
		mapaPassword.put(email, sha2);
		if (this.mapaPassword.get(email).equals(sha2)) {
			return user;
		} else {
			return null;
		}
	}
}