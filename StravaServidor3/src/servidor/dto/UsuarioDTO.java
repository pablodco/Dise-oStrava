package servidor.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String email;
	private Date fecha_nac;
	private int peso_kilo;
	private int altura;
	private int frec_card_max;
	private int frec_card_rep;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public int getPeso_kilo() {
		return peso_kilo;
	}

	public void setPeso_kilo(int peso_kilo) {
		this.peso_kilo = peso_kilo;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getFrec_card_max() {
		return frec_card_max;
	}

	public void setFrec_card_max(int frec_card_max) {
		this.frec_card_max = frec_card_max;
	}

	public int getFrec_card_rep() {
		return frec_card_rep;
	}

	public void setFrec_card_rep(int frec_card_rep) {
		this.frec_card_rep = frec_card_rep;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [nombre=" + nombre + ", email=" + email + ", fecha_nac=" + fecha_nac + ", peso_kilo="
				+ peso_kilo + ", altura=" + altura + ", frec_card_max=" + frec_card_max + ", frec_card_rep="
				+ frec_card_rep + "]";
	}
	
	
}
