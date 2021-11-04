package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;
import java.util.Arrays;

public class Horario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EDia[] listaDias=new EDia[2];
	EHorario[] listaHorario= new EHorario[2];
	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Horario(EDia[] listaDias, EHorario[] listaHorario) {
		super();
		this.listaDias = listaDias;
		this.listaHorario = listaHorario;
	}
	public EDia[] getListaDias() {
		return listaDias;
	}
	public void setListaDias(EDia[] listaDias) {
		this.listaDias = listaDias;
	}
	public EHorario[] getListaHorario() {
		return listaHorario;
	}
	public void setListaHorario(EHorario[] listaHorario) {
		this.listaHorario = listaHorario;
	}
	@Override
	public String toString() {
		return "Horario [listaDias=" + Arrays.toString(listaDias) + ", listaHorario=" + Arrays.toString(listaHorario)
				+ "]";
	}
	
	

}
