package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFileCSV {

	public void save(String fileName, PersonGrades courseCollection) throws FileNotFoundException{
		
		// Make folders if appropriate folders don't exist.
		File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\");
		if (myFilePath.mkdirs()) {
			System.out.println("New folder created.");
		} else {
			System.out.println("Path location already exist, no new folders made.");
		}
		
		// Make the .csv file 
		// Based on: https://www.w3schools.com/java/java_files_create.asp
		File mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".csv");
		try {
			if (mySaveFile.createNewFile()) {
				System.out.println("File made: "+mySaveFile.getName());
			} else {
				System.out.println("The file already existed.");
			}
		} catch (IOException e) {
			System.out.println("Error in creation of new file.");
			e.printStackTrace();
		}
		
		// Retrieve information and write to .csv file
		PrintWriter fileWriter = new PrintWriter(mySaveFile);
		fileWriter.print("-PersonName:"+courseCollection.getPersonName()+"*");
		fileWriter.println();
		fileWriter.print("_CourseName,CourseCode,CourseGrade,CoursePoints");
		fileWriter.println();
		for (int i = 0; i < courseCollection.getCourseAmount(); i++) {
			Grade currentCourse = courseCollection.getCourse(i);
			String printLine = currentCourse.getCourseName()+","+currentCourse.getCourseCode()+","+String.valueOf(currentCourse.getCourseGrade()+","+String.valueOf(currentCourse.getCoursePoints()));
			fileWriter.print(printLine);
			fileWriter.println();
		}
		// Close the fileWriter
		fileWriter.close();
	}
	
	// Allows for deletion of file based on fileName.
	// Intended use: Tidy up after test files are made with JUnit. No other use currently.
	public void deleteFile(String fileName) {
		File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\");
		File mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".csv");
		mySaveFile.deleteOnExit();
	}
	
	public static void main(String[] args) {
		PersonGrades testPerson = new PersonGrades("Test");
		testPerson.addNewGrade("TestCourse01", "MMM0001", 'B');
		testPerson.addNewGrade("TestCourse02", "MMM0002", 'C');
		
		SaveFileCSV mySave = new SaveFileCSV();
		try {
			mySave.save("TestSaving", testPerson);
			mySave.deleteFile("TestSaving");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
