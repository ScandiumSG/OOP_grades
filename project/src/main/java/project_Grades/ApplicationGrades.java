package project_Grades;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationGrades extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("BasicPanel.fxml"));
		primaryStage.setTitle("Stian K. Gaustad Project - Grades");
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(ApplicationGrades.class, args);
	}
}
