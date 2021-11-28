package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Estudiante extends User implements Serializable{

	ArrayList<Credito> listaCreditosRegistrado= new ArrayList<Credito>();
	private static final long serialVersionUID = 1L;

	public Estudiante() {
		super();
	}

	public Estudiante(String name, String iD, String email, String pasword) {
		super(name, iD, email, pasword);
	}

	public Estudiante(String name, String iD, String email, String pasword,ArrayList<Credito> listaAux) {
		super(name, iD, email, pasword);
		this.listaCreditosRegistrado=listaAux;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	@Override
	public String toString() {
		return "Estudiante [listaCreditosRegistrado=" + listaCreditosRegistrado + ", getName()=" + getName()
				+ ", getiD()=" + getiD() + ", getEmail()=" + getEmail() + ", getPasword()=" + getPasword()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	public boolean verificarID(String iD) {
		
		if(getiD().equalsIgnoreCase(iD))
			return true;
		return false;
	}

	public boolean verificarCorreo(String correo) {

		if(getEmail().equalsIgnoreCase(correo))
			return true;
		
		return false;
	}

	public boolean actualizar(Estudiante estudianteAux) {
		
		setName   (estudianteAux.getName());
		setiD     (estudianteAux.getiD());
		setEmail  (estudianteAux.getEmail());
		setPasword(estudianteAux.getPasword());
		
		return true;
	}

	@Override
	public String getDatosTXT() {
		String listaCreditosRegistrados="";
		for (Credito credito : listaCreditosRegistrado) {
			listaCreditosRegistrados+=credito.getNombre()+";";
			
		}
		return getName()+";"+getPasword()+";"+getiD()+";"+getEmail()+";"+listaCreditosRegistrados;
	}

	public boolean validarLoggin(String correo, String clave) {
		if(correo.equals(getEmail()) && clave.equals(getPasword()))
			return true;
		return false;
	}

	

	
	
}
