import java.util.Arrays;

public class TaskManager {
    public static final int MAX_TASKS = 100;

    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASKS];

    private enum Type {
        DEADLINE, EVENT, TODO;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public static void taskDone(int id) {
        Task currentTask = tasks[id];
        currentTask.markAsDone();
        Message.printWithSpacers("Nice! I've marked this task as done:\n" + currentTask);
    }

    public static void newTask(String userInput) {
        String command = userInput.split(" ")[0];
        userInput = userInput.substring(command.length()+1, userInput.length());

        String[] inputs;
        switch (Type.valueOf(command.toUpperCase())) {
        case DEADLINE:
            inputs = userInput.split(String.format(" /%s ", Deadline.PREPOSITION));
            tasks[taskCount] = new Deadline(inputs[0], inputs[1]);
            break;
        case EVENT:
            inputs = userInput.split(String.format(" /%s ", Event.PREPOSITION));
            tasks[taskCount] = new Event(inputs[0], inputs[1]);
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
