package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskCollection {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
