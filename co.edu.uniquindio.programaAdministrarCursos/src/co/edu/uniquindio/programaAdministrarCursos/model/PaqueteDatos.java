package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PaqueteDatos  implements Serializable{
	AccionEnum accion;
	Object    contenido;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaqueteDatos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaqueteDatos(AccionEnum accion, Object contenido) {
		super();
		this.accion = accion;
		this.contenido = contenido;
	}

	public AccionEnum getAccion() {
		return accion;
	}

	public void setAccion(AccionEnum accion) {
		this.accion = accion;
	}

	public Object getContenido() {
		return contenido;
	}

	public void setContenido(Object contenido) {
		this.contenido = contenido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaqueteDatos [accion=" + accion + ", contenido=" + contenido + "]";
	}
	
	

}
