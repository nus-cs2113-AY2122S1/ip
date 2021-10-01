package duke.task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<List> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<List> list) {
        this.list = list;
    }

    /**
     * Get the task in the list corresponding to the index
     *
     * @param taskId The index of the task that need to be returned
     * @return The task in the list corresponding to the index
     */
    public List getTask(int taskId) {
        return list.get(taskId);
    }

    /**
     * Add the task into list
     *
     * @param task The index of the task that need to be added
     * @return True or false which indicates whether add the task successfully
     */
    public boolean addTask(List task) {
        return list.add(task);
    }

    /**
     * Remove the task from the list according to the corresponding index
     *
     * @param taskId The index of the task that need to be removed
     * @return The task in the list corresponding to the index
     */
    public List removeTask(int taskId) {
        return list.remove(taskId);
    }

    /**
     * Get size of list
     *
     * @return The size of list
     */
    public int listSize() {
        return list.size();
    }

    @Override
    public String toString() {
        return String.valueOf(list);
    }
}
