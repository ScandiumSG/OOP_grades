package project_Grades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GradeTest {
	private Course testCourse;
	
	@BeforeEach
	public void setUp() {
		testCourse = new Course("testCourse", "ABC1234", 'C');
	}
	
	@Test
	@DisplayName("Initialize a grade.")
	void testGrade() {
		Course testCourseInit = new Course("testCourse", "ABC1234", 'C', 7.5);
		Assertions.assertEquals('C', testCourseInit.getCourseGrade(), "Feil karakter for emnet");
		Assertions.assertEquals("testCourse", testCourseInit.getCourseName(), "Feil emne navn.");
		Assertions.assertEquals("ABC1234", testCourseInit.getCourseCode(), "Feil emnekode.");
		Assertions.assertEquals(7.5, testCourseInit.getCoursePoints(), "Feil emne poeng.");
	}

	@Test
	@DisplayName("Subject name validation")
	void testCourseName() {
		Assertions.assertTrue(testCourse.getCourseName() == "testCourse", "Course name not initialized to correct value");
		testCourse.setCourseName("testCourseThree");
		Assertions.assertTrue(testCourse.getCourseName() == "testCourseThree", "Course name not change to correct value");
	}
	
	@Test
	@DisplayName("Subject course code validation")
	void testCourseCode() {
		Assertions.assertTrue(testCourse.getCourseCode().equals("ABC1234"), "Emnekode ble ikke satt korrekt ved intialisering av Grade.");
		testCourse.setCourseCode("CBA4321");
		Assertions.assertTrue(testCourse.getCourseCode().equals("CBA4321"), "Emnekode ble ikke korrekt endret.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> 
			{testCourse.setCourseCode("1234ABC");}, "Validering hindret ikke emnekode med number før bokstaver.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> 
			{testCourse.setCourseCode("1234567");}, "Validering hindret ikke emnekode med bare tall.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> 
			{testCourse.setCourseCode("ABCDEFG");}, "Validering hindret ikke emnekode med bare bokstaver.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> 
			{testCourse.setCourseCode("A1234568967868");}, "Validering hindret ikke emnekode med 1 bokstav så 13 tall");
	}
	
	@Test
	@DisplayName("Subject grade initialization and changes")
	void testCourseGrade() {
		Assertions.assertEquals('C', testCourse.getCourseGrade(), "Initialization of course grade failed.");
		testCourse.setCourseGrade('B');
		Assertions.assertEquals('B', testCourse.getCourseGrade(), "Grade didnt change to B.");
		testCourse.setCourseGrade('a');
		Assertions.assertEquals('A', testCourse.getCourseGrade(), "Lower case grade was not corrected to uppercase.");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> 
			{testCourse.setCourseGrade('G');}, "Grade set to invalid value.");
	}
		
	@Test
	@DisplayName("Subject points initialization and changes")
	void testCoursePoints() {
		Assertions.assertEquals(7.5, testCourse.getCoursePoints(), "Uspesifisert emne poeng var ikke 7.5");
		testCourse.setCoursePoints(10);
		Assertions.assertEquals(10, testCourse.getCoursePoints(), "Endret emne poeng ble ikke satt til 10 på riktig måte");
		Assertions.assertThrows(IllegalArgumentException.class, () -> 
			{testCourse.setCoursePoints(-7.5);}, "Emnepoeng skal ikke kunne settes til en negativ verdi.");
	}
}
