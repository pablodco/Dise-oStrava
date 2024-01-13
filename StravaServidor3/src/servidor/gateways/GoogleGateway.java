package servidor.gateways;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import com.google.gson.Gson;

public class GoogleGateway implements Gateway {

	private static GoogleGateway instance;
	private static Gson gson = new Gson();

	private static String BaseURL= "http://127.0.0.1:8080/";

	private GoogleGateway() {
	}

	public void setInstance(GoogleGateway instance) {
		GoogleGateway.instance = instance;
	}

	public static GoogleGateway getInstance() {
		if(instance==null) {
			instance= new GoogleGateway();
		}
		return instance;
	}

//	@Autowired
//	public void setRestTemplate(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}

	@Override
	public boolean validarEmail(String email) {
		System.out.format("Deleting a user (id=11): %s/validarGmail/{Gmail}...", instance.BaseURL);

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BaseURL + "validarGmail/" + email))
					.build();

		StringBuffer buffer = new StringBuffer();
		System.out.println("DSADJAIJDAIJAIK");
		try {

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			if (response.statusCode() == 200) {
				boolean resultado = gson.fromJson(response.body(), Boolean.class);
				return resultado;
			}
		} catch (Exception e) {
		}

		System.out.println(buffer.toString());
		return false;
	}

	@Override
	public boolean validarUsuario(String email, String password) {
		 System.out.format("Validando usuario: %s/validarUsuario/%s/%s...", BaseURL, email, password);

		    HttpClient client = HttpClient.newHttpClient();

		    // Construye el cuerpo de la solicitud como un objeto JSON
		    String jsonBody = String.format("{\"Gmail\":\"%s\", \"password\":\"%s\"}", email, password);

		    HttpRequest request = HttpRequest.newBuilder()
		            .uri(URI.create(BaseURL + "validarUsuario"))
		            .header("Content-Type", "application/json")
		            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
		            .build();

		    try {
		        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		        if (response.statusCode() == 200) {
		            boolean resultado = gson.fromJson(response.body(), Boolean.class);
		            System.out.format("- Status code: %d\n", response.statusCode());
		            return resultado;
		        } else {
		            System.out.format("- Error: %d\n", response.statusCode());
		        }
		    } catch (Exception e) {
		        System.out.format("- ERROR: %s\n", e.getMessage());
		    }

		    return false;
		}
//	@Override
//	public boolean validarUsuario(String email, String password) {
//		String validarUsuarioUrl = serverURL + ":" + serverPort + "/validarUsuario/" + email + "/" + password + ";";
//			return restTemplate.getForObject(validarUsuarioUrl, Boolean.class);
//	}
//	
//	public boolean validarEmail(String email) {
//		String validarUsuarioUrl = serverURL + ":" + serverPort + "/validarGmail/" + email + ";";
//		return (boolean)restTemplate.getForObject(validarUsuarioUrl, Boolean.class);
//		
//	}
////
}
