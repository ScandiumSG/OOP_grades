package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveUserData {

	public void save(Student studentToSave) throws FileNotFoundException{
		try {
			// Make folders if appropriate folders don't exist.
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Data\\");
			if (myFilePath.mkdirs()) {
				System.out.println("New folder created.");
			}
			
			String fileName = studentToSave.getPersonName();
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
			fileWriter.print("-PersonName:"+studentToSave.getPersonName()+"*");
			fileWriter.println();
			fileWriter.print("_CourseName,CourseCode,CourseGrade,CoursePoints");
			fileWriter.println();
			for (int i = 0; i < studentToSave.getCourseAmount(); i++) {
				Course currentCourse = studentToSave.getCourse(i);
				String printLine = currentCourse.getCourseName()+","+currentCourse.getCourseCode()+","+String.valueOf(currentCourse.getCourseGrade()+","+String.valueOf(currentCourse.getCoursePoints()));
				fileWriter.print(printLine);
				fileWriter.println();
			}
			// Close the fileWriter
			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Allows for deletion of file based on fileName.
	// Intended use: Tidy up after test files are made with JUnit. No other use currently.
	public void deleteFile(Student studentToDelete) {
		try {
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Data\\");
			String fullFilePath;
			String fileName = studentToDelete.getPersonName();
			
			fullFilePath = myFilePath.getAbsolutePath()+"\\"+fileName+".csv";
			File mySaveFile = new File(fullFilePath);
			if (mySaveFile.delete()) {
				System.out.println("File \""+fileName+"\" successfully deleted.");
			} else {
				throw new FileNotFoundException("Could not delete file \""+fileName+"\"");
			}
			mySaveFile.deleteOnExit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Student testPerson = new Student("Test");
		testPerson.addNewGrade("TestCourse01", "MMM0001", 'B');
		testPerson.addNewGrade("TestCourse02", "MMM0002", 'C');
		
		SaveUserData mySave = new SaveUserData();
		try {
			mySave.save(testPerson);
			mySave.deleteFile(testPerson);
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found. Process terminated.");
			e.printStackTrace();
		}
	}
}
