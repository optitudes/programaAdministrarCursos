package co.edu.uniquindio.programaAdministrarCursos;

import co.edu.uniquindio.programaAdministrarCursos.model.AdminHilos;
import co.edu.uniquindio.programaAdministrarCursos.model.Bienestar;
import co.edu.uniquindio.programaAdministrarCursos.model.Server;


public class ServerMain {
	/**
	 * método main de la clase ServerMain
	 * @param args
	 */
	public static  void main(String[] args) {
		Server servidor= new Server(8081);
		servidor.arrancarServer();
	}


}

