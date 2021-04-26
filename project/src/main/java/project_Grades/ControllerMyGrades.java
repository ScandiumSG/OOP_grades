package project_Grades;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
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
	Button loadSelectFileFromCSV;
	
	@FXML
	Button saveSelectFileToCSV;
	
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
	
	// Change of the username.
	@FXML
	Button openUsernameChangePane;
	
	@FXML
	Pane usernameChangePane;
	
	@FXML
	TextField usernameChangeField;
	
	@FXML
	Button usernameChangePaneClose;
	
	@FXML
	Button usernameChangeConfirm;
	
	@FXML
	private void initialize() {
		startupPane.toFront();
		saveFilePaneToggle();
		loadFilePaneToggle();
		usernameChangePaneToggle();
		IOPanes.setDisable(true);
		IOPanes.toBack();
		newCourseGrade.setItems(Constants.getValidGrades());
	}
	
	@FXML
	private void closeStartupPane() {
		try {
			myGrades = new Student(initialUserName.getText());
			loadUserData();
			startupPane.toBack();
			startupPane.setOpacity(0);
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig brukernavn", "Du m� skrive inn et brukernavn f�r du kan bytte bruker.");
		}
	}
	
	@FXML
	private void addNewCourse() {
		try {
			Course addedCourse = new Course(newCourseName.getText(), newCourseCode.getText(), newCourseGrade.getSelectionModel().getSelectedItem(), Double.valueOf(newCoursePoints.getText()));
			myGrades.addNewGrade(addedCourse);
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig emneinformasjon", "Oppgitt emneinformasjon var feil.\nVennligst fyll ut p� nytt og pr�v igjen.");
		}
	}
	
	@FXML
	private void removeCourse() {
		try {
			myGrades.removeCourse(myGrades.findCourseUsingCourseCode(courseToRemove.getText()));
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig emnekode", "Kunne ikke finne \""+courseToRemove.getText()+"\" i listen over emner for "+myGrades.getPersonName()+".\nKontroller om du skrev inn riktig emnekode og pr�v p� nytt.");
		}
	}
	
	@FXML
	private void closeErrorMessagePane() {
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
		System.out.println(newUsername.getText());
		try {
			myGrades = new Student(newUsername.getText());
			loadUserData();
			reloadGUI();
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig brukernavn", "Du m� skrive inn et brukernavn f�r du kan bytte bruker.");
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
				showErrorMessage("Feil", "Noe gikk galt som hindret oss � lage filen "+fileName+".\nKontroller at filplassering er skrivbar og pr�v igjen.");
				g.printStackTrace();
			}
		}
	}
	
	@FXML
	private void loadFile() {
		String fileName = loadPaneFileName.getText();
		SaveFileCSV fileLoader = new SaveFileCSV();
		try {
			myGrades = new Student("UserImport");
			fileLoader.load(fileName, myGrades);
			loadFilePaneToggle();
			reloadGUI();
		} catch (FileNotFoundException e) {
			showErrorMessage(fileName+" ikke funnet.", "Filen "+fileName+" ble ikke funnet.\nSjekk om du skrev inn riktig navn og pr�v igjen.");
			e.printStackTrace();
		} catch (IOException f) {
			try  {
				fileLoader.load(fileName, myGrades);
				saveFilePaneToggle();
				reloadGUI();
			} catch(IOException g) {
				showErrorMessage("Feil", "Noe gikk galt som hindret oss � hente filen "+fileName+".\nKontroller at filplassering er lesbar, at du skrev filnavn riktig, og pr�v igjen.");
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
			showErrorMessage("Kunne ikke lagre", "Noe gikk galt under lagringen av data. Vennligst pr�v igjen senere.");
			e.printStackTrace();
		} catch (IOException f) {
			try  {
				dataSaver.save(myGrades);
				saveFilePaneToggle();
				reloadGUI();
			} catch(IOException g) {
				showErrorMessage("Feil", "Noe gikk galt som hindret oss � lage filen "+myGrades.getPersonName()+".\nKontroller at filplassering er skrivbar og pr�v igjen.");
				g.printStackTrace();
			}
		}
	}
	
	@FXML
	private void loadUserData() {
		SaveUserData dataLoader = new SaveUserData();
		try {
			dataLoader.load(myGrades);
		} catch (FileNotFoundException e) {
			showErrorMessage("Data for "+myGrades.getPersonName()+" ikke funnet.", "Kunne ikke finne data for "+myGrades.getPersonName()+".\nSjekk om du har skrevet inn riktig navn og pr�v igjen.");
			e.printStackTrace();
		}
	}
	
	@FXML
	/**
	 * Private function to easily refresh GUI elements and clear textFields.
	 */
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
		usernameChangeField.clear();
	}
	
	@FXML
	void printCourseInfo() {
		userNameField.setText(myGrades.getPersonName());
		gradeTextField.setText(myGrades.outputFormattedGradesString(myGrades));
	}
	
	@FXML
	void changeUsernameConfirm() {
		try {
			System.out.println("Saved");
			myGrades.setPersonName(usernameChangeField.getText());
			System.out.println(usernameChangeField.getText()+"-"+loadPaneFileName.getText()+"-"+savePaneFileName.getText());
			System.out.println("Name changed - "+usernameChangeField.getText());
			usernameChangePaneToggle();
			System.out.println("Toggled");
			reloadGUI();
			System.out.println("Reloaded GUI");
		} catch (IllegalArgumentException e) {
			showErrorMessage("Ugyldig brukernavn", "Ditt nye brukernavn kan ikke v�re \"null\" eller tomt.\nPr�v p� nytt.");
		}
	}
	
	@FXML
	void usernameChangePaneToggle() {
		if (usernameChangePane.isDisabled()) {
			IOPanes.setDisable(false);
			IOPanes.toFront();
			usernameChangePane.setDisable(false);
			usernameChangePane.setOpacity(1);
			usernameChangePane.toFront(); // Bring the pane to front.
		} else if (!usernameChangePane.isDisabled()) {
			IOPanes.setDisable(true);
			IOPanes.toBack();
			usernameChangePane.setDisable(true);
			usernameChangePane.setOpacity(0);
			usernameChangePane.toBack(); 
		}
	}
	
	@FXML
	public void initTableView() {
		// Setup of the 4 columns in the tableView.
		courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
		courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
		courseGradeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseGrade"));
		coursePointsColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("coursePoints"));
		
		// ObservableList<Course> is retrieved by Student object and added to table.
		contentTable.setItems(myGrades.getObservableListOfCourses());
		contentTable.getSortOrder().add(courseCodeColumn); // Default sorting by Course code.
	}
	
	@FXML
	public void exportFileChooser() {
		// Making Filechooser and setup.
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow folders to be selected.
		fileChooser.setDialogTitle("Velg hvilken mappe du vil lagre dine karakterer.");
		fileChooser.setPreferredSize(new Dimension(720, 480));
		fileChooser.setApproveButtonText("Lagre her");
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		// if "Lagre her" has been clicked.
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsoluteFile().getAbsolutePath();
			String fileName = savePaneFileName.getText();
			// If no filled in fileName
			if (fileName.equals("")) {
				showErrorMessage("Ingen filnavn", "Du m� skrive inn hvilket navn du vil gi filen f�r du pr�ver � lagre.");
				return;
			}
			SaveFileCSV fileSaver = new SaveFileCSV();
			try {
				fileSaver.save(filePath, fileName, myGrades);
				saveFilePaneToggle();
				reloadGUI();
			} catch (IOException f) {
				// If IOException try same action 1 more time before displaying error.
				try  {
					fileSaver.save(fileName, myGrades);
					saveFilePaneToggle();
					reloadGUI();
				} catch(IOException g) {
					showErrorMessage("Feil", "Noe gikk galt som hindret oss � lage filen "+fileName+".\nKontroller at filplassering er skrivbar og pr�v igjen.");
					g.printStackTrace();
				}
			} 
		}
	}
	
	@FXML
	public void importFileChooser() {
		// Making Filechooser and setup.
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Only allow import of files.
		fileChooser.setDialogTitle("Velg fil du vil importere til MyGrades");
		fileChooser.setPreferredSize(new Dimension(720, 480));
		fileChooser.setApproveButtonText("Importer denne filen");
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		// if "Importer denne filen" has been clicked.
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsoluteFile().getAbsolutePath();
			String fileName = fileChooser.getSelectedFile().getName();
			SaveFileCSV fileLoader = new SaveFileCSV();
			try {
				myGrades = new Student("UserImport");
				fileLoader.loadSpecifiedFile(filePath, myGrades);
				loadFilePaneToggle();
				reloadGUI();
			} catch (FileNotFoundException e) {
				showErrorMessage(fileName+" ikke funnet.", "Filen "+fileName+" ble ikke funnet.\nSjekk om du skrev inn riktig navn og pr�v igjen.");
				e.printStackTrace();
			} catch (IOException f) {
				// If IOException try same action 1 more time before displaying error.
				try  {
					fileLoader.load(fileName, myGrades);
					saveFilePaneToggle();
					reloadGUI();
				} catch(IOException g) {
					showErrorMessage("Feil", "Noe gikk galt som hindret oss � hente filen "+fileName+".\nKontroller at filplassering er lesbar, at du skrev filnavn riktig, og pr�v igjen.");
					g.printStackTrace();
				}
			}
		}
	}
}
