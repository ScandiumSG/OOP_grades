package project_Grades;

public class Course {
	private String courseName;
	private String courseCode;
	private double coursePoints = 7.5;
	private String courseGrade;
	
	public Course(String subjectName, String subjectCode, String subjectGrade, double points) {
		// Validation of course name
		if (!subjectName.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course name must be a string.");
		} else {
			courseName = subjectName;
		}
		
		// Validation of course code
		if (!subjectCode.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course code must be a string.");
		} else if (!(subjectCode.substring(0,2).matches("[A-Z]+")) && (subjectCode.substring(0,3).matches("[A-Z]+")) && (subjectCode.substring(0,4).matches("[A-Z]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code must start with 2-4 letters.");
		} else if ((!subjectCode.substring(2).matches("[0-9]+")) && (!subjectCode.substring(3).matches("[0-9]+")) && (!subjectCode.substring(4).matches("[0-9]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code cannot start with numbers, must begin with letters.");
		} else {
			courseCode = subjectCode;
		}
		
		// Validation of course grade
		if (subjectGrade.toLowerCase().equals("bestått")) {
			courseGrade = "Bestått";
		} else if ((!"ABCDEF".contains(subjectGrade)) && (!"abcdef".contains(subjectGrade))) {
			throw new IllegalArgumentException("Invalid grade input, must be a grade in range A-F or \"Bestått\".");
		} else if ("abcdef".contains(subjectGrade)) {
			courseGrade = subjectGrade.toUpperCase();
		} else {
			courseGrade = subjectGrade;
		}
		
		// Validation of course points
		if (points <= 0) {
			throw new IllegalArgumentException ("Course points must be a double larger than 0.");
		} else {
			coursePoints = points;
		}
	}
	
	public Course(String subjectName, String subjectCode, String subjectGrade) {
		// Validation of course name
		if (!subjectName.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course name must be a string.");
		} else {
			courseName = subjectName;
		}
		
		// Validation of course code
		if (!subjectCode.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course code must be a string.");
		} else if (!(subjectCode.substring(0,2).matches("[A-Z]+")) && (subjectCode.substring(0,3).matches("[A-Z]+")) && (subjectCode.substring(0,4).matches("[A-Z]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code must start with 2-4 letters.");
		} else if ((!subjectCode.substring(2).matches("[0-9]+")) && (!subjectCode.substring(3).matches("[0-9]+")) && (!subjectCode.substring(4).matches("[0-9]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code cannot start with numbers, must begin with letters.");
		} else {
			courseCode = subjectCode;
		}
		
		// Validation of course grade
		if (subjectGrade.toLowerCase().equals("bestått")) {
			courseGrade = "Bestått";
		} else if ((!"ABCDEF".contains(subjectGrade)) && (!"abcdef".contains(subjectGrade))) {
			throw new IllegalArgumentException("Invalid grade input, must be a grade in range A-F or \"Bestått\".");
		} else if ("abcdef".contains(subjectGrade)) {
			courseGrade = subjectGrade.toUpperCase();
		} else {
			courseGrade = subjectGrade;
		}
	}

	/**
	 * Change the course name of this Course object.
	 * @param inputName A string with the new course name
	 * @throws IllegalArgumentException if the new String inputName = null.
	 */
	public void setCourseName(String inputName) {
		if (!inputName.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course name must be a string.");
		} else {
			courseName = inputName;
			return;
		}
	}
	
	/**
	 * Change the course code of this Course object.
	 * @param inputName A string with the new course code
	 * @throws IllegalArgumentException if the new String inputCode = null.
	 */
	public void setCourseCode(String inputCode) {
		if (!inputCode.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course code must be a string.");
		} else if ((!(inputCode.substring(0,2).matches("[A-Z]+")) && !(inputCode.substring(0,3).matches("[A-Z]+")) && !(inputCode.substring(0,4).matches("[A-Z]+")))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code must start with 2-4 letters.");
		} else if ((!inputCode.substring(2).matches("[0-9]+")) && (!inputCode.substring(3).matches("[0-9]+")) && (!inputCode.substring(4).matches("[0-9]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code cannot start with numbers, must begin with letters.");
		} else {
			this.courseCode = inputCode;
			return;
		}
	}
	
	/**
	 * Change the course grade of this Course object.
	 * @param inputName A string with the new course grade
	 * @throws IllegalArgumentException if the new String inputGrade = null.
	 */
	public void setCourseGrade(String inputGrade) {
		if (inputGrade.toLowerCase().equals("bestått")) {
			courseGrade = "Bestått";
		} else if ((!"ABCDEF".contains(inputGrade)) && (!"abcdef".contains(inputGrade))) {
			throw new IllegalArgumentException("Invalid grade input, must be a grade in range A-F or \"Bestått\".");
		} else if ("abcdef".contains(inputGrade)) {
			courseGrade = inputGrade.toUpperCase();
		} else {
			courseGrade = inputGrade;
		}
	}
	
	/**
	 * Change the course points of this Course object.
	 * @param inputName A double with the new course points
	 * @throws IllegalArgumentException if the new double points <= 0.
	 */
	public void setCoursePoints(double points) {
		if (points <= 0) {
			throw new IllegalArgumentException ("Course points must be a double larger than 0.");
		} else {
			coursePoints = points;
			return;
		}
	}
	
	/**
	 * Return the interal course name of this Object.
	 * @return String contained the course Name.
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Return the interal course code of this Object.
	 * @return String contained the course Code.
	 */
	public String getCourseCode() {
		return courseCode;
	}
	
	/**
	 * Return the interal course points of this Object.
	 * @return Double contained the course points.
	 */
	public double getCoursePoints() {
		return coursePoints;
	}
	
	/**
	 * Return the interal course grade of this Object.
	 * @return String contained the course grade.
	 */
	public String getCourseGrade() {
		return courseGrade;
	}
	
	@Override
	public String toString() {
		return getCourseCode()+" ("+getCourseName()+") - Grade: "+getCourseGrade()+", points: "+getCoursePoints();
	}
	
	public static void main(String[] args) {
		Course new1 = new Course("ITGK", "TDT4109", "B", 7.5);
		System.out.println(new1);
		Course new2 = new Course("Webtek", "IT2805", "A");
		System.out.println(new2);
		Course new3 = new Course("Generell Kjemi", "KJ1000", "B", 15);
		System.out.println(new3);
		System.out.println("---");
		
		Course new4 = new Course("Nanomaterialer", "TMT4100", "C");
		System.out.println(new4);
		new4.setCourseGrade("B");
		new4.setCourseName("Nanomat");
		System.out.println(new4);
	}

}
