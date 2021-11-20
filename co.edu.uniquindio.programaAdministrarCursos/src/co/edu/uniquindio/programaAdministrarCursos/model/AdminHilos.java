package co.edu.uniquindio.programaAdministrarCursos.model;

import java.util.logging.Level;

public class AdminHilos implements Runnable {
	Log hiloLog;
	
	
	boolean runHilo=false;
	int valorEntrada;
	int contador=1;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public void startHiloLogger(String mensaje, Level tipo){
		hiloLog=new Log(mensaje, tipo);
	}
	

}
