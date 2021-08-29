public class TaskManager {
    // Task list
    private Task[] tasks = new Task[100];

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
        int pos_at = description.indexOf("/at");
        String at = description.substring(pos_at + 4);
        description = description.substring(0, pos_at - 1);
        Event event = new Event(description, at);
        return this.addTask(event);
    }

    public Task addDeadline(String description) {
        int pos_by = description.indexOf("/by");
        String by = description.substring(pos_by + 4);
        description = description.substring(0, pos_by - 1);
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
