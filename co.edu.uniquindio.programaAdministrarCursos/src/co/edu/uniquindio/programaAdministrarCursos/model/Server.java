package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JOptionPane;


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
				+ "El programa ha detectado backups, elija uno si desea cargar un "
				+ "backup.\n");
		buscarBackups();

		while(true){
			System.out.println("Esperando usuario...");

			try {
				socket = servidor.accept();
				flujoEntradaObjeto = new ObjectInputStream(socket.getInputStream());//10101
				paqueteDatos =  (PaqueteDatos) flujoEntradaObjeto.readObject();

				if(paqueteDatos.getAccion()==AccionEnum.LOGGEAR_ESTUDIANTE)
				{
					ArrayList<String> contenido = (ArrayList<String>) paqueteDatos.getContenido();
					datosLoggin=contenido;
					if(datosLoggin!=null){

						for (String string : datosLoggin) {
							System.out.println(string);

						}
					}
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
}

