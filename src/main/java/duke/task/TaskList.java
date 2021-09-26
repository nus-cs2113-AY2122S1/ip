package duke.task;

// Task Manager
public class TaskList {

    public static Task addEvent(String description, String at) {
        return new Event(description, at);
    }

    public static Task addDeadline(String description, String by) {
        return new Deadline(description, by);
    }

    public static Task addTodo(String description) {
        return new Todo(description);
    }
}
