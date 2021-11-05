package co.edu.uniquindio.programaAdministrarCursos;
	
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.programaAdministrarCursos.controller.AdminController;
import co.edu.uniquindio.programaAdministrarCursos.controller.EstudianteController;
import co.edu.uniquindio.programaAdministrarCursos.controller.InstructorController;
import co.edu.uniquindio.programaAdministrarCursos.controller.LoggingController;
import co.edu.uniquindio.programaAdministrarCursos.model.Bienestar;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;
import co.edu.uniquindio.programaAdministrarCursos.model.Instructor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;

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


			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Estudiante crearEstudiante(String nombre, String iD, String correo, String contrasenia) {
		return bienestar.crearEstudiante(nombre, iD, correo, contrasenia);
	}
	
	public Instructor crearInstructor(String nombre, String iD, String correo, String contrasenia) {
		return bienestar.crearInstructor(nombre, iD, correo, contrasenia);
	}


	public boolean borrarEstudiante(Estudiante estudianteSeleccionado) {
		return bienestar.borrarEstudiante(estudianteSeleccionado);
	}
	
	public boolean borrarInstructor(Instructor instructorSeleccionado) {
		return bienestar.borrarInstructor(instructorSeleccionado);
	}


	public boolean actualizarEstudiante(Estudiante estudianteAux, Estudiante estudianteSeleccionado) {
		return bienestar.actualizarEstudiante(estudianteAux,estudianteSeleccionado);
		
	}
	public boolean actualizarInstructor(Instructor instructorAux, Instructor instructorSeleccionado) {
		return bienestar.actualizarInstructor(instructorAux, instructorSeleccionado);
	}



	
	

	
	

	

	

}
