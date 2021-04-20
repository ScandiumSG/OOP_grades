package project_Grades;

public class Grade {
	private String courseName;
	private String courseCode;
	private double coursePoints = 7.5;
	private char courseGrade;
	
	public Grade(String subjectName, String subjectCode, char subjectGrade, double points) {
		if (!subjectName.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course name must be a string.");
		} else {
			courseName = subjectName;
		}
		
		if (!subjectCode.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course code must be a string.");
		} else if (((!subjectCode.substring(0,2).matches("[A-Z]+")) && (!subjectCode.substring(0,3).matches("[A-Z]+")) && (!subjectCode.substring(0,4).matches("[A-Z]+")))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code must start with 2-4 letters.");
		} else if ((!subjectCode.substring(2).matches("[0-9]+")) && (!subjectCode.substring(3).matches("[0-9]+")) && (!subjectCode.substring(4).matches("[0-9]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Course code cannot start with numbers, must begin with letters.");
		} else {
			courseCode = subjectCode;
		}
		
		if (!"ABCDEF".contains(String.valueOf(subjectGrade)) && (!"abcdef".contains(String.valueOf(subjectGrade)))) {
			throw new IllegalArgumentException("Invalid grade input, must be a char in range A-F.");
		} else if ("abcdef".contains(String.valueOf(subjectGrade))) {
			courseGrade= Character.toUpperCase(subjectGrade);
		} else {
			courseGrade = subjectGrade;
		}
		
		
		if (points <= 0) {
			throw new IllegalArgumentException ("Course points must be a double larger than 0.");
		} else {
			coursePoints = points;
		}
	}
	
	public Grade(String subjectName, String subjectCode, char subjectGrade) {
		if (!subjectName.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course name must be a string.");
		} else {
			courseName = subjectName;
		}
		
		if (!subjectCode.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course code must be a string.");
		} else if (((!subjectCode.substring(0,2).matches("[A-Z]+")) && (!subjectCode.substring(0,3).matches("[A-Z]+"))) || (!subjectCode.substring(3).matches("[0-9]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Must be 2/3 letters then a number.");
		} else {
			courseCode = subjectCode;
		}
		
		if ("ABCDEF".contains(String.valueOf(subjectGrade))) {
			courseGrade = subjectGrade;
		} else if ("abcdef".contains(String.valueOf(subjectGrade))) {
			courseGrade= Character.toUpperCase(subjectGrade);
		} else {
			throw new IllegalArgumentException("Invalid grade input, must be a char in range A-F.");
		}
	}

	public void setCourseName(String inputName) {
		if (!inputName.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course name must be a string.");
		} else {
			courseName = inputName;
			return;
		}
	}
	
	public void setCourseCode(String inputCode) {
		if (!inputCode.getClass().equals(String.class)) {
			throw new IllegalArgumentException("Course code must be a string.");
		} else if (((!inputCode.substring(0,2).matches("[A-Z]+")) && (!inputCode.substring(0,3).matches("[A-Z]+"))) || (!inputCode.substring(3).matches("[0-9]+"))) {
			throw new IllegalArgumentException("Invalid format of course code. Must be 2/3 letters then a number.");
		} else {
			courseCode = inputCode;
			return;
		}
	}
	
	public void setCourseGrade(char inputGrade) {
		if (!"ABCDEF".contains(String.valueOf(inputGrade)) && (!"abcdef".contains(String.valueOf(inputGrade)))) {
			throw new IllegalArgumentException("Invalid grade input, must be a char in range A-F.");
		} else if ("abcdef".contains(String.valueOf(inputGrade))) {
			courseGrade= Character.toUpperCase(inputGrade);
			return;
		} else {
			courseGrade = inputGrade;
			return;
		}
	}
	
	public void setCoursePoints(double points) {
		if (points <= 0) {
			throw new IllegalArgumentException ("Course points must be a double larger than 0.");
		} else {
			coursePoints = points;
			return;
		}
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public double getCoursePoints() {
		return coursePoints;
	}
	
	public char getCourseGrade() {
		return courseGrade;
	}
	
	@Override
	public String toString() {
		return getCourseCode()+" ("+getCourseName()+") - Grade: "+getCourseGrade()+", points: "+getCoursePoints();
	}
	
	public static void main(String[] args) {
		Grade new1 = new Grade("ITGK", "TDT4109", 'B', 7.5);
		System.out.println(new1);
		Grade new2 = new Grade("Webtek", "IT2805", 'A');
		System.out.println(new2);
		Grade new3 = new Grade("Generell Kjemi", "KJ1000", 'B', 15);
		System.out.println(new3);
		System.out.println("---");
		
		Grade new4 = new Grade("Nanomaterialer", "TMT4100", 'C');
		System.out.println(new4);
		new4.setCourseGrade('B');
		new4.setCourseName("Nanomat");
		System.out.println(new4);
	}

}
