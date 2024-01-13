package servidor.services;

import java.util.Date;
import java.util.HashMap;

import servidor.dao.UsuarioDAO;
import servidor.dominio.MetodoLogin;
import servidor.dominio.Usuario;
import servidor.gateways.Fabrica;
import servidor.gateways.GoogleGateway;
import servidor.gateways.MetaGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {

	private static LoginAppService instance;
	private HashMap<String, String> mapaPassword = new HashMap<String, String>();
	private HashMap<String, Usuario> mapaUsuarios = new HashMap<String, Usuario>();

	public static LoginAppService getInstance() {
		if (instance == null) {
			instance = new LoginAppService();
		}
		return instance;
	}

	public Usuario login(String email, String password, MetodoLogin metodo) {
		if(mapaUsuarios.get(email)!=null) {
			Fabrica.getInstance().createGateway(metodo).validarUsuario(email, password);
			System.out.println(mapaUsuarios.get(email));
			return UsuarioDAO.getInstance().find(email);
		}return null;
	}

	public Usuario registro(String email, String password, String nombre, Date fecha_nac, int peso_kilo, int altura,
			int frecuencia_card, int frecuencia_card_max, MetodoLogin metodo) {
		if (MetodoLogin.GOOGLE .equals(metodo)) {
			System.out.println("El problema es el puto gateway");
			if (GoogleGateway.getInstance().validarEmail(email)) {
				System.out.println("······");
				Usuario user = new Usuario(nombre, email, fecha_nac, peso_kilo, altura, frecuencia_card_max,
						frecuencia_card,metodo);
				UsuarioDAO.getInstance().store(user);
				System.out.println("Bolas");
				// Generate the hash of the password
				String sha2 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
				mapaUsuarios.put(user.getEmail(), user);
				mapaPassword.put(email, sha2);
				if (this.mapaPassword.get(email).equals(sha2)) {
					return user;
				}
				System.out.println("prolemas");
				return null;
			}System.out.println(22);
		}
			if (MetodoLogin.META.equals(metodo)) {
				if (MetaGateway.getInstance().validarEmail(email)) {
					Usuario user = new Usuario(nombre, email, fecha_nac, peso_kilo, altura, frecuencia_card_max,
							frecuencia_card,metodo);
					UsuarioDAO.getInstance().store(user);
					// Generate the hash of the password
					String sha2 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
					mapaUsuarios.put(user.getEmail(), user);
					mapaPassword.put(email, sha2);
					if (this.mapaPassword.get(email).equals(sha2)) {
						return user;
					}
					return null;
				}
				return null;
			}
			System.out.println("EL metodo");
		return null;
	}
}
