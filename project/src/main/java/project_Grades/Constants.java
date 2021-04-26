package project_Grades;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Constants {

	/**
	 * Function that populates valid grades for a ChoiceBox.
	 * @return All valid options for ChoiceBox.
	 */
	public static ObservableList<String> getValidGrades() {
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
}
