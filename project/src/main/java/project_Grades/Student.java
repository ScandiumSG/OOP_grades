package project_Grades;

import java.util.ArrayList;

public class Student {
	/**
	 * A ArrayList containing all the courses assigned to this Student Object.
	 */
	private ArrayList<Course> myGrades = new ArrayList<Course>();
	
	/**
	 * A string that contains the internal name/nickname for the Student Object.
	 */
	private String studentName;
	
	/**
	 * Initialization of the Student object.
	 * @param name A string that sets the internal name/nickname of the Student object.
	 * @throws IllegalArgumentException if the String name = null.
	 */
	public Student(String name) {
		name = name.trim();
		if (name.equals(null) || name.equals(" ")) {
			throw new IllegalArgumentException("Person name cannot be 'null' or blank");
		} else {
			this.studentName = name;
		}
	}
	
	/**
	 * Return a course Object from the assigned objects to the Student.
	 * @param n A interger corresponding to the Course object index to retrieve from the Student arrayList
	 * @return A Course object.
	 */
	public Course getCourse(int n) {
		return this.myGrades.get(n);
	}
	
	/**
	 * Return a course from the assigned courses of the Student Object.
	 * @param desiredCourse A premade Course object
	 * @return
	 */
	public Course getCourse(Course desiredCourse) {
		Course foundCourse = null;
		for (Course thisCourse: myGrades) {
			if (thisCourse == desiredCourse) {
				foundCourse = thisCourse;
			}
		}
		if (foundCourse != null) {
			return foundCourse;
		} else {
			return null;
		}
		
	}
	
	/**
	 * Remove a coruse from the list of assigned courss to this Student Object.
	 * @param n A int corresponding to Course Object's index that we wish to remove.
	 */
	public void removeCourse(int n) {
		this.myGrades.remove(n);
	}
	
	/**
	 * Remove a course from the list of assigned courses to this Student Object.
	 * @param removeCourse A course object that is to be removed from the Student.
	 */
	public void removeCourse(Course removeCourse) {
		this.myGrades.remove(removeCourse);
	}
	
	/**
	 * Get the amount of Course objects added to this Student.
	 * @return A interger of the amount of courses. If 0 courses add returns -1
	 */
	
	public int getCourseAmount() {
		if (this.myGrades.size()!=0) {
			return this.myGrades.size();
		} else {
			return -1;
		}
	}
	
	/**
	 * Return total course points, inclding points from pass/fail courses.
	 * @return A double equal to amount of course points for the student.
	 */
	public double getTotalCoursePoints() {
		double totalPoints = 0;
		for (Course thisCourse: this.myGrades) {
			totalPoints = totalPoints + thisCourse.getCoursePoints();
		}
		return totalPoints;
	}
	
	/**
	 * Return total course points, excluding points from pass/fail courses.
	 * @return A double equal to amount of course points for the student.
	 */
	public double getTotalUsableCoursePoints() {
		double totalPoints = 0;
		for (Course thisCourse: this.myGrades) {
			if (!thisCourse.getCourseGrade().equals("Bestått")) {
				totalPoints = totalPoints + thisCourse.getCoursePoints();
			}
		}
		return totalPoints;
	}
	
	/**
	 * Change the name of the student for this Object.
	 * @param name String of the new name/nickname for Object subject.
	 * @throws IllegalArgumentException if input name = null.
	 */
	public void setPersonName(String name) {
		name = name.trim();
		if (name.equals(null) || name.equals(" ")) {
			throw new IllegalArgumentException("Person name cannot be 'null' or blank");
		} else {
			this.studentName = name;
		}
	}
	
	/**
	 * Retrieve the set name of the Student.
	 * @return A string of the students name or identifier.
	 */
	public String getPersonName() {
		return this.studentName;
	}
	
	/**
	 * Add a new course to the student by making the new Course Object.
	 * @param subjectName A self-deterined to identify/give a course a nickname.
	 * @param subjectCode A courseCode that is validated before acceptance.
	 * @param grade is a String of A-F or "Bestått".
	 * @throws IllegalArgumentException in case of invalid inputs.
	 */
	public void addNewGrade(String subjectName, String subjectCode, String grade) {
		Course newCourse = new Course(subjectName, subjectCode, grade);
		this.myGrades.add(newCourse);
		// System.out.println("New course added: "+newCourse);
	}
	
	/**
	 * Add a new course to the student by making the new Course Object.
	 * @param subjectName A self-deterined to identify/give a course a nickname.
	 * @param subjectCode A courseCode that is validated before acceptance.
	 * @param grade is a String of A-F or "Bestått".
	 * @param points A double of the points a course would give the student.
	 * @throws IllegalArgumentException in case of invalid inputs.
	 */
	public void addNewGrade(String subjectName, String subjectCode, String grade, double points) {
		Course newCourse = new Course(subjectName, subjectCode, grade, points);
		this.myGrades.add(newCourse);
		// System.out.println("New course added: "+newCourse);
	}
	
