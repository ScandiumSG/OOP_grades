package project_Grades;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class OptionsController {

	public static ObservableList<String> getValidGrades() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		
		ObservableList<String> availableChoices = FXCollections.observableArrayList(list);
		return availableChoices;
	}
	
	public static String getUserMessage(Student myGrades) {
		String outputMessage = "Hei, "+myGrades.getPersonName();
		return outputMessage;
	}
	
	public static String getCalculationMessage(Student myGrades) {
		String outputMessage;
		if (myGrades.getCourseAmount()==-1) {
			outputMessage = "\nDu har ingen registerte emner.";
		} else {
			outputMessage = "Din gjennomsnittskarakter er: "+myGrades.getAverageGrade()+"\nBeste karakter: "+Character.toString(myGrades.getBestGrade())+"\nVærste karakter: "+Character.toString(myGrades.getWorstGrade());
		}
		return outputMessage;
	}
	
	public static ObservableList<Course> getListOfCourses(Student myGrades) {
		ObservableList<Course> listedCourses = FXCollections.observableArrayList();
		for (int i = 0; i < myGrades.getCourseAmount(); i++) {
			listedCourses.add(myGrades.getCourse(i));
		}
		return listedCourses;
	}
	
	public static void addCourseToStudent(TextField newCourseName, TextField newCourseCode, ChoiceBox<String> newCourseGrade, TextField newCoursePoints, Student myGrades) {
		char courseGrade = newCourseGrade.getSelectionModel().getSelectedItem().charAt(0);
		double coursePoints = Double.valueOf(newCoursePoints.getText());
		Course addedCourse = new Course(newCourseName.getText(), newCourseCode.getText(), courseGrade, coursePoints);
		myGrades.addNewGrade(addedCourse);
	}
	
	public static Course findCourseOnCode(TextField findThisCourse, Student myGrades) {
		Course targetCourse = null;
		for (int i = 0; i < myGrades.getCourseAmount(); i++) {
			if (myGrades.getCourse(i).getCourseCode().equals((findThisCourse.getText()).trim())) {
				targetCourse = myGrades.getCourse(i);
			}
		}
		return targetCourse;
	}
	
}
