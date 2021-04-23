package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadUserData {

	public void load(Student readToStudent) throws FileNotFoundException {
		
		// Check if the folders exist.
		File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\UserData\\");
		if (myFilePath.mkdirs()) {
			System.out.println("Data folder not found. New folder created.");
		}
		
		String fileName = readToStudent.getPersonName();
		fileName = fileName.replace(" ", "_");
		// Locate the .csv file 
		File userDataFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".data");
		if (!userDataFile.exists()) {
			System.out.println("No user associated with that name.");
		} else if (userDataFile.exists()) {
			try (Scanner userDataParser = new Scanner(userDataFile)) {
				Course newCourse;
				char courseGrade;
				double coursePoints;
				while (userDataParser.hasNextLine()) {
					String currentLine = userDataParser.nextLine();
					if (currentLine.startsWith("ID")) {
						int startIndexID = "ID:".length();
						int endIndexID = currentLine.indexOf(";");
						String parsedStudentName = (currentLine.substring(startIndexID, endIndexID)).replace("_", " ");
						readToStudent.setPersonName(parsedStudentName);
						continue;
					}
					String dataFields[] = currentLine.split("::");
					courseGrade = dataFields[2].charAt(0);
					coursePoints = Double.parseDouble(dataFields[3]);
					newCourse = new Course(dataFields[0], dataFields[1], courseGrade, coursePoints);
					readToStudent.addNewGrade(newCourse);
				}
				userDataParser.close();
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}