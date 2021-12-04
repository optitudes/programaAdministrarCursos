package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.ClienteMain;
import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.exception.DatosInvalidosException;
import co.edu.uniquindio.programaAdministrarCursos.exception.EstudianteNoCreadoException;
import co.edu.uniquindio.programaAdministrarCursos.exception.InstructorNoCreadoException;
import co.edu.uniquindio.programaAdministrarCursos.hilos.HiloLog;
import co.edu.uniquindio.programaAdministrarCursos.model.Academico;
import co.edu.uniquindio.programaAdministrarCursos.model.AccionEnum;
import co.edu.uniquindio.programaAdministrarCursos.model.Admin;
import co.edu.uniquindio.programaAdministrarCursos.model.AdminHilos;
import co.edu.uniquindio.programaAdministrarCursos.model.Credito;
import co.edu.uniquindio.programaAdministrarCursos.model.Cultural;
import co.edu.uniquindio.programaAdministrarCursos.model.Deportivo;
import co.edu.uniquindio.programaAdministrarCursos.model.EArea;
import co.edu.uniquindio.programaAdministrarCursos.model.EAsistenciaMinima;
import co.edu.uniquindio.programaAdministrarCursos.model.EDia;
import co.edu.uniquindio.programaAdministrarCursos.model.EHorario;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;
import co.edu.uniquindio.programaAdministrarCursos.model.Horario;
import co.edu.uniquindio.programaAdministrarCursos.model.Instructor;
import co.edu.uniquindio.programaAdministrarCursos.model.Lugar;
import co.edu.uniquindio.programaAdministrarCursos.model.PaqueteDatos;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class AdminController implements Initializable{

	Admin admin= new Admin("aizen","1991" , "aizen@lord.com", "1001");
	HiloLog  loggerAdmin;
	AdminHilos administradorHilos;

	ClienteMain mainCliente;

	ObservableList<Estudiante> listaEstudiantesData = FXCollections.observableArrayList();
	ObservableList<Instructor> listaInstructoresData = FXCollections.observableArrayList();
	ObservableList<Credito> listaCreditosData = FXCollections.observableArrayList();


	Estudiante estudianteSeleccionado;
	Instructor instructorSeleccionado;
	Credito    creditoSeleccionado;

	FilteredList<Estudiante> filteredData;
	FilteredList<Instructor> filteredDataInstructor;


	@FXML
	private Label lblFecha;
	@FXML
	private Label lblHora;


	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

    @FXML
    private Label lblUserAdmin;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCrearBackup;

    @FXML
    private Button btnCargarBackup;
    @FXML
    private TextField txtNombreEstudiante;

    @FXML
    private TextField txtIdEstudiante;

    @FXML
    private TextField txtCorreoEstudiante;

    @FXML
    private TextField txtContraseniaEstudiante;

    @FXML
    private Button btnCrearEstudiante;

    @FXML
    private Button btnBorrarEstudiante;

    @FXML
    private Button btnActualizarEstudiante;

    @FXML
    private TableView<Estudiante> tableEstudiantes;

    @FXML
    private TableColumn<Estudiante, String> columnNombreEstudiante;

    @FXML
    private TableColumn<Estudiante, String> columnIdEstudiante;

    @FXML
    private TextField txtBuscarEstudiante;

    @FXML
    private Button btnNuevoEstudiante;

    @FXML
    private TextField txtNombreInstructor;

    @FXML
    private TextField txtIdInstructor;

    @FXML
    private TextField txtCorreoInstructor;

    @FXML
    private TextField txtContraseniaInstructor;

    @FXML
    private Button btnCrearInstructor;


    @FXML
    private Button btnCargarDatosEstudiante;
    @FXML
    private Button btnGuardarDatosEstudiante;
    @FXML
    private Button btnCargarDatosInstructor;
    @FXML
    private Button btnGuardarDatosInstructor;
    @FXML
    private Button btnCargarDatosCredito;
    @FXML
    private Button btnGuardarDatosCredito;

    @FXML
    private Button btnBorrarInstructor;

    @FXML
    private Button btnActualizarInstructor;

    @FXML
    private TableView<Instructor> tableInstructor;

    @FXML
    private TableColumn<Instructor, String> columnNombreIntructor;

    @FXML
    private TableColumn<Instructor, String> columnIdIntructor;

    @FXML
    private TextField txtBuscarInstructor;

    @FXML
    private Button btnNuevoInstructor;

    @FXML
    private TextField txtCuposDisponiblesCredito;

    @FXML
    private TextField txtBloqueCurso;

    @FXML
    private TextField txtCostoCurso;

    @FXML
    private Button btnCrearCurso;

    @FXML
    private Button btnBorrarCurso;

    @FXML
    private Button btnActualizarCurso;

    @FXML
    private TableView<Credito> tableCreditos;

    @FXML
    private TableColumn<Credito, String> columnNombreCurso;

    @FXML
    private TableColumn<Credito, String> columnCupoDisponiblesCurso;

    @FXML
    private TableColumn<Credito, String> columnCuposRegistradosCurso;

    @FXML
    private TableColumn<Credito, String> columnTipoCredito;

    @FXML
    private TextField txtBuscarCurso;

    @FXML
    private ComboBox<EHorario> comboBoxHorario1;

    @FXML
    private ComboBox<EHorario> comboBoxHorario2;

    @FXML
    private ComboBox<EDia> comboBoxDia1;

    @FXML
    private ComboBox<EDia> comboBoxDia2;

    @FXML
    private RadioButton rBtnDeportivo;

    @FXML
    private RadioButton rBtnAcademico;

    @FXML
    private RadioButton rBtnCultural;

    @FXML
    private TextField txtPisoCredito;

    @FXML
    private TextField txtNumSalonCurso;

    @FXML
    private TextField txtNombreCredito;

    @FXML
    private Label labelAuxCurso;

    @FXML
    private ComboBox<String> comboBoxAuxCurso;

    @FXML
    private ComboBox<EArea> comboBoxAuxCredito2;

    @FXML
    private Label lblAux2;

    @FXML
    private TextArea textAreaAcademicosInfo;

    @FXML
    private TextArea textAreaCulturalesInfo;

    @FXML
    private TextArea textAreaDeportivosInfo;

    @FXML
    void crearEstudianteAction(ActionEvent event) {
//    	crearEstudiante();

    }

    @FXML
    void cargarDatosEstudianteAction(ActionEvent event) {
//    	cargarDatosTXT("estudiante.txt");

    }


	@FXML
    void guardarDatosEstudianteAction(ActionEvent event) {
//    	guardarDatosTXT("estudiante.txt");
    }

	@FXML
    void cargarDatosInstructorAction(ActionEvent event) {
//		cargarDatosTXT("instructor.txt");

    }
    @FXML
    void guardarDatosInstructorAction(ActionEvent event) {
//    	guardarDatosTXT("instructor.txt");

    }
    @FXML
    void cargarDatosCreditoAction(ActionEvent event) {
//    	cargarDatosTXT("credito");
//    	refrescarTablas();

    }
    @FXML
    void guardarDatosCreditoAction(ActionEvent event) {
//    	 guardarDatosTXT("credito");

    }
	@FXML
    void nuevoEstudianteAction(ActionEvent event) {
    	nuevoEstudiante();
    }

	@FXML
    void nuevoInstructorAction(ActionEvent event) {
    	nuevoInstructor();
	}



	@FXML
    void borrarEstudianteAction(ActionEvent event) {
//		borrarEstudiante();
    }



	@FXML
    void actualizarEstudianteAction(ActionEvent event) {
//		actualizarEstudiante();
    }


	@FXML
    void crearInstructor(ActionEvent event) {
//		crearInstructor();

    }



	@FXML
    void borrarInstructor(ActionEvent event) {
//		borrarInstructor();
    }



	@FXML
    void actualizarInstructor(ActionEvent event) {
//		actualizarInstructor();
    }


	@FXML
    void crearCurso(ActionEvent event) {
//		crearCurso();

    }



	@FXML
    void borrarCurso(ActionEvent event) {
//		borrarCreditoAction();

    }



	@FXML
    void actualizarCurso(ActionEvent event) {
//    	actualizarCreditoAction();

	}

	/**
	 * método que permite cargar un backup
	 * @param event
	 */
	@FXML
	void cargarBackupAction(ActionEvent event) {
//		String mensaje="";
//		try {
////			mensaje=mainCliente.cargarDatosBackup();
////			refrescarTablas();
//			administradorHilos.startHiloLogger("Backup "+mensaje+" por admin ["+admin.getName()+"]", Level.INFO);
//		} catch (Exception e) {
//			mostrarMensaje("Error", "Backup no reconocido","Hubo un error al cargar el archivo backup",AlertType.ERROR);
//			administradorHilos.startHiloLogger("Error al cargar datos admin ["+admin.getName()+"]"+"error tipo[Exception]", Level.SEVERE);
//		}
	}

	/**
	 * método que crea un backup
	 * @param event
	 */
	@FXML
	void crearBackupAction(ActionEvent event) {
		hacerBackup();
	}




	@FXML
    void BtnElegirDeportivo(ActionEvent event) {
    	limpiarCreditos();
    	rBtnDeportivo.setSelected(true);
    	rBtnAcademico.setSelected(false);
    	rBtnCultural.setSelected(false);
    	nuevoCredito();
    	nuevoCreditoDeportivo();
    }

	@FXML
    void BtnElegirAcademico(ActionEvent event) {
		limpiarCreditos();
		rBtnAcademico.setSelected(true);
    	rBtnDeportivo.setSelected(false);
    	rBtnCultural.setSelected(false);
    	nuevoCredito();
    	nuevoCreditoAcademico();
    }

	@FXML
    void BtnElegirCultural(ActionEvent event) {
		limpiarCreditos();
		rBtnCultural.setSelected(true);
		rBtnAcademico.setSelected(false);
    	rBtnDeportivo.setSelected(false);
    	nuevoCredito();
    }

    @FXML
    void cerrarSesionActrion(ActionEvent event) {
    	registrarAccion("cierre de sesion admin"+admin.getName(),Level.INFO );
    	hacerBackup();
    	mainCliente.mostrarVentanaLogging();

    }


	private void registrarAccion(String mensaje, Level tipo) {
			ArrayList<Object> listaContenido= new ArrayList<>();
			listaContenido.add(mensaje);
			listaContenido.add(tipo);
			PaqueteDatos paqueteAux= new PaqueteDatos(AccionEnum.REGISTRAR_ACCION, listaContenido);
			administradorHilos.startHiloEnviarPaqueteServer(paqueteAux);

		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		lblFecha.setText(lblFecha.getText()+LocalDate.now(Clock.systemDefaultZone ()));
		lblHora.setText(lblHora.getText()+LocalTime.now());

		ObservableList<EDia> listaDiasData = FXCollections.observableArrayList();
		listaDiasData.addAll(EDia.values());

		ObservableList<EHorario> listaHorarioData = FXCollections.observableArrayList();
		listaHorarioData.addAll(EHorario.values());

		ObservableList<EArea> listaAreaData = FXCollections.observableArrayList();
		listaAreaData.addAll(EArea.values());

		lblUserAdmin.setText(lblUserAdmin.getText()+admin.getName());

		this.columnNombreEstudiante.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.columnIdEstudiante.setCellValueFactory    (new PropertyValueFactory<>("iD"));

		this.columnNombreIntructor.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.columnIdIntructor.setCellValueFactory(new PropertyValueFactory<>("iD"));

		this.columnNombreCurso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCupoDisponiblesCurso.setCellValueFactory(new PropertyValueFactory<>("cuposDisponibles"));
		this.columnCuposRegistradosCurso.setCellValueFactory(new PropertyValueFactory<>("cuposRegistrados"));
		this.columnTipoCredito.setCellValueFactory(new PropertyValueFactory<>("tipo"));

		comboBoxDia1.getItems().addAll(listaDiasData);
		comboBoxDia2.getItems().addAll(listaDiasData);

		comboBoxHorario1.getItems().addAll(listaHorarioData);
		comboBoxHorario2.getItems().addAll(listaHorarioData);

		limpiarCreditos();



		tableEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			estudianteSeleccionado = newSelection;

			mostrarInformacionEstudiante(estudianteSeleccionado);

		});

		tableInstructor.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			instructorSeleccionado = newSelection;

			mostrarInformacionInstructor(instructorSeleccionado);

		});
		tableCreditos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {



			creditoSeleccionado = newSelection;
			mostrarInformacionCredito(creditoSeleccionado);



		});
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
    	filteredData = new FilteredList<>(listaEstudiantesData, p -> true);


    	// 2. Set the filter Predicate whenever the filter changes.
    	txtBuscarEstudiante.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(estudiante-> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (estudiante.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (estudiante.getiD().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

    	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
    	filteredDataInstructor = new FilteredList<>(listaInstructoresData, p -> true);


    	// 2. Set the filter Predicate whenever the filter changes.
    	txtBuscarInstructor.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataInstructor.setPredicate(instructor-> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (instructor.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (instructor.getiD().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
	}


	public void mostrarInformacionCredito(Credito creditoSeleccionado) {
		if(creditoSeleccionado!=null)
		{
			if(creditoSeleccionado instanceof Deportivo)
			{
				mostrarDatosDeportivo(creditoSeleccionado);
			}else{
				if(creditoSeleccionado instanceof Cultural)
				{
					mostrarDatosCultural(creditoSeleccionado);
				}else{
					mostrarDatosAcademico(creditoSeleccionado);
				}
			}
		}
	}
	private void mostrarDatosAcademico(Credito creditoSeleccionado) {
		Academico academicoAux=(Academico) creditoSeleccionado;

		limpiarCreditos();
		rBtnAcademico.setSelected(true);
    	rBtnDeportivo.setSelected(false);
    	rBtnCultural.setSelected(false);
    	mostrarCreditoGeneral(creditoSeleccionado);

    	comboBoxAuxCurso.setVisible(true);
		comboBoxAuxCredito2.setVisible(true);
		labelAuxCurso.setVisible(true);
		lblAux2.setVisible(true);

		lblAux2.setText("Area");
		labelAuxCurso.setText("Homologación");


		comboBoxAuxCredito2.setValue(academicoAux.getArea());

		comboBoxAuxCurso.getItems().clear();
		comboBoxAuxCurso.getItems().addAll("3.0","3.5","4.0");
		comboBoxAuxCurso.setValue(""+academicoAux.getNotaMinima());

	}

	private void mostrarDatosCultural(Credito creditoSeleccionado) {
		limpiarCreditos();
		rBtnCultural.setSelected(true);
		rBtnAcademico.setSelected(false);
    	rBtnDeportivo.setSelected(false);
		mostrarCreditoGeneral(creditoSeleccionado);

	}

	private void mostrarDatosDeportivo(Credito creditoSeleccionado) {

		Deportivo deportivoAux=(Deportivo) creditoSeleccionado;

		limpiarCreditos();
    	rBtnDeportivo.setSelected(true);
    	rBtnAcademico.setSelected(false);
    	rBtnCultural.setSelected(false);
    	mostrarCreditoGeneral(creditoSeleccionado);

    	comboBoxAuxCurso.setVisible(true);
    	labelAuxCurso.setVisible(true);

    	labelAuxCurso.setText("Asistencia Min");
    	comboBoxAuxCurso.getItems().clear();
    	comboBoxAuxCurso.getItems().addAll("SETENTA_PORCIENTO","SETENTA_Y_CINCO_PORCIENTO","OCHENTA_PORCIENTO");
    	comboBoxAuxCurso.setValue(deportivoAux.getAsistenciaMinima()+"");






	}

	private void mostrarCreditoGeneral(Credito creditoSeleccionado) {

		ArrayList<EHorario> listaHorarios=creditoSeleccionado.getHorario().getListaHorarios();
		ArrayList<EDia>     listaDias=creditoSeleccionado.getHorario().getListaDias();

		 txtNombreCredito.setText(creditoSeleccionado.getNombre());
		 txtCuposDisponiblesCredito.setText(""+creditoSeleccionado.getCuposDisponibles());
		 txtBloqueCurso.setText(creditoSeleccionado.getLugar().getBloque());
		 txtPisoCredito.setText(""+creditoSeleccionado.getLugar().getPiso());
		 txtNumSalonCurso.setText(""+creditoSeleccionado.getLugar().getNumSalon());
		 txtCostoCurso.setText(""+creditoSeleccionado.getCosto());

		 if(listaHorarios.size()==2)
		 {
			 comboBoxHorario1.setValue(listaHorarios.get(0));
			 comboBoxHorario2.setValue(listaHorarios.get(1));
		 }else{
			 comboBoxHorario1.setValue(listaHorarios.get(0));
			 comboBoxHorario2.setValue(null);
		 }

		 if(listaDias.size()==2)
		 {
			 comboBoxDia1.setValue(listaDias.get(0));
			 comboBoxDia2.setValue(listaDias.get(1));
		 }else{
			 comboBoxDia1.setValue(listaDias.get(0));
			 comboBoxDia2.setValue(null);
		 }

		 comboBoxAuxCurso.setVisible(false);
		 labelAuxCurso.setVisible(false);
		 lblAux2.setVisible(false);
		 comboBoxAuxCredito2.setVisible(false);

	}

	private void mostrarInformacionEstudiante(Estudiante estudianteSeleccionado) {

		if(estudianteSeleccionado != null){
			txtNombreEstudiante.setText(estudianteSeleccionado.getName());
			txtIdEstudiante.setText(estudianteSeleccionado.getiD());
			txtCorreoEstudiante.setText(estudianteSeleccionado.getEmail());
			txtContraseniaEstudiante.setText(estudianteSeleccionado.getPasword());
		}
	}

	private void mostrarInformacionInstructor(Instructor instructorSeleccionado) {
		if(instructorSeleccionado != null){
			txtNombreInstructor.setText(instructorSeleccionado.getName());
			txtIdInstructor.setText(instructorSeleccionado.getiD());
			txtCorreoInstructor.setText(instructorSeleccionado.getEmail());
			txtContraseniaInstructor.setText(instructorSeleccionado.getPasword());
		}
	}


	public void setAplicacion(ClienteMain clienteMain) {
		this.mainCliente=clienteMain;
		administradorHilos=new AdminHilos(mainCliente,this);
		tableEstudiantes.getItems().clear();
		tableInstructor.getItems().clear();
		tableCreditos.getItems().clear();

//		tableCreditos.setItems(getListaCreditosData());
//		tableEstudiantes.setItems(getListaEstudiantesData());
//		tableInstructor.setItems(getListaInstructoresData());


		// 3. Wrap the FilteredList in a SortedList.
    	SortedList<Estudiante> sortedData = new SortedList<>(filteredData);

    	// 4. Bind the SortedList comparator to the TableView comparator.
    	sortedData.comparatorProperty().bind(tableEstudiantes.comparatorProperty());

    	// 5. Add sorted (and filtered) data to the table.
    	tableEstudiantes.setItems(sortedData);

    	// 3. Wrap the FilteredList in a SortedList.
    	SortedList<Instructor> sortedDataInstructor = new SortedList<>(filteredDataInstructor);

    	// 4. Bind the SortedList comparator to the TableView comparator.
    	sortedDataInstructor.comparatorProperty().bind(tableInstructor.comparatorProperty());

    	// 5. Add sorted (and filtered) data to the table.
    	tableInstructor.setItems(sortedDataInstructor);
	}


	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle      (titulo);
		alert.setHeaderText (header);
		alert.setContentText(contenido);
		alert.showAndWait   ();
	}

	private boolean mostrarMensajeConfirmacion(String mensaje) {
		Alert alert  = new Alert (Alert.AlertType.CONFIRMATION);
		alert.setTitle(null);
		alert.setHeaderText("Confirmacion");
		alert.setContentText(mensaje);
		Optional<ButtonType> action = alert.showAndWait();

		if(action.get() == ButtonType.OK){
			return true;

		}else{
			return false;
		}
	}

	private void limpiarCamposEstudiante() {

		txtNombreEstudiante.setText     ("");
		txtIdEstudiante.setText         ("");
		txtCorreoEstudiante.setText     ("");
		txtContraseniaEstudiante.setText("");
	}

	private void limpiarCamposInstructor() {
		txtNombreInstructor.setText     ("");
		txtIdInstructor.setText         ("");
		txtCorreoInstructor.setText     ("");
		txtContraseniaInstructor.setText("");
	}

	private void limpiarCreditos() {

		txtNombreCredito.setText("");
		txtCuposDisponiblesCredito.setText("");
		txtBloqueCurso.setText("");
		txtPisoCredito.setText("");
		txtNumSalonCurso.setText("");
		txtCostoCurso.setText("");

		comboBoxHorario1.setValue(null);
		comboBoxHorario2.setValue(null);
		comboBoxDia1.setValue(null);
		comboBoxDia2.setValue(null);

		comboBoxAuxCurso.setVisible(false);
		comboBoxAuxCredito2.setVisible(false);
		labelAuxCurso.setVisible(false);
		lblAux2.setVisible(false);
	}

	private void nuevoEstudiante() {

		txtNombreEstudiante.setText     ("Ingrese el nombre del nuevo estudiante");
		txtIdEstudiante.setText         ("Ingrese el id del nuevo estudiante");
		txtCorreoEstudiante.setText     ("Ingrese el correo del nuevo estudiante");
		txtContraseniaEstudiante.setText("Ingrese la contraseña del nuevo estudiante");

	}

	private void nuevoInstructor() {

		txtNombreInstructor.setText     ("Ingrese el nombre del nuevo Instructor");
		txtIdInstructor.setText         ("Ingrese el id del nuevo Instructor");
		txtCorreoInstructor.setText     ("Ingrese el correo del nuevo Instructor");
		txtContraseniaInstructor.setText("Ingrese la contraseña del nuevo Instructor");

	}

	private void nuevoCredito() {

		 txtNombreCredito.setText("Ingrese el nombre del credito");
		 txtCuposDisponiblesCredito.setText("Ingrese la cantidad de cupos disponibles");
		 txtBloqueCurso.setText("Ingrese el bloque del curso");
		 txtPisoCredito.setText("Ingrese el piso del credito");
		 txtNumSalonCurso.setText("Ingrese el numero del salon");
		 txtCostoCurso.setText("Ingrese el costo del curso");

		 comboBoxHorario1.setValue(null);
		 comboBoxHorario2.setValue(null);
		 comboBoxDia1.setValue(null);
		 comboBoxDia2.setValue(null);

		 comboBoxAuxCurso.setVisible(false);
		 labelAuxCurso.setVisible(false);
		 lblAux2.setVisible(false);
		 comboBoxAuxCredito2.setVisible(false);
	}

	private void nuevoCreditoDeportivo() {
		 comboBoxAuxCurso.setVisible(true);
		 labelAuxCurso.setVisible(true);

		 labelAuxCurso.setText("Asistencia Min");
		 comboBoxAuxCurso.getItems().clear();
		 comboBoxAuxCurso.getItems().addAll("SETENTA_PORCIENTO","SETENTA_Y_CINCO_PORCIENTO","OCHENTA_PORCIENTO");
	}

	private void nuevoCreditoAcademico() {
		comboBoxAuxCurso.setVisible(true);
		comboBoxAuxCredito2.setVisible(true);
		labelAuxCurso.setVisible(true);
		lblAux2.setVisible(true);

		lblAux2.setText("Area");
		labelAuxCurso.setText("Homologación");

		ObservableList<EArea> listaAreasData = FXCollections.observableArrayList();
		listaAreasData.addAll(EArea.values());

		comboBoxAuxCredito2.getItems().clear();
		comboBoxAuxCredito2.getItems().addAll(listaAreasData);

		comboBoxAuxCurso.getItems().clear();
		comboBoxAuxCurso.getItems().addAll("3.0","3.5","4.0");
	}
//
//	private void crearEstudiante() {
//
//		String nombre=txtNombreEstudiante.getText();
//		String iD=txtIdEstudiante.getText();
//		String correo=txtCorreoEstudiante.getText();
//		String contrasenia=txtContraseniaEstudiante.getText();
//		try {
//			if(validarDatosEstudiante(nombre,iD,correo,contrasenia)){
//
//				Estudiante estudianteAux=null;
//
////				estudianteAux= main.crearEstudiante(nombre, iD, correo, contrasenia);
//				if(estudianteAux==null)
//					throw new EstudianteNoCreadoException("ocurrió un error al crear el estudiante");
//				listaEstudiantesData.add(estudianteAux);
//
//				tableEstudiantes.refresh();
//				mostrarMensaje("Notificacion Estudiante","Estudiante registrado","El estudiante se registró con éxito",AlertType.INFORMATION);
//				registrarAccion("Estudiante con ID : "+estudianteAux.getiD()+"creado por el admin:"+admin.getName(),Level.INFO );
//			}
//		} catch (DatosInvalidosException | EstudianteNoCreadoException e) {
//	    	registrarAccion("Error al crear estudiante "+admin.getName()+"DatosInvalidosException ó EstudianteNoCreadoException",Level.SEVERE );
//			mostrarMensaje("Notificación Estudiante", "Estudiante no registrado",e.getMessage(), AlertType.ERROR);		}
//	}
//
//	private void crearInstructor() {
//		String nombre=txtNombreInstructor.getText();
//		String iD=txtIdInstructor.getText();
//		String correo=txtCorreoInstructor.getText();
//		String contrasenia=txtContraseniaInstructor.getText();
//		try {
//			if(validarDatosInstructor(nombre,iD,correo,contrasenia)){
//
//				Instructor instructorAux=null;
//
////				instructorAux= mainCliente.crearInstructor(nombre, iD, correo, contrasenia);
//				if(instructorAux==null)
//					throw new InstructorNoCreadoException("ocurrió un error al crear el instructor");
//				listaInstructoresData.add(instructorAux);
//				tableInstructor.refresh();
//				limpiarCamposInstructor();
//				mostrarMensaje("Notificacion Instructor","Instructor registrado","El instructor se registró con éxito",AlertType.INFORMATION);
//				registrarAccion("Instructor con ID : "+instructorAux.getiD()+"creado por el admin: "+admin.getName(),Level.INFO );
//			}
//		} catch (DatosInvalidosException |  InstructorNoCreadoException e) {
//			registrarAccion("Error al crear el instructor\n"+admin.getName()+"\n"+"DatosInvalidosException ó  InstructorNoCreadoException",Level.SEVERE );
//			mostrarMensaje("Notificación instructor", "Instructor no registrado",e.getMessage(), AlertType.ERROR);		}
//	}
//  private void crearCurso() {
//		 String nombre=txtNombreCredito.getText();
//		 String cuposDisponibles=txtCuposDisponiblesCredito.getText();
//		 String bloque=txtBloqueCurso.getText();
//		 String piso=txtPisoCredito.getText();
//		 String numSalon=txtNumSalonCurso.getText();
//		 String costo=txtCostoCurso.getText();
//		 EHorario horario1=comboBoxHorario1.getValue();
//		 EHorario horario2=comboBoxHorario2.getValue();
//		 EDia     dia1=comboBoxDia1.getValue();
//		 EDia     dia2=comboBoxDia2.getValue();
//		 try {
//			if(rBtnAcademico.isSelected())
//			 crearCreditoAcademico(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//					 			   horario1, horario2, dia1, dia2);
//		 if(rBtnCultural.isSelected())
//			 crearCreditoCultural(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//					 horario1, horario2, dia1, dia2);
//		 if(rBtnDeportivo.isSelected())
//			 crearCreditoDeportivo(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//					 horario1, horario2, dia1, dia2);
//
//
//		} catch (NumberFormatException | DatosInvalidosException e) {
//			registrarAccion("Error al crear el curso\n"+admin.getName()+"\n"+" NumberFormatException ó DatosInvalidosException",Level.SEVERE );
//			mostrarMensaje("Notificación credito", "Credito no creado", e.getMessage(), AlertType.ERROR);
//
//		}
//
//	}
//	private void crearCreditoDeportivo(String nombre, String cuposDisponibles, String bloque, String piso, String numSalon,
//		String costo, EHorario horario1, EHorario horario2, EDia dia1, EDia dia2) throws DatosInvalidosException,NumberFormatException {
//
//		String asistenciaMin=comboBoxAuxCurso.getValue();
//		String tipoCredito="Deportivo";
//
//		if(validarDatosCredito(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//					 horario1, horario2, dia1, dia2)==true ){
//
//
//			if(asistenciaMin==null || asistenciaMin.equalsIgnoreCase(""))
//				throw new DatosInvalidosException("Asistencia invalida");
//
//			int pisoInt=Integer.parseInt(piso);
//			int salonInt=Integer.parseInt(numSalon);
//			int cuposDisponiblesInt=Integer.parseInt(cuposDisponibles);
//			EAsistenciaMinima asistenciaMinAux=obtenerAsistencia(asistenciaMin);
//			double costoDouble=Double.parseDouble(costo);
//
//			Horario horarioAux=crearHorario(horario1,horario2,dia1,dia2);
//			Lugar   lugarAux= new Lugar(bloque, pisoInt, salonInt);
////			Deportivo deportivo=mainCliente.crearDeportivo(nombre, cuposDisponiblesInt, costoDouble,horarioAux,lugarAux,asistenciaMinAux,tipoCredito);
////			listaCreditosData.add(deportivo);
//			tableCreditos.refresh();
//			mostrarMensaje("Notificacion Credito Deportivo","Credito Deportivo registrado","El Credito Deportivo se registró con éxito",AlertType.INFORMATION);
////			registrarAccion("Credito Deportivo con nombre : "+deportivo.getNombre()+"creado por el admin:"+admin.getName(),Level.INFO );
//
//
//
//		}
//}
//
//
//
//
//
//
//	private Horario crearHorario(EHorario horario1, EHorario horario2, EDia dia1, EDia dia2)  {
//		Horario horarioAux;
//		ArrayList<EHorario> listaHorariosAux= new ArrayList<EHorario>();
//		ArrayList<EDia> listaDiasAux= new ArrayList<EDia>();
//
//		if(horario1!=null){listaHorariosAux.add(horario1);}
//		if(horario2!=null){listaHorariosAux.add(horario2);}
//		if(dia1!=null){listaDiasAux.add(dia1);}
//		if(dia2!=null){listaDiasAux.add(dia2);}
//
//		horarioAux=new Horario(listaDiasAux,listaHorariosAux);
//
//		return horarioAux;
//	}
//
//	private void crearCreditoCultural(String nombre, String cuposDisponibles, String bloque, String piso, String numSalon,
//		String costo, EHorario horario1, EHorario horario2, EDia dia1, EDia dia2) throws NumberFormatException, DatosInvalidosException {
//
//		String tipoCredito="Cultural";
//
//		if(validarDatosCredito(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//				 horario1, horario2, dia1, dia2)==true){
//
//		int pisoInt=Integer.parseInt(piso);
//		int salonInt=Integer.parseInt(numSalon);
//		int cuposDisponiblesInt=Integer.parseInt(cuposDisponibles);
//		double costoDouble=Double.parseDouble(costo);
//		if(costoDouble<50000 || costoDouble>100000)
//			throw new DatosInvalidosException("El costo debe estar entre 50.000 y 100.000");
//
//		Horario horarioAux=crearHorario(horario1,horario2,dia1,dia2);
//		Lugar   lugarAux= new Lugar(bloque, pisoInt, salonInt);
////		Cultural cultural=mainCliente.crearCultural(nombre, cuposDisponiblesInt, costoDouble,horarioAux,lugarAux,tipoCredito);
////		listaCreditosData.add(cultural);
//		tableCreditos.refresh();
//		mostrarMensaje("Notificacion Credito Cultural","Credito Cultural registrado","El Credito Cultural se registró con éxito",AlertType.INFORMATION);
////		registrarAccion("Credito Cultural con nombre : "+cultural.getNombre()+"creado por el admin:"+admin.getName(),Level.INFO );
//
//
//
//	}
//
//
//
//}
//
//	private void crearCreditoAcademico(String nombre, String cuposDisponibles, String bloque, String piso, String numSalon,
//		String costo, EHorario horario1, EHorario horario2, EDia dia1, EDia dia2) throws NumberFormatException, DatosInvalidosException {
//
//
//		EArea area= comboBoxAuxCredito2.getValue();
//		String notaMinima=comboBoxAuxCurso.getValue();
//
//		String tipoCredito="Academico";
//
//		if(validarDatosCredito(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//					 horario1, horario2, dia1, dia2)==true ){
//
//
//			if(area==null || notaMinima==null || notaMinima.equals(""))
//				throw new DatosInvalidosException("La nota o el area son invalidas");
//
//			double notaDouble=Double.parseDouble(notaMinima);
//			int pisoInt=Integer.parseInt(piso);
//			int salonInt=Integer.parseInt(numSalon);
//			int cuposDisponiblesInt=Integer.parseInt(cuposDisponibles);
//			double costoDouble=Double.parseDouble(costo);
//
//			Horario horarioAux=crearHorario(horario1,horario2,dia1,dia2);
//			Lugar   lugarAux= new Lugar(bloque, pisoInt, salonInt);
//
////			Academico academico=mainCliente.crearAcademico(nombre, cuposDisponiblesInt, costoDouble, horarioAux, lugarAux, tipoCredito, notaDouble, area);
////			listaCreditosData.add(academico);
//			tableCreditos.refresh();
//			mostrarMensaje("Notificacion Credito Academico","Credito Academico registrado","El Credito Academico se registró con éxito",AlertType.INFORMATION);
////			registrarAccion("Credito Academico con nombre : "+academico.getNombre()+"creado por el admin:"+admin.getName(),Level.INFO );
//
//
//		}
//}

//	private void actualizarEstudiante() {
//		if(estudianteSeleccionado!=null){
//
//			String nombre=txtNombreEstudiante.getText();
//			String iD=txtIdEstudiante.getText();
//			String correo=txtCorreoEstudiante.getText();
////			String contrasenia=txtContraseniaEstudiante.getText();
//
////			try {
////				if(validarDatosEstudiante(nombre,iD,correo,contrasenia)){
////
////					Estudiante estudianteAux=new Estudiante(nombre, iD, correo, contrasenia);
////
//////					if(main.actualizarEstudiante(estudianteAux,estudianteSeleccionado)){
////						tableEstudiantes.refresh();
////						mostrarMensaje("Notificacion Estudiante","Estudiante actualizado","El estudiante se actualizó con éxito",AlertType.INFORMATION);
////						registrarAccion("Estudiante con ID : "+estudianteSeleccionado.getiD()+"actualizado por el admin: "+admin.getName(),Level.WARNING );
////
////					}else
////					{
////						mostrarMensaje("Notificación Estudiante", "Estudiante no actualizado","el estudiante no se actualizó", AlertType.WARNING);
////
////					}
////				}
////			}catch (DatosInvalidosException e) {
////				registrarAccion("Error al actualizar el estudiante admin="+admin.getName()+"\n"+" DatosInvalidosException",Level.SEVERE );
////				mostrarMensaje("Notificación Estudiante", "Estudiante no actualizado",e.getMessage(), AlertType.ERROR);
////			}
////		}else{
////			mostrarMensaje("Notificación Estudiante", "Estudiante no seleccionado","Seleccione un estudiante", AlertType.WARNING);
////		}
//	}
//
//	private void actualizarInstructor() {
//		if(instructorSeleccionado!=null){
//
//			String nombre=txtNombreInstructor.getText();
//			String iD=txtIdInstructor.getText();
//			String correo=txtCorreoInstructor.getText();
//			String contrasenia=txtContraseniaInstructor.getText();
//
//			try {
//				if(validarDatosInstructor(nombre,iD,correo,contrasenia)){
//
//					Instructor instructorAux=new Instructor(nombre, iD, correo, contrasenia);
//
////					if(mainCliente.actualizarInstructor(instructorAux,instructorSeleccionado)){
//						tableInstructor.refresh();
//						limpiarCamposInstructor();
//						mostrarMensaje("Notificacion Instructor","Instructor actualizado","El Instructor se actualizó con éxito",AlertType.INFORMATION);
//						registrarAccion("Instructor con ID : "+instructorAux.getiD()+"actualizado por el admin: "+admin.getName(),Level.INFO );
//					}else
//					{
//						mostrarMensaje("Notificación Instructor", "Instructor no actualizado","el Instructor no se actualizó", AlertType.WARNING);
//
//					}
//				}
//			}catch (DatosInvalidosException e) {
//				registrarAccion("Error al actualizar el instructor admin="+admin.getName()+"\n"+" DatosInvalidosException",Level.SEVERE );
//				mostrarMensaje("Notificación Instructor", "Instructor no actualizado",e.getMessage(), AlertType.ERROR);
//			}
//		}else{
//			mostrarMensaje("Notificación Instructor", "Instructor no seleccionado","Seleccione un Instructor", AlertType.WARNING);
//		}
//	}
//	 private void actualizarCreditoAction() {
//			if(creditoSeleccionado!=null)
//			{
//				 String nombre=txtNombreCredito.getText();
//				 String cuposDisponibles=txtCuposDisponiblesCredito.getText();
//				 String bloque=txtBloqueCurso.getText();
//				 String piso=txtPisoCredito.getText();
//				 String numSalon=txtNumSalonCurso.getText();
//				 String costo=txtCostoCurso.getText();
//				 EHorario horario1=comboBoxHorario1.getValue();
//				 EHorario horario2=comboBoxHorario2.getValue();
//				 EDia     dia1=comboBoxDia1.getValue();
//				 EDia     dia2=comboBoxDia2.getValue();
//
//				 try {
//					if(rBtnAcademico.isSelected())
//					 actualizarCreditoAcademico(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//							 			   horario1, horario2, dia1, dia2,creditoSeleccionado);
//				 if(rBtnCultural.isSelected())
//					 actualizarCreditoCultural(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//							 horario1, horario2, dia1, dia2);
//				 if(rBtnDeportivo.isSelected())
//					 ActualizarCreditoDeportivo(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//							 horario1, horario2, dia1, dia2);
//
//
//				} catch (NumberFormatException | DatosInvalidosException e) {
//					registrarAccion("Error al actualizar el credito admin="+admin.getName()+"\n"+" NumberFormatException ó DatosInvalidosException",Level.SEVERE );
//					mostrarMensaje("Notificación credito", "Credito no actualizado", e.getMessage(), AlertType.ERROR);
//
//				}
//
//			}else{
//				mostrarMensaje("Notificación Credito", "Credito no seleccionado","Seleccione un Credito", AlertType.WARNING);
//
//			}
//
//		}
//	private void ActualizarCreditoDeportivo(String nombre, String cuposDisponibles, String bloque, String piso,
//			String numSalon, String costo, EHorario horario1, EHorario horario2, EDia dia1, EDia dia2) throws NumberFormatException, DatosInvalidosException {
//
//		String asistenciaMin=comboBoxAuxCurso.getValue();
//		String tipoCredito="Deportivo";
//
//		if(validarDatosCredito(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//					 horario1, horario2, dia1, dia2)==true ){
//
//
//			if(asistenciaMin==null || asistenciaMin.equalsIgnoreCase(""))
//				throw new DatosInvalidosException("Asistencia invalida");
//
//			int pisoInt=Integer.parseInt(piso);
//			int salonInt=Integer.parseInt(numSalon);
//			int cuposDisponiblesInt=Integer.parseInt(cuposDisponibles);
//			EAsistenciaMinima asistenciaMinAux=obtenerAsistencia(asistenciaMin);
//			double costoDouble=Double.parseDouble(costo);
//
//			Horario horarioAux=crearHorario(horario1,horario2,dia1,dia2);
//			Lugar   lugarAux= new Lugar(bloque, pisoInt, salonInt);
//
//			Deportivo deportivoAux= new Deportivo(costoDouble,cuposDisponiblesInt,horarioAux,lugarAux,asistenciaMinAux,tipoCredito,nombre);
//
//			if(mainCliente.actualizarCredito(deportivoAux,creditoSeleccionado)){
//				tableCreditos.refresh();
//				mostrarMensaje("Notificacion Credito deportivo","Credito Deportivo actualizado","El Credito Deportivo se actualizado con éxito",AlertType.INFORMATION);
//				registrarAccion("Credito Deportivo con nombre : "+deportivoAux.getNombre()+" actualizado por el admin: "+admin.getName(),Level.INFO );
//			}
//		}
//
//	}
//
//	private void actualizarCreditoCultural(String nombre, String cuposDisponibles, String bloque, String piso,
//			String numSalon, String costo, EHorario horario1, EHorario horario2, EDia dia1, EDia dia2) throws NumberFormatException, DatosInvalidosException {
//
//		String tipoCredito="Cultural";
//
//		if(validarDatosCredito(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//				horario1, horario2, dia1, dia2)==true){
//
//			int pisoInt=Integer.parseInt(piso);
//			int salonInt=Integer.parseInt(numSalon);
//			int cuposDisponiblesInt=Integer.parseInt(cuposDisponibles);
//			double costoDouble=Double.parseDouble(costo);
//			if(costoDouble<50000 || costoDouble>100000)
//				throw new DatosInvalidosException("El costo debe estar entre 50.000 y 100.000");
//
//
//			Horario horarioAux=crearHorario(horario1,horario2,dia1,dia2);
//			Lugar   lugarAux= new Lugar(bloque, pisoInt, salonInt);
//
//			Cultural culturalAux= new Cultural(costoDouble,cuposDisponiblesInt,horarioAux,lugarAux
//					,tipoCredito,nombre);
//
//			if(mainCliente.actualizarCredito(culturalAux,creditoSeleccionado)){
//				tableCreditos.refresh();
//				mostrarMensaje("Notificacion Credito Cultural","Credito Cultural actualizado","El Credito Cultural se actualizado con éxito",AlertType.INFORMATION);
//				registrarAccion("Credito Cultural con nombre : "+culturalAux.getNombre()+" actualizado por el admin: "+admin.getName(),Level.INFO );
//			}
//		}
//
//	}
//
//	private void actualizarCreditoAcademico(String nombre, String cuposDisponibles, String bloque, String piso,
//			String numSalon, String costo, EHorario horario1, EHorario horario2, EDia dia1, EDia dia2,
//			Credito creditoSeleccionado) throws NumberFormatException, DatosInvalidosException {
//
//
//		EArea area= comboBoxAuxCredito2.getValue();
//		String notaMinima=comboBoxAuxCurso.getValue();
//
//		String tipoCredito="Academico";
//
//		if(validarDatosCredito(nombre, cuposDisponibles, bloque, piso, numSalon, costo,
//					 horario1, horario2, dia1, dia2)==true ){
//
//
//			if(area==null || notaMinima==null || notaMinima.equals(""))
//				throw new DatosInvalidosException("La nota o el area son invalidas");
//
//			double notaDouble=Double.parseDouble(notaMinima);
//			int pisoInt=Integer.parseInt(piso);
//			int salonInt=Integer.parseInt(numSalon);
//			int cuposDisponiblesInt=Integer.parseInt(cuposDisponibles);
//			double costoDouble=Double.parseDouble(costo);
//
//			Horario horarioAux=crearHorario(horario1,horario2,dia1,dia2);
//			Lugar   lugarAux= new Lugar(bloque, pisoInt, salonInt);
//
//			Academico academicoAux= new Academico(costoDouble,cuposDisponiblesInt,horarioAux,lugarAux,notaDouble
//											     ,area,tipoCredito,nombre);
//
//			if(mainCliente.actualizarCredito(academicoAux,creditoSeleccionado)){
//				tableCreditos.refresh();
//				mostrarMensaje("Notificacion Credito Academico","Credito Academico actualizado","El Credito Academico se actualizado con éxito",AlertType.INFORMATION);
//				registrarAccion("Credito Academico con nombre : "+academicoAux.getNombre()+"actualizado por el admin:"+admin.getName(),Level.INFO );
//			}
//		}
//
//	}
//
//	private void borrarEstudiante() {
//
//		if(estudianteSeleccionado!=null)
//		{
//			if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar al estudiante?") == true)
//			{
//				if(mainCliente.borrarEstudiante(estudianteSeleccionado)){
//
//					String iDEstudiante=estudianteSeleccionado.getName();
//					listaEstudiantesData.remove(estudianteSeleccionado);
//					estudianteSeleccionado=null;
//					tableEstudiantes.getSelectionModel().clearSelection();
//					limpiarCamposEstudiante();
//					tableEstudiantes.refresh();
//
//					mostrarMensaje("Notificacion Estudiante","Estudiante borrado","El estudiante se borró con éxito",AlertType.INFORMATION);
//					registrarAccion("Estudiante con ID : "+iDEstudiante+" borrado por el admin: "+admin.getName(),Level.WARNING );
//
//				}else{
//					mostrarMensaje("Notificación Estudiante", "Estudiante no borrado","El estudiante no se borró con éxito", AlertType.ERROR);
//				}
//			}
//		}else{
//			mostrarMensaje("Notificación Estudiante", "Estudiante no seleccionado","Seleccione un estudiante", AlertType.WARNING);
//		}
//	}
//
//	private void borrarInstructor() {
//		if(instructorSeleccionado!=null)
//		{
//			if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar al instructor?") == true)
//			{
//				if(main.borrarInstructor(instructorSeleccionado)){
//
//					String iDInstructor=instructorSeleccionado.getiD();
//					listaInstructoresData.remove(instructorSeleccionado);
//					instructorSeleccionado=null;
//					tableInstructor.getSelectionModel().clearSelection();
//					limpiarCamposInstructor();
//					tableInstructor.refresh();
//
//					mostrarMensaje("Notificacion Instructor","Instructor borrado","El Instructor se borró con éxito",AlertType.INFORMATION);
//					registrarAccion("Instructor con ID : "+iDInstructor+" borrado por el admin: "+admin.getName(),Level.WARNING );
//
//				}else{
//					mostrarMensaje("Notificación Instructor", "Instructor no borrado","El Instructor no se borró con éxito", AlertType.ERROR);
//				}
//			}
//		}else{
//			mostrarMensaje("Notificación Instructor", "Instructor no seleccionado","Seleccione un Instructor", AlertType.WARNING);
//		}
//	}
//	private void borrarCreditoAction() {
//	if(creditoSeleccionado!=null)
//		{
//			if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar el credito "+creditoSeleccionado.getNombre()+" ?") == true)
//			{
//				if(mainCliente.borrarCredito(creditoSeleccionado)){
//
//					String nombreCredito=creditoSeleccionado.getNombre();
//					listaCreditosData.remove(creditoSeleccionado);
//					creditoSeleccionado=null;
//					tableCreditos.getSelectionModel().clearSelection();
//					limpiarCreditos();
//					tableCreditos.refresh();
//
//					mostrarMensaje("Notificacion Credito","Credito borrado","El Credito se borró con éxito",AlertType.INFORMATION);
//					registrarAccion("Credito con nombre : "+nombreCredito+" borrado por el admin: "+admin.getName(),Level.WARNING );
//
//				}else{
//					mostrarMensaje("Notificación Credito", "Credito no borrado","El Credito no se borró con éxito", AlertType.ERROR);
//				}
//			}
//		}else{
//			mostrarMensaje("Notificación Credito", "Credito no seleccionado","Credito un Instructor", AlertType.WARNING);
//		}
//
//	}
//	private boolean validarDatosEstudiante(String nombre, String iD, String correo, String contrasenia) throws DatosInvalidosException {
//
//		String mensaje="";
//
//		if(nombre == null || nombre.equals(""))
//			mensaje += "El nombre del estudiante es invalido \n";
//
//		if(iD == null || iD.equals("")){
//			mensaje += "El ID del retiro es invalido \n";
//		}else{
//			if(main.verificarIDEstudiante(iD))
//			{
//				mensaje+="El ID ya está registrado";
//			}
//		}
//
//		if(correo == null || correo.equals("")){
//			mensaje += "El correo es invalido \n";
//		}else{
//			if(main.verificarCorreoEstudiante(correo))
//			{
//				mensaje+="El correo ya está registrado";
//			}
//		}
//
//		if(contrasenia == null || contrasenia.equals(""))
//			mensaje += "La contraseña es invalida \n";
//
//
//		if(mensaje.isEmpty())
//		{
//			return true;
//		}else
//		{
//			throw new DatosInvalidosException(mensaje);
//		}
//	}
//
//	private boolean validarDatosInstructor(String nombre, String iD, String correo, String contrasenia) throws DatosInvalidosException {
//		String mensaje="";
//
//		if(nombre == null || nombre.equals(""))
//			mensaje += "El nombre del instructor es invalido \n";
//
//		if(iD == null || iD.equals("")){
//			mensaje += "El ID del instructor es invalido \n";
//		}else{
//			if(main.verificarIDInstructor(iD))
//			{
//				mensaje+="El ID ya está registrado";
//			}
//		}
//
//		if(correo == null || correo.equals("")){
//			mensaje += "El correo es invalido \n";
//		}else{
//			if(main.verificarCorreoInstructor(correo))
//			{
//				mensaje+="El correo ya está registrado";
//			}
//		}
//
//		if(contrasenia == null || contrasenia.equals(""))
//			mensaje += "La contraseña es invalida \n";
//
//
//		if(mensaje.isEmpty())
//		{
//			return true;
//		}else
//		{
//			throw new DatosInvalidosException(mensaje);
//		}
//	}
//private boolean validarDatosCredito(String nombre, String cuposDisponibles, String bloque, String piso, String numSalon,
//			String costo, EHorario horario1, EHorario horario2, EDia dia1, EDia dia2) throws DatosInvalidosException {
//		String mensaje="";
//
//
//
//
//
//		if(nombre == null || nombre.equals("")){
//			mensaje += "El nombre del credito del instructor es invalido \n";
//		}else{
//				if(main.validarNombreCredito(nombre))
//					mensaje += "Ya existe un credito academico con ese nombre \n";
//		}
//
//
//		if(cuposDisponibles == null || cuposDisponibles.equals("")){
//			mensaje += "Los cupos disponibles del curso son invalidos \n";
//		}
//
//		if(bloque == null || bloque.equals("")){
//			mensaje += "El bloque es invalido \n";
//		}
//
//		if(piso == null || piso.equals(""))
//			mensaje += "El piso es invalido \n";
//
//		if(numSalon == null || numSalon.equals(""))
//			mensaje += "El número de piso es invalido \n";
//
//
//		if(costo == null || costo.equals(""))
//			mensaje += "El costo es invalido \n";
//
//		if(horario1 == null && horario2==null){
//			mensaje += "Debe ingresar almenos un horario \n";
//		}else{
//			if(horario1!=null && horario2!=null)
//			{
//				if(horario1==horario2)
//					mensaje+="Los dos horarios no pueden ser iguales\n";
//			}
//		}
//
//
//		if(dia1 == null && dia2==null){
//			mensaje += "Debe ingresar almenos un dia \n";
//		}else{
//			if(dia1!=null && dia2!=null)
//			{
//				if(dia1==dia2)
//					mensaje+="Los dos dias no pueden ser iguales\n";
//			}
//		}
//
//		if(mensaje.isEmpty())
//		{
//			return true;
//		}else
//		{
//			throw new DatosInvalidosException(mensaje);
//		}
//}
//private ObservableList<Credito> getListaCreditosData() {
//	listaCreditosData.clear();
//	listaCreditosData.addAll(main.obtenerCreditos());
//
//	return listaCreditosData;
//}
//
//private ObservableList<Estudiante> getListaEstudiantesData() {
//	listaEstudiantesData.clear();
//	listaEstudiantesData.addAll(main.obtenerEstudiantes());
//
//	return listaEstudiantesData;
//}
//
//	private ObservableList<Instructor> getListaInstructoresData() {
//		listaInstructoresData.clear();
//		listaInstructoresData.addAll(main.obtenerInstructores());
//		return listaInstructoresData;
//	}
//

	private EAsistenciaMinima obtenerAsistencia(String asistenciaMin) {

		if(asistenciaMin.equals(EAsistenciaMinima.SETENTA_PORCIENTO)){
			return EAsistenciaMinima.SETENTA_PORCIENTO;
		}else{
			if(asistenciaMin.equals(EAsistenciaMinima.SETENTA_Y_CINCO_PORCIENTO)){
				return EAsistenciaMinima.SETENTA_Y_CINCO_PORCIENTO;
			}else{
				return EAsistenciaMinima.OCHENTA_PORCIENTO;
			}
		}
	}
//	private void guardarDatosTXT(String nombreArchivo) {
//		administradorHilos.startHiloGuardarDatosObtejos(nombreArchivo);
//
//	}
//	 private void cargarDatosTXT(String nombreArchivo) {
//		 administradorHilos.startHiloCargarDatosObtejos(nombreArchivo);
//
//		}

	/**
	 * Metodo que hace el Backup
	 */
	private void hacerBackup() {
		PaqueteDatos paqueteDatos= new PaqueteDatos(AccionEnum.HACER_BACKUP,admin.getName() );
		administradorHilos.startHiloEnviarPaqueteServer(paqueteDatos);
	}
//
//	public Admin getAdmin() {
//		return admin;
//	}
//
//	public void setAdmin(Admin admin) {
//		this.admin = admin;
//	}
//
//	public void refrescarTablas() {
//
//
//		tableCreditos.setItems(getListaCreditosData());
//		tableEstudiantes.setItems(getListaEstudiantesData());
//		tableInstructor.setItems(getListaInstructoresData());
//
//		tableCreditos.refresh();
//		tableEstudiantes.refresh();
//		tableInstructor.refresh();
//
//	}
//

}

