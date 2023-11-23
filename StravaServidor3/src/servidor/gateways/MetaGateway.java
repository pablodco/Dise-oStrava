package servidor.gateways;



public class MetaGateway {
	private static MetaGateway instance;
	private MetaGateway() {
		
	}
	
	public static MetaGateway getInstance(){
		if(instance==null) {
			instance= new MetaGateway();
		}return instance;
	}
	
	
	public boolean login(String email,String password) {
		return true;
	}
}

