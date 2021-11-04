package co.edu.uniquindio.programaAdministrarCursos.model;

public class Cultural extends Credito {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cultural() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cultural(double costo, int cuposDisponibles, int cuposRegistrados, Horario horario, Lugar lugar) {
		super(costo, cuposDisponibles, cuposRegistrados, horario, lugar);
		// TODO Auto-generated constructor stub
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

}
