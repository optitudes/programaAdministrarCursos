package co.edu.uniquindio.programaAdministrarCursos.model;

public class Academico extends Credito{

	private double  notaMinima;
	private static final long serialVersionUID = 1L;
	private EArea   area;
	
	
	public Academico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Academico(double costo, int cuposDisponibles, Horario horario,
					 Lugar lugar,double notaMinima,EArea area, String tipo,String nombre) {
		super(costo, cuposDisponibles, 0, horario, lugar,tipo, nombre);
		this.notaMinima=notaMinima;
		this.area=area;
	}
	
	public EArea getArea() {
		return area;
	}
	public void setArea(EArea area) {
		this.area = area;
	}
	public double getNotaMinima() {
		return notaMinima;
	}
	public void setNotaMinima(double notaMinima) {
		this.notaMinima = notaMinima;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Academico [notaMinima=" + notaMinima + ", area=" + area + ", getCosto()=" + getCosto() + ", getLugar()="
				+ getLugar() + ", getHorario()=" + getHorario() + ", getCuposDisponibles()=" + getCuposDisponibles()
				+ ", getCuposRegistrados()=" + getCuposRegistrados() + "]";
	}
}
