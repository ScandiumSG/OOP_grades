package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoadFileCSV {

	/**
	 * Load the Student personName and all Course Objects found in a .csv file in the User/GradesApplication/Import folder.
	 * @param fileName The name of the .csv file that is read from User/GradesApplication/Import folder.
	 * @param readToObject The student Object that will add the read data.
	 * @throws FileNotFoundException If file not found
	 * @throws IOException If program cannot make required folders. Check if area is writeable.
	 */
	public void load(String fileName, Student readToObject) throws FileNotFoundException, IOException {
		
		// Check if the folders exist.
		File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Import\\");
		if (myFilePath.mkdirs()) {
			System.out.println("New folder created.");
		} else if (!myFilePath.exists()) {
			throw new IOException("Could not find or create Import folder.");
		}
		
		// Locate the .csv file 
		File mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".csv");
		if (!mySaveFile.exists()) {
			throw new FileNotFoundException("File not found!");
		}
		
		try (Scanner CSVParser = new Scanner(mySaveFile)) {
			Course newCourse;
			double coursePoints;
			while (CSVParser.hasNextLine()) {
				String currentLine = CSVParser.nextLine();
				if (currentLine.startsWith("::")) {
					int startIndex = currentLine.indexOf("PersonName")+11;
					int endIndex = currentLine.indexOf(";");
					readToObject.setPersonName(currentLine.substring(startIndex, endIndex));
					continue;
				} else if (currentLine.startsWith("_")) {
					continue;
				} else {
					// Substrings trimmed incase user input a file with trailing or leading whitespace.
					String LineFields [] = currentLine.split(",", 4);
					coursePoints = Double.parseDouble(LineFields[3].trim());
					newCourse = new Course(LineFields[0].trim(), LineFields[1].trim(), LineFields[2].trim(), coursePoints);
					readToObject.addNewGrade(newCourse);
				}
			}
			CSVParser.close();
		}
	}
	public static void main(String[] args) throws IOException {
		Student testReading = new Student("testReading");
		LoadFileCSV load = new LoadFileCSV();
			load.load("TestLoading", testReading);
			System.out.println("Courses read:");
			for (int i = 0; i < testReading.getCourseAmount(); i++) {
				System.out.println(testReading.getCourse(i).toString());
			}
	}
}
