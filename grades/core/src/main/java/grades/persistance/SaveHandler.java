package grades.persistance;

import java.io.FileNotFoundException;
import java.io.IOException;

import grades.core.Student;

public interface SaveHandler {

	/**
	 * Print all courses in the Student object sent to a .csv file in the
	 * User/GradesApplication/Export folder.
	 * 
	 * @param fileName         A string that specifies what the name of the
	 *                         output .csv will be called.
	 * @param courseCollection The Student object we wish to convert into a .csv
	 *                         file.
	 * @throws IOException If the write area is inaccessable or read-only a
	 *                     IOException will be thrown.
	 */
	void save(String fileName, Student courseCollection) throws IOException;

	/**
	 * Print all courses in the Student object sent to a .csv file in the
	 * User/GradesApplication/Export folder.
	 * 
	 * @param filePath         A string that specifies the path of the
	 *                         output file.
	 * @param fileName         A string that specifies what the name of
	 *                         the output .csv will be called.
	 * @param courseCollection The Student object we wish to convert into
	 *                         a .csv file.
	 * @throws IOException If the write area is inaccessable or read-only a
	 *                     IOException will be thrown.
	 */
	void save(String filePath, String fileName, Student courseCollection)
			throws IOException;

	/**
	 * A method to delete a specific file in the User/GradesApplication/Export
	 * folder. Intended only for testing purposes.
	 * 
	 * @param fileName The name of the file you wish to delete.
	 * @throws FileNotFoundException If the entered fileName does no
	 *                               correspond to a existing file.
	 */
	void deleteFile(String fileName) throws FileNotFoundException;

	/**
	 * Load the Student personName and all Course Objects found in a .csv
	 * file in the User/GradesApplication/Import folder.
	 * 
	 * @param fileName     The name of the .csv file that is read from
	 *                     User/GradesApplication/Import folder.
	 * @param readToObject The student Object that will add the read data.
	 * @throws FileNotFoundException If file not found
	 * @throws IOException           If program cannot make required folders.
	 *                               Check if area is writeable.
	 */
	void load(String fileName, Student readToObject)
			throws FileNotFoundException, IOException;

	/**
	 * Load the Student personName and all Course Objects found in a .csv file
	 * in the User/GradesApplication/Import folder.
	 * 
	 * @param pathName     The absolute path of the file.
	 * @param readToObject The student Object that will add the read data.
	 * @throws FileNotFoundException If file not found
	 * @throws IOException           If program cannot make required folders.
	 *                               Check if area is writeable.
	 */
	void loadSpecifiedFile(String pathName, Student readToObject)
			throws FileNotFoundException, IOException;
}
