package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;

public class Cultural extends Credito  implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Cultural() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cultural(double costo, int cuposDisponibles, Horario horario, Lugar lugar,  String tipo
					,String nombre) {
		super(costo, cuposDisponibles, 0, horario, lugar,  tipo,  nombre);
		// TODO Auto-generated constructor stub
	}

	public Cultural(Double costo, int cuposDisponibles, Horario horarioAux, Lugar lugarAux, String tipo, String nombre,
			int cuposRegistrados) {
		super(costo, cuposDisponibles, cuposRegistrados, horarioAux, lugarAux,  tipo,  nombre);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cultural [getCosto()=" + getCosto() + ", getLugar()=" + getLugar() + ", getHorario()=" + getHorario()
				+ ", getCuposDisponibles()=" + getCuposDisponibles() + ", getCuposRegistrados()="
				+ getCuposRegistrados() + "]";
	}

	@Override
	public boolean actualizar(Credito creditoAux) {
		Cultural culturalAux= (Cultural) creditoAux;

		setNombre(culturalAux.getNombre());
		setCosto(culturalAux.getCosto());
		setCuposDisponibles(culturalAux.getCuposDisponibles());
		setHorario(culturalAux.getHorario());
		setLugar(culturalAux.getLugar());
		return true;
	}

	@Override
	public String getDatosTXT() {
		return getNombre()+";"+getCosto()+";"+getCuposDisponibles()+";"+getCuposRegistrados()+";"+getHorario().getListaDias()+";"+
				getHorario().getListaHorarios()+";"+getLugar().getBloque()+";"+getLugar().getPiso()+";"+getLugar().getNumSalon()+";";
	}

}
