package duke.storage;

import duke.data.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskListEncoder {

    public static List<String> encodeTaskList(TaskList toSave){
        final List<String> encodedTasks = new ArrayList<>();

        toSave.forEach(task -> encodedTasks.add(task.parseToStore()));
        return encodedTasks;
    }

}
