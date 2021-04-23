package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFileCSV {

	public void load(String fileName, Student readToObject) throws FileNotFoundException {
		
		// Check if the folders exist.
		File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Import\\");
		if (myFilePath.mkdirs()) {
			System.out.println("New folder created.");
		}
		
		// Locate the .csv file 
		File mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".csv");
		if (!mySaveFile.exists()) {
			throw new FileNotFoundException("File not found!");
		}
		
		try (Scanner CSVParser = new Scanner(mySaveFile)) {
			Course newCourse;
			char courseGrade;
			double coursePoints;
			
			while (CSVParser.hasNextLine()) {
				String currentLine = CSVParser.nextLine();
				System.out.println(currentLine);
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
					courseGrade = LineFields[2].trim().charAt(0);
					coursePoints = Double.parseDouble(LineFields[3].trim());
					newCourse = new Course(LineFields[0].trim(), LineFields[1].trim(), courseGrade, coursePoints);
					readToObject.addNewGrade(newCourse);
				}
			}
			CSVParser.close();
		}
	}
	public static void main(String[] args) {
		Student testReading = new Student("testReading");
		LoadFileCSV load = new LoadFileCSV();
		try {
			load.load("TestLoading", testReading);
			System.out.println("Courses read:");
			for (int i = 0; i < testReading.getCourseAmount(); i++) {
				System.out.println(testReading.getCourse(i).toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
