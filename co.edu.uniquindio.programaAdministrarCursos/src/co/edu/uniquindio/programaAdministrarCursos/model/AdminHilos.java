package co.edu.uniquindio.programaAdministrarCursos.model;

import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.controller.AdminController;
import co.edu.uniquindio.programaAdministrarCursos.hilos.Log;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminHilos implements Runnable {
	Main main;
	AdminController adminController;
	Credito     creditoAux;






	Log hiloLog;


	Thread obtenerDirectorioRaiz;



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
		if( obtenerDirectorioRaiz==hiloEjecucion)
		{
			main.setDirectorioRaiz();
			main.crearDirectorios();
		}

	}
	public void startHiloLogger(String mensaje, Level tipo){
		hiloLog=new Log(mensaje, tipo);
		hiloLog.setRuta(main.getRutaLog());
		hiloLog.start();

	}
//	public void startHiloMostrarCredito(Credito creditoSeleccionado){
//		creditoAux=creditoSeleccionado;
//		mostrarCredito= new Thread(this);
//		mostrarCredito.start();
//
//	}
	public void startHiloObtenerRutaPersistencia() {
			obtenerDirectorioRaiz= new Thread(this);
			obtenerDirectorioRaiz.start();
	}
	public Log getHiloLog() {
		return hiloLog;
	}
	public void setHiloLog(Log hiloLog) {
		this.hiloLog = hiloLog;
	}
	public Thread getObtenerDirectorioRaiz() {
		return obtenerDirectorioRaiz;
	}
	public void setObtenerDirectorioRaiz(Thread obtenerDirectorioRaiz) {
		this.obtenerDirectorioRaiz = obtenerDirectorioRaiz;
	}



}
