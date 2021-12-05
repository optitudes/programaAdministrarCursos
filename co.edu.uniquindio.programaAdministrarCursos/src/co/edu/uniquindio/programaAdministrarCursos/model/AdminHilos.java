package co.edu.uniquindio.programaAdministrarCursos.model;

import java.awt.Component;
import java.io.File;
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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	boolean sesion;
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
	Thread hiloRefrescarTablas;
	Thread hiloCargarDatosBackup;


	boolean runHilo=false;
	int valorEntrada;
	int contador=1;
	public AdminHilos(ClienteMain main, EstudianteController estudianteController,boolean sesion) {
		this.mainCliente=main;
		this.estudianteController=estudianteController;
		this.sesion=sesion;
	}
	public AdminHilos(ClienteMain main, AdminController adminController,boolean sesion) {
		this.mainCliente=main;
		this.adminController=adminController;
		this.sesion=sesion;
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
	
	
	public boolean isSesion() {
		return sesion;
	}
	public void setSesion(boolean sesion) {
		this.sesion = sesion;
	}
	@Override
	public void run() {
		Thread hiloEjecucion= Thread.currentThread();

		if(hiloRefrescarTablas==hiloEjecucion){
			if(sesion==true){
				adminController.refrescarTablas();
			}
		}
		if(hiloCargarDatosBackup==hiloEjecucion){
			PaqueteDatos paqueteDatos;

			String mensaje="";
			int seleccion=JFileChooser.APPROVE_OPTION;
			boolean procesoExitoso=false;
			while(!procesoExitoso ){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Backup", "dat"));
				//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
				seleccion=fileChooser.showOpenDialog(new Component() {
				});
				if(seleccion==JFileChooser.CANCEL_OPTION){
					break;
				}			//Si el usuario, pincha en aceptar
				if(seleccion==JFileChooser.APPROVE_OPTION){

					//Seleccionamos el fichero
					File file=fileChooser.getSelectedFile();
					if(file!=null)
					{
						if(file.isFile())
						{
							ArrayList<String> listaDatos= new ArrayList<String>();
							listaDatos.add(adminController.getAdmin().getEmail());
							listaDatos.add(file.getAbsolutePath());
							paqueteDatos= new PaqueteDatos(AccionEnum.CARGAR_BACKUP,listaDatos);
							startHiloEnviarPaqueteServer(paqueteDatos);
							procesoExitoso=true;
						}
					}
				}

			}
		}

	}
	private void validarEstudiante() throws UnknownHostException, IOException {

		socket= new Socket("localhost", 8081);
		flujoSalidaObjeto= new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(datosLoggin);
		flujoSalidaObjeto.close();
	}
	public void startHiloLogger(String mensaje, Level tipo){
		hiloLog=new HiloLog(mensaje, tipo);
		hiloLog.setRuta(mainServer.getRutaLog());
		hiloLog.start();

	}

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
	public void starHiloRefrescarTablas() {
		hiloRefrescarTablas=new Thread(this);
		hiloRefrescarTablas.start();
		
		
	}
	public void starHiloCargarDatosBackup() {
		hiloCargarDatosBackup=new Thread(this);
		hiloCargarDatosBackup.start();
		
	}
}
