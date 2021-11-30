package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {
	String host = "localhost";
	int puerto = 8081;
	ServerSocket server;
	
	Socket socketComunicacion;
	
	ObjectOutputStream flujoSalidadObjeto;
	
	String mensajeCliente;

	public AppServer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
