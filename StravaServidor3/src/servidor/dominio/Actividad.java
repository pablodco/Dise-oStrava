package servidor.dominio;

import java.io.Serializable;

public enum Actividad implements Serializable{
	ciclismo,running; 
	private static final long serialVersionUID=1L;
	@Override
	public String toString() {
		if(this==Actividad.ciclismo) {
			return "ciclismo";
		}if(this==Actividad.running) {
			return "running";
		}return null;
	}

}
