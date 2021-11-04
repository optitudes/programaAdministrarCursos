package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class LoggingController implements Initializable{
	Main main;
	private static final Logger LOGGER = Logger.getLogger(LoggingController.class.getName());
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
	void accederAction(ActionEvent event) {
		if(correoadmin.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
			registrarLoggin();
			main.cargarVistaAdmin();}
		if(correoEstudiante.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
			registrarLoggin();
			main.cargarVistaEstudiante();}
		if(correoInstructor.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
			registrarLoggin();
			main.cargarVistaInstructor();}
	}

	private void registrarLoggin() {
		FileHandler archivo;

		try {
			archivo= new FileHandler("src/resources/loggers/inicioSesion.txt",true);//si es true no sobreescribe
			archivo.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(archivo);

			LOGGER.log(Level.INFO, "inicio de sesion user :"+txtCorreoLogin.getText());

		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	}

