package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.ObjectInputStream;
import java.net.Socket;

public class AppCliente {
	String host;
	int puerto;
	Socket socketComunicacion;
	
	ObjectInputStream flujoEntradaObjeto;

	public AppCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppCliente(String host, int puerto) {
		super();
		this.host = host;
		this.puerto = puerto;
	}

	
}
