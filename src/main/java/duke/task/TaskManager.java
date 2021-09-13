package duke.task;

import java.util.ArrayList;

public class TaskManager {
    // Constants
    private static final int MAX_TASKS = 100;

    // Task list
    private final ArrayList<Task> tasks = new ArrayList<>();

    // Class variable for counting number of tasks
    private static int taskCount = 0;

    public int getTasksCount() {
        return taskCount;
    }

    // Add new task to the task list
    public Task addTask(Task task) {
        tasks.add(task);
        taskCount++;
        return task;
    }

    public Task deleteTask(int id) {
        Task task = tasks.get(id);
        tasks.remove(id);
        taskCount--;
        return task;
    }

    public Task addTodo(String description) {
        Todo todo = new Todo(description);
        return this.addTask(todo);
    }

    public Task addEvent(String description) {
        int atIndex = description.indexOf("/at");
        String at;
        try {
            at = description.substring(atIndex + 4);
            description = description.substring(0, atIndex - 1);
        } catch (Exception e) {
            System.out.println("Invalid command format!!" + System.lineSeparator() +
                    "Correct format is: " + "event <event name> /at <time>");
            return null;
        }
        Event event = new Event(description, at);
        return this.addTask(event);
    }

    public Task addDeadline(String description) {
        int byIndex = description.indexOf("/by");
        String by;
        try {
            by = description.substring(byIndex + 4);
            description = description.substring(0, byIndex - 1);
        } catch (Exception e) {
            System.out.println("Invalid command format!!" + System.lineSeparator() +
                    "Correct format is: " + "deadline <task name> /by <date>");
            return null;
        }
        Deadline deadline = new Deadline(description, by);
        return this.addTask(deadline);
    }

    // Mark the specified task as done
    public Task markAsDone(int id) {
        Task task = tasks.get(id);
        task.markAsDone();
        return task;
    }

    // Print out task list
    public void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." +
                    tasks.get(i).toString());
        }
    }
}
