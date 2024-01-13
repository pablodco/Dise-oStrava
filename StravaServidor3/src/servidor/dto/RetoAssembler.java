package servidor.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import servidor.dominio.Actividad;
import servidor.dominio.Reto;
import servidor.dominio.Reto;

public class RetoAssembler {
	private static RetoAssembler instance;
	private static DateFormat dt= new SimpleDateFormat("dd/MM/yyyy");
	public static RetoAssembler getInstance() {
		if (instance == null) {
			instance = new RetoAssembler();
		}
		return instance;
	}
	private RetoAssembler() {
		super();
	}
	
	public RetoDTO retoToDTO(Reto reto) {
		RetoDTO dto = new RetoDTO();
		dto.setDescripcion(reto.getDescripcion());
		dto.setNombre(reto.getNombre());
		dto.setObjetivo(reto.getObjetivo());
		dto.setFecha_ini((reto.getFecha_ini()));
		dto.setFecha_fin((reto.getFecha_fin()));
		String actividades="";
		for(Actividad a: reto.getActividades()) {
			if(actividades.equals("")) {
				actividades= a.toString();
			}else {
				actividades= actividades+","+a.toString();
			}
		}
		dto.setActividades(actividades);
		dto.setTipoObjectivo(reto.getTipoObjectivo().toString());
		return dto;
	}
	
	
	public List<RetoDTO> retoToDTO(List<Reto> Retos) {
		List<RetoDTO> listaRetoDTOs= new ArrayList<RetoDTO>();
		for (Reto entre: Retos) {
			listaRetoDTOs.add(retoToDTO(entre));
		}
		return listaRetoDTOs;
	}
}
