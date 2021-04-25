package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadUserData {

	/**
	 * Load the Student personName and all Course Objects found in a .MGD file in the User/GradesApplication/Export folder.
	 * @param readToStudent The student Object that will add the read data.
	 * @throws FileNotFoundException If file not found
	 */
	public void load(Student readToStudent) throws FileNotFoundException {
		
		// Check if the folders exist.
		File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\UserData\\");
		if (myFilePath.mkdirs()) {
			System.out.println("Data folder not found. New folder created.");
		}
		
		String fileName = readToStudent.getPersonName();
		fileName = fileName.replace(" ", "_");
		// Locate the .csv file 
		File userDataFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".MGD");
		if (!userDataFile.exists()) {
			throw new FileNotFoundException("Could not find this file.");
		} else if (userDataFile.exists()) {
			try (Scanner userDataParser = new Scanner(userDataFile)) {
				Course newCourse;
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
					coursePoints = Double.parseDouble(dataFields[3]);
					newCourse = new Course(dataFields[0], dataFields[1], dataFields[2], coursePoints);
					readToStudent.addNewGrade(newCourse);
				}
				userDataParser.close();
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}