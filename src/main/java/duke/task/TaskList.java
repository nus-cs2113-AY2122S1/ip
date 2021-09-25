package duke.task;

import java.io.File;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int numOfTasks = 0;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task addTodo(String description) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        numOfTasks++;
        return newTask;
    }

    public Task addDeadline(String description, String timeField) {
        Task newTask = new Deadline(description, timeField);
        tasks.add(newTask);
        numOfTasks++;
        return newTask;
    }

    public Task addEvent(String description, String timeField) {
        Task newTask = new Event(description, timeField);
        tasks.add(newTask);
        numOfTasks++;
        return newTask;
    }

    public Task markDone(int index) {
        Task task = tasks.get(index - 1);
        task.setDone();
        numOfTasks--;
        return task;
    }

    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }
}
