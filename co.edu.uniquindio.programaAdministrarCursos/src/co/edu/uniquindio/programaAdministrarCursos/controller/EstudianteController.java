package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programaAdministrarCursos.Main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.fxml.Initializable;

public class EstudianteController implements Initializable{

	Main main;
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
    	main.mostrarVentanaLogging();

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void setAplicacion(Main mainAux) {
		main=mainAux;
		
	}

}
