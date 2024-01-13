package servidor.gateways;

import servidor.dominio.MetodoLogin;
import servidor.gateways.Gateway;
import servidor.gateways.GoogleGateway;

public class Fabrica {
	private static Fabrica instance;
	private Fabrica() {}
	
	public static Fabrica getInstance() {
		if(instance == null) {
			instance = new Fabrica();
		}
		return instance;
	}
	
    public Gateway createGateway(MetodoLogin metodo) {
        switch (metodo) {
            case GOOGLE:
                return GoogleGateway.getInstance();
            case META:
            	return MetaGateway.getInstance();
            default:
                return null;
        }
    }
}
