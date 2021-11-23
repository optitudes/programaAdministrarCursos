package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.controller.AdminController;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminHilos implements Runnable {
	Main main;
	AdminController adminController;
	Credito     creditoAux;

	String archivoTXTGuardar;
	String archivoTXTCargar;
	String fecha;
	String nombreArchivoBackup="backup";





	HiloLog hiloLog;


	Thread obtenerDirectorioRaiz;
	Thread hiloGuardarTXT;
	Thread hiloCargarTXT;


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
				main.guardarDatosTXT(archivoTXTGuardar);
				startHiloLogger("Datos guardados en txt en el registro ["+archivoTXTGuardar+"] por el admin "+adminController.getAdmin().getName(),Level.INFO);

			} catch (IOException e) {
				e.printStackTrace();
				startHiloLogger("Error al guardar datos ["+archivoTXTGuardar+"] "+adminController.getAdmin().getName(), Level.SEVERE);
			}
		}
		if(hiloCargarTXT==hiloEjecucion)
		{
			try {
				main.CargarDatosTXT(archivoTXTCargar);
				adminController.refrescarTablas();
				startHiloLogger("Datos Cargados del txt  del registro ["+archivoTXTCargar+"] por el admin "+adminController.getAdmin().getName(),Level.INFO);

			} catch (IOException e) {
				e.printStackTrace();
				startHiloLogger("Error al cargar datos ["+archivoTXTGuardar+"] "+adminController.getAdmin().getName(), Level.SEVERE);
			}
		}

	}
	public void startHiloLogger(String mensaje, Level tipo){
		hiloLog=new HiloLog(mensaje, tipo);
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
	public HiloLog getHiloLog() {
		return hiloLog;
	}
	public void setHiloLog(HiloLog hiloLog) {
		this.hiloLog = hiloLog;
	}
	public Thread getObtenerDirectorioRaiz() {
		return obtenerDirectorioRaiz;
	}
	public void setObtenerDirectorioRaiz(Thread obtenerDirectorioRaiz) {
		this.obtenerDirectorioRaiz = obtenerDirectorioRaiz;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void startHiloGuardarDatosObtejos(String archivoTXT) {
		hiloGuardarTXT= new Thread(this);
		this.archivoTXTGuardar=archivoTXT;
		hiloGuardarTXT.start();
	}
	public void startHiloCargarDatosObtejos(String nombreArchivo) {
		hiloCargarTXT= new Thread(this);
		this.archivoTXTCargar=nombreArchivo;
		hiloCargarTXT.start();
	}
	public void startHiloBackup() {
		fecha=""+LocalDate.now(Clock.systemDefaultZone ())+LocalTime.now();
		fecha=fecha.replaceAll("-","_");
		fecha=fecha.replaceAll(":","_");
		fecha=fecha.replace(".","_");
		
		
		try {
			main.serializarBienestar(nombreArchivoBackup+fecha+".dat");
		} catch (IOException e) {
			adminController.mostrarMensaje("Error","No se pudo hacer el backup",
										  "El programa no pudo crear el backup"
										  + " póngase en contacto con el proveedor"
										  + " del servicio", AlertType.ERROR);
			adminController.registrarAccion("Error al hacer backup admin ["+adminController.getAdmin().getName()+"]",Level.SEVERE);
		}
	}



}
