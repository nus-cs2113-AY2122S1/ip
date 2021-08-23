public class TaskManager {
    // Task list
    private Task[] tasks = new Task[100];

    // Class variable for counting number of tasks
    private static int taskCount = 0;

    // Add new task to the task list
    public void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
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
                    tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
        }
    }
}
