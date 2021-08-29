public class TaskManager {
    // Constants
    private static final int MAX_TASKS = 100;

    // Task list
    private Task[] tasks = new Task[MAX_TASKS];

    // Class variable for counting number of tasks
    private static int taskCount = 0;

    // Add new task to the task list
    public Task addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        return task;
    }

    public Task addTodo(String description) {
        Todo todo = new Todo(description);
        return this.addTask(todo);
    }

    public Task addEvent(String description) {
        int atIndex = description.indexOf("/at");
        String at = description.substring(atIndex + 4);
        description = description.substring(0, atIndex - 1);
        Event event = new Event(description, at);
        return this.addTask(event);
    }

    public Task addDeadline(String description) {
        int byIndex = description.indexOf("/by");
        String by = description.substring(byIndex + 4);
        description = description.substring(0, byIndex - 1);
        Deadline deadline = new Deadline(description, by);
        return this.addTask(deadline);
    }

    // Mark the specified task as done
    public Task markAsDone(int id) {
        Task task = tasks[id];
        tasks[id].markAsDone();
        return task;
    }

    // Print out task list
    public void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." +
                    tasks[i].toString());
        }
    }
}
