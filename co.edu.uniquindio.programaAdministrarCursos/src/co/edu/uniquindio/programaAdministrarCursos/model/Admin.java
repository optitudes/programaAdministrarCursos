package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;

public class Admin extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	public Admin(String name, String iD, String email, String pasword) {
		super(name, iD, email, pasword);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Admin [getName()=" + getName() + ", getiD()=" + getiD() + ", getEmail()=" + getEmail()
				+ ", getPasword()=" + getPasword() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

	@Override
	public String getDatosTXT() {
		return getName()+";"+getPasword()+";"+getiD()+";"+getEmail()+";";
	}
	
	

}
