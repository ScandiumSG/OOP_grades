package grades.persistance;

import grades.core.Course;
import grades.core.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SaveFileCSV implements SaveHandler {

    /**
     * Save method that uses the default file location to store a new CSV file.
     * @param fileName String of the chosen name of the output CSV file.
     * @param courseCollection The student object that is to be output as CSV file.
     */
    public void save(String fileName, Student courseCollection)
            throws IOException {
        // Make folders if appropriate folders don't exist.
        File myFilePath = new File(System.getProperty("user.home")
                + "\\GradesApplication\\Export\\");
        if (myFilePath.mkdirs()) {
            System.out.println("New folder created.");
        } else if (!myFilePath.exists()) {
            throw new IOException("Unable to create a new folder.");
        }

        // Make the .csv file
        // Based on: https://www.w3schools.com/java/java_files_create.asp
        File mySaveFile;
        if (fileName.contains(".csv")) {
            mySaveFile = new File(myFilePath.getAbsolutePath()
                    + "\\"
                    + fileName);
        } else {
            mySaveFile = new File(myFilePath.getAbsolutePath()
                    + "\\"
                    + fileName
                    + ".csv");
        }

        // Retrieve information and write to .csv file
        PrintWriter fileWriter = new PrintWriter(mySaveFile, StandardCharsets.UTF_8);
        fileWriter.print("::PersonName: "
                + courseCollection.getPersonName()
                + ";");
        fileWriter.println();
        fileWriter.print("_CourseName,CourseCode,CourseGrade,CoursePoints");
        fileWriter.println();
        for (int i = 0; i < courseCollection.getCourseAmount(); i++) {
            Course currentCourse = courseCollection.getCourse(i);
            String printLine = currentCourse.getCourseName()
                    + ","
                    + currentCourse.getCourseCode()
                    + ","
                    + currentCourse.getCourseGrade()
                    + ","
                    + String.valueOf(currentCourse.getCoursePoints());
            fileWriter.print(printLine);
            fileWriter.println();
        }
        // Close the fileWriter
        fileWriter.close();
    }

    /**
     * Alternate save method that uses a provided filePath, allowing the user to specify a separate 
     * location to store files outside of the default position.
     * @param filePath String of the path towards the desired directory to store files.
     * @param fileName String of the chosen name of the output CSV file.
     * @param courseCollection The student object that is to be output as CSV file.
     */
    public void save(String filePath, String fileName,
            Student courseCollection) throws IOException {
        // Make folders if appropriate folders don't exist.
        File myFilePath = new File(filePath);
        if (myFilePath.mkdirs()) {
            System.out.println("New folder created.");
        } else if (!myFilePath.exists()) {
            throw new IOException("Unable to create a new folder.");
        }

        // Make the .csv file
        // Based on: https://www.w3schools.com/java/java_files_create.asp
        File mySaveFile;
        if (fileName.contains(".csv")) {
            mySaveFile = new File(myFilePath.getAbsolutePath()
                    + "\\"
                    + fileName);
        } else {
            mySaveFile = new File(myFilePath.getAbsolutePath()
                    + "\\"
                    + fileName
                    + ".csv");
        }

        // Retrieve information and write to .csv file
        PrintWriter fileWriter = new PrintWriter(mySaveFile, StandardCharsets.UTF_8);
        fileWriter.print("::PersonName: "
                + courseCollection.getPersonName()
                + ";");
        fileWriter.println();
        fileWriter.print("_CourseName,CourseCode,CourseGrade,CoursePoints");
        fileWriter.println();
        for (int i = 0; i < courseCollection.getCourseAmount(); i++) {
            Course currentCourse = courseCollection.getCourse(i);
            String printLine = currentCourse.getCourseName()
                    + "," + currentCourse.getCourseCode()
                    + ","
                    + currentCourse.getCourseGrade()
                    + ","
                    + String.valueOf(currentCourse.getCoursePoints());
            fileWriter.print(printLine);
            fileWriter.println();
        }
        // Close the fileWriter
        fileWriter.close();
    }

    // Allows for deletion of file based on fileName.
    // Intended use: Tidy up after test files are made with JUnit. No other use
    // currently.
    /**
     * Method that allows deletion of the specified file. Intended to be used to deleted files
     * generated by the JUnit tests.
     * @param fileName The name of the file to be deleted.
     */
    public void deleteFile(String fileName) throws FileNotFoundException {
        File myFilePath = new File(System.getProperty("user.home")
                + "\\GradesApplication\\Export\\");
        String fullFilePath;
        if (fileName.contains(".csv")) {
            fullFilePath = myFilePath.getAbsolutePath()
                    + "\\"
                    + fileName;
        } else {
            fullFilePath = myFilePath.getAbsolutePath()
                    + "\\"
                    + fileName
                    + ".csv";
        }
        File mySaveFile = new File(fullFilePath);
        if (!mySaveFile.exists()) {
            throw new FileNotFoundException(
                    "The file you attempted to delete does not exist!");
        } else if (!mySaveFile.delete()) {
            throw new FileNotFoundException(
                    "Could not delete file \"" + fileName + "\"");
        } else {
            // System.out.println(
            // "File \""+fileName+"\" successfully deleted.");
        }
        mySaveFile.deleteOnExit();
    }

    /**
     * Load the data from a locally stored file into the provided Student object. 
     * @param fileName Name of the file that is to be loaded.
     * @param readToObject The student object to load courses to.
     */
    public void load(String fileName, Student readToObject)
            throws FileNotFoundException, IOException {
        // Check if the folders exist.
        File myFilePath = new File(System.getProperty("user.home")
                + "\\GradesApplication\\Import\\");
        if (myFilePath.mkdirs()) {
            System.out.println("New folder created.");
        } else if (!myFilePath.exists()) {
            throw new IOException("Could not find or create Import folder.");
        }

        // Locate the .csv file
        File mySaveFile = new File(myFilePath.getAbsolutePath()
                + "\\"
                + fileName
                + ".csv");
        if (!mySaveFile.exists()) {
            throw new FileNotFoundException("File not found!");
        }

        try (Scanner CSVParser = new Scanner(mySaveFile, StandardCharsets.UTF_8)) {
            Course newCourse;
            double coursePoints;
            while (CSVParser.hasNextLine()) {
                String currentLine = CSVParser.nextLine();
                if (currentLine.startsWith("::")) {
                    int startIndex = currentLine.indexOf("PersonName")
                            + 11;
                    int endIndex = currentLine.indexOf(";");
                    readToObject.setPersonName(
                            currentLine.substring(startIndex, endIndex));
                    continue;
                } else if (currentLine.startsWith("_")) {
                    continue;
                } else {
                    // Substrings trimmed incase user input a
                    // file with trailing or leading whitespace.
                    String[] lineFields = currentLine.split(",", 4);
                    coursePoints = Double.parseDouble(lineFields[3].trim());
                    newCourse = new Course(lineFields[0].trim(),
                            lineFields[1].trim(),
                            lineFields[2].trim(),
                            coursePoints);
                    readToObject.addNewGrade(newCourse);
                }
            }
            CSVParser.close();
        }
    }

    /**
     * Load saved courses from spesified locally stored file into the specific Student 
     * object provided to this method.
     * @param pathName The pathname of the specified file.
     * @param readToObject The student object the found file is to be read into.
     */
    public void loadSpecifiedFile(String pathName, Student readToObject)
            throws FileNotFoundException, IOException {
        // Locate the .csv file
        File mySaveFile = new File(pathName);
        if (!mySaveFile.exists()) {
            throw new FileNotFoundException("File not found!");
        }

        try (Scanner CSVParser = new Scanner(mySaveFile, StandardCharsets.UTF_8)) {
            Course newCourse;
            double coursePoints;
            while (CSVParser.hasNextLine()) {
                String currentLine = CSVParser.nextLine();
                if (currentLine.startsWith("::")) {
                    int startIndex = currentLine.indexOf("PersonName") + 11;
                    int endIndex = currentLine.indexOf(";");
                    readToObject.setPersonName(
                            currentLine.substring(startIndex, endIndex));
                    continue;
                } else if (currentLine.startsWith("_")) {
                    continue;
                } else {
                    // Substrings trimmed incase user input a file with
                    // trailing or leading whitespace.
                    String[] lineFields = currentLine.split(",", 4);
                    coursePoints = Double.parseDouble(lineFields[3].trim());
                    newCourse = new Course(lineFields[0].trim(),
                            lineFields[1].trim(),
                            lineFields[2].trim(),
                            coursePoints);
                    readToObject.addNewGrade(newCourse);
                }
            }
            CSVParser.close();
        }
    }

    /**
     * Main method to quickly test functionality.
     * @param args None
     * @throws FileNotFoundException From called methods.
     * @throws IOException From called methods.
     */
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
            f.printStackTrace();
        }

        Student testReading = new Student("testReading");
        SaveFileCSV load = new SaveFileCSV();
        try {
            load.load("TestLoading", testReading);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found. Process terminated.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
