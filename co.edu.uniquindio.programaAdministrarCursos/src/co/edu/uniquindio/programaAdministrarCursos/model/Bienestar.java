package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Bienestar {

	private static final Logger LOGGER = Logger.getLogger(Bienestar.class.getName());
	
	private String nombre;
	private String nit;
	
	private ArrayList<Estudiante> listaEstudiantes=  new ArrayList<Estudiante>();
	private ArrayList<Admin>      listaAdmins=       new ArrayList<Admin>();
	private ArrayList<Instructor> listaInstructores= new ArrayList<Instructor>();
	private ArrayList<Credito> listaCreditos= new ArrayList<Credito>();
	
	public Bienestar() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bienestar(String nombre, String nit) {
		super();
		this.nombre = nombre;
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	@Override
	public String toString() {
		return "Bienestar [nombre=" + nombre + ", nit=" + nit + "]";
	}
	
//	public void cargarDatos(String rutaUsuarios,String rutaCreditos){
//		try {
//			listaUsuarios=(ArrayList<User>) Persistencia.deseRializarObjeto(rutaUsuarios);
//			listaCreditos=(ArrayList<Credito>) Persistencia.deseRializarObjeto(rutaCreditos);
//			registrarDatosCargados();
//		} catch (Exception e) {
//			registrarErrorCargarDatos(e.getMessage());
//		}
//	}
//	public void guardarDatos(String rutaUsuarios,String rutaCreditos)
//	{
//		try {
//			Persistencia.serializarObjeto(rutaUsuarios, listaUsuarios);
//			Persistencia.serializarObjeto(rutaCreditos,listaCreditos);
//			registrarDatosGuardados();
//		} catch (IOException e) {
//			registrarErrorGuardarDatos(e.getMessage());
//		}
//	}
//
//	private void registrarErrorGuardarDatos(String mensaje) {
//		FileHandler archivo;
//
//		try {
//			archivo= new FileHandler("src/resources/loggers/errorGuardarDatosBienestar.txt",true);//si es true no sobreescribe
//			archivo.setFormatter(new SimpleFormatter());
//			LOGGER.addHandler(archivo);
//
//			LOGGER.log(Level.INFO,mensaje);
//
//		} catch (SecurityException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//private void registrarErrorCargarDatos(String mensaje) {
//	FileHandler archivo;
//
//		try {
//			archivo= new FileHandler("src/resources/loggers/errorCargarDatosBienestar.txt",true);//si es true no sobreescribe
//			archivo.setFormatter(new SimpleFormatter());
//			LOGGER.addHandler(archivo);
//
//			LOGGER.log(Level.INFO,mensaje);
//
//		} catch (SecurityException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void registrarDatosGuardados() {
//		FileHandler archivo;
//
//		try {
//			archivo= new FileHandler("src/resources/loggers/datosGuardadosBienestar.txt",true);//si es true no sobreescribe
//			archivo.setFormatter(new SimpleFormatter());
//			LOGGER.addHandler(archivo);
//
//			LOGGER.log(Level.INFO, "datos guardados por  user:admin");
//
//		} catch (SecurityException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//private void registrarDatosCargados() {
//	FileHandler archivo;
//
//		try {
//			archivo= new FileHandler("src/resources/loggers/datosCargadosBienestar.txt",true);//si es true no sobreescribe
//			archivo.setFormatter(new SimpleFormatter());
//			LOGGER.addHandler(archivo);
//
//			LOGGER.log(Level.INFO, "datos cargados por  user:admin");
//
//		} catch (SecurityException | IOException e) {
//			e.printStackTrace();
//		}
//	}
public ArrayList<Estudiante> getEstudiantes() {
	return listaEstudiantes;
}
public void quemarDatos() {

	Estudiante estudiante=new Estudiante("juan","1193370914","juan@uqvirtual.co","1111");
	Admin       admin= new Admin("zeus","1002","admin@gmail.com","1234");
	Instructor instructor= new Instructor("Orfeo","1002","orfeo@gmail.com","2222");
	
	listaEstudiantes.add(estudiante);
	listaAdmins.add(admin);
	listaInstructores.add(instructor);
	
}

}


