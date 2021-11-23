package co.edu.uniquindio.programaAdministrarCursos.hilos;

public class HiloBackup implements Runnable{
	String fecha="";
	
	Thread hiloBackup;

	
	
	
	public HiloBackup(String fecha, Thread hiloBackup) {
		super();
		this.fecha = fecha;
		this.hiloBackup = hiloBackup;
	}


	public String getFecha() {
		return fecha;
	}




	public void setFecha(String fecha) {
		this.fecha = fecha;
	}




	public Thread getHiloBackup() {
		return hiloBackup;
	}




	public void setHiloBackup(Thread hiloBackup) {
		this.hiloBackup = hiloBackup;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
