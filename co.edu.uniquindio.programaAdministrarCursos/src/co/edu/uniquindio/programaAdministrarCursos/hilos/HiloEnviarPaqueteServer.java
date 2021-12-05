package co.edu.uniquindio.programaAdministrarCursos.hilos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import co.edu.uniquindio.programaAdministrarCursos.model.PaqueteDatos;

public class HiloEnviarPaqueteServer implements Runnable{
	PaqueteDatos paqueteAux;
	String       host;
	int          puerto;
	public Thread       hilo;

	public HiloEnviarPaqueteServer(PaqueteDatos paqueteAux, String host, int puerto) {
		this.paqueteAux=paqueteAux;
		this.host=host;
		this.puerto=puerto;
		hilo= new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {
		try {
			Socket socket= new Socket("localhost",8081);
			ObjectOutputStream flujoSalida= new ObjectOutputStream(socket.getOutputStream());
			flujoSalida.writeObject(paqueteAux);
			flujoSalida.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
