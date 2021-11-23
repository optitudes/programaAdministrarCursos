package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Academico extends Credito implements Serializable{

	private double  notaMinima;
	private ArrayList<Estudiante> listaEstudiantes=new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private EArea   area;


	public Academico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Academico(double costo, int cuposDisponibles, Horario horario,
					 Lugar lugar,double notaMinima,EArea area, String tipo,String nombre) {
		super(costo, cuposDisponibles, 0, horario, lugar,tipo, nombre);
		this.notaMinima=notaMinima;
		this.area=area;
	}

	public Academico(Double costo, int cuposDisponibles, Horario horarioAux, Lugar lugarAux, Double notaMin,
			EArea areaAux, String tipo, String nombre, int cuposRegistrados) {
		super(costo, cuposDisponibles, cuposRegistrados, horarioAux, lugarAux,tipo, nombre);
		this.notaMinima=notaMin;
		this.area=areaAux;
	}
	public EArea getArea() {
		return area;
	}
	public void setArea(EArea area) {
		this.area = area;
	}
	public double getNotaMinima() {
		return notaMinima;
	}
	public void setNotaMinima(double notaMinima) {
		this.notaMinima = notaMinima;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}
	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}
	@Override
	public String toString() {
		return "Academico [notaMinima=" + notaMinima + ", area=" + area + ", getCosto()=" + getCosto() + ", getLugar()="
				+ getLugar() + ", getHorario()=" + getHorario() + ", getCuposDisponibles()=" + getCuposDisponibles()
				+ ", getCuposRegistrados()=" + getCuposRegistrados() + "]";
	}
	@Override
	public boolean actualizar(Credito creditoAux) {
		Academico academicoAux= (Academico) creditoAux;

		setNombre(academicoAux.getNombre());
		setArea(academicoAux.getArea());
		setCosto(academicoAux.getCosto());
		setCuposDisponibles(academicoAux.getCuposDisponibles());
		setHorario(academicoAux.getHorario());
		setLugar(academicoAux.getLugar());
		setNotaMinima(academicoAux.getNotaMinima());
		return true;
	}

	@Override
	public String getDatosTXT() {
		return getNombre()+";"+getCosto()+";"+getCuposDisponibles()+";"+getCuposRegistrados()+";"+getHorario().getListaDias()+";"+
				getHorario().getListaHorarios()+";"+getLugar().getBloque()+";"+getLugar().getPiso()+";"+getLugar().getNumSalon()+";"
				+getArea()+";"+getNotaMinima()+";";
	}
}
