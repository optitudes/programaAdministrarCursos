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
	private String ruta;
	private static final Logger LOGGER = Logger.getLogger(Log.class.getName());
	
	
	
	public Log(String mensaje, Level tipo) {
		hilo= new Thread(this);
	    this.mensaje=mensaje;
	    this.tipo=tipo;

	}
	
	@Override
	public void run() {
	FileHandler archivo;

		try {
			archivo= new FileHandler(ruta+"/registros.txt",true);//si es true no sobreescribe
			archivo.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(archivo);

			LOGGER.log(tipo, mensaje);
			archivo.close();

		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	public void start(){
		  hilo.start();
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Level getTipo() {
		return tipo;
	}

	public void setTipo(Level tipo) {
		this.tipo = tipo;
	}

	public Thread getHilo() {
		return hilo;
	}

	public void setHilo(Thread hilo) {
		this.hilo = hilo;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}

