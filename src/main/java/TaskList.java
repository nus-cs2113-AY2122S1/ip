import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList is the type to store the tasks
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {}

    /**
     * add task
     * @param toAdd task to add
     */
    public void add(Task toAdd) {
        taskList.add(toAdd);
    }

    /**
     * get task
     * @param toGet the index of the task to get
     * @return
     */
    public Task get(int toGet) {
        return taskList.get(toGet);
    }

    /**
     * remove task
     * @param toRemove the index of the task to remove
     */
    public void remove(int toRemove) {
        taskList.remove(toRemove);
    }

    /**
     * get the task size
     * @return the task size
     */
    public int size() {
        return taskList.size();
    }
}
