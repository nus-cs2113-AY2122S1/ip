package duke.data;

import duke.data.task.Task;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    /* Constructor for new tasklist */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /* Constructor for tasklist based on an existing ArrayList of tasks */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new task to the tasklist
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * removes a task from the tasklist
     *
     * @param task task to be removed
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * removes a task from the tasklist based on index
     *
     * @param index index of the task to be removed, 1-based index
     * @return Task that was removed from the tasklist
     */
    public Task removeTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(task);
        return task;
    }

    /**
     * marks task given as done
     *
     * @param task task to be marked as done
     */
    public void markDone(Task task) {
        task.setDone();
    }

    /**
     * marks task given as done based on index
     *
     * @param index index of the task to be marked as done, 1-based index
     * @return Task that was marked as done
     */
    public Task markDone(int index) {
        Task task = taskList.get(index);
        task.setDone();
        return task;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getCount() {
        return taskList.size();
    }
}

