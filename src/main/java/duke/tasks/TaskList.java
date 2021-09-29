package duke.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    /**
     * Sort all tasks by dateTime to help user prioritise task urgency.
     * Todo task by its creation dateTime.
     * Deadline task by its specified dateTime.
     * Event task by its specified dateTime.
     */
    public void sortByDateTime() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
    }

    /**
     * Overwrite TaskList with empty ArrayList to purge all Tasks
     */
    public void purgeTaskList() {
        tasks = new ArrayList<Task>();
    }
}
