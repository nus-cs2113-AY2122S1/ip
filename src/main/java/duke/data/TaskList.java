package duke.data;

import duke.task.Task;

import java.util.ArrayList;

/**
 * This class contains the task list that undergoes modifications as user gives commands
 */
public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
