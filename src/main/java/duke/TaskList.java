package duke;

import java.util.ArrayList;

/**
 * Represents a list to store all the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates a <code>TaskList</code>.
     *
     * @param savedTasks Tasks that are saved into file.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
    }

    /**
     * Returns all the tasks in the list.
     *
     * @return Tasks in the list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
