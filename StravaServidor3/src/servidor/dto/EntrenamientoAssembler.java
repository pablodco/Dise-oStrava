package servidor.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import servidor.dominio.Entrenamiento;

public class EntrenamientoAssembler {
	private static EntrenamientoAssembler instance;
	
	public static EntrenamientoAssembler getInstance() {
		if (instance == null) {
			instance = new EntrenamientoAssembler();
		}
		return instance;
	}
	
	public EntrenamientoDTO entreToDTO(Entrenamiento entre) {
		EntrenamientoDTO dto = new EntrenamientoDTO();
		dto.setDistancia(entre.getdistancia());
		dto.setDuracion(entre.getDuracion());
		DateFormat dt= new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dto.setFecha_ini(entre.getFecha_ini());
		System.out.println(dto.getFecha_ini());
		dto.setTitulo(entre.getTitulo());
		dto.setActividad(entre.getActividad().toString());
		return dto;
	}
	
	public List<EntrenamientoDTO> entreToDTO(List<Entrenamiento> entrenamientos) {
		List<EntrenamientoDTO> listaEntrenamientoDTOs= new ArrayList<EntrenamientoDTO>();
		for (Entrenamiento entre: entrenamientos) {
			listaEntrenamientoDTOs.add(entreToDTO(entre));
		}
		return listaEntrenamientoDTOs;
	}
}
