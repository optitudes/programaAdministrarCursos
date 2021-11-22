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
	ArrayList<EDia> listaDias=new ArrayList<EDia>();
	ArrayList<EHorario> listaHorarios= new ArrayList<EHorario>();

	Estudiante estudiante=new Estudiante("juan","1193370914","juan@uqvirtual.co","1111");
	Admin       admin= new Admin("zeus","1002","admin@gmail.com","1234");
	Instructor instructor= new Instructor("Orfeo","1002","orfeo@gmail.com","2222");
	listaDias.add(EDia.LUNES);
	listaDias.add(EDia.JUEVES);
	listaHorarios.add(EHorario.SIETE_A_NUEVE_AM);
	listaHorarios.add(EHorario.NUEVE_A_ONCE_AM);

	Deportivo  deportivo= new Deportivo(2,2,new Horario(listaDias, listaHorarios),new Lugar("bloque 1",3, 2),EAsistenciaMinima.OCHENTA_PORCIENTO,"Deportivo","salud fisica");
	Cultural  cultural = new Cultural(2,2,new Horario(listaDias, listaHorarios), new Lugar("dos", 2, 2),"Cultural","lectura general");
	Academico academico= new Academico(2, 3, new Horario(listaDias, listaHorarios), new Lugar("Ingenieria", 2, 1), 4.0, EArea.MATEMATICAS, "Academica", "Calculo");


	listaEstudiantes.add(estudiante);
	listaAdmins.add(admin);
	listaInstructores.add(instructor);
	listaCreditos.add(deportivo);
	listaCreditos.add(academico);
	listaCreditos.add(cultural);



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

