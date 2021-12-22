package grades.peristance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import grades.core.Course;
import grades.core.Student;
import grades.persistance.SaveUserData;
import org.junit.jupiter.api.BeforeEach;

public class SaveUserDataTest {
	private Student testPerson = new Student("UserDataWriterTest");

	@BeforeEach
	public void setUp() {
		Course course1 = new Course("Testemne1", "ABC1001", "A");
		testPerson.addNewGrade(course1);
	}

	@Test
	@DisplayName("Make and delete a MGD (MyGradesData) storage file.")
	public void writeToFile() throws FileNotFoundException, IOException {
		String testFile = "UserDataWriterTest";
		SaveUserData writerUserData = new SaveUserData();
		writerUserData.save(testPerson);
		File testFileUserData = new File(
				System.getProperty("user.home") + "\\GradesApplication\\UserData\\" + testFile + ".MGD");
		Assertions.assertTrue(testFileUserData.exists(), "File was not properly created.");
		writerUserData.deleteFile(testPerson);
		Assertions.assertFalse(testFileUserData.exists(), "File was not properly deleted.");
	}

	@Test
	@DisplayName("Try to deleted nonexistant file.")
	public void deleteFile() throws FileNotFoundException, IOException {
		SaveUserData writerUserData = new SaveUserData();
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			writerUserData.deleteFile(testPerson);
		}, "Should not have been able to delete a non-existant file.");
	}
}
