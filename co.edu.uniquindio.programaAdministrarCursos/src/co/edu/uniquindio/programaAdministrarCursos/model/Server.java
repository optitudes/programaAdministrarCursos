package co.edu.uniquindio.programaAdministrarCursos.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert.AlertType;


public class Server{
	ServerSocket servidor;
	Socket  socket;
	PaqueteDatos paqueteDatos;
	PaqueteDatos paqueteDatosAux;

	ObjectInputStream flujoEntradaObjeto;
	ObjectOutputStream flujoSalidaObjeto;

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
				+ "Menú backups\n elija un backup  si desea cargarlo.\n");
		buscarBackups();

		while(true){
			System.out.println("Esperando usuario...");
			AccionEnum accionServer;

			try {
				socket = servidor.accept();
				flujoEntradaObjeto = new ObjectInputStream(socket.getInputStream());//10101
				paqueteDatos =  (PaqueteDatos) flujoEntradaObjeto.readObject();
				accionServer=paqueteDatos.getAccion();
				System.out.println(accionServer);

				switch(accionServer){

				case LOGGEAR_ESTUDIANTE:logearEstudiante();
									    break;
				case REGISTRAR_ACCION:  registrarAccion();
										break;
				case HACER_BACKUP:      hacerBackup();
										break;
				case OBTENER_ESTUDIANTES: obtenerEstudiantes();
										break;
				case OBTENER_INSTRUCTORES: obtenerInstructores();
										break;
				case OBTENER_CREDITOS:  obtenerCreditos();
										break;
				case VERIFICAR_ID_ESTUDIANTE: verificarIDEstudiante();
										break;
				case VERIFICAR_ID_INSTRUCTOR: verificarIDInstructor();
										break;
				case VERIFICAR_CORREO_ESTUDIANTE: verificarCorreoEstudiante();
										break;
				case VERIFICAR_CORREO_INSTRUCTOR: verificarCorreoInstructor();
										break;
				case VERIFICAR_NOMBRE_CREDITO: verificarNombreCredito();
										break;
				case REGISTRAR_ESTUDIANTE: registrarEstudiante();
										break;
				case REGISTRAR_INSTRUCTOR: registrarInstructor();
										break;
				case REGISTRAR_CREDITO_DEPORTIVO: registrarCreditoDeportivo();
										break;
				case REGISTRAR_CREDITO_ACADEMICO: registrarCreditoAcademico();
										break;
				case REGISTRAR_CREDITO_CULTURAL: registrarCreditoCultural();
										break;
				case ACTUALIZAR_ESTUDIANTE: actualizarEstudiante();
										break;
				case ACTUALIZAR_INSTRUCTOR: actualizarInstructor();
										break;
				case ACTUALIZAR_CREDITO: actualizarCredito();
										break;
				case BORRAR_ESTUDIANTE: borrarEstudiante();
										break;
				case BORRAR_INSTRUCTOR: borrarInstructor();
										break;
				case BORRAR_CREDITO: borrarCredito();
										break;
				case CARGAR_DATOS_TXT: cargarDatosTXT();
										break;
				case GUARDAR_DATOS_TXT: guardarDatosTXT();
										break;
				case CARGAR_BACKUP:		 cargarBackup();
										break;
				}

			} catch ( Exception e) {
				e.printStackTrace();
				adminHilos.startHiloLogger("Error I/O [Servidor]"+e.getCause(),Level.SEVERE);
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
	
	private void cargarBackup() throws Exception {
		String correoAdmin;
		String rutaArchivo;

		ArrayList<String> listaDatos;
		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=       	        (String)     listaDatos.get(0);
		rutaArchivo= 				(String) 	 listaDatos.get(1);

		System.out.println("datos recibidos con éxito");
		this.bienestar=(Bienestar) Persistencia.deseRializarObjeto(rutaArchivo);
		adminHilos.startHiloLogger("Backup cargado["+rutaArchivo+"] por el admin ["+correoAdmin+"]",Level.INFO);
	
		
	}
	private void guardarDatosTXT() throws IOException {
		String correoAdmin;
		String nombreArchivo;

		ArrayList<String> listaDatos;
		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=       	        (String)     listaDatos.get(0);
		nombreArchivo= 				(String) 	 listaDatos.get(1);

		System.out.println("datos recibidos con éxito");
		bienestar.guardarDatosTXT(bienestar.getRutaArchivos()+"/"+nombreArchivo, nombreArchivo);

		adminHilos.startHiloLogger("datos guardados en el registro["+nombreArchivo+"] por el admin ["+correoAdmin+"]",Level.INFO);
	
		
	}
	private void cargarDatosTXT() throws IOException {
		String correoAdmin;
		String nombreArchivo;

		ArrayList<String> listaDatos;
		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=       	        (String)     listaDatos.get(0);
		nombreArchivo= 				(String) 	 listaDatos.get(1);

		System.out.println("datos recibidos con éxito");
		bienestar.cargarDatosTXT(bienestar.getRutaArchivos()+"/"+nombreArchivo, nombreArchivo);

		adminHilos.startHiloLogger("datos cargador en el registro["+nombreArchivo+"] por el admin ["+correoAdmin+"]",Level.INFO);
	
		
	}
	private void borrarCredito() throws IOException {
		boolean respuesta;
		String correoAdmin;
		String nombreCredito;

		ArrayList<String> listaDatos;
		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=       	        (String)     listaDatos.get(0);
		nombreCredito= 				(String) 	 listaDatos.get(1);

		respuesta=bienestar.borrarCredito(nombreCredito);
		System.out.println("datos recibidos con éxito");

		paqueteDatosAux=new PaqueteDatos(AccionEnum.BORRAR_CREDITO,respuesta);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("Credito con nombre "+nombreCredito+" borrado por el admin ["+correoAdmin+"]",Level.INFO);
	
		
	}
	private void borrarInstructor() throws IOException {
		boolean respuesta;
		String correoAdmin;
		String iDInstructor;

		ArrayList<String> listaDatos;
		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=       	        (String)     listaDatos.get(0);
		iDInstructor= 				(String) 	 listaDatos.get(1);

		respuesta=bienestar.borrarInstructor(iDInstructor);
		System.out.println("datos recibidos con éxito");

		paqueteDatosAux=new PaqueteDatos(AccionEnum.BORRAR_INSTRUCTOR,respuesta);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("Instructor con ID "+iDInstructor+" borrado por el admin ["+correoAdmin+"]",Level.INFO);
	
		
	}
	private void borrarEstudiante() throws IOException {
		boolean respuesta;
		String correoAdmin;
		String iDEstudiante;
		Instructor instructorAux;

		ArrayList<String> listaDatos;
		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=       	        (String)     listaDatos.get(0);
		iDEstudiante= 				(String) 	 listaDatos.get(1);

		respuesta=bienestar.borrarEstudiante(iDEstudiante);
		System.out.println("datos recibidos con éxito");

		paqueteDatosAux=new PaqueteDatos(AccionEnum.BORRAR_ESTUDIANTE,respuesta);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("Estudiante con ID "+iDEstudiante+" borrado por el admin ["+correoAdmin+"]",Level.INFO);
	
		
	}
	private void actualizarCredito() throws IOException {
		boolean respuesta;
		String correoAdmin;
		String nombreCredito;
		Credito credito;

		ArrayList<Object> listaDatos;
		listaDatos= (ArrayList<Object>) paqueteDatos.getContenido();

		correoAdmin=       	    (String)     listaDatos.get(0);
		credito=		   		(Credito) listaDatos.get(1);
		nombreCredito= 				(String) listaDatos.get(2);

		respuesta=bienestar.actualizarCredito(credito, nombreCredito);
		System.out.println("datos recibidos con éxito");

		paqueteDatosAux=new PaqueteDatos(AccionEnum.ACTUALIZAR_CREDITO,respuesta);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("Credito con nombre "+nombreCredito+" actualizado por el admin ["+correoAdmin+"]",Level.INFO);

		
	}
	private void actualizarInstructor() throws IOException {
		boolean respuesta;
		String correoAdmin;
		String iDBuscar;
		Instructor instructorAux;

		ArrayList<Object> listaDatos;
		listaDatos= (ArrayList<Object>) paqueteDatos.getContenido();

		correoAdmin=       	    (String)     listaDatos.get(0);
		instructorAux=		    (Instructor) listaDatos.get(1);
		iDBuscar= 				(String) listaDatos.get(2);

		respuesta=bienestar.actualizarInstructor(instructorAux, iDBuscar);
		System.out.println("datos recibidos con éxito");

		paqueteDatosAux=new PaqueteDatos(AccionEnum.ACTUALIZAR_INSTRUCTOR,respuesta);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("Instructor con nombre "+instructorAux.getName()+" actualizado por el admin ["+correoAdmin+"]",Level.INFO);
	}
	private void actualizarEstudiante() throws IOException {
		boolean respuesta;
		String correoAdmin;
		String iDBuscar;
		Estudiante estudianteAux;

		ArrayList<Object> listaDatos;
		listaDatos= (ArrayList<Object>) paqueteDatos.getContenido();

		correoAdmin=       	    (String)     listaDatos.get(0);
		estudianteAux=		    (Estudiante) listaDatos.get(1);
		iDBuscar= 				(String) listaDatos.get(2);

		respuesta=bienestar.actualizarEstudiante(estudianteAux, iDBuscar);
		System.out.println("datos recibidos con éxito");

		paqueteDatosAux=new PaqueteDatos(AccionEnum.ACTUALIZAR_ESTUDIANTE,respuesta);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("estudiante con nombre "+estudianteAux.getName()+" actualizado por el admin ["+correoAdmin+"]",Level.INFO);

		
	}
	private void obtenerCreditos() throws IOException {
		ArrayList<Credito> listaCreditos=new ArrayList<Credito>();
		
		System.out.println("datos recibidos con éxito");
		listaCreditos=bienestar.getListaCreditos();

		paqueteDatosAux=new PaqueteDatos(AccionEnum.OBTENER_CREDITOS,listaCreditos);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		
	}
	private void obtenerInstructores() throws IOException {
		ArrayList<Instructor> listaInstructores=new ArrayList<Instructor>();
		
		System.out.println("datos recibidos con éxito");
		listaInstructores=bienestar.getListaInstructores();

		paqueteDatosAux=new PaqueteDatos(AccionEnum.OBTENER_INSTRUCTORES,listaInstructores);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		
	}
	private void obtenerEstudiantes() throws IOException {
		ArrayList<Estudiante> listaEstudiantes=new ArrayList<Estudiante>();
		
		System.out.println("datos recibidos con éxito");
		listaEstudiantes=bienestar.getListaEstudiantes();

		paqueteDatosAux=new PaqueteDatos(AccionEnum.OBTENER_ESTUDIANTES,listaEstudiantes);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		
	}
	/**
	 * método que crea un credito academico con los atributos 
	 * recogidos por el socket, al terminar de 
	 * crear al credito lo retorna por el 
	 * mismo socket
	 * @throws IOException
	 */
	private void registrarCreditoCultural() throws IOException {
		Cultural culturalAux;
		ArrayList<Object> listaDatos;
		String correoAdmin;
		
		String nombre;
		int cuposDisponiblesInt;
		double costoDouble;
		Horario horarioAux;
		Lugar lugarAux;
		String tipoCredito;

		listaDatos= (ArrayList<Object>) paqueteDatos.getContenido();

		correoAdmin=        (String) listaDatos.get(0);
		nombre=             (String) listaDatos.get(1);
		cuposDisponiblesInt=(int) listaDatos.get(2);
		costoDouble=		(double) listaDatos.get(3);
		horarioAux=			(Horario) listaDatos.get(4);
		lugarAux=			(Lugar) listaDatos.get(5);
		tipoCredito=		(String) listaDatos.get(6);

		System.out.println("datos recibidos con éxito");
		culturalAux=bienestar.crearCultural(nombre, cuposDisponiblesInt, costoDouble, horarioAux, lugarAux, tipoCredito);

		paqueteDatosAux=new PaqueteDatos(AccionEnum.REGISTRAR_CREDITO_CULTURAL,culturalAux);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("credito cultural con nombre "+nombre+" creado por el admin ["+correoAdmin+"]",Level.INFO);
	}
	/**
	 * método que crea un credito academico con los atributos 
	 * recogidos por el socket, al terminar de 
	 * crear al credito lo retorna por el 
	 * mismo socket
	 * @throws IOException
	 */
	private void registrarCreditoAcademico() throws IOException {
		Academico academicoAux;
		ArrayList<Object> listaDatos;
		String correoAdmin;
		
		String nombre;
		int cuposDisponiblesInt;
		double costoDouble;
		Horario horarioAux;
		Lugar lugarAux;
		String tipoCredito;
		double notaDouble;
		EArea area;

		listaDatos= (ArrayList<Object>) paqueteDatos.getContenido();

	
		
		correoAdmin=        (String) listaDatos.get(0);
		nombre=             (String) listaDatos.get(1);
		cuposDisponiblesInt=(int) listaDatos.get(2);
		costoDouble=		(double) listaDatos.get(3);
		horarioAux=			(Horario) listaDatos.get(4);
		lugarAux=			(Lugar) listaDatos.get(5);
		tipoCredito=		(String) listaDatos.get(6);
		notaDouble=			(double) listaDatos.get(7);
		area=			    (EArea) listaDatos.get(8);

		System.out.println("datos recibidos con éxito");
		academicoAux=bienestar.crearAcademico(nombre, cuposDisponiblesInt, costoDouble, horarioAux, lugarAux, tipoCredito, notaDouble, area);

		paqueteDatosAux=new PaqueteDatos(AccionEnum.REGISTRAR_CREDITO_ACADEMICO,academicoAux);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("credito academico con nombre "+nombre+" creado por el admin ["+correoAdmin+"]",Level.INFO);
	
		
	}
	/**
	 * método que crea un credito deportivo con los atributos 
	 * recogidos por el socket, al terminar de 
	 * crear al credito lo retorna por el 
	 * mismo socket
	 * @throws IOException
	 */
	private void registrarCreditoDeportivo() throws IOException {
		Deportivo deportivoAux;
		ArrayList<Object> listaDatos;
		String correoAdmin;
		
		String nombre;
		int cuposDisponiblesInt;
		double costoDouble;
		Horario horarioAux;
		Lugar lugarAux;
		EAsistenciaMinima asistenciaMinAux;
		String tipoCredito;

		listaDatos= (ArrayList<Object>) paqueteDatos.getContenido();

	
		
		correoAdmin=        (String) listaDatos.get(0);
		nombre=             (String) listaDatos.get(1);
		cuposDisponiblesInt=(int) listaDatos.get(2);
		costoDouble=		(double) listaDatos.get(3);
		horarioAux=			(Horario) listaDatos.get(4);
		lugarAux=			(Lugar) listaDatos.get(5);
		asistenciaMinAux=   (EAsistenciaMinima) listaDatos.get(6);
		tipoCredito=		(String) listaDatos.get(7);

		System.out.println("datos recibidos con éxito");
		deportivoAux=bienestar.crearDeportivo(nombre, cuposDisponiblesInt, costoDouble, horarioAux,
							                 lugarAux, asistenciaMinAux, tipoCredito);

		paqueteDatosAux=new PaqueteDatos(AccionEnum.REGISTRAR_CREDITO_DEPORTIVO,deportivoAux);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("credito deportivo con nombre "+nombre+" creado por el admin ["+correoAdmin+"]",Level.INFO);
	
	}
	/**
	 * método que crea un estudiante con los atributos 
	 * recogidos por el socket, al terminar de 
	 * crear al estudiante lo retorna por el 
	 * mismo socket
	 * @throws IOException
	 */
	private void registrarEstudiante() throws IOException {
		Estudiante estudianteAux;
		ArrayList<String> listaDatos;
		
		String correoAdmin;
		String nombre;
		String iD;
		String correo;
		String contrasenia;

		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=listaDatos.get(0);
		nombre=listaDatos.get(1);
		iD=listaDatos.get(2);
		correo=listaDatos.get(3);
		contrasenia=listaDatos.get(4);

		System.out.println("datos recibidos con éxito");
		estudianteAux=bienestar.crearEstudiante(nombre, iD, correo, contrasenia);

		paqueteDatosAux=new PaqueteDatos(AccionEnum.REGISTRAR_ESTUDIANTE,estudianteAux);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("estudiante con id"+iD+" creado por el admin ["+correoAdmin+"]",Level.INFO);
	}
	private void registrarInstructor() throws IOException {
		Instructor instructorAux;
		ArrayList<String> listaDatos;
		
		String correoAdmin;
		String nombre;
		String iD;
		String correo;
		String contrasenia;

		listaDatos= (ArrayList<String>) paqueteDatos.getContenido();

		correoAdmin=listaDatos.get(0);
		nombre=listaDatos.get(1);
		iD=listaDatos.get(2);
		correo=listaDatos.get(3);
		contrasenia=listaDatos.get(4);

		System.out.println("datos recibidos con éxito");
		instructorAux=bienestar.crearInstructor(nombre, iD, correo, contrasenia);

		paqueteDatosAux=new PaqueteDatos(AccionEnum.REGISTRAR_INSTRUCTOR,instructorAux);
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		adminHilos.startHiloLogger("instructor con id"+iD+" creado por el admin ["+correoAdmin+"]",Level.INFO);
		
	}

	/**
	 * método que verifica si el nombre ingresado por el socket
	 * pertenece a algún credito registrado
	 * si pertenece envía un paquete con la acción [VERIFICAR_NOMBRE_CREDITO]
	 * y el contenido booleano true, de lo contrario el contenido es 
	 * booleano false.
	 * @throws IOException
	 */
	private void verificarNombreCredito() throws IOException {
		boolean respuesta;
		String  nombreValidar;

		nombreValidar= (String) paqueteDatos.getContenido();

		System.out.println("datos recibidos con éxito");
		respuesta=bienestar.verificarNombreCredito(nombreValidar);
		paqueteDatosAux=new PaqueteDatos(AccionEnum.VERIFICAR_NOMBRE_CREDITO,respuesta);
		System.out.println("enviando datos respuesta");
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		
	}
	/**
	 * método que verifica si el correo ingresado por el socket
	 * pertenece a algún estudiante registrado
	 * si pertenece envía un paquete con la acción [VERIFICAR_CORREO_ESTUDIANTE]
	 * y el contenido booleano true, de lo contrario el contenido es 
	 * booleano false.
	 * @throws IOException
	 */
	private void verificarCorreoEstudiante() throws IOException {
		boolean respuesta;
		String  correoValidar;

		correoValidar= (String) paqueteDatos.getContenido();

		System.out.println("datos recibidos con éxito");
		respuesta=bienestar.verificarCorreoEstudiante(correoValidar);
		paqueteDatosAux=new PaqueteDatos(AccionEnum.VERIFICAR_CORREO_ESTUDIANTE,respuesta);
		System.out.println("enviando datos respuesta");
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");

	}
	/**
	 * método que verifica si el correo ingresado por el socket
	 * pertenece a algún instructor registrado
	 * si pertenece envía un paquete con la acción [VERIFICAR_CORREO_INSTRUCTOR]
	 * y el contenido booleano true, de lo contrario el contenido es 
	 * booleano false.
	 * @throws IOException
	 */
	private void verificarCorreoInstructor() throws IOException {
		boolean respuesta;
		String  correoValidar;

		correoValidar= (String) paqueteDatos.getContenido();

		System.out.println("datos recibidos con éxito");
		respuesta=bienestar.verificarCorreoInstructor(correoValidar);
		paqueteDatosAux=new PaqueteDatos(AccionEnum.VERIFICAR_CORREO_INSTRUCTOR,respuesta);
		System.out.println("enviando datos respuesta");
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
		System.out.println("datos respuesta enviados");
		
	}
	/**
	 * método que verifica si el id ingresado por el socket
	 * pertenece a algún estudiante registrado
	 * si pertenece envía un paquete con la acción [VERIFICAR_ID_ESTUDIANTE]
	 * y el contenido booleano true, de lo contrario el contenido es 
	 * booleano false.
	 * @throws IOException
	 */
	private void verificarIDEstudiante() throws IOException {
		boolean respuesta;
		String  idValidar;
		
		idValidar= (String) paqueteDatos.getContenido();

		System.out.println("datos recibidos con éxito");
		respuesta=bienestar.verificarIDEstudiante(idValidar);
		paqueteDatosAux=new PaqueteDatos(AccionEnum.VERIFICAR_ID_ESTUDIANTE,respuesta);
		System.out.println("enviando datos respuesta");
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
	}
	/**
	 * método que verifica si el id ingresado por el socket
	 * pertenece a algún instructor registrado
	 * si pertenece envía un paquete con la acción [VERIFICAR_ID_INSTRUCTOR]
	 * y el contenido booleano true, de lo contrario el contenido es 
	 * booleano false.
	 * @throws IOException
	 */
	private void verificarIDInstructor() throws IOException {
		boolean respuesta;
		String  idValidar;
		
		idValidar= (String) paqueteDatos.getContenido();

		System.out.println("datos recibidos con éxito");
		respuesta=bienestar.verificarIDInstructor(idValidar);
		paqueteDatosAux=new PaqueteDatos(AccionEnum.VERIFICAR_ID_INSTRUCTOR,respuesta);
		System.out.println("enviando datos respuesta");
		flujoSalidaObjeto=new ObjectOutputStream(socket.getOutputStream());
		flujoSalidaObjeto.writeObject(paqueteDatosAux);
	}
	private void hacerBackup() {
		String nombre=(String) paqueteDatos.getContenido();
		crearBackup(nombre);

	}
	private void registrarAccion() {
		try {
			ArrayList<Object> contenido= new ArrayList<>();
			contenido= (ArrayList<Object>) paqueteDatos.getContenido();
			String mensaje;
			Level  tipo;

			mensaje=(String) contenido.get(0);
			tipo=(Level) contenido.get(1);

			adminHilos.startHiloLogger(mensaje, tipo);

		} catch (ClassCastException e) {
			adminHilos.startHiloLogger("Error al registrar accion [ClassCastException]",Level.SEVERE);

		}


	}
	private void logearEstudiante() {
		ArrayList<String> contenido = (ArrayList<String>) paqueteDatos.getContenido();
		datosLoggin=contenido;
		if(datosLoggin!=null){

			for (String string : datosLoggin) {
				System.out.println(string);

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
		if(posicion!=listaArchivos.length){
			
			try {
				this.bienestar=(Bienestar) Persistencia.deseRializarObjeto(bienestar.getRutaRespaldo()+"/"+listaArchivos[posicion]);
				System.out.println("-->Backup ["+listaArchivos[posicion]+"] Cargado con éxito");
				adminHilos.startHiloLogger("backup ["+listaArchivos[posicion]+"] Cargado con éxito ",Level.INFO);
			} catch (Exception e) {
				e.printStackTrace();
				adminHilos.startHiloLogger("Error al cargar backup ["+e.getMessage()+"] "+e.getCause(),Level.SEVERE);
			}	
		}
	}
	/**
	 * método que imprime los backups existentes y les asigna un 
	 * indice, regoge el indice con un JOption y lo retorna
	 * @param listaArchivos
	 * @return
	 */
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
	public void serializarBienestar(String nombreArchivo) throws IOException {
		Persistencia.serializarObjeto(bienestar.getRutaRespaldo()+"/"+nombreArchivo, bienestar);

	}
	public void crearBackup(String nombre) {
		String fecha;
		fecha=""+LocalDate.now(Clock.systemDefaultZone ())+LocalTime.now();
		fecha=fecha.replaceAll("-","_");
		fecha=fecha.replaceAll(":","_");
		fecha=fecha.replace(".","_");


		try {
			serializarBienestar("Backup_"+fecha+".dat");
		} catch (IOException e) {
			System.out.println("Error\n"+"No se pudo hacer el backup\n"+
										  "El programa no pudo crear el backup "
										  + " póngase en contacto con el proveedor"
										  + " del servicio");
			adminHilos.startHiloLogger("Error al hacer backup admin ["+nombre+"]",Level.SEVERE);
		}
	}
}

