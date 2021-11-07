package co.edu.uniquindio.programaAdministrarCursos.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;

import co.edu.uniquindio.programaAdministrarCursos.Main;
import co.edu.uniquindio.programaAdministrarCursos.exception.DatosInvalidosException;
import co.edu.uniquindio.programaAdministrarCursos.exception.EstudianteNoCreadoException;
import co.edu.uniquindio.programaAdministrarCursos.exception.InstructorNoCreadoException;
import co.edu.uniquindio.programaAdministrarCursos.model.Admin;
import co.edu.uniquindio.programaAdministrarCursos.model.Credito;
import co.edu.uniquindio.programaAdministrarCursos.model.EDia;
import co.edu.uniquindio.programaAdministrarCursos.model.EHorario;
import co.edu.uniquindio.programaAdministrarCursos.model.Estudiante;
import co.edu.uniquindio.programaAdministrarCursos.model.Instructor;
import co.edu.uniquindio.programaAdministrarCursos.model.Log;
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
	Log  loggerAdmin;
	
	
	Main main;

	ObservableList<Estudiante> listaEstudiantesData = FXCollections.observableArrayList();
	ObservableList<Instructor> listaInstructoresData = FXCollections.observableArrayList();	

	
	Estudiante estudianteSeleccionado;
	Instructor instructorSeleccionado;
	
	FilteredList<Estudiante> filteredData;
	FilteredList<Instructor> filteredDataInstructor;
	
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
    private Button btnNuevoInstructor;

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
    void nuevoInstructorAction(ActionEvent event) {
    	nuevoInstructor();
	}

	

	@FXML
    void borrarEstudianteAction(ActionEvent event) {
		borrarEstudiante();
    }

   

	@FXML
    void actualizarEstudianteAction(ActionEvent event) {
		actualizarEstudiante();
    }

  
	@FXML
    void crearInstructor(ActionEvent event) {
		crearInstructor();

    }

   

	@FXML
    void borrarInstructor(ActionEvent event) {
		borrarInstructor();
    }

   

	@FXML
    void actualizarInstructor(ActionEvent event) {
		actualizarInstructor();
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
    	registrarAccion("cierre de sesion admin"+admin.getName(),Level.INFO );
    	main.mostrarVentanaLogging();

    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.columnNombreEstudiante.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.columnIdEstudiante.setCellValueFactory(new PropertyValueFactory<>("iD"));
		
		this.columnNombreIntructor.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.columnIdIntructor.setCellValueFactory(new PropertyValueFactory<>("iD"));
		
		
		tableEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			estudianteSeleccionado = newSelection;

			mostrarInformacionEstudiante(estudianteSeleccionado);

		});
		
		tableInstructor.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			instructorSeleccionado = newSelection;

			mostrarInformacionInstructor(instructorSeleccionado);

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


	public void setAplicacion(Main mainAux) {
		this.main=mainAux;
		main.quemarDatos();
		tableEstudiantes.getItems().clear();
		tableInstructor.getItems().clear();

		tableEstudiantes.setItems(getListaEstudiantesData());
		tableInstructor.setItems(getListaInstructoresData());

		
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
	private void crearEstudiante() {

		String nombre=txtNombreEstudiante.getText();
		String iD=txtIdEstudiante.getText();
		String correo=txtCorreoEstudiante.getText();
		String contrasenia=txtContraseniaEstudiante.getText();
		try {
			if(validarDatosEstudiante(nombre,iD,correo,contrasenia)){

				Estudiante estudianteAux=null;

				estudianteAux= main.crearEstudiante(nombre, iD, correo, contrasenia);
				if(estudianteAux==null)
					throw new EstudianteNoCreadoException("ocurrió un error al crear el estudiante");
				listaEstudiantesData.add(estudianteAux);
				tableEstudiantes.refresh();
				mostrarMensaje("Notificacion Estudiante","Estudiante registrado","El estudiante se registró con éxito",AlertType.INFORMATION);
				registrarAccion("Estudiante con ID : "+estudianteAux.getiD()+"creado por el admin:"+admin.getName(),Level.INFO );
			}
		} catch (DatosInvalidosException | EstudianteNoCreadoException e) {
			mostrarMensaje("Notificación Estudiante", "Estudiante no registrado",e.getMessage(), AlertType.ERROR);		}
	}
	
	
	private void crearInstructor() {
		String nombre=txtNombreInstructor.getText();
		String iD=txtIdInstructor.getText();
		String correo=txtCorreoInstructor.getText();
		String contrasenia=txtContraseniaInstructor.getText();
		try {
			if(validarDatosInstructor(nombre,iD,correo,contrasenia)){

				Instructor instructorAux=null;

				instructorAux= main.crearInstructor(nombre, iD, correo, contrasenia);
				if(instructorAux==null)
					throw new InstructorNoCreadoException("ocurrió un error al crear el instructor");
				listaInstructoresData.add(instructorAux);
				tableInstructor.refresh();
				limpiarCamposInstructor();
				mostrarMensaje("Notificacion Instructor","Instructor registrado","El instructor se registró con éxito",AlertType.INFORMATION);
				registrarAccion("Instructor con ID : "+instructorAux.getiD()+"creado por el admin: "+admin.getName(),Level.INFO );
			}
		} catch (DatosInvalidosException |  InstructorNoCreadoException e) {
			mostrarMensaje("Notificación instructor", "Instructor no registrado",e.getMessage(), AlertType.ERROR);		}
	}



private void actualizarEstudiante() {
		  if(estudianteSeleccionado!=null){
			  
			String nombre=txtNombreEstudiante.getText();
			String iD=txtIdEstudiante.getText();
			String correo=txtCorreoEstudiante.getText();
			String contrasenia=txtContraseniaEstudiante.getText();
			
			try {
				if(validarDatosEstudiante(nombre,iD,correo,contrasenia)){

					Estudiante estudianteAux=new Estudiante(nombre, iD, correo, contrasenia);

					if(main.actualizarEstudiante(estudianteAux,estudianteSeleccionado)){
						tableEstudiantes.refresh();
						mostrarMensaje("Notificacion Estudiante","Estudiante actualizado","El estudiante se actualizó con éxito",AlertType.INFORMATION);
						registrarAccion("Estudiante con ID : "+estudianteSeleccionado.getiD()+"actualizado por el admin: "+admin.getName(),Level.WARNING );

					}else
					{
						mostrarMensaje("Notificación Estudiante", "Estudiante no actualizado","el estudiante no se actualizó", AlertType.WARNING);

					}
				}
				}catch (DatosInvalidosException e) {
					mostrarMensaje("Notificación Estudiante", "Estudiante no actualizado",e.getMessage(), AlertType.ERROR);
					}
			}else{
			mostrarMensaje("Notificación Estudiante", "Estudiante no seleccionado","Seleccione un estudiante", AlertType.WARNING);
		}
}
private void actualizarInstructor() {
	  if(instructorSeleccionado!=null){
		  
			String nombre=txtNombreInstructor.getText();
			String iD=txtIdInstructor.getText();
			String correo=txtCorreoInstructor.getText();
			String contrasenia=txtContraseniaInstructor.getText();
			
			try {
				if(validarDatosInstructor(nombre,iD,correo,contrasenia)){

					Instructor instructorAux=new Instructor(nombre, iD, correo, contrasenia);

					if(main.actualizarInstructor(instructorAux,instructorSeleccionado)){
						tableInstructor.refresh();
						limpiarCamposInstructor();
						mostrarMensaje("Notificacion Instructor","Instructor actualizado","El Instructor se actualizó con éxito",AlertType.INFORMATION);
						registrarAccion("Instructor con ID : "+instructorAux.getiD()+"actualizado por el admin: "+admin.getName(),Level.INFO );
					}else
					{
						mostrarMensaje("Notificación Instructor", "Instructor no actualizado","el Instructor no se actualizó", AlertType.WARNING);

					}
				}
				}catch (DatosInvalidosException e) {
					mostrarMensaje("Notificación Instructor", "Instructor no actualizado",e.getMessage(), AlertType.ERROR);
					}
			}else{
			mostrarMensaje("Notificación Instructor", "Instructor no seleccionado","Seleccione un Instructor", AlertType.WARNING);
		}
}

	 private void borrarEstudiante() {
		 
		 if(estudianteSeleccionado!=null)
		 {
			 if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar al estudiante?") == true)
			 {
				 if(main.borrarEstudiante(estudianteSeleccionado)){

					 String iDEstudiante=estudianteSeleccionado.getName();
					 listaEstudiantesData.remove(estudianteSeleccionado);
					 estudianteSeleccionado=null;
					 tableEstudiantes.getSelectionModel().clearSelection();
					 limpiarCamposEstudiante();
					 tableEstudiantes.refresh();
					 
					 mostrarMensaje("Notificacion Estudiante","Estudiante borrado","El estudiante se borró con éxito",AlertType.INFORMATION);
					 registrarAccion("Estudiante con ID : "+iDEstudiante+" borrado por el admin: "+admin.getName(),Level.WARNING );

				 }else{
						mostrarMensaje("Notificación Estudiante", "Estudiante no borrado","El estudiante no se borró con éxito", AlertType.ERROR);
				 }
			 }
			 
			 
		 }else{
				mostrarMensaje("Notificación Estudiante", "Estudiante no seleccionado","Seleccione un estudiante", AlertType.WARNING);

		 }
		 
		}
	
	 private void borrarInstructor() {
		 if(instructorSeleccionado!=null)
		 {
			 if(mostrarMensajeConfirmacion("¿Estas seguro de eliminar al instructor?") == true)
			 {
				 if(main.borrarInstructor(instructorSeleccionado)){

					 String iDInstructor=instructorSeleccionado.getiD();
					 listaInstructoresData.remove(instructorSeleccionado);
					 instructorSeleccionado=null;
					 tableInstructor.getSelectionModel().clearSelection();
					 limpiarCamposInstructor();
					 tableInstructor.refresh();
					 
					 mostrarMensaje("Notificacion Instructor","Instructor borrado","El Instructor se borró con éxito",AlertType.INFORMATION);
					 registrarAccion("Instructor con ID : "+iDInstructor+" borrado por el admin: "+admin.getName(),Level.WARNING );

				 }else{
						mostrarMensaje("Notificación Instructor", "Instructor no borrado","El Instructor no se borró con éxito", AlertType.ERROR);
				 }
			 }
			 
			 
		 }else{
				mostrarMensaje("Notificación Instructor", "Instructor no seleccionado","Seleccione un Instructor", AlertType.WARNING);

		 }
		 
		}
	

	private boolean validarDatosEstudiante(String nombre, String iD, String correo, String contrasenia) throws DatosInvalidosException {

		String mensaje="";

		if(nombre == null || nombre.equals(""))
			mensaje += "El codigo del retiro es invalido \n";
	 
	 if(iD == null || iD.equals("")){
			mensaje += "El codigo del retiro es invalido \n";
	 }else{
		 if(main.verificarIDEstudiante(iD))
		 {
			 mensaje+="El ID ya está registrado";
		 }
	 }
	 
	 if(correo == null || correo.equals("")){
			mensaje += "El correo es invalido \n";
	 }else{
		 if(main.verificarCorreoEstudiante(correo))
		 {
			 mensaje+="El correo ya está registrado";
		 }
	 }
	 
	 if(contrasenia == null || contrasenia.equals(""))
			mensaje += "La contraseña es invalida \n";
	 
	 
	 if(mensaje.isEmpty())
	 {
		 return true;
	 }else
	 {
		 throw new DatosInvalidosException(mensaje);
	 }
}
	
	private boolean validarDatosInstructor(String nombre, String iD, String correo, String contrasenia) throws DatosInvalidosException {
		String mensaje="";

		if(nombre == null || nombre.equals(""))
			mensaje += "El codigo del instructor es invalido \n";

		if(iD == null || iD.equals("")){
			mensaje += "El codigo del instructor es invalido \n";
		}else{
			if(main.verificarIDInstructor(iD))
			{
				mensaje+="El ID ya está registrado";
			}
		}

		if(correo == null || correo.equals("")){
			mensaje += "El correo es invalido \n";
		}else{
			if(main.verificarCorreoInstructor(correo))
			{
				mensaje+="El correo ya está registrado";
			}
		}

		if(contrasenia == null || contrasenia.equals(""))
			mensaje += "La contraseña es invalida \n";


		if(mensaje.isEmpty())
		{
			return true;
		}else
		{
			throw new DatosInvalidosException(mensaje);
		}
	}

private ObservableList<Estudiante> getListaEstudiantesData() {

	 listaEstudiantesData.addAll(main.obtenerEstudiantes());

	 return listaEstudiantesData;
 }

private ObservableList<Instructor> getListaInstructoresData() {

	listaInstructoresData.addAll(main.obtenerInstructores());
	return listaInstructoresData;
}

public void registrarAccion(String mensaje, Level tipo){
	loggerAdmin= new Log(mensaje,tipo);
	loggerAdmin.hilo.start();

}}

