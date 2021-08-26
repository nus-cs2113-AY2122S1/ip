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
            Character check = task.isDone() ? 'X' : ' ';

            System.out.printf("%d. [%c] %s\n", i + 1, check, task.getName());
        }
    }

    static void mark(int taskNo) {
        Task task = tasks[taskNo];
        task.markDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("   [X] %s\n", task.getName());
    }
}
