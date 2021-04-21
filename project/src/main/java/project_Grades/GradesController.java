package project_Grades;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
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
	TextArea myAverageGradeField;
	
	@FXML
	Tab courseOverview;
	
	@FXML
	Tab gradesGraph;
	
	@FXML
	Tab gradesOverTime;
	
	@FXML
	Button loadFile;
	
	@FXML
	Button saveFile;
	
	@FXML
	Button addCourse;
	
	@FXML
	TextField newCourseName;
	
	@FXML
	TextField newCourseCode;
	
	@FXML
	TextField newCourseGrade;
	
	@FXML
	TextField newCoursePoints;
	
	@FXML
	private void addNewCourse() {
		if (newCoursePoints.getText()=="7.5") {
		myGrades.addNewGrade(newCourseName.getText(), newCourseCode.getText(), newCourseGrade.getText().charAt(0));
		} else {
			myGrades.addNewGrade(newCourseName.getText(), newCourseCode.getText(), newCourseGrade.getText().charAt(0), Double.valueOf(newCoursePoints.getText()));
		}
		calcAverageGrade();
	}
	
	@FXML
	private void initialize() {
		myGrades = new PersonGrades("Ny bruker");
		calcAverageGrade();
	}
	
//	@FXML
//	private void createCourseTiles() {
//		coursePane.setStyle(" -fx-background-color: green");
//		Pane newPane = new Pane();
//		newPane.prefHeight(30);
//		newPane.prefWidth(20);
//		newPane.setTranslateX(40);
//		newPane.setTranslateY(30);
//		newPane.setStyle("-fx-border-color: blue; -fx-border-width: 2px");
//		for (int i=0; i<myGrades.getCourseAmount();i++) {
//
//		}
//	}
	
	@FXML
	void calcAverageGrade() {
		if (myGrades.getCourseAmount()==-1) {
			myAverageGradeField.setText("Hei, "+myGrades.getPersonName()+".\n\nDu har ingen registerte emner.");
		} else {
			myAverageGradeField.setText("Hei, "+myGrades.getPersonName()+".\n\n Din gjennomsnittskarakter er: "+myGrades.getAverageGrade());
//			myAverageGradeField.setText("Hei Stian!\n\nDin gjennomsnittskarakter er: C");	
		}
	}
}
