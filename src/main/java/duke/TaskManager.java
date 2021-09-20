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

        System.out.print("Got it. I've added this task:\n");
        System.out.printf("   %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    public static void addTodo(String todoName) throws DukeException {
        if (todoName.isEmpty()) {
            throw new DukeException("Todo cannot be empty");
        }
        Todo todo = new Todo(todoName);
        addTask(todo);
    }

    public static void addDeadline(String deadlineName, String deadlineDue) {
        Deadline deadline = new Deadline(deadlineName, deadlineDue);
        addTask(deadline);
    }

    public static void addEvent(String eventName, String eventTime) {
        Event event = new Event(eventName, eventTime);
        addTask(event);
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }

    public static void markTaskNoAsDone(int taskNo) {
        Task task = tasks.get(taskNo);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("   %s\n", task);
    }

    public static void deleteTask(int taskNo) {
        Task task = tasks.remove(taskNo);

        System.out.println("Noted. I've removed this task:");
        System.out.printf("   %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}
