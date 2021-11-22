package co.edu.uniquindio.programaAdministrarCursos.model;

public class Instructor extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Instructor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Instructor(String name, String iD, String email, String pasword) {
		super(name, iD, email, pasword);
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Instructor [getName()=" + getName() + ", getiD()=" + getiD() + ", getEmail()=" + getEmail()
				+ ", getPasword()=" + getPasword() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
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

	public boolean actualizar(Instructor instructorAux) {
		setName   (instructorAux.getName());
		setiD     (instructorAux.getiD());
		setEmail  (instructorAux.getEmail());
		setPasword(instructorAux.getPasword());
		
		return true;
	}

	@Override
	public String getDatosTXT() {
		return getName()+";"+getPasword()+";"+getiD()+";"+getEmail()+";";
	}

}
