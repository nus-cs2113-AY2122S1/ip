import java.util.Scanner;

public class Duke {
    // Horizontal line used for improving readability
    private static String horizontalLine = "____________________________________________________________";

    // Print greeting message
    private static void printGreeting() {
        // Greeting message
        String greeting = horizontalLine + System.lineSeparator() +
                " Hello! I'm Duke" + System.lineSeparator() +
                " What can I do for you?" + System.lineSeparator() +
                horizontalLine + System.lineSeparator();
        System.out.println(greeting);
    }

    // Print farewell message
    private static void printFarewell() {
        // Farewell message
        String farewell = " Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    // Print invalid command warning
    private static void printInvalid() {
        // Invalid command message
        String invalid = " Invalid Command!";
        System.out.println(invalid);
    }

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
        int posDescription = line.indexOf(" ") + 1;
        if (posDescription <= 0) {
            printInvalid();
            return;
        }
        String description = line.substring(posDescription);
        Task task = null;
        switch (command) {
        case "todo":
            task = taskManager.addTodo(description);
            break;
        case "deadline":
            task = taskManager.addDeadline(description);
            break;
        case "event":
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

    private static void execute(String line, TaskManager taskManager) {
        String[] words = line.split(" "); // Convert sentence into array of words
        switch (words[0]) {
        case "bye":
            printFarewell();
            break;
        case "list":
            executeList(taskManager);
            break;
        case "done":
            executeDone(taskManager, words[1]);
            break;
        case "todo":
        case "deadline":
        case "event":
            executeAdd(taskManager, line, words[0]);
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
            System.out.println(horizontalLine);
            execute(line, taskManager);
            System.out.println(horizontalLine + System.lineSeparator());
        } while (!line.equals("bye"));
    }
}
