package co.edu.uniquindio.programaAdministrarCursos.model;

public class Deportivo extends Credito{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EAsistenciaMinima asistenciaMinima;

	

	public Deportivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deportivo(double costo, int cuposDisponibles, int cuposRegistrados, Horario horario, Lugar lugar,EAsistenciaMinima asistenciaMinima) {
		super(costo, cuposDisponibles, cuposRegistrados, horario, lugar);
		this.asistenciaMinima=asistenciaMinima;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EAsistenciaMinima getAsistenciaMinima() {
		return asistenciaMinima;
	}

	public void setAsistenciaMinima(EAsistenciaMinima asistenciaMinima) {
		this.asistenciaMinima = asistenciaMinima;
	}

	@Override
	public String toString() {
		return "Deportivo [asistenciaMinima=" + asistenciaMinima + ", getCosto()=" + getCosto() + ", getLugar()="
				+ getLugar() + ", getHorario()=" + getHorario() + ", getCuposDisponibles()=" + getCuposDisponibles()
				+ ", getCuposRegistrados()=" + getCuposRegistrados() + "]";
	}
	
}
