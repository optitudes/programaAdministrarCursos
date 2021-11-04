package co.edu.uniquindio.programaAdministrarCursos.model;

public class Lugar {
	
	private String bloque;
	private int piso;
	private int numSalon;
	public Lugar() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lugar(String bloque, int piso, int numSalon) {
		super();
		this.bloque = bloque;
		this.piso = piso;
		this.numSalon = numSalon;
	}
	public String getBloque() {
		return bloque;
	}
	public void setBloque(String bloque) {
		this.bloque = bloque;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public int getNumSalon() {
		return numSalon;
	}
	public void setNumSalon(int numSalon) {
		this.numSalon = numSalon;
	}
	@Override
	public String toString() {
		return "Lugar [bloque=" + bloque + ", piso=" + piso + ", numSalon=" + numSalon + "]";
	}
	
	

}
