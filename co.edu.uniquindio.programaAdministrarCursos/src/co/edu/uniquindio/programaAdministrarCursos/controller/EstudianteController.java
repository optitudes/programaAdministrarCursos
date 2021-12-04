package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.ClienteMain;
import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import co.edu.uniquindio.programaAdministrarCursos.model.AdminHilos;
import co.edu.uniquindio.programaAdministrarCursos.model.Credito;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

public class EstudianteController implements Initializable{

	ClienteMain mainCliente;
	Estudiante estudianteLoggeado;
	AdminHilos adminHilos;
	ObservableList<Credito> listaCreditosData = FXCollections.observableArrayList();
	ObservableList<Credito> listaCreditosRegistradosData = FXCollections.observableArrayList();

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
    	mainCliente.mostrarVentanaLogging();

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblDia.setText("Dia :"+LocalDate.now(Clock.systemDefaultZone ()));
		lblHora.setText("Hora :"+LocalTime.now());

		this.columnNombreCredito.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCuposDisponiblesCredito.setCellValueFactory(new PropertyValueFactory<>("cuposDisponibles"));
		this.columnCuposRegistradosCredito.setCellValueFactory(new PropertyValueFactory<>("cuposRegistrados"));
		this.columnLugarCredito.setCellValueFactory(new PropertyValueFactory<>("lugar"));
		this.columnHorarioCredito.setCellValueFactory(new PropertyValueFactory<>("horario"));


		this.columnNombreCreditoRegistrado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnLugarRegistrado.setCellValueFactory(new PropertyValueFactory<>("lugar"));
		this.columnHorarioRegistrado.setCellValueFactory(new PropertyValueFactory<>("horario"));


	}
	public void setDatos(ClienteMain clienteMain,Estudiante estudianteAux) {
		mainCliente=clienteMain;
		this.estudianteLoggeado=estudianteAux;
		adminHilos= new AdminHilos(clienteMain,this);

		labelUsuario.setText("Usuario :"+estudianteLoggeado.getName());

		tableCreditos.getItems().clear();
		tableCreditosRegistrados.getItems().clear();

		tableCreditos.setItems(getlistaCreditosData());

	}
private ObservableList<Credito> getlistaCreditosData() {
		listaCreditosData.clear();
		listaCreditosData.addAll(mainCliente.obtenerCreditos());
		return listaCreditosData;
	}

public void registrarAccion(String mensaje, Level tipo){
	adminHilos.startHiloLogger(mensaje, tipo);

}

}
