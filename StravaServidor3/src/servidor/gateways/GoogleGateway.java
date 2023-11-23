package servidor.gateways;

public class GoogleGateway {
	private static GoogleGateway instance;
	
	private GoogleGateway() {
		
	}
	
	public static GoogleGateway getInstance(){
		if(instance==null) {
			instance= new GoogleGateway();
		}return instance;
	}
	
	
	public boolean login(String email,String password) {
		return true;
	}
}
