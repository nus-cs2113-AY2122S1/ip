import java.util.Scanner;

public class Duke {
    // Line width
    private static final int LINE_WIDTH = 60;

    // Command strings
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";

    // Print horizontal line for improving readability
    private static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    // Print greeting message
    private static void printGreeting() {
        printHorizontalLine();
        String greeting = " Hello! I'm Duke" + System.lineSeparator() +
                " What can I do for you?";
        System.out.println(greeting);
        printHorizontalLine();
        System.out.print(System.lineSeparator());
    }

    // Print farewell message
    private static void printFarewell() {
        String farewell = " Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    // Print invalid command warning
    private static void printInvalid() {
        String invalid = " Invalid Command!";
        System.out.println(invalid);
    }

    // Print task added message
    private static void printAddTask(Task task) {
        String addTask = " Got it. I've added this task:" + System.lineSeparator() +
                "  " + task.toString() + System.lineSeparator() +
                " Now you have " + task.getNoOfTasks() +
                " tasks in the list.";
        System.out.println(addTask);
    }

    private static void executeList(TaskManager taskManager) {
        System.out.println(" Here are the tasks in your list:");
        taskManager.listTasks();
    }

    private static void executeDone(TaskManager taskManager, String taskId) {
        int num = Integer.parseInt(taskId);
        Task task = taskManager.markAsDone(num - 1);
        System.out.println(" Nice! I've marked this task as done:" + System.lineSeparator() +
                "   " + task.toString());
    }

    private static void executeAdd(TaskManager taskManager, String line, String command) {
        int descriptionIndex = line.indexOf(" ") + 1;
        if (descriptionIndex <= 0) {
            printInvalid();
            return;
        }
        String description = line.substring(descriptionIndex);
        Task task = null;
        switch (command) {
        case COMMAND_TODO:
            task = taskManager.addTodo(description);
            break;
        case COMMAND_DEADLINE:
            task = taskManager.addDeadline(description);
            break;
        case COMMAND_EVENT:
            task = taskManager.addEvent(description);
            break;
        default:
            printInvalid();
            break;
        }
        if (task != null) {
            printAddTask(task);
        }
    }

    private static void execute(TaskManager taskManager, String line) {
        String[] words = line.split(" "); // Convert sentence into array of words
        String command = words[0];
        switch (command) {
        case COMMAND_BYE:
            printFarewell();
            break;
        case COMMAND_LIST:
            executeList(taskManager);
            break;
        case COMMAND_DONE:
            String taskId = words[1];
            executeDone(taskManager, taskId);
            break;
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            executeAdd(taskManager, line, command);
            break;
        default:
            printInvalid();
            break;
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        printGreeting();

        // Scanner class for reading user input
        String line;
        Scanner in = new Scanner(System.in);

        // While user has not said "bye", check user input repetitively
        do {
            line = in.nextLine();
            printHorizontalLine();
            execute(taskManager, line);
            printHorizontalLine();
            System.out.print(System.lineSeparator());
        } while (!line.equals(COMMAND_BYE));
    }
}
