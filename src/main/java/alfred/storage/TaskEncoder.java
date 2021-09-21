package alfred.storage;

import alfred.exception.FileErrorException;
import alfred.task.Task;
import alfred.task.TaskList;

import java.io.FileWriter;
import java.io.IOException;

import static alfred.storage.Storage.SEPARATOR;

public class TaskEncoder {
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

    private static String encodeTask(Task t) {
        String baseTask = t.getType() + SEPARATOR + t.isDone()
                + SEPARATOR + t.getDescription() + SEPARATOR + t.getDate() + "\n";
        return baseTask;
    }
}
