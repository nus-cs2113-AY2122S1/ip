public class TaskManager {
    private static int taskNo = 0;
    final private static int maxTasks = 100;
    final static Task[] tasks = new Task[maxTasks];

    static void add(String taskName) {
        if (taskNo < maxTasks) {
            tasks[taskNo] = new Task(taskName);
            taskNo++;

            System.out.printf("added: %s\n", taskName);
        }
    }

    static void list() {
        for (int i = 0; i < taskNo; ++i) {
            Task task = tasks[i];

            System.out.printf("%d. [%s] %s\n", i + 1, task.getStatusIcon(), task.getName());
        }
    }

    static void mark(int taskNo) {
        Task task = tasks[taskNo];
        task.markDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("   [X] %s\n", task.getName());
    }
}
