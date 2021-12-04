package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert.AlertType;


public class Server{
	ServerSocket servidor;
	Socket  socket;
	PaqueteDatos paqueteDatos;

	ObjectInputStream flujoEntradaObjeto;
	ArrayList<String> datosLoggin;

	int puerto;
	Bienestar bienestar;
	AdminHilos adminHilos;


	/**
	 * constructor de la clase server
	 * @param host
	 * @param puerto
	 */
	public Server(int puerto) {
		this.puerto=puerto;

		try {
			this.servidor= new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
			setearPuerto();
		}

		this.bienestar= new Bienestar("Cooperativa", "1022");
		this.adminHilos= new AdminHilos(this);
	}
	private void setearPuerto() {
		try {
			int puerto=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto del servidor..."));
			this.servidor= new ServerSocket(puerto);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			setearPuerto();
		}
	}
	/**
	 * método que inicia el servidor
	 */
	public void arrancarServer(){
		if(!bienestar.validarRutaRaiz())
		{
			System.err.println("Atención!(Mensaje servidor)"+"\nEl programa no cuenta con un directorio raiz"+
					"\nPor favor ingrese una carpeta para que el programa pueda "
					+"\ndesplegar sus ficheros. En caso de windows se recomienda"
					+"\nla dirección (C:/td/persistencia/) en caso de linux (home/td/persistencia) \n");
			bienestar.obtenerRutaRaiz();
		}
		clear();
		System.out.println("(Este mensaje solo se mostrará una vez)\n"
				+ "Menú backups\n elija un backup  si desea cargarlo.\n");
		buscarBackups();

		while(true){
			System.out.println("Esperando usuario...");
			AccionEnum accionServer;

			try {
				socket = servidor.accept();
				flujoEntradaObjeto = new ObjectInputStream(socket.getInputStream());//10101
				paqueteDatos =  (PaqueteDatos) flujoEntradaObjeto.readObject();
				accionServer=paqueteDatos.getAccion();
				switch(accionServer){
				case LOGGEAR_ESTUDIANTE:logearEstudiante();
									    break;
				case REGISTRAR_ACCION:  registrarAccion();
										break;
				case HACER_BACKUP:      hacerBackup();
										break;
				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}finally{

				try {
					flujoEntradaObjeto.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}


			}
		}
	}

	private void hacerBackup() {
		String nombre=(String) paqueteDatos.getContenido();
		crearBackup(nombre);

	}
	private void registrarAccion() {
		try {
			ArrayList<Object> contenido= new ArrayList<>();
			contenido= (ArrayList<Object>) paqueteDatos.getContenido();
			String mensaje;
			Level  tipo;

			mensaje=(String) contenido.get(0);
			tipo=(Level) contenido.get(1);

			adminHilos.startHiloLogger(mensaje, tipo);

		} catch (ClassCastException e) {
			adminHilos.startHiloLogger("Error al registrar accion [ClassCastException]",Level.SEVERE);

		}


	}
	private void logearEstudiante() {
		ArrayList<String> contenido = (ArrayList<String>) paqueteDatos.getContenido();
		datosLoggin=contenido;
		if(datosLoggin!=null){

			for (String string : datosLoggin) {
				System.out.println(string);

			}
		}


	}
	private void buscarBackups() {
		int posicion;
		String rutaRespaldo=bienestar.getRutaRespaldo();
		File   carpetaRespaldo= new File(rutaRespaldo);
		String[] listaArchivos;
		listaArchivos=carpetaRespaldo.list();
		posicion=obtenerIndiceRespaldo(listaArchivos);
		if(posicion!=listaArchivos.length)
			System.out.println("-->Backup ["+listaArchivos[posicion]+"]");







	}
	private int obtenerIndiceRespaldo(String[] listaArchivos) {
		int eleccion;
		for (int i = 0; i < listaArchivos.length; i++) {
			System.out.println("["+listaArchivos[i]+"]"+" pos :"+i);
		}
		System.out.println("[ no cargar ningún backup]  pos :"+listaArchivos.length);


		while(true){
			try {
				eleccion=Integer.parseInt(JOptionPane.showInputDialog("elija un backup"));
				if(eleccion>=0 && eleccion<=listaArchivos.length){
					break;
				}else{
					System.err.println("La eleccion no pertenece a ningún archivo...");
				}
			} catch (NumberFormatException e) {
				adminHilos.startHiloLogger("Error al cargar respaldo... [Number"
						+ "FormatException]", Level.SEVERE);
			}
		}

		return eleccion;


	}
	public void clear(){
		System.out.println("\n\n\n\n\n\n\n\n\n");
	}
	public String getRutaLog() {
		return bienestar.getRutaLog();
	}
	public void serializarBienestar(String nombreArchivo) throws IOException {
		Persistencia.serializarObjeto(bienestar.getRutaRespaldo()+"/"+nombreArchivo, bienestar);

	}
	public void crearBackup(String nombre) {
		String fecha;
		fecha=""+LocalDate.now(Clock.systemDefaultZone ())+LocalTime.now();
		fecha=fecha.replaceAll("-","_");
		fecha=fecha.replaceAll(":","_");
		fecha=fecha.replace(".","_");


		try {
			serializarBienestar("Backup_"+fecha+".dat");
		} catch (IOException e) {
			System.out.println("Error\n"+"No se pudo hacer el backup\n"+
										  "El programa no pudo crear el backup "
										  + " póngase en contacto con el proveedor"
										  + " del servicio");
			adminHilos.startHiloLogger("Error al hacer backup admin ["+nombre+"]",Level.SEVERE);
		}
	}
}

