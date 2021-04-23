package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadUserData {

	public void load(Student readToStudent) throws FileNotFoundException {
		
		// Check if the folders exist.
		File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Data\\");
		if (myFilePath.mkdirs()) {
			System.out.println("Data folder not found. New folder created.");
		}
		
		String fileName = readToStudent.getPersonName();
		// Locate the .csv file 
		File mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".csv");
		if (!mySaveFile.exists()) {
			throw new FileNotFoundException("File not found!");
		}
		
//		try (Scanner CSVParser = new Scanner(mySaveFile)) {
//			Course newCourse;
//			char courseGrade;
//			double coursePoints;
//			
//			while (CSVParser.hasNextLine()) {
//				String currentLine = CSVParser.nextLine();
//				System.out.println(currentLine);
//				if (currentLine.startsWith("-")) {
//					int startIndex = currentLine.indexOf("PersonName")+10;
//					int endIndex = currentLine.indexOf("*");
//					readToObject.setPersonName(currentLine.substring(startIndex, endIndex));
//				} else if (currentLine.startsWith("_")) {
//					continue;
//				} else {
//				String LineFields [] = currentLine.split(",", 4);
//				courseGrade = LineFields[2].charAt(0);
//				coursePoints = Double.parseDouble(LineFields[3]);
//				newCourse = new Course(LineFields[0], LineFields[1], courseGrade, coursePoints);
//				readToObject.addNewGrade(newCourse);
//				}
//			}
//			CSVParser.close();
//		}
	}
	public static void main(String[] args) {
		
	}
}