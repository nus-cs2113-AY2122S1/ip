package alfred.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * This method removes a Task from the TaskList given an index.
     * @param index The index of the Task to be removed
     * @return Task The Task removed is returned
     */
    public Task removeTask(int index) {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return removedTask;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * This method allows the user to set a Task as done via the TaskList
     * by specifying the index.
     * @param index The index of Task to be marked as done
     */
    public void setTaskDoneInList(int index) {
        tasks.get(index).setTaskDone();
    }
}
