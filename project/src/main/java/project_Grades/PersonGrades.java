package project_Grades;

import java.util.ArrayList;

public class PersonGrades {
	private ArrayList<Grade> myGrades = new ArrayList<Grade>();
	private String personName;
	
	public PersonGrades(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Person name cannot be 'null'");
		} else {
			this.personName = name;
		}
	}
	
	public Grade getCourse(int n) {
		return this.myGrades.get(n);
	}
	
	public Grade getCourse(Grade desiredCourse) {
		Grade foundCourse = null;
		for (Grade thisCourse: myGrades) {
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
	
	public void removeCourse(int n) {
		this.myGrades.remove(n);
	}
	
	public void removeCourse(Grade removeCourse) {
		this.myGrades.remove(removeCourse);
	}
	
	public int getCourseAmount() {
		if (this.myGrades.size()!=0) {
			return this.myGrades.size();
		} else {
			return -1;
		}
	}
	
	public double getTotalCoursePoints() {
		double totalPoints = 0;
		for (Grade thisCourse: this.myGrades) {
			totalPoints = totalPoints + thisCourse.getCoursePoints();
		}
		return totalPoints;
	}
	
	public void setPersonName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Person name cannot be 'null'");
		} else {
			this.personName = name;
		}
	}
	
	public String getPersonName() {
		return this.personName;
	}
	
	public void addNewGrade(String subjectName, String subjectCode, char grade) {
		Grade newCourse = new Grade(subjectName, subjectCode, grade);
		this.myGrades.add(newCourse);
		// System.out.println("New course added: "+newCourse);
	}
	
	public void addNewGrade(String subjectName, String subjectCode, char grade, double points) {
		Grade newCourse = new Grade(subjectName, subjectCode, grade, points);
		this.myGrades.add(newCourse);
		// System.out.println("New course added: "+newCourse);
	}
	
	public void addNewGrade(Grade newCourse) {
		this.myGrades.add(newCourse);
		// System.out.println("New course added: "+newCourse);
	}
	
	public char getWorstGrade() {
		char worstGrade = 'A'; // fixed value set since this is best allowed grade.
		for (Grade thisCourse: myGrades) {
			if (thisCourse.getCourseGrade() > worstGrade) {
				worstGrade = thisCourse.getCourseGrade();
			}
		}
		return worstGrade;
	}
	
	public char getBestGrade() {
		char bestGrade = 'F'; // fixed value set since this is worst allowed grade. 
		for (Grade thisCourse: myGrades) {
			if (thisCourse.getCourseGrade() < bestGrade) {
				bestGrade = thisCourse.getCourseGrade();
			}
		}
		return bestGrade;
	}
	
	public char getAverageGrade() {
		double totalPoints = 0; //Initialization of point summation used in loop.
		
		for (Grade thisCourse : this.myGrades) { // Look at all courses in this.myGrades
			double weightedPoints = fromGradeToInt(thisCourse.getCourseGrade()) * thisCourse.getCoursePoints(); // Weight the courses based on points
			totalPoints = totalPoints + weightedPoints;
		}
		double averageGradePoints = Math.round(totalPoints/this.getTotalCoursePoints()); // Finds average grade points then rounds the number
		char averageCharGrade = this.fromDoubleToGrade(averageGradePoints); // Convert from double to grade
		return averageCharGrade;
	}
	
	// Convert from a char grade to a int that can be directly used in calculations.
	private int fromGradeToInt(char grade) {
		if (grade == 'A') {
			return 5;
		} else if (grade == 'B') {
			return 4;
		} else if (grade == 'C') {
			return 3;
		} else if (grade == 'D') {
			return 2;
		} else if (grade == 'E') {
			return 1;
		} else {
			return 0;
		}
	}
	
	// Convert from a double to a char grade.
	private char fromDoubleToGrade(double n) {
		if (n == 5) {
			return 'A';
		} else if (n == 4) {
			return 'B';
		} else if (n == 3) {
			return 'C';
		} else if (n == 2) {
			return 'D';
		} else if (n == 1) {
			return 'E';
		} else {
			return 'F';
		}
	}
	
	// Abstract retrieval of number of each grade, return as a integer list. A = int[0], F = int[5].
	public int[] extractData() {
    	int[] gradeSummation = {0, 0, 0, 0, 0, 0};
    	for (Grade thisCourse: this.myGrades) {
    		if (thisCourse.getCourseGrade() == 'A') {
    			gradeSummation[0] += 1;
    		} else if (thisCourse.getCourseGrade() == 'B') {
    			gradeSummation[1] += 1;
    		} else if (thisCourse.getCourseGrade() == 'C') {
    			gradeSummation[2] += 1;
    		} else if (thisCourse.getCourseGrade() == 'D') {
    			gradeSummation[3] += 1;
    		} else if (thisCourse.getCourseGrade() == 'E') {
    			gradeSummation[4] += 1;
    		} else if (thisCourse.getCourseGrade() == 'F') {
    			gradeSummation[5] += 1;
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
		PersonGrades Per = new PersonGrades("Per");
		Per.addNewGrade("ITGK", "TDT4109",'B');
		Per.addNewGrade("Webtek", "IT2805", 'A');
		Per.addNewGrade("Matte1", "TMA4100", 'B');
		Per.addNewGrade("Masterprosjekt", "KJ3900", 'B', 60);
		Per.addNewGrade("Exphil", "EXPH0004", 'D', 7.5);
		System.out.println(Per);
		System.out.println("Best grade: "+Per.getBestGrade());
		System.out.println("Worst grade: "+Per.getWorstGrade());
	}

}
