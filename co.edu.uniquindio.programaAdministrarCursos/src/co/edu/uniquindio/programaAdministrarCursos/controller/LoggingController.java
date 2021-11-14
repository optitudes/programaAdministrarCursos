package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.model.Log;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class LoggingController implements Initializable{
	Main main;
	Log  logger;

	String correoadmin="admin";
	String correoEstudiante="estudiante";
	String correoInstructor="instructor";
	String clave="1234";

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private RadioButton rBtnAdmin;

	@FXML
	private TextField txtClaveLogin;

	@FXML
	private Button btnAccederLogin;

	@FXML
	private RadioButton rBtnInstructor;

	@FXML
	private TextField txtCorreoLogin;

	@FXML
	private RadioButton rBtnEstudiante;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setAplicacion(Main mainAux) {
		main= mainAux;

	}

	@FXML
	void BtnElegirAdmin(ActionEvent event) {
		limpiarLogging();
		rBtnAdmin.setSelected(true);
		rBtnEstudiante.setSelected(false);
		rBtnInstructor.setSelected(false);
	}

	@FXML
	void BtnElegirInstructor(ActionEvent event) {
		limpiarLogging();
		rBtnInstructor.setSelected(true);
		rBtnAdmin.setSelected(false);
		rBtnEstudiante.setSelected(false);
	}

	@FXML
	void BtnElegirEstudiante(ActionEvent event) {
		limpiarLogging();
		rBtnEstudiante.setSelected(true);
		rBtnAdmin.setSelected(false);
		rBtnInstructor.setSelected(false);
	}

	@FXML
	void accederAction(ActionEvent event) {
		if(rBtnAdmin.isSelected())
			logearAdmin();
		if(rBtnEstudiante.isSelected())
			logearEstudiante();
		if(rBtnInstructor.isSelected())
			logearInstructor();
	}

	private void logearInstructor() {
	if(correoInstructor.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
			registrarAccion("Inicio de sesion instructor",Level.INFO);
			main.cargarVistaInstructor();}
		
	}

	private void logearEstudiante() {
	if(correoEstudiante.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
			registrarAccion("Inicio de sesion estudiante",Level.INFO );
			main.cargarVistaEstudiante();}
		
	}

	private void logearAdmin() {
	if(correoadmin.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
			registrarAccion("Inicio de sesion admin", Level.INFO);
			main.cargarVistaAdmin();}
		
	}

	private void limpiarLogging() {
		txtCorreoLogin.setText("");
		txtClaveLogin.setText("");

	}

	public void registrarAccion(String mensaje, Level tipo){
		logger= new Log(mensaje,tipo);
		logger.hilo.start();

	}
}
