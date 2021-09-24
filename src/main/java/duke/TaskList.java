package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