	/**
	 * Adds a new course to the student, from a premade object.
	 * @param newCourse A previously made Course object.
	 */
	public void addNewGrade(Course newCourse) {
		this.myGrades.add(newCourse);
		// System.out.println("New course added: "+newCourse);
	}
	
	/**
	 * Return the worst grade obtained (only A-F, pass grades does not count) for the Student.
	 * @return String contained the worst grade (A-F).
	 */
	public String getWorstGrade() {
		char worstGrade = 'A'; // fixed value set since this is best allowed grade.
		for (Course thisCourse: myGrades) {
			if (!thisCourse.getCourseGrade().equals("Bestått")) {
				if (thisCourse.getCourseGrade().charAt(0) > worstGrade) {
					worstGrade = thisCourse.getCourseGrade().charAt(0);
				}
			}
		}
		return Character.toString(worstGrade);
	}
	
	/**
	 * Return the best grade obtained (only A-F, pass grades does not count) for the Student.
	 * @return String contained the best grade (A-F).
	 */
	public String getBestGrade() {
		char bestGrade = 'F'; // fixed value set since this is worst allowed grade. 
		for (Course thisCourse: myGrades) {
			if (!thisCourse.getCourseGrade().equals("Bestått")) {
				if (thisCourse.getCourseGrade().charAt(0) < bestGrade) {
					bestGrade = thisCourse.getCourseGrade().charAt(0);
				}
			}
		}
		return Character.toString(bestGrade);
	}
	
	/**
	 * Calculate the average grade for the Student based on all courses assigned the the student with a A-F grade, Pass grades are not included in calculation.
	 * @return A string of the average Grade (A-F) rounded.
	 */
	public String getAverageGrade() {
		double totalPoints = 0; //Initialization of point summation used in loop.
		for (Course thisCourse : this.myGrades) { // Look at all courses in this.myGrades
				if (!thisCourse.getCourseGrade().equals("Bestått")) {
					double weightedPoints = fromGradeToInt(thisCourse.getCourseGrade()) * thisCourse.getCoursePoints(); // Weight the courses based on points
				totalPoints = totalPoints + weightedPoints;
			}
		}
		double averageGradePoints = Math.round(totalPoints/this.getTotalUsableCoursePoints()); // Finds average grade points then rounds the number
		String averageCharGrade = this.fromDoubleToGrade(averageGradePoints); // Convert from Double to String
		return averageCharGrade;
	}
	
	// Convert from a char grade to a int that can be directly used in calculations.
	private int fromGradeToInt(String grade) {
		if (grade.equals("A")) {
			return 5;
		} else if (grade.equals("B")) {
			return 4;
		} else if (grade.equals("C")) {
			return 3;
		} else if (grade.equals("D")) {
			return 2;
		} else if (grade.equals("E")) {
			return 1;
		} else {
			return 0;
		}
	}
	
	// Convert from a double to a char grade.
	private String fromDoubleToGrade(double n) {
		if (n == 5) {
			return "A";
		} else if (n == 4) {
			return "B";
		} else if (n == 3) {
			return "C";
		} else if (n == 2) {
			return "D";
		} else if (n == 1) {
			return "E";
		} else if (n == 0) {
			return "F";
		} else {
			return "Bestått";
		}
	}
	
	// Abstract retrieval of number of each grade, return as a integer list. A = int[0], F = int[5].
	public int[] extractData() {
    	int[] gradeSummation = {0, 0, 0, 0, 0, 0, 0};
    	for (Course thisCourse: this.myGrades) {
    		if (thisCourse.getCourseGrade().equals("A")) {
    			gradeSummation[0] += 1;
    		} else if (thisCourse.getCourseGrade().equals("B")) {
    			gradeSummation[1] += 1;
    		} else if (thisCourse.getCourseGrade().equals("C")) {
    			gradeSummation[2] += 1;
    		} else if (thisCourse.getCourseGrade().equals("D")) {
    			gradeSummation[3] += 1;
    		} else if (thisCourse.getCourseGrade().equals("E")) {
    			gradeSummation[4] += 1;
    		} else if (thisCourse.getCourseGrade().equals("F")) {
    			gradeSummation[5] += 1;
    		} else if (thisCourse.getCourseGrade().equals("Bestått")) {
    			gradeSummation[6] += 1;
    		}
    	}
    	return gradeSummation;
	}
	
	@Override
	// Reformatting of toString method.
	public String toString() {
		return "Name: "+getPersonName()+"\nTotal courses: "+getCourseAmount()+" - Average Grade: "+getAverageGrade();
	}
	
	public static void main(String[] args) {
		Student Per = new Student("Per");
		Per.addNewGrade("ITGK", "TDT4109","B");
		Per.addNewGrade("Webtek", "IT2805", "A");
		Per.addNewGrade("Matte1", "TMA4100", "B");
		Per.addNewGrade("Masterprosjekt", "KJ3900", "B", 60);
		Per.addNewGrade("Exphil", "EXPH0004", "D", 7.5);
		System.out.println(Per);
		System.out.println("Best grade: "+Per.getBestGrade());
		System.out.println("Worst grade: "+Per.getWorstGrade());
	}

}
