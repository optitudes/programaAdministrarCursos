package co.edu.uniquindio.programaAdministrarCursos;

import java.awt.Component;
import java.awt.Label;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import co.edu.uniquindio.programaAdministrarCursos.controller.AdminController;
import co.edu.uniquindio.programaAdministrarCursos.controller.EstudianteController;
import co.edu.uniquindio.programaAdministrarCursos.controller.InstructorController;
import co.edu.uniquindio.programaAdministrarCursos.controller.LoggingController;
import co.edu.uniquindio.programaAdministrarCursos.model.Academico;
import co.edu.uniquindio.programaAdministrarCursos.model.AdminHilos;
import co.edu.uniquindio.programaAdministrarCursos.model.Bienestar;
import co.edu.uniquindio.programaAdministrarCursos.model.Credito;
import co.edu.uniquindio.programaAdministrarCursos.model.Cultural;
import co.edu.uniquindio.programaAdministrarCursos.model.Deportivo;
import co.edu.uniquindio.programaAdministrarCursos.model.EArea;
import co.edu.uniquindio.programaAdministrarCursos.model.EAsistenciaMinima;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;
import co.edu.uniquindio.programaAdministrarCursos.model.Horario;
import co.edu.uniquindio.programaAdministrarCursos.model.Instructor;
import co.edu.uniquindio.programaAdministrarCursos.model.Lugar;
import co.edu.uniquindio.programaAdministrarCursos.model.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	private Stage primaryStage;
	private String rutaRaiz="";
	private String rutaPersistencia;
	private String rutaRespaldo;
	private String rutaArchivos;
	private String rutaLog;

	Bienestar bienestar = new Bienestar("Cooperativa", "1022");

	@Override
	public void start(Stage primaryStage) {

		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Bienestar");
			mostrarVentanaLogging();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * metodo que carga la vista del programa y le asigna n controlador y
	 * la clase main
	 */
public void mostrarVentanaLogging() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/loggingView.fxml"));

			BorderPane rootLayout = (BorderPane)loader.load();
			LoggingController loggingController = loader.getController();
			loggingController.setAplicacion(this);
			if(rutaRaiz==null || rutaRaiz.isEmpty())
			{
				mostrarMensaje("Atenci�n!","El programa no cuenta con un directorio raiz",
						"Por favor ingrese una carpeta para que el programa pueda "
								+" desplegar sus ficheros. En caso de windows se recomienda"
								+ " la direcci�n (C:/td/persistencia/) en caso de linux (home/td/persistencia) ", AlertType.ERROR);
				loggingController.obtenerRutaPersistencia();


			}


			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	public void setDirectorioRaiz() {
		int seleccion=JFileChooser.APPROVE_OPTION;
		boolean procesoExitoso=false;
		while(!procesoExitoso ){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(2);
			//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
			seleccion=fileChooser.showOpenDialog(new Component() {
			});

			//Si el usuario, pincha en aceptar
			if(seleccion==JFileChooser.APPROVE_OPTION){

				//Seleccionamos el fichero
				File fichero=fileChooser.getSelectedFile();
				if(fichero!=null)
				{
					if(fichero.isDirectory())
					{
						procesoExitoso=true;
						rutaRaiz=fichero.getAbsolutePath();
						System.out.println(rutaRaiz);
					}
				}
			}

		}
	}

	public void cargarVistaAdmin() {
		try {
			FXMLLoader loaderAdmin = new FXMLLoader();
			loaderAdmin.setLocation(Main.class.getResource("view/adminView.fxml"));

			BorderPane rootLayout = (BorderPane)loaderAdmin.load();
			AdminController adminController = loaderAdmin.getController();
			adminController.setAplicacion(this);


			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void cargarVistaEstudiante() {
		try {
			FXMLLoader loaderEstudiante = new FXMLLoader();
			loaderEstudiante.setLocation(Main.class.getResource("view/estudianteView.fxml"));

			BorderPane rootLayout = (BorderPane)loaderEstudiante.load();
			EstudianteController estudianteController = loaderEstudiante.getController();
			estudianteController.setAplicacion(this);


			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cargarVistaInstructor() {
	try {
			FXMLLoader loaderInstructor = new FXMLLoader();
			loaderInstructor.setLocation(Main.class.getResource("view/instructorView.fxml"));

			BorderPane rootLayout = (BorderPane)loaderInstructor.load();
			InstructorController instructorController = loaderInstructor.getController();
			instructorController.setAplicacion(this);


			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public String getRutaPersistencia() {
		return rutaPersistencia;
	}

	public void setRutaPersistencia(String rutaPersistencia) {
		this.rutaPersistencia = rutaPersistencia;
	}

	public String getRutaRaiz() {
		return rutaRaiz;
	}

	public void setRutaRaiz(String rutaRaiz) {
		this.rutaRaiz = rutaRaiz;
	}

	public String getRutaRespaldo() {
		return rutaRespaldo;
	}

	public void setRutaRespaldo(String rutaRespaldo) {
		this.rutaRespaldo = rutaRespaldo;
	}

	public String getRutaArchivos() {
		return rutaArchivos;
	}

	public void setRutaArchivos(String rutaArchivos) {
		this.rutaArchivos = rutaArchivos;
	}

	public String getRutaLog() {
		return rutaLog;
	}

	public void setRutaLog(String rutaLog) {
		this.rutaLog = rutaLog;
	}

	public Bienestar getBienestar() {
		return bienestar;
	}

	public void setBienestar(Bienestar bienestar) {
		this.bienestar = bienestar;
	}

	public ArrayList<Estudiante> obtenerEstudiantes() {
		ArrayList<Estudiante> listaEstudiantes=new ArrayList<>();
		listaEstudiantes=bienestar.getEstudiantes();
		return listaEstudiantes;
	}

	public ArrayList<Instructor> obtenerInstructores() {
		ArrayList<Instructor> listaInstructores=new ArrayList<>();
		listaInstructores=bienestar.getInstructores();
		return listaInstructores;

	}
	public ArrayList<Credito> obtenerCreditos() {
		ArrayList<Credito> listaCreditos=new ArrayList<>();
		listaCreditos=bienestar.getListaCreditos();
		return listaCreditos;

	}



	public void quemarDatos() {
		bienestar.quemarDatos();

	}

	public boolean verificarIDEstudiante(String iD) {
		return bienestar.verificarIDEstudiante(iD);

	}
	public boolean verificarIDInstructor(String iD) {
		return bienestar.verificarIDInstructor(iD);
	}

	public boolean verificarCorreoEstudiante(String correo) {
		return bienestar.verificarCorreoEstudiante(correo);
	}

	public boolean verificarCorreoInstructor(String correo) {
		return bienestar.verificarCorreoInstructor(correo);
	}
	public boolean validarNombreCredito(String nombre) {
		return bienestar.verificarNombreCredito(nombre);
	}
	public Estudiante crearEstudiante(String nombre, String iD, String correo, String contrasenia) {
		return bienestar.crearEstudiante(nombre, iD, correo, contrasenia);
	}

	public Instructor crearInstructor(String nombre, String iD, String correo, String contrasenia) {
		return bienestar.crearInstructor(nombre, iD, correo, contrasenia);
	}
	public Deportivo crearDeportivo(String nombre, int cuposDisponibles, double costoDouble, Horario horarioAux,
			Lugar lugarAux, EAsistenciaMinima asistenciaMinAux, String tipo) {
		return bienestar.crearDeportivo(nombre, cuposDisponibles, costoDouble,horarioAux,lugarAux,asistenciaMinAux, tipo);
	}
	public Cultural crearCultural(String nombre, int cuposDisponiblesInt, double costoDouble, Horario horarioAux,
			Lugar lugarAux, String tipoCredito) {
		return bienestar.crearCultural(nombre, cuposDisponiblesInt, costoDouble,horarioAux,lugarAux, tipoCredito);

	}

	public Academico crearAcademico(String nombre, int cuposDisponiblesInt, double costoDouble, Horario horarioAux,
			Lugar lugarAux, String tipoCredito, double notaDouble, EArea area) {
		return bienestar.crearAcademico(nombre, cuposDisponiblesInt, costoDouble, horarioAux, lugarAux, tipoCredito, notaDouble, area);
	}

	public boolean borrarEstudiante(Estudiante estudianteSeleccionado) {
		return bienestar.borrarEstudiante(estudianteSeleccionado);
	}

	public boolean borrarInstructor(Instructor instructorSeleccionado) {
		return bienestar.borrarInstructor(instructorSeleccionado);
	}

	public boolean borrarCredito(Credito creditoSeleccionado) {
		return bienestar.borrarCredito(creditoSeleccionado);
	}
	public boolean actualizarEstudiante(Estudiante estudianteAux, Estudiante estudianteSeleccionado) {
		return bienestar.actualizarEstudiante(estudianteAux,estudianteSeleccionado);

	}
	public boolean actualizarInstructor(Instructor instructorAux, Instructor instructorSeleccionado) {
		return bienestar.actualizarInstructor(instructorAux, instructorSeleccionado);
	}



	public boolean actualizarCredito(Credito creditoAux, Credito creditoSeleccionado) {
		return bienestar.actualizarCredito(creditoAux, creditoSeleccionado);
	}



	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle      (titulo);
		alert.setHeaderText (header);
		alert.setContentText(contenido);
		alert.showAndWait   ();
	}

	public void crearDirectorios() {
		if(rutaRaiz!=null)
		{
			Persistencia.crearCarpeta(rutaRaiz, "persistencia");
			rutaPersistencia=rutaRaiz+"/persistencia";
			Persistencia.crearCarpeta(rutaPersistencia,"respaldo");
			rutaRespaldo=rutaPersistencia+"/respaldo";
			Persistencia.crearCarpeta(rutaPersistencia, "archivos");
			rutaArchivos=rutaPersistencia+"/archivos";
			Persistencia.crearCarpeta(rutaPersistencia, "log");
			rutaLog=rutaPersistencia+"/log";
		}else{
			mostrarMensaje("ERROR1", "No hay ruta de directorio","Por favor ingrese una ruta", AlertType.ERROR);
			setDirectorioRaiz();
		}


	}





}
