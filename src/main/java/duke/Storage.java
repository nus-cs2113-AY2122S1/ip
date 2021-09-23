package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Stores the task list in a file in the computer and updates it
 */
public class Storage {
    public static void writeToFile(String filePath, TaskList tasklist) throws IOException {
        FileWriter file = new FileWriter(filePath);
        for (Task task : tasklist.list) {
            file.write(task.fileFormat() + System.lineSeparator());
        }
        file.close();
    }
}
