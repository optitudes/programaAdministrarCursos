package co.edu.uniquindio.programaAdministrarCursos.hilos;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class Log implements Runnable{
	
	private String mensaje="";
	private Level  tipo;
	public Thread hilo;
	private static final Logger LOGGER = Logger.getLogger(Log.class.getName());
	
	
	
	public Log(String mensaje, Level tipo) {
		hilo= new Thread(this);
	    this.mensaje=mensaje;
	    this.tipo=tipo;
	    hilo.start();
	}
	
	@Override
	public void run() {
	FileHandler archivo;

		try {
			archivo= new FileHandler("src/resources/loggers/registros.txt",true);//si es true no sobreescribe
			archivo.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(archivo);

			LOGGER.log(tipo, mensaje);
			archivo.close();

		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
}

