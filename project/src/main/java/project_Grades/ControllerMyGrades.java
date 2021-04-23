package project_Grades;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControllerMyGrades {
	private Student myGrades;
	
	@FXML
	Pane topPane;
	
	@FXML
	Pane contentPane;
	
	@FXML
	Pane IOPanes;
	
	@FXML
	Pane loadFilePane;
	
	@FXML
	Pane saveFilePane;
	
	@FXML
	TextArea myAverageGradeField;
	
	@FXML
	Button loadFile;
	
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
	TableView<?> contentTable;
	
	
	@FXML
	private void addNewCourse() {
		myGrades.addNewGrade(newCourseName.getText(), newCourseCode.getText(), newCourseGrade.getSelectionModel().getSelectedItem().charAt(0), Double.valueOf(newCoursePoints.getText()));
		newCourseName.setText(null); newCourseCode.setText(null); newCourseGrade.setValue(null); newCoursePoints.setText(null);
		calcAverageGrade();
	}
	
	@FXML
	private void initialize() {
		myGrades = new Student("Ny bruker");
		saveFilePaneToggle();
		loadFilePaneToggle();
		IOPanes.setDisable(true);
		IOPanes.toBack();
		calcAverageGrade();
		
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		
		ObservableList<String> availableChoices = FXCollections.observableArrayList(list);
		newCourseGrade.setItems(availableChoices);
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
		} catch (FileNotFoundException e) {
			// Connect to error pane
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadFile() {
		String fileName = loadPaneFileName.getText();
		LoadFileCSV fileLoader = new LoadFileCSV();
		try {
			fileLoader.load(fileName, myGrades);
			loadFilePaneToggle();
			calcAverageGrade();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	void calcAverageGrade() {
		if (myGrades.getCourseAmount()==-1) {
			myAverageGradeField.setText("Hei, "+myGrades.getPersonName()+".\n\nDu har ingen registerte emner.");
		} else {
			myAverageGradeField.setText("Hei, "+myGrades.getPersonName()+".\n\n Din gjennomsnittskarakter er: "+myGrades.getAverageGrade());
		}
	}
}
