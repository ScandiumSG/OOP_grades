package grades.peristance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import grades.core.Course;
import grades.core.Student;
import grades.persistance.SaveFileCSV;

import org.junit.jupiter.api.BeforeEach;

public class SaveFileCSVTest {
	private Student testPerson = new Student("writeTestCSV");

	@BeforeEach
	public void setUp() {
		Course course1 = new Course("Testemne1", "ABC1001", "A", 7.5);
		testPerson.addNewGrade(course1);
	}

	@Test
	@DisplayName("Make and delete a CSV export file.")
	public void writeToFile() throws FileNotFoundException, IOException {
		String testFile = "TestFileWriteCSV";
		SaveFileCSV writerCSV = new SaveFileCSV();
		writerCSV.save(testFile, testPerson);
		File testFileCSV = new File(
				System.getProperty("user.home") + "\\GradesApplication\\Export\\" + testFile + ".csv");
		Assertions.assertTrue(testFileCSV.exists(), "File was not properly created.");
		writerCSV.deleteFile(testFile);
		Assertions.assertFalse(testFileCSV.exists(), "File was not properly deleted.");
	}

	@Test
	@DisplayName("Can filename contain .csv")
	public void writeToCSVFile() throws FileNotFoundException, IOException {
		String testFile = "TestFileWriteCSV.csv";
		SaveFileCSV writerCSV = new SaveFileCSV();
		writerCSV.save(testFile, testPerson);
		File testFileCSV = new File(System.getProperty("user.home") + "\\GradesApplication\\Export\\" + testFile);
		Assertions.assertTrue(testFileCSV.exists(), "File was not properly created.");
		writerCSV.deleteFile(testFile);
		Assertions.assertFalse(testFileCSV.exists(), "File was not properly deleted.");
	}

	@Test
	@DisplayName("Try to deleted nonexistant file.")
	public void deleteFile() throws FileNotFoundException, IOException {
		String testFile = "DeleteNonExistantFile";
		SaveFileCSV writerCSV = new SaveFileCSV();
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			writerCSV.deleteFile(testFile);
		}, "Should not have been able to delete a non-existant file.");
	}
}
