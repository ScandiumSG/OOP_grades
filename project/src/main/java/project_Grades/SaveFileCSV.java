package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFileCSV {

	/**
	 * Print all courses in the Student object sent to a .csv file in the User/GradesApplication/Export folder.
	 * @param fileName A string that specifies what the name of the output .csv will be called.
	 * @param courseCollection The Student object we wish to convert into a .csv file.
	 * @throws FileNotFoundException If file not found
	 */
	public void save(String fileName, Student courseCollection) throws FileNotFoundException{
		try {
			// Make folders if appropriate folders don't exist.
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Export\\");
			if (myFilePath.mkdirs()) {
				System.out.println("New folder created.");
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
			fileWriter.print("::PersonName: "+courseCollection.getPersonName()+";");
			fileWriter.println();
			fileWriter.print("_CourseName,CourseCode,CourseGrade,CoursePoints");
			fileWriter.println();
			for (int i = 0; i < courseCollection.getCourseAmount(); i++) {
				Course currentCourse = courseCollection.getCourse(i);
				String printLine = currentCourse.getCourseName()+","+currentCourse.getCourseCode()+","+currentCourse.getCourseGrade()+","+String.valueOf(currentCourse.getCoursePoints());
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
	/**
	 * A method to delete a specific file in the User/GradesApplication/Export folder. Intended only for testing purposes. 
	 * @param fileName The name of the file you wish to delete.
	 * @throws FileNotFoundException If the entered fileName does no correspond to a existing file.
	 */
	public void deleteFile(String fileName) {
		try {
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Export\\");
			String fullFilePath;
			if (fileName.contains(".csv")) {
				fullFilePath = myFilePath.getAbsolutePath()+"\\"+fileName;
			} else {
				fullFilePath = myFilePath.getAbsolutePath()+"\\"+fileName+".csv";
			}
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
		testPerson.addNewGrade("TestCourse01", "MMM0001", "B");
		testPerson.addNewGrade("TestCourse02", "MMM0002", "C");
		
		SaveFileCSV mySave = new SaveFileCSV();
		try {
			mySave.save("TestSaving", testPerson);
			mySave.deleteFile("TestSaving");
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found. Process terminated.");
			e.printStackTrace();
		}
	}
}
