package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.model.Credito;
import co.edu.uniquindio.programaAdministrarCursos.model.EDia;
import co.edu.uniquindio.programaAdministrarCursos.model.EHorario;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;
import co.edu.uniquindio.programaAdministrarCursos.model.Instructor;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class AdminController implements Initializable{

	Main main;
	private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());

	ObservableList<Estudiante> listaEstudiantesData = FXCollections.observableArrayList();	
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Estudiante> tableEstudiantes;

    @FXML
    private Button btnCrearInstructor;

    @FXML
    private Button btnActualizarInstructor;

    @FXML
    private TextField txtIdInstructor;

    @FXML
    private Button btnBorrarEstudiante;

    @FXML
    private TextField txtCorreoInstructor;

    @FXML
    private TextField txtContraseniaEstudiante;

    @FXML
    private TextArea textAreaAcademicosInfo;

    @FXML
    private TextField txtBuscarEstudiante;

    @FXML
    private TextField txtCuposDisponiblesCredito;

    @FXML
    private ComboBox<String> comboBoxAuxCurso;

    @FXML
    private RadioButton rBtnAcademico;

    @FXML
    private Button btnBorrarInstructor;

    @FXML
    private TableColumn<Credito, String> columnCuposRegistradosCurso1;

    @FXML
    private TableColumn<Credito, String> columnNombreCurso;

    @FXML
    private TableColumn<Estudiante, String> columnNombreEstudiante;

    @FXML
    private TextField txtBuscarInstructor;

    @FXML
    private Label labelAuxCurso;

    @FXML
    private TableColumn<Estudiante, String> columnIdEstudiante;

    @FXML
    private ComboBox<EHorario> comboBoxHorario2;

    @FXML
    private ComboBox<EHorario> comboBoxDia2;

    @FXML
    private TableColumn<Instructor, String> columnNombreIntructor;

    @FXML
    private ComboBox<EHorario> comboBoxHorario1;

    @FXML
    private ComboBox<EDia> comboBoxDia1;

    @FXML
    private TextField txtNombreCredito;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private TableView<Instructor> tableInstructor;

    @FXML
    private Button btnActualizarEstudiante;

    @FXML
    private RadioButton rBtnDeportivo;

    @FXML
    private Button btnCrearEstudiante;

    @FXML
    private Label lblUserAdmin;

    @FXML
    private TextField txtBloqueCurso;

    @FXML
    private TableColumn<Instructor,String> columnIdIntructor;

    @FXML
    private Button btnBorrarCurso;

    @FXML
    private TextArea textAreaCulturalesInfo;

    @FXML
    private TextField txtPisoCredito;

    @FXML
    private TextField txtNombreInstructor;

    @FXML
    private RadioButton rBtnCultural;

    @FXML
    private Button btnActualizarCurso;
    @FXML
    private Button btnNuevoEstudiante;
    @FXML
    private TextArea textAreaDeportivosInfo;

    @FXML
    private TextField txtNumSalonCurso;

    @FXML
    private Button btnCrearCurso;

    @FXML
    private TableColumn<Credito, String> columnCupoDisponiblesCurso;

    @FXML
    private TextField txtBuscarCurso;

    @FXML
    private TextField txtCorreoEstudiante;

    @FXML
    private TextField txtIdEstudiante;

    @FXML
    private TextField txtContraseniaInstructor;

    @FXML
    private TextField txtNombreEstudiante;

    @FXML
    private TableColumn<Credito, String> columnCuposRegistradosCurso;

    @FXML
    private TextField txtCostoCurso;

    @FXML
    void crearEstudianteAction(ActionEvent event) {
    	crearEstudiante();

    }
   
	@FXML
    void nuevoEstudianteAction(ActionEvent event) {
    	nuevoEstudiante();
    }

   
	@FXML
    void borrarEstudiante(ActionEvent event) {

    }

    @FXML
    void actualizarEstudiante(ActionEvent event) {

    }

    @FXML
    void crearInstructor(ActionEvent event) {

    }

    @FXML
    void borrarInstructor(ActionEvent event) {

    }

    @FXML
    void actualizarInstructor(ActionEvent event) {

    }

    @FXML
    void crearCurso(ActionEvent event) {

    }

    @FXML
    void borrarCurso(ActionEvent event) {

    }

    @FXML
    void actualizarCurso(ActionEvent event) {

    }

    @FXML
    void cerrarSesionActrion(ActionEvent event) {
    	cerrarSesionLog();
    	main.mostrarVentanaLogging();

    }
	private void cerrarSesionLog() {
		FileHandler archivo;

		try {
			archivo= new FileHandler("src/resources/loggers/cierreSesionAdmin.txt",true);//si es true no sobreescribe
			archivo.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(archivo);

			LOGGER.log(Level.INFO, "cierre de sesion user :admin");

		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.columnNombreEstudiante.setCellValueFactory(new PropertyValueFactory<>("name"));
    	this.columnIdEstudiante.setCellValueFactory(new PropertyValueFactory<>("iD"));
	}

	public void setAplicacion(Main mainAux) {
		this.main=mainAux;
		main.quemarDatos();
    	tableEstudiantes.getItems().clear();
    	tableEstudiantes.setItems(getListaEstudiantesData());


	}
 private void nuevoEstudiante() {
	 
	 txtNombreEstudiante.setText     ("Ingrese el nombre del nuevo estudiante");
	 txtIdEstudiante.setText         ("Ingrese el id del nuevo estudiante");
	 txtCorreoEstudiante.setText     ("Ingrese el correo del nuevo estudiante");
	 txtContraseniaEstudiante.setText("Ingrese la contraseña del nuevo estudiante");
		
 } 
 private void crearEstudiante() {
	String nombre=txtNombreEstudiante.getText();
	String iD=txtIdEstudiante.getText();
	String correo=txtCorreoEstudiante.getText();
	String contrasenia=txtContraseniaEstudiante.getText();
	

 }
 private ObservableList<Estudiante> getListaEstudiantesData() {

	 listaEstudiantesData.addAll(main.obtenerEstudiantes());

	 return listaEstudiantesData;
 }
}
