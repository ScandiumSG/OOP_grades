package grades.persistance;

import grades.core.Student;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface InternalSaveHandler {

    /**
     * Print all courses in the Student object sent to a .MGD file in the
     * User/GradesApplication/UserData folder.
     * 
     * @param studentToSave The Student object we wish to convert into a
     *                      .MGD file.
     * @throws FileNotFoundException If file not found
     * @throws IOException           If program cannot make a new file or cannot
     *                               make required folders. Check if area is
     *                               writeable.
     */
    void save(Student studentToSave) throws FileNotFoundException, IOException;

    /**
     * A method to delete a specific file in the User/GradesApplication/UserData
     * folder. Intended only for testing purposes.
     * 
     * @throws FileNotFoundException If the entered fileName does no correspond
     *                               to a existing file.
     */
    void deleteFile(Student studentToDelete) throws FileNotFoundException,
            IOException;

    /**
     * Load the Student personName and all Course Objects found in a
     * .MGD file in the User/GradesApplication/Export folder.
     * 
     * @param readToStudent The student Object that will add the read data.
     * @throws FileNotFoundException If file not found
     */
    void load(Student readToStudent) throws FileNotFoundException;
}
