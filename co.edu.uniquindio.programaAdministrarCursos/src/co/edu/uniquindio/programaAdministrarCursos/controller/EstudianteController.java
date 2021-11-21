package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.hilos.Log;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.fxml.Initializable;

public class EstudianteController implements Initializable{

	Main main;
	Estudiante estudianteLoggeado=new Estudiante("sebas", "1002", "sebas@bienestar.con", "1234");
	Log loggerEstudiante;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblDia;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label labelUsuario;

    @FXML
    private Label lblHora;

    @FXML
    void cerrarSesionAction(ActionEvent event) {
    	registrarAccion("cierre de sesion estudiante :"+estudianteLoggeado.getName(),Level.INFO );
    	main.mostrarVentanaLogging();

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void setAplicacion(Main mainAux) {
		main=mainAux;
		
	}
public void registrarAccion(String mensaje, Level tipo){
	loggerEstudiante= new Log(mensaje,tipo);
	loggerEstudiante.hilo.start();

}
}
