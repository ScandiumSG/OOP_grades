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
	 * @throws IOException If the write area is inaccessable or read-only a IOException will be thrown.
	 */
	public void save(String fileName, Student courseCollection) throws IOException{
			// Make folders if appropriate folders don't exist.
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Export\\");
			if (myFilePath.mkdirs()) {
				System.out.println("New folder created.");
			} else if (!myFilePath.exists()) {
				throw new IOException("Unable to create a new folder.");
			}
			
			// Make the .csv file 
			// Based on: https://www.w3schools.com/java/java_files_create.asp
			File mySaveFile;
			if (fileName.contains(".csv")) {
				mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName);
			} else {
				mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".csv");
			}
			
			if (mySaveFile.createNewFile()) {
				System.out.println("File made: "+mySaveFile.getName());
			} else {
				System.out.println("The file already existed.");
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
		}
	
	// Allows for deletion of file based on fileName.
	// Intended use: Tidy up after test files are made with JUnit. No other use currently.
	/**
	 * A method to delete a specific file in the User/GradesApplication/Export folder. Intended only for testing purposes. 
	 * @param fileName The name of the file you wish to delete.
	 * @throws FileNotFoundException If the entered fileName does no correspond to a existing file.
	 */
	public void deleteFile(String fileName) throws FileNotFoundException {
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\Export\\");
			String fullFilePath;
			if (fileName.contains(".csv")) {
				fullFilePath = myFilePath.getAbsolutePath()+"\\"+fileName;
			} else {
				fullFilePath = myFilePath.getAbsolutePath()+"\\"+fileName+".csv";
			}
			File mySaveFile = new File(fullFilePath);
			if (!mySaveFile.exists()) {
				throw new FileNotFoundException("The file you attempted to delete does not exist!");
			} else if (!mySaveFile.delete()) {
				throw new FileNotFoundException("Could not delete file \""+fileName+"\"");
			} else {
				System.out.println("File \""+fileName+"\" successfully deleted.");
			}
			mySaveFile.deleteOnExit();
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
		} catch (IOException f) {
			
		}
	}
}
