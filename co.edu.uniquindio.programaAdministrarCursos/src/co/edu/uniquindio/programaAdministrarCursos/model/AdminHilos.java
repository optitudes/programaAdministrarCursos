package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.IOException;
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

	String archivoTXT;





	Log hiloLog;


	Thread obtenerDirectorioRaiz;
	Thread hiloGuardarTXT;


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
		if(hiloGuardarTXT==hiloEjecucion)
		{
			try {
				if(archivoTXT.equals("estudiante.txt"))
				{
					main.guardarDatosTXT("estudiante.txt");
					startHiloLogger("estudiantes guardados en txt por el admin "+adminController.getAdmin().getName(),Level.INFO);

				}else{
					if(archivoTXT.equals("instructor.txt"))
					{
						main.guardarInstructoresTXT();

					}else{
						if(archivoTXT.equals("credito.txt"))
						{
							//						main.guardarCreditosTXT();
						}
					}
				}		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	public void startHiloGuardarDatosEstudiantes() {
		// TODO Auto-generated method stub
		
	}
	public void startHiloGuardarDatosObtejos(String archivoTXT) {
		hiloGuardarTXT= new Thread(this);
		this.archivoTXT=archivoTXT;
		hiloGuardarTXT.start();
		
		
	}



}
