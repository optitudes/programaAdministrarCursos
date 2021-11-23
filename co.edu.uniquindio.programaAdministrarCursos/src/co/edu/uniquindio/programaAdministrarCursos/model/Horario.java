package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Horario implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<EDia> listaDias=new ArrayList<EDia>();
	ArrayList<EHorario> listaHorarios= new ArrayList<EHorario>();
	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Horario(ArrayList<EDia> listaDias, ArrayList<EHorario> listaHorarios) {
		super();
		this.listaDias = listaDias;
		this.listaHorarios = listaHorarios;
	}
	public ArrayList<EDia> getListaDias() {
		return listaDias;
	}
	public void setListaDias(ArrayList<EDia> listaDias) {
		this.listaDias = listaDias;
	}
	public ArrayList<EHorario> getListaHorarios() {
		return listaHorarios;
	}
	public void setListaHorarios(ArrayList<EHorario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Horario [listaDias=" + listaDias + ", listaHorarios=" + listaHorarios + "]";
	}

}
