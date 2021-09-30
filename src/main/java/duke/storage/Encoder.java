package duke.storage;

import duke.data.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Encoder {
    /**
     * Encodes tasks in task list into a list of readable string presentation for storage.
     *
     * @param tasks the task list used.
     * @return a list of string representing tasks
     */
    public static List<String> encoder(ArrayList<Task> tasks) {
        final ArrayList<String> encodedTasks = new ArrayList<>();
        tasks.stream()
                .forEach(task -> encodedTasks.add(task.store()));
        return encodedTasks;
    }
}
