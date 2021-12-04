package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.ClienteMain;
import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import co.edu.uniquindio.programaAdministrarCursos.model.Instructor;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InstructorController implements Initializable{
	ClienteMain mainCliente;
	Instructor instructorLoggeado=new Instructor("Diego", "103","dig@instru.com", "1234");
	HiloLog loggerInstructor;
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
    	registrarAccion("cierre sesion instructor "+instructorLoggeado.getName(), Level.INFO);
    	mainCliente.mostrarVentanaLogging();
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	public void setAplicacion(ClienteMain clienteMain) {
		main=clienteMain;

	}
public void registrarAccion(String mensaje, Level tipo){
	loggerInstructor= new HiloLog(mensaje,tipo);
	loggerInstructor.hilo.start();

}
}
