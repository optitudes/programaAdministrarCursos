package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import co.edu.uniquindio.programaAdministrarCursos.ClienteMain;
import co.edu.uniquindio.programaAdministrarCursos.exception.UsuarioNoEncontradoException;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import co.edu.uniquindio.programaAdministrarCursos.model.AccionEnum;
import co.edu.uniquindio.programaAdministrarCursos.model.AdminHilos;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;
import co.edu.uniquindio.programaAdministrarCursos.model.PaqueteDatos;
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
	ClienteMain main;
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


	public void setAplicacion(ClienteMain mainAux) {
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

			try {
				if(rBtnAdmin.isSelected())
					logearAdmin();
				if(rBtnEstudiante.isSelected())
					logearEstudiante();
				if(rBtnInstructor.isSelected())
					logearInstructor();

			} catch (UsuarioNoEncontradoException e) {
//				registrarAccion("Error en el logging [Usuario no encontrado] ="+e.getMessage(), Level.SEVERE);
				}

	}

	private void logearInstructor() {
	if(correoInstructor.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
//			registrarAccion("Inicio de sesion instructor",Level.INFO);
			main.cargarVistaInstructor();}

	}

	private void logearEstudiante() throws UsuarioNoEncontradoException {
		Estudiante estudianteAux;
		estudianteAux=validarEstudiante(txtCorreoLogin.getText(),txtClaveLogin.getText());
//		registrarAccion("Inicio de sesion estudiante "+txtCorreoLogin.getText(),Level.INFO );
//		main.cargarVistaEstudiante(estudianteAux);
		}

	public Estudiante validarEstudiante(String correo, String clave) {
		PaqueteDatos paqueteLogin;
		ArrayList<String> listaDatos=new ArrayList<>();
		listaDatos.add(correo);
		listaDatos.add(clave);
		paqueteLogin= new PaqueteDatos(AccionEnum.LOGGEAR_ESTUDIANTE,listaDatos);
		return validarEstudiante(paqueteLogin);
	}


	private Estudiante validarEstudiante(PaqueteDatos paqueteLogin) {

		try {
			Socket socket= new Socket("localhost",8081);
			ObjectOutputStream flujoSalida= new ObjectOutputStream(socket.getOutputStream());
			flujoSalida.writeObject(paqueteLogin);
			JOptionPane.showMessageDialog(null, "datos enviados");
			flujoSalida.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	private void logearAdmin() {
		if(correoadmin.equalsIgnoreCase(txtCorreoLogin.getText()) && clave.equalsIgnoreCase(txtClaveLogin.getText())){
//			registrarAccion("Inicio de sesion admin", Level.INFO);
			main.cargarVistaAdmin();}

	}

	private void limpiarLogging() {
		txtCorreoLogin.setText("");
		txtClaveLogin.setText("");

	}

//	public void registrarAccion(String mensaje, Level tipo){
//		adminHilos.startHiloLogger(mensaje, tipo);
//
//	}
//
//
//	public void obtenerRutaPersistencia() {
//		adminHilos.startHiloObtenerRutaPersistencia();
//
//
//	}


}
