package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskManager {
    private static int taskNo = 0;
    private static final int maxTasks = 100;
    private static final Task[] tasks = new Task[maxTasks];

    public static void addTask(Task task) {
        if (taskNo < maxTasks) {
            tasks[taskNo] = task;
            taskNo++;
            Storage.exportData();
        }
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
        for (int i = 0; i < taskNo; ++i) {
            Task task = tasks[i];
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }

    public static Task markTaskNoAsDone(int taskNo) {
        Task task = tasks[taskNo];
        task.markAsDone();
        Storage.exportData();
        return task;
    }

    public static int getTasklistSize() {
        return taskNo;
    }

    public static Task[] getTasklist() {
        return tasks;
    }
}
