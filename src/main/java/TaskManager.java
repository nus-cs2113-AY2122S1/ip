public class TaskManager {
    private static int taskNo = 0;
    final private static int maxTasks = 100;
    final static Task[] tasks = new Task[maxTasks];

    static void addTask(Task task) {
        if (taskNo < maxTasks) {
            tasks[taskNo] = task;
            taskNo++;

            System.out.print("Got it. I've added this task:\n");
            System.out.printf("   %s\n", task);
            System.out.printf("Now you have %d tasks in the list.\n", taskNo);
        }
    }

    static void addTodo(String todoName) throws TodoException {
        if (todoName.isEmpty()) {
            throw new TodoException();
        }
        Todo todo = new Todo(todoName);
        addTask(todo);
    }

    static void addDeadline(String deadlineName, String deadlineDue) {
        Deadline deadline = new Deadline(deadlineName, deadlineDue);
        addTask(deadline);
    }

    static void addEvent(String eventName, String eventTime) {
        Event event = new Event(eventName, eventTime);
        addTask(event);
    }

    static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNo; ++i) {
            Task task = tasks[i];
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }

    static void markTaskNoAsDone(int taskNo) {
        Task task = tasks[taskNo];
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("   %s\n", task);
    }
}
