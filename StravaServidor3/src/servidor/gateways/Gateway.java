package servidor.gateways;

public interface Gateway {

		public boolean validarEmail(String email);
		public boolean validarUsuario(String email, String password);	

}
