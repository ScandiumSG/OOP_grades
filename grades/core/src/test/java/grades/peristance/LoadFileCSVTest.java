package grades.peristance;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import grades.core.Course;
import grades.core.Student;
import grades.persistance.SaveFileCSV;
import org.junit.jupiter.api.BeforeEach;

public class LoadFileCSVTest {
	private Student testPerson = new Student("loadTestCSV");
	private String fileName;
	private SaveFileCSV fileLoader;

	@BeforeEach
	public void setUp() throws FileNotFoundException, IOException {
		testPerson = new Student("loadTestCSV");
		fileName = "TestLoading";
		fileLoader = new SaveFileCSV();
		fileLoader.load(fileName, testPerson);
	}

	@Test
	@DisplayName("Load inn \"TestLoading\"")
	public void loadTestLoading() throws FileNotFoundException, IOException {

		Assertions.assertEquals("loadTestPerson", testPerson.getPersonName(), "Import did not change Student name.");
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			fileLoader.load("InvalidTestFileName", testPerson);
		}, "Load in a non-existant file.");

		// Assertions.assertThrows(FileNotFoundException.class, () ->
		// {writerUserData.deleteFile(testPerson);}, "Should not have been able to
		// delete a non-existant file.");
	}

	@Test
	@DisplayName("Check content of Course 1")
	public void checkCourseOne() {
		Course courseOne = testPerson.getCourse(0);
		Assertions.assertEquals("TestCourse01", courseOne.getCourseName());
		Assertions.assertEquals("MMM0001", courseOne.getCourseCode());
		Assertions.assertEquals("B", courseOne.getCourseGrade());
		Assertions.assertEquals(7.5, courseOne.getCoursePoints());
	}

	@Test
	@DisplayName("Check content of Course 2")
	public void checkCourseTwo() {
		Course courseTwo = testPerson.getCourse(1);
		Assertions.assertEquals("TestCourse02", courseTwo.getCourseName());
		Assertions.assertEquals("MMM0002", courseTwo.getCourseCode());
		Assertions.assertEquals("C", courseTwo.getCourseGrade());
		Assertions.assertEquals(7.5, courseTwo.getCoursePoints());
	}
}
