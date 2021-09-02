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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNo; ++i) {
            Task task = tasks[i];

            System.out.printf("%d. %s\n", i + 1, task);
        }
    }

    static void mark(int taskNo) {
        Task task = tasks[taskNo];
        task.markDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("   %s\n", task);
    }

    static void todo(String todoName) {
        if (taskNo < maxTasks) {
            Todo todo = new Todo(todoName);
            tasks[taskNo] = todo;
            taskNo++;

            System.out.print("Got it. I've added this task:\n");
            System.out.printf("   %s\n", todo);
            System.out.printf("Now you have %d tasks in the list.\n", taskNo);
        }
    }

    static void deadline(String deadlineName, String dueDate) {
        if (taskNo < maxTasks) {
            Deadline deadline = new Deadline(deadlineName, dueDate);
            tasks[taskNo] = deadline;
            taskNo++;

            System.out.print("Got it. I've added this task:\n");
            System.out.printf("   %s\n", deadline);
            System.out.printf("Now you have %d tasks in the list.\n", taskNo);
        }
    }

    static void event(String eventName, String timeslot) {
        if (taskNo < maxTasks) {
            Event event = new Event(eventName, timeslot);
            tasks[taskNo] = event;
            taskNo++;

            System.out.print("Got it. I've added this task:\n");
            System.out.printf("   %s\n", event);
            System.out.printf("Now you have %d tasks in the list.\n", taskNo);
        }
    }
}
