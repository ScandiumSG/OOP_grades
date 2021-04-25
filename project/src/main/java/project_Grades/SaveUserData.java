package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveUserData {

	/**
	 * Print all courses in the Student object sent to a .MGD file in the User/GradesApplication/UserData folder.
	 * @param fileName A string that specifies what the name of the output .MGD will be called.
	 * @param courseCollection The Student object we wish to convert into a .MGD file.
	 * @throws FileNotFoundException If file not found
	 */
	public void save(Student studentToSave) throws FileNotFoundException{
		try {
			// Make folders if appropriate folders don't exist.
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\UserData\\");
			if (myFilePath.mkdirs()) {
				System.out.println("New folder created.");
			}
			
			String fileName = studentToSave.getPersonName();
			fileName = fileName.replace(" ", "_"); // Replace whitespace with "_".
			// Make the .csv file 
			// Based on: https://www.w3schools.com/java/java_files_create.asp
			File mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".MGD");
			try {
				if (mySaveFile.createNewFile()) {
					System.out.println("File made: "+mySaveFile.getName());
				} else {
					System.out.println("The file already existed. Overrode data with new student data");
					this.deleteFile(studentToSave);
					this.save(studentToSave);
				}
			} catch (IOException e) {
				System.out.println("Error in creation of new file.");
				e.printStackTrace();
			}
		
			// Retrieve information and write to .csv file
			PrintWriter studentDataWriter = new PrintWriter(mySaveFile);
			studentDataWriter.print("ID:"+studentToSave.getPersonName()+";");
			studentDataWriter.println();
			for (int i = 0; i < studentToSave.getCourseAmount(); i++) {
				Course currentCourse = studentToSave.getCourse(i);
				String printDataLine = currentCourse.getCourseName()+"::"+currentCourse.getCourseCode()+"::"+currentCourse.getCourseGrade()+"::"+String.valueOf(currentCourse.getCoursePoints());
				studentDataWriter.print(printDataLine);
				studentDataWriter.println();
			}
			// Close the fileWriter
			studentDataWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Allows for deletion of file based on fileName.
	// Intended use: Tidy up after test files are made with JUnit. No other use currently.
	/**
	 * A method to delete a specific file in the User/GradesApplication/UserData folder. Intended only for testing purposes. 
	 * @param fileName The name of the file you wish to delete.
	 * @throws FileNotFoundException If the entered fileName does no correspond to a existing file.
	 */
	public void deleteFile(Student studentToDelete) {
		try {
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\UserData\\");
			String fullFilePath;
			String fileName = studentToDelete.getPersonName();
			fileName = fileName.replace(" ", "_");
			fullFilePath = myFilePath.getAbsolutePath()+"\\"+fileName+".MGD";
			File mySaveFile = new File(fullFilePath);
			if (mySaveFile.delete()) {
				System.out.println("File \""+fileName+"\" successfully deleted.");
			} else {
				throw new FileNotFoundException("Could not delete file \""+fileName+"\"");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Student testPerson = new Student("Ola Nordmann");
		testPerson.addNewGrade("TestCourse01", "MMM0001", "B");
		testPerson.addNewGrade("TestCourse02", "MMM0002", "C");
		testPerson.addNewGrade("TestCourse03", "MMM0003", "D");
		
		SaveUserData mySave = new SaveUserData();
		try {
			mySave.save(testPerson);
//			mySave.deleteFile(testPerson);
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found. Process terminated.");
			e.printStackTrace();
		}
	}
}
