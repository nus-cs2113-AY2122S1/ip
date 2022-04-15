package alfred.storage;

import alfred.exception.FileErrorException;
import alfred.task.Task;
import alfred.task.TaskList;

import java.io.FileWriter;
import java.io.IOException;

import static alfred.storage.Storage.SEPARATOR;

public class TaskEncoder {
    /**
     * This method writes/saves all Tasks in the TaskList to a local storage file via the given filePath.
     * @param filePath Given filePath to write TaskList to
     * @param taskList TaskList to be written to local storage
     * @throws FileErrorException If there are errors writing to local storage
     */
    public static void saveAllTasks(String filePath, TaskList taskList) throws FileErrorException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                String encodedTask = encodeTask(taskList.getTask(i));
                fileWriter.write(encodedTask);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileErrorException();
        }
    }

    /**
     * This method encodes a Task to a decode-able comma-separated format to be saved in local storage.
     * @param t Task to be encoded
     * @return String This returns encoded Task in String form
     */
    private static String encodeTask(Task t) {
        String baseTask = t.getType() + SEPARATOR + t.isDone()
                + SEPARATOR + t.getDescription() + SEPARATOR + t.getDate() + "\n";
        return baseTask;
    }
}
