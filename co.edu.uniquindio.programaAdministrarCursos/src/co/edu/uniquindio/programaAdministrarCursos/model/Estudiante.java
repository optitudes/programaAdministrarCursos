package co.edu.uniquindio.programaAdministrarCursos.model;

public class Estudiante extends User{

	private static final long serialVersionUID = 1L;

	public Estudiante() {
		super();
	}

	public Estudiante(String name, String iD, String email, String pasword) {
		super(name, iD, email, pasword);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Estudiante [getName()=" + getName() + ", getiD()=" + getiD() + ", getEmail()=" + getEmail()
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

	public boolean actualizar(Estudiante estudianteAux) {
		
		setName   (estudianteAux.getName());
		setiD     (estudianteAux.getiD());
		setEmail  (estudianteAux.getEmail());
		setPasword(estudianteAux.getPasword());
		
		return true;
	}

	@Override
	public String getDatosTXT() {
		return getName()+";"+getPasword()+";"+getiD()+";"+getEmail()+";";
	}

	

	
	
}
