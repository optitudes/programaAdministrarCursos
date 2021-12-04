package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import co.edu.uniquindio.programaAdministrarCursos.ClienteMain;
import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.ServerMain;
import co.edu.uniquindio.programaAdministrarCursos.controller.AdminController;
import co.edu.uniquindio.programaAdministrarCursos.controller.EstudianteController;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloEnviarPaqueteServer;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminHilos implements Runnable {
	ClienteMain mainCliente;
	Server  mainServer;
	Socket  socket;

	AdminController adminController;
	EstudianteController estudianteController;

	Credito     creditoAux;

	String archivoTXTGuardar;
	String archivoTXTCargar;
	String fecha;
	String nombreArchivoBackup="backup";

	ArrayList<String> datosLoggin=null;
	ObjectOutputStream flujoSalidaObjeto;


	HiloLog hiloLog;
	HiloEnviarPaqueteServer hiloEnviarPaqueteServer;

	Thread hiloGuardarTXT;
	Thread hiloCargarTXT;


	boolean runHilo=false;
	int valorEntrada;
	int contador=1;
	public AdminHilos(ClienteMain main, EstudianteController estudianteController) {
		this.mainCliente=main;
		this.estudianteController=estudianteController;
	}
	public AdminHilos(ClienteMain main, AdminController adminController) {
		this.mainCliente=main;
		this.adminController=adminController;
	}
	public AdminHilos(ClienteMain main2) {
		this.mainCliente=main2;
	}


	public AdminHilos() {
		super();
		}

	public AdminHilos(Server server) {
		this.mainServer=server;
	}
	@Override
	public void run() {
		Thread hiloEjecucion= Thread.currentThread();


//		if(hiloGuardarTXT==hiloEjecucion)
//		{
//			try {
////				main.guardarDatosTXT(archivoTXTGuardar);
//				startHiloLogger("Datos guardados en txt en el registro ["+archivoTXTGuardar+"] por el admin "+adminController.getAdmin().getName(),Level.INFO);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				startHiloLogger("Error al guardar datos ["+archivoTXTGuardar+"] "+adminController.getAdmin().getName(), Level.SEVERE);
//			}
//		}
//		if(hiloCargarTXT==hiloEjecucion)
//		{
//			try {
////				main.CargarDatosTXT(archivoTXTCargar);
//				adminController.refrescarTablas();
//				startHiloLogger("Datos Cargados del txt  del registro ["+archivoTXTCargar+"] por el admin "+adminController.getAdmin().getName(),Level.INFO);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				startHiloLogger("Error al cargar datos ["+archivoTXTGuardar+"] "+adminController.getAdmin().getName(), Level.SEVERE);
//			}
//		}

	}
	private void validarEstudiante() throws UnknownHostException, IOException {

		socket= new Socket("localhost", 8081);
		flujoSalidaObjeto= new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(datosLoggin);
		flujoSalidaObjeto.close();
		JOptionPane.showInputDialog("objeto enviado");


	}
	public void startHiloLogger(String mensaje, Level tipo){
		hiloLog=new HiloLog(mensaje, tipo);
		hiloLog.setRuta(mainServer.getRutaLog());
		hiloLog.start();

	}
//	public void startHiloMostrarCredito(Credito creditoSeleccionado){
//		creditoAux=creditoSeleccionado;
//		mostrarCredito= new Thread(this);
//		mostrarCredito.start();
//
//	}
	public HiloLog getHiloLog() {
		return hiloLog;
	}
	public void setHiloLog(HiloLog hiloLog) {
		this.hiloLog = hiloLog;
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

	public void startHiloEnviarPaqueteServer(PaqueteDatos paqueteAux) {
		hiloEnviarPaqueteServer= new HiloEnviarPaqueteServer(paqueteAux,"localhost",8081);

	}
}
