package grades.persistance;

import grades.core.Course;
import grades.core.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SaveUserData implements InternalSaveHandler {

    /**
     * A method that takes the provided student object and writes each course to a file. 
     * If file associated with the provided student object already exists this previous file is 
     * deleted to make room for the newer data.
     * @param studentToSave The student object we wish to save the data of.
     */
    public void save(Student studentToSave) throws FileNotFoundException, IOException {
        // Make folders if appropriate folders don't exist.
        File myFilePath = new File(
            System.getProperty("user.home") + "\\GradesApplication\\UserData\\");
        if (myFilePath.mkdirs()) {
            System.out.println("New folder created.");
        } else if (!myFilePath.exists()) {
            throw new IOException("Unable to create a new folder.");
        }

        String fileName = studentToSave.getPersonName();
        fileName = fileName.replace(" ", "_"); // Replace whitespace with "_".
        // Make the .csv file
        // Based on: https://www.w3schools.com/java/java_files_create.asp
        File mySaveFile = new File(myFilePath.getAbsolutePath() + "\\" + fileName + ".MGD");
        if (mySaveFile.exists()) {
            this.deleteFile(studentToSave);
            this.save(studentToSave);
        } else if (!mySaveFile.createNewFile()) {
            throw new IOException("Could not create a new user data file.");
        }

        // Retrieve information and write to .csv file
        PrintWriter studentDataWriter = new PrintWriter(mySaveFile, StandardCharsets.UTF_8);
        studentDataWriter.print("ID:" + studentToSave.getPersonName() + ";");
        studentDataWriter.println();
        for (int i = 0; i < studentToSave.getCourseAmount(); i++) {
            Course currentCourse = studentToSave.getCourse(i);
            String sep = "::";
            String printDataLine = String.join(
                currentCourse.getCourseName(), sep, 
                currentCourse.getCourseCode(), sep, 
                currentCourse.getCourseGrade(), sep, 
                String.valueOf(currentCourse.getCoursePoints()));
            studentDataWriter.print(printDataLine);
            studentDataWriter.println();
        }
        // Close the fileWriter
        studentDataWriter.close();
    }

    /**
     * Delete the local file associated with the provided student. Intended for tidying up after
     * files are made when running JUnit tests.
     * @param studentToDelete The student object associated with the file to delete.
     */
    public void deleteFile(Student studentToDelete) throws FileNotFoundException, IOException {
        File myFilePath = new File(
            System.getProperty("user.home") + "\\GradesApplication\\UserData\\");
        String fullFilePath;
        String fileName = studentToDelete.getPersonName();
        fileName = fileName.replace(" ", "_");
        fullFilePath = myFilePath.getAbsolutePath() + "\\" + fileName + ".MGD";
        File mySaveFile = new File(fullFilePath);
        if (!mySaveFile.exists()) {
            throw new FileNotFoundException("Could not delete file \"" + fileName + "\"");
        } else if (!mySaveFile.delete()) {
            throw new IOException("Could not delete the file \"+fileName+\"");
        } else {
            // System.out.println("File \""+fileName+"\" successfully deleted.");
        }
    }

    /**
     * Load the locally stored courses into the provided Student object. Retrieval
     * is based on the getters of the Student object, cannot be manually overridden.
     * @param readToStudent The student object that saved courses is to be loaded onto.
     */
    public void load(Student readToStudent) throws FileNotFoundException {
        // Check if the folders exist.
        File myFilePath = new File(
            System.getProperty("user.home") + "\\GradesApplication\\UserData\\");
        if (myFilePath.mkdirs()) {
            System.out.println("Data folder not found. New folder created.");
        }

        String fileName = readToStudent.getPersonName();
        fileName = fileName.replace(" ", "_");
        // Locate the .csv file
        File userDataFile = new File(myFilePath.getAbsolutePath() + "\\" + fileName + ".MGD");
        if (!userDataFile.exists()) {
            throw new FileNotFoundException("Could not find this file.");
        } else if (userDataFile.exists()) {
            try (Scanner userDataParser = new Scanner(userDataFile, StandardCharsets.UTF_8)) {
                Course newCourse;
                double coursePoints;
                while (userDataParser.hasNextLine()) {
                    String currentLine = userDataParser.nextLine();
                    if (currentLine.startsWith("ID")) {
                        int startIndexID = "ID:".length();
                        int endIndexID = currentLine.indexOf(";");
                        String parsedStudentName = (
                            currentLine.substring(startIndexID, endIndexID)).replace("_", " ");
                        readToStudent.setPersonName(parsedStudentName);
                        continue;
                    }
                    String[] dataFields = currentLine.split("::");
                    coursePoints = Double.parseDouble(dataFields[3]);
                    newCourse = new Course(
                        dataFields[0], dataFields[1], dataFields[2], coursePoints);
                    readToStudent.addNewGrade(newCourse);
                }
                userDataParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method to quickly test functionality.
     * @param args None
     * @throws FileNotFoundException From called methods.
     * @throws IOException From called methods.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Student testPerson = new Student("Ola Nordmann");
        testPerson.addNewGrade("TestCourse01", "MMM0001", "B");
        testPerson.addNewGrade("TestCourse02", "MMM0002", "C");
        testPerson.addNewGrade("TestCourse03", "MMM0003", "D");

        SaveUserData mySave = new SaveUserData();
        mySave.save(testPerson);
        // mySave.deleteFile(testPerson);
    }
}
