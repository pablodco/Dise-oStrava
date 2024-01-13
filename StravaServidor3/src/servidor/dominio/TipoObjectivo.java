package servidor.dominio;

import java.io.Serializable;

public enum TipoObjectivo implements Serializable{
	distancia,tiempo,DISTANCIA,TIEMPO;
	private static final long serialVersionUID=1l;
	@Override
	public String toString() {
		if(this.equals(TipoObjectivo.DISTANCIA)||this.equals(TipoObjectivo.distancia)) {
			return "DISTANCIA";
		}if(this.equals(TipoObjectivo.TIEMPO)||this.equals(TipoObjectivo.tiempo)) {
			return "TIEMPO";
		}return null;
	}
//	@Override
//	public static TipoObjectivo valueOf(String s) {
//		if(s.toUpperCase().equals("DISTAnCIA")) {
//			return TipoObjectivo.DISTANCIA;
//		}	if(s.toUpperCase().equals("TIEMPO")) {
//			return TipoObjectivo.TIEMPO;
//		}return null;
//	}
//	
	
}
