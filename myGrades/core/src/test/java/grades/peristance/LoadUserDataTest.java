package grades.peristance;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import grades.core.Course;
import grades.core.Student;
import grades.persistance.SaveUserData;
import org.junit.jupiter.api.BeforeEach;

public class LoadUserDataTest {
	private Student testPerson;
	private SaveUserData fileLoader;

	@BeforeEach
	public void setUp() throws FileNotFoundException, IOException {
		testPerson = new Student("Ola Nordmann");
		fileLoader = new SaveUserData();
		fileLoader.load(testPerson);
	}

	@Test
	@DisplayName("Load inn \"TestLoading\"")
	public void loadTestLoading() throws FileNotFoundException, IOException {
		Student InvalidStudent = new Student("InvalidStudentLoader");

		Assertions.assertTrue(3 == testPerson.getCourseAmount(), "Check if all three courses loaded");
		Assertions.assertTrue(testPerson.getPersonName().equals("Ola Nordmann"), "Check if person name changed.");
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			fileLoader.load(InvalidStudent);
		}, "Load in a non-existant file.");
	}

	@Test
	@DisplayName("Check correct loaded content of Course 1")
	public void checkCourseOne() {
		Course courseOne = testPerson.getCourse(0);
		Assertions.assertEquals("TestCourse01", courseOne.getCourseName());
		Assertions.assertEquals("MMM0001", courseOne.getCourseCode());
		Assertions.assertEquals("B", courseOne.getCourseGrade());
		Assertions.assertEquals(7.5, courseOne.getCoursePoints());
	}

	@Test
	@DisplayName("Check correct loaded content of Course 2")
	public void checkCourseTwo() {
		Course courseTwo = testPerson.getCourse(1);
		Assertions.assertEquals("TestCourse02", courseTwo.getCourseName());
		Assertions.assertEquals("MMM0002", courseTwo.getCourseCode());
		Assertions.assertEquals("C", courseTwo.getCourseGrade());
		Assertions.assertEquals(7.5, courseTwo.getCoursePoints());
	}

	@Test
	@DisplayName("Check correct loaded content of Course 3")
	public void checkCourseThree() {
		Course courseTwo = testPerson.getCourse(2);
		Assertions.assertEquals("TestCourse03", courseTwo.getCourseName());
		Assertions.assertEquals("MMM0003", courseTwo.getCourseCode());
		Assertions.assertEquals("D", courseTwo.getCourseGrade());
		Assertions.assertEquals(7.5, courseTwo.getCoursePoints());
	}
}
