package co.edu.uniquindio.programaAdministrarCursos.model;

import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.controller.AdminController;
import co.edu.uniquindio.programaAdministrarCursos.hilos.Log;

public class AdminHilos implements Runnable {
	Main main;
	AdminController adminController;
	Credito     creditoAux;






	Log hiloLog;


	//Thread mostrarCredito;



	boolean runHilo=false;
	int valorEntrada;
	int contador=1;
	public AdminHilos(Main main, AdminController adminController) {
		this.main=main;
		this.adminController=adminController;
	}
	public AdminHilos(Main main) {
		this.main=main;
	}
	@Override
	public void run() {
		Thread hiloEjecucion= Thread.currentThread();
//		if(mostrarCredito==hiloEjecucion)
//		{
//			adminController.mostrarInformacionCredito(creditoAux);
//		}

	}
	public void startHiloLogger(String mensaje, Level tipo){
		hiloLog=new Log(mensaje, tipo);
	}
//	public void startHiloMostrarCredito(Credito creditoSeleccionado){
//		creditoAux=creditoSeleccionado;
//		mostrarCredito= new Thread(this);
//		mostrarCredito.start();
//
//	}



}
