package duke;

import duke.Exceptions.TaskHasBeenDoneException;
import duke.task.Task;

import java.util.ArrayList;


public class TasksList {
    private final ArrayList<Task> tasks;

    public TasksList() {
        this.tasks = new ArrayList<>();
    }

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
}