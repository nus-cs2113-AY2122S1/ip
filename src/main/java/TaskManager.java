import java.util.Arrays;

public class TaskManager {
    public static final int MAX_TASKS = 100;

    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void taskDone(int id) {
        Task currentTask = tasks[id];
        currentTask.markAsDone();
        Message.printWithSpacers("Nice! I've marked this task as done:\n" + currentTask);
    }

    public static void newTask(String userInput) {
        String command = userInput.split(" ")[0];
        //Remove command from userInput.
        userInput = userInput.substring(command.length() + 1);

        switch (Task.Types.valueOf(command.toUpperCase())) {
        case DEADLINE:
            tasks[taskCount] = new Deadline(userInput);
            break;
        case EVENT:
            tasks[taskCount] = new Event(userInput);
            break;
        case TODO:
            tasks[taskCount] = new Todo(userInput);
            break;
        }
        printInputReceived(tasks[taskCount++]);
    }

    public static void printInputReceived(Task task) {
        Message.printWithSpacers(String.format("Got it, I've added this task:\n%s\n" +
                "Now you have %d tasks in the list.", task, taskCount));
    }

    public static void printTasks() {
        int count = 1;
        String message = "";
        for (Task task : Arrays.copyOf(tasks, taskCount)) {
            message += String.format("%d.%s\n", count++, task);
        }
        Message.printWithSpacers(message);
    }
}
