package project_Grades;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApplicationMyGrades extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("PrimaryGUI.fxml"));
		primaryStage.setTitle("Stian K. Gaustad Project - Grades");
		primaryStage.getIcons().add(new Image("Images/thumbnail.png"));
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(ApplicationMyGrades.class, args);
	}
}
