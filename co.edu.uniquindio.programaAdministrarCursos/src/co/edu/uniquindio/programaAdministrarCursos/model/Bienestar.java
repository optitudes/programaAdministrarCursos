package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.programaAdministrarCursos.exception.EstudianteNoCreadoException;

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
public ArrayList<Instructor> getInstructores() {
	return listaInstructores;
}
public void quemarDatos() {

	Estudiante estudiante=new Estudiante("juan","1193370914","juan@uqvirtual.co","1111");
	Admin       admin= new Admin("zeus","1002","admin@gmail.com","1234");
	Instructor instructor= new Instructor("Orfeo","1002","orfeo@gmail.com","2222");
	
	listaEstudiantes.add(estudiante);
	listaAdmins.add(admin);
	listaInstructores.add(instructor);
	
}

public boolean verificarIDEstudiante(String iD) {
	
	for (Estudiante estudiante : listaEstudiantes) {
		
		if(estudiante.verificarID(iD))
			return true;
		
	}
	return false;
}
public boolean verificarIDInstructor(String iD) {
for (Instructor instructor : listaInstructores) {
		
		if(instructor.verificarID(iD))
			return true;
		
	}
	return false;
}
public boolean verificarCorreoEstudiante(String correo) {
	
	for (Estudiante estudiante : listaEstudiantes) {

		if(estudiante.verificarCorreo(correo))
			return true;

	}
	return false;
}
public boolean verificarCorreoInstructor(String correo) {
	for (Instructor instructor : listaInstructores) {

		if(instructor.verificarCorreo(correo))
			return true;

	}
	return false;
}
public boolean verificarNombreAcademico(String nombre) {

	for (Credito credito : listaCreditos) {
		if(credito instanceof Academico){
			if(credito.verificarNombre(nombre))
				return true;
		}
		
	}
	return false;
}
public boolean verificarNombreCultural(String nombre2) {

	for (Credito credito : listaCreditos) {
		if(credito instanceof Cultural){
			if(credito.verificarNombre(nombre))
				return true;
		}
		
	}
	return false;
}
public boolean verificarNombreDeportivo(String nombre2) {
	
	for (Credito credito : listaCreditos) {
		if(credito instanceof Deportivo){
			if(credito.verificarNombre(nombre))
				return true;
		}
		
	}
	return false;
}
public Estudiante crearEstudiante(String nombre, String iD, String correo, String contrasenia) {

	Estudiante estudianteAux= new Estudiante(nombre, iD, correo, contrasenia);
	listaEstudiantes.add(estudianteAux);
	return estudianteAux;
}

public Instructor crearInstructor(String nombre, String iD, String correo, String contrasenia) {
	Instructor instructorAux= new Instructor(nombre, iD, correo, contrasenia);
	listaInstructores.add(instructorAux);
	return instructorAux;
}
public boolean borrarEstudiante(Estudiante estudianteSeleccionado) {
	if(listaEstudiantes.remove(estudianteSeleccionado))	
		return true;
	return false;
}
public boolean borrarInstructor(Instructor instructorSeleccionado) {
	if(listaInstructores.remove(instructorSeleccionado))
		return true;
	return false;
}

public boolean actualizarEstudiante(Estudiante estudianteAux, Estudiante estudianteSeleccionado) {
	int index=listaEstudiantes.indexOf(estudianteSeleccionado);
	if(listaEstudiantes.get(index).actualizar(estudianteAux))
		return true;
	return false;
}
public boolean actualizarInstructor(Instructor instructorAux, Instructor instructorSeleccionado) {
	int index=listaInstructores.indexOf(instructorSeleccionado);
	if(listaInstructores.get(index).actualizar(instructorAux))
		return true;
	return false;
}
public Deportivo crearDeportivo(String nombre, int cuposDisponibles, double costo, Horario horario,
		Lugar lugar, EAsistenciaMinima asistenciaMinAux) {
	
	Deportivo deportivoAux=new Deportivo(costo, cuposDisponibles, horario, lugar, asistenciaMinAux);
	listaCreditos.add(deportivoAux);
	
	return deportivoAux;
}







}


