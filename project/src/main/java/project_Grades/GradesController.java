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

public class GradesController {
	private PersonGrades myGrades;
	
	@FXML
	Pane topPane;
	
	@FXML
	Pane contentPane;
	
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
	Button CloseSaveFilePaneBtn;
	
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
		myGrades = new PersonGrades("Ny bruker");
		saveFilePaneToggle();
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
	

	
//	@FXML
//	private void loadFile() {
//		LoadFileCSV fileLoader = new LoadFileCSV();
//		fileLoader.load(fileName, myGrades);
//	}
	// TODO Connect loading of file
	
	@FXML
	private void saveFilePaneToggle() {
		if (saveFilePane.isDisabled()) {
			saveFilePane.setDisable(false);
			saveFilePane.setOpacity(1);
			saveFilePane.toFront();
		} else if (!saveFilePane.isDisabled()) {
			saveFilePane.setDisable(true);
			saveFilePane.setOpacity(0);
			saveFilePane.toBack();
		}
	}
	
	@FXML
	private void saveFile() {
		String fileName = savePaneFileName.getText();
		SaveFileCSV fileSaver = new SaveFileCSV();
		try {
			fileSaver.save(fileName, myGrades);
		} catch (FileNotFoundException e) {
			// Connect to error pane
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
