package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
        Storage.exportData();
    }

    public static Task addTodo(String todoName) throws DukeException {
        if (todoName.isEmpty()) {
            throw new DukeException("Todo cannot be empty");
        }
        Todo todo = new Todo(todoName);
        addTask(todo);
        return todo;
    }

    public static Task addDeadline(String deadlineName, String deadlineDue) {
        Deadline deadline = new Deadline(deadlineName, deadlineDue);
        addTask(deadline);
        return deadline;
    }

    public static Task addEvent(String eventName, String eventTime) {
        Event event = new Event(eventName, eventTime);
        addTask(event);
        return event;
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }

    public static Task markTaskNoAsDone(int taskNo) {
        Task task = tasks.get(taskNo);
        task.markAsDone();
        Storage.exportData();
        return task;
    }

    public static int getTasklistSize() {
        return tasks.size();
    }

    public static ArrayList<Task> getTasklist() {
        return tasks;
    }

    public static Task deleteTask(int taskNo) {
        Task task = tasks.remove(taskNo);
        Storage.exportData();
        return task;
    }
}
