package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;

import javafx.scene.DepthTest;

public class Deportivo extends Credito implements Serializable{

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

	public Deportivo(Double costo, int cuposDisponibles, Horario horarioAux, Lugar lugarAux,
			EAsistenciaMinima asistenciaMin, String tipo, String nombre, int cuposRegistrados) {
		super(costo, cuposDisponibles, cuposRegistrados, horarioAux, lugarAux,  tipo, nombre);
		this.asistenciaMinima=asistenciaMin;
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

	@Override
	public String getDatosTXT() {

		return getNombre()+";"+getCosto()+";"+getCuposDisponibles()+";"+getCuposRegistrados()+";"+getHorario().getListaDias()+";"+
				getHorario().getListaHorarios()+";"+getLugar().getBloque()+";"+getLugar().getPiso()+";"+getLugar().getNumSalon()+";"+getAsistenciaMinima()+";";
	}

}
