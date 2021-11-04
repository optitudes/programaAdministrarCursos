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
		return "Estudiante []";
	}

	
	
}
