package duke;

import duke.exceptions.TaskHasBeenDoneException;
import duke.task.Task;

import java.util.ArrayList;


public class TasksList {
    private final ArrayList<Task> tasks;

    public TasksList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises a new <code>TaskList</code> with the same tasks as the input <code>TaskList</code>.
     * @param oldTaskList The <code>TaskList</code> whose tasks are to be copied.
     */
    public TasksList(TasksList oldTaskList) {
        this.tasks = oldTaskList.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public String getTaskString(int taskIndex) {
        Task task = getTask(taskIndex);
        return task.toString();
    }

    public void markTaskAsDone(int taskIndex) throws TaskHasBeenDoneException {
        Task task = tasks.get(taskIndex);
        if (task.getIsDone()) {
            throw new TaskHasBeenDoneException(taskIndex);
        }
        task.markAsDone();
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}