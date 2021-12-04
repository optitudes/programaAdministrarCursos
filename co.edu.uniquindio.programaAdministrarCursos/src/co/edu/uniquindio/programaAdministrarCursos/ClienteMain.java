package co.edu.uniquindio.programaAdministrarCursos;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.programaAdministrarCursos.controller.AdminController;
import co.edu.uniquindio.programaAdministrarCursos.controller.EstudianteController;
import co.edu.uniquindio.programaAdministrarCursos.controller.InstructorController;
import co.edu.uniquindio.programaAdministrarCursos.controller.LoggingController;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClienteMain extends Application{
	private Stage primaryStage;


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
			loader.setLocation(ClienteMain.class.getResource("view/loggingView.fxml"));

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
		loaderAdmin.setLocation(ClienteMain.class.getResource("view/adminView.fxml"));

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
public void cargarVistaEstudiante(Estudiante estudianteAux) {
	try {
		FXMLLoader loaderEstudiante = new FXMLLoader();
		loaderEstudiante.setLocation(Main.class.getResource("view/estudianteView.fxml"));

		BorderPane rootLayout = (BorderPane)loaderEstudiante.load();
		EstudianteController estudianteController = loaderEstudiante.getController();
		estudianteController.setDatos(this,estudianteAux);

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


}
