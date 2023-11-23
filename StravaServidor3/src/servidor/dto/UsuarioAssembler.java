package servidor.dto;

import java.util.ArrayList;
import java.util.List;

import servidor.dominio.Usuario;
import servidor.dominio.Usuario;

public class UsuarioAssembler  {
	private static UsuarioAssembler instance;

	private UsuarioAssembler() { }
	
	public static UsuarioAssembler getInstance() {
		if (instance == null) {
			instance = new UsuarioAssembler();
		}

		return instance;
	}

	public UsuarioDTO usuarioToDTO(Usuario user) {
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setEmail(user.getEmail());
		dto.setNombre(user.getNombre());
		dto.setFecha_nac(user.getFecha_nac());
		dto.setAltura(user.getAltura());
		dto.setFrec_card_max(user.getFrec_card_max());
		dto.setFrec_card_rep(user.getFrec_card_rep());
		dto.setPeso_kilo(user.getPeso_kilo());
		
		return dto;
	}
	
	public List<UsuarioDTO> usuarioToDTO(List<Usuario> Usuarios) {
		List<UsuarioDTO> listaUsuarioDTOs= new ArrayList<UsuarioDTO>();
		for (Usuario entre: Usuarios) {
			listaUsuarioDTOs.add(usuarioToDTO(entre));
		}
		return listaUsuarioDTOs;
	}
}
