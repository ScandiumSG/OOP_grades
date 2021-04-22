package project_Grades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PersonGradesTest {
	private Student testPerson = new Student("JUnitTesting");
	
	@BeforeEach
	public void setUp() {
		Course course1 = new Course("Testemne1", "ABC1001", 'A');
		Course course2 = new Course("Testemne2", "ABC1002", 'B');
		Course course3 = new Course("Testemne3", "ABC1003", 'C');
		Course course4 = new Course("Testemne4", "ABC1004", 'D');
		testPerson.addNewGrade(course1);
		testPerson.addNewGrade(course2);
		testPerson.addNewGrade(course3);
		testPerson.addNewGrade(course4);
	}
	
	@Test
	@DisplayName("Get and set person name")
	void testPersonName() {
		Assertions.assertTrue(testPerson.getPersonName().equals("JUnitTesting"), "Wrong name of test person.");
		testPerson.setPersonName("Jens Jensen");
		Assertions.assertTrue(testPerson.getPersonName().equals("Jens Jensen"), "Person name did not change to correct value.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> 
			{testPerson.setPersonName(null);}, "Should not be able to set person name to be 'null'");
	}

	@Test
	@DisplayName("Get best and worst grade")
	void testBestAndWorst() {
		Assertions.assertTrue(testPerson.getWorstGrade() == 'D', "Incorrect worst grade, should be D");
		Assertions.assertTrue(testPerson.getBestGrade() == 'A', "Incorrect best grade, should be A");
	}
	
	@Test
	@DisplayName("Check average grade calculation")
	void testAverageGrade() {
		Student averageC = new Student("averageC");
		averageC.addNewGrade("courseA", "AAA0001", 'A');
		averageC.addNewGrade("courseC", "AAA0001", 'C');
		averageC.addNewGrade("courseE", "AAA0002", 'E');
		Assertions.assertEquals('C', averageC.getAverageGrade());
		Assertions.assertEquals('B', testPerson.getAverageGrade());
	}
	
	@Test
	@DisplayName("Return grades.")
	void testReturnGrades() {
		Course searchGrade = new Course("Returnable grade", "DDD9876", 'B');
		testPerson.addNewGrade(searchGrade);
		Assertions.assertEquals(searchGrade, testPerson.getCourse(searchGrade), "Didnt retrieve the correct grade");
		testPerson.removeCourse(searchGrade);
		Assertions.assertEquals(null, testPerson.getCourse(searchGrade), "Found a course that should have been removed.");
	}
	
	@Test
	@DisplayName("Find number of courses")
	void testGetCourseAmounts() {
		Assertions.assertEquals(4, testPerson.getCourseAmount(), "Didnt return correct number of courses (4).");
		Course anotherGrade = new Course("Course09", "OOO1111", 'C');
		testPerson.addNewGrade(anotherGrade);
		Assertions.assertEquals(5,  testPerson.getCourseAmount(), "Didnt return correct number of courses (5).");
		Student newStudent = new Student("NewStudent");
		Assertions.assertEquals(-1, newStudent.getCourseAmount(), "Should have returned -1 for student without any courses.");
	}
	
	@Test
	@DisplayName("Get total course points")
	void testTotalCoursePoints() {
		Student pointPerson = new Student("StudentWithPoints");
		pointPerson.addNewGrade("SomeCourse", "ABC1234", 'C', 5.0);
		Assertions.assertEquals(5, pointPerson.getTotalCoursePoints(), "Should have 5 course points.");
		pointPerson.getCourse(0).setCoursePoints(20);
		Assertions.assertEquals(20,  pointPerson.getTotalCoursePoints(), "Did not change course points correctly, should be 20.");
	}
}
