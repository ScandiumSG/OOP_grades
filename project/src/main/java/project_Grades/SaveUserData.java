package project_Grades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveUserData {

	/**
	 * Print all courses in the Student object sent to a .MGD file in the User/GradesApplication/UserData folder.
	 * @param fileName A string that specifies what the name of the output .MGD will be called.
	 * @param studentToSave The Student object we wish to convert into a .MGD file.
	 * @throws FileNotFoundException If file not found
	 * @throws IOException If program cannot make a new file or cannot make required folders. Check if area is writeable.
	 */
	public void save(Student studentToSave) throws FileNotFoundException, IOException{
			// Make folders if appropriate folders don't exist.
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\UserData\\");
			if (myFilePath.mkdirs()) {
				System.out.println("New folder created.");
			} else if (!myFilePath.exists()) {
				throw new IOException("Unable to create a new folder.");
			}
			
			String fileName = studentToSave.getPersonName();
			fileName = fileName.replace(" ", "_"); // Replace whitespace with "_".
			// Make the .csv file 
			// Based on: https://www.w3schools.com/java/java_files_create.asp
			File mySaveFile = new File(myFilePath.getAbsolutePath()+"\\"+fileName+".MGD");
			if (mySaveFile.exists()) {
				this.deleteFile(studentToSave);
				this.save(studentToSave);
			} else if (!mySaveFile.createNewFile()) {
				throw new IOException("Could not create a new user data file.");
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
	}
	
	// Allows for deletion of file based on fileName.
	// Intended use: Tidy up after test files are made with JUnit. No other use currently.
	/**
	 * A method to delete a specific file in the User/GradesApplication/UserData folder. Intended only for testing purposes. 
	 * @param fileName The name of the file you wish to delete.
	 * @throws FileNotFoundException If the entered fileName does no correspond to a existing file.
	 */
	public void deleteFile(Student studentToDelete) throws FileNotFoundException, IOException{
			File myFilePath = new File(System.getProperty("user.home")+"\\GradesApplication\\UserData\\");
			String fullFilePath;
			String fileName = studentToDelete.getPersonName();
			fileName = fileName.replace(" ", "_");
			fullFilePath = myFilePath.getAbsolutePath()+"\\"+fileName+".MGD";
			File mySaveFile = new File(fullFilePath);
			if (!mySaveFile.exists()) {
				throw new FileNotFoundException("Could not delete file \""+fileName+"\"");
			} else if (!mySaveFile.delete()) {
				throw new IOException("Could not delete the file \"+fileName+\"");
			} else {
//				System.out.println("File \""+fileName+"\" successfully deleted.");
			}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Student testPerson = new Student("Ola Nordmann");
		testPerson.addNewGrade("TestCourse01", "MMM0001", "B");
		testPerson.addNewGrade("TestCourse02", "MMM0002", "C");
		testPerson.addNewGrade("TestCourse03", "MMM0003", "D");
		
		SaveUserData mySave = new SaveUserData();
		mySave.save(testPerson);
//		mySave.deleteFile(testPerson);
	}
}
