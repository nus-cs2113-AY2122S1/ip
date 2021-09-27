package duke.storage;

import duke.data.task.Task;
import duke.data.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskListEncoder {

    /**
     * Encodes task list into String representation for the storage file
     */
    public List<String> encodeTaskList(TaskList tasks) {
        final List<String> encodedTaskList = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            encodedTaskList.add(task.toTextFileString());
        }

        return encodedTaskList;
    }

}
