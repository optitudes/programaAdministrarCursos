package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InstructorController implements Initializable{
	Main main;
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void setAplicacion(Main main2) {
		main=main2;
		
	}

}
