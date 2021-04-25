package project_Grades;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ControllerMyGrades {
	private Student myGrades;
	private OptionsController options = new OptionsController();
	
	@FXML
	Pane topPane;
	
	@FXML
	Pane bottomPane;
	
	@FXML
	Pane contentPane;
	
	@FXML
	Pane IOPanes;
	
	@FXML
	Pane loadFilePane;
	
	@FXML
	Pane saveFilePane;
	
	@FXML
	TextArea gradeTextField;
	
	@FXML
	TextField userNameField;
	
	@FXML
	TextField userDataSaveStatus;
	
	// Change user
	@FXML
	TextField newUsername;
	
	@FXML
	Button changeUserBtn;
	
	// Start panel
	@FXML
	Pane startupPane;
	
	@FXML
	Button 	setInitialSettingsBtn;
	
	@FXML
	TextField initialUserName;
	
	// Error panel
	@FXML
	Button errorMessageOKBtn;
	
	@FXML
	TextField errorTextHeader;
	
	@FXML
	TextArea errorTextArea;
	
	@FXML 
	Pane errorMessagePane;
	
	// Remove course
	@FXML
	TextField courseToRemove;
	
	@FXML
	Button removeCourseBtn;
	
	@FXML
	Button loadFileFromCSV;
	
	@FXML
	Button saveFileToCSV;
	
	@FXML
	Button saveFile;
	
	@FXML
	Button addCourse;
	
	@FXML
	Button savePaneConfirmButton;
	
	@FXML
	Button loadPaneConfirmButton;
	
	@FXML
	TextField newCourseName;
	
	@FXML
	TextField newCourseCode;
	
	@FXML
	ChoiceBox<String> newCourseGrade;
	
	@FXML
	TextField newCoursePoints;
	
	@FXML
	TextField savePaneFileName;
	
	@FXML
	TextField loadPaneFileName;
	
	@FXML
	Button CloseSaveFilePaneBtn;
	
	@FXML
	Button CloseLoadFilePaneBtn;
	
	@FXML
	TableView<Course> contentTable;
	
	@FXML
	TableColumn<Course, String> courseCodeColumn;
	
	@FXML
	TableColumn<Course, String> courseNameColumn;
	
	@FXML
	TableColumn<Course, String> courseGradeColumn;
	
	@FXML
	TableColumn<Course, String> coursePointsColumn;
	
	@FXML
	private void initialize() {
		startupPane.toFront();
		saveFilePaneToggle();
		loadFilePaneToggle();
		IOPanes.setDisable(true);
		IOPanes.toBack();
		newCourseGrade.setItems(options.getValidGrades());
	}
	
	@FXML
	private void closeStartupPane() {
//		Pane startupPane;
//		Button 	setInitialSettingsBtn;
//		TextField initialUserName;
		try {
			myGrades = new Student(initialUserName.getText());
			loadUserData();
			startupPane.toBack();
			startupPane.setOpacity(0);
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig brukernavn", "Du må skrive inn et brukernavn før du kan bytte bruker.");
		}
	}
	
	@FXML
	private void addNewCourse() {
		try {
			options.addCourseToStudent(newCourseName, newCourseCode, newCourseGrade, newCoursePoints, myGrades);
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig emneinformasjon", "Oppgitt emneinformasjon var feil.\nVennligst fyll ut på nytt og prøv igjen.");
		}
	}
	
	@FXML
	private void removeCourse() {
//		TextField courseToRemove;
//		Button removeCourseBtn;
		try {
			myGrades.removeCourse(options.findCourseOnCode(courseToRemove, myGrades));
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig emnekode", "Kunne ikke finne \""+courseToRemove.getText()+"\" i listen over emner for "+myGrades.getPersonName()+".\nKontroller om du skrev inn riktig emnekode og prøv på nytt.");
		}
	}
	
	@FXML
	private void closeErrorMessagePane() {
//		Button errorMessageOKBtn;
//		TextField errorTextHeader;
//		TextArea errorTextArea;
//		Pane errorMessagePane;
		
		errorMessagePane.toBack();
		errorMessagePane.setOpacity(0);
		errorTextHeader.clear();
		errorTextArea.clear();
	}
	
	private void showErrorMessage(String errorHeader, String errorMessage) {
		errorMessagePane.toFront();
		errorMessagePane.setOpacity(1);
		errorTextHeader.setText(errorHeader);
		errorTextArea.setText(errorMessage);
	}
	
	@FXML
	private void changeUserAccount() {
//		TextField newUsername;
//		Button changeUserBtn;
		System.out.println(newUsername.getText());
		try {
			myGrades = new Student(newUsername.getText());
			loadUserData();
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig brukernavn", "Du må skrive inn et brukernavn før du kan bytte bruker.");
		}
	}
	
	@FXML
	private void saveFilePaneToggle() {
		if (saveFilePane.isDisabled()) {
			IOPanes.setDisable(false);
			IOPanes.toFront();
			saveFilePane.setDisable(false);
			saveFilePane.setOpacity(1);
			saveFilePane.toFront();
		} else if (!saveFilePane.isDisabled()) {
			IOPanes.setDisable(true);
			IOPanes.toBack();
			saveFilePane.setDisable(true);
			saveFilePane.setOpacity(0);
			saveFilePane.toBack();
		}
	}
	
	@FXML
	private void loadFilePaneToggle() {
		if (loadFilePane.isDisabled()) {
			IOPanes.setDisable(false);
			IOPanes.toFront();
			loadFilePane.setDisable(false);
			loadFilePane.setOpacity(1);
			loadFilePane.toFront(); // Bring the pane to front.
		} else if (!loadFilePane.isDisabled()) {
			IOPanes.setDisable(true);
			IOPanes.toBack();
			loadFilePane.setDisable(true);
			loadFilePane.setOpacity(0);
			loadFilePane.toBack(); // Send the pane all the way back so it doens't interfere with other GUI elements.
		}
	}
	
	@FXML
	private void saveFile() {
		String fileName = savePaneFileName.getText();
		SaveFileCSV fileSaver = new SaveFileCSV();
		try {
			fileSaver.save(fileName, myGrades);
			saveFilePaneToggle();
			reloadGUI();
		} catch (IOException f) {
			try  {
				fileSaver.save(fileName, myGrades);
				saveFilePaneToggle();
				reloadGUI();
			} catch(IOException g) {
				showErrorMessage("Feil", "Noe gikk galt som hindret oss å lage filen "+fileName+".\nKontroller at filplassering er skrivbar og prøv igjen.");
				g.printStackTrace();
			}
		}
	}
	
	@FXML
	private void loadFile() {
		String fileName = loadPaneFileName.getText();
		LoadFileCSV fileLoader = new LoadFileCSV();
		try {
			fileLoader.load(fileName, myGrades);
			loadFilePaneToggle();
			reloadGUI();
		} catch (FileNotFoundException e) {
			showErrorMessage(fileName+" ikke funnet.", "Filen "+fileName+" ble ikke funnet.\nSjekk om du skrev inn riktig navn og prøv igjen.");
			e.printStackTrace();
		} catch (IOException f) {
			try  {
				fileLoader.load(fileName, myGrades);
				saveFilePaneToggle();
				reloadGUI();
			} catch(IOException g) {
				showErrorMessage("Feil", "Noe gikk galt som hindret oss å hente filen "+fileName+".\nKontroller at filplassering er lesbar, at du skrev filnavn riktig, og prøv igjen.");
				g.printStackTrace();
			}
		}
	}
	
	@FXML
	private void saveUserData() {
		SaveUserData dataSaver = new SaveUserData();
		try {
			dataSaver.save(myGrades);
			reloadGUI();
			userDataSaveStatus.setText("Lagret");
		} catch (FileNotFoundException e) {
			showErrorMessage("Kunne ikke lagre", "Noe gikk galt under lagringen av data. Vennligst prøv igjen senere.");
			e.printStackTrace();
		} catch (IOException f) {
			try  {
				dataSaver.save(myGrades);
				saveFilePaneToggle();
				reloadGUI();
			} catch(IOException g) {
				showErrorMessage("Feil", "Noe gikk galt som hindret oss å lage filen "+myGrades.getPersonName()+".\nKontroller at filplassering er skrivbar og prøv igjen.");
				g.printStackTrace();
			}
		}
	}
	
	@FXML
	private void loadUserData() {
		LoadUserData dataLoader = new LoadUserData();
		try {
			dataLoader.load(myGrades);
		} catch (FileNotFoundException e) {
			showErrorMessage("Data for "+myGrades.getPersonName()+" ikke funnet.", "Kunne ikke finne data for "+myGrades.getPersonName()+".\nSjekk om du har skrevet inn riktig navn og prøv igjen.");
			e.printStackTrace();
		}
	}
	
	@FXML
	private void reloadGUI() {
		printCourseInfo();
		initTableView();
		closeErrorMessagePane();
		newCourseCode.clear();
		newCourseName.clear();
		newCourseGrade.getSelectionModel().clearSelection();
		newCoursePoints.clear();
		userDataSaveStatus.clear();
		loadPaneFileName.clear();
		savePaneFileName.clear();
		newUsername.clear();
		courseToRemove.clear();
	}
	
	@FXML
	void printCourseInfo() {
		userNameField.setText(options.getUserMessage(myGrades));
		gradeTextField.setText(options.getCalculationMessage(myGrades));
	}
	
	@FXML
	public void initTableView() {
//		TableView name and columns:
//		TableView<Course> contentTable;
//		TableColumn<Course, String> courseCodeColumn;
//		TableColumn<Course, String> courseNameColumn;
//		TableColumn<Course, String> courseGradeColumn;
//		TableColumn<Course, String> coursePointsColumn;
		
		courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
		courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
		courseGradeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseGrade"));
		coursePointsColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("coursePoints"));
		
		contentTable.setItems(options.getListOfCourses(myGrades));
	}
}
