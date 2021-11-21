package co.edu.uniquindio.programaAdministrarCursos.model;

import javafx.scene.DepthTest;

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

	public Deportivo(double costo, int cuposDisponibles, Horario horario, Lugar lugar,EAsistenciaMinima asistenciaMinima,  String tipo,
					String nombre) {
		super(costo, cuposDisponibles, 0, horario, lugar,  tipo, nombre);
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

	@Override
	public boolean actualizar(Credito creditoAux) {
		Deportivo deportivoAux= (Deportivo) creditoAux;

		setNombre(deportivoAux.getNombre());
		setCosto(deportivoAux.getCosto());
		setCuposDisponibles(deportivoAux.getCuposDisponibles());
		setHorario(deportivoAux.getHorario());
		setLugar(deportivoAux.getLugar());
		setAsistenciaMinima(deportivoAux.getAsistenciaMinima());
		return true;
	}

}
