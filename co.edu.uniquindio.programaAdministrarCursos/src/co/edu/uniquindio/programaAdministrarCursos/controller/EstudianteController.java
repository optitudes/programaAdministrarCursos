package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import co.edu.uniquindio.programaAdministrarCursos.model.AdminHilos;
import co.edu.uniquindio.programaAdministrarCursos.model.Credito;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

public class EstudianteController implements Initializable{

	Main main;
	Estudiante estudianteLoggeado=new Estudiante("sebas", "1002", "sebas@bienestar.con", "1234");
	AdminHilos adminHilos;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Credito,String> columnHorarioCredito;

    @FXML
    private TableColumn<Credito, String> columnCuposDisponiblesCredito;

    @FXML
    private Label lblDia;

    @FXML
    private Button btnEnviarMensaje;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private TableColumn<Credito,String> columnLugarRegistrado;

    @FXML
    private TableColumn<Credito,String> columnNombreCreditoRegistrado;

    @FXML
    private TableColumn<Credito, String> columnNombreCredito;

    @FXML
    private TextField txtMensaje;

    @FXML
    private Button btnRegistrarCredito;

    @FXML
    private TableView<Credito> tableCreditosRegistrados;

    @FXML
    private TableColumn<Credito, String> columnHorarioRegistrado;

    @FXML
    private TableColumn<Credito, String> columnLugarCredito;

    @FXML
    private TextArea textAreaChat;

    @FXML
    private Label labelUsuario;

    @FXML
    private TableView<Credito> tableCreditos;

    @FXML
    private Label lblHora;

    @FXML
    private TextArea textAreaHorarioRegistrado;

    @FXML
    private TableColumn<Credito, String> columnCuposRegistradosCredito;

    @FXML
    void registrarCreditoAction(ActionEvent event) {

    }

    @FXML
    void enviarMensajeAction(ActionEvent event) {

    }

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
		adminHilos= new AdminHilos(mainAux,this);
		
	}
public void registrarAccion(String mensaje, Level tipo){
	adminHilos.startHiloLogger(mensaje, tipo);
	
}
}
