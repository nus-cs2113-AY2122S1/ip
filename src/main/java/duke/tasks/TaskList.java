package duke.tasks;

import duke.parser.Parser;

import java.util.ArrayList;

/**
 * Represents the ArrayList of all Tasks.
 * Essentially a wrapper class
 */
public class TaskList {
    // ArrayList to store all tasks
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int sizeOfTaskList() {
        return tasks.size();
    }

    public Task getTaskAtIndex(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public String getTaskListInFileFormat() {
        String data = "";
        for (Task task : tasks) {
            data += task.toFileFormat() + System.lineSeparator();
        }
        return data;
    }
}
