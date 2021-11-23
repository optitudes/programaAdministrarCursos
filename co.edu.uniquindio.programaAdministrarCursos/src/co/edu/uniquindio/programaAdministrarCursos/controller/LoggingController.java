package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import co.edu.uniquindio.programaAdministrarCursos.model.AdminHilos;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


public class LoggingController implements Initializable{
	Main main;
	AdminHilos adminHilos;

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
    private PasswordField txtClaveLogin;

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
		adminHilos=new AdminHilos(main);

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
		if(main.getRutaRaiz().isEmpty())
		{	main.mostrarMensaje("Atención!","El programa no cuenta con un directorio raiz",
						"Por favor ingrese una carpeta para que el programa pueda "
								+" desplegar sus ficheros. En caso de windows se recomienda"
								+ " la dirección (C:/td/persistencia/) en caso de linux (home/td/persistencia) ", AlertType.ERROR);

			obtenerRutaPersistencia();
		}else{
			if(rBtnAdmin.isSelected())
				logearAdmin();
			if(rBtnEstudiante.isSelected())
				logearEstudiante();
			if(rBtnInstructor.isSelected())
				logearInstructor();
		}

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
		adminHilos.startHiloLogger(mensaje, tipo);

	}


	public void obtenerRutaPersistencia() {
		adminHilos.startHiloObtenerRutaPersistencia();


	}


}