public boolean verificarNombreCredito(String nombre) {

	for (Credito credito : listaCreditos) {
			if(credito.verificarNombre(nombre))
				return true;
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
public boolean borrarCredito(Credito creditoSeleccionado) {
	if(listaCreditos.remove(creditoSeleccionado))
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
public boolean actualizarCredito(Credito creditoAux, Credito creditoSeleccionado) {
	int index=listaCreditos.indexOf(creditoSeleccionado);
	if(listaCreditos.get(index).actualizar(creditoAux))
		return true;
	return false;
}
public Deportivo crearDeportivo(String nombre, int cuposDisponibles, double costo, Horario horario,
		Lugar lugar, EAsistenciaMinima asistenciaMinAux, String tipo) {

	Deportivo deportivoAux=new Deportivo(costo, cuposDisponibles, horario, lugar, asistenciaMinAux,tipo, nombre);
	listaCreditos.add(deportivoAux);

	return deportivoAux;
}

public Cultural crearCultural(String nombre, int cuposDisponiblesInt, double costoDouble, Horario horarioAux,
		Lugar lugarAux, String tipoCredito) {

	Cultural culturalAux= new Cultural(costoDouble, cuposDisponiblesInt, horarioAux, lugarAux, tipoCredito, nombre);
	listaCreditos.add(culturalAux);
	return culturalAux;
}
public Academico crearAcademico(String nombre, int cuposDisponiblesInt, double costoDouble, Horario horarioAux,
		Lugar lugarAux, String tipoCredito, double notaDouble, EArea area) {

	Academico academicoAux= new Academico(costoDouble, cuposDisponiblesInt, horarioAux, lugarAux, notaDouble, area, tipoCredito, nombre);
	listaCreditos.add(academicoAux);
	return academicoAux;
}

public ArrayList<Estudiante> getListaEstudiantes() {
	return listaEstudiantes;
}
public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
	this.listaEstudiantes = listaEstudiantes;
}
public ArrayList<Admin> getListaAdmins() {
	return listaAdmins;
}
public void setListaAdmins(ArrayList<Admin> listaAdmins) {
	this.listaAdmins = listaAdmins;
}
public ArrayList<Instructor> getListaInstructores() {
	return listaInstructores;
}
public void setListaInstructores(ArrayList<Instructor> listaInstructores) {
	this.listaInstructores = listaInstructores;
}
public ArrayList<Credito> getListaCreditos() {
	return listaCreditos;
}
public void setListaCreditos(ArrayList<Credito> listaCreditos) {
	this.listaCreditos = listaCreditos;
}
public void guardarEstudiantes(String rutaArchivo) throws IOException {



}
public void guardarDatosTXT(String rutaArchivo, String tipo) throws IOException {
	ArrayList<String> listaInformacion= new ArrayList<String>();


	if(tipo.equals("credito")){
		ArrayList<String> listaInformacionAux1= new ArrayList<String>();
		ArrayList<String> listaInformacionAux2= new ArrayList<String>();


		for (Credito creditoAux : listaCreditos) {

			if(creditoAux instanceof Deportivo){
				Deportivo deportivoAux=(Deportivo) creditoAux;
				listaInformacion.add(deportivoAux.getDatosTXT());
			}
			if(creditoAux instanceof Cultural){
				Cultural culturalAux=(Cultural) creditoAux;
				listaInformacionAux1.add(culturalAux.getDatosTXT());
			}
			if(creditoAux instanceof Academico){
				Academico academicoAux=(Academico) creditoAux;
				listaInformacionAux2.add(academicoAux.getDatosTXT());
			}
		}
		Persistencia.escribirArchivo(rutaArchivo+"Deportivo.txt", listaInformacion, false);
		Persistencia.escribirArchivo(rutaArchivo+"Cultural.txt", listaInformacionAux1, false);
		Persistencia.escribirArchivo(rutaArchivo+"Academico.txt", listaInformacionAux2, false);

	}else{

		if(tipo.equals("estudiante.txt")){
			for (Estudiante estudianteAux : listaEstudiantes) {
				listaInformacion.add(estudianteAux.getDatosTXT());
			}
		}
		if(tipo.equals("instructor.txt")){
			for (Instructor instructorAux : listaInstructores) {
				listaInformacion.add(instructorAux.getDatosTXT());
			}
		}

		Persistencia.escribirArchivo(rutaArchivo, listaInformacion, false);
	}
}
public void cargarDatosTXT(String rutaArchivo, String tipo) throws IOException {
	ArrayList<String> listaInformacion= new ArrayList<String>();


	if(tipo.equals("credito")){
		ArrayList<String> listaInformacionAux1= new ArrayList<String>();
		ArrayList<String> listaInformacionAux2= new ArrayList<String>();

		ArrayList<Credito> listaCreditoRecuperado=  new ArrayList<>();

		listaInformacionAux2=Persistencia.leerArchivo(rutaArchivo+"Academico.txt");
		listaInformacionAux1=Persistencia.leerArchivo(rutaArchivo+"Cultural.txt");
		listaInformacion=Persistencia.leerArchivo(rutaArchivo+"Deportivo.txt");


		listaCreditoRecuperado.addAll(obtenerCreditos(listaInformacionAux2,"Academico"));
		listaCreditoRecuperado.addAll(obtenerCreditos(listaInformacionAux1,"Cultural"));
		listaCreditoRecuperado.addAll(obtenerCreditos(listaInformacion,"Deportivo"));
		listaCreditos=listaCreditoRecuperado;
	}
	if(tipo.equals("estudiante.txt"))
	{
		listaInformacion=Persistencia.leerArchivo(rutaArchivo);
		for (String elementos : listaInformacion) {
			{

			}

		}

	}
	if(tipo.equals("instructor.txt"))
	{
		listaInformacion=Persistencia.leerArchivo(rutaArchivo);
		for (String elementos : listaInformacion) {
			{

			}

		}

	}



}
private ArrayList<Credito> obtenerCreditos(ArrayList<String> listaInformacion, String tipo) {
	ArrayList<Credito> listaCreditos=new ArrayList<>();
//
	String nombre="";
	Double costo=0.0;
	 int cuposDisponibles=0;
	 int cuposRegistrados=0;
	Horario horarioAux=null;
	Lugar   lugarAux=null;



	if(tipo.equals("Academico"))
	{
		EArea   areaAux=null;
		Double  notaMin=null;
		Academico academicoAux=null;

		for (String elementos : listaInformacion) {

			String[] elementoDividido=elementos.split(";");

			for (int i = 0; i < elementoDividido.length; i++) {

				switch(i){
				case 0:nombre=elementoDividido[i];
					   break;
				case 1:costo=Double.parseDouble(elementoDividido[i]);
					   break;
				case 2:cuposDisponibles=Integer.parseInt(elementoDividido[i]);
					   break;
				case 3:cuposRegistrados=Integer.parseInt(elementoDividido[i]);
					   break;
				case 4:horarioAux=obtenerHorario(elementoDividido[i]);
					   break;
				case 5:lugarAux=obtenerLugar(elementoDividido[i]);
					   break;
				case 6:areaAux=obtenerArea(elementoDividido[i]);
					   break;
				case 7:notaMin=Double.parseDouble(elementoDividido[i]);
					   break;
				}
			}
			academicoAux=new Academico(costo, cuposDisponibles, horarioAux, lugarAux, notaMin, areaAux,tipo, nombre);
			listaCreditos.add(academicoAux);
		}
	}else{
		if(tipo.equals("Deportivo"))
		{
			EAsistenciaMinima asistenciaMin=null;

			Deportivo deportivoAux=null;

			for (String elementos : listaInformacion) {

				String[] elementoDividido=elementos.split(";");

				for (int i = 0; i < elementoDividido.length; i++) {

					switch(i){
					case 0:nombre=elementoDividido[i];
					break;
					case 1:costo=Double.parseDouble(elementoDividido[i]);
					break;
					case 2:cuposDisponibles=Integer.parseInt(elementoDividido[i]);
					break;
					case 3:cuposRegistrados=Integer.parseInt(elementoDividido[i]);
					break;
					case 4:horarioAux=obtenerHorario(elementoDividido[i]);
					break;
					case 5:lugarAux=obtenerLugar(elementoDividido[i]);
					break;
					case 6:asistenciaMin=obtenerAsistencia(elementoDividido[i]);
					break;

					}
				}
				deportivoAux=new Deportivo(costo, cuposDisponibles, horarioAux, lugarAux, asistenciaMin, tipo, nombre);
				listaCreditos.add(deportivoAux);

			}
		}else{

			Cultural culturalAux=null;

			for (String elementos : listaInformacion) {

				String[] elementoDividido=elementos.split(";");

				for (int i = 0; i < elementoDividido.length; i++) {

					switch(i){
					case 0:nombre=elementoDividido[i];
					break;
					case 1:costo=Double.parseDouble(elementoDividido[i]);
					break;
					case 2:cuposDisponibles=Integer.parseInt(elementoDividido[i]);
					break;
					case 3:cuposRegistrados=Integer.parseInt(elementoDividido[i]);
					break;
					case 4:horarioAux=obtenerHorario(elementoDividido[i]);
					break;
					case 5:lugarAux=obtenerLugar(elementoDividido[i]);
					break;

					}
				}
				culturalAux=new Cultural(costo, cuposDisponibles, horarioAux, lugarAux, tipo, nombre);
				listaCreditos.add(culturalAux);

			}
		}
	}
	return listaCreditos;
}

private EAsistenciaMinima obtenerAsistencia(String asistencia) {
	if(asistencia.equals("OCHENTA_PORCIENTO"))
	{
		return EAsistenciaMinima.OCHENTA_PORCIENTO;
	}else{
		if(asistencia.equals("SETENTA_PORCIENTO"))
		{
			return EAsistenciaMinima.SETENTA_PORCIENTO;
		}else{
			return EAsistenciaMinima.SETENTA_Y_CINCO_PORCIENTO;
		}

	}
}
private Lugar obtenerLugar(String lugarString) {
	Lugar lugar;
	lugarString=lugarString.replace(" ", "");
	String palabra="";
	String bloque="";
	int    piso=0;
	int    numSalon=0;
	int    contador;
	String[] elementos=lugarString.split(",");
	for (int i = 0; i < elementos.length; i++) {
		contador=0;
		palabra="";

		for (int j = 0; j < elementos[i].length(); j++) {

			if(elementos[i].charAt(j)=='='){
				contador++;
			}else{
				if(contador==1 && elementos[i].charAt(j)!=']' )
				{
					palabra+=elementos[i].charAt(j);
				}
			}
		}

		if(i==0)
		{
			bloque=palabra;
		}else{
			if(i==1)
			{
				piso=Integer.parseInt(palabra);
			}else{
				numSalon=Integer.parseInt(palabra);
			}
		}
	}

	lugar=new Lugar(bloque, piso, numSalon);

	return lugar;
}
private Horario obtenerHorario(String horarioAux) {
	String palabra="";
	String dias="";
	String horarios="";
	Horario horario;
	int  contadorCorcheteAbre=0;
	int  contadorCorcheteCierra=0;
	int  contadorAgregado=0;
	for (int i = 0; i < horarioAux.length(); i++) {

		if(horarioAux.charAt(i)=='[')
			contadorCorcheteAbre++;
		if(horarioAux.charAt(i)==']')
			contadorCorcheteCierra++;
		if(horarioAux.charAt(i)!=']' && horarioAux.charAt(i)!='['){

			if(contadorCorcheteAbre==2 && contadorCorcheteCierra==0)
				palabra+=horarioAux.charAt(i);
			if(contadorCorcheteAbre==3 && contadorCorcheteCierra==1)
				palabra+=horarioAux.charAt(i);

		}

		if(contadorCorcheteAbre==2 && contadorCorcheteCierra==1 && contadorAgregado==0){
			dias=palabra;
			palabra="";
			contadorAgregado++;
		}
		if(contadorCorcheteAbre==3 && contadorCorcheteCierra==2){
			horarios=palabra;
			palabra="";
			break;
		}
	}
//
//

	ArrayList<EDia> listaDias=obtenerDias(dias);
	ArrayList<EHorario> listaHorarios=obtenerHorarios(horarios);
	horario=new Horario(listaDias, listaHorarios);


	return horario;
}
private ArrayList<EHorario> obtenerHorarios(String horarios) {
	ArrayList<EHorario> listaHorariosEnum= new ArrayList<>();
	horarios=horarios.replace(" ", "");
	String[] listaHorarios=horarios.split(",");

	for (String horario : listaHorarios) {
		EHorario horarioAux=obtenerHorarioEnum(horario);
		listaHorariosEnum.add(horarioAux);
	}
	return listaHorariosEnum;
}
private EHorario obtenerHorarioEnum(String horario) {
	if(horario.equals("SIETE_A_NUEVE_AM"))
	{
		return EHorario.SIETE_A_NUEVE_AM;
	}else{
		if(horario.equals("NUEVE_A_ONCE_AM")){
			return EHorario.NUEVE_A_ONCE_AM;
		}else{
			if(horario.equals("DOS_A_CUATRO_PM")){
				return EHorario.DOS_A_CUATRO_PM;
			}else{
				return EHorario.CUATRO_A_SEIS_PM;
			}

		}
	}
}
private EArea obtenerArea(String area) {
	if(area.equals("DESARROLLO_PERSONAL"))
	{
		return EArea.DESARROLLO_PERSONAL;
	}else{
		if(area.equals("MATEMATICAS"))
		{
			return EArea.MATEMATICAS;
		}else{
			if(area.equals("PROGRAMACION"))
			{
				return EArea.PROGRAMACION;
			}else{
				return EArea.SISTEMAS;
			}
		}
	}
}
private ArrayList<EDia> obtenerDias(String dias) {

	ArrayList<EDia> listaDiasEnum= new ArrayList<>();
	dias=dias.replace(" ", "");
	String[] listaDias=dias.split(",");

	for (String dia : listaDias) {
		EDia diaAux=obtenerDia(dia);
		listaDiasEnum.add(diaAux);
	}



	return listaDiasEnum;
}
private EDia obtenerDia(String dia) {

	if(dia.equals("LUNES")){
		return EDia.LUNES;
	}else{
		if(dia.equals("MARTES")){
			return EDia.MARTES;
		}else{
			if(dia.equals("MIERCOLES")){
				return EDia.MIERCOLES;
			}else{
				if(dia.equals("JUEVES")){
					return EDia.JUEVES;
				}else{
					return EDia.VIERNES;

				}
			}
		}
	}

}
}


