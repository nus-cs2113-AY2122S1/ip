package duke.storage;

import duke.data.task.Task;
import duke.data.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Encodes Task from the application into the appropriate syntax for storing into the storage file.
 */
public class TaskListEncoder {

    /**
     * Encodes task list into String representation for the storage file.
     *
     * @param tasks TaskList containing tasks to be encoded
     * @return List<String> where each index is an encoded task corresponding to its index in the TaskList
     */
    public List<String> encodeTaskList(TaskList tasks) {
        final List<String> encodedTaskList = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            encodedTaskList.add(task.toTextFileString());
        }

        return encodedTaskList;
    }

}
