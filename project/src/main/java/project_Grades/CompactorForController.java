package project_Grades;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CompactorForController {

	public ObservableList<String> getValidGrades() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("Bestått");
		
		ObservableList<String> availableChoices = FXCollections.observableArrayList(list);
		return availableChoices;
	}
	
	public String getUserMessage(Student myGrades) {
		String outputMessage = "Hei, "+myGrades.getPersonName();
		return outputMessage;
	}
	
	public String getCalculationMessage(Student myGrades) {
		String outputMessage;
		if (myGrades.getCourseAmount()==-1) {
			outputMessage = "\nDu har ingen registerte emner.";
		} else {
			outputMessage = "Din gjennomsnittskarakter er: "+myGrades.getAverageGrade()+"\nBeste karakter: "+myGrades.getBestGrade()+"\nVærste karakter: "+myGrades.getWorstGrade();
		}
		return outputMessage;
	}
	
	public ObservableList<Course> getListOfCourses(Student myGrades) {
		ObservableList<Course> listedCourses = FXCollections.observableArrayList();
		for (int i = 0; i < myGrades.getCourseAmount(); i++) {
			listedCourses.add(myGrades.getCourse(i));
		}
		return listedCourses;
	}
	
	/**
	 * Adds a new Course Object to the student Object.
	 * @param newCourseName A textfield containing the new course name.
	 * @param newCourseCode A textfield containing the new course code.
	 * @param newCourseGrade A choicebox of valid string Grades.
	 * @param newCoursePoints A textfield containing the new course point.
	 * @param myGrades The Student object that the course is added to.
	 */
	public void addCourseToStudent(TextField newCourseName, TextField newCourseCode, ChoiceBox<String> newCourseGrade, TextField newCoursePoints, Student myGrades) {
		double coursePoints = Double.valueOf(newCoursePoints.getText());
		Course addedCourse = new Course(newCourseName.getText(), newCourseCode.getText(), newCourseGrade.getSelectionModel().getSelectedItem(), coursePoints);
		myGrades.addNewGrade(addedCourse);
	}
	
	/**
	 * Find and return a Course object from the assigned Courses to the Student object based on the Course code.
	 * @param findThisCourse The course code that we look for in the Course objects.
	 * @param myGrades The student object that is searched through for the Course.
	 * @return
	 */
	public Course findCourseOnCode(TextField findThisCourse, Student myGrades) throws IllegalArgumentException{
		Course targetCourse = null;
		for (int i = 0; i < myGrades.getCourseAmount(); i++) {
			if (myGrades.getCourse(i).getCourseCode().equals((findThisCourse.getText()).trim())) {
				targetCourse = myGrades.getCourse(i);
			}
		}
		if (targetCourse != null) {
			return targetCourse;
		} else {
			throw new IllegalArgumentException("Could not find the Course with the course code \""+findThisCourse+"\"");
		}
	}
	
}
