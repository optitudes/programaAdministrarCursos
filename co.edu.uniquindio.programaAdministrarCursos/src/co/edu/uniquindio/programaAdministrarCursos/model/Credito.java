package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;

public abstract class Credito implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private int cuposDisponibles;
	private int cuposRegistrados;
	private double costo;
	private Horario horario;
	private Lugar   lugar;
	public Credito() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Credito(double costo,int cuposDisponibles, int cuposRegistrados,Horario horario,Lugar lugar) {
		super();
		this.costo= costo;
		this.cuposDisponibles = cuposDisponibles;
		this.cuposRegistrados = cuposRegistrados;
		this.horario= horario;
		this.lugar= lugar;
	}
	
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public Lugar getLugar() {
		return lugar;
	}
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	public int getCuposDisponibles() {
		return cuposDisponibles;
	}
	public void setCuposDisponibles(int cuposDisponibles) {
		this.cuposDisponibles = cuposDisponibles;
	}
	public int getCuposRegistrados() {
		return cuposRegistrados;
	}
	public void setCuposRegistrados(int cuposRegistrados) {
		this.cuposRegistrados = cuposRegistrados;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Credito [cuposDisponibles=" + cuposDisponibles + ", cuposRegistrados=" + cuposRegistrados + ", costo="
				+ costo + ", horario=" + horario + ", lugar=" + lugar + "]";
	}
	
	
	
	

}
