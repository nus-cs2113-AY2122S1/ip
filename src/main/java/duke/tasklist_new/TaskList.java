package duke.tasklist_new;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     * @return list of tasks
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * Adds a task.
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task.
     * @param index task number of task to be deleted
     * @return the task to be deleted
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(task);
        return task;
    }

    /**
     * Marks a task as done.
     * @param index task number of task to be marked as done
     */
    public void markDone(int index) {
        tasks.get(index).markAsDone();
    }
}
